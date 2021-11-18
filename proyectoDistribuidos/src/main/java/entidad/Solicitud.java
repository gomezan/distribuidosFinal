/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import java.io.Serializable;

/**
 *
 * @author TUCHYS
 */
public class Solicitud implements Serializable{
      //Atributos
    private int codigo;
    private int idSector;
    private Aspirante aspirante;
    
    //Constructor

    public Solicitud() {
    }

    public Solicitud(int codigo) {
        this.codigo = codigo;
    }
    
    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public Aspirante getAspirante() {
        return aspirante;
    }

    //Metodos
    public void setAspirante(Aspirante aspirante) {    
        this.aspirante = aspirante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    } 
}