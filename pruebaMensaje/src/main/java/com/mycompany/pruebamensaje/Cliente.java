/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebamensaje;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.io.Serializable;

/**
 *
 * @author Guatavita
 */
public class Cliente {
 
    public static void main(String[] args) throws IOException
    {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            //socket.connect("tcp://25.4.189.185:5555");
            socket.connect("tcp://localhost:5555");
            //for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
                String request = "HOLIIIIIIIIIIIIIIII";
                System.out.println("Sending Hello " );
                Habilidad[] aux = new Habilidad[2];
                aux[0]=new Habilidad("Excel",2);
                aux[1]=new Habilidad("Tinto",3);
               /*aux[0].setNombre("Excel");
                aux[0].setAnios(2);
                aux[1].setNombre("Tinto");
                aux[1].setAnios(3);
                */Oferta ofer = new Oferta();
                ofer.setHabilidades(aux);
                ofer.setCargo("Secretario");
                ofer.setIDSector(1);
                ofer.setIDempleador(2);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                //ObjectOutputStream os = new ObjectOutputStream(out);
                try (ObjectOutputStream ois = new ObjectOutputStream(out)) {                 
                    ois.writeObject(ofer);
                   // out.toByteArray();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
                //os.flush();
                byte [] data = out.toByteArray();
                socket.send(data);

                byte[] reply = socket.recv(0);
                System.out.println(
                    "Received " + new String(reply, ZMQ.CHARSET) + " " 
                );
            //}
        }
    }
    
}
