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
 * Secciones.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_SECCION, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_SECCION)
public class Seccions extends EntidadBase {
	 
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** codigoEstandard **/
	@ApiModelProperty(value = "codigoEstandard", required = false)
	private java.lang.String codigoEstandard;
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion", required = false)
	private java.lang.String descripcion;
	
	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
	private java.lang.String nombre;
	
	/** orden **/
	@ApiModelProperty(value = "orden", required = false)
	private int orden;
	
	/** perfil **/
	@ApiModelProperty(value = "perfil", required = false)
	private java.lang.String perfil;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** padre **/
	@ApiModelProperty(value = "link_padre", required = false)
	private Link link_padre;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long padre;
	
		
	public Seccions (org.ibit.rol.sac.model.Seccion elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Seccions() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_padre = this.generaLink(this.padre,Constantes.ENTIDAD_SECCION, Constantes.URL_SECCION, urlBase , null );
	}


	public static Seccions valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Seccions> typeRef = new TypeReference<Seccions>() {
		};
		Seccions obj;
		try {
			obj = (Seccions) objectMapper.readValue(json, typeRef);
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
	 * @return the codigoEstandard
	 */
	public java.lang.String getCodigoEstandard() {
		return codigoEstandard;
	}

	/**
	 * @param codigoEstandard the codigoEstandard to set
	 */
	public void setCodigoEstandard(java.lang.String codigoEstandard) {
		this.codigoEstandard = codigoEstandard;
	}

	/**
	 * @return the descripcion
	 */
	public java.lang.String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(java.lang.String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the nombre
	 */
	public java.lang.String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
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
	 * @return the perfil
	 */
	public java.lang.String getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(java.lang.String perfil) {
		this.perfil = perfil;
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
	 * @return the padre
	 */
	@XmlTransient
	public Long getPadre() {
		return padre;
	}

	/**
	 * @param padre the padre to set
	 */
	public void setPadre(Long padre) {
		this.padre = padre;
	}

}
