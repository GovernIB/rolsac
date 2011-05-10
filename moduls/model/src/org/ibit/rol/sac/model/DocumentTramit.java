
package org.ibit.rol.sac.model;

import java.util.Iterator;



public class DocumentTramit extends Document  {

	
	private Tramite tramit;

	

	public Tramite getTramit() {
		return tramit;
	}



	public void setTramit(Tramite tramit) {
		this.tramit = tramit;
	}




	@Override
	public String toString() {
		Long tid=null==tramit?null:tramit.getId();
		return "DocumentTramit ["+super.toString()+" tramite="+tid+" ]";
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DocumentTramit)) return false;
		DocumentTramit dt2=(DocumentTramit)obj;
		return getId() == dt2.getId();
	}
}
