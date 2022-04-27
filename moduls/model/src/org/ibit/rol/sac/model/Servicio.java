package org.ibit.rol.sac.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Clase servicio.
 *
 * @author slromero
 *
 */
public class Servicio extends Classificable implements Indexable, Validable, Comparator<Servicio> {

	public static final Integer ESTADO_PUBLICACION_PUBLICA = 1;
	public static final Integer ESTADO_PUBLICACION_INTERNA = 2;
	public static final Integer ESTADO_PUBLICACION_RESERVA = 3;

	private static final long serialVersionUID = 1L;
	/************ SECCION DADES. *********************/
	/** Id. **/
	private Long id;
	/** Codigo SIA. **/
	private String codigoSIA;
	/** Fecha SIA. **/
	private Date fechaSIA;
	/** Estado SIA. **/
	private String estadoSIA;
	/** Codigo. **/
	private String codigo;
	/** Organo instructor. **/
	private UnidadAdministrativa organoInstructor;
	/** Pendiente validar. **/
	private boolean pendienteValidar;

	/************ SECCION DADES. *********************/
	/** Servicio responsable. **/
	private UnidadAdministrativa servicioResponsable;
	/** Nombre del responsable. **/
	private String nombreResponsable;
	/** Correo. **/
	private String correo;
	/** Telefono. **/
	private String telefono;

	/************ SECCION TRAMITE. *******************/

	private boolean presencial;
	private boolean telefonico;
	private boolean telematico;

	/** Es comun. **/
	private boolean comun;
	/** Tramite URL. **/
	// private String tramiteUrl;
	/** Tramite ID. **/
	private String tramiteId;
	/** Tramite version. **/
	private String tramiteVersion;

	/************ MODUL TAXES. ***********************/
	/** Taxa url. **/
	private String tasaUrl;

	/************ MODUL PUBLICACIO. ******************/
	/** Validacion. **/
	private Integer validacion;
	/** Fecha despublicacion. **/
	private Date fechaDespublicacion;
	/** Fecha publicacion. **/
	private Date fechaPublicacion;
	/** Fecha actualizacion. **/
	private Date fechaActualizacion;

	/** Documentos. **/
	private Set<DocumentoServicio> documentos = new HashSet<DocumentoServicio>();

	/** Normativas. **/
	private Set<Normativa> normativas;
	/** Hechos Vitales. **/
	private Set<HechoVitalServicio> hechosVitalesServicios;
	/** Publico objetivo. **/
	private Set<PublicoObjetivo> publicosObjetivo;
	/** Lopd **/
	private LopdLegitimacion lopdLegitimacion;
	private String lopdResponsable;
	private String lopdFinalidad;
	private String lopdDestinatario;
	private String lopdDerechos;
	private Archivo lopdInfoAdicional;
	private boolean lopdActivo;

	// ---------------------------------------------
	// Campos especiales para optimizar la busqueda
	/** Nombre servicio que indica el order by. **/
	private String nombreServicio;
	/** Idioma de la busqueda. **/
	private String idioma;

	/** Plataforma. **/
	private Plataforma plataforma;

	/** Parametros si es un tramite telematico y necesita parametros. **/
	private String parametros;

	/** Mensajes no leidos **/
	private boolean mensajesNoLeidosGestor;
	private boolean mensajesNoLeidosSupervisor;

