package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.TraduccionEdificio;


public class EdificioTransferible extends ActuacionTransferible implements Serializable {
	


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public TraduccionEdificioTransferible[] getTraducciones() {
		return traducciones;
	}
	public void setTraducciones(TraduccionEdificioTransferible[] traducciones) {
		this.traducciones = traducciones;
	}


	private Long id;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String telefono;
    private String fax;
    private String email;
    private String latitud;
    private String longitud;
    private TraduccionEdificioTransferible[] traducciones;
    
    
    public static EdificioTransferible generar(final Edificio edificio){
    	EdificioTransferible edificioTransferible = null;

    	if(edificio!=null){
    		edificioTransferible = new EdificioTransferible();
    		edificioTransferible.setId(edificio.getId());
    		edificioTransferible.setCodigoPostal(edificio.getCodigoPostal());
    		edificioTransferible.setDireccion(edificio.getDireccion());
    		edificioTransferible.setEmail(edificio.getEmail());
    		edificioTransferible.setFax(edificio.getFax());
    		edificioTransferible.setPoblacion(edificio.getPoblacion());
    		edificioTransferible.setTelefono(edificio.getTelefono());
    		edificioTransferible.setLatitud(edificio.getLatitud());
    		edificioTransferible.setLongitud(edificio.getLongitud());
    		
    		 //Relleno las traducciones
    		final List<TraduccionEdificioTransferible> traducciones = new ArrayList<TraduccionEdificioTransferible>(); 
    		for (final String idioma : (Collection<String>)edificio.getLangs()){
                final TraduccionEdificio traduccion = (TraduccionEdificio)edificio.getTraduccion(idioma);
                if(traduccion!=null){
                    final TraduccionEdificioTransferible temp =  new TraduccionEdificioTransferible();

                    temp.setDescripcion(traduccion.getDescripcion());
                    temp.setCodigoEstandarIdioma(idioma);
                    traducciones.add(temp);
                }
            }
    		edificioTransferible.setTraducciones(traducciones.toArray(new TraduccionEdificioTransferible[0]));
    	}
    	return edificioTransferible;
    }
}
