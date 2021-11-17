package com.mycompany.proydist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Control {
	public Control(Conexion conex) {
	
	try(         
			Connection  conexDHT= (Connection) Conexion.getConnection();
            PreparedStatement ps = (PreparedStatement) conexDHT.prepareStatement("SELECT * FROM `dht`.`empleador`");
			ResultSet rs= ps.executeQuery();
            ){
			while(rs.next()){
            //ps.executeUpdate();
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
			}
        }catch(SQLException ex){
        	Logger lgr = Logger.getLogger(Control.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            //Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null,ex);
        } 
	}
}
