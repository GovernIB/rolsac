package org.ibit.rol.sac.model.transients;

import java.util.List;

import org.ibit.rol.sac.model.ValueObject;

public class AfectacionesTransient implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;
	
	private List<AfectacionTransient> listaAfectaciones;
	
	
    public AfectacionesTransient() {
    	super();
    }


	/**
	 * Devuelve el valor de listaAfectaciones.
	 *
	 * @return Valor de listaAfectaciones.
	 */
	public List<AfectacionTransient> getListaAfectaciones() {
		return listaAfectaciones;
	}


	/**
	 * Guarda el valor de listaAfectaciones.
	 *
	 * @param listaAfectaciones Nuevo valor de listaAfectaciones.
	 */
	public void setListaAfectaciones(List<AfectacionTransient> listaAfectaciones) {
		this.listaAfectaciones = listaAfectaciones;
	}
    	
	
	
}
