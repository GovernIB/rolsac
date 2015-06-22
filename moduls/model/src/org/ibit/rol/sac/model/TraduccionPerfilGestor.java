package org.ibit.rol.sac.model;


public class TraduccionPerfilGestor implements Traduccion {
	private static final long serialVersionUID = -2205142633629218016L;
	private String nombre;
	private String descripcion;
	    
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
