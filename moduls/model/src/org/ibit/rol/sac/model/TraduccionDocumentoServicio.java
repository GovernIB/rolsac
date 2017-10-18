package org.ibit.rol.sac.model;

/** 
 * Traduccion documento del servicio.
 * @author slromero
 *
 */
public class TraduccionDocumentoServicio implements Traduccion {

    /** Serial Version UID. **/
	private static final long serialVersionUID = 1L;
	/** Archivo. **/
	private Archivo archivo;
     /** Titulo. **/
    private String titulo;
    /** Descripción. **/
    private String descripcion;
	/**
	 * @return the archivo
	 */
	public Archivo getArchivo() {
		return archivo;
	}
	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
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
    
}
