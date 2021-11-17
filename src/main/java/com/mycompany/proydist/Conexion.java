/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proydist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author puma0
 */
public class Conexion {
	
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
		String url1 = "jdbc:mysql://localhost:3306/dht?user=" + user + "&pasword="+password;
		Connection conexion = (Connection) DriverManager.getConnection(url1);
		return conexion;
	}
}