	/**
	 * Constructor para busqueda optimizada.
	 *
	 * @param id
	 * @param nombreServicio
	 * @param validacion
	 * @param fechaActualizacion
	 * @param fechaDespublicacion
	 * @param fechaPublicacion
	 * @param nombreServicio
	 * @param idioma
	 * @param nombreServicioResponsable
	 */
	public Servicio(final Long id, final String nombreServicio, final Integer validacion, final Date fechaActualizacion,
			final Date fechaDespublicacion, final Date fechaPublicacion, final String idioma,
			final String nombreServicioResponsable, final boolean comun, final String lopdFinalidad,
			final String lopdDestinatario, final String lopdDerechos, final LopdLegitimacion lopdLegitimacion,
			final Archivo lopdInfoAdicional) {

		super();
		this.id = id;
		this.nombreServicio = nombreServicio != null ? nombreServicio : "";
		this.validacion = validacion;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaDespublicacion = fechaDespublicacion;
		this.fechaPublicacion = fechaPublicacion;
		this.idioma = idioma;
		this.nombreResponsable = nombreServicioResponsable;
		this.comun = comun;
		this.lopdLegitimacion = lopdLegitimacion;
		this.lopdFinalidad = lopdFinalidad;
		this.lopdDestinatario = lopdDestinatario;
		this.lopdDerechos = lopdDerechos;
		this.lopdInfoAdicional = lopdInfoAdicional;
	}

	// Constructores
	/**
	 * Constructor.
	 *
	 * @param id
	 */
	public Servicio(final Long id) {
		super();
		this.id = id;
	}

	/**
	 * Constructor.
	 */
	public Servicio() {
		super();
	}

	/**
	 * Idioma.
	 *
	 * @return
	 */
	public String getIdioma() {
		return this.idioma;
	}

	/**
	 * Get id.
	 *
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id.
	 *
	 * @param id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Lista de documentos servicios.
	 *
	 * @return
	 */
	public Set<DocumentoServicio> getDocumentos() {
		return documentos;
	}

	/**
	 * Set documentos servicios.
	 *
	 * @param documentos
	 */
	public void setDocumentos(final Set<DocumentoServicio> documentos) {
		this.documentos = documentos;
	}

	/**
	 * Add documento servicio.
	 *
	 * @param documento
	 */
	public final void addDocumentoServicio(final DocumentoServicio documento) {
		documento.setServicio(this);
		documento.setOrden(Long.valueOf(documentos.size()));
		documentos.add(documento);
	}

	/**
	 * Remove documento servicio.
	 *
	 * @param documento
	 */
	public final void removeDocumentoServicio(final DocumentoServicio documento) {
		documentos.remove(documento);
		Long i = 0l;
		for (final DocumentoServicio documentoServicio : documentos) {
			documentoServicio.setOrden(i);
			i++;
		}
	}

	/**
	 * Get normativas.
	 *
	 * @return
	 */
	public Set<Normativa> getNormativas() {
		return normativas;
	}

	/**
	 * Set normativas.
	 *
	 * @param normativas
	 */
	public void setNormativas(final Set<Normativa> normativas) {
		this.normativas = normativas;
	}

	@Override
	public String toString() {
		final String pid = obtenirId();
		final String nombre = obtenerNombre();
		return "Servicio [id=" + pid + " nombre=" + nombre + "]";
	}

	/**
	 * Obtener nombre.
	 *
	 * @return
	 */
	private String obtenerNombre() {
		final TraduccionServicio traduccion = (TraduccionServicio) getTraduccion();
		if (null == traduccion) {
			return null;
		} else {
			return traduccion.getNombre();
		}
	}

	/**
	 * Obtiene id.
	 *
	 * @return
	 */
	private String obtenirId() {
		return null == id ? null : id.toString();
	}

	/**
	 * Set publico objetivo.
	 *
	 * @param publicosObjetivo
	 */
	public void setPublicosObjetivo(final Set<PublicoObjetivo> publicosObjetivo) {
		this.publicosObjetivo = publicosObjetivo;
	}

	/**
	 * Add publico objetivo.
	 *
	 * @param publicosObjetivo
	 */
	public void addPublicosObjetivo(final PublicoObjetivo publicosObjetivo) {
		this.publicosObjetivo.add(publicosObjetivo);

	}

	/**
	 * Remove publico objetivo.
	 *
	 * @param id
	 */
	public void removePublicosObjetivo(final long id) {
		final PublicoObjetivo pob = new PublicoObjetivo();
		pob.setId(id);
		this.publicosObjetivo.remove(pob);
	}

