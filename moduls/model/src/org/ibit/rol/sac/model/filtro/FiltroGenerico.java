package org.ibit.rol.sac.model.filtro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 * filtro generico para aplanar los filtros del api rest
 * @author Indra
 *
 */

public class FiltroGenerico implements Serializable {

	private Map<String,String> filtros;
	private Map<String,String> columnasOrdenar;
	
	private String LANG_DEFECTO="ca";
	private int SIZE_DEFECTO=30;
	private int PAGE_DEFECTO=1;
	
	private final String ASCENDENTE = "ASC";
	private final String DESCENDENTE = "DESC";
	
	public final static String FILTRO_GENERICO_LANG="lang";
	public final static String FILTRO_GENERICO_SIZE="size";
	public final static String FILTRO_GENERICO_PAGE="page";
	
	
	public final static String FILTRO_UA_CODIGO_UA_PADRE = "codigoUAPadre";
	public final static String FILTRO_UA_VALIDACION = "validacion";
	public final static String FILTRO_UA_CODIGO_SECCION = "codigoSeccion";
	public final static String FILTRO_UA_CODIGO_NORMATIVA = "codigoNormativa";
	

	
	public FiltroGenerico() {
		this.filtros = new HashMap<String,String>();
		this.columnasOrdenar = new HashMap<String,String>();
	}
	
	public void addFiltro(String campo, String valor) {
		this.filtros.put(campo, valor);		
	}
	
	/**
	 * Retorna el valor del campo o null si no existe
	 * @param campo
	 * @return
	 */
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
		String page = this.filtros.get(FILTRO_GENERICO_PAGE);
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
	
	public void setPage(Integer page) {
		if(page!=null && page.intValue()>0) {
			this.filtros.put(FILTRO_GENERICO_PAGE, page+"");			
		}
	}
	
	
	
	public int getPageSize() {
		String size = this.filtros.get(FILTRO_GENERICO_SIZE);
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
	
	public void setPageSize(Integer size) {
		if(size!=null && size.intValue()>0) {
			this.filtros.put(FILTRO_GENERICO_SIZE, size+"");			
		}
	}
	
	
	
	public String getLang() {
		String lang = this.filtros.get(FILTRO_GENERICO_LANG);
		if(lang==null || lang.length()!=2) {
			lang = LANG_DEFECTO; // idioma por defecto
		}
		return lang.toLowerCase();
	}
	
	public void setLang(String lang) {
		if(lang!=null && lang.length()==2) {
			this.filtros.put(FILTRO_GENERICO_LANG, lang);			
		}
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

	/**
	 * Retorna la sentencia ORDER BY concatenando los diferentes prefijoEntidad+campo
	 * @param prefijoEntidad indica la entidad / alias de que dependen los campos
	 * @return
	 */
	public String getOrdenSQL(String prefijoEntidad) {
		StringBuilder res = new StringBuilder();
		boolean primero = true;
		for (Map.Entry<String, String> orden : columnasOrdenar.entrySet()){
			if (primero) {
				res.append(" ORDER BY ");
			}else {
				res.append(", ");
			}
			if(!StringUtils.isEmpty(prefijoEntidad)){
				res.append(prefijoEntidad);
				res.append(".");
			}
			res.append(orden.getKey());
			res.append(" ");		    
			res.append(orden.getValue());		    
		}	
		return res.toString();
	}
}
