package com.ceiba.dominio.excepcion;

public class ExcepcionPersonaConDosCitas extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionPersonaConDosCitas(String message) {
        super(message);
    }
}
