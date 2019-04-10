package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Representación de un Sia Job .
 * Indexaciones a traves de solr
 */
public class SiaJob implements ValueObject {
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
			
	/** Id. **/
    private Long id;
    
    /** Fecha de inicio. **/
    private Date fechaIni;
    
    /** Fecha de fin. **/
	private Date fechaFin;
	
	/** Descripción breve del envío de datos. 
	 * Incluirá información básica a mostrar en la interfaz como cuantos envíos se han realizado 
	 * y cuántos de ellos han sido correctos o incorrectos.. **/
//	private Clob descBreve; 
	private String descBreve; 
	
	/** Descripción completa de todos los envíos realizados. La información a incluir será:
                  Fecha  -Desc_SIA – Resultado
	Siendo la fecha el momento del envío, en desc_SIA se mostrará información sobre la fila 
	mientras en resultado se almacenará la respuesta obtenida. **/
	//private Clob descripcion;
	private String descripcion;
	
	
	/** Estado del envio de datos SIA **/
	private Integer estado;
	
	/** Tipo de ejecucion: TOT (tots); PDT (pendientes); TMP (cambio estado por tiempo) */ 
	private String tipo;
	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(final Long id) {
		this.id = id;
	}
	
	
	/**
	 * @return the fechaIni
	 */
	public final Date getFechaIni() {
		return fechaIni;
	}
	/**
	 * @param fechaIni the fechaIni to set
	 */
	public final void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	/**
	 * @return the fechaFin
	 */
	public final Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public final void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * @return the descBreve
	 */
	public String getDescBreve() {
		return descBreve;
	}
	/**
	 * @param descBreve the descBreve to set
	 */
	public void setDescBreve(String descBreve) {		
			this.descBreve = descBreve;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
	}
	/**
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		StringBuffer texto = new StringBuffer();
		texto.append("SiaJob id:");
		texto.append(id);
		texto.append(" fechaIni:");
		texto.append(fechaIni);
		texto.append(" fechaFin:");
		texto.append(fechaFin);
		texto.append(" tipo:");
		texto.append(tipo);
		return texto.toString();
	}


}
