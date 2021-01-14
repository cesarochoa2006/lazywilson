package com.cesarochoa2006.aplicacion.funciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cesarochoa2006.aplicacion.param.PeticionDTO;
import com.cesarochoa2006.aplicacion.tipos.ExcepcionAplicacion;
import com.cesarochoa2006.modelo.Peticion;

/**
 * Clase de aplicación que contiene métodos de CRUD para la entidad Peticion
 * 
 * @author cesar
 *
 */
public class CrudPeticion {
	private static final Logger LOG = LoggerFactory.getLogger(CrudPeticion.class);
	@PersistenceContext(unitName = "lazywilson-persistence-unit")
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("lazywilson-persistence-unit");
	private EntityManager em = fabrica.createEntityManager();

	/**
	 * Crea una entidad y retorna el id
	 * 
	 * @param peticion DTO de la petición
	 * @return Long el identificador de la entidad creada
	 * @throws ExcepcionAplicacion
	 */
	public Long crearPeticion(PeticionDTO peticion) throws ExcepcionAplicacion {
		try {
			if (peticion.getCedula() == null || peticion.getCedula().isEmpty()
					|| !peticion.getCedula().matches("\\d{4,12}")) {
				throw new ExcepcionAplicacion(
						"La cédula enviada no es correcta, por favor verifique e intente nuevamente");
			}
			Peticion entidad = peticion.fromDTO(null, em);
			entidad.setFecha(new Date());
			em.getTransaction().begin();
			em.persist(entidad);
			em.flush();
			em.getTransaction().commit();
			return entidad.getId();
		} catch (ExcepcionAplicacion e) {
			throw e;
		} catch (Exception e) {
			LOG.error("Error en <<{}>>", this.getClass(), e);
			throw new ExcepcionAplicacion(
					"Ocurrió un problema creando la petición, por favor intente nuevamente o consulte con el administrador del sistema");
		}
	}

	
	/**
	 * Obtener todas las peticiones realizadas
	 * @return
	 * @throws ExcepcionAplicacion 
	 */
	public List<PeticionDTO> obtenerPeticiones() throws ExcepcionAplicacion {
		try {
			TypedQuery<Peticion> buscarTodo = em.createQuery(
					"SELECT p FROM Peticion p ORDER BY p.fecha desc",
					Peticion.class);
			final List<Peticion> resultadoConsulta = buscarTodo.getResultList();
			List<PeticionDTO> resultado = new ArrayList<>();
			for(Peticion p: resultadoConsulta) {
				PeticionDTO dto = new PeticionDTO();
				dto.setCedula(p.getCedula());
				dto.setFecha(p.getFecha());
				resultado.add(dto);
			}
			return resultado;
		}  catch (Exception e) {
			LOG.error("Error en <<{}>>", this.getClass(), e);
			throw new ExcepcionAplicacion(
					"Ocurrió un problema obteniendo las peticiones, por favor intente nuevamente o consulte con el administrador del sistema");
		}
	}
}
