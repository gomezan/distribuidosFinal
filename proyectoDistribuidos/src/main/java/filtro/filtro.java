/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Guatavita
 */
package filtro;

import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import entidad.Oferta;
import entidad.Sector;
import entidad.Solicitud;

public class filtro {

    ///Atributos
    private boolean modo = false;
    private static String puertoSector = "5556";
    private static PresentacionFiltro presentacion = new PresentacionFiltro();
    private static Suscripcion subscribe = new Suscripcion();
    private static ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
    private static ArrayList<Solicitud> listaSolicitudes = new ArrayList<Solicitud>();

    ///Constructor
    public filtro() {

    }

    ///Métodos
    private static void imprimirOfertas() {

        for (Oferta offer : listaOfertas) {

            System.out.println("*****************************");
            System.out.println(offer.getId());
            System.out.println(offer.getCargo());
        }
    }

    private static void imprimirSolicitudes() {

        for (Solicitud sol : listaSolicitudes) {

            System.out.println("*****************************");
            System.out.println(sol.getCodigo());
            System.out.println(sol.getIdSector());
        }
    }

    public static boolean autentificar(String entrada) {

        String[] parts = entrada.split("-");

        String user = parts[0];
        String password = parts[1];

        return (filtro.presentacion.autentificar(user, password));
    }

    public static void agregarOferta(byte[] oferta) throws ClassNotFoundException {

        Oferta ofer = new Oferta();
        // Print the message
        ByteArrayInputStream bs = new ByteArrayInputStream(oferta); // bytes es el byte[]
        try (
                ObjectInputStream is = new ObjectInputStream(bs);) {
            ofer = (Oferta) is.readObject();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        listaOfertas.add(ofer);
        //if (listaOfertas.size() == 10) {
            filtro.presentacion.enviarOfertas(listaOfertas);
            listaOfertas = new ArrayList<Oferta>();
        //}

        imprimirOfertas();

    }

    public static void agregarSolicitud(byte[] solucion) throws ClassNotFoundException {

        Solicitud sol = new Solicitud();
        // Print the message
        ByteArrayInputStream bs = new ByteArrayInputStream(solucion); // bytes es el byte[]
        try (
                ObjectInputStream is = new ObjectInputStream(bs);) {
            sol = (Solicitud) is.readObject();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        listaSolicitudes.add(sol);
        if (listaSolicitudes.size() == 10) {
            filtro.presentacion.enviarSolicitudes(listaSolicitudes);
            listaSolicitudes = new ArrayList<Solicitud>();
        }

        imprimirSolicitudes();

    }

    //MAIN
    public static void main(String[] args) throws Exception {
        System.out.println("Filtro iniciado");

        //configurarPresentacion();
        presentacion.start();
        subscribe.start();

        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");
            socket.setReceiveTimeOut(500);

            ZMQ.Socket socketHealth = context.createSocket(ZMQ.REP);
            socketHealth.bind("tcp://*:5559");
            socketHealth.setReceiveTimeOut(500);

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received

                byte[] reply = socket.recv(0);

                if (reply != null) {

                    String entrada = new String(reply, ZMQ.CHARSET);

                    System.out.println(
                            "Received : [" + entrada + "]"
                    );

                    // Send a response
                    if (autentificar(entrada)) {

                        //Revisar solicitud cliente
                        String[] parts = entrada.split("-");

                        if (parts[2].equals("subscribir")) {
                            String response = puertoSector;
                            socket.send(response.getBytes(ZMQ.CHARSET), 0);
                        } else if (parts[2].equals("agregarOferta")) {
                            String response = "OK";
                            socket.send(response.getBytes(ZMQ.CHARSET), 0);
                            byte[] reply2 = socket.recv(0);
                            agregarOferta(reply2);
                            socket.send(response.getBytes(ZMQ.CHARSET), 0);
                        } else if (parts[2].equals("agregarSolicitud")) {
                            String response = "OK";
                            socket.send(response.getBytes(ZMQ.CHARSET), 0);
                            byte[] reply2 = socket.recv(0);
                            agregarSolicitud(reply2);
                            socket.send(response.getBytes(ZMQ.CHARSET), 0);
                        } else {
                            socket.send("NOK".getBytes(ZMQ.CHARSET), 0);
                        }

                    } else {
                        String response = "NOK";
                        socket.send(response.getBytes(ZMQ.CHARSET), 0);
                    }

                }

                byte[] salud = socketHealth.recv(0);

                if (salud != null) {
                    System.out.println(
                            "Received " + new String(salud, ZMQ.CHARSET));

                    String response = "OK";
                    socketHealth.send(response.getBytes(ZMQ.CHARSET), 0);

                }

            }
        }
    }

}
