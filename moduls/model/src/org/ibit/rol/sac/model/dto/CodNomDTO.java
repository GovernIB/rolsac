package org.ibit.rol.sac.model.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringEscapeUtils;

public class CodNomDTO implements Serializable {

	private static final long serialVersionUID = -1227937193155485933L;

	private String codigo;
	private String nom;
	
	public CodNomDTO() {
		super();
	}
	
	public CodNomDTO(String codigo, String nom) {
		super();
		this.codigo = codigo;
		this.nom = nom;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getJson() {
		return "{\"codigo\" : \"" + codigo + "\", \"nom\" : \"" + StringEscapeUtils.escapeHtml(nom) + "\" }";
	}
}
