/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils.filtro;

import com.utils.filtro.Capacidad;

/**
 *
 * @author Guatavita
 */
public class Experiencia extends Capacidad {

    //Atributos
    private String cargo;
    private String compañia;
    private int duracionMeses;

    //Constructor

    public Experiencia(String cargo, String compañia, int duracionMeses, int codigo) {
        super(codigo);
        this.cargo = cargo;
        this.compañia = compañia;
        this.duracionMeses = duracionMeses;
    }
  

    //Metodos
    public String getCargo() {
        return cargo;
    }

    public String getCompañia() {
        return compañia;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

}
