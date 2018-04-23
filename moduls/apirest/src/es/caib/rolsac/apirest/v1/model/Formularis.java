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
 * Formulario.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_FORMULARIO, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_FORMULARIO)
public class Formularis extends EntidadBase {
	 

	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;

	/** url **/
	@ApiModelProperty(value = "url", required = false)
	private String url;
	
	/** urlManual **/
	@ApiModelProperty(value = "urlManual", required = false)
	private String urlManual;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** archivo **/
	@ApiModelProperty(value = "link_archivo", required = false)
	private Link link_archivo;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long archivo;
	
	/** manual **/
	@ApiModelProperty(value = "link_manual", required = false)
	private Link link_manual;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long manual;
	
	/** tramite **/
	@ApiModelProperty(value = "link_tramite", required = false)
	private Link link_tramite;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long tramite;
		
	public Formularis (org.ibit.rol.sac.model.Formulario elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Formularis() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_manual = this.generaLinkArchivo(this.manual, urlBase , null );
		link_archivo = this.generaLinkArchivo(this.archivo, urlBase , null );
		link_tramite = this.generaLink(this.tramite, Constantes.ENTIDAD_TRAMITE, Constantes.URL_TRAMITE, urlBase,null );		
	}


	public static Formularis valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Formularis> typeRef = new TypeReference<Formularis>() {
		};
		Formularis obj;
		try {
			obj = (Formularis) objectMapper.readValue(json, typeRef);
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
	 * @return the archivo
	 */
	@XmlTransient
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
	 * @return the manual
	 */
	@XmlTransient
	public Long getManual() {
		return manual;
	}

	/**
	 * @param manual the manual to set
	 */
	public void setManual(Long manual) {
		this.manual = manual;
	}

	/**
	 * @return the tramite
	 */
	public Long getTramite() {
		return tramite;
	}

	/**
	 * @param tramite the tramite to set
	 */
	@XmlTransient
	public void setTramite(Long tramite) {
		this.tramite = tramite;
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
	 * @return the urlManual
	 */
	public String getUrlManual() {
		return urlManual;
	}

	/**
	 * @param urlManual the urlManual to set
	 */
	public void setUrlManual(String urlManual) {
		this.urlManual = urlManual;
	}

	/**
	 * @return the link_archivo
	 */
	public Link getLink_archivo() {
		return link_archivo;
	}

	/**
	 * @param link_archivo the link_archivo to set
	 */
	public void setLink_archivo(Link link_archivo) {
		this.link_archivo = link_archivo;
	}

	/**
	 * @return the link_manual
	 */
	public Link getLink_manual() {
		return link_manual;
	}

	/**
	 * @param link_manual the link_manual to set
	 */
	public void setLink_manual(Link link_manual) {
		this.link_manual = link_manual;
	}

	/**
	 * @return the link_tramite
	 */
	public Link getLink_tramite() {
		return link_tramite;
	}

	/**
	 * @param link_tramite the link_tramite to set
	 */
	public void setLink_tramite(Link link_tramite) {
		this.link_tramite = link_tramite;
	}

	
}
