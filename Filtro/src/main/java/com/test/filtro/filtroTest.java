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
                
               //Se suscribe
                String request = "gomezan,97080703620,subscribir";
                System.out.println("Sending Hello ");
                socket.send( request.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);
                System.out.println("Received " + new String(reply, ZMQ.CHARSET));
                
                
                //guardar oferta
                for(int i=0; i<10;i++){
                
                String request2 = "gomezan,97080703620,agregarOferta,entrenador";
                System.out.println("Sending Hello ");
                socket.send( request2.getBytes(ZMQ.CHARSET), 0);

                byte[] reply2 = socket.recv(0);
                System.out.println("Received " + new String(reply2, ZMQ.CHARSET));
                
                }
                
            
        }
    }
    
}
