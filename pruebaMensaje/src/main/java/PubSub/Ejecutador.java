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
public class Ejecutador {

    public static void main(String[] args) {

        try (ZContext context = new ZContext()) {

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5558");
            
            while (!Thread.currentThread().isInterrupted()) {
                //  Get values that will fool the boss
                String zipcode, mensaje;
                zipcode = "Ingenieria";
                mensaje ="funciono" ;

                //  Send message to all subscribers
                String update = String.format(
                    "%s %s", zipcode, mensaje);
                
                socket.send(update.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);
                System.out.println(
                    "Received " + new String(reply, ZMQ.CHARSET)
                );
            }
            
        }
    }
}
