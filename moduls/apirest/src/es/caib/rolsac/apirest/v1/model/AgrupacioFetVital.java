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
@ApiModel(value = Constantes.ENTIDAD_ARUPACIO_FET_VITAL, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_ARUPACIO_FET_VITAL)
public class AgrupacioFetVital extends EntidadBase {
	 
	/** codigoEstandar. **/
	@ApiModelProperty(value = "codigoEstandar", dataType = "java.lang.String", required = false)
	private String codigoEstandar;
	
	/** contenido **/
	@ApiModelProperty(value = "contenido", dataType = "java.lang.Long", required = false)
	private Long contenido;
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion", dataType = "java.lang.String", required = false)
	private String descripcion;
	
	/** distribComp **/
	@ApiModelProperty(value = "distribComp", dataType = "java.lang.Long", required = false)
	private Long distribComp;	
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", dataType = "java.lang.Long", required = false)
	private long codigo;
	
	/** nombre **/
	@ApiModelProperty(value = "nombre", dataType = "java.lang.Long", required = false)
	private String nombre;
	
	
	
	/** palabrasclave **/
	@ApiModelProperty(value = "palabrasclave", dataType = "java.lang.Long", required = false)
	private String palabrasclave;
	
	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** foto **/
	@ApiModelProperty(value = "link_foto", required = false)
	private Link link_foto;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long foto;
	
	/** icono **/
	@ApiModelProperty(value = "link_icono", required = false)
	private Link link_icono;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long icono;
	
	/** iconoGrande **/
	@ApiModelProperty(value = "link_iconoGrande", required = false)
	private Link link_iconoGrande;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long iconoGrande;
	
	/** publico **/
	@ApiModelProperty(value = "link_publico",  required = false)
	private Link link_publico;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long publico;
	
	/** normativa 
	 * 
	 * hemos revisado el modelo y en realidad no hay correspòndencia con el modelo de datos, por lo que
	 * no vendrá nunca rellenado. deberia eliminarse, se mantiene para que tenga una correspondencia con el
	 * antiguo api soap
	 * **/
	@ApiModelProperty(value = "link_normativa", required = false)
	private Link link_normativa;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long normativa;
	
	
	

	public AgrupacioFetVital (org.ibit.rol.sac.model.AgrupacionHechoVital ahv, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( ahv, urlBase, idioma, hateoasEnabled);
	}
	
	public AgrupacioFetVital() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
				
		link_foto = this.generaLinkArchivo(this.foto, urlBase , null );
		link_icono = this.generaLinkArchivo(this.icono, urlBase , null );
		link_iconoGrande = this.generaLinkArchivo(this.iconoGrande,urlBase,null );
		link_publico = this.generaLink(this.publico, Constantes.ENTIDAD_PUBLICO, Constantes.URL_PUBLICO, urlBase,null );		
	}


	public static AgrupacioFetVital valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<AgrupacioFetVital> typeRef = new TypeReference<AgrupacioFetVital>() {
		};
		AgrupacioFetVital obj;
		try {
			obj = (AgrupacioFetVital) objectMapper.readValue(json, typeRef);
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
	 * @return the contenido
	 */
	public Long getContenido() {
		return contenido;
	}


	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(Long contenido) {
		this.contenido = contenido;
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
	 * @return the distribComp
	 */
	public Long getDistribComp() {
		return distribComp;
	}


	/**
	 * @param distribComp the distribComp to set
	 */
	public void setDistribComp(Long distribComp) {
		this.distribComp = distribComp;
	}


	/**
	 * @return the foto
	 */
	
	@XmlTransient
	public Long getFoto() {
		return foto;
	}


	/**
	 * @param foto the foto to set
	 */
	public void setFoto(Long foto) {
		this.foto = foto;
	}


	/**
	 * @return the icono
	 */
	@XmlTransient
	public Long getIcono() {
		return icono;
	}


	/**
	 * @param icono the icono to set
	 */
	public void setIcono(Long icono) {
		this.icono = icono;
	}


	/**
	 * @return the iconoGrande
	 */
	@XmlTransient
	public Long getIconoGrande() {
		return iconoGrande;
	}


	/**
	 * @param iconoGrande the iconoGrande to set
	 */
	public void setIconoGrande(Long iconoGrande) {
		this.iconoGrande = iconoGrande;
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


	/**
	 * @return the palabrasclave
	 */
	public String getPalabrasclave() {
		return palabrasclave;
	}


	/**
	 * @param palabrasclave the palabrasclave to set
	 */
	public void setPalabrasclave(String palabrasclave) {
		this.palabrasclave = palabrasclave;
	}


	/**
	 * @return the publico
	 */
	@XmlTransient
	public Long getPublico() {
		return publico;
	}


	/**
	 * @param publico the publico to set
	 */
	public void setPublico(Long publico) {
		this.publico = publico;
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
	 * @return the link_foto
	 */
	public Link getLink_foto() {
		return link_foto;
	}

	/**
	 * @param link_foto the link_foto to set
	 */
	public void setLink_foto(Link link_foto) {
		this.link_foto = link_foto;
	}

	/**
	 * @return the link_icono
	 */
	public Link getLink_icono() {
		return link_icono;
	}

	/**
	 * @param link_icono the link_icono to set
	 */
	public void setLink_icono(Link link_icono) {
		this.link_icono = link_icono;
	}

	/**
	 * @return the link_iconoGrande
	 */
	public Link getLink_iconoGrande() {
		return link_iconoGrande;
	}

	/**
	 * @param link_iconoGrande the link_iconoGrande to set
	 */
	public void setLink_iconoGrande(Link link_iconoGrande) {
		this.link_iconoGrande = link_iconoGrande;
	}

	/**
	 * @return the link_publico
	 */
	public Link getLink_publico() {
		return link_publico;
	}

	/**
	 * @param link_publico the link_publico to set
	 */
	public void setLink_publico(Link link_publico) {
		this.link_publico = link_publico;
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
	
}