	@Override
	public boolean equals(final Object obj) {
		final Servicio other = (Servicio) obj;
		return (other instanceof Servicio) && id.equals(other.id);
	}

	/**
	 * Es visible?
	 *
	 * @return
	 */
	public Boolean isVisible() {

		final GregorianCalendar dataActual = new GregorianCalendar();
		Boolean visible;

		final Boolean esPublic = Validacion.PUBLICA.equals(this.getValidacion());
		final Boolean noCaducat = (this.getFechaDespublicacion() != null
				&& this.getFechaDespublicacion().after(dataActual.getTime())) || this.getFechaDespublicacion() == null;
		final Boolean esPublicat = (this.getFechaPublicacion() != null
				&& this.getFechaPublicacion().before(dataActual.getTime())) || this.getFechaPublicacion() == null;

		if (esPublic && noCaducat && esPublicat) {
			visible = Boolean.TRUE;
		} else {
			visible = Boolean.FALSE;
		}
		return visible;
	}

	// Metode creat per poder ser cridat des de la JSP a traves de JSTL
	/**
	 * Get isVisible.
	 *
	 * @return
	 */
	public Boolean getIsVisible() {
		return this.isVisible();
	}

	/**
	 * Set nombre servicio.
	 *
	 * @param nombreServicio
	 */
	public void setNombreServicio(final String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Set idioma.
	 *
	 * @param idioma
	 */
	public void setIdioma(final String idioma) {
		this.idioma = idioma;
	}

	@Override
	public int compare(final Servicio o1, final Servicio o2) {
		int resultado;
		if (o1 == null || o1.getId() == null) {
			resultado = -1;
		} else if (o2 == null || o2.getId() == null) {
			resultado = 1;
		} else {
			resultado = o1.getId().compareTo(o2.getId());
		}
		return resultado;
	}

	/**
	 * @return the codigoSIA
	 */
	public String getCodigoSIA() {
		return codigoSIA;
	}

	/**
	 * @param codigoSIA
	 *            the codigoSIA to set
	 */
	public void setCodigoSIA(final String codigoSIA) {
		this.codigoSIA = codigoSIA;
	}

	/**
	 * @return the fechaSIA
	 */
	public Date getFechaSIA() {
		return fechaSIA;
	}

	/**
	 * @param fechaSIA
	 *            the fechaSIA to set
	 */
	public void setFechaSIA(final Date fechaSIA) {
		this.fechaSIA = fechaSIA;
	}

	/**
	 * @return the estadoSIA
	 */
	public String getEstadoSIA() {
		return estadoSIA;
	}

	/**
	 * @param estadoSIA
	 *            the estadoSIA to set
	 */
	public void setEstadoSIA(final String estadoSIA) {
		this.estadoSIA = estadoSIA;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the organoInstructor
	 */
	public UnidadAdministrativa getOrganoInstructor() {
		return organoInstructor;
	}

	/**
	 * @param organoInstructor
	 *            the organoInstructor to set
	 */
	public void setOrganoInstructor(final UnidadAdministrativa organoInstructor) {
		this.organoInstructor = organoInstructor;
	}

	/**
	 * @return the servicioResponsable
	 */
	public UnidadAdministrativa getServicioResponsable() {
		return servicioResponsable;
	}

	/**
	 * @param servicioResponsable
	 *            the servicioResponsable to set
	 */
	public void setServicioResponsable(final UnidadAdministrativa servicioResponsable) {
		this.servicioResponsable = servicioResponsable;
	}

	/**
	 * @return the nombreResponsable
	 */
	public String getNombreResponsable() {
		return nombreResponsable;
	}

	/**
	 * @param nombreResponsable
	 *            the nombreResponsable to set
	 */
	public void setNombreResponsable(final String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo
	 *            the correo to set
	 */
	public void setCorreo(final String correo) {
		this.correo = correo;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}

	/**
	 * retonna la traducción de la url del idioma por defecto
	 *
	 * @return the tramiteUrl
	 */
	public String getTramiteUrl() {
		// return tramiteUrl;

		final TraduccionServicio traduccion = (TraduccionServicio) getTraduccion();
		if (null == traduccion) {
			return null;
		} else {
			return traduccion.getUrlTramiteExterno();
		}

	}

	/**
	 * @param tramiteUrl
	 *            the tramiteUrl to set
	 */
	// public void setTramiteUrl(final String tramiteUrl) {
	// this.tramiteUrl = tramiteUrl;
	// }

	/**
	 * @return the tramiteId
	 */
	public String getTramiteId() {
		return tramiteId;
	}

	/**
	 * @param tramiteId
	 *            the tramiteId to set
	 */
	public void setTramiteId(final String tramiteId) {
		this.tramiteId = tramiteId;
	}

	/**
	 * @return the tramiteVersion
	 */
	public String getTramiteVersion() {
		return tramiteVersion;
	}

	/**
	 * @param tramiteVersion
	 *            the tramiteVersion to set
	 */
	public void setTramiteVersion(final String tramiteVersion) {
		this.tramiteVersion = tramiteVersion;
	}

	/**
	 * @return the tasaUrl
	 */
	public String getTasaUrl() {
		return tasaUrl;
	}

	/**
	 * @param tasaUrl
	 *            the tasaUrl to set
	 */
	public void setTasaUrl(final String tasaUrl) {
		this.tasaUrl = tasaUrl;
	}

	/**
	 * @return the validacion
	 */
	@Override
	public Integer getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion
	 *            the validacion to set
	 */
	@Override
	public void setValidacion(final Integer validacion) {
		this.validacion = validacion;
	}

	/**
	 * @return the fechaDespublicacion
	 */
	public Date getFechaDespublicacion() {
		return fechaDespublicacion;
	}

	/**
	 * @param fechaDespublicacion
	 *            the fechaDespublicacion to set
	 */
	public void setFechaDespublicacion(final Date fechaDespublicacion) {
		this.fechaDespublicacion = fechaDespublicacion;
	}

	/**
	 * @return the fechaPublicacion
	 */
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion
	 *            the fechaPublicacion to set
	 */
	public void setFechaPublicacion(final Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * @return the fechaActualizacion
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion
	 *            the fechaActualizacion to set
	 */
	public void setFechaActualizacion(final Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the hechosVitalesServicios
	 */
	public Set<HechoVitalServicio> getHechosVitalesServicios() {
		return hechosVitalesServicios;
	}

	/**
	 * @param hechosVitalesServicios
	 *            the hechosVitalesServicios to set
	 */
	public void setHechosVitalesServicios(final Set<HechoVitalServicio> hechosVitalesServicios) {
		this.hechosVitalesServicios = hechosVitalesServicios;
	}

	/**
	 * @return the publicosObjetivo
	 */
	public Set<PublicoObjetivo> getPublicosObjetivo() {
		return publicosObjetivo;
	}

	/**
	 * @return the nombreServicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Add documento.
	 *
	 * @param documento
	 */
	public void addDocumento(final DocumentoServicio documento) {
		documento.setServicio(this);
		documento.setOrden(Long.valueOf(documentos.size()));
		documentos.add(documento);
	}

	/**
	 * Add Hecho Vitales Servicio.
	 *
	 * @param hpv
	 */
	public void addHechoVitalServicio(final HechoVitalServicio hpv) {
		hpv.setServicio(this);
		hechosVitalesServicios.add(hpv);
	}

	/**
	 * Remove hecho vitales servicio.
	 *
	 * @param hechovp
	 */
	public void removeHechoVitalServicio(final HechoVitalServicio hechovp) {
		hechovp.setServicio(null);
		hechosVitalesServicios.remove(hechovp);
	}

	public ServicioRemoto crearRemoto() {
		final ServicioRemoto remoto = new ServicioRemoto();
		try {
			remoto.setParamValue(getId().toString());
			remoto.setParamName("idServicio");

			PropertyUtils.copyProperties(remoto, this);
			PropertyUtils.copyProperties(remoto, this.getTraduccion());
		} catch (final IllegalAccessException e) {
			;
		} catch (final InvocationTargetException e) {
			;
		} catch (final NoSuchMethodException e) {
			;
		}

		return remoto;
	}

	/**
	 * @return the presencial
	 */
	public boolean isPresencial() {
		return presencial;
	}

	/**
	 * @param presencial
	 *            the presencial to set
	 */
	public void setPresencial(final boolean presencial) {
		this.presencial = presencial;
	}

	/**
	 * @return the telefonico
	 */
	public boolean isTelefonico() {
		return telefonico;
	}

	/**
	 * @param telefonico
	 *            the telefonico to set
	 */
	public void setTelefonico(final boolean telefonico) {
		this.telefonico = telefonico;
	}

	/**
	 * @return the telematico
	 */
	public boolean isTelematico() {
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
	 * @return the plataforma
	 */
	public Plataforma getPlataforma() {
		return plataforma;
	}

	/**
	 * @param plataforma
	 *            the plataforma to set
	 */
	public void setPlataforma(final Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	/**
	 * @return the parametros
	 */
	public String getParametros() {
		return parametros;
	}

	/**
	 * @param parametros
	 *            the parametros to set
	 */
	public void setParametros(final String parametros) {
		this.parametros = parametros;
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

	/**
	 * @return the lopdFinalidad
	 */
	public String getLopdFinalidad() {
		return lopdFinalidad;
	}

	/**
	 * @param lopdFinalidad
	 *            the lopdFinalidad to set
	 */
	public void setLopdFinalidad(final String lopdFinalidad) {
		this.lopdFinalidad = lopdFinalidad;
	}

	/**
	 * @return the lopdInfoAdicional
	 */
	public Archivo getLopdInfoAdicional() {
		return lopdInfoAdicional;
	}

	/**
	 * @param lopdInfoAdicional
	 *            the lopdInfoAdicional to set
	 */
	public void setLopdInfoAdicional(final Archivo lopdInfoAdicional) {
		this.lopdInfoAdicional = lopdInfoAdicional;
	}

	/**
	 * @return the lopdActivo
	 */
	public boolean isLopdActivo() {
		return lopdActivo;
	}

	/**
	 * @param lopdActivo
	 *            the lopdActivo to set
	 */
	public void setLopdActivo(final boolean lopdActivo) {
		this.lopdActivo = lopdActivo;
	}

	/**
	 * @return the pendienteValidar
	 */
	public boolean isPendienteValidar() {
		return pendienteValidar;
	}

	/**
	 * @param pendienteValidar
	 *            the pendienteValidar to set
	 */
	public void setPendienteValidar(final boolean pendienteValidar) {
		this.pendienteValidar = pendienteValidar;
	}

	/**
	 * @return the mensajesNoLeidosGestor
	 */
	public boolean isMensajesNoLeidosGestor() {
		return mensajesNoLeidosGestor;
	}

	/**
	 * @param mensajesNoLeidosGestor
	 *            the mensajesNoLeidosGestor to set
	 */
	public void setMensajesNoLeidosGestor(final boolean mensajesNoLeidosGestor) {
		this.mensajesNoLeidosGestor = mensajesNoLeidosGestor;
	}

	/**
	 * @return the mensajesNoLeidosSupervisor
	 */
	public boolean isMensajesNoLeidosSupervisor() {
		return mensajesNoLeidosSupervisor;
	}

	/**
	 * @param mensajesNoLeidosSupervisor
	 *            the mensajesNoLeidosSupervisor to set
	 */
	public void setMensajesNoLeidosSupervisor(final boolean mensajesNoLeidosSupervisor) {
		this.mensajesNoLeidosSupervisor = mensajesNoLeidosSupervisor;
	}

}
