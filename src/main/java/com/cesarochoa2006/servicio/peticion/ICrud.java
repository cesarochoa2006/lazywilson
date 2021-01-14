package com.cesarochoa2006.servicio.peticion;

import javax.ws.rs.core.Response;

/**
 * Interface de apoyo para servicios CRUD
 * @author cesar
 *
 */
public interface ICrud {
	public Response crear(Object o);
	public Response obtener(String o);
	public Response obtenerTodos();
	public Response actualizar(Object o);
	public Response eliminar(Object o);
}
