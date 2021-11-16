/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Guatavita
 */

package com.mycompany.filtro;


import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import com.mycompany.filtro.Sector;


public class filtro {

    ///Atributos
    private boolean modo = false;
    private static Sector  sector;
    private static String  sectors[]={"Ingeneiria ","Derecho ","publicidad "};
    private static String  puertoSector="tcp://*:5556";
    
    
    ///Constructor
    public filtro() {

      //  this.sector = new Sector("Ingenieria");

    }
    
    ///Métodos
    
    private static void configurarSector(String nombre)
    {
        filtro.sector = new Sector(puertoSector);
        filtro.sector.start();
    }

    //MAIN
    public static void main(String[] args) throws Exception {
        System.out.println("Filtro iniciado");
       
        configurarSector("Ingenieria");
        
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                if (reply.length != 0) {
                    // Print the message
                    System.out.println(
                            "Received : [" + new String(reply, ZMQ.CHARSET) + "]"
                    );

                    // Send a response
                    String response = "Hello, world!";
                    socket.send(response.getBytes(ZMQ.CHARSET), 0);
                }

            }
        }
    }

}
