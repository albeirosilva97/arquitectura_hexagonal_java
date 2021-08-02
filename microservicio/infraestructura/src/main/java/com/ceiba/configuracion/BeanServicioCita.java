package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.usuario.puerto.dao.DaoCita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.servicio.ServicioCrearCita;
import com.ceiba.usuario.servicio.ServicioEliminarCita;

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
