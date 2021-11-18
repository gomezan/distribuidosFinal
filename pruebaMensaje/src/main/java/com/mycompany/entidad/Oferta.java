package com.mycompany.entidad;

import java.io.Serializable;

public class Oferta implements Serializable{

    public Oferta(String cargo, int IDempleador, Habilidad[] habilidades, int IDSector) {
        this.cargo = cargo;
        this.IDempleador = IDempleador;
        this.habilidades = habilidades;
        this.IDSector = IDSector;
    }

	public Oferta() {
	}
        int id;
	String cargo;
	int IDempleador;
	public Habilidad [] habilidades;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
        
	public String getCargo() {
		return cargo;
	}
	public Habilidad[] getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(Habilidad[] habilidades) {
		this.habilidades = habilidades;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getIDempleador() {
		return IDempleador;
	}
	public void setIDempleador(int iDempleador) {
		IDempleador = iDempleador;
	}
	public int getIDSector() {
		return IDSector;
	}
	public void setIDSector(int iDSector) {
		IDSector = iDSector;
	}
	int IDSector;

}
