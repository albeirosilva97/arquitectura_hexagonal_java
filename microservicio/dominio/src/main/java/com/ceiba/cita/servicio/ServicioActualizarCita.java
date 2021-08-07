package com.ceiba.cita.servicio;

import static com.ceiba.cita.util.ConstantesCita.COSTO_SERVICIO_1;
import static com.ceiba.cita.util.ConstantesCita.COSTO_SERVICIO_2;
import static com.ceiba.cita.util.ConstantesCita.COSTO_SERVICIO_3;
import static com.ceiba.cita.util.ConstantesCita.DIA_NO_HABIL_PARA_SERVICIO;
import static com.ceiba.cita.util.ConstantesCita.EXECENTE_CITAS_30_DIAS_ANTICIPACION;
import static com.ceiba.cita.util.ConstantesCita.EXECENTE_FINES_DE_SEMANA;
import static com.ceiba.cita.util.ConstantesCita.FECHA_CITA_MENOR_A_FECHA_ACTUAL;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionCitaDiaNoHabil;
import com.ceiba.dominio.excepcion.ExcepcionFechaCitaMenorFechaActual;

public class ServicioActualizarCita {

	private final RepositorioCita repositorioCita;

	public ServicioActualizarCita(RepositorioCita repositorioCita) {
		this.repositorioCita = repositorioCita;
	}

	public void ejecutar(Cita cita) {
		validarFechaCitaMayorAfechaDelSistemaActualizar(cita);
		fijarCostoActualizar(cita);
		validarDiaDeCitaActualizar(cita);
        this.repositorioCita.actualizar(cita);
    }
	
	private void fijarCostoActualizar(Cita cita) {
		int dias = contarDiasHabilesEntreDosFechasActualizar(cita);
		if (cita.getTipoServicio() == 1) {
			cita.setCostoServicio(dias <= 30 ? (COSTO_SERVICIO_1 + aniadirExecenteCitaFinDeSemanaActualizar(cita))
					: (COSTO_SERVICIO_1 + EXECENTE_CITAS_30_DIAS_ANTICIPACION + aniadirExecenteCitaFinDeSemanaActualizar(cita)));
		}
		if (cita.getTipoServicio() == 2) {
			cita.setCostoServicio(
					dias <= 30 ? COSTO_SERVICIO_2 : (COSTO_SERVICIO_2 + EXECENTE_CITAS_30_DIAS_ANTICIPACION));
		}
		if (cita.getTipoServicio() == 3) {
			cita.setCostoServicio(
					dias <= 30 ? COSTO_SERVICIO_3 : (COSTO_SERVICIO_3 + EXECENTE_CITAS_30_DIAS_ANTICIPACION));
		}
	}
	
	private void validarDiaDeCitaActualizar(Cita cita) {
		if (validarSiesSabadoOEsDomingoActualizar(cita) && !validarTipoServicioActualizar(cita)) {
			throw new ExcepcionCitaDiaNoHabil(DIA_NO_HABIL_PARA_SERVICIO);
		}
	}

	private boolean validarSiesSabadoOEsDomingoActualizar(Cita cita) {
		Calendar fechaCita = GregorianCalendar.from(ZonedDateTime.of(cita.getFechaCita(), ZoneId.systemDefault()));
		return fechaCita.get(Calendar.DAY_OF_WEEK) == 7 || fechaCita.get(Calendar.DAY_OF_WEEK) == 1 ? Boolean.TRUE : Boolean.FALSE;
	}

	private boolean validarTipoServicioActualizar(Cita cita) {
		boolean bandera = false;
		if (cita.getTipoServicio() == 1) {
			bandera = true;
		}
		return bandera;
	}

	private void validarFechaCitaMayorAfechaDelSistemaActualizar(Cita cita) {
		if (cita.getFechaCita().isBefore(LocalDateTime.now())) {
			throw new ExcepcionFechaCitaMenorFechaActual(FECHA_CITA_MENOR_A_FECHA_ACTUAL);
		}
	}

	private int contarDiasHabilesEntreDosFechasActualizar(Cita cita) {
		int diffDays = 0;
		Calendar fechaInicial = Calendar.getInstance();
		Calendar fechaFinal = GregorianCalendar.from(ZonedDateTime.of(cita.getFechaCita(), ZoneId.systemDefault()));
		while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
			if (fechaInicial.get(Calendar.DAY_OF_WEEK) != 1 && fechaInicial.get(Calendar.DAY_OF_WEEK) != 7) {
				diffDays++;
			}
			fechaInicial.add(Calendar.DATE, 1);
		}
		return diffDays;
	}

	private int aniadirExecenteCitaFinDeSemanaActualizar(Cita cita) {
		return validarSiesSabadoOEsDomingoActualizar(cita) ? EXECENTE_FINES_DE_SEMANA : 0;
	}
}
