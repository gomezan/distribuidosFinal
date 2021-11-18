/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PubSub;

import java.util.StringTokenizer;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 *
 * @author Guatavita
 */
public class Suscriptor {
    
 
    
    public static void main(String[] args){
    
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Estoy suscrito, espero mensajes");
            ZMQ.Socket subscriber  = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://localhost:5556");

            String filter = "10001 ";
            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));
            
          
            //  Process 10 updates
            int update_nbr;
            long total_temp = 0;
            for (update_nbr = 0; update_nbr < 10; update_nbr++) {
                //  Use trim to remove the tailing '0' character
                String string = subscriber.recvStr(0).trim();

                StringTokenizer sscanf = new StringTokenizer(string, " ");
                String topic = sscanf.nextToken();
                String mensaje = sscanf.nextToken();
                
                System.out.println(topic);
                System.out.println(mensaje);

              
            }
           
        }
    }
    
}
