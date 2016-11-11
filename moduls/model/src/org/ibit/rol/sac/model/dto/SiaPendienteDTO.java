package org.ibit.rol.sac.model.dto;

import java.util.Date;

import org.ibit.rol.sac.model.ValueObject;

public class SiaPendienteDTO implements ValueObject {

	private Long id;
	private String tipo;
	private Long idElemento;
	private Integer estado;
	private Date fecAlta;
	private Date fecIdx;
	private String mensaje;
	private Integer tipoAccion;
	
	

    public SiaPendienteDTO() {}

    public SiaPendienteDTO(Long id, String tipo, Long idElemento, Integer estado, Date fecAlta, Date fecIdx,String mensaje) {
        super();
        this.id              = id;
        this.tipo            = tipo;
        this.idElemento      = idElemento;
        this.estado          = estado;
        this.fecAlta		 = fecAlta;
        this.fecIdx			 = fecIdx;
        this.mensaje		 = mensaje;
    }

	/**
	 * @return the fecAlta
	 */
	public Date getFecAlta() {
		return fecAlta;
	}

	/**
	 * @param fecAlta the fecAlta to set
	 */
	public void setFecAlta(Date fecAlta) {
		this.fecAlta = fecAlta;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdElemento() {
		return idElemento;
	}

	public void setIdElemento(Long idElemento) {
		this.idElemento = idElemento;
	}

	
  
}
