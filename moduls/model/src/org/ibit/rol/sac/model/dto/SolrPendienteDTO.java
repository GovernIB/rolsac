package org.ibit.rol.sac.model.dto;

import java.util.Date;

import org.ibit.rol.sac.model.ValueObject;

public class SolrPendienteDTO implements ValueObject {

	private Long id;
	private String tipo;
	private Long idElemento;
	private int accion;
	private Date fechaCreacion;
	private Date fechaIndexacion;
	private Integer resultado;
	private String mensajeError;

    public SolrPendienteDTO() {}

    public SolrPendienteDTO(Long id, String tipo, Long idElemento, int accion, Date fechaCreacion, Date fechaIndexacion, 
    		              Integer resultado, String mensajeError) {
        super();
        this.id              = id;
        this.tipo            = tipo;
        this.idElemento      = idElemento;
        this.accion          = accion;
        this.fechaCreacion   = fechaCreacion;
        this.fechaIndexacion = fechaIndexacion;
        this.resultado       = resultado;
        this.mensajeError       = mensajeError;
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

	public int getAccion() {
		return accion;
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaIndexacion() {
		return fechaIndexacion;
	}

	public void setFechaIndexacion(Date fechaIndexacion) {
		this.fechaIndexacion = fechaIndexacion;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

  
}
