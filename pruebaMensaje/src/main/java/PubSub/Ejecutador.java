/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PubSub;

/**
 *
 * @author Guatavita
 */
public class Ejecutador {
 
    public static void main(String[] args){
        
        Publicador pub=new Publicador();
        pub.enviarMensajeSuscripcion("Funcionó", "Ingenieria ");
    }
}
