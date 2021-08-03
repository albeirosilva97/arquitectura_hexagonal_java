package com.ceiba.cita.puerto.dao;

import java.util.List;

import com.ceiba.cita.modelo.dto.DtoCita;

public interface DaoCita {

	/**
     * Permite listar citas
     * @return las citas
     */
    List<DtoCita> listar();

    /**
     * Permite buscar una citas por idPersona
     * @return List<DtoCita>
     */
    List<DtoCita> buscarCitaPorIdPersona(Long idPersona);
}
