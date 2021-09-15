package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Mensajes de emails
 *
 * @author Indra
 *
 */
public class MensajeEmail implements ValueObject {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String from;
	private String to;
	private String titulo;
	private String contenido;
	private String error;
	private Date fechaCreacion;
	private Date fechaEnviado;
	private boolean enviado;
	private String tipo;
	private Long codigo;

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
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(final String from) {
		this.from = from;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido
	 *            the contenido to set
	 */
	public void setContenido(final String contenido) {
		this.contenido = contenido;
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
	 * @return the fechaEnviado
	 */
	public Date getFechaEnviado() {
		return fechaEnviado;
	}

	/**
	 * @param fechaEnviado
	 *            the fechaEnviado to set
	 */
	public void setFechaEnviado(final Date fechaEnviado) {
		this.fechaEnviado = fechaEnviado;
	}

	/**
	 * @return the enviado
	 */
	public boolean isEnviado() {
		return enviado;
	}

	/**
	 * @param enviado
	 *            the enviado to set
	 */
	public void setEnviado(final boolean enviado) {
		this.enviado = enviado;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(final Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(final String to) {
		this.to = to;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final String error) {
		this.error = error;
	}

}