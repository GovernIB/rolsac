package org.ibit.rol.sac.model;

import java.util.Set;
import java.util.Iterator;

public class Usuario implements ValueObject {

	private static final long serialVersionUID = 1L;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Set getUnidadesAdministrativas() {
        return unidadesAdministrativas;
    }

    public void setUnidadesAdministrativas(Set unidadesAdministrativas) {
        this.unidadesAdministrativas = unidadesAdministrativas;
    }

    public boolean hasAccess(UnidadAdministrativa ua) {
        return unidadesAdministrativas.contains(ua) || (!ua.isRaiz() && hasAccess(ua.getPadre()));
    }

    public boolean hasRaizAccess() {
        for (Iterator iter = unidadesAdministrativas.iterator(); iter.hasNext();) {
            UnidadAdministrativa ua = (UnidadAdministrativa) iter.next();
            if (ua.isRaiz()) {
                return true;
            }
        }
        return false;
    }
    

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	private Long id;
    private String username;
    private String password;
    private String nombre;
    private String observaciones;
    private String perfil;
    private Set unidadesAdministrativas;
    private String email;

}
