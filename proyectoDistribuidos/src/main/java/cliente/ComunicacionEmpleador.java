/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

/**
 *
 * @author Guatavita
 */
import java.util.StringTokenizer;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import entidad.Oferta;
import entidad.Sector;

/**
 *
 * @author Guatavita
 */
public class ComunicacionEmpleador {

    ///Atributos
    //Metodos
    /**
     *
     * @param entrada
     */
    public static String solicitarPuerto(ZMQ.Socket socket) {

        String request = "gomezan-97080703620-subscribir";
        //System.out.println("Sending Hello ");
        socket.send(request.getBytes(ZMQ.CHARSET), 0);
        byte[] reply = socket.recv(0);
        String puerto = new String(reply, ZMQ.CHARSET);
        System.out.println("Received " + puerto);

        return puerto;

    }

    public static void enviarOferta(ZMQ.Socket socket, Oferta oferta) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(out)) {
            ois.writeObject(oferta);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        byte[] data = out.toByteArray();
        socket.send(data);

        byte[] reply2 = socket.recv(0);
        String entrada2 = new String(reply2, ZMQ.CHARSET);
        System.out.println("Received " + entrada2);

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Filtro Test iniciado");

        try (ZContext context = new ZContext()) {

            //  Comunicación Filtro
            System.out.println("Connecting to hello world server");
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");
            

            ZMQ.Socket socketServer = context.createSocket(SocketType.REP);
            socketServer.bind("tcp://*:5554");
            socketServer.setReceiveTimeOut(10);

            //Envia oferta
            String request2 = "gomezan-97080703620-agregarOferta-";

            byte[] encabezado = request2.getBytes(ZMQ.CHARSET);
            socket.send(encabezado, 0);

            byte[] reply = socket.recv(0);
            String respuesta = new String(reply, ZMQ.CHARSET);
            System.out.println("Received " + respuesta);

            if (respuesta.equals("OK")) {

                Oferta oferta = new Oferta();
                oferta.setId(1);
                oferta.setCargo("Secretaria");

                enviarOferta(socket, oferta);

            }

            //Se suscribe
            String puerto = solicitarPuerto(socket);

            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://localhost:"+puerto);
            subscriber.setReceiveTimeOut(10);

            String filter = "IngenieriaOferta ";
            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));
            System.out.println("Estoy suscrito, espero mensajes");

            //  Process 10 updates
            int update_nbr;
            long total_temp = 0;
            while (!Thread.currentThread().isInterrupted()) {
                //  Use trim to remove the tailing '0' character
                String string  = subscriber.recvStr(0);

                if (string!=null) {
                    string= string.trim();
                    StringTokenizer sscanf = new StringTokenizer(string, " ");
                    String topic = sscanf.nextToken();
                    String mensaje = sscanf.nextToken();

                    System.out.println(topic);
                    System.out.println(mensaje);
                }

                byte[] match = socketServer.recv(0);

                if (match != null) {

                    System.out.println(
                            "MATCH! " + new String(match, ZMQ.CHARSET));

                    String response = "Gracias";
                    socketServer.send(response.getBytes(ZMQ.CHARSET), 0);

                }

            }

        }
    }

}
