package org.ibit.rol.sac.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 28-may-2007
 * Time: 14:52:21
 * Bean que representa los datos de una agrupación de hecho vital( es una clasificación de los hechos vitales)
 * (PORMAD)
 */
public class AgrupacionHechoVital extends Traducible {

    private Long id;
    private String codigoEstandar;
    private List<HechoVitalAgrupacionHV> hechosVitalesAgrupacionHV;
    private Archivo foto;
    private Archivo iconoGrande;
    private Archivo icono;
    private PublicoObjetivo publico;
    
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

    public List<HechoVitalAgrupacionHV> getHechosVitalesAgrupacionHV() {
        return hechosVitalesAgrupacionHV;
    }

    public void setHechosVitalesAgrupacionHV(List<HechoVitalAgrupacionHV> hechosVitales) {
        this.hechosVitalesAgrupacionHV = hechosVitales;
    }
    
    public void addHechoVitalAgrupacionHV(HechoVitalAgrupacionHV hva){
    	hva.setAgrupacion(this);
    	hva.setOrden(hechosVitalesAgrupacionHV.size());
        hechosVitalesAgrupacionHV.add(hva);
    }

    public void removeHechoVitalAgrupacionHV(HechoVitalAgrupacionHV hechova){
        int ind = hechosVitalesAgrupacionHV.indexOf(hechova);
        hechosVitalesAgrupacionHV.remove(ind);
        for (int i = ind; i < hechosVitalesAgrupacionHV.size(); i++) {
        	HechoVitalAgrupacionHV hva = (HechoVitalAgrupacionHV) hechosVitalesAgrupacionHV.get(i);
            hva.setOrden(i);
        }
    }

    public Archivo getFoto() {
        return foto;
    }

    public void setFoto(Archivo foto) {
        this.foto = foto;
    }
    
	public PublicoObjetivo getPublico() {
		return publico;
	}
	
	public void setPublico(PublicoObjetivo publico) {
		this.publico = publico;
	}
	public Archivo getIcono() {
		return icono;
	}
	public void setIcono(Archivo icono) {
		this.icono = icono;
	}
	public Archivo getIconoGrande() {
		return iconoGrande;
	}
	public void setIconoGrande(Archivo iconoGrande) {
		this.iconoGrande = iconoGrande;
	}
}
