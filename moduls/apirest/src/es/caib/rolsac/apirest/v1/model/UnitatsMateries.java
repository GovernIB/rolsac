package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Unidades Materias.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_UNIDAD_MATERIA, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_UNIDAD_MATERIA)
public class UnitatsMateries extends EntidadBase {
	

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	/** unidadPrincipal **/
	@ApiModelProperty(value = "unidadPrincipal", required = false)
	private String unidadPrincipal;

	/** urlUnidadMateria **/
	@ApiModelProperty(value = "urlUnidadMateria", required = false)
	private String urlUnidadMateria;
	
	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** materia **/
	@ApiModelProperty(value = "link_materia", required = false)
	private Link link_materia;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long materia;

	/** materia **/
	@ApiModelProperty(value = "link_unidad", required = false)
	private Link link_unidad;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long unidad;
	
	
	public UnitatsMateries (org.ibit.rol.sac.model.UnidadMateria elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public UnitatsMateries() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_materia = this.generaLink(this.materia, Constantes.ENTIDAD_MATERIA, Constantes.URL_MATERIA, urlBase,null );		
		link_unidad = this.generaLink(this.unidad, Constantes.ENTIDAD_UA, Constantes.URL_UA, urlBase,null );				
	}


	public static UnitatsMateries valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<UnitatsMateries> typeRef = new TypeReference<UnitatsMateries>() {
		};
		UnitatsMateries obj;
		try {
			obj = (UnitatsMateries) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}

	
	public String toJson() {
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			return objectMapper.writeValueAsString(this);
		} catch (final JsonProcessingException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
	}


	@Override
	protected void addSetersInvalidos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(Long codigo) {
		this.codigo = codigo;		
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * @return the materia
	 */
	@XmlTransient
	public Long getMateria() {
		return materia;
	}

	/**
	 * @param materia the materia to set
	 */
	public void setMateria(Long materia) {
		this.materia = materia;
	}

	/**
	 * @return the unidad
	 */
	@XmlTransient
	public Long getUnidad() {
		return unidad;
	}

	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(Long unidad) {
		this.unidad = unidad;
	}

	/**
	 * @return the unidadPrincipal
	 */
	public String getUnidadPrincipal() {
		return unidadPrincipal;
	}

	/**
	 * @param unidadPrincipal the unidadPrincipal to set
	 */
	public void setUnidadPrincipal(String unidadPrincipal) {
		this.unidadPrincipal = unidadPrincipal;
	}

	/**
	 * @return the urlUnidadMateria
	 */
	public String getUrlUnidadMateria() {
		return urlUnidadMateria;
	}

	/**
	 * @param urlUnidadMateria the urlUnidadMateria to set
	 */
	public void setUrlUnidadMateria(String urlUnidadMateria) {
		this.urlUnidadMateria = urlUnidadMateria;
	}

	/**
	 * @return the idioma
	 */
	public java.lang.String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(java.lang.String idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the link_materia
	 */
	public Link getLink_materia() {
		return link_materia;
	}

	/**
	 * @param link_materia the link_materia to set
	 */
	public void setLink_materia(Link link_materia) {
		this.link_materia = link_materia;
	}

	/**
	 * @return the link_unidad
	 */
	public Link getLink_unidad() {
		return link_unidad;
	}

	/**
	 * @param link_unidad the link_unidad to set
	 */
	public void setLink_unidad(Link link_unidad) {
		this.link_unidad = link_unidad;
	}
}
