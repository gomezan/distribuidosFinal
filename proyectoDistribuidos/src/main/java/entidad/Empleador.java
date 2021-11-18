/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.ArrayList;

/**
 *
 * @author TUCHYS
 */
public class Empleador {
    private int codigo;
    private String razonSocial;
    private static ArrayList<Sector> listaSector = new ArrayList<Sector>();

    public Empleador() {
    }

    public Empleador(int codigo, String razonSocial) {
        this.codigo = codigo;
        this.razonSocial = razonSocial;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public static ArrayList<Sector> getListaSector() {
        return listaSector;
    }

    public static void setListaSector(ArrayList<Sector> listaSector) {
        Empleador.listaSector = listaSector;
    }

}