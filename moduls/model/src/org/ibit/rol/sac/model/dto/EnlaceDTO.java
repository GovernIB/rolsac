package org.ibit.rol.sac.model.dto;

import java.util.Map;

import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.Traducible;


public class EnlaceDTO extends Traducible {

	private static final long serialVersionUID = 1L;
	
	private Long id;  
    private long orden;
    private long idMainItem;
    private long idRelatedItem;
    
    public EnlaceDTO (Long id, Long orden, Map<String,Traduccion> traducciones, long idMainItem, long idRelatedItem){
        this.id = id;
        this.orden = orden;
        this.setTraducciones(traducciones);
        this.idMainItem = idMainItem;
        this.idRelatedItem = idRelatedItem;
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

	public long getIdMainItem() {
		return idMainItem;
	}

	public void setIdMainItem(long idMainItem) {
		this.idMainItem = idMainItem;
	}

	public long getIdRelatedItem() {
		return idRelatedItem;
	}

	public void setIdRelatedItem(long idRelatedItem) {
		this.idRelatedItem = idRelatedItem;
	}
	
}
