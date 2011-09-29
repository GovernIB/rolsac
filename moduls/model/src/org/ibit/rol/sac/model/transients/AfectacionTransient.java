package org.ibit.rol.sac.model.transients;

import org.ibit.rol.sac.model.ValueObject;

public class AfectacionTransient implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;
	
	private long normaId;
    private String normaNom;
	
	public AfectacionTransient(long normaId, String normaNom) {
	    super();
	    this.normaId = normaId;
	    this.normaNom = normaNom;
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
