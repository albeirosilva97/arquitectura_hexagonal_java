package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;

public interface RepositorioCita {

	 /**
     * Permite crear una cita
     * @param cita
     * @return el id generado
     */
    Long crear(Cita cita);

    /**
     * Permite actualizar una cita
     * @param cita
     */
    void actualizar(Cita cita);

    /**
     * Permite eliminar una cita
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe una cita con un id de persona
     * @param nombre
     * @return si existe o no
     */
    boolean existe(Long idPersona);
}
