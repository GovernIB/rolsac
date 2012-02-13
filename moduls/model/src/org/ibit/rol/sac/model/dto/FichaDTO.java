package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

/**
 * Bean que representa a una ficha. Modificado para (PORMAD)
 */

public class FichaDTO implements ValueObject {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String fechaPublicacion;
	private String fechaCaducidad;
	private String fechaActualizacion;
	private Boolean caducat;
	private Long ordre;
		
	public Long getOrdre() { 
		return ordre; 
	}
  
	public void setOrdre( Long ordre ) {
		this.ordre = ordre;	   
	}
	
	public FichaDTO() {
		super();
	}
	
	public FichaDTO(long id, String titulo, String fechaPublicacion, String fechaCaducidad, String fechaActualizacion, Boolean caducat){
		
		super();
		
		this.id = id;
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaCaducidad = fechaCaducidad;
		this.fechaActualizacion = fechaActualizacion;
		this.caducat = caducat;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }    
    
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

	public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setCaducat(Boolean caducat) {
		this.caducat = caducat;
	}

	public Boolean getCaducat() {
		return caducat;
	}
    
}