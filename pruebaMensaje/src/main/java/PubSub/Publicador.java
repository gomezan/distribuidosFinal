/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PubSub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 *
 * @author Guatavita
 */
public class Publicador extends Thread {

    private ZMQ.Socket publisher;

   public Publicador() {
        System.out.println(" Suscripción iniciado ");
        try (ZContext context = new ZContext()) {
            this.publisher = context.createSocket(SocketType.PUB);
            this.publisher.bind("tcp://*:5556");
        }
    }
   
   public void enviarMensajeSuscripcion(String mensaje, String topic) {
        System.out.println(mensaje);
        String update = String.format(
                "%s %s", topic, mensaje
        );
        this.publisher.send(update, 0);
    }
   

}
