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
	 
	public Link(String entidad, String codigo, String url,String descripcion,boolean hateoas) {
		super();
		this.rel = entidad;
		this.codigo = codigo;		
		this.descripcion = descripcion;
		if(hateoas) {
			this.href = url;
		}else {
			this.href = null;
		}
	}
	
	public Link(String entidad, String codigo, String url, boolean hateoas) {
		this(entidad,codigo,url,null,hateoas);
	}

	public Link() {
		super();
	}


	/** Status a retornar. **/
	@ApiModelProperty(value = "Entidad relacionada", dataType = "java.lang.String", required = true)
	private String rel;
	
	/** Mensaje de  error. **/
	@ApiModelProperty(value = "Codigo", dataType = "java.lang.String", required = false)
	private String codigo;
	
	/** Mensaje de  error. **/
	@ApiModelProperty(value = "Url", dataType = "java.lang.String", required = true)
	private String href;
	
	/** Mensaje de  error. **/
	@ApiModelProperty(value = "Descripcion", dataType = "java.lang.String", required = false)
	private String descripcion;

	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @param rel the rel to set
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

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
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
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
	



}
