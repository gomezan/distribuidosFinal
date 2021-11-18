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
import java.util.ArrayList;
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DHT dhtServ = new DHT();
        DHT dht = new DHT();

        Map<Integer, Oferta> ofertaXSector = new HashMap<>();
        DHT.agregarOferta(dht.getOfertasdeBD());
        DHT.dhtSerOferSector();
        ofertaXSector = DHT.getOfertaSectoS();

        Map<Integer, Solicitud> solicitudSectorS = new HashMap<>();
        DHT.agregarSolicitud(dht.getSolicitudesBD());
        solicitudSectorS = DHT.getSolicitudSectorS();

        var ofer = ofertaXSector.values();
        System.out.println("ofertas en dht:");
        ofer.forEach((ofet) -> {
            System.out.println(ofet.getCargo());
        });


        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5550");

            while (!Thread.currentThread().isInterrupted()) {

                byte[] reply = socket.recv(0);

                String entrada = new String(reply, ZMQ.CHARSET);

                System.out.println(
                        "Received : [" + entrada + "]"
                );

                if (entrada.equals("Ofertas")) {

                    String response = "OK";
                    socket.send(response.getBytes(ZMQ.CHARSET), 0);
                    byte[] lista = socket.recv(0);
                   
                    ByteArrayInputStream bs = new ByteArrayInputStream(lista);
                     ArrayList<Oferta> listaOfertas;
                    try (
                            ObjectInputStream is = new ObjectInputStream(bs);) {
                        listaOfertas = (ArrayList<Oferta>) is.readObject();
                        DHT.actualizaOfertas(listaOfertas);
                    } catch (IOException ioe) {
                        System.out.println(ioe);
                    }

                } else if (entrada.equals("Solicitudes")) {

                    String response = "OK";
                    socket.send(response.getBytes(ZMQ.CHARSET), 0);
                     byte[] lista = socket.recv(0);
                   
                    ByteArrayInputStream bs = new ByteArrayInputStream(lista);
                     ArrayList<Solicitud> listaSolicitudes;
                    try (
                            ObjectInputStream is = new ObjectInputStream(bs);) {
                        listaSolicitudes = (ArrayList<Solicitud>) is.readObject();
                    } catch (IOException ioe) {
                        System.out.println(ioe);
                    }

                } else {

                    String response = "NOK";
                    socket.send(response.getBytes(ZMQ.CHARSET), 0);
                }

                //recibeMap = new HashMap<>();

                //ObjectInputStream is = new ObjectInputStream(bs);
                //Oferta ofer = (Oferta)is.readObject();
                //is.close();
                
                /*
                var oferRx = recibeMap.values();
                oferRx.forEach((ofet) -> {
                    System.out.println(
                            "Oferta para el cargo: " + ofet.getCargo() + "\n Id sector: "
                            + ofet.getIDSector() + "\n Id empleador: "
                            + ofet.getIDempleador() + "\n con habilidades:"
                    );
                });
                */
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
         */
    }
}
