/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidad;

/**
 *
 * @author TUCHYS
 */
public class Sector {
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
