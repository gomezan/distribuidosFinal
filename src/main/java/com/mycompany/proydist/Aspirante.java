/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proydist;
import java.util.Scanner;
import org.zeromq.SocketType;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import java.util.*;

/**
 *
 * @author puma0
 */



public class Aspirante
{
    static int idAspirante, edad, experiencia;
    static String carreraProfesional, nombre, apellido, sectorA, sectorB;
    static ArrayList<Oferta> ofertas=new ArrayList<>();
    public static void main(String[] args) throws Exception
    {
        //Se indica el inicio de sesión, sea empleador o aspirante.
        System.out.println("--- SESION INICIADA COMO ASPIRANTE ---");
        System.out.println();
        Scanner r= new Scanner(System.in);
        System.out.println("Ingrese el id: ");
        idAspirante=Integer.parseInt(r.next());
        System.out.println("Ingrese el nombre: ");
        nombre=r.next();
        System.out.println("Ingrese el apellido: ");
        apellido=r.next();
        System.out.println("Ingrese la edad: \n");
        edad=Integer.parseInt(r.next());
        System.out.println("Ingrese la carrera profesional: ");
        carreraProfesional=r.next();
        System.out.print("Ingrese los años de experiencia: ");
        experiencia=Integer.parseInt(r.next());
        System.out.println();
        //SE PODRIA CAMBIAR SECTORES
        System.out.println("--- SECTORES DISPONIBLES ---");
        System.out.println("1. Tecnologia.");
        System.out.println("2. Salud.");
        System.out.println("3. Contruccion.");
        System.out.println("4. Politica.");
        System.out.println("5. Otros.");
        System.out.print("Ingrese el sector a suscribirse:");
        sectorA=r.next();
        System.out.print("Ingrese el segundo sector (de no ser ninguno escriba 0):");
        sectorB=r.next();
        r.close();
        //Se inicializa el contexto.
        try (ZContext context = new ZContext()) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket publisher = context.createSocket(SocketType.PUB);
                publisher.bind("tcp://*:5563");
                Thread.sleep(1000);
                publisher.sendMore("B");
                publisher.send(Integer.toString(idAspirante)+" "+Integer.toString(edad)+" "+Integer.toString(experiencia)+" "+sectorA+" "+sectorB);
                context.destroySocket(publisher);
                Thread.currentThread().interrupt();
            }
        }catch (Exception e) {
            System.out.println("Solicitud enviada.");
            System.out.println();
            try {
                Thread.interrupted();
            } catch (Exception ex) {
                //No hacer nada :V
            }
            while(true)
            {
                try (ZContext context = new ZContext()) {
                    while (true) {
                        Socket subscriber = context.createSocket(SocketType.SUB);
                        subscriber.subscribe(ZMQ.SUBSCRIPTION_ALL);
                        boolean conexion=subscriber.connect("tcp://25.4.189.185:5560");
                        if(!conexion){//LIDA SERV
                            conexion=subscriber.connect("tcp://25.7.239.122:5560");
                        }
                        if(subscriber.connect("tcp://25.4.189.185:5560")){
                            System.out.println("Conexion exitosa.");
                            String address = subscriber.recvStr();
                            if(address!=null)
                            {
                                String contents = subscriber.recvStr();
                                //if(address.equalsIgnoreCase(sectorA) || address.equalsIgnoreCase(sectorB))
                                //{
                                    StringTokenizer tokens=new StringTokenizer(contents);
                                    int idAsp= Integer.parseInt(tokens.nextToken().toString());
                                    int idOferta=Integer.parseInt(tokens.nextToken().toString());
                                    String nombreE=tokens.nextToken().toString();
                                    String cargo= tokens.nextToken().toString();
                                    int sueldo= Integer.parseInt(tokens.nextToken().toString());
                                    System.out.println("Oferta recibida!");
                                    System.out.println("Oferta "+idOferta);
                                    System.out.print("Cargo ofrecido: "+cargo);
                                    System.out.println(" - Empresa: "+nombreE);
                                    System.out.println("Sueldo: "+sueldo);
                                    Oferta oferta= new Oferta(idOferta, nombreE, sueldo, cargo);
                                    ofertas.add(oferta);
                                    Thread.currentThread().interrupt();
                                //}
                            }
                        }else{
                            System.out.println("Conexion fallida.");
                        }
                        context.destroySocket(subscriber);
                    }
                }catch (Exception e1) {
                    System.out.println("Cantidad de ofertas recibidas: "+ofertas.size());
                    System.out.println();
                    try {
                        Thread.interrupted();
                    } catch (Exception ex) {
                        //No hacer nada :V
                    }
                }
            }
        }
    }

    public Aspirante(int idAspirante, int edad, int experiencia, String carreraProfesional, String nombre,
            String apellido) {
        Aspirante.idAspirante = idAspirante;
        Aspirante.edad = edad;
        Aspirante.experiencia = experiencia;
        Aspirante.carreraProfesional = carreraProfesional;
        Aspirante.nombre = nombre;
        Aspirante.apellido = apellido;
    }

    public Aspirante(int idAspirante, int edad, int experiencia, String sectorA, String sectorB) {
        Aspirante.idAspirante = idAspirante;
        Aspirante.edad = edad;
        Aspirante.experiencia = experiencia;
        Aspirante.sectorA = sectorA;
        Aspirante.sectorB = sectorB;
    }

    public int getEdad() {
        return edad;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public String getSectorA() {
        return sectorA;
    }

    public String getSectorB() {
        return sectorB;
    }
    
}
