package org.ibit.rol.sac.model;

/**
 * Traduccion de tramite plantilla.
 */
public class TraduccionTramitePlantilla implements Traduccion {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Descripcion. **/
	private String nombre;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

}
