/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorInformacion;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robertoarmas
 */
public class DBManager {

    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "base_aerolinea";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return (Connection) DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_NAME, DB_USER, DB_PASSWD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection cn = getConnection();
        PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        // Permite cerrar la conexion que se establecio con la base de datos
        CachedRowSetImpl rowsetImpl = new CachedRowSetImpl();
        rowsetImpl.populate(rs);
        ps.close();
        cn.close();
        return rowsetImpl;
    }
    
    public static ResultSet executeQuery(String sql, String[] columNames) throws SQLException {
        Connection cn = getConnection();
        PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
        for(int i=0;i<columNames.length;i++){
            ps.setObject(i+1,columNames[i]);
        }
        ResultSet rs = ps.executeQuery();
        // Permite cerrar la conexion que se establecio con la base de datos
        CachedRowSetImpl rowsetImpl = new CachedRowSetImpl();
        rowsetImpl.populate(rs);
        ps.close();
        cn.close();
        return rowsetImpl;
    }
    
        public static int executeUpdate(String sql, String[] columNames) throws SQLException {
        Connection cn = getConnection();
        PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
        for(int i=0;i<columNames.length;i++){
            ps.setObject(i+1,columNames[i]);
        }
        int rs = ps.executeUpdate();
        // Permite cerrar la conexion que se establecio con la base de datos
       
        ps.close();
        cn.close();
        return rs;
    }
    

}
