/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Guatavita
 */
package filtroBackup;

import filtro.*;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import entidad.Oferta;
import entidad.Sector;
import entidad.Solicitud;
import org.zeromq.SocketType;
import static org.zeromq.ZMQ.context;

public class filtroB extends filtro {

    ///Atributos
    private boolean modo = false;
    private static String puertoSector = "5556";
    private static PresentacionFiltro presentacion = new PresentacionFiltro();
    private static Suscripcion subscribe = new Suscripcion();
    private static ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
    private static ArrayList<Solicitud> listaSolicitudes = new ArrayList<Solicitud>();

    private static boolean modalidad = false;

    ///Constructor
    public filtroB() {

    }

    //MAIN
    public static void main(String[] args) throws Exception {

        while (!modalidad) {

            System.out.println("Filtro Backup iniciado");
            try (ZContext context = new ZContext()) {
                ZMQ.Socket healthSocket = context.createSocket(ZMQ.REQ);
                healthSocket.connect("tcp://localhost:5559");
                healthSocket.setReceiveTimeOut(1000);

                String request = "Health";
                System.out.println("are you ok?");
                healthSocket.send(request.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = healthSocket.recv(0);

                if (reply != null) {

                    System.out.println(
                            "Received " + new String(reply, ZMQ.CHARSET));

                } else {

                    modalidad = true;
                }
            }

        }
        System.out.println("Filtro iniciado");
        presentacion.start();
        subscribe.start();

        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received

                byte[] reply = socket.recv(0);

                if (reply.length != 0) {

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

            }
        }
    }

}
