package com.cesarochoa2006.aplicacion.tipos;

public interface ITransformador {
	// Métodos de codificacion y decodificación
	public String codificar(String objeto) throws ExcepcionAplicacion;
	public String decodificar(String objeto) throws ExcepcionAplicacion;
}
