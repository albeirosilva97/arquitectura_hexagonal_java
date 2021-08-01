update cita
set nombre = :nombre,
	id_persona = :idPersona,
	tipo_servicio = :tipoServicio,
	costo_servicio = :costoServicio,
	fecha_cita = :fechaCita
where id = :id