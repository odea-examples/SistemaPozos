package com.odea.domain;

import java.io.Serializable;

public class Yacimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ID;
	private String nombre;
	
	
	
	@Override
	public String toString() {
		return this.nombre;
	}

	
	
	//GETTERS & SETTERS
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public boolean equals(Object obj) {
		return this.nombre.equals(obj.toString());
	}
	
}
