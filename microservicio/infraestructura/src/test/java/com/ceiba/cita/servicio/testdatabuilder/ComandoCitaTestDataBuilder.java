package com.ceiba.cita.servicio.testdatabuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ceiba.usuario.comando.ComandoCita;
import com.ceiba.usuario.modelo.entidad.Cita;


public class ComandoCitaTestDataBuilder {

	private Long id;
	private String nombre;
	private Long idPersona;
	private Integer tipoServicio;
	private Integer costoServicio;
	private LocalDateTime fechaCita;

	public ComandoCitaTestDataBuilder() {
		nombre = UUID.randomUUID().toString();
		idPersona = 1111L;
		tipoServicio = 1;
		costoServicio = 2000;
		fechaCita = LocalDateTime.now().plusDays(2);
	}


	public ComandoCita build() {
        return new ComandoCita(id,nombre, idPersona,tipoServicio,costoServicio,fechaCita);
    }
}
