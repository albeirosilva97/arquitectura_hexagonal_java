package com.ceiba.usuario.comando;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCita {

	private Long id;
	private String nombre;
	private Long idPersona;
	private Integer tipoServicio;
	private Integer costoServicio;
	private LocalDateTime fechaCita;
}
