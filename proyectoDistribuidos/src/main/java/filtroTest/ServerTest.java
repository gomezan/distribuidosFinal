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
public class ServerTest {

    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5554");

            while (!Thread.currentThread().isInterrupted()) {
                //  Get values that will fool the boss
                String topic, mensaje;
                topic = "Cirujano";
                mensaje = "felicitaciones";

                //  Send message to all subscribers
                String update = String.format(
                        "%s %s", topic, mensaje
                );

                socket.send(update.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);
                System.out.println(
                        "Received " + new String(reply, ZMQ.CHARSET));

            }

        }
    }

}
