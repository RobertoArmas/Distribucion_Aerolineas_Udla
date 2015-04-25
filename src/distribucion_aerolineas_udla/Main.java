/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package distribucion_aerolineas_udla;

import Entidades.Asiento;
import Entidades.Avion;
import Entidades.Hora;
import Entidades.Registro;
import GestorInformacion.GestorInformacion;
import Listas.Lista;

/**
 *
 * @author cisco
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       App aplication = new App();
        aplication.setTitle("Sistema de Aerolíneas");
        aplication.show();
        
       /* new GestorInformacion("registro"){

            @Override
            public void didGetData(Lista datos) {
                for(int i=0;i<datos.size();i++){
                    System.out.println(((Registro)datos.get(i)).getAsiento().getName());
                }
            }

            @Override
            public void didFailLoad() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };*/
     
       
        
        
    }
    
    
    
}
