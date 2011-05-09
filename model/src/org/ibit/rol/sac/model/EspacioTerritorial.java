package org.ibit.rol.sac.model;

import java.util.List;
import java.util.Set;

/**
 * bean que contiene la información de un Espacio Territorial, 0:AUTONOMICO 1:INSULAR 2:MUNICIPIO(nivel)
 * (PORMAD)
 */
public class EspacioTerritorial extends Traducible {
	
	private Long id;
	private Integer nivel;
	private Set<EspacioTerritorial> hijos;
	private Archivo mapa;
	private Archivo logo;
	private Set<UnidadAdministrativa> unidades;
	private String coordenadas;
	private EspacioTerritorial padre;
    private Set<AdministracionRemota> adminRemotas;

   
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}
	public Set<EspacioTerritorial> getHijos() {
		return hijos;
	}
	public void setHijos(Set<EspacioTerritorial> hijos) {
		this.hijos = hijos;
	}
	public  void addHijo(EspacioTerritorial hijo) {
		if (!hijos.contains(hijo)) {
            if (hijo.getPadre() != null) {
                hijo.getPadre().removeHijo(hijo);
            }
            hijo.setPadre(this);
            hijos.add(hijo);
        }
	}
	public void removeHijo(EspacioTerritorial hijo) {
        hijo.setPadre(null);
        hijos.remove(hijo);
	}
	public Archivo getLogo() {
		return logo;
	}
	public void setLogo(Archivo logo) {
		this.logo = logo;
	}
	public Archivo getMapa() {
		return mapa;
	}
	public void setMapa(Archivo mapa) {
		this.mapa = mapa;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public EspacioTerritorial getPadre() {
		return padre;
	}
	public void setPadre(EspacioTerritorial padre) {
		this.padre = padre;
	}
	public Set<UnidadAdministrativa> getUnidades() {
		return unidades;
	}
	public void setUnidades(Set<UnidadAdministrativa> unidades) {
		this.unidades = unidades;
	}

    public Set<AdministracionRemota> getAdminRemotas() {
        return adminRemotas;
    }

    public void setAdminRemotas(Set<AdministracionRemota> adminRemotas) {
        this.adminRemotas = adminRemotas;
    }
}
