package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

public class PlataformasDTO implements ValueObject {

	private long id;
	private String nombre;
	private String descripcion;
	private String urlAcceso;
	private int orden;

	public PlataformasDTO() {
	}

	public PlataformasDTO(final Long id, final String nombre, final int orden) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.orden = orden;
	}

	public PlataformasDTO(final Long id, final String nombre, final int orden, final String descripcion,
			final String urlAcceso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.orden = orden;
		this.descripcion = descripcion;
		this.urlAcceso = urlAcceso;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(final int orden) {
		this.orden = orden;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the urlAcceso
	 */
	public String getUrlAcceso() {
		return urlAcceso;
	}

	/**
	 * @param urlAcceso
	 *            the urlAcceso to set
	 */
	public void setUrlAcceso(final String urlAcceso) {
		this.urlAcceso = urlAcceso;
	}
}
