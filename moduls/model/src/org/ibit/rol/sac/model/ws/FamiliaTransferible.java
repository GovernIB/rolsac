package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.TraduccionFamilia;

public class FamiliaTransferible implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String nombre;
    private String descripcion;

    private TraduccionFamiliaTransferible[] traducciones;

    public Long getId() {
		return id;
	}

    public void setId(Long id) {
		this.id = id;
	}

    public String getNombre() {
		return nombre;
	}

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public String getDescripcion() {
		return descripcion;
	}

    public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    public TraduccionFamiliaTransferible[] getTraducciones() {
        return traducciones;
    }

    public void setTraducciones( TraduccionFamiliaTransferible[] traducciones ) {
        this.traducciones = traducciones;
    }

	public void rellenar( Familia familia )
	{
        this.setId( familia.getId());
        
         //Relleno las traducciones
		final List<TraduccionFamiliaTransferible> traducciones = new ArrayList<TraduccionFamiliaTransferible>(); 
		for (final String idioma : (Collection<String>)familia.getLangs()){
            final TraduccionFamilia traduccion = ( TraduccionFamilia )familia.getTraduccion( idioma );
            if(traduccion!=null){
                final TraduccionFamiliaTransferible temp =  new TraduccionFamiliaTransferible();
                temp.setNombre( traduccion.getNombre() );
                temp.setDescripcion( traduccion.getDescripcion() );
                temp.setCodigoEstandarIdioma( idioma );
                traducciones.add( temp );
            }
        }
        
        this.setTraducciones(traducciones.toArray(new TraduccionFamiliaTransferible[0]));
    }

	public static FamiliaTransferible generar( Familia familia )
    {
    	FamiliaTransferible famT = new FamiliaTransferible();
    	if( familia != null )
    	{
    		famT.rellenar( familia );
    	}
    	return famT;
    }
}
