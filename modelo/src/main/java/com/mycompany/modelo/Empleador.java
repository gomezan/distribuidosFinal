/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author Guatavita
 */

import com.mycompany.modelo.Sector;
import java.io.Serializable;
import java.util.ArrayList;

public class Empleador implements Serializable {
    
     //Atributos
    private int codigo;
    private String nombre;
    private static ArrayList<Sector> listaSector = new ArrayList<Sector>();
    
    //Constructor

    public Empleador(int codigo, String nombre) {
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

  

    public static ArrayList<Sector> getListaSector() {
        return listaSector;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static void setListaSector(ArrayList<Sector> listaSector) {
        Empleador.listaSector = listaSector;
    }
    
    
    
}
