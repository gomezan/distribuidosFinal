/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.filtro;

/**
 *
 * @author Guatavita
 */
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

/**
 *
 * @author Guatavita
 */
public class filtroTest {
 
    public static void main(String[] args)
    {
        
        System.out.println("Filtro Test iniciado");
        
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");

            for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
                String request = "gomezan,97080703620,subscribir";
                System.out.println("Sending Hello " + requestNbr);
                socket.send(request.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);
                System.out.println(
                    "Received " + new String(reply, ZMQ.CHARSET) + " " +
                    requestNbr
                );
            }
        }
    }
    
}
