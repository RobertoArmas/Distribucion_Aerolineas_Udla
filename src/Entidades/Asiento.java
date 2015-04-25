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
public class Asiento implements Serializable{
    
    private int id;
    private String name;
    private Boolean disponible;
    private Avion avion;

    public Asiento(int id, String name, Boolean disponible, Avion avion) {
        this.id = id;
        this.name = name;
        this.disponible = disponible;
        this.avion = avion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    public static Asiento findAsientoById(int id, Lista datos){
        for(int i=0;i<datos.size();i++){
            if(id == ((Asiento)datos.get(i)).getId()){
                return ((Asiento)datos.get(i));
            }
        }
        return null;
    }
    
    
}
