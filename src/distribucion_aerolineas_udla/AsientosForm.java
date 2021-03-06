/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribucion_aerolineas_udla;

import Model.Asiento;
import GestorInformacion.GestorInformacion;
import Listas.Lista;
import javax.swing.DefaultListModel;

/**
 *
 * @author robertoarmas
 */
public class AsientosForm extends javax.swing.JFrame {

    private DefaultListModel vendidosModel;
    private DefaultListModel disponiblesModel;
    private Lista asientosLista;

    /**
     * Creates new form AsientosForm
     */
    public AsientosForm() {
        initComponents();
        vendidosModel = new DefaultListModel();
        disponiblesModel = new DefaultListModel();
        asientosLista = new Lista();
        new GestorInformacion("asiento") {

            @Override
            public void didGetData(Lista datos) {
                asientosLista = datos;
                updateList();
            }

            @Override
            public void didFailLoad(String message) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        this.disponiblesList.setModel(disponiblesModel);
        this.vendidosList.setModel(vendidosModel);
    }

    private void updateList() {
        disponiblesModel.removeAllElements();
        vendidosModel.removeAllElements();
        for (int i = 0; i < asientosLista.size(); i++) {
            if (((Asiento) asientosLista.get(i)).getDisponible()) {
                disponiblesModel.addElement(((Asiento) asientosLista.get(i)).getName() + "-" + ((Asiento) asientosLista.get(i)).getAvion().getNombre());

            } else {
                vendidosModel.addElement(((Asiento) asientosLista.get(i)).getName() + "-" + ((Asiento) asientosLista.get(i)).getAvion().getNombre());

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        vendidosList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        disponiblesList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        avionTxtField = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        actualizarBtn = new javax.swing.JButton();
        cerrarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(vendidosList);

        jScrollPane2.setViewportView(disponiblesList);

        jLabel1.setText("Asientos");

        jLabel2.setText("Avion:");

        buscarBtn.setText("Buscar");
        buscarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarBtnMouseClicked(evt);
            }
        });

        jLabel3.setText("Vendidos");

        jLabel4.setText("Disponibles");

        actualizarBtn.setText("Actualizar");
        actualizarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualizarBtnMouseClicked(evt);
            }
        });
        actualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarBtnActionPerformed(evt);
            }
        });

        cerrarBtn.setText("Cerrar");
        cerrarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(avionTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarBtn)
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(81, 81, 81))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(actualizarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cerrarBtn)
                        .addGap(120, 120, 120))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(avionTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscarBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actualizarBtn)
                    .addComponent(cerrarBtn))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarBtnMouseClicked
        disponiblesModel.removeAllElements();
        vendidosModel.removeAllElements();
        for (int i = 0; i < asientosLista.size(); i++) {
            if (this.avionTxtField.getText().compareTo(((Asiento) asientosLista.get(i)).getAvion().getNombre()) == 0) {
                if (((Asiento) asientosLista.get(i)).getDisponible()) {
                    disponiblesModel.addElement(((Asiento) asientosLista.get(i)).getName() + "-" + ((Asiento) asientosLista.get(i)).getAvion().getNombre());

                } else {
                    vendidosModel.addElement(((Asiento) asientosLista.get(i)).getName() + "-" + ((Asiento) asientosLista.get(i)).getAvion().getNombre());
                }

            }

        }
    }//GEN-LAST:event_buscarBtnMouseClicked

    private void actualizarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarBtnMouseClicked
        updateList();
        this.avionTxtField.setText("");
    }//GEN-LAST:event_actualizarBtnMouseClicked

    private void cerrarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarBtnMouseClicked
        App app = new App();
        app.show();
        this.dispose();
    }//GEN-LAST:event_cerrarBtnMouseClicked

    private void actualizarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_actualizarBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AsientosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AsientosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AsientosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AsientosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AsientosForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizarBtn;
    private javax.swing.JTextField avionTxtField;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton cerrarBtn;
    private javax.swing.JList disponiblesList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList vendidosList;
    // End of variables declaration//GEN-END:variables
}
