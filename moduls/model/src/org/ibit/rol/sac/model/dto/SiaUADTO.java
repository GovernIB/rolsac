package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

/**
 * Objeto SIA DTO.
 * @author slromero
 *
 */
public class SiaUADTO implements ValueObject {

	/** Serial Version UID.	 */
	private static final long serialVersionUID = 1L;
	
	/** ID. **/
	private Long id;
	/** ID y nombre de la unidad administrativa. **/
	private String ua;
	/** Usuario. **/
	private String usuario;
	/** Contraseña. **/
	private String contrasenya;
	
	/** Constructor. **/
	public SiaUADTO() {}

	/** Constructor completo. **/
	public SiaUADTO(final Long id, final String iUa, final String usuario, final String contrasenya) {
        super();
        this.setId(id);
        this.ua            = iUa;
        this.usuario		 = usuario;
        this.contrasenya 	 = contrasenya;
    }


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idUA
	 */
	public String getUa() {
		return ua;
	}

	/**
	 * @param idUA the idUA to set
	 */
	public void setUa(String ua) {
		this.ua = ua;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the contrasenya
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**
	 * @param contrasenya the contrasenya to set
	 */
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	

  
}
