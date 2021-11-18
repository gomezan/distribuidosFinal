/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtroTest;

/**
 *
 * @author Guatavita
 */

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class ClaseB
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");
            socket.setReceiveTimeOut(100);

                String request = "Hello";
                System.out.println("Sending Hello " );
                socket.send(request.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);
                
                if(reply!=null){
                    
                    System.out.println(
                    "Received " + new String(reply, ZMQ.CHARSET));
                }
                
            
        }
    }
}