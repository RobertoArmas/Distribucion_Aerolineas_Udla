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
public class Cliente implements Serializable{
    
    private String cedula;
    private String name;
    private String apellido;
    private String telefono;
    private String direccion;

    public Cliente(String cedula, String name, String apellido, String telefono, String direccion) {
        this.cedula = cedula;
        this.name = name;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
      public static Cliente findClienteByCedula(String name, Lista datos) {
        for (int i = 0; i < datos.size(); i++) {
            if (name.compareTo(((Cliente) datos.get(i)).getCedula()) == 0) {
                return ((Cliente) datos.get(i));
            }
        }
        return null;
    }
    
    
    
    
}
