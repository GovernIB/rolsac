package es.caib.rolsac.apirest.v1.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * RespuestaBase. Estructura de respuesta que contiene la información comun a todas las respuestas.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "Link", description = "Relaciones con otros objetos")
public class Link {
	 
	public Link(String entidad, String codigo, String url,String descripcio) {
		super();
		this.entidad = entidad;
		this.codigo = codigo;
		this.url = url;
		this.descripcio = descripcio;
	}
	
	public Link(String entidad, String codigo, String url) {
		this(entidad,codigo,url,null);
	}

	public Link() {
		super();
	}


	/** Status a retornar. **/
	@ApiModelProperty(value = "Entidad", dataType = "java.lang.String", required = true)
	private String entidad;
	
	/** Mensaje de  error. **/
	@ApiModelProperty(value = "Codigo", dataType = "java.lang.String", required = false)
	private String codigo;
	
	/** Mensaje de  error. **/
	@ApiModelProperty(value = "Url", dataType = "java.lang.String", required = true)
	private String url;
	
	/** Mensaje de  error. **/
	@ApiModelProperty(value = "Descripcion", dataType = "java.lang.String", required = false)
	private String descripcio;
	


	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the descripcio
	 */
	public String getDescripcio() {
		return descripcio;
	}

	/**
	 * @param descripcio the descripcio to set
	 */
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
		
}
