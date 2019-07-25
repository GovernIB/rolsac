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
 * Procediments.
 *
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_PROCEDIMIENTO, description = Constantes.TXT_DEFINICION_CLASE
		+ Constantes.ENTIDAD_PROCEDIMIENTO)
public class Procediments extends EntidadBase {

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	/**  **/
	@ApiModelProperty(value = "destinatarios", required = false)
	private java.lang.String destinatarios;

	/**  **/
	@ApiModelProperty(value = "fechaActualizacion", required = false)
	private java.util.Calendar fechaActualizacion;

	/**  **/
	@ApiModelProperty(value = "fechaCaducidad", required = false)
	private java.util.Calendar fechaCaducidad;

	/**  **/
	@ApiModelProperty(value = "fechaPublicacion", required = false)
	private java.util.Calendar fechaPublicacion;

	/**  **/
	@ApiModelProperty(value = "indicador", required = false)
	private boolean indicador;

	/**  **/
	@ApiModelProperty(value = "dirElectronica", required = false)
	private java.lang.String dirElectronica;

	/**  **/
	@ApiModelProperty(value = "lugar", required = false)
	private java.lang.String lugar;

	/**  **/
	@ApiModelProperty(value = "nombre", required = false)
	private java.lang.String nombre;

	/**  **/
	@ApiModelProperty(value = "notificacion", required = false)
	private java.lang.String notificacion;

	/**  **/
	@ApiModelProperty(value = "observaciones", required = false)
	private java.lang.String observaciones;

	/**  **/
	@ApiModelProperty(value = "plazos", required = false)
	private java.lang.String plazos;

	/**  **/
	@ApiModelProperty(value = "recursos", required = false)
	private java.lang.String recursos;

	/**  **/
	@ApiModelProperty(value = "requisitos", required = false)
	private java.lang.String requisitos;

	/**  **/
	@ApiModelProperty(value = "resolucion", required = false)
	private java.lang.String resolucion;

	/**  **/
	@ApiModelProperty(value = "responsable", required = false)
	private java.lang.String responsable;

	/**  **/
	@ApiModelProperty(value = "resumen", required = false)
	private java.lang.String resumen;

	/**  **/
	@ApiModelProperty(value = "signatura", required = false)
	private java.lang.String signatura;

	/**  **/
	@ApiModelProperty(value = "signatura", required = false)
	private boolean taxa;

	/**  **/
	@ApiModelProperty(value = "url", required = false)
	private java.lang.String url;

	/**  **/
	@ApiModelProperty(value = "validacion", required = false)
	private java.lang.Integer validacion;

	/**  **/
	@ApiModelProperty(value = "codigoSIA", required = false)
	private java.lang.String codigoSIA;

	/**  **/
	@ApiModelProperty(value = "estadoSIA", required = false)
	private java.lang.String estadoSIA;

	/**  **/
	@ApiModelProperty(value = "fechaSIA", required = false)
	private java.util.Calendar fechaSIA;

	@ApiModelProperty(value = "tramite", required = false)
	private java.lang.String tramite;

	@ApiModelProperty(value = "version", required = false)
	private java.lang.Long version;

	/*
	 * private java.lang.String resultat; private boolean ventanillaUnica;
	 */

	// -- LINKS--//
	// -- se duplican las entidades para poder generar la clase link en funcion de
	// la propiedad principal (sin "link_")
	/** servicioResponsable **/
	@ApiModelProperty(value = "link_servicioResponsable", required = false)
	private Link link_servicioResponsable;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long servicioResponsable;

	/** unidadAdministrativa **/
	@ApiModelProperty(value = "link_unidadAdministrativa", required = false)
	private Link link_unidadAdministrativa;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long unidadAdministrativa;

	/** organResolutori **/
	@ApiModelProperty(value = "link_organResolutori", required = false)
	private Link link_organResolutori;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long organResolutori;

	/** familia **/
	@ApiModelProperty(value = "link_familia", required = false)
	private Link link_familia;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long familia;

	////// CASOS ESPECIALES, SE RELLENA LA SUBENTIDAD.
	/** silencio **/
	@ApiModelProperty(value = "silencio", required = false)
	private Silencis silencio;

	/** iniciacion **/
	@ApiModelProperty(value = "iniciacion", required = false)
	private Iniciacions iniciacion;

