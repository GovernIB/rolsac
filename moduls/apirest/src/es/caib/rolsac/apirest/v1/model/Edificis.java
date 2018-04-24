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
@ApiModel(value = Constantes.ENTIDAD_EDIFICIO, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_EDIFICIO)
public class Edificis extends EntidadBase {

	 
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
    
	/** codigoPostal. **/
	@ApiModelProperty(value = "codigoPostal", required = false)
	private String codigoPostal;
	
	/** descripcion. **/
	@ApiModelProperty(value = "descripcion", required = false)
	private String descripcion;
	
	/** direccion. **/
	@ApiModelProperty(value = "direccion", required = false)
	private String direccion;
	
	/** email. **/
	@ApiModelProperty(value = "email", required = false)
	private String email;
	
	/** fax. **/
	@ApiModelProperty(value = "fax", required = false)
	private String fax;
	
	/** latitud. **/
	@ApiModelProperty(value = "latitud", required = false)
	private String latitud;
	
	/** longitud. **/
	@ApiModelProperty(value = "longitud", required = false)
	private String longitud;
	
	/** poblacion. **/
	@ApiModelProperty(value = "poblacion", required = false)
	private String poblacion;
	
	/** telefono. **/
	@ApiModelProperty(value = "telefono", required = false)
	private String telefono;

	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
	

	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** fotoPequenya **/
	@ApiModelProperty(value = "link_fotoPequenya", required = false)
	private Link link_fotoPequenya;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long fotoPequenya;
	
	
	/** fotoGrande **/
	@ApiModelProperty(value = "link_fotoGrande", required = false)
	private Link link_fotoGrande;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long fotoGrande ;
	
	/** plano **/
	@ApiModelProperty(value = "link_plano", required = false)
	private Link link_plano;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long plano;

		
	public Edificis (org.ibit.rol.sac.model.Edificio elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Edificis() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_fotoGrande = this.generaLinkArchivo(this.fotoGrande,urlBase,null );
		link_fotoPequenya = this.generaLinkArchivo(this.fotoPequenya,urlBase,null );
		link_plano = this.generaLinkArchivo(this.plano,urlBase,null );
	}


	public static Edificis valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Edificis> typeRef = new TypeReference<Edificis>() {
		};
		Edificis obj;
		try {
			obj = (Edificis) objectMapper.readValue(json, typeRef);
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
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	 * @return the link_fotoPequenya
	 */
	public Link getLink_fotoPequenya() {
		return link_fotoPequenya;
	}

	/**
	 * @param link_fotoPequenya the link_fotoPequenya to set
	 */
	public void setLink_fotoPequenya(Link link_fotoPequenya) {
		this.link_fotoPequenya = link_fotoPequenya;
	}

	/**
	 * @return the fotoPequenya
	 */
	@XmlTransient
	public Long getFotoPequenya() {
		return fotoPequenya;
	}

	/**
	 * @param fotoPequenya the fotoPequenya to set
	 */
	public void setFotoPequenya(Long fotoPequenya) {
		this.fotoPequenya = fotoPequenya;
	}

	/**
	 * @return the link_fotoGrande
	 */
	public Link getLink_fotoGrande() {
		return link_fotoGrande;
	}

	/**
	 * @param link_fotoGrande the link_fotoGrande to set
	 */
	public void setLink_fotoGrande(Link link_fotoGrande) {
		this.link_fotoGrande = link_fotoGrande;
	}

	/**
	 * @return the fotoGrande
	 */
	@XmlTransient
	public Long getFotoGrande() {
		return fotoGrande;
	}

	/**
	 * @param fotoGrande the fotoGrande to set
	 */
	public void setFotoGrande(Long fotoGrande) {
		this.fotoGrande = fotoGrande;
	}

	/**
	 * @return the link_plano
	 */
	public Link getLink_plano() {
		return link_plano;
	}

	/**
	 * @param link_plano the link_plano to set
	 */
	public void setLink_plano(Link link_plano) {
		this.link_plano = link_plano;
	}

	/**
	 * @return the plano
	 */
	@XmlTransient
	public Long getPlano() {
		return plano;
	}

	/**
	 * @param plano the plano to set
	 */
	public void setPlano(Long plano) {
		this.plano = plano;
	}

}
