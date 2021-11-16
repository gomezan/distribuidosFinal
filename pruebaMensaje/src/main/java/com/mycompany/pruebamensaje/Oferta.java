package com.mycompany.pruebamensaje;

import java.io.Serializable;
import java.nio.charset.Charset;

public class Oferta implements Serializable{

    public Oferta(String cargo, int IDempleador, Habilidad[] habilidades, int IDSector) {
        this.cargo = cargo;
        this.IDempleador = IDempleador;
        this.habilidades = habilidades;
        this.IDSector = IDSector;
    }

	public Oferta() {
	}
	String cargo;
	int IDempleador;
	Habilidad [] habilidades;
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
