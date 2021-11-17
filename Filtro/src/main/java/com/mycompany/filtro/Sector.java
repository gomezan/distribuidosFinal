/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filtro;

import java.util.Random;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

/**
 *
 * @author Guatavita
 */
public class Sector  extends Thread {
    
    ///Atributos
    
     ///Constructor
    public Sector(){
         System.out.println(" iniciado sector ");
    }

    ///Métodos
  
    
    public void enviarMensajeSuscripcion(String mensaje){
         System.out.println(mensaje);
    }
    
    //MAIN
    @Override
    public void run() {
        
        System.out.println(" iniciado sector ");
       
     
        //  Prepare our context and publisher
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5556");
            //publisher.bind("ipc://weather");

            //  Initialize random number generator
            Random srandom = new Random(System.currentTimeMillis());
            while (!Thread.currentThread().isInterrupted()) {
                //  Get values that will fool the boss
                int zipcode, temperature, relhumidity;
                zipcode = 10000 + srandom.nextInt(10000);
                temperature = srandom.nextInt(215) - 80 + 1;

                System.out.println(zipcode);
                
                
                //  Send message to all subscribers
                String update = String.format(
                    "%05d %d", zipcode, temperature
                );
                publisher.send(update, 0);
            }
        }
    }
}
