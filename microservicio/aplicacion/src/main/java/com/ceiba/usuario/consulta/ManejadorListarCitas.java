package com.ceiba.usuario.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoCita;
import com.ceiba.usuario.puerto.dao.DaoCita;

@Component
public class ManejadorListarCitas {

	private final DaoCita daoCita;

	public ManejadorListarCitas(DaoCita daoCita) {
		this.daoCita = daoCita;
	}
	
	public List<DtoCita> ejecutar(){ return this.daoCita.listar(); }
}
