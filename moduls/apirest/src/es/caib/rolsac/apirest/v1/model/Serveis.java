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
@ApiModel(value = Constantes.ENTIDAD_SERVICIOS, description = Constantes.TXT_DEFINICION_CLASE
		+ Constantes.ENTIDAD_SERVICIOS)
public class Serveis extends EntidadBase {

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	@ApiModelProperty(value = "codigoServicio", required = false)
	private java.lang.String codigoServicio; // en el modelo se llama codigo

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

	// @ApiModelProperty(value = "tramiteUrl", required = false)
	// private java.lang.String tramiteUrl;

	@ApiModelProperty(value = "urlTramiteExterno", required = false)
	private java.lang.String urlTramiteExterno;

	@ApiModelProperty(value = "tramiteId", required = false)
	private java.lang.String tramiteId;

	@ApiModelProperty(value = "tasaUrl", required = false)
	private java.lang.String tasaUrl;

	@ApiModelProperty(value = "tramiteVersion", required = false)
	private java.lang.String tramiteVersion;

	@ApiModelProperty(hidden = true)
	private Plataformas plataforma;

	@ApiModelProperty(value = "parametros", required = false)
	private java.lang.String parametros;

	@ApiModelProperty(value = "telematico", required = false)
	private boolean telematico;

	@ApiModelProperty(value = "validacion", required = false)
	private java.lang.Integer validacion;

	// -- LINKS--//
	// -- se duplican las entidades para poder generar la clase link en funcion de
	// la propiedad principal (sin "link_")
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

	/** es comun **/
	@ApiModelProperty(value = "comun", required = false)
	private boolean comun;

	@ApiModelProperty
	private LopdLegitimacion lopdLegitimacion;

	/** Info Adicional **/
	@ApiModelProperty(value = "link_lopdInfoAdicional", required = false)
	private Link link_lopdInfoAdicional;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private java.lang.Long lopdInfoAdicional;

	@ApiModelProperty(value = "lopdResponsable", required = false)
	private String lopdResponsable;

	@ApiModelProperty(value = "lopdFinalidad", required = false)
	private String lopdFinalidad;

	@ApiModelProperty(value = "lopdDestinatario", required = false)
	private String lopdDestinatario;

	@ApiModelProperty(value = "lopdDerechos", required = false)
	private String lopdDerechos;

	public Serveis(final org.ibit.rol.sac.model.Servicio elem, final String urlBase, final String idioma,
			final boolean hateoasEnabled) {
		super(elem, urlBase, idioma, hateoasEnabled);
		if (elem.getCodigo() != null) {
			this.codigoServicio = elem.getCodigo();
		}

		try {
			// copiamos los datos que no tienen la misma estructura:
			if (elem.getPlataforma() != null) {
				this.plataforma = new Plataformas(elem.getPlataforma(), urlBase, idioma, hateoasEnabled);
			}

			// copiamos los datos que no tienen la misma estructura:
			if (elem.getLopdLegitimacion() != null) {
				this.lopdLegitimacion = new LopdLegitimacion(elem.getLopdLegitimacion(), urlBase, idioma,
						hateoasEnabled);
			}

		} catch (final Exception e) {

		}
	}

	public Serveis() {
		super();
	}

	@Override
	public void generaLinks(final String urlBase) {
		link_servicioResponsable = this.generaLink(this.servicioResponsable, Constantes.ENTIDAD_UA, Constantes.URL_UA,
				urlBase, null);
		link_organoInstructor = this.generaLink(this.organoInstructor, Constantes.ENTIDAD_UA, Constantes.URL_UA,
				urlBase, null);
		link_lopdInfoAdicional = this.generaLinkArchivo(this.lopdInfoAdicional, urlBase, null);

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
		if (!SETTERS_INVALIDS.contains("setCodigo")) {
			SETTERS_INVALIDS.add("setCodigo");
		}

		if (!SETTERS_INVALIDS.contains("setPlataforma")) {
			SETTERS_INVALIDS.add("setPlataforma");
		}

	}

