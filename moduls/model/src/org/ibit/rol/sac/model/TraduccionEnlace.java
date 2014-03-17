package org.ibit.rol.sac.model;


public class TraduccionEnlace implements Traduccion {

	private static final long serialVersionUID = 1L;
	
	private String titulo;
    private String enlace;
    
    public TraduccionEnlace() {};
    
	public String getEnlace() {
		return enlace;
	}
	
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
    
}
