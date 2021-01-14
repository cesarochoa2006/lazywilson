package com.cesarochoa2006.aplicacion.tipos;

import java.util.List;

/**
 * Interface que define los métodos del proveedor de 
 * información 
 * @author cesar
 *
 */
public interface IProveedor {
	public List<Integer> recibir(String datos) throws ExcepcionAplicacion;
	public String enviar(List<String> datos) throws ExcepcionAplicacion;
}
