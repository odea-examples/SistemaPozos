package com.odea.domain;

import java.io.Serializable;

public class Pozo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ID;
	private String CID;
	private String nombre;
	private Yacimiento yacimiento;
	private Double coordenadaX;
	private Double coordenadaY;
	private Double coordenadaZ;
	private Double RKB;
	private Double MR;
	private Integer nivelReferencia;
	private String linSism;
	private String ptoExp;
	private String categoria;
	private String estado;
	private String metExtraccion;
	private String observaciones;
	private Double posicionMapaX;
	private Double posicionMapaY;
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	
	
	//GETTERS & SETTERS
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cid) {
		CID = cid;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Yacimiento getYacimiento() {
		return yacimiento;
	}
	public void setYacimiento(Yacimiento yacimiento) {
		this.yacimiento = yacimiento;
	}
	public Double getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(Double coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public Double getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(Double coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public Double getCoordenadaZ() {
		return coordenadaZ;
	}
	public void setCoordenadaZ(Double coordenadaZ) {
		this.coordenadaZ = coordenadaZ;
	}
	public Double getRKB() {
		return RKB;
	}
	public void setRKB(Double rKB) {
		RKB = rKB;
	}
	public Double getMR() {
		return MR;
	}
	public void setMR(Double mR) {
		MR = mR;
	}
	public Integer getNivelReferencia() {
		return nivelReferencia;
	}
	public void setNivelReferencia(Integer nivelReferencia) {
		this.nivelReferencia = nivelReferencia;
	}
	public String getLinSism() {
		return linSism;
	}
	public void setLinSism(String linSism) {
		this.linSism = linSism;
	}
	public String getPtoExp() {
		return ptoExp;
	}
	public void setPtoExp(String ptoExp) {
		this.ptoExp = ptoExp;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMetExtraccion() {
		return metExtraccion;
	}
	public void setMetExtraccion(String metExtraccion) {
		this.metExtraccion = metExtraccion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Double getPosicionMapaX() {
		return posicionMapaX;
	}
	public void setPosicionMapaX(Double posicionMapaX) {
		this.posicionMapaX = posicionMapaX;
	}
	public Double getPosicionMapaY() {
		return posicionMapaY;
	}
	public void setPosicionMapaY(Double posicionMapaY) {
		this.posicionMapaY = posicionMapaY;
	}
	
}
