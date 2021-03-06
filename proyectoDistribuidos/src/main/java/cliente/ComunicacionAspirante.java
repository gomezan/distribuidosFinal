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

import entidad.Solicitud;
import entidad.Sector;

public class ComunicacionAspirante {
    
    ///Atributos
  
    
    //Metodos
    
    public static void suscribirAjustes(String puerto) {
        

       try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Estoy suscrito, espero mensajes");
            ZMQ.Socket subscriber  = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://localhost:5556");
            //subscriber.connect("tcp://25.13.204.246:5556");
            
            String filter = "IngenieriaSolicitud ";
            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));
            
            //  Process 10 updates
            int update_nbr;
            long total_temp = 0;
            for (update_nbr = 0; update_nbr < 10; update_nbr++) {
                //  Use trim to remove the tailing '0' character
                String string = subscriber.recvStr(0).trim();

                StringTokenizer sscanf = new StringTokenizer(string, " ");
                String topic = sscanf.nextToken();
                String mensaje = sscanf.nextToken();
                
                System.out.println(topic);
                System.out.println(mensaje);

              
            }
           
        }

    }
    
    public static void suscribir(ZMQ.Socket socket) {

        String request = "gomezan-97080703620-subscribir";
        //System.out.println("Sending Hello ");
        socket.send(request.getBytes(ZMQ.CHARSET), 0);
        byte[] reply = socket.recv(0);
        String puerto = new String(reply, ZMQ.CHARSET);
        System.out.println("Received " + puerto);
        suscribirAjustes(puerto);

    }
    
    public static void enviarSolicitud(ZMQ.Socket socket, Solicitud solicitud) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(out)) {
            ois.writeObject(solicitud);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        byte[] data = out.toByteArray();
        socket.send(data);

        byte[] reply2 = socket.recv(0);
        String entrada2 = new String(reply2, ZMQ.CHARSET);
        System.out.println("Received " + entrada2);

    }
    
    ///Main
    public static void main(String[] args) throws IOException {

        System.out.println("Filtro Test iniciado");

        try (ZContext context = new ZContext()) {
            
           //  Comunicaci??n Filtro
            System.out.println("aspirante comunicandose");
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://25.4.189.185:5555");
 
            //Envia solicitud
            String request2 = "gomezan-97080703620-agregarSolicitud-";
         
            byte[] encabezado = request2.getBytes(ZMQ.CHARSET);
            socket.send(encabezado, 0);

            byte[] reply = socket.recv(0);
            String respuesta = new String(reply, ZMQ.CHARSET);
            System.out.println("Received " + respuesta);

            if (respuesta.equals("OK")) {

                
                Solicitud sol = new Solicitud();
                sol.setCodigo(1);
                sol.setIdSector(1);

                enviarSolicitud(socket, sol);
                
            }

            //Se suscribe
            suscribir(socket);
        }
    }
    
}
