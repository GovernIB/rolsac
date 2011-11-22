package org.ibit.rol.sac.model.dto;

import java.util.List;

import org.ibit.rol.sac.model.ValueObject;

public class AfectacionesDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;
	
	private List<AfectacionDTO> listaAfectaciones;
	
	
    public AfectacionesDTO() {
    	super();
    }


	/**
	 * Devuelve el valor de listaAfectaciones.
	 *
	 * @return Valor de listaAfectaciones.
	 */
	public List<AfectacionDTO> getListaAfectaciones() {
		return listaAfectaciones;
	}


	/**
	 * Guarda el valor de listaAfectaciones.
	 *
	 * @param listaAfectaciones Nuevo valor de listaAfectaciones.
	 */
	public void setListaAfectaciones(List<AfectacionDTO> listaAfectaciones) {
		this.listaAfectaciones = listaAfectaciones;
	}
    	
	
	
}
