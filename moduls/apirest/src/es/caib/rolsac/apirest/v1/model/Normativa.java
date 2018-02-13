package es.caib.rolsac.apirest.v1.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Clase normativa del RestAPI. 
 * 
 * @author slromero
 *
 */
@XmlRootElement
@ApiModel(value = "Normativa", description = "Definición de la clase normativa")
public class Normativa {

	/** Id. **/
	//@ApiModelProperty(value = "Id", dataType = "java.lang.Long", required = true)
	private Long id;
	
	/** Título. **/
	//@ApiModelProperty(value = "Titulo", dataType = "java.lang.String", required = false)
	private String titulo;
	
	/** Boletín. **/
	//@ApiModelProperty(value = "Id del boletín", dataType = "java.lang.Long", required = true)
	private Long boletin;
    
	/** Código vuds. **/
	//@ApiModelProperty(value = "Código Vuds", dataType = "java.lang.String", required = false)
	private String codiVuds;
	
	/** Descripción codigo vuds. **/
	//@ApiModelProperty(value = "Descripción código vuds", dataType = "java.lang.String", required = false)
	private String descCodiVuds;
    
    /** Enlace. **/
	//@ApiModelProperty(value = "Enlace", dataType = "java.lang.String", required = false)
	private String enlace;
    
    /** Fecha. **/
	//@ApiModelProperty(value = "Fecha", dataType = "java.util.Date", required = false)
	private Date fecha;
    
    /** Fecha boletín. */
	//@ApiModelProperty(value = "Fecha boletin", dataType = "java.util.Date", required = false)
	private Date fechaBoletin;
    
    /** Observaciones. **/
	//@ApiModelProperty(value = "Observaciones", dataType = "java.lang.String", required = false)
	private String observaciones;
    
    /** Registro. **/
	//@ApiModelProperty(value = "Registro", dataType = "java.lang.Long", required = true)
	private Long registro;
    
    /** Responsable. **/
	//@ApiModelProperty(value = "Responsable", dataType = "java.lang.String", required = false)
	private String responsable;
    
    /** Seccion. **/
	//@ApiModelProperty(value = "Sección", dataType = "java.lang.String", required = false)
	private String seccion;
    
    /** Tipo. **/
	//@ApiModelProperty(value = "Tipo", dataType = "java.lang.Long", required = true)
	private Long tipo;
    
    /** Unidades administrativas. **/
	//@ApiModelProperty(value = "Unidades administrativas", dataType = "java.util.List", required = true)
	private List<Long> unidadesAdministrativas;
    
    /** Validación. **/
	//@ApiModelProperty(value = "Validación (VIGENTE o DEREOGADA)", dataType = "java.lang.Integer", required = true)
	private Integer validacion;
	
    /** Documentos. **/
	//@ApiModelProperty(value = "Documentos", dataType = "java.util.List", required = true)
	private List<NormativaDocumento> documentos;
    
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the boletin
	 */
	public Long getBoletin() {
		return boletin;
	}


	/**
	 * @param boletin the boletin to set
	 */
	public void setBoletin(Long boletin) {
		this.boletin = boletin;
	}


	/**
	 * @return the codiVuds
	 */
	public String getCodiVuds() {
		return codiVuds;
	}


	/**
	 * @param codiVuds the codiVuds to set
	 */
	public void setCodiVuds(String codiVuds) {
		this.codiVuds = codiVuds;
	}


	/**
	 * @return the descCodiVuds
	 */
	public String getDescCodiVuds() {
		return descCodiVuds;
	}


	/**
	 * @param descCodiVuds the descCodiVuds to set
	 */
	public void setDescCodiVuds(String descCodiVuds) {
		this.descCodiVuds = descCodiVuds;
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
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}


	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	/**
	 * @return the fechaBoletin
	 */
	public Date getFechaBoletin() {
		return fechaBoletin;
	}


	/**
	 * @param fechaBoletin the fechaBoletin to set
	 */
	public void setFechaBoletin(Date fechaBoletin) {
		this.fechaBoletin = fechaBoletin;
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
	 * @return the registro
	 */
	public Long getRegistro() {
		return registro;
	}


	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(Long registro) {
		this.registro = registro;
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
	 * @return the tipo
	 */
	public Long getTipo() {
		return tipo;
	}


	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Long tipo) {
		this.tipo = tipo;
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
	 * @return the unidadesAdministrativas
	 */
	public List<Long> getUnidadesAdministrativas() {
		return unidadesAdministrativas;
	}


	/**
	 * @param unidadesAdministrativas the unidadesAdministrativas to set
	 */
	public void setUnidadesAdministrativas(List<Long> unidadesAdministrativas) {
		this.unidadesAdministrativas = unidadesAdministrativas;
	}


	/**
	 * @return the documentos
	 */
	public List<NormativaDocumento> getDocumentos() {
		return documentos;
	}


	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<NormativaDocumento> documentos) {
		this.documentos = documentos;
	}


	@Override
	public String toString() {
		return "Normativa [id=" + id + ", titulo=" + titulo + "]";
	}
	
}
