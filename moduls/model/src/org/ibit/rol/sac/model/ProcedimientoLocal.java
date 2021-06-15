package org.ibit.rol.sac.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Modificado para (PORMAD)
 */
@SuppressWarnings("deprecation")
public class ProcedimientoLocal extends Classificable
		implements Procedimiento, Indexable, Validable, Comparator<ProcedimientoLocal> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String signatura;
	private List<Tramite> tramites;
	private List<Documento> documentos;
	private Set<Normativa> normativas;
	private Date fechaCaducidad;
	private Date fechaPublicacion;
	private Date fechaActualizacion;

	private String tramite;
	private Long version;
	private String url;
	private Long orden;
	private Long orden2;
	private Long orden3;
	private Integer validacion;
	private UnidadAdministrativa unidadAdministrativa; // organisme responsable
	private Familia familia;
	private Iniciacion iniciacion;
	private String indicador;
	private String ventanillaUnica;
	// #351 se cambia info por dirElectronica
	// private String info;
	private String responsable;
	private Set<HechoVitalProcedimiento> hechosVitalesProcedimientos;
	private Set<PublicoObjetivo> publicosObjetivo;
	private String taxa;
	private UnidadAdministrativa organResolutori;
	private UnidadAdministrativa servicioResponsable;
	private String dirElectronica;
	private String codigoSIA;
	private Date fechaSIA;
	private String estadoSIA;
	private SilencioAdm silencio;
	private boolean comun;
	private boolean pendienteValidar;

	// ---------------------------------------------
	// Campos especiales para optimizar la búsqueda
	private String nombreProcedimiento;
	private String nombreFamilia;
	private String idioma;

	// LOPD
	private String lopdResponsable;
	private LopdLegitimacion lopdLegitimacion;
	/** Lopd Transient **/
	private String lopdFinalidad;
	private String lopdDestinatario;
	private String lopdDerechos;
	private Archivo lopdInfoAdicional;

	// Constructor para búsqueda optimizada
	public ProcedimientoLocal(final Long id, final String nombreProcedimiento, final Integer validacion,
			final Date fechaActualizacion, final boolean comun, final String lopdFinalidad,
			final String lopdDestinatario, final String lopdDerechos, final LopdLegitimacion lopdLegitimacion,
			final Archivo lopdInfoAdicional, final Date fechaCaducidad, final Date fechaPublicacion,
			final String nombreFamilia, final String idioma, final UnidadAdministrativa ua) {

		super();

		this.id = id;
		this.nombreProcedimiento = nombreProcedimiento != null ? nombreProcedimiento : "";
		this.validacion = validacion;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaCaducidad = fechaCaducidad;
		this.fechaPublicacion = fechaPublicacion;
		this.nombreFamilia = nombreFamilia != null ? nombreFamilia : "";
		this.unidadAdministrativa = ua;
		this.idioma = idioma;
		this.comun = comun;
		this.lopdFinalidad = lopdFinalidad;
		this.lopdDestinatario = lopdDestinatario;
		this.lopdDerechos = lopdDerechos;
		this.lopdLegitimacion = lopdLegitimacion;
		this.lopdInfoAdicional = lopdInfoAdicional;
	}

	// ---------------------------------------------

	// Constructores
	public ProcedimientoLocal(final Long id) {
		super();
		this.id = id;
	}

	public ProcedimientoLocal() {
		super();
	}

	public String getNombreFamilia() {
		return this.nombreFamilia;
	}

	public String getNombreProcedimiento() {
		return this.nombreProcedimiento;
	}

	public String getIdioma() {
		return this.idioma;
	}
	// -------------------------------------------

	// get & set

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public String getSignatura() {
		return signatura;
	}

	@Override
	public void setSignatura(final String signatura) {
		this.signatura = signatura;
	}

	public List<Tramite> getTramites() {
		return tramites;
	}

	public void setTramites(final List<Tramite> tramites) {
		this.tramites = tramites;
	}

	public void addTramite(final Tramite tramite) {
		tramite.setProcedimiento(this);
		tramite.setOrden((long) tramites.size());
		tramites.add(tramite);

	}

	public void removeTramite(final Tramite tramite) {
		final int ind = tramites.indexOf(tramite);
		tramite.setProcedimiento(null);
		tramites.remove(tramite);
		for (int i = ind; i < tramites.size(); i++) {
			final Tramite t = tramites.get(i);
			if (t != null)
				t.setOrden((long) i);
		}
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(final List<Documento> documentos) {
		this.documentos = documentos;
	}

	public void addDocumento(final Documento documento) {
		documento.setProcedimiento(this);
		documento.setOrden(documentos.size());
		documentos.add(documento);
	}

	public void removeDocumento(final Documento documento) {
		final int ind = documentos.indexOf(documento);
		documentos.remove(ind);
		for (int i = ind; i < documentos.size(); i++) {
			final Documento d = documentos.get(i);
			d.setOrden(i);
		}
	}

	public Set<Normativa> getNormativas() {
		return normativas;
	}

	public void setNormativas(final Set<Normativa> normativas) {
		this.normativas = normativas;
	}

	@Override
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	@Override
	public void setFechaCaducidad(final Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	@Override
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	@Override
	public void setFechaPublicacion(final Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	@Override
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	@Override
	public void setFechaActualizacion(final Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Override
	public Integer getValidacion() {
		return validacion;
	}

	@Override
	public void setValidacion(final Integer validacion) {
		this.validacion = validacion;
	}

	public UnidadAdministrativa getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	public void setUnidadAdministrativa(final UnidadAdministrativa unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(final Familia familia) {
		this.familia = familia;
	}

	public Iniciacion getIniciacion() {
		return iniciacion;
	}

	public void setIniciacion(final Iniciacion iniciacion) {
		this.iniciacion = iniciacion;
	}

	public Set<HechoVitalProcedimiento> getHechosVitalesProcedimientos() {
		return hechosVitalesProcedimientos;
	}

	public void setHechosVitalesProcedimientos(final Set<HechoVitalProcedimiento> hechosVitalesProcedimientos) {
		this.hechosVitalesProcedimientos = hechosVitalesProcedimientos;
	}

	public void addHechoVitalProcedimiento(final HechoVitalProcedimiento hpv) {
		hpv.setProcedimiento(this);
		hechosVitalesProcedimientos.add(hpv);
	}

	public void removeHechoVitalProcedimiento(final HechoVitalProcedimiento hechovp) {
		hechovp.setProcedimiento(null);
		hechosVitalesProcedimientos.remove(hechovp);
	}

	public ProcedimientoRemotoAntiguo crearRemoto() {
		final ProcedimientoRemotoAntiguo remoto = new ProcedimientoRemotoAntiguo();
		try {
			remoto.setParamValue(getId().toString());
			remoto.setParamName("idProcedimiento");

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

	public String getTramite() {
		return tramite;
	}

	public void setTramite(final String tramite) {
		this.tramite = tramite;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(final Long version) {
		this.version = version;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(final Long orden) {
		this.orden = orden;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(final String indicador) {
		this.indicador = indicador;
	}

	public String getVentanillaUnica() {
		return ventanillaUnica;
	}

	public void setVentanillaUnica(final String ventana) {
		this.ventanillaUnica = ventana;
	}

	public Long getOrden2() {
		return orden2;
	}

	public void setOrden2(final Long orden2) {
		this.orden2 = orden2;
	}

	public Long getOrden3() {
		return orden3;
	}

	public void setOrden3(final Long orden3) {
		this.orden3 = orden3;
	}

	@Override
	public String toString() {
		final String pid = obtenirId();
		final String nombre = obtenerNombre();
		return "ProcedimientoLocal [id=" + pid + " nombre=" + nombre + "]";
	}

	private String obtenerNombre() {
		final TraduccionProcedimientoLocal traduccion = (TraduccionProcedimientoLocal) getTraduccion();
		if (null == traduccion)
			return null;

		return traduccion.getNombre();
	}

	private String obtenirId() {
		return null == id ? null : id.toString();
	}

	@Override
	public int compare(final ProcedimientoLocal u1, final ProcedimientoLocal u2) {

		int res = 0;
		if (u1 == null) {
			res = -1;
		} else if (u2 == null) {
			res = 1;
		} else if (u1.getOrden() != null && u2.getOrden() != null) {
			res = u1.getOrden().intValue() - u2.getOrden().intValue();
		} else {
			res = u1.getId().intValue() - u2.getId().intValue();
		}
		return res;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(final String responsable) {
		this.responsable = responsable;
	}

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(final String taxa) {
		this.taxa = taxa;
	}

	public UnidadAdministrativa getOrganResolutori() {
		return organResolutori;
	}

	public void setOrganResolutori(final UnidadAdministrativa organResolutori) {
		this.organResolutori = organResolutori;
	}

	public Set<PublicoObjetivo> getPublicosObjetivo() {
		return publicosObjetivo;
	}

	public void setPublicosObjetivo(final Set<PublicoObjetivo> publicosObjetivo) {
		this.publicosObjetivo = publicosObjetivo;
	}

	public void addPublicosObjetivo(final PublicoObjetivo publicosObjetivo) {
		this.publicosObjetivo.add(publicosObjetivo);

	}

	public void removePublicosObjetivo(final long id) {
		final PublicoObjetivo pob = new PublicoObjetivo();
		pob.setId(id);
		this.publicosObjetivo.remove(pob);
	}

	// u92770[enric] anyadido equals para que procedimiento pueda ser testeable con
	// easyMock.
	@Override
	public boolean equals(final Object obj) {
		final ProcedimientoLocal other = (ProcedimientoLocal) obj;
		return (other instanceof ProcedimientoLocal) && id.equals(other.id);
	}

	String getNombreUnidadAdministrativa(final String idioma) {
		return ((TraduccionUA) unidadAdministrativa.getTraduccion(idioma)).getNombre();
	}

	public boolean esVentanillaUnica() {
		return "1".equals(getVentanillaUnica());
	}

	public Boolean isVisible() {

		final GregorianCalendar dataActual = new GregorianCalendar();
		Boolean visible;

		final Boolean esPublic = Validacion.PUBLICA.equals(this.getValidacion());
		final Boolean noCaducat = (this.getFechaCaducidad() != null
				&& this.getFechaCaducidad().after(dataActual.getTime())) || this.getFechaCaducidad() == null;
		final Boolean esPublicat = (this.getFechaPublicacion() != null
				&& this.getFechaPublicacion().before(dataActual.getTime())) || this.getFechaPublicacion() == null;

		if (esPublic && noCaducat && esPublicat) {
			visible = Boolean.TRUE;
		} else {
			visible = Boolean.FALSE;
		}
		return visible;
	}

	// Mètode creat per poder ser cridat des de la JSP a través de JSTL
	public Boolean getIsVisible() {
		return this.isVisible();
	}

	public void setNombreProcedimiento(final String nombreProcedimiento) {
		this.nombreProcedimiento = nombreProcedimiento;
	}

	public void setNombreFamilia(final String nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}

	public void setIdioma(final String idioma) {
		this.idioma = idioma;
	}

	public UnidadAdministrativa getServicioResponsable() {
		return servicioResponsable;
	}

	public void setServicioResponsable(final UnidadAdministrativa servicioResponsable) {
		this.servicioResponsable = servicioResponsable;
	}

	public String getDirElectronica() {
		return dirElectronica;
	}

	public void setDirElectronica(final String dirElectronica) {
		this.dirElectronica = dirElectronica;
	}

	public String getCodigoSIA() {
		return codigoSIA;
	}

	public void setCodigoSIA(final String codigoSIA) {
		this.codigoSIA = codigoSIA;
	}

	public SilencioAdm getSilencio() {
		return silencio;
	}

	public void setSilencio(final SilencioAdm silencio) {
		this.silencio = silencio;
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
	 * Para la cache añadir normativas.
	 */
	public void addNormativa(final Normativa normativa) {
		boolean encontrado = false;
		if (this.normativas != null) {
			for (final Normativa norm : this.normativas) {
				if (norm.getId() != null && normativa.getId() != null
						&& normativa.getId().compareTo(norm.getId()) == 0) {
					encontrado = true;
					break;
				}
			}
		}

		if (!encontrado) {
			normativa.getProcedimientos().add(this);
			this.normativas.add(normativa);
		}
	}

	/**
	 * Para la cache borrar normativas.
	 *
	 * @param normativa
	 */
	public void borrarNormativa(final Normativa normativa) {

		boolean encontrado = false;
		if (this.normativas != null) {
			for (final Normativa norm : this.normativas) {
				if (norm.getId() != null && normativa.getId() != null
						&& normativa.getId().compareTo(norm.getId()) == 0) {
					encontrado = true;
					break;
				}
			}
		}

		if (encontrado) {
			normativa.getProcedimientos().remove(this);
			this.normativas.remove(normativa);
		}

	}

	/**
	 * Para mergear dos procedimientos.
	 *
	 * @param procedimientoNuevo
	 */
	public void mergeNormativas(final ProcedimientoLocal procedimientoNuevo) {

		// Las que desaparecen
		final List<Normativa> normativasBorrar = new ArrayList<Normativa>();
		for (final Normativa normativa : this.getNormativas()) {
			boolean existe = false;
			for (final Normativa norm : procedimientoNuevo.getNormativas()) {
				if (norm.getId() != null && norm.getId().compareTo(normativa.getId()) == 0) {
					existe = true;
					break;
				}
			}

			if (!existe) {
				normativasBorrar.add(normativa);
			}
		}

		for (final Normativa norm : normativasBorrar) {
			this.borrarNormativa(norm);
		}

		// Las que aparecen nuevas
		final List<Normativa> normativasNuevas = new ArrayList<Normativa>();
		for (final Normativa normativa : procedimientoNuevo.getNormativas()) {

			boolean existe = false;
			for (final Normativa norm : this.getNormativas()) {
				if (norm.getId() != null && norm.getId().compareTo(normativa.getId()) == 0) {
					existe = true;
					break;
				}
			}

			if (!existe) {
				normativasNuevas.add(normativa);
			}
		}

		for (final Normativa norm : normativasNuevas) {
			this.addNormativa(norm);
		}

	}

}
