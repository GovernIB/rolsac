package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

public class TraduccionTaxaTransferible extends AbstractTraduccion implements Serializable {


	private String descripcio;
	private String codificacio;
	private String formaPagament;
	
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
	


}
