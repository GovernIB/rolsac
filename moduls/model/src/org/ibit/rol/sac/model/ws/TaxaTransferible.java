package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionTaxa;

/**
 * Clase que representa la información a transferir de un Taxa
 */
public class TaxaTransferible implements Serializable {
	
	private Long id;
	
	private Long orden;
	
	private TraduccionTaxaTransferible[] traducciones;

	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public TraduccionTaxaTransferible[] getTraducciones() {
		return traducciones;
	}
	public void setTraducciones(TraduccionTaxaTransferible[] traducciones) {
		this.traducciones = traducciones;
	}
	
    public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public void rellenar(final Taxa taxa){
		
    	if(taxa!=null){
    		
        	this.setId(null);
        	this.setOrden(taxa.getOrden());
        	
    		 //Relleno las traducciones
    		final List<TraduccionTaxaTransferible> traducciones = new ArrayList<TraduccionTaxaTransferible>(); 
    		for (final String idioma : (Collection<String>)taxa.getLangs()){
                final TraduccionTaxa traduccion = (TraduccionTaxa)taxa.getTraduccion(idioma);
                if(traduccion!=null){
                    final TraduccionTaxaTransferible temp =  new TraduccionTaxaTransferible();
                    temp.setCodificacio(traduccion.getCodificacio());
                    temp.setDescripcio(traduccion.getDescripcio());
                    temp.setFormaPagament(traduccion.getFormaPagament());
                    temp.setCodigoEstandarIdioma(idioma);
                    traducciones.add(temp);
                }
            }
    		this.setTraducciones(traducciones.toArray(new TraduccionTaxaTransferible[0]));
    	}

    }
    
    public TaxaTransferible generar(Taxa tax){
    	TaxaTransferible taxaT = new TaxaTransferible();
    	if(tax!=null){
    		taxaT.rellenar(tax);
    	}
    	return taxaT;
    }
      
}
