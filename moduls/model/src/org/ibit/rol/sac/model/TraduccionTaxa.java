
/**
 * @author u92770
 */
package org.ibit.rol.sac.model;

import java.util.Iterator;


public class TraduccionTaxa implements Traduccion {
	
	String descripcio;
	String codificacio;
	String formaPagament;
	
	public IndexObject indexObject() {
		//TODO u93770 
		return null;
    }

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getCodificacio() {
		return codificacio;
	}

	public void setCodificacio(String codificacio) {
		this.codificacio = codificacio;
	}

	public String getFormaPagament() {
		return formaPagament;
	}

	public void setFormaPagament(String formaPagament) {
		this.formaPagament = formaPagament;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof TraduccionTaxa)) return false;
		TraduccionTaxa tt=(TraduccionTaxa)obj;
		return descripcio.equals(tt.getDescripcio()) &&
			   codificacio.equals(tt.getCodificacio()) &&
			   formaPagament.equals(tt.getFormaPagament());
	}
	
	@Override
	public String toString() {
		return "TraducccionTaxa [codificacio=" + codificacio + ", descripcio="
				+ descripcio + ", formaPagament=" + formaPagament + "]";
	}


	
	
}
