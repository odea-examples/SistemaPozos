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
    	List<Pozo> pozos = jdbcTemplate.query("SELECT cid, nombre, yacim_id, coor_x, coor_y, coor_z, rkb, mr, nivel_ref, lin_sism, pto_exp, categoria, estado, met_extrac, observ FROM Pozo", new RowMapperPozo());
    	
    	return pozos;
    }

    
    public List<Pozo> buscarPozos(Pozo pozo) {
    	
    	logger.debug("SE COMIENZA LA BUSQUEDA DE POZOS");
    	
    	ArrayList<Object> argumentos = new ArrayList<Object>();
    	String sql = "SELECT cid, nombre, yacim_id, coor_x, coor_y, coor_z, rkb, mr, nivel_ref, lin_sism, pto_exp, categoria, estado, met_extrac, observ FROM Pozo WHERE 1 = 1 ";
    	
    	
    	//Nombre
    	if (pozo.getNombre() != null && !pozo.getNombre().equals("")) {
    		sql += "AND nombre LIKE ? ";
			argumentos.add("%" + pozo.getNombre() + "%");
		}

    	
    	//Yacimiento
    	if (pozo.getYacimiento() != null && !pozo.getYacimiento().equals("")) {
    		sql += "AND yacim_id = ? ";
    		argumentos.add(pozo.getYacimiento());
    	}
    	
    	
    	//Busqueda
    	List<Pozo> pozos = jdbcTemplate.query(sql, new RowMapperPozo(), argumentos.toArray());
    	
    	return pozos;
    }
    
    
    
    //RowMappers
    
    private class RowMapperPozo implements RowMapper<Pozo> {

		@Override
		public Pozo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Pozo pozo = new Pozo();
			
			pozo.setCID(rs.getString("cid"));
			pozo.setNombre(rs.getString("nombre"));
			pozo.setYacimiento(rs.getString("yacim_id"));
			pozo.setCoordenadaX(rs.getDouble("coor_x"));
			pozo.setCoordenadaY(rs.getDouble("coor_y"));
			pozo.setCoordenadaZ(rs.getDouble("coor_z"));
			pozo.setRKB(rs.getDouble("rkb"));
			pozo.setMR(rs.getDouble("mr"));
			pozo.setNivelReferencia(rs.getInt("nivel_ref"));
			pozo.setLinSism(rs.getString("lin_sism"));
			pozo.setPtoExp(rs.getString("pto_exp"));
			pozo.setCategoria(rs.getString("categoria"));
			pozo.setEstado(rs.getString("estado"));
			pozo.setMetExtraccion(rs.getString("met_extrac"));
			pozo.setObservaciones(rs.getString("observ"));
			
			return pozo;
		}
    	
    }
    
}
