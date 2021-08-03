package com.ceiba.cita.servicio.testdatabuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ceiba.usuario.modelo.dto.DtoCita;
import com.ceiba.usuario.modelo.entidad.Cita;

public class CitaTestDataBuilder {

	private Long id;
	private String nombre;
	private Long idPersona;
	private Integer tipoServicio;
	private Integer costoServicio;
	private LocalDateTime fechaCita;

	public CitaTestDataBuilder() {
		nombre = UUID.randomUUID().toString();
		idPersona = 1111L;
		tipoServicio = 2;
		costoServicio = 2000;
		fechaCita = LocalDateTime.now();
	}

	public CitaTestDataBuilder conFechaCitaEIdPersona(LocalDateTime fechaCita, Long idPersona) {
		this.fechaCita = fechaCita;
		this.idPersona = idPersona;
		return this;
	}

	public CitaTestDataBuilder conFechaCita(LocalDateTime fechaCita) {
		this.fechaCita = fechaCita;
		return this;
	}

	public CitaTestDataBuilder conTipoServicioYfechaCita(Integer tipoServicio, LocalDateTime fechaCita) {
		this.tipoServicio = tipoServicio;
		this.fechaCita = fechaCita;
		return this;
	}

	public Cita build() {
		return new Cita(id, nombre, idPersona, tipoServicio, costoServicio, fechaCita);
	}

	public DtoCita buildDtoCita() {
		return new DtoCita(id, nombre, idPersona, tipoServicio, costoServicio, fechaCita);
	}
}
