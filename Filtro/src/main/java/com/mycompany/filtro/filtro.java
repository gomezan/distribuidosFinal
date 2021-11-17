/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Guatavita
 */
package com.mycompany.filtro;

import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mycompany.filtro.PresentacionFiltro;

public class filtro {

    ///Atributos
    private boolean modo = false;
    private static String puertoSector = "5556";
    private static  PresentacionFiltro presentacion=new PresentacionFiltro();
    private ArrayList<String> listaOfertas = new ArrayList<String>();
    private ArrayList<String> listaSolicitudes = new ArrayList<String>();
		

    ///Constructor
    public filtro() {

        //this.presentacion = new PresentacionFiltro();
        //this.presentacion.start();
    }

    ///Métodos
    private static void configurarPresentacion() {

        filtro.presentacion = new PresentacionFiltro();
        filtro.presentacion.start();
    }

    public static boolean autentificar(String entrada){
    
        String[] parts = entrada.split(",");
     
        String user=parts[0]; 
        String password=parts[1];
        
        return(filtro.presentacion.autentificar(user, password));
    }
    
    public static void agregarOferta(String oferta){
        System.out.println("En proceso");
    }
    
       
    //MAIN
    public static void main(String[] args) throws Exception {
        System.out.println("Filtro iniciado");

        //configurarPresentacion();
         presentacion.start();
        
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                if (reply.length != 0) {
                    
                    String entrada=new String(reply, ZMQ.CHARSET);
                    
                    System.out.println(
                            "Received : [" + entrada + "]"
                    );

                    // Send a response
                    if(autentificar(entrada)){
                    
                     //Revisar solicitud cliente
                     String[] parts = entrada.split(",");
                     
                     if(parts[2].equals("subscribir")){
                     String response = puertoSector+",OK";
                     socket.send(response.getBytes(ZMQ.CHARSET), 0);
                     }
                     else if(parts[2].equals("agregarOferta")){
                     agregarOferta(parts[3]);    
                     String response = "OK";
                     socket.send(response.getBytes(ZMQ.CHARSET), 0);
                     }
                     else{
                     socket.send("NOK".getBytes(ZMQ.CHARSET), 0); 
                     }
                     
                    }
                    else{
                     String response = "NOK";
                     socket.send(response.getBytes(ZMQ.CHARSET), 0); 
                    }
                    
                }

            }
        }
    }

}
