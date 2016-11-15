package org.ibit.rol.sac.model;


/**
 * Representación  Sia.
 * Envio SIA
 */
public class Sia implements ValueObject {
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	
	/** Id. **/
    private Long id;
    
    /** Id del procedimiento **/
	private String idProc;
	
	/** Título del procedimiento para el ciudadano. **/
	private String titulo;
	
	/** Descripción del procedimiento para le ciudadano. **/
	private String descripcion;
	
	/** Los ids de los centros directivos. **/
	private String idCent;
	
	/** Nombre del órgano instructor. **/
	private String uaGest;
	
	/** Los id del destinatario separados por ; siendo el valor 1 ciudadano, 2 la empresa y 3 la administración. **/
	private String idDest;
	
	/** Nivel de administración electrónica. Valores posibles:
	1-Información 2-Descarga de formulario 3-Descarga y envío 4-Tramitación electrónica 5-Proactivo 6-Sin tramitación electrónica
	**/
	private String nivAdm;
	
	/** Valores posibles: N  o  S **/
	private String fiVia;
	
	/** Identificador de la normativa**/
	private String IdNorm;
	
	/** Título normativa**/
	private String tiNorm;
	
	/** Identificador de las materias separados por ; **/
	private String materias;
	
	/** Campo con el ID SIA (será nulo si es un procedimiento creado)**/
	private String idSIA;
	
	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * @return the idProc
	 */
	public String getIdProc() {
		return idProc;
	}
	/**
	 * @param idProc the idProc to set
	 */
	public void setIdProc(String idProc) {
		this.idProc = idProc;
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
	 * @return the idCent
	 */
	public String getIdCent() {
		return idCent;
	}
	/**
	 * @param idCent the idCent to set
	 */
	public void setIdCent(String idCent) {
		this.idCent = idCent;
	}
	/**
	 * @return the uaGest
	 */
	public String getUaGest() {
		return uaGest;
	}
	/**
	 * @param uaGest the uaGest to set
	 */
	public void setUaGest(String uaGest) {
		this.uaGest = uaGest;
	}
	/**
	 * @return the idDest
	 */
	public String getIdDest() {
		return idDest;
	}
	/**
	 * @param idDest the idDest to set
	 */
	public void setIdDest(String idDest) {
		this.idDest = idDest;
	}
	/**
	 * @return the nivAdm
	 */
	public String getNivAdm() {
		return nivAdm;
	}
	/**
	 * @param nivAdm the nivAdm to set
	 */
	public void setNivAdm(String nivAdm) {
		this.nivAdm = nivAdm;
	}
	/**
	 * @return the fiVia
	 */
	public String getFiVia() {
		return fiVia;
	}
	/**
	 * @param fiVia the fiVia to set
	 */
	public void setFiVia(String fiVia) {
		this.fiVia = fiVia;
	}
	/**
	 * @return the idNorm
	 */
	public String getIdNorm() {
		return IdNorm;
	}
	/**
	 * @param idNorm the idNorm to set
	 */
	public void setIdNorm(String idNorm) {
		IdNorm = idNorm;
	}
	/**
	 * @return the tiNorm
	 */
	public String getTiNorm() {
		return tiNorm;
	}
	/**
	 * @param tiNorm the tiNorm to set
	 */
	public void setTiNorm(String tiNorm) {
		this.tiNorm = tiNorm;
	}
	
	
	
	/**
	 * @return the idSIA
	 */
	public String getIdSIA() {
		return idSIA;
	}
	/**
	 * @param idSIA the idSIA to set
	 */
	public void setIdSIA(String idSIA) {
		this.idSIA = idSIA;
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
	 * @return the materias
	 */
	public String getMaterias() {
		return materias;
	}
	/**
	 * @param materias the materias to set
	 */
	public void setMaterias(String materias) {
		this.materias = materias;
	}
	
	
	@Override
	public String toString() {
		StringBuffer texto = new StringBuffer();
		texto.append("Sia id:");
		texto.append(id);
		texto.append(" descripción:");
		texto.append(descripcion);		
		return texto.toString();
	}
}
