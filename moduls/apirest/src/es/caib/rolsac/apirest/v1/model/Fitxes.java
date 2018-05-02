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
 * Fitxes.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_FICHA, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_FICHA)
public class Fitxes extends EntidadBase {
	 

	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	
	/** descAbr **/
	@ApiModelProperty(value = "descAbr", required = false)
    private java.lang.String descAbr;
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion", required = false)
    private java.lang.String descripcion;
	
	/** fechaActualizacion **/
	@ApiModelProperty(value = "fechaActualizacion", required = false)
    private java.util.Calendar fechaActualizacion;
	
	/** fechaCaducidad **/
	@ApiModelProperty(value = "fechaCaducidad", required = false)
	private java.util.Calendar fechaCaducidad;

	/** fechaPublicacion **/
	@ApiModelProperty(value = "fechaPublicacion", required = false)
    private java.util.Calendar fechaPublicacion;
	
	/** foro_tema **/
	@ApiModelProperty(value = "foro_tema", required = false)
    private java.lang.String foro_tema;
	
	/** info **/
	@ApiModelProperty(value = "info", required = false)
    private java.lang.String info;
	
	/** responsable **/
	@ApiModelProperty(value = "responsable", required = false)
    private java.lang.String responsable;
	
	/** titulo **/
	@ApiModelProperty(value = "titulo", required = false)
    private java.lang.String titulo;
	
	/** url **/
	@ApiModelProperty(value = "url", required = false)
    private java.lang.String url;
	
	/** urlForo **/
	@ApiModelProperty(value = "urlForo", required = false)
    private java.lang.String urlForo;
	
	/** urlVideo **/
	@ApiModelProperty(value = "urlVideo", required = false)
    private java.lang.String urlVideo;
	
	/** validacion **/
	@ApiModelProperty(value = "validacion", required = false)
    private java.lang.Integer validacion;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** baner **/
	@ApiModelProperty(value = "link_baner", required = false)
	private Link link_baner;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long baner;
	
	
	/** icono **/
	@ApiModelProperty(value = "link_icono", required = false)
	private Link link_icono;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long icono;
    
	/** imagen **/
	@ApiModelProperty(value = "link_imagen", required = false)
	private Link link_imagen;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long imagen;
    
		
	public Fitxes (org.ibit.rol.sac.model.Ficha elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Fitxes() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_baner = this.generaLinkArchivo(this.baner, urlBase , null );
		link_icono = this.generaLinkArchivo(this.icono, urlBase , null );
		link_imagen = this.generaLinkArchivo(this.imagen, urlBase , null );
	}


	public static Fitxes valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Fitxes> typeRef = new TypeReference<Fitxes>() {
		};
		Fitxes obj;
		try {
			obj = (Fitxes) objectMapper.readValue(json, typeRef);
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
	 * @return the descAbr
	 */
	public java.lang.String getDescAbr() {
		return descAbr;
	}

	/**
	 * @param descAbr the descAbr to set
	 */
	public void setDescAbr(java.lang.String descAbr) {
		this.descAbr = descAbr;
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
	 * @return the fechaActualizacion
	 */
	public java.util.Calendar getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion the fechaActualizacion to set
	 */
	public void setFechaActualizacion(java.util.Calendar fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the fechaCaducidad
	 */
	public java.util.Calendar getFechaCaducidad() {
		return fechaCaducidad;
	}

	/**
	 * @param fechaCaducidad the fechaCaducidad to set
	 */
	public void setFechaCaducidad(java.util.Calendar fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	/**
	 * @return the fechaPublicacion
	 */
	public java.util.Calendar getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * @return the foro_tema
	 */
	public java.lang.String getForo_tema() {
		return foro_tema;
	}

	/**
	 * @param foro_tema the foro_tema to set
	 */
	public void setForo_tema(java.lang.String foro_tema) {
		this.foro_tema = foro_tema;
	}

	/**
	 * @return the info
	 */
	public java.lang.String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(java.lang.String info) {
		this.info = info;
	}

	/**
	 * @return the responsable
	 */
	public java.lang.String getResponsable() {
		return responsable;
	}

	/**
	 * @param responsable the responsable to set
	 */
	public void setResponsable(java.lang.String responsable) {
		this.responsable = responsable;
	}

	/**
	 * @return the titulo
	 */
	public java.lang.String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(java.lang.String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the url
	 */
	public java.lang.String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	/**
	 * @return the urlForo
	 */
	public java.lang.String getUrlForo() {
		return urlForo;
	}

	/**
	 * @param urlForo the urlForo to set
	 */
	public void setUrlForo(java.lang.String urlForo) {
		this.urlForo = urlForo;
	}

	/**
	 * @return the urlVideo
	 */
	public java.lang.String getUrlVideo() {
		return urlVideo;
	}

	/**
	 * @param urlVideo the urlVideo to set
	 */
	public void setUrlVideo(java.lang.String urlVideo) {
		this.urlVideo = urlVideo;
	}

	/**
	 * @return the validacion
	 */
	public java.lang.Integer getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion the validacion to set
	 */
	public void setValidacion(java.lang.Integer validacion) {
		this.validacion = validacion;
	}

	/**
	 * @return the link_baner
	 */
	public Link getLink_baner() {
		return link_baner;
	}

	/**
	 * @param link_baner the link_baner to set
	 */
	public void setLink_baner(Link link_baner) {
		this.link_baner = link_baner;
	}

	/**
	 * @return the baner
	 */
	@XmlTransient
	public java.lang.Long getBaner() {
		return baner;
	}

	/**
	 * @param baner the baner to set
	 */
	public void setBaner(java.lang.Long baner) {
		this.baner = baner;
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
	public java.lang.Long getIcono() {
		return icono;
	}

	/**
	 * @param icono the icono to set
	 */
	public void setIcono(java.lang.Long icono) {
		this.icono = icono;
	}

	/**
	 * @return the link_imagen
	 */
	public Link getLink_imagen() {
		return link_imagen;
	}

	/**
	 * @param link_imagen the link_imagen to set
	 */
	public void setLink_imagen(Link link_imagen) {
		this.link_imagen = link_imagen;
	}

	/**
	 * @return the imagen
	 */
	@XmlTransient
	public java.lang.Long getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(java.lang.Long imagen) {
		this.imagen = imagen;
	}
	
	

}
