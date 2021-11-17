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
public class Capacidad implements Serializable {
    
     //Atributos
    private int codigo;
    
    //Constructor

    public Capacidad(int codigo) {
        this.codigo = codigo;
    }
    
    //Metodos

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
