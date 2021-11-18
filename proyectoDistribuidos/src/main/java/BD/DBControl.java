/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import entidad.Oferta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TUCHYS
 */
public class DBControl {
    
    public DBControl(DBconnect conex) {

        try(         
                Connection  conexDHT= (Connection) DBconnect.getConnection();
                PreparedStatement ps = (PreparedStatement) conexDHT.prepareStatement("SELECT * FROM `dht`.`empleador`");
                ResultSet rs= ps.executeQuery();
        ){
            while(rs.next()){
                //ps.executeUpdate();
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }
        }catch(SQLException ex){
            Logger lgr = Logger.getLogger(DBControl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
       } 
    }

    DBControl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static List<Oferta> getOfertas() {
        List<Oferta> ofertasBD = new ArrayList<Oferta>();
        try(	            
                Connection  conexDHT= (Connection) DBconnect.getConnection();
                PreparedStatement ps = (PreparedStatement) conexDHT.prepareStatement("SELECT * FROM `dht`.`ofertas`");
                ResultSet rs= ps.executeQuery();
        ){
            while(rs.next()){
                Oferta aux = new Oferta();
                System.out.println(rs.getString(1));
                var auxN = ((Number) rs.getObject(1)).intValue();
                aux.setId(auxN);
                aux.setCargo(rs.getString(2));
                auxN = ((Number) rs.getObject(3)).intValue();
                aux.setIDempleador(auxN);
                aux.setCargo(rs.getString(2));
                auxN = ((Number) rs.getObject(4)).intValue();
                aux.setIDSector(auxN);
                
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                ofertasBD.add(aux);
                }
        }catch(SQLException ex){
                    System.out.println(ex);
        }
        return ofertasBD;
    }
    
}
