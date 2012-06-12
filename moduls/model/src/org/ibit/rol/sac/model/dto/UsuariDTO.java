package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

public class UsuariDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;

	private long id;
    private String nombre;
    private String username;
    private String perfil;
    private String email;
	

    public UsuariDTO(long id, String nombre, String username, String perfil, String email) {
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.perfil = perfil;
		this.email = email;
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPerfil() {
		return perfil;
	}


	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
