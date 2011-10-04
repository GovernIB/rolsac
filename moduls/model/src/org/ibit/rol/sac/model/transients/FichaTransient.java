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
	private Date fechaPublicacion;
	private Date fechaCaducidad;

   public FichaTransient(long id, String titulo, Date fechaPublicacion, Date fechaCaducidad){
        super();
        this.id = id;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCaducidad = fechaCaducidad;
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
    
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    
}