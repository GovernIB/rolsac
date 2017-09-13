package org.ibit.rol.sac.model;

/**
 * Traducción de normativa. 
 * @author slromero
 *
 */
public class TraduccionNormativa implements Traduccion {
	
	/** Serial Version UID.	 */
	private static final long serialVersionUID = 1L;

	/** Constructor vacio. **/
	public TraduccionNormativa() {}
    
	/**
	 *  Constructor. 
	 * 
	 * @param titulo
	 * @param enlace
	 * @param apartado
	 * @param paginaInicial
	 * @param paginaFinal
	 * @param observaciones
	 */
    public TraduccionNormativa(String titulo, String enlace, String apartado, Integer paginaInicial, Integer paginaFinal, String observaciones) {
    	this.titulo = titulo;
    	this.enlace = enlace;
    	this.apartado = apartado;
    	this.paginaInicial = paginaInicial;
    	this.paginaFinal = paginaFinal;
    	this.observaciones = observaciones;
    }
    
    /** Sección. **/
    private String seccion;
    
    /** Apartado. **/
    private String apartado;
    
    /** Página inicial. **/
    private Integer paginaInicial;
    
    /** Pagina final. **/
    private Integer paginaFinal;
    
    /** Titulo. **/
    private String titulo;
    
    /** Enlace. */
    private String enlace;
    
    /** Archivo. **/
    private Archivo archivo;
    
    /** Observaciones. **/
    private String observaciones;
    
    /** Responsable. **/
    private String responsable;
    
	/**
	 * @return the seccion
	 */
	public String getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return the apartado
	 */
	public String getApartado() {
		return apartado;
	}

	/**
	 * @param apartado the apartado to set
	 */
	public void setApartado(String apartado) {
		this.apartado = apartado;
	}

	/**
	 * @return the paginaInicial
	 */
	public Integer getPaginaInicial() {
		return paginaInicial;
	}

	/**
	 * @param paginaInicial the paginaInicial to set
	 */
	public void setPaginaInicial(Integer paginaInicial) {
		this.paginaInicial = paginaInicial;
	}

	/**
	 * @return the paginaFinal
	 */
	public Integer getPaginaFinal() {
		return paginaFinal;
	}

	/**
	 * @param paginaFinal the paginaFinal to set
	 */
	public void setPaginaFinal(Integer paginaFinal) {
		this.paginaFinal = paginaFinal;
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
	 * @return the enlace
	 */
	public String getEnlace() {
		return enlace;
	}

	/**
	 * @param enlace the enlace to set
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

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
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the responsable
	 */
	public String getResponsable() {
		return responsable;
	}

	/**
	 * @param responsable the responsable to set
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
    
    
    
}
