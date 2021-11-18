/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author TUCHYS
 */
public class Solicitud {
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
