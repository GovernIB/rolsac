package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.TraduccionIniciacion;

public class IniciacionTransferible implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigoEstandar;
	private String nombre;
	private String descripcion;

	private TraduccionIniciacionTransferible[] traducciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoEstandar() {
		return codigoEstandar;
	}

	public void setCodigoEstandar(String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
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

    public TraduccionIniciacionTransferible[] getTraducciones() {
        return traducciones;
    }

    public void setTraducciones( TraduccionIniciacionTransferible[] traducciones ) {
        this.traducciones = traducciones;
    }

	public void rellenar( Iniciacion iniciacion )
	{
		this.setId( iniciacion.getId());
        this.setCodigoEstandar( iniciacion.getCodigoEstandar() );

        //Relleno las traducciones
		final List<TraduccionIniciacionTransferible> traducciones = new ArrayList<TraduccionIniciacionTransferible>(); 
		for( final String idioma : ( Collection<String> )iniciacion.getLangs() )
		{
            final TraduccionIniciacion traduccion = ( TraduccionIniciacion )iniciacion.getTraduccion( idioma );
            if( traduccion != null )
            {
                final TraduccionIniciacionTransferible temp =  new TraduccionIniciacionTransferible();
                temp.setNombre( traduccion.getNombre() );
                temp.setDescripcion( traduccion.getDescripcion() );
                temp.setCodigoEstandarIdioma( idioma );
                traducciones.add( temp );
            }
        }
        
        this.setTraducciones( traducciones.toArray( new TraduccionIniciacionTransferible[0] ) );
    }

	public static IniciacionTransferible generar( Iniciacion iniciacion )
    {
		IniciacionTransferible iniT = new IniciacionTransferible();
    	if( iniciacion != null )
    	{
    		iniT.rellenar( iniciacion );
    	}
    	return iniT;
    }
}
