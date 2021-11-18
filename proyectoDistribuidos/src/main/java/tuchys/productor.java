/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuchys;

import com.google.common.hash.Hashing;
import entidad.Oferta;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class productor
{

    public static void main(String[] args) throws Exception
    {
        Map<Integer,Oferta> recibeMap;
               
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");
            
            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);
                recibeMap = new HashMap<>();
                // Print the message
                ByteArrayInputStream bs= new ByteArrayInputStream(reply); // bytes es el byte[]
                try (
                ObjectInputStream is = new ObjectInputStream(bs);) {                 
                    recibeMap = (Map<Integer, Oferta>)is.readObject();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
                //ObjectInputStream is = new ObjectInputStream(bs);
                //Oferta ofer = (Oferta)is.readObject();
                //is.close();
                
                var ofer = recibeMap.values();
                ofer.forEach((ofet) -> {
                    System.out.println(
                            "Oferta para el cargo: " + ofet.getCargo() + "\n Id sector: "
                                    + ofet.getIDSector() + "\n Id empleador: " +
                                    ofet.getIDempleador() + "\n con habilidades:"
                    );
                });
                /*for (Habilidad habilidade : ofer.habilidades) {
                    System.out.println(habilidade.getNombre() + "\n Con experiencia: " + habilidade.getAnios() + "anios");
               */
                String response = "Hello, world!";
                String sha256hex = Hashing.sha256()
                .hashString(response, StandardCharsets.UTF_8)
                .toString();

                socket.send(sha256hex.getBytes(ZMQ.CHARSET), 0);
            }
        }
    }
}