	@Override
	public void setId(final Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(final long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codigoServicio
	 */
	public java.lang.String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * @param codigoServicio
	 *            the codigoServicio to set
	 */
	public void setCodigoServicio(final java.lang.String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	/**
	 * @return the codigoSIA
	 */
	public java.lang.String getCodigoSIA() {
		return codigoSIA;
	}

	/**
	 * @param codigoSIA
	 *            the codigoSIA to set
	 */
	public void setCodigoSIA(final java.lang.String codigoSIA) {
		this.codigoSIA = codigoSIA;
	}

	/**
	 * @return the correo
	 */
	public java.lang.String getCorreo() {
		return correo;
	}

	/**
	 * @param correo
	 *            the correo to set
	 */
	public void setCorreo(final java.lang.String correo) {
		this.correo = correo;
	}

	/**
	 * @return the destinatarios
	 */
	public java.lang.String getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios
	 *            the destinatarios to set
	 */
	public void setDestinatarios(final java.lang.String destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * @return the estadoSIA
	 */
	public java.lang.String getEstadoSIA() {
		return estadoSIA;
	}

	/**
	 * @param estadoSIA
	 *            the estadoSIA to set
	 */
	public void setEstadoSIA(final java.lang.String estadoSIA) {
		this.estadoSIA = estadoSIA;
	}

	/**
	 * @return the fechaActualizacion
	 */
	public java.util.Calendar getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion
	 *            the fechaActualizacion to set
	 */
	public void setFechaActualizacion(final java.util.Calendar fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the fechaDespublicacion
	 */
	public java.util.Calendar getFechaDespublicacion() {
		return fechaDespublicacion;
	}

	/**
	 * @param fechaDespublicacion
	 *            the fechaDespublicacion to set
	 */
	public void setFechaDespublicacion(final java.util.Calendar fechaDespublicacion) {
		this.fechaDespublicacion = fechaDespublicacion;
	}

	/**
	 * @return the fechaPublicacion
	 */
	public java.util.Calendar getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion
	 *            the fechaPublicacion to set
	 */
	public void setFechaPublicacion(final java.util.Calendar fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * @return the fechaSIA
	 */
	public java.util.Calendar getFechaSIA() {
		return fechaSIA;
	}

	/**
	 * @param fechaSIA
	 *            the fechaSIA to set
	 */
	public void setFechaSIA(final java.util.Calendar fechaSIA) {
		this.fechaSIA = fechaSIA;
	}

	/**
	 * @return the nombre
	 */
	public java.lang.String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(final java.lang.String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nombreResponsable
	 */
	public java.lang.String getNombreResponsable() {
		return nombreResponsable;
	}

	/**
	 * @param nombreResponsable
	 *            the nombreResponsable to set
	 */
	public void setNombreResponsable(final java.lang.String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	/**
	 * @return the objeto
	 */
	public java.lang.String getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto
	 *            the objeto to set
	 */
	public void setObjeto(final java.lang.String objeto) {
		this.objeto = objeto;
	}

	/**
	 * @return the observaciones
	 */
	public java.lang.String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(final java.lang.String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the requisitos
	 */
	public java.lang.String getRequisitos() {
		return requisitos;
	}

	/**
	 * @param requisitos
	 *            the requisitos to set
	 */
	public void setRequisitos(final java.lang.String requisitos) {
		this.requisitos = requisitos;
	}

	/**
	 * @return the telefono
	 */
	public java.lang.String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(final java.lang.String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @param tramiteUrl
	 *            the tramiteUrl to set
	 */
	/*
	 * public void setTramiteUrl(final java.lang.String tramiteUrl) {
	 * this.tramiteUrl = tramiteUrl; }
	 */

	/**
	 * @return the tramiteId
	 */
	public java.lang.String getTramiteId() {
		return tramiteId;
	}

	/**
	 * @param tramiteId
	 *            the tramiteId to set
	 */
	public void setTramiteId(final java.lang.String tramiteId) {
		this.tramiteId = tramiteId;
	}

	/**
	 * @return the tasaUrl
	 */
	public java.lang.String getTasaUrl() {
		return tasaUrl;
	}

	/**
	 * @param tasaUrl
	 *            the tasaUrl to set
	 */
	public void setTasaUrl(final java.lang.String tasaUrl) {
		this.tasaUrl = tasaUrl;
	}

	/**
	 * @return the tramiteVersion
	 */
	public java.lang.String getTramiteVersion() {
		return tramiteVersion;
	}

	/**
	 * @param tramiteVersion
	 *            the tramiteVersion to set
	 */
	public void setTramiteVersion(final java.lang.String tramiteVersion) {
		this.tramiteVersion = tramiteVersion;
	}

	/**
	 * @return the validacion
	 */
	public java.lang.Integer getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion
	 *            the validacion to set
	 */
	public void setValidacion(final java.lang.Integer validacion) {
		this.validacion = validacion;
	}

	/**
	 * @return the link_servicioResponsable
	 */
	public Link getLink_servicioResponsable() {
		return link_servicioResponsable;
	}

	/**
	 * @param link_servicioResponsable
	 *            the link_servicioResponsable to set
	 */
	public void setLink_servicioResponsable(final Link link_servicioResponsable) {
		this.link_servicioResponsable = link_servicioResponsable;
	}

	/**
	 * @return the servicioResponsable
	 */
	public Long getServicioResponsable() {
		return servicioResponsable;
	}

	/**
	 * @param servicioResponsable
	 *            the servicioResponsable to set
	 */
	public void setServicioResponsable(final Long servicioResponsable) {
		this.servicioResponsable = servicioResponsable;
	}

	/**
	 * @return the link_organoInstructor
	 */
	public Link getLink_organoInstructor() {
		return link_organoInstructor;
	}

	/**
	 * @param link_organoInstructor
	 *            the link_organoInstructor to set
	 */
	public void setLink_organoInstructor(final Link link_organoInstructor) {
		this.link_organoInstructor = link_organoInstructor;
	}

	/**
	 * @return the organoInstructor
	 */
	public Long getOrganoInstructor() {
		return organoInstructor;
	}

	/**
	 * @param organoInstructor
	 *            the organoInstructor to set
	 */
	public void setOrganoInstructor(final Long organoInstructor) {
		this.organoInstructor = organoInstructor;
	}

	/**
	 * @return the comun
	 */
	public boolean isComun() {
		return comun;
	}

	/**
	 * @param comun
	 *            the comun to set
	 */
	public void setComun(final boolean comun) {
		this.comun = comun;
	}

	/**
	 * @return the id
	 */
	public java.lang.Long getId() {
		return id;
	}

	/**
	 * @return the telematico
	 */
	public boolean getTelematico() {
		return telematico;
	}

	/**
	 * @param telematico
	 *            the telematico to set
	 */
	public void setTelematico(final boolean telematico) {
		this.telematico = telematico;
	}

	/**
	 * @return the parametros
	 */
	public java.lang.String getParametros() {
		return parametros;
	}

	/**
	 * @param parametros
	 *            the parametros to set
	 */
	public void setParametros(final java.lang.String parametros) {
		this.parametros = parametros;
	}

	/**
	 * @return the plataforma
	 */
	public Plataformas getPlataforma() {
		return plataforma;
	}

	/**
	 * @param plataforma
	 *            the plataforma to set
	 */
	public void setPlataforma(final Plataformas plataforma) {
		this.plataforma = plataforma;
	}

	/**
	 * @return the urlTramiteExterno
	 */
	public java.lang.String getUrlTramiteExterno() {
		return urlTramiteExterno;
	}

	/**
	 * @param urlTramiteExterno
	 *            the urlTramiteExterno to set
	 */
	public void setUrlTramiteExterno(final java.lang.String urlTramiteExterno) {
		this.urlTramiteExterno = urlTramiteExterno;
	}

	/**
	 * @return the link_lopdInfoAdicional
	 */
	public Link getLink_lopdInfoAdicional() {
		return link_lopdInfoAdicional;
	}

	/**
	 * @param link_lopdInfoAdicional
	 *            the link_lopdInfoAdicional to set
	 */
	public void setLink_lopdInfoAdicional(final Link link_lopdInfoAdicional) {
		this.link_lopdInfoAdicional = link_lopdInfoAdicional;
	}

	/**
	 * @return the lopdInfoAdicional
	 */
	public java.lang.Long getLopdInfoAdicional() {
		return lopdInfoAdicional;
	}

	/**
	 * @param lopdInfoAdicional
	 *            the lopdInfoAdicional to set
	 */
	public void setLopdInfoAdicional(final java.lang.Long lopdInfoAdicional) {
		this.lopdInfoAdicional = lopdInfoAdicional;
	}

	/**
	 * @return the lopdResponsable
	 */
	public String getLopdResponsable() {
		return lopdResponsable;
	}

	/**
	 * @param lopdResponsable
	 *            the lopdResponsable to set
	 */
	public void setLopdResponsable(final String lopdResponsable) {
		this.lopdResponsable = lopdResponsable;
	}

	/**
	 * @return the lopdFinalidad
	 */
	public String getLopdFinalidad() {
		return lopdFinalidad;
	}

	/**
	 * @return the lopdLegitimacion
	 */
	public LopdLegitimacion getLopdLegitimacion() {
		return lopdLegitimacion;
	}

	/**
	 * @param lopdLegitimacion
	 *            the lopdLegitimacion to set
	 */
	public void setLopdLegitimacion(final LopdLegitimacion lopdLegitimacion) {
		this.lopdLegitimacion = lopdLegitimacion;
	}

	/**
	 * @param lopdFinalidad
	 *            the lopdFinalidad to set
	 */
	public void setLopdFinalidad(final String lopdFinalidad) {
		this.lopdFinalidad = lopdFinalidad;
	}

	/**
	 * @return the lopdDestinatario
	 */
	public String getLopdDestinatario() {
		return lopdDestinatario;
	}

	/**
	 * @param lopdDestinatario
	 *            the lopdDestinatario to set
	 */
	public void setLopdDestinatario(final String lopdDestinatario) {
		this.lopdDestinatario = lopdDestinatario;
	}

	/**
	 * @return the lopdDerechos
	 */
	public String getLopdDerechos() {
		return lopdDerechos;
	}

	/**
	 * @param lopdDerechos
	 *            the lopdDerechos to set
	 */
	public void setLopdDerechos(final String lopdDerechos) {
		this.lopdDerechos = lopdDerechos;
	}

}
