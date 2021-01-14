package com.cesarochoa2006.aplicacion.param;

import java.io.Serializable;
import com.cesarochoa2006.modelo.Peticion;
import javax.persistence.EntityManager;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PeticionDTO implements Serializable {

	/**
	 * Objeto serializado
	 */
	private static final long serialVersionUID = 7292964517714174499L;
	private Long id;
	private int version;
	private String cedula;
	private Calendar fecha;

	public PeticionDTO() {
	}

	public PeticionDTO(final Peticion entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.version = entity.getVersion();
			this.cedula = entity.getCedula();
			this.fecha = entity.getFecha();
		}
	}

	public Peticion fromDTO(Peticion entity, EntityManager em) {
		if (entity == null) {
			entity = new Peticion();
		}
		entity.setVersion(this.version);
		entity.setCedula(this.cedula);
		entity.setFecha(this.fecha);
		entity = em.merge(entity);
		return entity;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(final String cedula) {
		this.cedula = cedula;
	}

	public Calendar getFecha() {
		return this.fecha;
	}

	public void setFecha(final Calendar fecha) {
		this.fecha = fecha;
	}
	
}