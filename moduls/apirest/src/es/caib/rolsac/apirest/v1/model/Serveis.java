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
 * Serveis.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_SERVICIOS, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_SERVICIOS)
public class Serveis extends EntidadBase {
	 

	
		/** codigo **/
		@ApiModelProperty(value = "codigo", required = false)
		private long codigo;
	
		@ApiModelProperty(value = "codigoServicio", required = false)
	  	private java.lang.String codigoServicio; //en el modelo se llama codigo

		@ApiModelProperty(value = "codigoSIA", required = false)
	    private java.lang.String codigoSIA;

		@ApiModelProperty(value = "correo", required = false)
	    private java.lang.String correo;

		@ApiModelProperty(value = "destinatarios", required = false)
	    private java.lang.String destinatarios;

		@ApiModelProperty(value = "estadoSIA", required = false)
	    private java.lang.String estadoSIA;

		@ApiModelProperty(value = "fechaActualizacion", required = false)
	    private java.util.Calendar fechaActualizacion;

		@ApiModelProperty(value = "fechaDespublicacion", required = false)
	    private java.util.Calendar fechaDespublicacion;

		@ApiModelProperty(value = "fechaPublicacion", required = false)
	    private java.util.Calendar fechaPublicacion;

		@ApiModelProperty(value = "fechaSIA", required = false)
	    private java.util.Calendar fechaSIA;

		@ApiModelProperty(value = "id", required = false)
	    private java.lang.Long id;

		@ApiModelProperty(value = "nombre", required = false)
	    private java.lang.String nombre;

		@ApiModelProperty(value = "nombreResponsable", required = false)
	    private java.lang.String nombreResponsable;

		@ApiModelProperty(value = "objeto", required = false)
	    private java.lang.String objeto;

		@ApiModelProperty(value = "observaciones", required = false)
	    private java.lang.String observaciones;


		@ApiModelProperty(value = "requisitos", required = false)
	    private java.lang.String requisitos;

		@ApiModelProperty(value = "telefono", required = false)
	    private java.lang.String telefono;

		@ApiModelProperty(value = "tramiteUrl", required = false)
	    private java.lang.String tramiteUrl;

		@ApiModelProperty(value = "tramiteId", required = false)
	    private java.lang.String tramiteId;

		@ApiModelProperty(value = "tasaUrl", required = false)
	    private java.lang.String tasaUrl;

		@ApiModelProperty(value = "tramiteVersion", required = false)
	    private java.lang.String tramiteVersion;

		@ApiModelProperty(value = "validacion", required = false)
	    private java.lang.Integer validacion;


	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/**  **/
	@ApiModelProperty(value = "link_servicioResponsable", required = false)
	private Link link_servicioResponsable;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long servicioResponsable;
	
	@ApiModelProperty(value = "link_organoInstructor", required = false)
	private Link link_organoInstructor;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long organoInstructor;
		
