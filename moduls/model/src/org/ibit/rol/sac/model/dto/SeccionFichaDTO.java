package org.ibit.rol.sac.model.dto;

import java.io.Serializable;
import java.util.List;

public class SeccionFichaDTO extends IdNomDTO implements Serializable {
	
	private List<FichaDTO> listaFichas;
	
	public void setListaFichas( List<FichaDTO> listaFichas ) {
		this.listaFichas = listaFichas;
	}
	
	public List<FichaDTO> getListaFichas() {
		return listaFichas;
	}
}
