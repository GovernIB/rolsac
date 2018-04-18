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
 * Idioma.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_DOCUMENTOS, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_ARUPACIO_FET_VITAL)
public class Documents extends EntidadBase {
	 
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
	
	
	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** archivo **/
	@ApiModelProperty(value = "link_archivo", required = false)
	private Link link_archivo;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long archivo;
	
	/** procedimiento **/
	@ApiModelProperty(value = "link_procedimiento", required = false)
	private Link link_procedimiento;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long procedimiento;
	
	/** ficha **/
	@ApiModelProperty(value = "link_ficha", required = false)
	private Link link_ficha;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long ficha;
	
	
	public Documents (org.ibit.rol.sac.model.Documento elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Documents() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {				
		link_archivo = this.generaLinkArchivo(this.archivo, urlBase , null );
		link_procedimiento = this.generaLink(this.procedimiento, Constantes.ENTIDAD_PROCEDIMIENTO, Constantes.URL_PROCEDIMIENTO, urlBase,null );
		link_ficha = this.generaLink(this.ficha, Constantes.ENTIDAD_FICHA, Constantes.URL_FICHA, urlBase,null );
	}


	public static Documents valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Documents> typeRef = new TypeReference<Documents>() {
		};
		Documents obj;
		try {
			obj = (Documents) objectMapper.readValue(json, typeRef);
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
	
	
	
	

	
	
}
