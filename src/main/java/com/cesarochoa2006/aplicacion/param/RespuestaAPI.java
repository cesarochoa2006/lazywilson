/**
 * 
 */
package com.cesarochoa2006.aplicacion.param;

/**
 * 
 * Clase de apoyo que me permite manejar eventos de 
 * api REST de forma estandarizada
 * @author cesar
 *
 */
public class RespuestaAPI {
	public static final int ERROR = 999;
	public static final int OK = 0;
	public static final String NOIMPL = "No implementado aún";
	private int codigo;
	private String mensaje = "OK";
	private Object datos;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Object getDatos() {
		return datos;
	}
	public void setDatos(Object datos) {
		this.datos = datos;
	}
}
