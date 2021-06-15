package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Mensajes de procedimiento
 *
 * @author Indra
 *
 */
public class ServicioMensaje implements ValueObject {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String usuario;
	private String usuarioLectura;
	private String texto;
	private Date fechaCreacion;
	private Date fechaLectura;
	private boolean gestor;
	private boolean leido;
	private Servicio servicio;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(final String texto) {
		this.texto = texto;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(final Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the fechaLectura
	 */
	public Date getFechaLectura() {
		return fechaLectura;
	}

	/**
	 * @param fechaLectura
	 *            the fechaLectura to set
	 */
	public void setFechaLectura(final Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}

	/**
	 * @return the gestor
	 */
	public boolean isGestor() {
		return gestor;
	}

	/**
	 * @param gestor
	 *            the gestor to set
	 */
	public void setGestor(final boolean gestor) {
		this.gestor = gestor;
	}

	/**
	 * @return the leido
	 */
	public boolean isLeido() {
		return leido;
	}

	/**
	 * @param leido
	 *            the leido to set
	 */
	public void setLeido(final boolean leido) {
		this.leido = leido;
	}

	/**
	 * @return the servicio
	 */
	public Servicio getServicio() {
		return servicio;
	}

	/**
	 * @param servicio
	 *            the servicio to set
	 */
	public void setServicio(final Servicio servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the usuarioLectura
	 */
	public String getUsuarioLectura() {
		return usuarioLectura;
	}

	/**
	 * @param usuarioLectura
	 *            the usuarioLectura to set
	 */
	public void setUsuarioLectura(final String usuarioLectura) {
		this.usuarioLectura = usuarioLectura;
	}

}