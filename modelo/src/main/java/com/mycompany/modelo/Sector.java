/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

import java.io.Serializable;

/**
 *
 * @author Guatavita
 */
public class Sector implements Serializable {
    
    private int codigo;
    private String nombre; 
    
    //Constructor
    public Sector(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
        
    //Metodos

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
