package org.ibit.rol.sac.model.dto;

import java.util.Map;

import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.Traducible;


public class EnlaceDTO extends Traducible {

    private Long id;  
    private long orden;

    
    public EnlaceDTO (Long id, Long orden, Map<String,Traduccion> traducciones){
        this.id = id;
        this.orden = orden;
        this.setTraducciones(traducciones);
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getOrden() {
		return orden;
	}

	public void setOrden(long orden) {
		this.orden = orden;
	}
}
