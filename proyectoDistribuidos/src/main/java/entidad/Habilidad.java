package entidad;

import java.io.Serializable;

public class Habilidad implements Serializable{

    public Habilidad(String nombre, int anios) {
        this.nombre = nombre;
        this.anios = anios;
    }
	String nombre;
	int anios;
	public Habilidad() {
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAnios() {
		return anios;
	}
	public void setAnios(int anios) {
		this.anios = anios;
	}
}
