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
 * EspaisTerritorials.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_ESPACIO_TERRITORIAL, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_ESPACIO_TERRITORIAL)
public class EspaisTerritorials extends EntidadBase {
	 
	/** coordenadas. **/
	@ApiModelProperty(value = "coordenadas", required = false)
	private String coordenadas;
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
		
	/** nivel **/
	@ApiModelProperty(value = "nivel", required = false)
	private Integer nivel;

	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;
	
	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** padre **/
	@ApiModelProperty(value = "link_padre", required = false)
	private Link link_padre;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long padre;
	
	
	/** logo **/
	@ApiModelProperty(value = "link_logo", required = false)
	private Link link_logo;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long logo ;
	
	/** mapa **/
	@ApiModelProperty(value = "link_mapa", required = false)
	private Link link_mapa;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long mapa;

		
	public EspaisTerritorials (org.ibit.rol.sac.model.EspacioTerritorial ahv, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( ahv, urlBase, idioma, hateoasEnabled);
	}
	
	public EspaisTerritorials() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_logo = this.generaLinkArchivo(this.logo,urlBase,null );
		link_mapa = this.generaLinkArchivo(this.mapa,urlBase,null );
		link_padre = this.generaLink(this.padre, Constantes.ENTIDAD_ESPACIO_TERRITORIAL, Constantes.URL_ESPACIO_TERRITORIAL, urlBase,null );			
	}


	public static EspaisTerritorials valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<EspaisTerritorials> typeRef = new TypeReference<EspaisTerritorials>() {
		};
		EspaisTerritorials obj;
		try {
			obj = (EspaisTerritorials) objectMapper.readValue(json, typeRef);
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

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}
	
	@XmlTransient
	public Long getLogo() {
		return logo;
	}

	public void setLogo(Long logo) {
		this.logo = logo;
	}

	@XmlTransient
	public Long getMapa() {
		return mapa;
	}

	public void setMapa(Long mapa) {
		this.mapa = mapa;
	}

	@XmlTransient
	public Long getPadre() {
		return padre;
	}

	public void setPadre(Long padre) {
		this.padre = padre;
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
	 * @return the nivel
	 */
	public Integer getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
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
	 * @return the link_padre
	 */
	public Link getLink_padre() {
		return link_padre;
	}

	/**
	 * @param link_padre the link_padre to set
	 */
	public void setLink_padre(Link link_padre) {
		this.link_padre = link_padre;
	}

	/**
	 * @return the link_logo
	 */
	public Link getLink_logo() {
		return link_logo;
	}

	/**
	 * @param link_logo the link_logo to set
	 */
	public void setLink_logo(Link link_logo) {
		this.link_logo = link_logo;
	}

	/**
	 * @return the link_mapa
	 */
	public Link getLink_mapa() {
		return link_mapa;
	}

	/**
	 * @param link_mapa the link_mapa to set
	 */
	public void setLink_mapa(Link link_mapa) {
		this.link_mapa = link_mapa;
	}

	
}
