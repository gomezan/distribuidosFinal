/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TUCHYS
 */
public class DBconnect {
    public static Connection getConnection() throws SQLException{	 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }
        // usuario y clave
        String user = "root";
        String password = "";
        // una sola cadena de conexión, en un sólo parámetro se concatena el
        // usuario y el password
        String url1 = "jdbc:mysql://localhost:3306/dht?user=" + user + "&pasword="+password+"&serverTimezone=UTC";
        Connection conexion = (Connection) DriverManager.getConnection(url1);
        return conexion;
    }
}
