package com.ceiba.usuario.puerto.dao;

import java.util.List;

import com.ceiba.usuario.modelo.dto.DtoCita;

public interface DaoCita {

	/**
     * Permite listar citas
     * @return las citas
     */
    List<DtoCita> listar();

    /**
     * Permite buscar una cita por idPersona
     * @return cita
     */
    List<DtoCita> buscarCitaPorIdPersona(Long idPersona);
}
