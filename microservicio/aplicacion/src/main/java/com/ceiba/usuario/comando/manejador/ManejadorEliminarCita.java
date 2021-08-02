package com.ceiba.usuario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.usuario.servicio.ServicioEliminarCita;

@Component
public class ManejadorEliminarCita implements ManejadorComando<Long> {

	private final ServicioEliminarCita servicioEliminarCita;

	public ManejadorEliminarCita(ServicioEliminarCita servicioEliminarCita) {
		this.servicioEliminarCita = servicioEliminarCita;
	}

	public void ejecutar(Long idCita) {
		this.servicioEliminarCita.ejecutar(idCita);	
	}
}
