/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import GestorInformacion.DBManager;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author robertoarmas
 */
public class Registro implements Serializable {

    public int id;
    public Cliente cliente;
    public Hora hora;
    public Asiento asiento;
    public Boolean status;

    public Registro(int id, Cliente cliente, Hora hora, Asiento asiento, Boolean status) {
        this.id = id;
        this.cliente = cliente;
        this.hora = hora;
        this.asiento = asiento;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int insert() throws SQLException {
        String asiento_id;
        ResultSet rs = DBManager.executeQuery("SELECT id FROM hora_asiento WHERE hora = ? and asiento = ?",
                new String[]{String.valueOf(this.hora.getId()),
                    String.valueOf(this.asiento.getId())});
        if (rs.first()) {
            asiento_id = rs.getString("id");
            if (DBManager.executeUpdate("UPDATE hora_asiento SET status=0 WHERE id=?", new String[]{asiento_id}) == 1) {

                return DBManager.executeUpdate("INSERT INTO registro (ci,asiento_id,`status`) VALUES (?,?,?)", new String[]{this.cliente.getCedula(), asiento_id, "1"});
            }
        }
        
        return 0;

    }

}