	/** es comun **/
	@ApiModelProperty(value = "comun", required = false)
	private boolean comun;

	public Procediments(final org.ibit.rol.sac.model.Procedimiento elem, final String urlBase, final String idioma,
			final boolean hateoasEnabled) {
		super(elem, urlBase, idioma, hateoasEnabled);

		try {
			// copiamos los datos que no tienen la misma estructura:
			if (((org.ibit.rol.sac.model.ProcedimientoLocal) elem).getSilencio() != null) {
				this.silencio = new Silencis(((org.ibit.rol.sac.model.ProcedimientoLocal) elem).getSilencio(), urlBase,
						idioma, hateoasEnabled);
			}

			// copiamos los datos que no tienen la misma estructura:
			if (((org.ibit.rol.sac.model.ProcedimientoLocal) elem).getIniciacion() != null) {
				this.iniciacion = new Iniciacions(((org.ibit.rol.sac.model.ProcedimientoLocal) elem).getIniciacion(),
						urlBase, idioma, hateoasEnabled);
			}
		} catch (final Exception e) {

		}

	}

	public Procediments() {
		super();
	}

	@Override
	public void generaLinks(final String urlBase) {
		link_servicioResponsable = this.generaLink(this.servicioResponsable, Constantes.ENTIDAD_UA, Constantes.URL_UA,
				urlBase, null);
		link_unidadAdministrativa = this.generaLink(this.unidadAdministrativa, Constantes.ENTIDAD_UA, Constantes.URL_UA,
				urlBase, null);
		link_organResolutori = this.generaLink(this.organResolutori, Constantes.ENTIDAD_UA, Constantes.URL_UA, urlBase,
				null);
		link_familia = this.generaLink(this.familia, Constantes.ENTIDAD_FAMILIA, Constantes.URL_FAMILIA, urlBase, null);

	}

