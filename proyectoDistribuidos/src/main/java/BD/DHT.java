/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import entidad.*;
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
    //dht para cada servidor que relaciona oferta con sector
    static Map<Integer,Oferta> ofertaSectoS=new HashMap<>(); 
    
   
    static Map<Integer,Aspirante> aspiranteSector=new HashMap<>();
     //dht para cada servidor que relaciona aspirante con sector  
    static Map<Integer,Aspirante> aspiranteSectorS=new HashMap<>();
    
    static Map<Integer,Solicitud> solicitudSector=new HashMap<>();
     //dht para cada servidor que relaciona solicitud con sector  
    static Map<Integer,Solicitud> solicitudSectorS=new HashMap<>();
    
    //Instancia conexion BD
    DBconnect dbConn = new DBconnect();
    //Control de consultas BD
    DBControl dbCont = new DBControl(dbConn);
    
    //Lectura de BD almacenado en listas de entidades 
    List<Oferta> ofertasdeBD = DBControl.getOfertas();
    List<Solicitud> solicitudesBD = DBControl.getSolicitudes();

     //Indicador de servidores
    static int NumServidor = 1;
    static int indice = 0;
    public DHT() {
    }

        
    public static void main(String[] args) throws IOException
    {
        System.out.println("Corriendo DHT");
        DBconnect dbConn = new DBconnect();
        DBControl dbCont = new DBControl(dbConn);
        
        List<Oferta> ofertasdeBD = DBControl.getOfertas();   
        List<Solicitud> solicitudesBD = DBControl.getSolicitudes();
        
        DHT.agregarOferta(ofertasdeBD); 
        DHT.agregarSolicitud(solicitudesBD);
        Map<Integer,Oferta> ofertaSecto = DHT.getOfertaSecto();
        Map<Integer,Solicitud> solicitudSector = DHT.getSolicitudSector();
        
        match(ofertaSecto, solicitudSector);
    }
        
    public static Map<Integer, Oferta> getOfertaSectoS() {
        return ofertaSectoS;
    }

    public static void setOfertaSectoS(Map<Integer, Oferta> ofertaSectoS) {
        DHT.ofertaSectoS = ofertaSectoS;
    }

    public static Map<Integer, Aspirante> getAspiranteSectorS() {
        return aspiranteSectorS;
    }

    public static void setAspiranteSectorS(Map<Integer, Aspirante> aspiranteSectorS) {
        DHT.aspiranteSectorS = aspiranteSectorS;
    }

    public static Map<Integer, Solicitud> getSolicitudSector() {
        return solicitudSector;
    }

    public static void setSolicitudSector(Map<Integer, Solicitud> solicitudSector) {
        DHT.solicitudSector = solicitudSector;
    }

    public static Map<Integer, Solicitud> getSolicitudSectorS() {
        return solicitudSectorS;
    }

    public static void setSolicitudSectorS(Map<Integer, Solicitud> solicitudSectorS) {
        DHT.solicitudSectorS = solicitudSectorS;
    }

    public List<Solicitud> getSolicitudesBD() {
        return solicitudesBD;
    }

    public void setSolicitudesBD(List<Solicitud> solicitudesBD) {
        this.solicitudesBD = solicitudesBD;
    }

    public static int getNumServidor() {
        return NumServidor;
    }

    public static void setNumServidor(int NumServidor) {
        DHT.NumServidor = NumServidor;
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
        
    public boolean relOferXSec(){ 
        return false;
        
    }
    
    public static void agregarOferta(List<Oferta> oferta)
    {
        for(Oferta ofer : oferta){           
            ofertaSecto.put(ofer.getIDSector(), ofer);
        }
    }
    
    public static void agregarSolicitud(List<Solicitud> solicitudes)
    {
        for(Solicitud sol: solicitudes){           
            solicitudSector.put(sol.getIdSector(), sol);
        }
    }
    
    public static void dhtSerOferSector(){
        ofertaSectoS = new HashMap<>();
        int tamanio = ofertaSecto.size();
        int partes = tamanio/3;        
        var ofer = ofertaSecto.values();
        ofer.forEach((ofet) -> {
            if((NumServidor == 1) && (indice < partes)){
                ofertaSectoS.put(ofet.getIDSector(),ofet);
            }else if((NumServidor == 2) && (indice < partes*2)){
                ofertaSectoS.put(ofet.getIDSector(),ofet);                
            }else if((NumServidor == 3) && (indice >= partes*2)){
                ofertaSectoS.put(ofet.getIDSector(),ofet);                    
            }
            indice++;
        });
        indice = 0;
    } 
    
    public static void dhtSerAspSector(){
        aspiranteSectorS=new HashMap<>();
        int tamanio = aspiranteSector.size();
        int partes = tamanio/3;
        var ofer = aspiranteSector.values();
        ofer.forEach((ofet) -> {
            if((NumServidor == 1) && (indice < partes)){
                aspiranteSectorS.put(ofet.getCodigo(),ofet);
            }else if((NumServidor == 2) && (indice < partes*2)){
                aspiranteSectorS.put(ofet.getCodigo(),ofet);                
            }else if((NumServidor == 3) && (indice >= partes*2)){
                aspiranteSectorS.put(ofet.getCodigo(),ofet);                    
            } 
            indice++;
        }); 
        indice=0;
    }
    
    public static DHT dhtSerOferSector(DHT dht){
        solicitudSectorS=new HashMap<>();
        int tamanio = solicitudSector.size();
        int partes = tamanio/3;
        var ofer = solicitudSector.values();
        ofer.forEach((ofet) -> {
            if((NumServidor == 1) && (indice < partes)){
                solicitudSectorS.put(ofet.getIdSector(),ofet);
            }else if((NumServidor == 2) && (indice < partes*2)){
                solicitudSectorS.put(ofet.getIdSector(),ofet);                
            }else if((NumServidor == 3) && (indice >= partes*2)){
                solicitudSectorS.put(ofet.getIdSector(),ofet);                    
            }  
            indice++;
        });
        NumServidor++;
        return null;    
    }
    
    public static void actualizaOfertas(List<Oferta> ofertas){
        ofertas.forEach((ofet) -> {
            //ofet.setIDempleador(1);
            System.out.println(ofet.getCargo()+" "+ofet.getIDempleador());
            DBControl.addOferta(ofet);
        });   
    }
    
    public static void actualizaSolicitudes(List<Solicitud> solicitudes){
        solicitudes.forEach((sol) -> {
            DBControl.addSolicitud(sol);
        });   
    }
    
    public static void match(Map<Integer,Oferta> mapaOfertas, Map<Integer,Solicitud> mapaSolicitud){
        //var ofer = ofertaSecto.values();
        System.out.println("Tamao"+mapaOfertas.size());
        for ( Integer key : mapaOfertas.keySet() ) {
            if(mapaSolicitud.containsKey(key)){
            
            }
            System.out.println("llave "+ key );
        }
    }
    
}
