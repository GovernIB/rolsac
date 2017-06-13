package org.ibit.rol.sac.model;


/**
 * Representación  SiaUA.
 */
public class SiaUA implements ValueObject {
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	
	/** Id. **/
    private Long id;
    
    /** UA. **/
    private UnidadAdministrativa unidadAdministrativa;
    
    /** Usuario. **/
	private String usuario;
	
	/** Contraseña. **/
	private String contrasenya;
	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public final void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * @return the unidadAdministrativa
	 */
	public UnidadAdministrativa getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	/**
	 * @param unidadAdministrativa the unidadAdministrativa to set
	 */
	public void setUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SiaUA [id=" + id + ", usuario=" + usuario + ", contrasenya=" + contrasenya + "]";
	}
}
