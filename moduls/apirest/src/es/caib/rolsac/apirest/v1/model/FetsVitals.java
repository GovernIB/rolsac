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
 * Hechos vitales.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_HECHOS_VITALES, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_HECHOS_VITALES)
public class FetsVitals extends EntidadBase {
	

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** Codigo estandar. **/
	@ApiModelProperty(value = "codigoEstandar", required = false)
    private String codigoEstandar;
	
	/** descripcion. **/
	@ApiModelProperty(value = "descripcion", required = false)
    private String descripcion;

	
	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private String idioma;
	
	/** orden **/
	@ApiModelProperty(value = "orden", required = false) 
    private int orden;
    
    
	/** nombre. **/
	@ApiModelProperty(value = "nombre", required = false)
    private String nombre;
    
	/** palabrasclave. **/
	@ApiModelProperty(value = "palabrasclave", required = false)
    private String palabrasclave;
    
    
  	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
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
	
	/** foto **/
	@ApiModelProperty(value = "link_foto", required = false)
	private Link link_foto;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long foto;
	
	
	public FetsVitals (org.ibit.rol.sac.model.HechoVital elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public FetsVitals() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_icono = this.generaLinkArchivo(this.icono, urlBase,null );		
		link_iconoGrande = this.generaLinkArchivo(this.iconoGrande, urlBase,null );	
		link_foto = this.generaLinkArchivo(this.foto, urlBase,null );	
	}


	public static FetsVitals valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FetsVitals> typeRef = new TypeReference<FetsVitals>() {
		};
		FetsVitals obj;
		try {
			obj = (FetsVitals) objectMapper.readValue(json, typeRef);
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
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(int orden) {
		this.orden = orden;
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

	
}
