package es.caib.rolsac.utils;

import java.io.Serializable;
import java.util.List;

/**
 *   Clase utilizada para recoger los datos de una búsqueda (registros actuales paginados y total), 
 * creada para que los EJB devuelvan esta información en una única llamada/transacción. 
 *  (e.g. "Fitxes Informativas", "Normativas", "Procedimientos").    
 */
public class ResultadoBusqueda implements Serializable {
	
	private static final long serialVersionUID = 2827868938639781901L;
	
	private  List<?> listaResultados;
	private int totalResultados;
	
	public void setListaResultados( List<?> listaResultados) {
		this.listaResultados = listaResultados;
	}  

	public void setTotalResultados( int totalResultados ) {
		this.totalResultados = totalResultados;
	}

	public List<?> getListaResultados() {
		return listaResultados;
	}
	
	public int getTotalResultados() {
		return totalResultados;						
	}
	
}

