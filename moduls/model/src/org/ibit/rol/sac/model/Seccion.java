/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.List;
import java.util.Set;

public class Seccion extends Traducible implements Comparable {

	private static final long serialVersionUID = 6322366470145398936L;

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoEstandard() {
        return codigoEstandard;
    }

    public void setCodigoEstandard(String codigoEstandard) {
        this.codigoEstandard = codigoEstandard;
    }

    public Seccion getPadre() {
        return padre;
    }

    public void setPadre(Seccion padre) {
        this.padre = padre;
    }

    public List<Seccion> getHijos() {
        return hijos;
    }

    public void setHijos(List<Seccion> hijos) {
        this.hijos = hijos;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Set<FichaUA> getFichasUA() {
        return fichasUA;
    }

    public void setFichasUA(Set<FichaUA> fichasUA) {
        this.fichasUA = fichasUA;
    }

    public void addHijo(Seccion hijo) {
        if (!hijos.contains(hijo)) {
            if (hijo.getPadre() != null) {
                hijo.getPadre().removeHijo(hijo);
            }
            hijo.setPadre(this);
            hijo.setOrden(hijos.size());
            hijos.add(hijo);
        }
    }

    public void removeHijo(Seccion hijo) {
    	hijo.setPadre(null);
    	hijos.remove(hijo);
    	for (int i = 0; i < hijos.size(); i++) {
    		Seccion seccion = (Seccion) hijos.get(i);
    		seccion.setOrden(i);
    	}
    }

    public void addFichaUA(FichaUA ficha) {
        ficha.setSeccion(this);
        fichasUA.add(ficha);
    }
    
    public void addFichaUA2(FichaUA ficha) {
        ficha.setSeccion(this);
    }
    
    public void removeFichaUA(FichaUA ficha) {
        fichasUA.remove(ficha);
        ficha.setSeccion(null);
    }
    
    public void removeFichaUA2(FichaUA ficha) {
        ficha.setSeccion(null);
    }
    
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	private Long id;
    private String codigoEstandard;
    private Seccion padre;
    private List<Seccion> hijos;
    private String perfil;
    private int orden;
    private Set<FichaUA> fichasUA;
    private String nombre;

    public Seccion() {
		super();
	}
    
    public Seccion(Long id, String nombre) {
    	
    	this.id = id;
    	this.nombre = nombre;
    	
	}


    public int compareTo(Object o) {
        Seccion other = (Seccion) o;
        return this.getOrden() - other.getOrden();
    }
    
    public String toString(){
		StringBuffer salida = new StringBuffer("---Secci�n---\n");
		salida.append("  -id: ");
		salida.append(id);
		salida.append("\n -nombre");
		if (getTraduccion() != null)
			salida.append(((TraduccionSeccion)getTraduccion()).getNombre());
		salida.append("\n  -descripci�n: ");
		if(getTraduccion() != null)
			salida.append(((TraduccionSeccion)getTraduccion()).getDescripcion());
		salida.append("\n");
		return salida.toString();
	}
  
}
