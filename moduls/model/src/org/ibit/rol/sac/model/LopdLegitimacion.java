package org.ibit.rol.sac.model;

/**
 * Representación Lopd Legitimacion.
 */
public class LopdLegitimacion extends Traducible implements ValueObject {

	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;

	/** Id. **/
	private Long id;

	/** Usuario. **/
	private String identificador;

	/** Contraseña. **/
	private boolean porDefecto;

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(final Long id) {
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
	 * @return the porDefecto
	 */
	public boolean isPorDefecto() {
		return porDefecto;
	}

	/**
	 * @param porDefecto
	 *            the porDefecto to set
	 */
	public void setPorDefecto(final boolean porDefecto) {
		this.porDefecto = porDefecto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LopdLeg [id=" + id + ", identificador=" + identificador + ", pordefecto=" + porDefecto + "]";
	}
}
