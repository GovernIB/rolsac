package es.caib.rolsac.apirest.v1.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Clase Fitxa del RestAPI. 
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "Fitxa", description = "Definició del tipus fitxa")
public class Fitxa {

	/** Id. **/
	//@ApiModelProperty(value = "Codigo", dataType = "java.lang.Long", required = true)
	private Long codigo;
	
	/** Título. **/
	//@ApiModelProperty(value = "Titulo", dataType = "java.lang.String", required = true)
	private String titulo;
	
	/** Fecha publicación. **/
	//@ApiModelProperty(value = "Fecha de publicación", dataType = "java.util.Date", required = false)
	private Date fechaPublicacion;
	
	/** Fecha caducidad. **/
	//@ApiModelProperty(value = "Fecha de caducidad", dataType = "java.util.Date", required = false)
	private Date fechaCaducidad;
	
	/** Fecha actualizacion. **/
	//@ApiModelProperty(value = "Fecha de actualización", dataType = "java.util.Date", required = false)
	private Date fechaActualizacion;
	
	/** Validacion. **/
	//@ApiModelProperty(value = "Validación (PUBLICA, PRIVADA o RESERVADA)", dataType = "java.lang.Integer", required = false)
	private Integer validacion;
	
	/** Documentos. **/
	//@ApiModelProperty(value = "Documentos", dataType = "java.lang.List", required = false)
	private List<Long> documentos;
	
	/** Info. **/
	//@ApiModelProperty(value = "Info", dataType = "java.lang.String", required = false)
	private String info;
	
	/** Hechos vitales. **/
	//@ApiModelProperty(value = "Hechos vitales", dataType = "java.lang.List", required = false)
	private List<Long> hechosVitales;
	
	/** Responsable. **/
	//@ApiModelProperty(value = "Responsable", dataType = "java.lang.String", required = false)
	private String responsable;
	
	/** Publico objetivo. **/
	//@ApiModelProperty(value = "Publicos objectivos", dataType = "java.lang.List", required = false)
	private List<Long> publicosObjetivo; 
	
	/** Descripcion. */
	//@ApiModelProperty(value = "Descripción", dataType = "java.lang.String", required = false)
	private String descripcion;
	
	/** Descripcion abr. */
	//@ApiModelProperty(value = "Descripción abreviada", dataType = "java.lang.String", required = false)
	private String descripcionAbr;
	
	/** Url. */
	//@ApiModelProperty(value = "URL", dataType = "java.lang.String", required = false)
	private String url;
	
	/**
	 * @return the id
	 */
	public Long getCodigo() {
		return codigo;
	}


	/**
	 * @param id the id to set
	 */
	public void setCodigo(Long l) {
		this.codigo = l;
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
	 * @return the fechaPublicacion
	 */
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}


	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}


	/**
	 * @return the fechaCaducidad
	 */
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}


	/**
	 * @param fechaCaducidad the fechaCaducidad to set
	 */
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}


	/**
	 * @return the fechaActualizacion
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}


	/**
	 * @param fechaActualizacion the fechaActualizacion to set
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the validacion
	 */
	public Integer getValidacion() {
		return validacion;
	}


	/**
	 * @param validacion the validacion to set
	 */
	public void setValidacion(Integer validacion) {
		this.validacion = validacion;
	}


	/**
	 * @return the documentos
	 */
	public List<Long> getDocumentos() {
		return documentos;
	}


	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<Long> documentos) {
		this.documentos = documentos;
	}


	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}


	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}


	/**
	 * @return the hechosVitales
	 */
	public List<Long> getHechosVitales() {
		return hechosVitales;
	}


	/**
	 * @param hechosVitales the hechosVitales to set
	 */
	public void setHechosVitales(List<Long> hechosVitales) {
		this.hechosVitales = hechosVitales;
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


	/**
	 * @return the publicosObjetivo
	 */
	public List<Long> getPublicosObjetivo() {
		return publicosObjetivo;
	}


	/**
	 * @param publicosObjetivo the publicosObjetivo to set
	 */
	public void setPublicosObjetivo(List<Long> publicosObjetivo) {
		this.publicosObjetivo = publicosObjetivo;
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
	 * @return the descripcionAbr
	 */
	public String getDescripcionAbr() {
		return descripcionAbr;
	}


	/**
	 * @param descripcionAbr the descripcionAbr to set
	 */
	public void setDescripcionAbr(String descripcionAbr) {
		this.descripcionAbr = descripcionAbr;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		return "Ficha [id=" + codigo + ", titulo=" + titulo + "]";
	}
	
}
