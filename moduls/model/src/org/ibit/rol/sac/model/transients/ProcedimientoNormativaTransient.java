package org.ibit.rol.sac.model.transients;

import org.ibit.rol.sac.model.ValueObject;

public class ProcedimientoNormativaTransient implements ValueObject {
	
	private long id;
	private String titulo;
    private String fecha;
    private String fechaBoletin;
	
    public ProcedimientoNormativaTransient() {
    	super();
    }
    
	public ProcedimientoNormativaTransient(long id, String titulo, String fecha, String fechaBoletin) {
	    super();
	    this.id = id;
	    this.titulo = titulo;
	    this.fecha = fecha;
	    this.fechaBoletin = fechaBoletin;
	}

	public long getId() {
    	return id;
    }

	public void setId(long id) {
    	this.id = id;
    }

	public String getTitulo() {
    	return titulo;
    }

	public void setTitulo(String titulo) {
    	this.titulo = titulo;
    }

	public String getFecha() {
    	return fecha;
    }

	public void setFecha(String fecha) {
    	this.fecha = fecha;
    }

	public String getFechaBoletin() {
    	return fechaBoletin;
    }

	public void setFechaBoletin(String fechaBoletin) {
    	this.fechaBoletin = fechaBoletin;
    }
	
}
