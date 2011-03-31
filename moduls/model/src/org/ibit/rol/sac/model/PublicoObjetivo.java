package org.ibit.rol.sac.model;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 28-may-2007
 * Time: 14:52:21
 * Bean que representa a un público objetivo (PORMAD)
 */
public class PublicoObjetivo extends Traducible {

    private Long id;
    private String codigoEstandar;
    private Set<AgrupacionHechoVital> agrupaciones;
    private Integer orden;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoEstandar() {
        return codigoEstandar;
    }

    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }

	public Set<AgrupacionHechoVital> getAgrupaciones() {
		return agrupaciones;
	}

	public void setAgrupaciones(Set<AgrupacionHechoVital> agrupaciones) {
		this.agrupaciones = agrupaciones;
	}

    public void removeAgrupacion(AgrupacionHechoVital agrupacionHV){
        agrupaciones.remove(agrupacionHV);
    }
    public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}
