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
@ApiModel(value = Constantes.ENTIDAD_ENLACE, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_ENLACE)
public class Enllacos extends EntidadBase {

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
    
	/** enlace. **/
	@ApiModelProperty(value = "enlace", required = false)
	private String enlace;
	
	/** orden **/
	@ApiModelProperty(value = "orden", required = false)
	private long orden;
	
	/** enlace. **/
	@ApiModelProperty(value = "titulo", required = false)
	private String titulo;	

	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
	

	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** ficha **/
	@ApiModelProperty(value = "link_ficha", required = false)
	private Link link_ficha;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long ficha;
	
	
	/** procedimiento **/
	@ApiModelProperty(value = "link_procedimiento", required = false)
	private Link link_procedimiento;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long procedimiento;
	
		
	public Enllacos (org.ibit.rol.sac.model.Enlace elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Enllacos() {
		super();
	}
		
	@Override
	public void generaLinks(String urlBase) {
		link_ficha = this.generaLink(this.ficha, Constantes.ENTIDAD_FICHA, Constantes.URL_FICHA, urlBase,null );		
		link_procedimiento = this.generaLink(this.procedimiento, Constantes.ENTIDAD_PROCEDIMIENTO, Constantes.URL_PROCEDIMIENTO, urlBase,null );		
	}


	public static Enllacos valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Enllacos> typeRef = new TypeReference<Enllacos>() {
		};
		Enllacos obj;
		try {
			obj = (Enllacos) objectMapper.readValue(json, typeRef);
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
	 * @return the orden
	 */
	public long getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(long orden) {
		this.orden = orden;
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
	 * @return the link_ficha
	 */
	public Link getLink_ficha() {
		return link_ficha;
	}

	/**
	 * @param link_ficha the link_ficha to set
	 */
	public void setLink_ficha(Link link_ficha) {
		this.link_ficha = link_ficha;
	}

	/**
	 * @return the ficha
	 */
	@XmlTransient
	public Long getFicha() {
		return ficha;
	}

	/**
	 * @param ficha the ficha to set
	 */
	public void setFicha(Long ficha) {
		this.ficha = ficha;
	}

	/**
	 * @return the link_procedimiento
	 */
	public Link getLink_procedimiento() {
		return link_procedimiento;
	}

	/**
	 * @param link_procedimiento the link_procedimiento to set
	 */
	public void setLink_procedimiento(Link link_procedimiento) {
		this.link_procedimiento = link_procedimiento;
	}

	/**
	 * @return the procedimiento
	 */
	@XmlTransient
	public Long getProcedimiento() {
		return procedimiento;
	}

	/**
	 * @param procedimiento the procedimiento to set
	 */
	public void setProcedimiento(Long procedimiento) {
		this.procedimiento = procedimiento;
	}

	

}
