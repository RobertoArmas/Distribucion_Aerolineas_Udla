/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Listas.Lista;
import java.io.Serializable;

/**
 *
 * @author robertoarmas
 */
public class Hora implements Serializable{
    
    private int id;
    private String hora;
    private Avion avion;
    
    public Hora(int id, String hora, Avion avion){
        this.id = id;
        this.hora = hora;
        this.avion = avion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }


    @Override
    public String toString() {
        return this.hora; //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public static Hora findHoraById(int id, Lista datos){
        for(int i=0;i<datos.size();i++){
            if(id == ((Hora)datos.get(i)).getId()){
                return ((Hora)datos.get(i));
            }
        }
        return null;
    }
       public static Hora findHoraByHora(String hora, Lista datos){
        for(int i=0;i<datos.size();i++){
            if(hora.compareTo(((Hora)datos.get(i)).getHora()) == 0){
                return ((Hora)datos.get(i));
            }
        }
        return null;
    }
    
    
    
}
