package com.odea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.odea.domain.Pozo;
import com.odea.domain.Yacimiento;

@Repository
public class PozoDAO extends AbstractDAO {
	
    private static final Logger logger = LoggerFactory.getLogger(PozoDAO.class);
	
	
    public Integer getPrueba() {
		
		logger.debug("SE BUSCA EL DATO");
		
		Integer resultado = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM Pozo");
		
		logger.debug("SE ENCONTRO EL DATO");
		
		return resultado;
	}
	
	
    
    public List<Pozo> getPozos() {
    	List<Pozo> pozos = jdbcTemplate.query("SELECT p.ID, p.cid, p.nombre, p.yacim_id, p.coor_x, p.coor_y, p.coor_z, p.rkb, p.mr, p.nivel_ref, p.lin_sism, p.pto_exp, p.categoria, p.estado, p.met_extrac, p.observ, p.posMapaX, p.posMapaY, y.nombre FROM Pozo p, Yacimiento y WHERE p.yacim_id = y.ID", new RowMapperPozo());
    	
    	return pozos;
    }

    
    public List<Pozo> buscarPozos(Pozo pozo) {
    	
    	logger.debug("SE COMIENZA LA BUSQUEDA DE POZOS");
    	
    	ArrayList<Object> argumentos = new ArrayList<Object>();
    	String sql = "SELECT p.ID, p.cid, p.nombre, p.yacim_id, p.coor_x, p.coor_y, p.coor_z, p.rkb, p.mr, p.nivel_ref, p.lin_sism, p.pto_exp, p.categoria, p.estado, p.met_extrac, p.observ, p.posMapaX, p.posMapaY, y.nombre FROM Pozo p, Yacimiento y WHERE p.yacim_id = y.ID ";
    	    	
    	if (pozo.getNombre() != null && !pozo.getNombre().equals("")) {
    		sql += "AND p.nombre LIKE ? ";
			argumentos.add("%" + pozo.getNombre() + "%");
		}

    	if (pozo.getYacimiento() != null) {
    		sql += "AND p.yacim_id = ? ";
    		argumentos.add(pozo.getYacimiento().getID());
    	}
    	
    	
    	List<Pozo> pozos = jdbcTemplate.query(sql, new RowMapperPozo(), argumentos.toArray());
    	
    	return pozos;
    }
    
    
    public void altaPozo(Pozo pozo) {
    	
    	String sql = "";
    	sql += "INSERT INTO Pozo ";
    	sql += "(cid, nombre, yacim_id, coor_x, coor_y, coor_z, rkb, mr, nivel_ref, lin_sism, pto_exp, categoria, estado, met_extrac, observ, posMapaX, posMapaY) ";
    	sql += "VALUES ";
    	sql += "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	jdbcTemplate.update(sql, pozo.getCID(), pozo.getNombre(), pozo.getYacimiento().getID(), pozo.getCoordenadaX(), pozo.getCoordenadaY(), pozo.getCoordenadaZ(), pozo.getRKB(), pozo.getMR(), pozo.getNivelReferencia(), pozo.getLinSism(), pozo.getPtoExp(), pozo.getCategoria(), pozo.getEstado(), pozo.getMetExtraccion(), pozo.getObservaciones(), pozo.getPosicionMapaX(), pozo.getPosicionMapaY());
    	
    	logger.debug("ALTA DE POZO - Nombre: " + pozo.getNombre());
	}
	
	public void bajaPozo(Pozo pozo) {
		
		jdbcTemplate.update("DELETE FROM Pozo WHERE ID = ?", pozo.getID());
		logger.debug("POZO BORRADO - ID: " + pozo.getID() + " - Nombre: " + pozo.getNombre());
		
	}
	
	public void modificarPozo(Pozo pozo) {

		String sql = "";
		sql += "UPDATE Pozo SET ";
		sql += "cid = ?, nombre = ?, yacim_id = ?, coor_x = ?, coor_y = ?, coor_z = ?, rkb = ?, mr = ?, nivel_ref = ?, lin_sism = ?, pto_exp = ?, categoria = ?, estado = ?, met_extrac = ?, observ = ?, posMapaX = ?, posMapaY = ? ";
		sql += "WHERE ID = ?";
		
		jdbcTemplate.update(sql, pozo.getCID(), pozo.getNombre(), pozo.getYacimiento().getID(), pozo.getCoordenadaX(), pozo.getCoordenadaY(), pozo.getCoordenadaZ(), pozo.getRKB(), pozo.getMR(), pozo.getNivelReferencia(), pozo.getLinSism(), pozo.getPtoExp(), pozo.getCategoria(), pozo.getEstado(), pozo.getMetExtraccion(), pozo.getObservaciones(), pozo.getPosicionMapaX(), pozo.getPosicionMapaY(), pozo.getID());
		
		logger.debug("POZO BORRADO - ID: " + pozo.getID());
		
	}
    
    
    //RowMappers
    
    private class RowMapperPozo implements RowMapper<Pozo> {

		@Override
		public Pozo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Pozo pozo = new Pozo();
			
			pozo.setID(rs.getInt("p.ID"));
			pozo.setCID(rs.getString("p.cid"));
			pozo.setNombre(rs.getString("p.nombre"));
			pozo.setCoordenadaX(rs.getString("p.coor_x"));
			pozo.setCoordenadaY(rs.getString("p.coor_y"));
			pozo.setCoordenadaZ(rs.getString("p.coor_z"));
			pozo.setRKB(rs.getString("p.rkb"));
			pozo.setMR(rs.getString("p.mr"));
			pozo.setNivelReferencia(rs.getInt("p.nivel_ref"));
			pozo.setLinSism(rs.getString("p.lin_sism"));
			pozo.setPtoExp(rs.getString("p.pto_exp"));
			pozo.setCategoria(rs.getString("p.categoria"));
			pozo.setEstado(rs.getString("p.estado"));
			pozo.setMetExtraccion(rs.getString("p.met_extrac"));
			pozo.setObservaciones(rs.getString("p.observ"));
			pozo.setPosicionMapaX(rs.getString("p.posMapaX"));
			pozo.setPosicionMapaY(rs.getString("p.posMapaY"));
			
			Yacimiento yacimiento = new Yacimiento();
			yacimiento.setID(rs.getString("p.yacim_id"));
			yacimiento.setNombre(rs.getString("y.nombre"));
			
			pozo.setYacimiento(yacimiento);
			
			return pozo;
		}
    	
    }
    
}
