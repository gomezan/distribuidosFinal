/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proydist;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author puma0
 */


public interface Trabajo extends java.rmi.Remote {
    public String registrarOferta( int idOferta,int idEmpresa, String nombreE, String carreraProfesional,int sueldo, int sector, int experiencia, int edad) throws java.rmi.RemoteException;
    public Oferta consultarOferta(int idOferta) throws java.rmi.RemoteException, FileNotFoundException, IOException;
}

