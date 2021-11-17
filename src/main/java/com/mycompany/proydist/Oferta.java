/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proydist;

/**
 *
 * @author puma0
 */



public class Oferta{
    private int idOferta;
    private int idEmpresa;
    private String nombreE;
    private String carreraProfesional;
    private int sueldo;
    private int sector;
    private int experiencia;
    private int edad;

    public Oferta( int idOferta,int idEmpresa, String nombreE, String carreraProfesional,int sueldo, int sector, int experiencia, int edad) {
        this.idOferta = idOferta;
        this.idEmpresa = idEmpresa;
        this.nombreE = nombreE;
        this.carreraProfesional = carreraProfesional;
        this.sueldo = sueldo;
        this.sector = sector;
        this.experiencia = experiencia;
        this.edad = edad;
    }
    
    public Oferta(int idOferta, String nombreE, int sueldo, String carreraProfesional) {
        this.idOferta = idOferta;
        this.nombreE = nombreE;
        this.sueldo = sueldo;
        this.carreraProfesional = carreraProfesional;
    }

    public int getidOferta() {
        return idOferta;
    }

    public int getidEmpresa() {
        return idEmpresa;
    }

    public String getnombreE() {
        return nombreE;
    }

    public String getcarreraProfesional() {
        return carreraProfesional;
    }
    public int getsueldo() {
        return sueldo;
    }

    public int getsector() {
        return sector;
    }
    public int getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    
}