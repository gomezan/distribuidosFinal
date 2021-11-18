/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtro;

import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.util.ArrayList;



/**
 *
 * @author Guatavita
 */
public class PresentacionFiltro extends Thread {

    ///Atributos

    ///Constructor

    public PresentacionFiltro() {
    
    }
    
    
    /// Metodos
    
    public boolean autentificar(String user, String password){
    
        boolean res=false;
        
        if(user.equals("gomezan")&&password.equals("97080703620")){
           res=true;
        }
        
        return res;
    }
    
    public void enviarOfertas(ArrayList ofertas){
    
    }
    
    public void enviarSolicitudes(ArrayList solicitudes){
    
    }

    ///Main
    @Override
    public void run() {
        System.out.println("Filtro presentacion iniciado");

        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5557");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                if (reply.length != 0) {
                    // Print the message
                    System.out.println(
                            "Received : [" + new String(reply, ZMQ.CHARSET) + "]"
                    );
                    

                    String response = "Hello, world!";
                    socket.send(response.getBytes(ZMQ.CHARSET), 0);
                }

            }
        }
    }

}