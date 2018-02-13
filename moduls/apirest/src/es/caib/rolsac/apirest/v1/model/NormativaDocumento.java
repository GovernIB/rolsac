package es.caib.rolsac.apirest.v1.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Clase documento normativa del RestAPI. 
 * 
 * @author slromero
 *
 */
@XmlRootElement
@ApiModel(value = "Documento de normativa", description = "Definición de la clase documento de normativa")
public class NormativaDocumento {

	/** Id. **/
	//@ApiModelProperty(value = "Id", dataType = "java.lang.Long", required = true)
	private Long id;
	
	/** Archivo. **/
	//@ApiModelProperty(value = "Id del archivo", dataType = "java.lang.Long", required = false)
	private Long archivo;

	/** Tipo. **/
	//@ApiModelProperty(value = "Tipo de documento", dataType = "java.lang.Long", required = false)
	private int tipo;
	
	/** Orden. **/
	//@ApiModelProperty(value = "Orden", dataType = "java.lang.Long", required = false)
	private Long orden;
	
	/** Enlace. **/
	//@ApiModelProperty(value = "Enlace", dataType = "java.lang.String", required = false)
	private String enlace;
	
	/** Titulo. **/
	//@ApiModelProperty(value = "Título", dataType = "java.lang.String", required = false)
	private String titulo;
	
	/** Descripcion. **/
	//@ApiModelProperty(value = "Descripción", dataType = "java.lang.String", required = false)
	private String descripcion;
	
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
	 * @return the archivo
	 */
	public Long getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Long archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the orden
	 */
	public Long getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Long orden) {
		this.orden = orden;
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

	@Override
	public String toString() {
		return "NormativaDocumento [id=" + id + "]";
	}
	
}
