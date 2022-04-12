package org.ibit.rol.sac.model;

/**
 * Tramite plantilla.
 */
public class TramitePlantilla extends Traducible {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Id. **/
	private Long id;

	/** Identificador. **/
	private String identificador;

	/** Version. **/
	private String version;

	/** Version. **/
	private String parametros;

	/** Plataforma. **/
	private Plataforma plataforma;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador
	 *            the identificador to set
	 */
	public void setIdentificador(final String identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(final String version) {
		this.version = version;
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

}
