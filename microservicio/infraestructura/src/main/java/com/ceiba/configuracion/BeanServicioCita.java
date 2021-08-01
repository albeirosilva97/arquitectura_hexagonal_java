package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.usuario.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.servicio.ServicioCrearCita;

@Configuration
public class BeanServicioCita {

	@Bean
    public ServicioCrearCita servicioCrearCita(RepositorioCita repositorioCita) {
        return new ServicioCrearCita(repositorioCita);
    }
}
