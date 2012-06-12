package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.TraduccionDocumento;

/**
 * Clase que representa la información a transferir de un DocumentTramit
 */
public class DocumentTramitTransferible implements Serializable {
	
	private Long id;
	
	private int tipus;
	
	private Long orden;
	
	private TraduccionDocumentoTransferible[] traducciones;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public int getTipus() {
		return tipus;
	}

	public void setTipus(int tipus) {
		this.tipus = tipus;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public TraduccionDocumentoTransferible[] getTraducciones() {
		return traducciones;
	}
	public void setTraducciones(TraduccionDocumentoTransferible[] traducciones) {
		this.traducciones = traducciones;
	}
	

	
    public void rellenar(final DocumentTramit documentTramit){

    	if(documentTramit!=null){
        	this.setId(null);
        	this.setOrden(documentTramit.getOrden());
        	this.setTipus(documentTramit.getTipus());
        	
    		 //Relleno las traducciones
    		final List<TraduccionDocumentoTransferible> traducciones = new ArrayList<TraduccionDocumentoTransferible>(); 
    		for (final String idioma : (Collection<String>)documentTramit.getLangs()){
                final TraduccionDocumento traduccion = (TraduccionDocumento)documentTramit.getTraduccion(idioma);
                if(traduccion!=null){
                    final TraduccionDocumentoTransferible temp =  new TraduccionDocumentoTransferible();
                    temp.setTitulo(traduccion.getTitulo());
                    temp.setDescripcion(traduccion.getDescripcion());
                    temp.setCodigoEstandarIdioma(idioma);
                    traducciones.add(temp);     
                    temp.setArchivoTransferible(ArchivoTransferible.generar(traduccion.getArchivo()));
                }
            }
    		this.setTraducciones(traducciones.toArray(new TraduccionDocumentoTransferible[0]));
 
    	}

    }
    
    public DocumentTramitTransferible generar(DocumentTramit documentTramit){
    	DocumentTramitTransferible documentTramitT = new DocumentTramitTransferible();
    	if(documentTramit!=null){
    		documentTramitT.rellenar(documentTramit);
    	}
    	return documentTramitT;
    }
      
}
