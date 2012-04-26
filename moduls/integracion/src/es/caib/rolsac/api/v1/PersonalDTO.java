/* Generated by Together */

package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.Personal;

public class PersonalDTO implements Serializable {  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6643359348435890190L;
	
	private Long id;
    private String username;
    private String nombre;
    private String funciones;
    private String cargo;
    private String email;
    private String extensionPublica;
    private String numeroLargoPublico;
    private String extensionPrivada;
    private String numeroLargoPrivado;
    private String extensionMovil;
    private String numeroLargoMovil;
	private Long unidadAdministrativa;
	
	public PersonalDTO(Personal persona) {
		this.id = persona.getId();
		this.username = persona.getUsername();
		this.nombre = persona.getNombre();
		this.funciones = persona.getFunciones();
		this.cargo = persona.getCargo();
		this.email = persona.getEmail();
		this.extensionPublica = persona.getExtensionPublica();
		this.numeroLargoPublico = persona.getNumeroLargoPublico();
		this.extensionPrivada = persona.getExtensionPrivada();
		this.numeroLargoPrivado = persona.getNumeroLargoPrivado();
		this.extensionMovil = persona.getExtensionMovil();
		this.numeroLargoMovil = persona.getNumeroLargoMovil();
		this.unidadAdministrativa = persona.getUnidadAdministrativa().getId();
	}
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getNombre() {
		return nombre;
	}
	public String getFunciones() {
		return funciones;
	}
	public String getCargo() {
		return cargo;
	}
	public String getEmail() {
		return email;
	}
	public String getExtensionPublica() {
		return extensionPublica;
	}
	public String getNumeroLargoPublico() {
		return numeroLargoPublico;
	}
	public String getExtensionPrivada() {
		return extensionPrivada;
	}
	public String getNumeroLargoPrivado() {
		return numeroLargoPrivado;
	}
	public String getExtensionMovil() {
		return extensionMovil;
	}
	public String getNumeroLargoMovil() {
		return numeroLargoMovil;
	}
	public Long getUnidadAdministrativa() {
		return unidadAdministrativa;
	}
	
}