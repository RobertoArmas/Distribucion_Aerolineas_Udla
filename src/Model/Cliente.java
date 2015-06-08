/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GestorInformacion.DBManager;
import Listas.Lista;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author robertoarmas
 */
public class Cliente implements Serializable {

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

    public static Cliente findClienteByCedula(String cedula) {
        Cliente cliente = null;
        try {
            ResultSet rs = DBManager.executeQuery("SELECT * FROM cliente WHERE id=?", new String[]{cedula});
            if (rs.first()) {
                cliente = new Cliente(rs.getString("id"), rs.getString("name"), rs.getString("lastname"), rs.getString("phone"), rs.getString("address"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return cliente;
    }

    public static Boolean isExistCliente(String cedula, Lista datos) {
        for (int i = 0; i < datos.size(); i++) {
            if (cedula.compareTo(((Cliente) datos.get(i)).getCedula()) == 0) {
                return true;
            }
        }
        return false;
    }

    public static Lista getAllClientes() throws SQLException {
        Lista clientes = new Lista();
        ResultSet rs = DBManager.executeQuery("SELECT * FROM cliente");
        while (rs.next()) {
            clientes.add(new Cliente(rs.getString("id"), rs.getString("name"), rs.getString("lastname"), rs.getString("phone"), rs.getString("address")));
        }
        return clientes;
    }

    public int insert() throws SQLException {
        return DBManager.executeUpdate("INSERT INTO cliente (id,name,lastname,phone,address) VALUES (?,?,?,?,?)", new String[]{this.cedula, this.name, this.apellido,this.telefono,this.direccion});

    }
}
