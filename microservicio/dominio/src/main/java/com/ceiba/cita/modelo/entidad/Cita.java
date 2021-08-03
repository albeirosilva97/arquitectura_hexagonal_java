package com.ceiba.cita.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cita {

	private static final String SE_DEBE_INGRESAR_LA_FECHA_CITA = "Se debe ingresar la fecha de la cita";
	private static final String SE_DEBE_INGRESAR_EL_ID_PERSONA = "Se debe ingresar el id de la persona";
	private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_PERSONA = "Se debe ingresar el nombre de persona";
	private static final String SE_DEBE_INGRESAR_TIPO_DE_SERVICIO = "Se debe ingresar el tipo de servicio";

	private Long id;
	private String nombre;
	private Long idPersona;
	private Integer tipoServicio;
	private Integer costoServicio;
	private LocalDateTime fechaCita;

	public Cita(Long id, String nombre, Long idPersona, Integer tipoServicio, Integer costoServicio,
			LocalDateTime fechaCita) {
		validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_PERSONA);
		validarObligatorio(fechaCita, SE_DEBE_INGRESAR_LA_FECHA_CITA);
		validarObligatorio(tipoServicio, SE_DEBE_INGRESAR_TIPO_DE_SERVICIO);
		validarObligatorio(idPersona, SE_DEBE_INGRESAR_EL_ID_PERSONA);
		this.id = id;
		this.nombre = nombre;
		this.idPersona = idPersona;
		this.tipoServicio = tipoServicio;
		this.costoServicio = costoServicio;
		this.fechaCita = fechaCita;
	}
}
