package com.odea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.odea.domain.Yacimiento;

@Repository
public class YacimientoDAO extends AbstractDAO {
	
	public List<Yacimiento> getYacimientos() {
		
		List<Yacimiento> yacimientos = jdbcTemplate.query("SELECT ID, nombre FROM Yacimiento", new RowMapperYacimiento());
		
		return yacimientos;
	}
	
	
	//RowMappers
	
	private class RowMapperYacimiento implements RowMapper<Yacimiento> {

		@Override
		public Yacimiento mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Yacimiento yacimiento = new Yacimiento();
			
			yacimiento.setID(rs.getString("ID"));
			yacimiento.setNombre(rs.getString("nombre"));
			
			return yacimiento;
		}
		
	}
	
}
