package com.ceiba.cita.servicio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionCitaDiaNoHabil;
import com.ceiba.dominio.excepcion.ExcepcionFechaCitaMenorFechaActual;

public class ServicioActualizarCitaTest {

	@Test
    public void validarFechaCitaParaTipoServicioDosEnDiaNoHabilTest() {
        // arrange
		int tipoServicio = 2;
        LocalDateTime fechaCita = obtenerFechaNoHabil(7);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarCita.ejecutar(cita), ExcepcionCitaDiaNoHabil.class,"Dia no habil para este servicio");
    }

	@Test
    public void validarFechaCitaParaTipoServicioTresEnDiaNoHabilTest() {
        // arrange
		int tipoServicio = 3;
        LocalDateTime fechaCita = obtenerFechaNoHabil(7);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarCita.ejecutar(cita), ExcepcionCitaDiaNoHabil.class,"Dia no habil para este servicio");
    }

	@Test
    public void validarFechaCitaMayorAFechaDelSistema() {
        // arrange
        LocalDateTime fechaCita=LocalDateTime.parse("2018-08-22T11:25");
        Cita cita = new CitaTestDataBuilder().conFechaCita(fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarCita.ejecutar(cita), ExcepcionFechaCitaMenorFechaActual.class,"La fecha de la cita es menor a la fecha actual");
    }

	@Test
    public void validarCostoBaseServicioUnoTest() {
        // arrange
		int tipoServicio = 1;
        LocalDateTime fechaCita = obtenerFechaHabilConDiasDeAnticipacion(7L);
        Cita cita = new CitaTestDataBuilder().conTipoServicioYfechaCita(tipoServicio, fechaCita).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        //when
        servicioActualizarCita.ejecutar(cita);
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
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        //when
        servicioActualizarCita.ejecutar(cita);
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
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        //when
        servicioActualizarCita.ejecutar(cita);
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
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        //when
        servicioActualizarCita.ejecutar(cita);
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
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        //when
        servicioActualizarCita.ejecutar(cita);
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
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        //when
        servicioActualizarCita.ejecutar(cita);
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
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        //when
        servicioActualizarCita.ejecutar(cita);
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
