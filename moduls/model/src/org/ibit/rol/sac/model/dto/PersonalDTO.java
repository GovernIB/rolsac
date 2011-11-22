package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

public class PersonalDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;
	private long   id;
	private String nombre;
	private String username;
	private String uniAdmin;
	private String email;
	private	String extensionPublica;
	
	public PersonalDTO(long id, String nombre, String username, String uniAdmin, String email, String extensionPublica){
	    super();
	    this.id = id;
	    this.nombre = nombre;
	    this.username = username;
	    this.uniAdmin = uniAdmin;
	    this.email = email;
	    this.extensionPublica = extensionPublica;
	}
	
	public String getNombre(){
		return this.nombre;			
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getUsername(){
	    return this.username;            
	}
	   
	public void setUsername(String username){
	    this.username = username;
	}
	
	public long getId(){
		return this.id;			
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getUniAdmin(){
		return this.uniAdmin;			
	}
	
	public void setUniAdmin(String uniAdmin){
		this.uniAdmin = uniAdmin;
	}
	
	public String getEmail(){
		return this.email;			
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getExtensionPublica(){
		return this.extensionPublica;			
	}
	
	public void setExtensionPublica(String extensionPublica){
		this.extensionPublica = extensionPublica;
	}
	
	
	
	
}
