/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

import java.util.ArrayList;
import com.mycompany.modelo.Empleador;
import java.io.Serializable;

/**
 *
 * @author Guatavita
 */
public class Oferta implements Serializable {
    
     //Atributos
    int codigo;
    private static ArrayList<Capacidad> listaCapacidades = new ArrayList<Capacidad>();
    private Empleador empleador; 
    
    //Constructor

    public Oferta(int codigo, Empleador empleador) {
        this.codigo = codigo;
        this.empleador = empleador;
    }
  
    public Oferta() {

    }
   
    //Metodos  

    public Empleador getEmpleador() {
        return empleador;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public static ArrayList<Capacidad> getListaCapacidades() {
        return listaCapacidades;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public static void setListaCapacidades(ArrayList<Capacidad> listaCapacidades) {
        Oferta.listaCapacidades = listaCapacidades;
    }

    public void setEmplador(Empleador empleador) {
        this.empleador = empleador;
    }
    
    
    
}
