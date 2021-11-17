/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils.filtro;

import java.util.ArrayList;

/**
 *
 * @author Guatavita
 */
public class Oferta {
    
     //Atributos
    int codigo;
    private static ArrayList<Capacidad> listaCapacidades = new ArrayList<Capacidad>();
    
    //Constructor
  
    public Oferta(int codigo) {
        this.codigo = codigo;
    }
   
    //Metodos  

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
    
    
    
}
