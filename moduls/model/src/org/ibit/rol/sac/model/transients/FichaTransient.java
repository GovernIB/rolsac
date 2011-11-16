package org.ibit.rol.sac.model.transients;

import java.util.Set;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.ibit.rol.sac.model.ValueObject;

/**
 * Bean que representa a una ficha. Modificado para (PORMAD)
 */

public class FichaTransient implements ValueObject {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String fechaPublicacion;
	private String fechaCaducidad;
	private Boolean caducat;

   public FichaTransient(long id, String titulo, String fechaPublicacion, String fechaCaducidad, Boolean caducat){
        super();
        this.id = id;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCaducidad = fechaCaducidad;
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

	public void setCaducat(Boolean caducat) {
		this.caducat = caducat;
	}

	public Boolean getCaducat() {
		return caducat;
	}
    
}