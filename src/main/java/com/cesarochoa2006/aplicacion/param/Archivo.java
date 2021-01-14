/**
 * 
 */
package com.cesarochoa2006.aplicacion.param;

/**
 * 
 * Clase de apoyo para la gestión de 
 * los servicios que envían y reciben archivos
 * @author cesar
 *
 */
public class Archivo {
	// El objeto a recibir, codificado
	private String objeto;
	// La cédula de quien envía
	private String cedula;
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
}
