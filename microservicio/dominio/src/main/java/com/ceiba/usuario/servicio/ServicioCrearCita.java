package com.ceiba.usuario.servicio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.ceiba.dominio.excepcion.ExcepcionCitaDiaNoHabil;
import com.ceiba.dominio.excepcion.ExcepcionFechaCitaMenorFechaActual;
import com.ceiba.dominio.excepcion.ExcepcionPersonaConDosCitas;
import com.ceiba.usuario.modelo.dto.DtoCita;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.dao.DaoCita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;

public class ServicioCrearCita {

	private static final String LA_PERSONA_YA_TIENE_CITA_EN_LA_SEMANA = "La persona ya tene una cita en la semana";
	private static final String DIA_NO_HABIL_PARA_SERVICIO = "Dia no habil para este servicio";
	private static final String FECHA_CITA_MENOR_A_FECHA_ACTUAL = "La fecha de la cita es menor a la fecha actual";
	private static final Integer COSTO_SERVICIO_1 = 5000;
	private static final Integer COSTO_SERVICIO_2 = 6000;
	private static final Integer COSTO_SERVICIO_3 = 10000;
	private static final Integer EXECENTE_FINES_DE_SEMANA = 3000;
	private static final Integer EXECENTE_CITAS_30_DIAS_ANTICIPACION = 2000;

	private final RepositorioCita repositorioCita;
	private final DaoCita daoCita;

	public ServicioCrearCita(RepositorioCita repositorioCita, DaoCita daoCita) {
		this.repositorioCita = repositorioCita;
		this.daoCita = daoCita;
	}

	public Long ejecutar(Cita cita) {
		validarFechaCitaMayorAfechaDelSistema(cita);
		fijarCosto(cita);
		validarDiaDeCita(cita);
		validarExistenciaPrevia(cita);
		return this.repositorioCita.crear(cita);
	}

	private void validarExistenciaPrevia(Cita cita) {
		List<DtoCita> citas = this.daoCita.buscarCitaPorIdPersona(cita.getIdPersona());
		Calendar fechaCitaNueva = GregorianCalendar.from(ZonedDateTime.of(cita.getFechaCita(), ZoneId.systemDefault()));
		if (!citas.isEmpty()) {
			citas.stream().forEach((p) -> {
				Calendar fechaCita = GregorianCalendar.from(ZonedDateTime.of(p.getFechaCita(), ZoneId.systemDefault()));
				if (fechaCitaNueva.get(Calendar.YEAR) == fechaCita.get(Calendar.YEAR)
						&& fechaCitaNueva.get(Calendar.WEEK_OF_YEAR) == fechaCita.get(Calendar.WEEK_OF_YEAR)) {
					throw new ExcepcionPersonaConDosCitas(LA_PERSONA_YA_TIENE_CITA_EN_LA_SEMANA);
				}
			});
		}
	}

	private void fijarCosto(Cita cita) {
		int dias = contarDiasHabilesEntreDosFechas(cita);
		if (cita.getTipoServicio() == 1) {
			cita.setCostoServicio(dias < 30 ? COSTO_SERVICIO_1 + aniadirExecenteCitaFinDeSemana(cita)
					: COSTO_SERVICIO_1 + EXECENTE_CITAS_30_DIAS_ANTICIPACION + aniadirExecenteCitaFinDeSemana(cita));
		}
		if (cita.getTipoServicio() == 2) {
			cita.setCostoServicio(
					dias < 30 ? COSTO_SERVICIO_2 : COSTO_SERVICIO_2 + EXECENTE_CITAS_30_DIAS_ANTICIPACION);
		}
		if (cita.getTipoServicio() == 3) {
			cita.setCostoServicio(
					dias < 30 ? COSTO_SERVICIO_3 : COSTO_SERVICIO_3 + EXECENTE_CITAS_30_DIAS_ANTICIPACION);
		}
	}
	
	private void validarDiaDeCita(Cita cita) {
		Calendar fechaCita = GregorianCalendar.from(ZonedDateTime.of(cita.getFechaCita(), ZoneId.systemDefault()));
		if (fechaCita.get(Calendar.DAY_OF_WEEK) == 7 || fechaCita.get(Calendar.DAY_OF_WEEK) == 1) {
			if (!validarTipoServicio(cita)) {
				throw new ExcepcionCitaDiaNoHabil(DIA_NO_HABIL_PARA_SERVICIO);
			}
		}
	}

	private boolean validarTipoServicio(Cita cita) {
		boolean bandera = false;
		if (cita.getTipoServicio() == 1) {
			bandera = true;
		}
		return bandera;
	}

	private int aniadirExecenteCitaFinDeSemana(Cita cita) {
		Calendar fechaCita = GregorianCalendar.from(ZonedDateTime.of(cita.getFechaCita(), ZoneId.systemDefault()));
		return fechaCita.get(Calendar.DAY_OF_WEEK) == 7 || fechaCita.get(Calendar.DAY_OF_WEEK) == 1
				? EXECENTE_FINES_DE_SEMANA
				: 0;
	}

	private void validarFechaCitaMayorAfechaDelSistema(Cita cita) {
		if (cita.getFechaCita().isBefore(LocalDateTime.now())) {
			throw new ExcepcionFechaCitaMenorFechaActual(FECHA_CITA_MENOR_A_FECHA_ACTUAL);
		}
	}
	private int contarDiasHabilesEntreDosFechas(Cita cita) {
		int diffDays= 0;
		Calendar fechaInicial = Calendar.getInstance();
		Calendar fechaFinal = GregorianCalendar.from(ZonedDateTime.of(cita.getFechaCita(), ZoneId.systemDefault()));
		  //mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
		  while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {

		  //si el dia de la semana de la fecha minima es diferente de sabado o domingo
		  if (fechaInicial.get(Calendar.DAY_OF_WEEK) != 1 && fechaInicial.get(Calendar.DAY_OF_WEEK) != 7) {
		   //se aumentan los d�as de diferencia entre min y max
		   diffDays++;
		   }
		  //se suma 1 dia para hacer la validaci�n del siguiente dia.
		  fechaInicial.add(Calendar.DATE, 1);
		  }
		return diffDays+1;
	}
}
