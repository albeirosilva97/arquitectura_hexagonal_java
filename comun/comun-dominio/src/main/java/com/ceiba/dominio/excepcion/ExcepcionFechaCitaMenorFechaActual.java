package com.ceiba.dominio.excepcion;

public class ExcepcionFechaCitaMenorFechaActual extends RuntimeException  {

	 private static final long serialVersionUID = 1L;

	    public ExcepcionFechaCitaMenorFechaActual(String mensaje) {
	        super(mensaje);
	    }
}
