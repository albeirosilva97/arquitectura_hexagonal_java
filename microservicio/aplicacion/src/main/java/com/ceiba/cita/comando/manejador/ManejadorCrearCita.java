package com.ceiba.cita.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.fabrica.FabricaCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import com.ceiba.manejador.ManejadorComandoRespuesta;

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
