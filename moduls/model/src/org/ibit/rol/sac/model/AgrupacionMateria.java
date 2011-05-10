package org.ibit.rol.sac.model;

import java.util.List;

public class AgrupacionMateria extends Traducible {

	private Long id;
	private String codigoEstandar;
	private List<MateriaAgrupacionM> materiasAgrupacionM;
	private Seccion seccion;
	
	public String getCodigoEstandar() {
		return codigoEstandar;
	}
	
	public void setCodigoEstandar(String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<MateriaAgrupacionM> getMateriasAgrupacionM() {
		return materiasAgrupacionM;
	}
	
	public void setMateriasAgrupacionM(List<MateriaAgrupacionM> materiasAgrupacionM) {
		this.materiasAgrupacionM = materiasAgrupacionM;
	}
	
    public void addMateriaAgrupacionM(MateriaAgrupacionM mam){
    	mam.setAgrupacion(this);
    	mam.setOrden(materiasAgrupacionM.size());
    	materiasAgrupacionM.add(mam);
    }

    public void removeMateriaAgrupacionM(MateriaAgrupacionM mamova){
        int ind = materiasAgrupacionM.indexOf(mamova);
        materiasAgrupacionM.remove(ind);
        for (int i = ind; i < materiasAgrupacionM.size(); i++) {
        	MateriaAgrupacionM mam = (MateriaAgrupacionM) materiasAgrupacionM.get(i);
        	mam.setOrden(i);
        }
    }	
	
	public Seccion getSeccion() {
		return seccion;
	}
	
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	

	
	
}