	public Serveis (org.ibit.rol.sac.model.Servicio elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);	
		if(elem.getCodigo()!=null ) {					
			this.codigoServicio = elem.getCodigo();				
		}	
	}
	
	public Serveis() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_servicioResponsable = this.generaLink(this.servicioResponsable,Constantes.ENTIDAD_UA,Constantes.URL_UA, urlBase , null );
		link_organoInstructor = this.generaLink(this.organoInstructor,Constantes.ENTIDAD_UA,Constantes.URL_UA, urlBase , null );
	}


	public static Serveis valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Serveis> typeRef = new TypeReference<Serveis>() {
		};
		Serveis obj;
		try {
			obj = (Serveis) objectMapper.readValue(json, typeRef);
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
		if(!SETTERS_INVALIDS.contains("setCodigo")) {
			SETTERS_INVALIDS.add("setCodigo");
		}
		
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
	 * @return the codigoServicio
	 */
	public java.lang.String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * @param codigoServicio the codigoServicio to set
	 */
	public void setCodigoServicio(java.lang.String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	/**
	 * @return the codigoSIA
	 */
	public java.lang.String getCodigoSIA() {
		return codigoSIA;
	}

	/**
	 * @param codigoSIA the codigoSIA to set
	 */
	public void setCodigoSIA(java.lang.String codigoSIA) {
		this.codigoSIA = codigoSIA;
	}

	/**
	 * @return the correo
	 */
	public java.lang.String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(java.lang.String correo) {
		this.correo = correo;
	}

	/**
	 * @return the destinatarios
	 */
	public java.lang.String getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios the destinatarios to set
	 */
	public void setDestinatarios(java.lang.String destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * @return the estadoSIA
	 */
	public java.lang.String getEstadoSIA() {
		return estadoSIA;
	}

	/**
	 * @param estadoSIA the estadoSIA to set
	 */
	public void setEstadoSIA(java.lang.String estadoSIA) {
		this.estadoSIA = estadoSIA;
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
	 * @return the fechaDespublicacion
	 */
	public java.util.Calendar getFechaDespublicacion() {
		return fechaDespublicacion;
	}

	/**
	 * @param fechaDespublicacion the fechaDespublicacion to set
	 */
	public void setFechaDespublicacion(java.util.Calendar fechaDespublicacion) {
		this.fechaDespublicacion = fechaDespublicacion;
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
	 * @return the fechaSIA
	 */
	public java.util.Calendar getFechaSIA() {
		return fechaSIA;
	}

	/**
	 * @param fechaSIA the fechaSIA to set
	 */
	public void setFechaSIA(java.util.Calendar fechaSIA) {
		this.fechaSIA = fechaSIA;
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
	 * @return the nombreResponsable
	 */
	public java.lang.String getNombreResponsable() {
		return nombreResponsable;
	}

	/**
	 * @param nombreResponsable the nombreResponsable to set
	 */
	public void setNombreResponsable(java.lang.String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	/**
	 * @return the objeto
	 */
	public java.lang.String getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto the objeto to set
	 */
	public void setObjeto(java.lang.String objeto) {
		this.objeto = objeto;
	}

	/**
	 * @return the observaciones
	 */
	public java.lang.String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(java.lang.String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the requisitos
	 */
	public java.lang.String getRequisitos() {
		return requisitos;
	}

	/**
	 * @param requisitos the requisitos to set
	 */
	public void setRequisitos(java.lang.String requisitos) {
		this.requisitos = requisitos;
	}

	/**
	 * @return the telefono
	 */
	public java.lang.String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(java.lang.String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the tramiteUrl
	 */
	public java.lang.String getTramiteUrl() {
		return tramiteUrl;
	}

	/**
	 * @param tramiteUrl the tramiteUrl to set
	 */
	public void setTramiteUrl(java.lang.String tramiteUrl) {
		this.tramiteUrl = tramiteUrl;
	}

	/**
	 * @return the tramiteId
	 */
	public java.lang.String getTramiteId() {
		return tramiteId;
	}

	/**
	 * @param tramiteId the tramiteId to set
	 */
	public void setTramiteId(java.lang.String tramiteId) {
		this.tramiteId = tramiteId;
	}

	/**
	 * @return the tasaUrl
	 */
	public java.lang.String getTasaUrl() {
		return tasaUrl;
	}

	/**
	 * @param tasaUrl the tasaUrl to set
	 */
	public void setTasaUrl(java.lang.String tasaUrl) {
		this.tasaUrl = tasaUrl;
	}

	/**
	 * @return the tramiteVersion
	 */
	public java.lang.String getTramiteVersion() {
		return tramiteVersion;
	}

	/**
	 * @param tramiteVersion the tramiteVersion to set
	 */
	public void setTramiteVersion(java.lang.String tramiteVersion) {
		this.tramiteVersion = tramiteVersion;
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
	 * @return the link_servicioResponsable
	 */
	public Link getLink_servicioResponsable() {
		return link_servicioResponsable;
	}

	/**
	 * @param link_servicioResponsable the link_servicioResponsable to set
	 */
	public void setLink_servicioResponsable(Link link_servicioResponsable) {
		this.link_servicioResponsable = link_servicioResponsable;
	}

	/**
	 * @return the servicioResponsable
	 */
	public Long getServicioResponsable() {
		return servicioResponsable;
	}

	/**
	 * @param servicioResponsable the servicioResponsable to set
	 */
	public void setServicioResponsable(Long servicioResponsable) {
		this.servicioResponsable = servicioResponsable;
	}

	/**
	 * @return the link_organoInstructor
	 */
	public Link getLink_organoInstructor() {
		return link_organoInstructor;
	}

	/**
	 * @param link_organoInstructor the link_organoInstructor to set
	 */
	public void setLink_organoInstructor(Link link_organoInstructor) {
		this.link_organoInstructor = link_organoInstructor;
	}

	/**
	 * @return the organoInstructor
	 */
	public Long getOrganoInstructor() {
		return organoInstructor;
	}

	/**
	 * @param organoInstructor the organoInstructor to set
	 */
	public void setOrganoInstructor(Long organoInstructor) {
		this.organoInstructor = organoInstructor;
	}

	/**
	 * @return the id
	 */
	public java.lang.Long getId() {
		return id;
	}
	

}
