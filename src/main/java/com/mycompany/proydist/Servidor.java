/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proydist;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 *
 * @author puma0
 */
public class Servidor {
        static int ContO, ContS, excepcion;
    static Map<Integer,Oferta> ofertas=new HashMap<>(); 
    static Map<Integer,Aspirante> aspirantes=new HashMap<>();
    public static void main(String[] args) throws InterruptedException
    {
        ArrayList<String> servidores = new ArrayList<String>();
        servidores.add("25.4.189.185:8000");
        //servidores.add("25.7.239.122:8000");
        //servidores.add("25.6.253.113:8000");
        ContO=0;
        ContS=0;
        int idOferta, idEmpresa, sueldo, sector, idAspirante, edad, experiencia;
        String nombreE, carreraProfesional, sectorA, sectorB;
        System.out.println("--- FILTRO DE OFERTAS/SOLICITUDES ---");
        System.out.println();
        while(true){
            try (ZContext context = new ZContext()) {
                ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
                subscriber.connect("tcp://25.6.247.129:5563");
                subscriber.subscribe("A".getBytes(ZMQ.CHARSET));
                subscriber.subscribe("B".getBytes(ZMQ.CHARSET));
                while (!Thread.currentThread().isInterrupted()) {
                    String address = subscriber.recvStr();
                    if(address!=null)
                    {
                        String contents = subscriber.recvStr();
                        if(address.equalsIgnoreCase("A"))
                        {
                            //Recepción de oferta
                            //Se tokenizan la información recibida para organizarla como un objeto.
                            StringTokenizer tokens=new StringTokenizer(contents);
                            idOferta=Integer.parseInt(tokens.nextToken().toString());
                            idEmpresa=Integer.parseInt(tokens.nextToken().toString());
                            nombreE=tokens.nextToken().toString();
                            carreraProfesional=tokens.nextToken().toString();
                            sueldo=Integer.parseInt(tokens.nextToken().toString());
                            sector=Integer.parseInt(tokens.nextToken().toString());
                            experiencia=Integer.parseInt(tokens.nextToken().toString());
                            edad=Integer.parseInt(tokens.nextToken().toString());
                            System.out.println("Oferta recibida!");
                            //Muestra información de la oferta recibida
                            System.out.println("ID Oferta:"+idOferta+" - Empresa:" +nombreE+" - Carrera Profesional:"+carreraProfesional+" - Sueldo:"+sueldo);
                            Oferta oferta=new Oferta(idOferta, idEmpresa, nombreE, carreraProfesional, sueldo, sector, experiencia, edad);
                            ofertas.put(oferta.getidOferta(), oferta);
                            ContO=ofertas.size();
                            //Muestra la cantidad de ofertas recibidas
                            System.out.println("Ofertas recibidas:"+ContO);
                            System.out.println();
                        }else{
                            StringTokenizer tokens=new StringTokenizer(contents);
                            idAspirante=Integer.parseInt(tokens.nextToken().toString());
                            edad=Integer.parseInt(tokens.nextToken().toString());
                            experiencia=Integer.parseInt(tokens.nextToken().toString());
                            sectorA=tokens.nextToken().toString();
                            sectorB=tokens.nextToken().toString();
                            Aspirante aspirante=new Aspirante(idAspirante, edad, experiencia, sectorA, sectorB);
                            System.out.println("Solicitud recibida!");
                            System.out.println("Aspirante:"+idAspirante + " -  Sectores:" + sectorA+" y "+sectorB);
                            aspirantes.put(idAspirante, aspirante);
                            ContS++;
                        }
                    }
                    if(ContO==3){
                        //Indica que ya recibió 10 ofertas de forma satisfactoria.
                        System.out.println("Ya se recibieron todas las ofertas.");
                        ContO=0;
                        excepcion=1;
                        //Interrumpe el hilo para salir del ciclo
                        Thread.currentThread().interrupt();
                    }
                    if(ContS==2){
                        //Indica que ya recibió 10 ofertas de forma satisfactoria.
                        System.out.println("Ya se recibieron todas las solicitudes.");
                        ContS=0;
                        excepcion=2;
                        //Interrumpe el hilo para salir del ciclo
                        Thread.currentThread().interrupt();
                    }   
                }
            }catch (Exception e) {
                switch(excepcion){
                    case 1:
                        //Muestra las ofertas previamente recibidas.
                        System.out.println("Ofertas Recibidas");
                        Set<Integer> llaves=ofertas.keySet();
                        Oferta oferta;
                        for(int i=0;i<llaves.size();i++)
                        {
                            oferta = ofertas.get(llaves.toArray()[i]);
                            System.out.println(i+1+".Oferta:"+oferta.getidOferta()+" - Carrera:"+oferta.getcarreraProfesional()+" - Sector:"+oferta.getsector());
                        }
                        System.out.println();
                        //Busca servidores para enviar la información de las ofertas
                        //y envía cada oferta a un servidor para que seaa añadida a la base de datos.
                        System.out.println("---------------------------");
                        String res = " ";
                        int i=0;
                        Trabajo miOferta;
                        for(int z=0;z<llaves.size();z++)
                        {
                            try {
                                miOferta=(Trabajo)Naming.lookup("rmi://"+servidores.get(i)+"/"+"Oferta");
                                if(i==2){
                                    i=0;
                                }else{
                                    i++;
                                }
                                Oferta o = ofertas.get(llaves.toArray()[z]);
                                res = miOferta.registrarOferta(o.getidOferta(),o.getidEmpresa(),o.getnombreE(),o.getcarreraProfesional(),o.getsueldo(),o.getsector(),o.getExperiencia(),o.getEdad());
                                System.out.println(res);
                            }catch (Exception s1) {
                                try {
                                    if(i==2){
                                        i=0;
                                    }else{
                                        i++;
                                    }
                                    miOferta=(Trabajo)Naming.lookup("rmi://"+servidores.get(i)+"/"+"Oferta");
                                    Oferta o=ofertas.get(llaves.toArray()[z]);
                                    res = miOferta.registrarOferta(o.getidOferta(),o.getidEmpresa(),o.getnombreE(),o.getcarreraProfesional(),o.getsueldo(),o.getsector(),o.getExperiencia(),o.getEdad());
                                    System.out.println(res);
                                } catch (Exception s2) {
                                    try {
                                        if(i==2){
                                            i=0;
                                        }else{
                                            i++;
                                        }
                                        miOferta=(Trabajo)Naming.lookup("rmi://"+servidores.get(i)+"/"+"Oferta");
                                        Oferta o=ofertas.get(llaves.toArray()[z]);
                                        res = miOferta.registrarOferta(o.getidOferta(),o.getidEmpresa(),o.getnombreE(),o.getcarreraProfesional(),o.getsueldo(),o.getsector(),o.getExperiencia(),o.getEdad());
                                        System.out.println(res);
                                    } catch (Exception s3) {
                                        System.err.println("No se pudo encontrar un servidor.");
                                    }
                                }
                            }
                        }
                        ofertas.clear();
                        //Se cambia el estado del hilo de tal forma que pueda ejecutar el ciclo otra vez.
                        try {
                            Thread.interrupted();
                        } catch (Exception ex) {
                            //No hacer nada :V
                        }
                        break;
                    case 2:
                        Set<Integer> llavesA=aspirantes.keySet();
                        int tam= llavesA.size();
                        int k=0;
                        try {
                            Thread.interrupted();
                        } catch (Exception ex0) {
                            //No hacer nada :V
                        }
                        while(k<tam){
                            try (ZContext context1 = new ZContext()) {
                                ZMQ.Socket publisher = context1.createSocket(SocketType.PUB);
                                publisher.bind("tcp://*:5560");
                                //Thread.sleep(1000);
                                String topico=aspirantes.get(llavesA.toArray()[k]).getSectorA();
                                publisher.sendMore(topico);
                                publisher.send(llavesA.toArray()[k].toString()+" "+"100"+" "+"Microsoft"+" "+"Ingeniero"+" "+"1000000");
                                context1.destroySocket(publisher);
                                k++;
                            }catch (Exception ex5) {
                                ex5.printStackTrace();
                            }
                        }  
                        //Se cambia el estado del hilo de tal forma que pueda ejecutar el ciclo otra vez.
                        try {
                            Thread.interrupted();
                        } catch (Exception ex) {
                            //No hacer nada :V
                        }
                        break;
                }
            }
        }
    }
                    
        
    
}
