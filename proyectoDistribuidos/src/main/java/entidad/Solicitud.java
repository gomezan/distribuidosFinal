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

public class Solicitud implements Serializable {
    
    //Atributos
    private int codigo;
    private int idAspirante;
    private int IDSector;
    
    //Constructor

    public Solicitud(int codigo, int idAspirante, int IDSector) {
        this.codigo = codigo;
        this.idAspirante = idAspirante;
        this.IDSector = IDSector;
    }

    public Solicitud() {
        
    }
  

    //Metodos
    public int getIDSector() {
        return IDSector;
    }

      public Solicitud(int codigo) {
        this.codigo = codigo;
    }
    
    public int getIdAspirante() {
        return idAspirante;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    
}
