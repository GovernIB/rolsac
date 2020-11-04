package org.ibit.rol.sac.model;

/**
 * Traducción de servicio. 
 * 
 * @author slromero
 * 
 */
public class TraduccionServicio implements Traduccion {
	
	/** Serial Version UID.	 */
	private static final long serialVersionUID = 1L;

	 /** Nombre. **/
    private String nombre;
    
    /** Objeto. **/
    private String objeto;
    
    /** Destinatarios. **/
    private String destinatarios;
    
    /** Requisitos. **/
    private String requisitos;
    
    /** Observaciones. */
    private String observaciones;
    
    
    private String urlTramiteExterno;
    
	/** Constructor vacio. **/
	public TraduccionServicio() {}
    
	/**
	 * Constructor. 
	 * 
	 * @param nombre
	 * @param objeto
	 * @param destinatarios
	 * @param requisitos
	 * @param observaciones
	 */
    public TraduccionServicio(String nombre, String objeto, String destinatarios, String requisitos, String observaciones, String urlTramiteExterno) {
    	this.nombre = nombre;
    	this.objeto = objeto;
    	this.destinatarios = destinatarios;
    	this.requisitos = requisitos;
    	this.observaciones = observaciones;
    	this.setUrlTramiteExterno(urlTramiteExterno);
    }
   
	/**
	 * @return the nombre
	 */
	public final String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public final void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the objeto
	 */
	public final String getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto the objeto to set
	 */
	public final void setObjeto(final String objeto) {
		this.objeto = objeto;
	}

	/**
	 * @return the destinatarios
	 */
	public final String getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios the destinatarios to set
	 */
	public final void setDestinatarios(final String destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * @return the requisitos
	 */
	public final String getRequisitos() {
		return requisitos;
	}

	/**
	 * @param requisitos the requisitos to set
	 */
	public final void setRequisitos(final String requisitos) {
		this.requisitos = requisitos;
	}

	/**
	 * @return the observaciones
	 */
	public final String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public final void setObservaciones(final String observaciones) {
		this.observaciones = observaciones;
	}

	public String getUrlTramiteExterno() {
		return urlTramiteExterno;
	}

	public void setUrlTramiteExterno(String urlTramiteExterno) {
		this.urlTramiteExterno = urlTramiteExterno;
	}
    
    
}
