package com.odea.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odea.domain.Pozo;
import com.odea.domain.Yacimiento;

@Service
public class DAOService {
	
	@Autowired
	private transient PozoDAO pozoDAO;
	
	@Autowired
	private transient YacimientoDAO yacimientoDAO;
	
	public Integer getPrueba() {
		return pozoDAO.getPrueba();
	}
	
	public List<Pozo> getPozos() {
		return pozoDAO.getPozos();
	}
	
	public List<Pozo> buscarPozos(Pozo pozo) {
		return pozoDAO.buscarPozos(pozo);
	}

	public List<Yacimiento> getYacimientos() {
		return yacimientoDAO.getYacimientos();
	}
	
}
