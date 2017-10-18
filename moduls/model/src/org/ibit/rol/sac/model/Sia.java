package org.ibit.rol.sac.model;

import java.util.Set;


/**
 * Representación  Sia.
 * Envio SIA
 */
public class Sia implements ValueObject {
	
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	
    /** Id del elemento. **/
	private String idElemento;
	
	/** Título del procedimiento para el ciudadano. **/
	private String titulo;
	
	/** Descripción del procedimiento para le ciudadano. **/
	private String descripcion;
	
	/** Los ids de los centros directivos. **/
	private String idDepartamento;
	
	/** Los ids de los centros directivos. **/
	private String idCent;
	
	/** Nombre del órgano instructor. **/
	private String uaGest;
	
	/** Los id del destinatario  siendo el valor 1 ciudadano, 2 la empresa y 3 la administración. **/
	private String[] idDest;
	
	/** Nivel de administración electrónica. Valores posibles:
	1-Información 2-Descarga de formulario 3-Descarga y envío 4-Tramitación electrónica 5-Proactivo 6-Sin tramitación electrónica
	**/
	private String nivAdm;
	
	/** Valores posibles: N  o  S **/
	private String fiVia;
	
	/** Normativas**/
	private Set<Normativa> normativas;
	
	/** Título normativa**/
	private String tiNorm;
	
	/** Identificador de las materias  **/
	private String[] materias;
	
	/** Campo con el ID SIA (será nulo si es un procedimiento creado)**/
	private String idSIA;
	
	/**Tipologia del trámite **/
	private Integer tipologia;
	
	/**Tipo trámite **/
	private String tipoTramite;
	
	/** Enlace web**/
	private String enlaceWeb;
	
	/** Operación a realizar **/
	private String operacion;
	
	/** Estado **/
	private String estado;
	
	/** Usuario de conexión. **/
	private String usuario;
	
	/** Password de conexión. **/
	private String password;
	
	/**
	 * @return the idElemento
	 */
	public String getIdElemento() {
		return idElemento;
	}
	/**
	 * @param idElemento the idElemento to set
	 */
	public void setIdElemento(String idElemento) {
		this.idElemento = idElemento;
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
	 * @return the idDest
	 */
	public String[] getIdDest() {
		return idDest;
	}
	/**
	 * @param idDest the idDest to set
	 */
	public void setIdDest(String[] idDest) {
		this.idDest = idDest;
	}
	/**
	 * @return the normativas
	 */
	public Set<Normativa> getNormativas() {
		return normativas;
	}
	/**
	 * @param normativas the normativas to set
	 */
	public void setNormativas(Set<Normativa> normativas) {
		this.normativas = normativas;
	}
	/**
	 * @return the materias
	 */
	public String[] getMaterias() {
		return materias;
	}
	/**
	 * @param materias the materias to set
	 */
	public void setMaterias(String[] materias) {
		this.materias = materias;
	}
	/**
	 * @return the tipologia
	 */
	public Integer getTipologia() {
		return tipologia;
	}
	/**
	 * @param tipologia the tipologia to set
	 */
	public void setTipologia(Integer tipologia) {
		this.tipologia = tipologia;
	}
	/**
	 * @return the tipoTramite
	 */
	public String getTipoTramite() {
		return tipoTramite;
	}
	/**
	 * @param tipoTramite the tipoTramite to set
	 */
	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}
	/**
	 * @return the enlaceWeb
	 */
	public String getEnlaceWeb() {
		return enlaceWeb;
	}
	/**
	 * @param enlaceWeb the enlaceWeb to set
	 */
	public void setEnlaceWeb(String enlaceWeb) {
		this.enlaceWeb = enlaceWeb;
	}
	/**
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the idDepartamento
	 */
	public String getIdDepartamento() {
		return idDepartamento;
	}
	/**
	 * @param idDepartamento the idDepartamento to set
	 */
	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
