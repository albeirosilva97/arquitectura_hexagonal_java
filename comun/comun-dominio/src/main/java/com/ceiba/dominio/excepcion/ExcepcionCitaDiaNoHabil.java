package com.ceiba.dominio.excepcion;

public class ExcepcionCitaDiaNoHabil extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionCitaDiaNoHabil(String message) {
        super(message);
    }
}
