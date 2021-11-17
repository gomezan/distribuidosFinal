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
public class Aspirante {
    
    //Atributos
    private int codigo;
    private String nombre;
    private String apellido;
    private int telefono;
    private String correo;
    private String direccion;
    private static ArrayList<Sector> listaSector = new ArrayList<Sector>();
    
    //Constructor

    public Aspirante(int codigo, String nombre, String apellido, int telefono, String correo, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }
    
   
    //Metodos

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
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

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public static void setListaSector(ArrayList<Sector> listaSector) {
        Aspirante.listaSector = listaSector;
    }

    
    
}
