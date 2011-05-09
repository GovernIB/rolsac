/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.Date;


public class TipoSuscripcion implements ValueObject {

	public static final Integer ACTIVO = new Integer(1); 
	public static final Integer INACTIVO = new Integer(0); 

    private Long id;
    private String nombre;
    private Long unidadAdministrativa;
    private String identificador;
    private String urlLogo;
    private String titulo;
    private String email;
    private String password;
    private Integer estado;
    private Date horaComienzoEnvio;



	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
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

	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Long getUnidadAdministrativa() {
		return unidadAdministrativa;
	}
	public void setUnidadAdministrativa(Long usuario) {
		this.unidadAdministrativa = usuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getUrlLogo() {
		return urlLogo;
	}
	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getHoraComienzoEnvio() {
		return horaComienzoEnvio;
	}
	public void setHoraComienzoEnvio(Date horaComienzoEnvio) {
		this.horaComienzoEnvio = horaComienzoEnvio;
	}

}
