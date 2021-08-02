package com.ceiba.usuario.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.dto.DtoCita;
import com.ceiba.usuario.puerto.dao.DaoCita;

@Component
public class DaoCitaMysql implements DaoCita {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace="cita", value="listar")
    private static String sqlListar;

	@SqlStatement(namespace="cita", value="buscar")
    private static String sqlBuscar;

	public DaoCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public List<DtoCita> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoCita());
	}

	@Override
	public List<DtoCita> buscarCitaPorIdPersona(Long idPersona) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("idPersona", idPersona);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBuscar, paramSource,
				new MapeoCita());
	}
}
