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
public class Asiento implements Serializable {

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

    public static Asiento findAsientoById(int id, Lista datos) {
        for (int i = 0; i < datos.size(); i++) {
            if (id == ((Asiento) datos.get(i)).getId()) {
                return ((Asiento) datos.get(i));
            }
        }
        return null;
    }

    public static Asiento findAsientoByNameAndAvion(String asiento, String avion, Lista datos) {
        for (int i = 0; i < datos.size(); i++) {
            if (asiento.compareTo(((Asiento) datos.get(i)).getName()) == 0 && avion.compareTo(((Asiento) datos.get(i)).getAvion().getNombre()) == 0) {
                return ((Asiento) datos.get(i));
            }
        }

        return null;
    }

    public static Asiento findAsientoByNameAndHour(Hora hora, String name) throws SQLException {
        Asiento asiento = null;
        ResultSet rs = DBManager.executeQuery("SELECT a.id, a.name, a.plane, h.status FROM asiento as a INNER JOIN hora_asiento as h WHERE a.id = h.asiento and a.name = ? and h.hora = ?", new String[]{name,String.valueOf(hora.getId())});
        while (rs.next()) {
            asiento = new Asiento(rs.getInt("id"), rs.getString("name"), rs.getBoolean("status"), new Avion(rs.getString("plane")));
        }
        return asiento;
    }

    public static Lista findAsientosDisponiblesFromHour(Hora hora) throws SQLException {
        Lista asientos = new Lista();
        ResultSet rs = DBManager.executeQuery("SELECT a.id, a.name, a.plane, ha.status FROM asiento as a INNER JOIN hora_asiento as ha, horas as h WHERE h.id = ha.hora and ha.status = 1 and a.id = ha.asiento and h.hora = ?", new String[]{hora.getHora()});
        while (rs.next()) {
            asientos.add(new Asiento(rs.getInt("id"), rs.getString("name"), rs.getBoolean("status"), new Avion(rs.getString("plane"))));
        }
        return asientos;
    }

}
