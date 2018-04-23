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
 * DocumentsNormatives.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_DOCUMENTOS_NORMATIVAS, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_DOCUMENTOS_NORMATIVAS)
public class DocumentsNormatives extends EntidadBase {
	 
	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion",  required = false)
	private String descripcion;
	
	/** orden **/
	@ApiModelProperty(value = "orden", required = false)
	private Long orden;	
	
	/** titulo **/
	@ApiModelProperty(value = "titulo",required = false)
	private String titulo;
	
	
	/** enlace **/
	@ApiModelProperty(value = "enlace",required = false)
	private String enlace;
		
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** archivo **/
	@ApiModelProperty(value = "link_archivo", required = false)
	private Link link_archivo;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long archivo;
	
	/** normativa **/
	@ApiModelProperty(value = "link_normativa", required = false)
	private Link link_normativa;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long normativa;
	
	
	public DocumentsNormatives (org.ibit.rol.sac.model.DocumentoNormativa elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public DocumentsNormatives() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {				
		link_archivo = this.generaLinkArchivo(this.archivo, urlBase , null );
		link_normativa = this.generaLink(this.normativa, Constantes.ENTIDAD_NORMATIVAS, Constantes.URL_NORMATIVAS, urlBase,null );		
	}


	public static DocumentsNormatives valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<DocumentsNormatives> typeRef = new TypeReference<DocumentsNormatives>() {
		};
		DocumentsNormatives obj;
		try {
			obj = (DocumentsNormatives) objectMapper.readValue(json, typeRef);
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
	 * @return the link_normativa
	 */
	public Link getLink_normativa() {
		return link_normativa;
	}

	/**
	 * @param link_normativa the link_normativa to set
	 */
	public void setLink_normativa(Link link_normativa) {
		this.link_normativa = link_normativa;
	}

	/**
	 * @return the normativa
	 */
	@XmlTransient
	public Long getNormativa() {
		return normativa;
	}

	/**
	 * @param normativa the normativa to set
	 */
	public void setNormativa(Long normativa) {
		this.normativa = normativa;
	}

	

	

	
}
