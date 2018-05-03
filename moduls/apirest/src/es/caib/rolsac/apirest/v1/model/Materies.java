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
 * Materias.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_MATERIA, description = Constantes.TXT_DEFINICION_CLASE
		+ Constantes.ENTIDAD_MATERIA)
public class Materies extends EntidadBase {

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	/** codiHita **/
	@ApiModelProperty(value = "codiHita", required = false)
	private String codiHita;
	
	/** codigoEstandar **/
	@ApiModelProperty(value = "codigoEstandar", required = false)
	private String codigoEstandar;
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion", required = false)
	private String descripcion;
	
	/** destacada **/
	@ApiModelProperty(value = "destacada", required = false)
	private boolean destacada;
	
	/** codigoSIA **/
	@ApiModelProperty(value = "codigoSIA", required = false)
	private Long codigoSIA;
	
	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;
	
	/** palabrasclave **/
	@ApiModelProperty(value = "palabrasclave", required = false)
	private String palabrasclave;

	// -- LINKS--//
	// -- se duplican las entidades para poder generar la clase link en funcion de
	// la propiedad principal (sin "link_")

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

	public Materies(org.ibit.rol.sac.model.Materia elem, String urlBase, String idioma, boolean hateoasEnabled) {
		super(elem, urlBase, idioma, hateoasEnabled);
	}

	public Materies() {
		super();
	}

	@Override
	public void generaLinks(String urlBase) {
		link_foto = this.generaLinkArchivo(this.foto, urlBase, null);
		link_icono = this.generaLinkArchivo(this.icono, urlBase, null);
		link_iconoGrande = this.generaLinkArchivo(this.iconoGrande, urlBase, null);
	}

	public static Materies valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Materies> typeRef = new TypeReference<Materies>() {
		};
		Materies obj;
		try {
			obj = (Materies) objectMapper.readValue(json, typeRef);
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
	 * @return the codiHita
	 */
	public String getCodiHita() {
		return codiHita;
	}

	/**
	 * @param codiHita the codiHita to set
	 */
	public void setCodiHita(String codiHita) {
		this.codiHita = codiHita;
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
	 * @return the destacada
	 */
	public boolean isDestacada() {
		return destacada;
	}

	/**
	 * @param destacada the destacada to set
	 */
	public void setDestacada(boolean destacada) {
		this.destacada = destacada;
	}

	/**
	 * @return the codigoSIA
	 */
	public Long getCodigoSIA() {
		return codigoSIA;
	}

	/**
	 * @param codigoSIA the codigoSIA to set
	 */
	public void setCodigoSIA(Long codigoSIA) {
		this.codigoSIA = codigoSIA;
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

}
