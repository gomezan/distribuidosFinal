/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import entidad.Aspirante;
import entidad.Oferta;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TUCHYS
 */
public class DHT {
    
    static Map<Integer,Oferta> ofertaSecto=new HashMap<>(); 
    static Map<Integer,Aspirante> aspiranteSector=new HashMap<>();
    DBconnect dbConn = new DBconnect();
    DBControl dbCont = new DBControl(dbConn);
    List<Oferta> ofertasdeBD = DBControl.getOfertas();

    public DHT() {
    }

    public static Map<Integer, Oferta> getOfertaSecto() {
        return ofertaSecto;
    }

    public static void setOfertaSecto(Map<Integer, Oferta> ofertaSecto) {
        DHT.ofertaSecto = ofertaSecto;
    }

    public static Map<Integer, Aspirante> getAspiranteSector() {
        return aspiranteSector;
    }

    public static void setAspiranteSector(Map<Integer, Aspirante> aspiranteSector) {
        DHT.aspiranteSector = aspiranteSector;
    }

    public DBconnect getDbConn() {
        return dbConn;
    }

    public void setDbConn(DBconnect dbConn) {
        this.dbConn = dbConn;
    }

    public DBControl getDbCont() {
        return dbCont;
    }

    public void setDbCont(DBControl dbCont) {
        this.dbCont = dbCont;
    }

    public List<Oferta> getOfertasdeBD() {
        return ofertasdeBD;
    }

    public void setOfertasdeBD(List<Oferta> ofertasdeBD) {
        this.ofertasdeBD = ofertasdeBD;
    }
    
    
    public static void main(String[] args) throws IOException
    {
        System.out.println("Corriendo DHT");
        DBconnect dbConn = new DBconnect();
        DBControl dbCont = new DBControl(dbConn);
        
        List<Oferta> ofertasdeBD = DBControl.getOfertas();
        
    }
    
    
    public boolean relOferXSec(){
        
        
        return false;
        
    }
    public static void agregarOferta(List<Oferta> oferta)
    {
       // Map<Integer , Oferta> docUsuario = new HashMap<>();
        for(Oferta ofer : oferta){           
            ofertaSecto.put(ofer.getIDSector(), ofer);
        }
        /*ApiFuture<WriteResult> future = bd.collection("ofertas").document(Integer.toString(oferta.getidOferta())).set(docUsuario);
        try {
        //   System.out.println("Ofertas Update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
        //e.printStackTrace();
        return false;
        }*/        
 
    }
    
}
