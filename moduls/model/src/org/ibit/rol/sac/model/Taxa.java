
/**
 * @author u92770
 */
package org.ibit.rol.sac.model;

import java.util.Map;

public class Taxa extends Ordenable {

	
	private Tramite tramit;

	public Tramite getTramit() {
		return tramit;
	}

	public void setTramit(Tramite tramit) {
		this.tramit = tramit;
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
