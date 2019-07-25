package org.ibit.rol.sac.model.dto;

import org.ibit.rol.sac.model.ValueObject;

/**
 * Servicio dto.
 *
 * @author slromero
 *
 */
public class ServicioDTO implements ValueObject {
	/** Serial Version UID. **/
	private static final long serialVersionUID = 3258125847574821172L;
	/** Id. **/
	private long id; // PK de la relación N-M.
	/** Id servicio. **/
	private Long idServicio;
	/** Id Related Item. **/
	private long idRelatedItem; // Para guardado genérico vía AJAX: el valor de la PK del procedimiento.
	/** Id Main Item. **/
	private long idMainItem; // Para guardado genérico vía AJAX: el valor de la PK del registro con el que se
								// encuentra relacionado el procedimiento.
	/** Nombre. **/
	private String nombre;
	/** Publicacio **/
	private String publicacio;
	/** Caducitat. **/
	private String caducitat;
	/** Caducat. **/
	private Boolean caducat;
	/** Orden. **/
	private Integer orden;
	/** Fecha Actualización. **/
	private String fechaActualizacion;
	/** Comun. **/
	private Boolean comun;

	/**
	 * Servicio DTO.
	 *
	 * @param id
	 * @param idProcedimiento
	 * @param nombre
	 * @param publicacio
	 * @param caducitat
	 * @param caducat
	 * @param orden
	 */
	public ServicioDTO(final long id, final Long idServicio, final String nombre, final String publicacio,
			final String caducitat, final Boolean caducat, final Integer orden) {
		super();
		this.id = id;
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.publicacio = publicacio;
		this.caducitat = caducitat;
		this.caducat = caducat;
		this.orden = orden;
	}

	/**
	 * Servicio DTO.
	 *
	 * @param id
	 * @param nombre
	 * @param publicacio
	 * @param caducitat
	 * @param caducat
	 * @param orden
	 * @param idRelatedItem
	 * @param idMainItem
	 */
	public ServicioDTO(final long id, final String nombre, final String publicacio, final String caducitat,
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

	/**
	 * Servicio DTO.
	 *
	 * @param id
	 * @param nombre
	 * @param caducat
	 * @param fechaActualizacion
	 * @param familia
	 */
	public ServicioDTO(final long id, final String nombre, final Boolean caducat, final String fechaActualizacion,
			final Boolean comun) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.caducat = caducat;
		this.fechaActualizacion = fechaActualizacion;
		this.comun = comun;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio
	 *            the idServicio to set
	 */
	public void setIdServicio(final Long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the idRelatedItem
	 */
	public long getIdRelatedItem() {
		return idRelatedItem;
	}

	/**
	 * @param idRelatedItem
	 *            the idRelatedItem to set
	 */
	public void setIdRelatedItem(final long idRelatedItem) {
		this.idRelatedItem = idRelatedItem;
	}

	/**
	 * @return the idMainItem
	 */
	public long getIdMainItem() {
		return idMainItem;
	}

	/**
	 * @param idMainItem
	 *            the idMainItem to set
	 */
	public void setIdMainItem(final long idMainItem) {
		this.idMainItem = idMainItem;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the publicacio
	 */
	public String getPublicacio() {
		return publicacio;
	}

	/**
	 * @param publicacio
	 *            the publicacio to set
	 */
	public void setPublicacio(final String publicacio) {
		this.publicacio = publicacio;
	}

	/**
	 * @return the caducitat
	 */
	public String getCaducitat() {
		return caducitat;
	}

	/**
	 * @param caducitat
	 *            the caducitat to set
	 */
	public void setCaducitat(final String caducitat) {
		this.caducitat = caducitat;
	}

	/**
	 * @return the caducat
	 */
	public Boolean getCaducat() {
		return caducat;
	}

	/**
	 * @param caducat
	 *            the caducat to set
	 */
	public void setCaducat(final Boolean caducat) {
		this.caducat = caducat;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(final Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the fechaActualizacion
	 */
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion
	 *            the fechaActualizacion to set
	 */
	public void setFechaActualizacion(final String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
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
}
