package org.ibit.rol.sac.model.filtro;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FiltroGenerico {

	private Map<String,String> filtros;
	private Map<String,String> columnasOrdenar;
	
	private String LANG_DEFECTO="ca";
	private int SIZE_DEFECTO=30;
	private int PAGE_DEFECTO=1;
	
	private final String ASCENDENTE = "ASC";
	private final String DESCENDENTE = "DESC";
	
	
	
	
	
	public FiltroGenerico() {
		this.filtros = new Map<String,String>();
	}
	
	public void addFiltro(String campo, String valor) {
		this.filtros.put(campo, valor);		
	}
	
	public String getValor(String campo) {
		return this.filtros.get(campo);		
	}
	
	public Set getCampos() {
		return this.filtros.keySet();
	}

	/**
	 * @return the filtros
	 */
	public Map<String,String> getFiltros() {
	return filtros;}

	/**
	 * @param filtros the filtros to set
	 */
	public void setFiltros(Map<String,String> filtros) {
	this.filtros = filtros;}
	
	
	public int getPage() {
		String page = this.filtros.get("page");
		int res = 1;//valor por defecto
		if(page!=null && !page.isEmpty()) {
			try {
				res = Integer.parseInt(page);
			}catch(Exception e) {
				res=1;
			}
		}		
		return res;
	}
	
	public int getPageSize() {
		String size = this.filtros.get("size");
		int res = 30;
		if(size!=null && !size.isEmpty()) {
			try {
				res = Integer.parseInt(size);
			}catch(Exception e) {
				res=30;
			}
		}		
		return res;
	}	
	
	public String getLang() {
		String lang = this.filtros.get("lang");
		if(lang==null || lang.length()!=2) {
			lang = LANG_DEFECTO; // idioma por defecto
		}
		return lang.toLowerCase();
	}

	/**
	 * @return the columnasOrdenar
	 */
	public Map<String,String> getColumnasOrdenar() {
	return columnasOrdenar;}

	/**
	 * @param columnasOrdenar the columnasOrdenar to set
	 */
	public void setColumnasOrdenar(Map<String,String> columnasOrdenar) {
	this.columnasOrdenar = columnasOrdenar;}
	
	/**
	 *  Añade la ordenación por el campo en el orden indicado. Sera orden ascendente a no ser que se indique this.DESCENDENTE. 
	 * @param campo
	 * @param orden
	 */
	public void addOrden(String campo, String orden) {
		this.columnasOrdenar.put(campo, orden.equals(this.DESCENDENTE)?this.DESCENDENTE:this.ASCENDENTE);		
	}

	public String getOrdenSQL() {
		String res = "";
		boolean primero = true;
		for (Map.Entry<String, String> orden : columnasOrdenar.entrySet())
		{
			if (primero) {
				res += " ORDER BY ";
			}else {
				res += ", ";
			}
			
			res += orden.getKey() + " " + orden.getValue();		    
		}		
	}
	
	
	
}
