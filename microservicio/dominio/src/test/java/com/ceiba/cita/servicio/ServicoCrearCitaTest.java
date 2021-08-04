package com.ceiba.cita.servicio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionCitaDiaNoHabil;
import com.ceiba.dominio.excepcion.ExcepcionFechaCitaMenorFechaActual;
import com.ceiba.dominio.excepcion.ExcepcionPersonaConDosCitas;


public class ServicoCrearCitaTest {

	@Test
    public void validarFechaCitaParaTipoServicioDosEnDiaNoHabilTest() {
        // arrange
		int tipoServicio = 2;
        LocalDateTime fechaCita = obtenerFechaNoHabil(7);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionCitaDiaNoHabil.class,"Dia no habil para este servicio");
    }

	@Test
    public void validarFechaCitaParaTipoServicioTresEnDiaNoHabilTest() {
        // arrange
		int tipoServicio = 3;
        LocalDateTime fechaCita = obtenerFechaNoHabil(7);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionCitaDiaNoHabil.class,"Dia no habil para este servicio");
    }

	@Test
    public void validarFechaCitaMayorAFechaDelSistema() {
        // arrange
        LocalDateTime fechaCita=LocalDateTime.parse("2018-08-22T11:25");
        Cita cita = new CitaTestDataBuilder().conFechaCita(fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionFechaCitaMenorFechaActual.class,"La fecha de la cita es menor a la fecha actual");
    }

	@Test
    public void validarExistenciaPreviaDeUnaCitaParaUnaPersonaEnUnaMismaSemanaTest() {
        // arrange
		LocalDateTime fechaCita = obtenerFechaHabilConDiasDeAnticipacion(10L);
		LocalDateTime fechaCitaIngresada = obtenerFechaHabilConDiasDeAnticipacion(10L);
		Cita cita = new CitaTestDataBuilder().conFechaCita(fechaCita).build();
		DtoCita citaIngresada = new CitaTestDataBuilder().conFechaCitaEIdPersona(fechaCitaIngresada, 1L).buildDtoCita();
		List<DtoCita>citas = new ArrayList<>();
		citas.add(citaIngresada);
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        Mockito.when(daoCita.buscarCitaPorIdPersona(Mockito.anyLong())).thenReturn(citas);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionPersonaConDosCitas.class,"La persona ya tene una cita en la semana");
    }

	@Test
    public void validarCostoBaseServicioUnoTest() {
        // arrange
		int tipoServicio = 1;
        LocalDateTime fechaCita = obtenerFechaHabilConDiasDeAnticipacion(7L);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        //when
        servicioCrearCita.ejecutar(cita);
        // act - assert
        Assert.assertEquals(cita.getCostoServicio(), Integer.valueOf(5000));
    }

	@Test
    public void validarCostoBaseServicioDosTest() {
        // arrange
		int tipoServicio = 2;
        LocalDateTime fechaCita = obtenerFechaHabilConDiasDeAnticipacion(7L);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        //when
        servicioCrearCita.ejecutar(cita);
        // act - assert
        Assert.assertEquals(cita.getCostoServicio(), Integer.valueOf(6000));
    }

	@Test
    public void validarCostoBaseServicioTresTest() {
        // arrange
		int tipoServicio = 3;
        LocalDateTime fechaCita = obtenerFechaHabilConDiasDeAnticipacion(7L); 
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        //when
        servicioCrearCita.ejecutar(cita);
        // act - assert
        Assert.assertEquals(cita.getCostoServicio(), Integer.valueOf(10000));
    }

	@Test
    public void validarCostoServicioUnoConCitaDeMasDeTreintaDiasDeReservaTest() {
        // arrange
		int tipoServicio = 1;
        LocalDateTime fechaCita = obtenerFechaHabilConDiasDeAnticipacion(50L);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        //when
        servicioCrearCita.ejecutar(cita);
        // act - assert
        Assert.assertEquals(cita.getCostoServicio(), Integer.valueOf(7000));
    }

	@Test
    public void validarCostoServicioUnoConCitaDeMasDeTreintaDiasDeReservaYFinDeSemanaTest() {
        // arrange
		int tipoServicio = 1;
        LocalDateTime fechaCita = obtenerFechaNoHabil(50);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        //when
        servicioCrearCita.ejecutar(cita);
        // act - assert
        Assert.assertEquals(cita.getCostoServicio(), Integer.valueOf(10000));
    }

	@Test
	public void validarCostoServicioDosConCitaDeMasDeTreintaDiasDeReservaTest() {
        // arrange
		int tipoServicio = 2;
        LocalDateTime fechaCita = obtenerFechaHabilConDiasDeAnticipacion(50L);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        //when
        servicioCrearCita.ejecutar(cita);
        // act - assert
        Assert.assertEquals(cita.getCostoServicio(), Integer.valueOf(8000));
    }

	@Test
	public void validarCostoServicioTresConCitaDeMasDeTreintaDiasDeReservaTest() {
        // arrange
		int tipoServicio = 3;
        LocalDateTime fechaCita = obtenerFechaHabilConDiasDeAnticipacion(50L);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        //when
        servicioCrearCita.ejecutar(cita);
        // act - assert
        Assert.assertEquals(cita.getCostoServicio(), Integer.valueOf(12000));
    }

	private LocalDateTime obtenerFechaHabilConDiasDeAnticipacion(Long numeroDias) {
		LocalDateTime fecha = LocalDateTime.now().plusDays(numeroDias);
		if (validarSiesSabadoOEsDomingo(fecha)) {
			fecha = fecha.plusDays(3);
		}
		return fecha;
	}

	private LocalDateTime obtenerFechaNoHabil(int numeroDias) {
		Calendar fechaCalendar = GregorianCalendar.from(ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()));
		fechaCalendar.add(Calendar.DATE, numeroDias);
		while(fechaCalendar.get(Calendar.DAY_OF_WEEK) != 7 && fechaCalendar.get(Calendar.DAY_OF_WEEK) != 1) {
			fechaCalendar.add(Calendar.DATE, 1);
		}
		return LocalDateTime.ofInstant(fechaCalendar.toInstant(), ZoneId.systemDefault());
	}

	private boolean validarSiesSabadoOEsDomingo(LocalDateTime fechaActual) {
		Calendar fechaCita = GregorianCalendar.from(ZonedDateTime.of(fechaActual, ZoneId.systemDefault()));
		return fechaCita.get(Calendar.DAY_OF_WEEK) == 7 || fechaCita.get(Calendar.DAY_OF_WEEK) == 1 ? Boolean.TRUE : Boolean.FALSE;
	}
}
