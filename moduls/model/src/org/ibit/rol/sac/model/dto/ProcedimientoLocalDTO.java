package org.ibit.rol.sac.model.dto;

import java.util.Date;

import org.ibit.rol.sac.model.ValueObject;

public class ProcedimientoLocalDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;

	private long id;
	private String nombre;
	private String publicacio;
	private String caducitat;
	private Boolean caducat;
	
	public ProcedimientoLocalDTO(long id, String nombre, String publicacio, String caducitat, Boolean caducat) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.publicacio = publicacio;
		this.caducitat = caducitat;
		this.caducat = caducat;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPublicacio() {
		return publicacio;
	}
	public void setPublicacio(String publicacio) {
		this.publicacio = publicacio;
	}
	public String getCaducitat() {
		return caducitat;
	}
	public void setCaducitat(String caducitat) {
		this.caducitat = caducitat;
	}

	public void setCaducat(Boolean caducat) {
		this.caducat = caducat;
	}

	public Boolean getCaducat() {
		return caducat;
	}
	
}