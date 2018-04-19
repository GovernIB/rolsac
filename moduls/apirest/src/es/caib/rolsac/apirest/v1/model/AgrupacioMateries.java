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
 * AgrupacioMateries.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_ARUPACIO_MATERIES, description = Constantes.TXT_DEFINICION_CLASE + Constantes.ENTIDAD_ARUPACIO_MATERIES )
public class AgrupacioMateries extends EntidadBase {
	 
	/** codigoEstandar. **/
	@ApiModelProperty(value = "codigoEstandar",  required = false)
	private String codigoEstandar;
	
	/** codigo **/
	@ApiModelProperty(value = "codigo",  required = false)
	private long codigo;
	
	/** nombre **/
	@ApiModelProperty(value = "nombre",  required = false)
	private String nombre;
	
	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
	
	//-- LINKS--//
	//-- se duplican las propiedades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** contenido **/
	@ApiModelProperty(value = "link_seccion", required = false)
	private Link link_seccion;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long seccion;
	

	public AgrupacioMateries (org.ibit.rol.sac.model.AgrupacionMateria ahv, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( ahv, urlBase, idioma, hateoasEnabled);
	}
	
	public AgrupacioMateries() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		this.link_seccion = this.generaLink(this.seccion, Constantes.ENTIDAD_SECCION, Constantes.URL_SECCION, urlBase,null );
	}


	public static AgrupacioMateries valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<AgrupacioMateries> typeRef = new TypeReference<AgrupacioMateries>() {
		};
		AgrupacioMateries obj;
		try {
			obj = (AgrupacioMateries) objectMapper.readValue(json, typeRef);
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
	 * @return the codigoEstandar
	 */
	public String getCodigoEstandar() {
		return codigoEstandar;
	}

	/**
	 * @param codigoEstandar the codigoEstandar to set
	 */
	public void setCodigoEstandar(String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the seccion
	 */
	@XmlTransient
	public Long getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
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
	 * @return the link_seccion
	 */
	public Link getLink_seccion() {
		return link_seccion;
	}

	/**
	 * @param link_seccion the link_seccion to set
	 */
	public void setLink_seccion(Link link_seccion) {
		this.link_seccion = link_seccion;
	}
	
}