	public static Procediments valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Procediments> typeRef = new TypeReference<Procediments>() {
		};
		Procediments obj;
		try {
			obj = (Procediments) objectMapper.readValue(json, typeRef);
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
		if (!SETTERS_INVALIDS.contains("setIniciacion")) {
			SETTERS_INVALIDS.add("setIniciacion");
		}

		if (!SETTERS_INVALIDS.contains("setSilencio")) {
			SETTERS_INVALIDS.add("setSilencio");
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
	 * @return the fechaCaducidad
	 */
	public java.util.Calendar getFechaCaducidad() {
		return fechaCaducidad;
	}

	/**
	 * @param fechaCaducidad
	 *            the fechaCaducidad to set
	 */
	public void setFechaCaducidad(final java.util.Calendar fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
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
	 * @return the indicador
	 */
	public boolean isIndicador() {
		return indicador;
	}

	/**
	 * @param indicador
	 *            the indicador to set
	 */
	public void setIndicador(final boolean indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return the dirElectronica
	 */
	public java.lang.String getDirElectronica() {
		return dirElectronica;
	}

	/**
	 * @param dirElectronica
	 *            the dirElectronica to set
	 */
	public void setDirElectronica(final java.lang.String dirElectronica) {
		this.dirElectronica = dirElectronica;
	}

	/**
	 * @return the lugar
	 */
	public java.lang.String getLugar() {
		return lugar;
	}

	/**
	 * @param lugar
	 *            the lugar to set
	 */
	public void setLugar(final java.lang.String lugar) {
		this.lugar = lugar;
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
	 * @return the notificacion
	 */
	public java.lang.String getNotificacion() {
		return notificacion;
	}

	/**
	 * @param notificacion
	 *            the notificacion to set
	 */
	public void setNotificacion(final java.lang.String notificacion) {
		this.notificacion = notificacion;
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
	 * @return the plazos
	 */
	public java.lang.String getPlazos() {
		return plazos;
	}

	/**
	 * @param plazos
	 *            the plazos to set
	 */
	public void setPlazos(final java.lang.String plazos) {
		this.plazos = plazos;
	}

	/**
	 * @return the recursos
	 */
	public java.lang.String getRecursos() {
		return recursos;
	}

	/**
	 * @param recursos
	 *            the recursos to set
	 */
	public void setRecursos(final java.lang.String recursos) {
		this.recursos = recursos;
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
	 * @return the resolucion
	 */
	public java.lang.String getResolucion() {
		return resolucion;
	}

	/**
	 * @param resolucion
	 *            the resolucion to set
	 */
	public void setResolucion(final java.lang.String resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * @return the responsable
	 */
	public java.lang.String getResponsable() {
		return responsable;
	}

	/**
	 * @param responsable
	 *            the responsable to set
	 */
	public void setResponsable(final java.lang.String responsable) {
		this.responsable = responsable;
	}

	/**
	 * @return the resumen
	 */
	public java.lang.String getResumen() {
		return resumen;
	}

	/**
	 * @param resumen
	 *            the resumen to set
	 */
	public void setResumen(final java.lang.String resumen) {
		this.resumen = resumen;
	}

	/**
	 * @return the signatura
	 */
	public java.lang.String getSignatura() {
		return signatura;
	}

	/**
	 * @param signatura
	 *            the signatura to set
	 */
	public void setSignatura(final java.lang.String signatura) {
		this.signatura = signatura;
	}

	/**
	 * @return the taxa
	 */
	public boolean isTaxa() {
		return taxa;
	}

	/**
	 * @param taxa
	 *            the taxa to set
	 */
	public void setTaxa(final boolean taxa) {
		this.taxa = taxa;
	}

	/**
	 * @return the url
	 */
	public java.lang.String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(final java.lang.String url) {
		this.url = url;
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
	@XmlTransient
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
	 * @return the link_unidadAdministrativa
	 */
	public Link getLink_unidadAdministrativa() {
		return link_unidadAdministrativa;
	}

	/**
	 * @param link_unidadAdministrativa
	 *            the link_unidadAdministrativa to set
	 */
	public void setLink_unidadAdministrativa(final Link link_unidadAdministrativa) {
		this.link_unidadAdministrativa = link_unidadAdministrativa;
	}

	/**
	 * @return the unidadAdministrativa
	 */
	@XmlTransient
	public Long getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	/**
	 * @param unidadAdministrativa
	 *            the unidadAdministrativa to set
	 */
	public void setUnidadAdministrativa(final Long unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

	/**
	 * @return the link_organResolutori
	 */
	public Link getLink_organResolutori() {
		return link_organResolutori;
	}

	/**
	 * @param link_organResolutori
	 *            the link_organResolutori to set
	 */
	public void setLink_organResolutori(final Link link_organResolutori) {
		this.link_organResolutori = link_organResolutori;
	}

	/**
	 * @return the organResolutori
	 */
	@XmlTransient
	public Long getOrganResolutori() {
		return organResolutori;
	}

	/**
	 * @param organResolutori
	 *            the organResolutori to set
	 */
	public void setOrganResolutori(final Long organResolutori) {
		this.organResolutori = organResolutori;
	}

	/**
	 * @return the link_familia
	 */
	public Link getLink_familia() {
		return link_familia;
	}

	/**
	 * @param link_familia
	 *            the link_familia to set
	 */
	public void setLink_familia(final Link link_familia) {
		this.link_familia = link_familia;
	}

	/**
	 * @return the familia
	 */
	@XmlTransient
	public Long getFamilia() {
		return familia;
	}

	/**
	 * @param familia
	 *            the familia to set
	 */
	public void setFamilia(final Long familia) {
		this.familia = familia;
	}

	/**
	 * @return the tramite
	 */
	public java.lang.String getTramite() {
		return tramite;
	}

	/**
	 * @param tramite
	 *            the tramite to set
	 */
	public void setTramite(final java.lang.String tramite) {
		this.tramite = tramite;
	}

	/**
	 * @return the version
	 */
	public java.lang.Long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(final java.lang.Long version) {
		this.version = version;
	}

	/**
	 * @return the silencio
	 */
	public Silencis getSilencio() {
		return silencio;
	}

	/**
	 * @param silencio
	 *            the silencio to set
	 */
	public void setSilencio(final Silencis silencio) {
		this.silencio = silencio;
	}

	/**
	 * @return the iniciacion
	 */
	public Iniciacions getIniciacion() {
		return iniciacion;
	}

	/**
	 * @param iniciacion
	 *            the iniciacion to set
	 */
	public void setIniciacion(final Iniciacions iniciacion) {
		this.iniciacion = iniciacion;
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

}
