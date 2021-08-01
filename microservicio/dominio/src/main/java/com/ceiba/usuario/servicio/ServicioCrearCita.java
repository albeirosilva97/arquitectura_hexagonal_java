package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;

public class ServicioCrearCita {

	private static final String LA_CITA_YA_EXISTE_EN_EL_SISTEMA = "La cita ya existe en el sistema";

	private RepositorioCita repositorioCita;

	public ServicioCrearCita(RepositorioCita repositorioCita) {
		this.repositorioCita = repositorioCita;
	}

	public Long ejecutar(Cita cita) {
		validarExistenciaPrevia(cita);
		return this.repositorioCita.crear(cita);
	}

	private void validarExistenciaPrevia(Cita cita) {
		boolean existe = this.repositorioCita.existe(cita.getNombre());
		if (existe) {
			throw new ExcepcionDuplicidad(LA_CITA_YA_EXISTE_EN_EL_SISTEMA);
		}
	}
}
