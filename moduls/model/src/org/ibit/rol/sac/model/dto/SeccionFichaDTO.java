package org.ibit.rol.sac.model.dto;

import java.io.Serializable;
import java.util.List;

public class SeccionFichaDTO extends IdNomDTO implements Serializable {

	private List<FichaDTO> listaFichas;

	// Cuidado: esta variable no equivale al size() de listaFichas.
	// Se usa cuando no queremos inicializar la lista de fichas por eficiencia y, aún así,
	// conocer el número de fichas relacionadas con una UA y una sección.
	private Long numFichas;
	

	public Long getNumFichas() {
		return numFichas;
	}
	

	public void setNumFichas(Long numFichas) {
		this.numFichas = numFichas;
	}
	

	public void setListaFichas( List<FichaDTO> listaFichas ) {
		this.listaFichas = listaFichas;
	}
	

	public List<FichaDTO> getListaFichas() {
		return listaFichas;
	}
	
}
