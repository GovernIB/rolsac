package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TraduccionTipo;

public class TipoTransferible implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String nombre;

    private TraduccionTipoTransferible[] traducciones;

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

	public TraduccionTipoTransferible[] getTraducciones() {
		return traducciones;
	}

	public void setTraducciones(TraduccionTipoTransferible[] traducciones) {
		this.traducciones = traducciones;
	}

	public void rellenar( final Tipo tipo )
	{
		
    	if( tipo != null )
    	{
        	this.setId( tipo.getId() );
        	
    		 //Relleno las traducciones
    		final List<TraduccionTipoTransferible> traducciones = new ArrayList<TraduccionTipoTransferible>(); 
    		for ( final String idioma : ( Collection<String> )tipo.getLangs() )
    		{
                final TraduccionTipo traduccion = ( TraduccionTipo )tipo.getTraduccion( idioma );
                if( traduccion != null)
                {
                    final TraduccionTipoTransferible temp =  new TraduccionTipoTransferible();
                    temp.setNombre( traduccion.getNombre() );
                    temp.setCodigoEstandarIdioma(idioma);
                    traducciones.add(temp);
                }
            }
    		this.setTraducciones(traducciones.toArray(new TraduccionTipoTransferible[0]));
    	}
    }
    
    public static TipoTransferible generar( Tipo tipo )
    {
    	TipoTransferible tipoT = new TipoTransferible();
    	if( tipo != null )
    	{
    		tipoT.rellenar( tipo );
    	}
    	return tipoT;
    }
}
