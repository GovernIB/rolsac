package org.ibit.rol.sac.model;

/**
 * Presenta la plataforma.
 */
public class Plataforma extends Traducible {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Id. **/
	private Long id;

	/** Nombre. **/
	private String identificador;

	/** Orden. **/
	private Integer orden;

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
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(final Integer orden) {
		this.orden = orden;
	}

}
