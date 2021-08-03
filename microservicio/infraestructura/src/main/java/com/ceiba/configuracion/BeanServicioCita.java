package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import com.ceiba.cita.servicio.ServicioEliminarCita;

@Configuration
public class BeanServicioCita {

	@Bean
    public ServicioCrearCita servicioCrearCita(RepositorioCita repositorioCita, DaoCita daoCita) {
        return new ServicioCrearCita(repositorioCita,daoCita);
    }

	@Bean
    public ServicioEliminarCita servicioEliminarCita(RepositorioCita repositorioCita) {
        return new ServicioEliminarCita(repositorioCita);
    }
}
