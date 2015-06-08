/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Listas.Lista;
import java.io.Serializable;

/**
 *
 * @author robertoarmas
 */
public class Avion implements Serializable {

    private String nombre;

    public Avion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return this.nombre;
    }

    public static Avion findAvionByName(String name, Lista datos) {
        for (int i = 0; i < datos.size(); i++) {
            if (name.compareTo(((Avion) datos.get(i)).getNombre()) == 0) {
                return ((Avion) datos.get(i));
            }
        }
        return null;
    }

}
