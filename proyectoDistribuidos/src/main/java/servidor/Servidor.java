/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import BD.DHT;
import com.google.common.hash.Hashing;
import entidad.Aspirante;
import entidad.Oferta;
import entidad.Solicitud;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 *
 * @author TUCHYS
 */
public class Servidor {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        DHT dhtServ = new DHT();
        DHT dht = new DHT();
        
        Map<Integer,Oferta> ofertaXSector = new HashMap<>();
        DHT.agregarOferta(dht.getOfertasdeBD()); 
        DHT.dhtSerOferSector();
        ofertaXSector =  DHT.getOfertaSectoS();
        
        Map<Integer,Solicitud> solicitudSectorS=new HashMap<>();
        DHT.agregarSolicitud(dht.getSolicitudesBD());
        solicitudSectorS =  DHT.getSolicitudSectorS();
        
        var ofer = ofertaXSector.values();
        System.out.println("ofertas en dht:");
        ofer.forEach((ofet) -> {
            System.out.println(ofet.getCargo());      
        });
        
        Map<Integer,Oferta> recibeMap;
               
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");
            
            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);
                recibeMap = new HashMap<>();
                // Print the message
                ByteArrayInputStream bs= new ByteArrayInputStream(reply); // bytes es el byte[]
                try (
                ObjectInputStream is = new ObjectInputStream(bs);) {                 
                    recibeMap = (Map<Integer, Oferta>)is.readObject();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
                //ObjectInputStream is = new ObjectInputStream(bs);
                //Oferta ofer = (Oferta)is.readObject();
                //is.close();
                
                var oferRx = recibeMap.values();
                oferRx.forEach((ofet) -> {
                    System.out.println(
                            "Oferta para el cargo: " + ofet.getCargo() + "\n Id sector: "
                                    + ofet.getIDSector() + "\n Id empleador: " +
                                    ofet.getIDempleador() + "\n con habilidades:"
                    );
                });
                /*for (Habilidad habilidade : ofer.habilidades) {
                    System.out.println(habilidade.getNombre() + "\n Con experiencia: " + habilidade.getAnios() + "anios");
               */
                String response = "Hello, world!";
                
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
            }
        }   
//ofertaXSector = DHT.getOfertaSectoS();
        //System.out.println(enviaMap);
        /*Map ofertaXaspirante;

        void relacionOferXaspirante();
        DHT.actualizarBD(List<Oferta> oferRx, List<Solicitud> solRx);
        void verificarMatch();

        void agendarCitaEm();
        void agendarCitaAs();
        void relacionarSolxCapa();
        void comprobacionVacante();
*/}
} 
