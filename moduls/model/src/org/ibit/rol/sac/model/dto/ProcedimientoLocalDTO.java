package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

public class ProcedimientoLocalDTO implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;

	private long id; // PK de la relación N-M.
	private Long idProcedimiento;

	private long idRelatedItem; // Para guardado genérico vía AJAX: el valor de la PK del procedimiento.
	private long idMainItem; // Para guardado genérico vía AJAX: el valor de la PK del registro con el que se
								// encuentra relacionado el procedimiento.

	private String nombre;
	private String publicacio;
	private String caducitat;
	private Boolean caducat;
	private Integer orden;
	private String fechaActualizacion;
	private String familia;
	private Boolean comun;
	private Boolean mensajes_gestor;
	private Boolean mensajes_supervisor;

	public ProcedimientoLocalDTO(final long id, final Long idProcedimiento, final String nombre,
			final String publicacio, final String caducitat, final Boolean caducat, final Integer orden) {
		super();
		this.id = id;
		this.idProcedimiento = idProcedimiento;
		this.nombre = nombre;
		this.publicacio = publicacio;
		this.caducitat = caducitat;
		this.caducat = caducat;
		this.orden = orden;
	}

	public ProcedimientoLocalDTO(final long id, final String nombre, final String publicacio, final String caducitat,
			final Boolean caducat, final Integer orden, final long idRelatedItem, final long idMainItem) {
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

	public ProcedimientoLocalDTO(final long id, final String nombre, final Boolean caducat,
			final String fechaActualizacion, final String familia, final Boolean comun, final Boolean mensajesGestor,
			final Boolean mensajesSupervisor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.caducat = caducat;
		this.fechaActualizacion = fechaActualizacion;
		this.familia = familia;
		this.comun = comun;
		this.mensajes_gestor = mensajesGestor;
		this.mensajes_supervisor = mensajesSupervisor;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public Long getIdProcedimiento() {
		return idProcedimiento;
	}

	public void setIdProcedimiento(final Long idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getPublicacio() {
		return publicacio;
	}

	public void setPublicacio(final String publicacio) {
		this.publicacio = publicacio;
	}

	public String getCaducitat() {
		return caducitat;
	}

	public void setCaducitat(final String caducitat) {
		this.caducitat = caducitat;
	}

	public void setCaducat(final Boolean caducat) {
		this.caducat = caducat;
	}

	public Boolean getCaducat() {
		return caducat;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(final Integer orden) {
		this.orden = orden;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(final String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(final String familia) {
		this.familia = familia;
	}

	public long getIdMainItem() {
		return idMainItem;
	}

	public void setIdMainItem(final long idMainItem) {
		this.idMainItem = idMainItem;
	}

	public long getIdRelatedItem() {
		return idRelatedItem;
	}

	public void setIdRelatedItem(final long idRelatedItem) {
		this.idRelatedItem = idRelatedItem;
	}

	/**
	 * @return the comun
	 */
	public Boolean getComun() {
		return comun;
	}

	/**
	 * @param comun
	 *            the comun to set
	 */
	public void setComun(final Boolean comun) {
		this.comun = comun;
	}

	/**
	 * @return the mensajes_gestor
	 */
	public Boolean getMensajes_gestor() {
		return mensajes_gestor;
	}

	/**
	 * @param mensajes_gestor
	 *            the mensajes_gestor to set
	 */
	public void setMensajes_gestor(final Boolean mensajes_gestor) {
		this.mensajes_gestor = mensajes_gestor;
	}

	/**
	 * @return the mensajes_supervisor
	 */
	public Boolean getMensajes_supervisor() {
		return mensajes_supervisor;
	}

	/**
	 * @param mensajes_supervisor
	 *            the mensajes_supervisor to set
	 */
	public void setMensajes_supervisor(final Boolean mensajes_supervisor) {
		this.mensajes_supervisor = mensajes_supervisor;
	}

}
