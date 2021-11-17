/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

import com.mycompany.modelo.Capacidad;
import java.io.Serializable;

/**
 *
 * @author Guatavita
 */
public class Formacion extends Capacidad implements Serializable {
    
     //Atributos
    private String institucion;
    private int nivel;
    private String titulo;
    
    //Constructor

    public Formacion(String institucion, int nivel, String titulo, int codigo) {
        super(codigo);
        this.institucion = institucion;
        this.nivel = nivel;
        this.titulo = titulo;
    }
    
     //Metodos

    public String getInstitucion() {
        return institucion;
    }

    public int getNivel() {
        return nivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    
}
