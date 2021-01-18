package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

/**
 * Objeto SIA DTO.
 *
 * @author slromero
 *
 */
public class LopdLegitimacionDTO implements ValueObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** ID. **/
	private Long id;
	/** ID de la lopd legitimacion. **/
	private String identificador;
	/** Nombre de la lopd legitimacion. **/
	private String nombre;
	/** Usuario. **/
	private boolean porDefecto;

	/** Constructor. **/
	public LopdLegitimacionDTO() {
	}

	/** Constructor completo. **/
	public LopdLegitimacionDTO(final Long id, final String identificador, final String nombre,
			final boolean porDefecto) {
		super();
		this.setId(id);
		this.setIdentificador(identificador);
		this.nombre = nombre;
		this.setPorDefecto(porDefecto);
	}

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
