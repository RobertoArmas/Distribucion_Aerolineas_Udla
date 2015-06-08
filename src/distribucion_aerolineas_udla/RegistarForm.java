/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribucion_aerolineas_udla;

import Entidades.Asiento;
import Entidades.Avion;
import Entidades.Cliente;
import Entidades.Hora;
import Entidades.Registro;
import GestorInformacion.GestorInformacion;
import Listas.Lista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author robertoarmas
 */
public class RegistarForm extends javax.swing.JFrame {

    private DefaultComboBoxModel clienteModel;
    private DefaultComboBoxModel horaModel;
    private DefaultComboBoxModel asientoModel;
    private Lista horasList;
    private Cliente clienteSeleccionado;
    private Hora horaSeleccionada;
    private Asiento asientoSeleccionado;
    //private Avion avionSeleccionado;

    /**
     * Creates new form RegistarForm
     */
    public RegistarForm() {
        initComponents();
        this.horasList = new Lista();
        this.clienteModel = new DefaultComboBoxModel();
        this.horaModel = new DefaultComboBoxModel();
        this.asientoModel = new DefaultComboBoxModel();

        new GestorInformacion("cliente") {

            @Override
            public void didGetData(Lista datos) {
                for (int i = 0; i < datos.size(); i++) {
                    clienteModel.addElement(((Cliente) datos.get(i)).getCedula());
                }
                clienteSeleccionado = Cliente.findClienteByCedula(clienteModel.getElementAt(0).toString());
            }

            @Override
            public void didFailLoad(String message) {
                System.out.println("ERROR:" + message);
            }

        };

        new GestorInformacion("horas") {

            @Override
            public void didGetData(Lista datos) {
                horasList = datos;
                for (int i = 0; i < datos.size(); i++) {
                    horaModel.addElement(((Hora) datos.get(i)).getHora());
                }
            }

            @Override
            public void didFailLoad(String message) {

            }

        };

        this.horaCheckBox.setModel(this.horaModel);
        this.clienteCheckBox.setModel(this.clienteModel);
        this.asientoCheckBox.setModel(this.asientoModel);

        this.clienteCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*new GestorInformacion("cliente") {

                 @Override
                 public void didGetData(Lista datos) {
                 for (int i = 0; i < datos.size(); i++) {
                 clienteSeleccionado = Cliente.findClienteByCedula((clienteModel.getElementAt(clienteCheckBox.getSelectedIndex()).toString()), datos);

                 }
                 }

                 @Override
                 public void didFailLoad(String message) {
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }
                 };*/

                clienteSeleccionado = Cliente.findClienteByCedula(clienteModel.getElementAt(clienteCheckBox.getSelectedIndex()).toString());
            }

        });

        this.asientoCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (asientoModel.getSize() == 0) {
                } else {
                    try {
                        asientoSeleccionado = Asiento.findAsientoByNameAndHour(horaSeleccionada, asientoModel.getElementAt(asientoCheckBox.getSelectedIndex()).toString());
                        asientoSeleccionado = new Asiento(asientoSeleccionado.getId(), asientoSeleccionado.getName(), false, asientoSeleccionado.getAvion());
                    } catch (SQLException ex) {
                        System.out.println(ex.toString());
                    }
                    /* new GestorInformacion("asiento") {

                     @Override
                     public void didGetData(Lista datos) {
                     for (int i = 0; i < datos.size(); i++) {

                     asientoSeleccionado = Asiento.findAsientoByNameAndAvion(asientoModel.getElementAt(asientoCheckBox.getSelectedIndex()).toString(), avionSeleccionado.getNombre(), datos);
                     asientoSeleccionado = new Asiento(asientoSeleccionado.getId(), asientoSeleccionado.getName(), false, asientoSeleccionado.getAvion());
                     }
                     }

                     @Override
                     public void didFailLoad(String message) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }
                     };
                     */
                }
            }

        });

        this.horaCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                asientoModel.removeAllElements();
                try {
                    horaSeleccionada = Hora.findHoraByHora((horaModel.getElementAt(horaCheckBox.getSelectedIndex()).toString()));
                    // avionSeleccionado = horaSeleccionada.getAvion();

                    Lista datos = Asiento.findAsientosDisponiblesFromHour(horaSeleccionada);
                    for (int i = 0; i < datos.size(); i++) {

                        asientoModel.addElement(((Asiento) datos.get(i)).getName());

                    }
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                /* new GestorInformacion("asiento") {

                 @Override
                 public void didGetData(Lista datos) {
                 for (int i = 0; i < datos.size(); i++) {

                 if (((Asiento) datos.get(i)).getAvion().getNombre().compareTo(horaSeleccionada.getAvion().getNombre()) == 0 && ((Asiento) datos.get(i)).getDisponible() == true) {
                 asientoModel.addElement(((Asiento) datos.get(i)).getName());

                 }

                 }
                 }

                 @Override
                 public void didFailLoad(String message) {
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 };*/

            }
        });

    }

    private void guardarAsientos(Lista asientos) {

        new GestorInformacion("asiento", "w", asientos) {

            @Override
            public void didGetData(Lista datos) {
                System.out.println("Guardo");
                App app = new App();
                app.show();
                dispose();
            }

            @Override
            public void didFailLoad(String message) {
                System.out.println("Error");
            }

        };
    }

    private Boolean validarCampos() {
        if (clienteSeleccionado == null || horaSeleccionada == null || asientoSeleccionado == null) {
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registrarBtn = new javax.swing.JButton();
        clienteCheckBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        addClientBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        horaCheckBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        asientoCheckBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        registrarBtn.setText("Registrar");
        registrarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registrarBtnMouseClicked(evt);
            }
        });

        jLabel1.setText("Cliente");

        addClientBtn.setText("Agregar Nuevo");
        addClientBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addClientBtnMouseClicked(evt);
            }
        });

        jLabel2.setText("Hora");

        jLabel3.setText("Asiento");

        asientoCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                asientoCheckBoxMouseClicked(evt);
            }
        });

        jLabel4.setText("Registrar Vuelo");

        cancelBtn.setText("Cancelar");
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelBtnMouseClicked(evt);
            }
        });
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(clienteCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addClientBtn))
                                    .addComponent(horaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(asientoCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(cancelBtn)
                        .addGap(109, 109, 109)
                        .addComponent(registrarBtn)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clienteCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addClientBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(horaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(asientoCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrarBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addClientBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addClientBtnMouseClicked
        // TODO add your handling code here:
        AgregarClienteForm addClientForm = new AgregarClienteForm();
        addClientForm.show();
    }//GEN-LAST:event_addClientBtnMouseClicked

    private void registrarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarBtnMouseClicked
        // TODO add your handling code here:
        //Registrar
        if (validarCampos()) {

            Registro nuevo = new Registro(0, clienteSeleccionado, horaSeleccionada, asientoSeleccionado, true);
            try {
                if (nuevo.insert() == 1) {
                    System.out.println("Guardo");
                    App app = new App();
                    app.show();
                    dispose();
                } else {
                    System.out.println("No guardo");
                }

            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }

            /* new GestorInformacion("registro") {
             @Override
             public void didGetData(Lista datos) {

             Registro nuevo = new Registro(datos.size() + 1, clienteSeleccionado, horaSeleccionada, asientoSeleccionado, "Vendido");
             datos.add(nuevo);

             new GestorInformacion("registro", "w", datos) {
             @Override
             public void didGetData(Lista datos) {

             new GestorInformacion("asiento") {

             @Override
             public void didGetData(Lista datos) {
             for (int i = 0; i < datos.size(); i++) {
             if (asientoSeleccionado.getId() == ((Asiento) datos.get(i)).getId()) {
             datos.getNode(i).setDato(asientoSeleccionado);
             }
             }
             guardarAsientos(datos);
             }

             @Override
             public void didFailLoad(String message) {

             }

             };
             }

             @Override
             public void didFailLoad(String message) {
             System.out.println("No guardo");
             }

             };
             }

             @Override
             public void didFailLoad(String message) {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

             };*/
        } else {
            System.out.println("Seleccione ");
        }

    }//GEN-LAST:event_registrarBtnMouseClicked

    private void asientoCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_asientoCheckBoxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_asientoCheckBoxMouseClicked

    private void cancelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMouseClicked
        // TODO add your handling code here:
        App app = new App();
        app.show();
        this.dispose();
    }//GEN-LAST:event_cancelBtnMouseClicked

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelBtnActionPerformed

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
            java.util.logging.Logger.getLogger(RegistarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistarForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClientBtn;
    private javax.swing.JComboBox asientoCheckBox;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox clienteCheckBox;
    private javax.swing.JComboBox horaCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton registrarBtn;
    // End of variables declaration//GEN-END:variables

    private DefaultListModel DefaultListModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
