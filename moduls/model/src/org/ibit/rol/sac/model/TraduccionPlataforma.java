package org.ibit.rol.sac.model;

/**
 * Created by IntelliJ IDEA. User: mgonzalez Date: 01-jun-2007 Time: 11:55:28
 * (PORMAD)
 */
public class TraduccionPlataforma implements Traduccion {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Descripcion. **/
	private String descripcion;

	/** Url acceso. **/
	private String urlAcceso;

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the urlAcceso
	 */
	public String getUrlAcceso() {
		return urlAcceso;
	}

	/**
	 * @param urlAcceso
	 *            the urlAcceso to set
	 */
	public void setUrlAcceso(final String urlAcceso) {
		this.urlAcceso = urlAcceso;
	}
}
