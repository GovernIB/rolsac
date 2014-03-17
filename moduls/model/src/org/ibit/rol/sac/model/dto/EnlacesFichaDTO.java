package org.ibit.rol.sac.model.dto;

import java.util.List;

import org.ibit.rol.sac.model.ValueObject;

public class EnlacesFichaDTO implements ValueObject {

	private static final long serialVersionUID = 1L;
	
	// XXX amartin: constructor vacío, necesario para que SPRING pueda mapearlo como parámetro de entrada @RequestBody.
	public EnlacesFichaDTO() {};
	
	private Long id; // id de la ficha  
    private List<EnlaceDTO> listaEnlaces;

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
	 * @return the listaEnlaces
	 */
	public List<EnlaceDTO> getListaEnlaces() {
		return listaEnlaces;
	}
	/**
	 * @param listaEnlaces the listaEnlaces to set
	 */
	public void setListaEnlaces(List<EnlaceDTO> listaEnlaces) {
		this.listaEnlaces = listaEnlaces;
	}
    	
}
