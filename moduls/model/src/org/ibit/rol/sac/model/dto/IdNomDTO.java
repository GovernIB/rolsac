package org.ibit.rol.sac.model.dto;

import java.io.Serializable;

public class IdNomDTO implements Serializable {

	private static final long serialVersionUID = -1227937193155485933L;

	private Long id;
	private String nom;
	
	public IdNomDTO() {
		super();
	}
	
	public IdNomDTO(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getJson() {
		return "{\"id\" : \"" + id + "\", \"nom\" : \"" + nom + "\" }";
	}
	
}
