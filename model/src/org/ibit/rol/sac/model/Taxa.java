
/**
 * @author u92770
 */
package org.ibit.rol.sac.model;

import java.util.Iterator;
import java.util.Map;


public class Taxa extends ElementOrdenat {

	
	private Tramite tramit;

	public Tramite getTramit() {
		return tramit;
	}

	public void setTramit(Tramite tramit) {
		this.tramit = tramit;
	}

	
	public IndexObject indexObject() {
		//TODO u93770 
		return null;
    }


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		Map<String,Traduccion> tds = getTraducciones();
		Long tid=null==tramit?null:tramit.getId();
		return "Taxa ["+ "id="+getId()+ tds+" tramit="+tid + "]";
	}
	
	
}
