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
    private Aspirante aspirante;
    
    //Constructor

    public Solicitud(int codigo) {
        this.codigo = codigo;
    }
    
    //Metodos

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
