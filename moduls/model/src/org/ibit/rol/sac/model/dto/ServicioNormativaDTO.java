package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

/**
 * Servicio Normativa DTO.
 * 
 * @author slromero
 */
public class ServicioNormativaDTO implements ValueObject {
	
	/** Serial Version UID. **/
	private static final long serialVersionUID = 1L;
	/** Id. **/
	private long id;
	/** Titulo. **/
	private String titulo;
	/** Fecha. **/
    private String fecha;
    /** Fecha boletin. **/
    private String fechaBoletin;
	
    /**
     * Servicio Normativa DTO. 
     */
    public ServicioNormativaDTO() {
    	super();
    }
    
    /**
     * ServicioNormativa DTO. 
     * 
     * @param id
     * @param titulo
     * @param fecha
     * @param fechaBoletin
     */
	public ServicioNormativaDTO(long id, String titulo, String fecha, String fechaBoletin) {
	    super();
	    this.id = id;
	    this.titulo = titulo;
	    this.fecha = fecha;
	    this.fechaBoletin = fechaBoletin;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the fechaBoletin
	 */
	public String getFechaBoletin() {
		return fechaBoletin;
	}

	/**
	 * @param fechaBoletin the fechaBoletin to set
	 */
	public void setFechaBoletin(String fechaBoletin) {
		this.fechaBoletin = fechaBoletin;
	}

	
}
