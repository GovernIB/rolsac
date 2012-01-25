package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

public class ProcedimientoLocalDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;

	private long id;
	private Long idProcedimiento;
	private String nombre;
	private String publicacio;
	private String caducitat;
	private Boolean caducat;
	private Integer orden;
	
	public ProcedimientoLocalDTO(long id, Long idProcedimiento, String nombre, String publicacio, String caducitat, Boolean caducat, Integer orden) {
		super();
		this.id = id;
		this.idProcedimiento = idProcedimiento;
		this.nombre = nombre;
		this.publicacio = publicacio;
		this.caducitat = caducitat;
		this.caducat = caducat;
		this.orden = orden;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Long getIdProcedimiento() {
		return idProcedimiento;
	}

	public void setIdProcedimiento(Long idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPublicacio() {
		return publicacio;
	}
	public void setPublicacio(String publicacio) {
		this.publicacio = publicacio;
	}
	public String getCaducitat() {
		return caducitat;
	}
	public void setCaducitat(String caducitat) {
		this.caducitat = caducitat;
	}

	public void setCaducat(Boolean caducat) {
		this.caducat = caducat;
	}

	public Boolean getCaducat() {
		return caducat;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}
