package org.ibit.rol.sac.model.dto;

import java.util.HashMap;
import java.util.Map;

import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionEnlace;
import org.ibit.rol.sac.model.Traducible;

public class EnlaceDTO extends Traducible {

	private static final long serialVersionUID = 1L;
	
	private String id;  
    private long orden;
    private long idMainItem;
    private String idRelatedItem;
    
    private Map<String,TraduccionEnlace> traduccionesEnlace = new HashMap<String,TraduccionEnlace>();
        
    // XXX amartin: Necesario para que SPRING pueda mapearlo como parámetro de entrada @RequestBody.
    public EnlaceDTO() {};
    
    public EnlaceDTO (String id, Long orden, Map<String,Traduccion> traducciones, long idMainItem, String idRelatedItem) {
        this.id = id;
        this.orden = orden;
        this.setTraducciones(traducciones);
        this.idMainItem = idMainItem;
        this.idRelatedItem = idRelatedItem;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getIdRelatedItem() {
		return idRelatedItem;
	}

	public void setIdRelatedItem(String idRelatedItem) {
		this.idRelatedItem = idRelatedItem;
	}
	
	public void setTraduccionesEnlace(Map<String, TraduccionEnlace> traduccionesEnlace) {
		
		// XXX amartin: obtenemos los datos para el proxy y luego hacemos el cast.
		// XXX amartin: Importante, TraduccionEnlace implementa la interfaz Traduccion.
		this.traduccionesEnlace = traduccionesEnlace;
		// Cast de Map<String, Traduccion> a Map<String, TraduccionEnlace>.
		super.setTraducciones((Map)traduccionesEnlace);
		
	}
	
	public Map<String, TraduccionEnlace> getTraduccionesEnlace() {
		
		// XXX amartin: Importante, TraduccionEnlace implementa la interfaz Traduccion.
		// Cast de Map<String, Traduccion> a Map<String, TraduccionEnlace>.
		this.traduccionesEnlace = (Map)getTraducciones();
		return traduccionesEnlace;
		
	}
	
}
