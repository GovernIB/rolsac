package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

public class EdificioDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;
    private Long id;
    private String direccion;
    private String codigoPostal;
    private String poblacion;    
    
    public EdificioDTO(long id, String direccion, String codigoPostal, String poblacion){
        super();
        this.id = id;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;     
    }
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
	
}
