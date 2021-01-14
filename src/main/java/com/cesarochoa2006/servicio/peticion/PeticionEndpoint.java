package com.cesarochoa2006.servicio.peticion;

import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cesarochoa2006.aplicacion.funciones.CrudPeticion;
import com.cesarochoa2006.aplicacion.param.RespuestaAPI;
import com.cesarochoa2006.aplicacion.tipos.ExcepcionAplicacion;

/**
 * 
 */
@Stateless
@Path("/peticion")
public class PeticionEndpoint implements ICrud {
	 
	private CrudPeticion peticion = new CrudPeticion();
	
	
	private RespuestaAPI respuesta = new RespuestaAPI();

	@Override
	@POST
	public Response crear(Object o) {
		return enviarNoImpl();
	}

	@Override
	@GET
	@Path("/{id}")
	public Response obtener(@PathParam("id") String id) {
		return enviarNoImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response obtenerTodos() {
		try {
			respuesta.setDatos(peticion.obtenerPeticiones());
			respuesta.setCodigo(RespuestaAPI.OK);
		} catch(ExcepcionAplicacion e) {
			respuesta.setCodigo(RespuestaAPI.ERROR);
			respuesta.setMensaje(e.getMessage());
		}
		return Response.ok(respuesta).build();
	}

	@PUT
	@Override
	public Response actualizar(Object o) {
		return enviarNoImpl();
	}

	@DELETE
	@Override
	public Response eliminar(Object o) {
		return enviarNoImpl();
	}
	
	private Response enviarNoImpl() {
		respuesta.setCodigo(RespuestaAPI.ERROR);
		respuesta.setMensaje(RespuestaAPI.NOIMPL);
		return Response.ok(respuesta).build();
	}
}
