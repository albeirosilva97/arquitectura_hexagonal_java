package com.ceiba.usuario.modelo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCita {

	private Long id;
	private String nombre;
	private Long idPersona;
	private Integer tipoServicio;
	private Integer costoServicio;
	private LocalDateTime fechaCita;
}
