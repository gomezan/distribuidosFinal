/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empleador;

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

import com.mycompany.modelo.Oferta;
import com.mycompany.modelo.Sector;
import com.mycompany.modelo.Empleador;

        

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
    
    public static void suscribir(String entrada){
    
    try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Collecting updates from weather server");
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://localhost:5556");

            //  Subscribe to zipcode, default is NYC, 10001
            String filter = "10001 ";
            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));

            //  Process 100 updates
            int update_nbr;
            long total_temp = 0;
            for (update_nbr = 0; update_nbr < 100; update_nbr++) {
                //  Use trim to remove the tailing '0' character
                String string = subscriber.recvStr(0).trim();

                 System.out.println(string);
                
                StringTokenizer sscanf = new StringTokenizer(string, " ");
                int zipcode = Integer.valueOf(sscanf.nextToken());
                int temperature = Integer.valueOf(sscanf.nextToken());

                total_temp += temperature;
            }

            System.out.println(
                String.format(
                    "Average temperature for zipcode '%s' was %d.",
                    filter,
                    (int)(total_temp / update_nbr)
                )
            );
        }

    
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Filtro Test iniciado");

        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");

            //Se suscribe
            //String request = "gomezan,97080703620,subscribir";
            //System.out.println("Sending Hello ");
            //socket.send(request.getBytes(ZMQ.CHARSET), 0);

            //byte[] reply = socket.recv(0);
            //String entrada=new String(reply, ZMQ.CHARSET);
            //System.out.println("Received " +entrada);
  
            Sector sector= new Sector(1,"Salud");
            Empleador emp= new Empleador(1,"Compensar");
            emp.getListaSector().add(sector);
            Oferta oferta= new Oferta(1,emp);
            
             ByteArrayOutputStream out = new ByteArrayOutputStream();
                try (ObjectOutputStream ois = new ObjectOutputStream(out)) {                 
                    ois.writeObject(oferta);
                   
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
               
                byte [] data = out.toByteArray();
                socket.send(data);
                
            byte[] reply = socket.recv(0);
            String entrada=new String(reply, ZMQ.CHARSET);
            System.out.println("Received " +entrada);                


            //suscribir(entrada);
            
            //guardar oferta
            /*
            for (int i = 0; i < 10; i++) {

                String request2 = "gomezan,97080703620,agregarOferta,entrenador";
                System.out.println("Sending Hello ");
                socket.send(request2.getBytes(ZMQ.CHARSET), 0);

                byte[] reply2 = socket.recv(0);
                System.out.println("Received " + new String(reply2, ZMQ.CHARSET));

            }
            */

        }
    }

}
