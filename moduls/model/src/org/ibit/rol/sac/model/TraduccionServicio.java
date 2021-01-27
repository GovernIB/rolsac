package org.ibit.rol.sac.model;

/**
 * Traducción de servicio.
 *
 * @author slromero
 *
 */
public class TraduccionServicio implements Traduccion {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Nombre. **/
	private String nombre;

	/** Objeto. **/
	private String objeto;

	/** Destinatarios. **/
	private String destinatarios;

	/** Requisitos. **/
	private String requisitos;

	/** Observaciones. */
	private String observaciones;

	/** LOPD **/
	private String lopdFinalidad;
	private String lopdDestinatario;
	private String lopdDerechos;
	private Archivo lopdInfoAdicional;

	/** URL Tramite Externo **/
	private String urlTramiteExterno;

	/** Constructor vacio. **/
	public TraduccionServicio() {
	}

	/**
	 * Constructor.
	 *
	 * @param nombre
	 * @param objeto
	 * @param destinatarios
	 * @param requisitos
	 * @param observaciones
	 */

	public TraduccionServicio(final String nombre, final String objeto, final String destinatarios,
			final String requisitos, final String observaciones, final String urlTramiteExterno,
			final String lopdFinalidad, final String lopdDestinatario, final String lopdDerechos,
			final Archivo lopdInfoAdicional) {
		this.nombre = nombre;
		this.objeto = objeto;
		this.destinatarios = destinatarios;
		this.requisitos = requisitos;
		this.observaciones = observaciones;
		this.setUrlTramiteExterno(urlTramiteExterno);
		this.lopdFinalidad = lopdFinalidad;
		this.lopdDestinatario = lopdDestinatario;
		this.lopdDerechos = lopdDerechos;
		this.lopdInfoAdicional = lopdInfoAdicional;
	}

	/**
	 * @return the nombre
	 */
	public final String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public final void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the objeto
	 */
	public final String getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto
	 *            the objeto to set
	 */
	public final void setObjeto(final String objeto) {
		this.objeto = objeto;
	}

	/**
	 * @return the destinatarios
	 */
	public final String getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios
	 *            the destinatarios to set
	 */
	public final void setDestinatarios(final String destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * @return the requisitos
	 */
	public final String getRequisitos() {
		return requisitos;
	}

	/**
	 * @param requisitos
	 *            the requisitos to set
	 */
	public final void setRequisitos(final String requisitos) {
		this.requisitos = requisitos;
	}

	/**
	 * @return the observaciones
	 */
	public final String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public final void setObservaciones(final String observaciones) {
		this.observaciones = observaciones;
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

	public String getUrlTramiteExterno() {
		return urlTramiteExterno;
	}

	public void setUrlTramiteExterno(final String urlTramiteExterno) {
		this.urlTramiteExterno = urlTramiteExterno;
	}

}
