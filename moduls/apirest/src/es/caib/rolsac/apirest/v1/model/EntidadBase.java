package es.caib.rolsac.apirest.v1.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * RespuestaBase. Estructura de respuesta que contiene la información comun a todas las respuestas.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "EntidadBase", description = "Entidad Base")
public class EntidadBase {
	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private boolean hateoasEnabled;
	
	public EntidadBase() {
		super();
	}

	/** Status a retornar. **/
	@ApiModelProperty(value = "Links", required = false)
	private ArrayList<Link> links=null;
	

	/**
	 * @return the links
	 */
	public ArrayList<Link> getLinks() {
		return this.links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}
	
	
	/**
	 * Añade el link especificado si el modo hateoas está activado y el codigo no es null
	 * 
	 * @param codigo codigo a usar
	 * @param entidad entidad a la que hace referencia el enlace
	 * @param url url relativa de acceso al objeto
	 * @param urlBase urlbase que unida a la url forman una url real. campo opcional, por defecto se usa la existente en el fichero properties
	 * @param descripcion campo al que hace referencia el enlace.
	 */
	public void addLink(String codigo, String entidad, String url, String urlBase, String descripcion) {
		if(hateoasEnabled) {
			if(this.links==null) {
				this.links = new ArrayList<Link>();
			}		
			if(codigo!=null) {
				this.links.add(new Link(entidad,codigo,(StringUtils.isEmpty(urlBase)?Constantes.URL_BASE:urlBase) + url.replace("{0}", codigo),descripcion));
			}	
		}
		
	}
	/**
	 *  Añade el link especificado si el modo hateoas está activado y el codigo no es null
	 */
	public void addLink(Long codigo, String entidad, String url, String urlBase, String descripcion) {	
		if(codigo!=null) {
			this.addLink(codigo.toString(),entidad,url,urlBase,descripcion);
		}
	}
	/**
	 *  Añade el link especificado si el modo hateoas está activado y el codigo no es null
	 */
	public void addLink(Integer codigo, String entidad, String url, String urlBase, String descripcion) {	
		if(codigo!=null) {
			this.addLink(codigo.toString(),entidad,url,urlBase,descripcion);
		}
	}
	/**
	 *  Añade el link especificado si el modo hateoas está activado y el codigo no es null. 
	 */
	public void addLink(String codigo, String entidad, String url, String urlBase) {
		this.addLink(codigo, entidad, url, urlBase,null);
	}
	
	/**
	 *  Añade el link especificado si el modo hateoas está activado y el codigo no es null. incluyendo la 
	 */
	public void addLinkArchivo(Long codigo,String descripcion, String urlBase) {
		if(codigo!=null) {
			this.addLink(codigo.toString(), Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,descripcion );
		}
	}
	
	/**
	 * Si el objeto no es nulo retorna el Id (codigo) del archivo
	 * @param archivo
	 * @return
	 */
	public Long getIdArchivo(org.ibit.rol.sac.model.Archivo archivo) {		
		return (archivo!=null)?archivo.getId():null;
	}

	/**
	 * @return the hateoasEnabled
	 */
	@XmlTransient
	public boolean isHateoasEnabled() {
		return hateoasEnabled;
	}

	/**
	 * @param hateoasEnabled the hateoasEnabled to set
	 */
	public void setHateoasEnabled(boolean hateoasEnabled) {
		this.hateoasEnabled = hateoasEnabled;
	}
	
	
	
	
	
}
