package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

public class AfectacionDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;
	
	private long normaId;
    private String normaNom;
    private String afectacioNom;
    private long afectacioId;		// PK del tipo de afectacion (nombre realmente desafortunado).

    private long idRelatedItem; 	// Para guardado genérico vía AJAX: el valor de la PK de la afectación.
	private long idMainItem;		// Para guardado genérico vía AJAX: el valor de la PK del registro con el que se encuentra relacionada la afectación.
    
    public AfectacionDTO() {
    	super();
    }
    	
	/**
	 * Devuelve el valor de afectacioId.
	 *
	 * @return Valor de afectacioId.
	 */
	public long getAfectacioId() {
		return afectacioId;
	}

	/**
	 * @return the idRelatedItem
	 */
	public long getIdRelatedItem() {
		return idRelatedItem;
	}

	/**
	 * @param idRelatedItem the idRelatedItem to set
	 */
	public void setIdRelatedItem(long idRelatedItem) {
		this.idRelatedItem = idRelatedItem;
	}

	/**
	 * @return the idMainItem
	 */
	public long getIdMainItem() {
		return idMainItem;
	}

	/**
	 * @param idMainItem the idMainItem to set
	 */
	public void setIdMainItem(long idMainItem) {
		this.idMainItem = idMainItem;
	}

	/**
	 * Guarda el valor de afectacioId.
	 *
	 * @param afectacioId Nuevo valor de afectacioId.
	 */
	public void setAfectacioId(long afectacioId) {
		this.afectacioId = afectacioId;
	}

	/**
	 * Devuelve el valor de afectacioNom.
	 *
	 * @return Valor de afectacioNom.
	 */
	public String getAfectacioNom() {
		return afectacioNom;
	}



	/**
	 * Guarda el valor de afectacioNom.
	 *
	 * @param afectacioNom Nuevo valor de afectacioNom.
	 */
	public void setAfectacioNom(String afectacioNom) {
		this.afectacioNom = afectacioNom;
	}



	/**
	 * Devuelve el valor de normaId.
	 *
	 * @return Valor de normaId.
	 */
	public long getNormaId() {
		return normaId;
	}

	/**
	 * Guarda el valor de normaId.
	 *
	 * @param normaId Nuevo valor de normaId.
	 */
	public void setNormaId(long normaId) {
		this.normaId = normaId;
	}

	/**
	 * Devuelve el valor de normaNom.
	 *
	 * @return Valor de normaNom.
	 */
	public String getNormaNom() {
		return normaNom;
	}

	/**
	 * Guarda el valor de normaNom.
	 *
	 * @param normaNom Nuevo valor de normaNom.
	 */
	public void setNormaNom(String normaNom) {
		this.normaNom = normaNom;
	}
	
}
