/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils.empleador;

/**
 *
 * @author Guatavita
 */
public class Habilidad extends Capacidad{
    
     //Atributos
    private String nombre;
    private int nivel;
 
    
    //Constructor

    public Habilidad(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }
    
     //Metodos

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
     
}
