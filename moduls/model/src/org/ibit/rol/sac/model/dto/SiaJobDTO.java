package org.ibit.rol.sac.model.dto;

import java.util.Date;

import org.ibit.rol.sac.model.ValueObject;

public class SiaJobDTO implements ValueObject {

	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	/** Id. **/
    private Long id;
    
    /** Fecha de inicio. **/
    private Date fechaIni;
    
    /** Fecha de fin. **/
	private Date fechaFin;
	
	/** Descripción breve **/
	private String descBreve;
	
	/** Descripción **/
	private String descripcion;
	
	/** Estado **/
	private Integer estado;
    
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
	 * @return the descBreve
	 */
	public String getDescBreve() {
		return descBreve;
	}

	/**
	 * @param descBreve the descBreve to set
	 */
	public void setDescBreve(String descBreve) {
		this.descBreve = descBreve;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

    public SiaJobDTO() {}

    public SiaJobDTO(Long id, Date fechaIni, Date fechaFin, String descBreve, String descripcion, Integer estado) {
        super();
        this.id              	= id;
        this.fechaIni			= fechaIni	;
        this.fechaFin			= fechaFin;
        this.descBreve 			= descBreve;
        this.descripcion 		= descripcion;
        this.estado				= estado;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fechaIni
	 */
	public final Date getFechaIni() {
		return fechaIni;
	}

	/**
	 * @param fechaIni the fechaIni to set
	 */
	public final void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * @return the fechaFin
	 */
	public final Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public final void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	

	


	
	


  
}
