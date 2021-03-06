/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtroTest;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 *
 * @author Guatavita
 */
public class Ejecutor {

    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5558");

            while (!Thread.currentThread().isInterrupted()) {
                //  Get values that will fool the boss
                String topic, mensaje;
                topic = "IngenieriaOferta";
                mensaje = "funciona";

                //  Send message to all subscribers
                String update = String.format(
                        "%s %s", topic, mensaje
                );

                socket.send(update.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);
                System.out.println(
                        "Received " + new String(reply, ZMQ.CHARSET));
                
                
                String topic2, mensaje2;
                topic2 = "IngenieriaSolicitud";
                mensaje2 = "funciona";

                //  Send message to all subscribers
                String update2 = String.format(
                        "%s %s", topic2, mensaje2
                );

                socket.send(update2.getBytes(ZMQ.CHARSET), 0);

                byte[] reply2 = socket.recv(0);
                System.out.println(
                        "Received " + new String(reply2, ZMQ.CHARSET));

            }

        }
    }
}
