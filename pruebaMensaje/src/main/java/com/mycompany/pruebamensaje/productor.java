/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebamensaje;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class productor
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);
                Oferta ofer = new Oferta();
                // Print the message
                ByteArrayInputStream bs= new ByteArrayInputStream(reply); // bytes es el byte[]
                try (
                ObjectInputStream is = new ObjectInputStream(bs);) {                 
                    ofer = (Oferta)is.readObject();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
                //ObjectInputStream is = new ObjectInputStream(bs);
                //Oferta ofer = (Oferta)is.readObject();
                //is.close();
                System.out.println(
                    "Oferta para el cargo: " + ofer.getCargo() + "\n Id sector: "
                        + ofer.getIDSector() + "\n Id empleador: " +
                        ofer.getIDempleador() + "\n con habilidades:"
                );
                for (Habilidad habilidade : ofer.habilidades) {
                    System.out.println(habilidade.getNombre() + "\n Con experiencia: " + habilidade.getAnios() + "anios");
                }
                // Send a response
                String response = "Hello, world!";
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
            }
        }
    }
}
