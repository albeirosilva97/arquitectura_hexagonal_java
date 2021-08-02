select id,nombre,id_persona,tipo_servicio, costo_servicio, fecha_cita
from cita where id_persona = :idPersona