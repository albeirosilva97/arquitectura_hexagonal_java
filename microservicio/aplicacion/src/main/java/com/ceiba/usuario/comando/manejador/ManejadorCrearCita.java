package com.ceiba.usuario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.comando.ComandoCita;
import com.ceiba.usuario.comando.fabrica.FabricaCita;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.servicio.ServicioCrearCita;

@Component
public class ManejadorCrearCita implements ManejadorComandoRespuesta<ComandoCita, ComandoRespuesta<Long>>{

	private final FabricaCita fabricaCita;
	private final ServicioCrearCita servicioCrearCita;
	
	public ManejadorCrearCita(FabricaCita fabricaCita, ServicioCrearCita servicioCrearCita) {
		this.fabricaCita = fabricaCita;
		this.servicioCrearCita = servicioCrearCita;
	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoCita comando) {
		Cita cita = this.fabricaCita.crear(comando);
		 return new ComandoRespuesta<>(this.servicioCrearCita.ejecutar(cita));
	}
}
