package com.ceiba.cita.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoCita implements RowMapper<DtoCita>, MapperResult{

	@Override
	public DtoCita mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        Long idPersona = resultSet.getLong("id_persona");
        Integer tipoServicio = resultSet.getInt("tipo_servicio");
        Integer costoServicio = resultSet.getInt("costo_servicio");
        LocalDateTime fechaCita = extraerLocalDateTime(resultSet, "fecha_cita");

        return new DtoCita(id,nombre,idPersona,tipoServicio, costoServicio, fechaCita);
	}

}
