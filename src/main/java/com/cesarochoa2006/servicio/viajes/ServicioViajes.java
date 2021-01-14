package com.cesarochoa2006.servicio.viajes;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.cesarochoa2006.aplicacion.funciones.CalculadorViajes;
import com.cesarochoa2006.aplicacion.param.Archivo;
import com.cesarochoa2006.aplicacion.param.RespuestaAPI;
import com.cesarochoa2006.aplicacion.tipos.ExcepcionAplicacion;

/**
 * Servicio para la ejecución del cálculo de los viajes
 * @author cesar
 *
 */
@Stateless
@Path("viajes")
public class ServicioViajes {

	private RespuestaAPI respuesta = new RespuestaAPI();
	private CalculadorViajes viajes = new CalculadorViajes();

	@POST
	public Response ejecutar(Archivo a) {
		try {
			respuesta.setDatos(viajes.ejecutar(a));
			respuesta.setCodigo(RespuestaAPI.OK);
		} catch(ExcepcionAplicacion e) {
			respuesta.setCodigo(RespuestaAPI.ERROR);
			respuesta.setMensaje(e.getMessage());
		}
		return Response.ok(respuesta).header("Access-Control-Allow-Origin", "*").build();
	}

}
