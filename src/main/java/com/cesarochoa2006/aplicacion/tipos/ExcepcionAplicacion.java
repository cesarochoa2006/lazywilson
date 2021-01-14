package com.cesarochoa2006.aplicacion.tipos;

/**
 * Excepcion personalizada para el manejo de las excepciones 
 * de aplicación
 * @author cesar
 *
 */
public class ExcepcionAplicacion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8193935048187959787L;
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ExcepcionAplicacion.class);
	public ExcepcionAplicacion(String mensaje) {
		super(mensaje);
		LOG.error("Error de aplicación");
	}
	public ExcepcionAplicacion(String mensaje, Throwable causa) {
		super(mensaje, causa);
		LOG.error("Error de aplicación con causa", causa);
	}
}
