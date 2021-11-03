package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Mensajes de procedimiento
 *
 * @author Indra
 *
 */
public class ProcedimientoMensaje implements ValueObject {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String usuario;
	private String usuarioLectura;
	private String texto;
	private Date fechaCreacion;
	private Date fechaLectura;
	private boolean gestor;
	private boolean leido;
	private Long idProcedimiento;
	private String usuarioNombre;
	private String usuarioLecturaNombre;

	public ProcedimientoMensaje() {

	}

	public ProcedimientoMensaje(final Long id, final String usuario, final String usuarioLectura, final String texto,
			final Date fechaCreacion, final Date fechaLectura, final boolean gestor, final boolean leido) {
		this.id = id;
		this.usuario = usuario;
		this.usuarioLectura = usuarioLectura;
		this.texto = texto;
		this.fechaCreacion = fechaCreacion;
		this.fechaLectura = fechaLectura;
		this.gestor = gestor;
		this.leido = leido;
		// idProcedimiento = idProcedimiento;
		this.usuarioNombre = usuarioNombre;

	}

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

	/**
	 * @return the idProcedimiento
	 */
	public Long getIdProcedimiento() {
		return idProcedimiento;
	}

	/**
	 * @param idProcedimiento
	 *            the idProcedimiento to set
	 */
	public void setIdProcedimiento(final Long idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
	}

	/**
	 * @return the usuarioNombre
	 */
	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	/**
	 * @param usuarioNombre
	 *            the usuarioNombre to set
	 */
	public void setUsuarioNombre(final String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	/**
	 * @return the usuarioLecturaNombre
	 */
	public String getUsuarioLecturaNombre() {
		return usuarioLecturaNombre;
	}

	/**
	 * @param usuarioLecturaNombre
	 *            the usuarioLecturaNombre to set
	 */
	public void setUsuarioLecturaNombre(final String usuarioLecturaNombre) {
		this.usuarioLecturaNombre = usuarioLecturaNombre;
	}

}