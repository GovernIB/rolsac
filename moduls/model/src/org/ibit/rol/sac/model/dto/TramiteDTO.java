package org.ibit.rol.sac.model.dto;

import java.io.Serializable;
import java.util.Date;

public class TramiteDTO implements  Serializable {

	private static final long serialVersionUID = -4816218682269329121L;

	private long id;
	private long idProcedimiento;
	
	private Date fechaActualizacion;
	private Date fechaCaducidad;
	private Date fechaPublicacion;
	
	private String nombre;
	//#351 se cambia descripcion por observaciones
	//private String descripcion;
	private String requisitos;
	private String documentacion;
	private String plazos;
	private String lugar;	
	
	public long getId() {
		return id;
	}
	public void setId(long idTramite) {
		this.id = idTramite;
	}
	public long getIdProcedimiento() {
		return idProcedimiento;
	}
	public void setIdProcedimiento(long idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//public String getDescripcion() {
	//	return descripcion;
	//}
	//public void setDescripcion(String descripcion) {
	//	this.descripcion = descripcion;
	//}
	public String getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	public String getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}
	public String getPlazos() {
		return plazos;
	}
	public void setPlazos(String plazos) {
		this.plazos = plazos;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}	
	
}
