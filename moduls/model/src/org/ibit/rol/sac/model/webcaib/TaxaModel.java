package org.ibit.rol.sac.model.webcaib;

public class TaxaModel implements java.io.Serializable {
	   int codiTra; /** codi del tramit al que pertany la taxa */
	   
	   String codiTax;
	   String desc;
	   String formPag;
	   
	public int getCodiTra() {
		return codiTra;
	}
	public void setCodiTra(int codiTra) {
		this.codiTra = codiTra;
	}
	public String getCodiTax() {
		return codiTax;
	}
	public void setCodiTax(String codiTax) {
		this.codiTax = codiTax;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFormPag() {
		return formPag;
	}
	public void setFormPag(String formPag) {
		this.formPag = formPag;
	}
	@Override
	public String toString() {
		return "TaxaModel [ "+ 
				"codiTra=" + codiTra + 
				", codiTax=" + codiTax + 
				", desc=" + desc + 
				", formPag=" + formPag + "]";
	}
	   
	   
	   
}
