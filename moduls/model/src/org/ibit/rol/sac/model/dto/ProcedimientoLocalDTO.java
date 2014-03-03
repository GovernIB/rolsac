package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

public class ProcedimientoLocalDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;

	private long id;			// PK de la relación N-M.
	private Long idProcedimiento;
	
	private long idRelatedItem; // Para guardado genérico vía AJAX: el valor de la PK del procedimiento.
	private long idMainItem;	// Para guardado genérico vía AJAX: el valor de la PK del registro con el que se encuentra relacionado el procedimiento.
	
	private String nombre;
	private String publicacio;
	private String caducitat;
	private Boolean caducat;
	private Integer orden;
	private String fechaActualizacion;
	private String familia;
	
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
	
	public ProcedimientoLocalDTO(long id, String nombre, String publicacio, String caducitat, Boolean caducat, Integer orden, long idRelatedItem, long idMainItem) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.publicacio = publicacio;
		this.caducitat = caducitat;
		this.caducat = caducat;
		this.orden = orden;
		this.idRelatedItem = idRelatedItem;
		this.idMainItem = idMainItem;
	}

	public ProcedimientoLocalDTO(long id, String nombre, Boolean caducat, String fechaActualizacion, String familia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.caducat = caducat;
		this.fechaActualizacion = fechaActualizacion;
		this.familia = familia;
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

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getFamilia() {
		return familia;
	}
	
	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public long getIdMainItem() {
		return idMainItem;
	}

	public void setIdMainItem(long idMainItem) {
		this.idMainItem = idMainItem;
	}

	public long getIdRelatedItem() {
		return idRelatedItem;
	}

	public void setIdRelatedItem(long idRelatedItem) {
		this.idRelatedItem = idRelatedItem;
	}	

}
