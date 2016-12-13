package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Representación  SiaPendiente.
 * Envio SIA pendiente
 */
public class SiaPendiente implements ValueObject {
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	
	/** Id. **/
    private Long id;
    
    /** Tipo UA/PROC/NORM **/
	private String tipo;
	
	/** Código elemento. **/
	private Long idElemento;
	
	/** Estado de la indexación. Valores posibles:
     0 -> A la espera de enviar
     1 -> Indexado correctamente
    -1 -> Indexado incorrectamente
	 **/
	private Integer estado;
	
	/** Fecha de creación del dato **/
	private Date fecAlta;
	
	/** Fecha de envio **/
	private Date fecIdx;
	
	/** Mensaje recibido tras el envío de la información**/
	private String mensaje;
	
	/** Tipo acción (alta,modificación.reactivación o baja) **/
	private Integer tipoAccion;
	
	/** Código SIA. **/
	private Long idSia;
	
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
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the idElemento
	 */
	public Long getIdElemento() {
		return idElemento;
	}
	/**
	 * @param idElemento the idElemento to set
	 */
	public void setIdElemento(Long idElemento) {
		this.idElemento = idElemento;
	}
	
	/**
	 * @return the fecIdx
	 */
	public Date getFecIdx() {
		return fecIdx;
	}
	/**
	 * @param fecIdx the fecIdx to set
	 */
	public void setFecIdx(Date fecIdx) {
		this.fecIdx = fecIdx;
	}
	
	/**
	 * @return the fecAlta
	 */
	public Date getFecAlta() {
		return fecAlta;
	}
	/**
	 * @param fecgAlta the fecAlta to set
	 */
	public void setFecAlta(Date fecAlta) {
		this.fecAlta = fecAlta;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

	/**
	 * @return the tipoAccion
	 */
	public Integer getTipoAccion() {
		return tipoAccion;
	}
	/**
	 * @param tipoAccion the tipoAccion to set
	 */
	public void setTipoAccion(Integer tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	
	/**
	 * @return the idSia
	 */
	public Long getIdSia() {
		return idSia;
	}
	/**
	 * @param idSia the idSia to set
	 */
	public void setIdSia(Long idSia) {
		this.idSia = idSia;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SiaPendiente [id=" + id + ", tipo=" + tipo + ", idElemento="
				+ idElemento + ", estado=" + estado + "]";
	}
	
	
}
