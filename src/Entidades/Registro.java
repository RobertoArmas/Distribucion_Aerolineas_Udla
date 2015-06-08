/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import GestorInformacion.DBManager;
import Listas.Lista;
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

    public static Lista allRegistro() throws SQLException {
        Lista registros = new Lista();
        ResultSet rs = DBManager.executeQuery("SELECT r.id, r.ci, c.name, c.lastname, c.phone, c.address, h.id as hora_id, h.hora, h.plane,"
                + "a.id as asiento_id, a.name as asiento, ha.status as asiento_status,  r.status FROM registro as r INNER JOIN "
                + "hora_asiento as ha, horas as h, asiento as a, cliente as c WHERE r.ci = c.id and h.id = ha.hora and a.id = ha.asiento "
                + "AND r.asiento_id = ha.id");
             
        while(rs.next()) {
            registros.add(new Registro(rs.getInt("id"),
                    new Cliente(rs.getString("ci"), rs.getString("name"), rs.getString("lastname"),
                            rs.getString("phone"), rs.getString("address")),
                    new Hora(rs.getInt("hora_id"), rs.getString("hora"),
                            new Avion(rs.getString("plane"))),
                    new Asiento(rs.getInt("asiento_id"), rs.getString("asiento"),
                            rs.getBoolean("asiento_status"),
                            new Avion(rs.getString("plane"))),
                    rs.getBoolean("status")));
            
        }   
        return registros;
    }
    
    public int saveCancelar() throws SQLException{
        
        return DBManager.executeUpdate("UPDATE registro set status=0 WHERE id=?",new String[]{String.valueOf(this.id)});
    }
}
