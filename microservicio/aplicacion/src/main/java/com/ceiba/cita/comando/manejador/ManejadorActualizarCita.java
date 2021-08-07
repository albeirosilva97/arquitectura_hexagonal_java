package com.ceiba.cita.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.fabrica.FabricaCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.servicio.ServicioActualizarCita;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorActualizarCita implements ManejadorComando<ComandoCita> {

	private final FabricaCita fabricaCita;
	private final ServicioActualizarCita servicioActualizarCita;

	
	public ManejadorActualizarCita(FabricaCita fabricaCita, ServicioActualizarCita servicioActualizarCita) {
		this.fabricaCita = fabricaCita;
		this.servicioActualizarCita = servicioActualizarCita;
	}


	@Override
	public void ejecutar(ComandoCita comando) {
		Cita cita = this.fabricaCita.crear(comando);
        this.servicioActualizarCita.ejecutar(cita);
	}

}
