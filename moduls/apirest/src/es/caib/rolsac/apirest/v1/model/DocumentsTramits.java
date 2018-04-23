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
 * DocumentsTramits.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_DOCUMENTOS_TRAMITES, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_DOCUMENTOS_TRAMITES)
public class DocumentsTramits extends EntidadBase {
	 
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
	
	/** tipus **/
	@ApiModelProperty(value = "tipus",required = false)
	private Integer tipus;
	
	/** titulo **/
	@ApiModelProperty(value = "titulo",required = false)
	private String titulo;
		
	/** excepcioDocumentacio **/
	@ApiModelProperty(value = "excepcionDocumentacion", required = false) 
	private excepcioDocumentacio excepcionDocumentacion;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private java.lang.Long excepcioDocumentacio;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** archivo **/
	@ApiModelProperty(value = "link_archivo", required = false)
	private Link link_archivo;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long archivo;
	
	/** tramit **/
	@ApiModelProperty(value = "link_tramite", required = false)
	private Link link_tramite;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long tramit;
	
	/** tramit **/
	@ApiModelProperty(value = "link_docCatalogo", required = false)
	private Link link_docCatalogo;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long docCatalogo;
	
	
	
	public DocumentsTramits (org.ibit.rol.sac.model.DocumentTramit elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
		
		if(elem.getExcepcioDocumentacio()!=null) {
			this.excepcionDocumentacion = new excepcioDocumentacio(elem.getExcepcioDocumentacio(),urlBase,idioma,hateoasEnabled);			
		}		
	}
	
	public DocumentsTramits() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {				
		link_archivo = this.generaLinkArchivo(this.archivo, urlBase , null );
		link_tramite = this.generaLink(this.tramit, Constantes.ENTIDAD_TRAMITE, Constantes.URL_TRAMITE, urlBase,null );
		link_docCatalogo = this.generaLink(this.docCatalogo, Constantes.ENTIDAD_CATALOGO_DOCUMENTOS, Constantes.URL_CATALOGO_DOCUMENTOS, urlBase,null );				
	}


	public static DocumentsTramits valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<DocumentsTramits> typeRef = new TypeReference<DocumentsTramits>() {
		};
		DocumentsTramits obj;
		try {
			obj = (DocumentsTramits) objectMapper.readValue(json, typeRef);
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
	 * @return the tipus
	 */
	public Integer getTipus() {
		return tipus;
	}

	/**
	 * @param tipus the tipus to set
	 */
	public void setTipus(Integer tipus) {
		this.tipus = tipus;
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

	/**
	 * @return the tramit
	 */
	@XmlTransient
	public Long getTramit() {
		return tramit;
	}

	/**
	 * @param tramit the tramit to set
	 */
	public void setTramit(Long tramit) {
		this.tramit = tramit;
	}

	/**
	 * @return the link_docCatalogo
	 */
	public Link getLink_docCatalogo() {
		return link_docCatalogo;
	}

	/**
	 * @param link_docCatalogo the link_docCatalogo to set
	 */
	public void setLink_docCatalogo(Link link_docCatalogo) {
		this.link_docCatalogo = link_docCatalogo;
	}

	/**
	 * @return the docCatalogo
	 */
	@XmlTransient
	public Long getDocCatalogo() {
		return docCatalogo;
	}

	/**
	 * @param docCatalogo the docCatalogo to set
	 */
	public void setDocCatalogo(Long docCatalogo) {
		this.docCatalogo = docCatalogo;
	}

	/**
	 * @return the excepcionDocumentacion
	 */
	public excepcioDocumentacio getExcepcionDocumentacion() {
		return excepcionDocumentacion;
	}

	/**
	 * @param excepcionDocumentacion the excepcionDocumentacion to set
	 */
	public void setExcepcionDocumentacion(excepcioDocumentacio excepcionDocumentacion) {
		this.excepcionDocumentacion = excepcionDocumentacion;
	}

	/**
	 * @param excepcioDocumentacio the excepcioDocumentacio to set
	 */
	public void setExcepcioDocumentacio(java.lang.Long excepcioDocumentacio) {
		this.excepcioDocumentacio = excepcioDocumentacio;
	}

	/**
	 * @return the excepcioDocumentacio
	 */
	@XmlTransient
	public java.lang.Long getExcepcioDocumentacio() {
		return excepcioDocumentacio;
	}
	
}
