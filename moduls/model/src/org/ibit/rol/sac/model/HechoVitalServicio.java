package org.ibit.rol.sac.model;

/**
 * Clase hecho vital servicio.
 * @author slromero
 *
 */
public class HechoVitalServicio implements ValueObject, Comparable<HechoVitalServicio> {
    
    private static final long serialVersionUID = -4753725381929109536L;

    /** Id. **/
    private Long id;
    /** Hecho vital. **/
    private HechoVital hechoVital = null;
    /** Servicio. **/
    private Servicio servicio = null;
    /** Orden. **/
    private int orden;
    
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
	 * @return the hechoVital
	 */
	public HechoVital getHechoVital() {
		return hechoVital;
	}
	/**
	 * @param hechoVital the hechoVital to set
	 */
	public void setHechoVital(HechoVital hechoVital) {
		this.hechoVital = hechoVital;
	}
	/**
	 * @return the servicio
	 */
	public Servicio getServicio() {
		return servicio;
	}
	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	/**
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}
	/**
	 * @param orden the orden to set
	 */
	public void setOrden(int orden) {
		this.orden = orden;
	}    
    
    /** 
     * CompareTo de hecho vital servicio. 
     */
	public int compareTo(HechoVitalServicio hvp) {
    	if (hvp == null || this.orden > hvp.getOrden()){
    		return 1;
    	} else if (this.orden < hvp.getOrden()) {
    		return -1;
		} else {
    		return 0;
    	}
	}

   
	
}
