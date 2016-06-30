package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Representación de un SolrPendiente.Anyadido nombre(MCGONZALEZU)
 * Indexaciones a traves de solr
 */
public class SolrPendiente implements ValueObject {
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	
	/** Id. **/
    private Long id;
    /** Tipo de dato (en solr sería categoria): FCH(FICHA), PRO(PROCEDIMIENTO).... **/
	private String tipo;
	/** Id del elemento. **/
	private Long idElemento;
	/** Accion siendo el valor 1 indexar y el 2 desindexar. **/
	private int accion;
	/** Fecha de creacion. **/
	private Date fechaCreacion;
	/** Fecha de indexación. **/
	private Date fechaIndexacion;
	/** Valor 0 (pendiente), 1 (indexado) y -1 (error) **/
	private Integer resultado;
	/** Texto con el mensaje de error. **/
	private String mensajeError;
	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(final Long id) {
		this.id = id;
	}
	/**
	 * @return the tipo
	 */
	public final String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public final void setTipo(final String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the idElemento
	 */
	public final Long getIdElemento() {
		return idElemento;
	}
	/**
	 * @param idElemento the idElemento to set
	 */
	public final void setIdElemento(final Long idElemento) {
		this.idElemento = idElemento;
	}
	/**
	 * @return the accion
	 */
	public final int getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public final void setAccion(final int accion) {
		this.accion = accion;
	}
	/**
	 * @return the fechaCreacion
	 */
	public final Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public final void setFechaCreacion(final Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the fechaIndexacion
	 */
	public final Date getFechaIndexacion() {
		return fechaIndexacion;
	}
	/**
	 * @param fechaIndexacion the fechaIndexacion to set
	 */
	public final void setFechaIndexacion(final Date fechaIndexacion) {
		this.fechaIndexacion = fechaIndexacion;
	}
	/**
	 * @return the resultado
	 */
	public final Integer getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public final void setResultado(final Integer resultado) {
		this.resultado = resultado;
	}
	/**
	 * @return the mensajeError
	 */
	public final String getMensajeError() {
		return mensajeError;
	}
	/**
	 * @param mensajeError the mensajeError to set
	 */
	public final void setMensajeError(final String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	@Override
	public String toString() {
		StringBuffer texto = new StringBuffer();
		texto.append("SolrPendiente id:");
		texto.append(id);
		texto.append(" tipo:");
		texto.append(tipo);
		texto.append(" idElemento:");
		texto.append(idElemento);
		texto.append(" accion:");
		texto.append(accion);
		texto.append(" fechaCreacion:");
		texto.append(fechaCreacion);
		texto.append(" fechaIndexacion:");
		texto.append(fechaIndexacion);
		texto.append(" resultado:");
		texto.append(resultado);
		texto.append(" mensajeError:");
		texto.append(mensajeError);
		return texto.toString();
	}
}
