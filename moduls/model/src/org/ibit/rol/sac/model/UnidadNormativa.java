package org.ibit.rol.sac.model;

/** 
 * Unidad administrativa por normativa. 
 * 
 * @author slromero
 *
 */
public class UnidadNormativa extends Traducible {
	
	/** Serial version UID.	 */
	private static final long serialVersionUID = 1L;
	/** ID. **/
	private Long id;
	/** Unidad administrativa. **/ 
    private UnidadAdministrativa unidadAdministrativa;
    /** Normativa. **/
    private Normativa normativa;
    
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
	 * @return the normativa
	 */
	public Normativa getNormativa() {
		return normativa;
	}
	/**
	 * @param normativa the normativa to set
	 */
	public void setNormativa(Normativa normativa) {
		this.normativa = normativa;
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
    
	
}
