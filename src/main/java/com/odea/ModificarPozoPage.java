package com.odea;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.odea.domain.Pozo;
import com.odea.domain.Yacimiento;

public class ModificarPozoPage extends EditarPozoPage {

	private static final long serialVersionUID = 1L;

	
	public ModificarPozoPage(final PageParameters parameters) {
		Pozo pozo = new Pozo();
		
		pozo.setID(parameters.get("pozoID").toInteger());
		pozo.setCID(parameters.get("pozoCID").toString());
		pozo.setNombre(parameters.get("pozoNombre").toString());
		pozo.setCoordenadaX(Double.parseDouble(parameters.get("pozoCoordX").toString()));
		pozo.setCoordenadaY(Double.parseDouble(parameters.get("pozoCoordY").toString()));
		pozo.setCoordenadaZ(Double.parseDouble(parameters.get("pozoCoordZ").toString()));
		pozo.setRKB(Double.parseDouble(parameters.get("pozoRKB").toString()));
		pozo.setMR(Double.parseDouble(parameters.get("pozoMR").toString()));
		pozo.setNivelReferencia(parameters.get("pozoNivelReferencia").toInteger());
		pozo.setLinSism(parameters.get("pozoLinSism").toString());
		pozo.setPtoExp(parameters.get("pozoPtoExp").toString());
		pozo.setCategoria(parameters.get("pozoCategoria").toString());
		pozo.setEstado(parameters.get("pozoEstado").toString());
		pozo.setMetExtraccion(parameters.get("pozoMetExtraccion").toString());
		pozo.setObservaciones(parameters.get("pozoObservaciones").toString());
		pozo.setPosicionMapaX(Double.parseDouble(parameters.get("pozoPosMapaX").toString()));
		pozo.setPosicionMapaY(Double.parseDouble(parameters.get("pozoPosMapaY").toString()));
		
		Yacimiento yacimiento = new Yacimiento();
		yacimiento.setID(parameters.get("pozoYacimientoID").toString());
		yacimiento.setNombre(parameters.get("pozoYacimientoNombre").toString());
		
		pozo.setYacimiento(yacimiento);
		
		this.preparePage(pozo);
	}
	

	@Override
	protected void insertarPozo(Pozo pozo) {
		this.daoService.modificarPozo(pozo);
	}
	
}

