/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtro;

import java.util.Random;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

/**
 *
 * @author Guatavita
 */
public class Suscripcion extends Thread {

    ///Atributos
    private ZMQ.Socket publisher;

    ///Constructor
    public Suscripcion() {
    }

    ///Métodos
    //MAIN
    @Override

    public void run() {

        try (ZContext context = new ZContext()) {

            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5556");

            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5558");

            while (!Thread.currentThread().isInterrupted()) {
                //  Get values that will fool the boss

                byte[] reply = socket.recv(0);
                publisher.send(reply, 0);

                String response = "OK";
                socket.send(response.getBytes(ZMQ.CHARSET), 0);

            }

        }

    }
}
