package org.ibit.rol.sac.model;

import java.util.HashSet;
import java.util.Set;

public class PerfilGestor extends Traducible {

	private static final long serialVersionUID = 1L;
    private Long id;
    private int orden;
    private String codigoEstandar;
    private String duplica;
    private Set<Seccion> seccions = new HashSet();
    private Set<Usuario> usuaris = new HashSet(); 
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getCodigoEstandar() {
		return codigoEstandar;
	}
	
	public String getDuplica() {
		return duplica;
	}

	public void setDuplica(String duplica) {
		this.duplica = duplica;
	}

	public void setCodigoEstandar(String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
	}
	
    public Set<Seccion> getSeccions() {
		return seccions;
	}

	public void setSeccions(Set<Seccion> seccions) {
		this.seccions = seccions;
	}

	public void addSeccioPerfilsGestor(Seccion seccio) {
		this.seccions.add(seccio);
    }

    public void removeSeccioPerfilsGestor(Seccion seccio) {
    	this.seccions.remove(seccio);
    }	

	public Set getUsuaris() {
		return usuaris;
	}
	public void setUsuaris(Set<Usuario> usuaris) {
		this.usuaris = usuaris;
	}
    public void addUsuariPerfilsGestor(Usuario usuario) {
        this.usuaris.add(usuario);
    }
    public void removeUsuariPerfilsGestor(Usuario usuario) {
    	this.usuaris.remove(usuario);
    }
    
    public String getNombrePerfilGestor(String idioma) {
        TraduccionPerfilGestor tpg = (TraduccionPerfilGestor) getTraduccion(idioma);
        return tpg == null ? this.getCodigoEstandar() : tpg.getNombre();
    }  
}
