/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author Guatavita
 */


import java.io.Serializable;
import java.util.ArrayList;

public class Empleador implements Serializable {
    
     //Atributos
    private int codigo;
    private String nombre;
   
    
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

 

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    
    
}
