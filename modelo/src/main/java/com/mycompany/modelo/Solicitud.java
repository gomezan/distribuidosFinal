/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author Guatavita
 */

import com.mycompany.modelo.Aspirante;
import java.io.Serializable;

public class Solicitud implements Serializable {
    
    //Atributos
    private int codigo;
    private Aspirante aspirante;
    
    //Constructor

    public Solicitud(int codigo) {
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
