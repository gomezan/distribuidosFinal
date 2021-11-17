/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils.empleador;

import com.utils.empleador.Capacidad;

/**
 *
 * @author Guatavita
 */
public class Habilidad extends Capacidad{
    
     //Atributos
    private String nombre;
    private int nivel;
 
    
    //Constructor

    public Habilidad(int codigo, String nombre, int nivel) {
        super(codigo);
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
