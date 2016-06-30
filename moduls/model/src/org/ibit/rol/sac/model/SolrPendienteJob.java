package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Representación de un SolrPendiente Job .Anyadido nombre(slromero)
 * Indexaciones a traves de solr
 */
public class SolrPendienteJob implements ValueObject {
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	
	
			
	/** Id. **/
    private Long id;
    /** Fecha de inicio. **/
    private Date fechaIni;
    /** Fecha de fin. **/
	private Date fechaFin;
	/** Fecha de fin de fichas. **/
	private Date fechaFicha; 
	/** Fecha de fin de procedimientos. **/
	private Date fechaProcedimiento;
	/** Fecha de fin de normativas. **/
	private Date fechaNormativa;
	/** Fecha de fin de trámites. **/
	private Date fechaTramite;
	/** Fecha de fin de unidad administrativa. */
	private Date fechaUnidadAdministrativa;
	/** Total ficha. **/
	private Float totalFicha;
	/** Total ficha doc. **/
	private Float totalFichaDoc;
	/** Total procedimiento. **/
	private Float totalProcedimiento;
	/** Total procedimiento doc. **/
	private Float totalProcedimientoDoc;
	/** Total normativa. **/
	private Float totalNormativa;
	/** Total normativa doc. **/
	private Float totalNormativaDoc;
	/** Total tramites. **/
	private Float totalTramite;
	/** Total unidad administrativa. **/
	private Float totalUnidadAdministrativa;
	
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
	 * @return the fechaFicha
	 */
	public final Date getFechaFicha() {
		return fechaFicha;
	}
	/**
	 * @param fechaFicha the fechaFicha to set
	 */
	public final void setFechaFicha(Date fechaFicha) {
		this.fechaFicha = fechaFicha;
	}
	/**
	 * @return the fechaProcedimiento
	 */
	public final Date getFechaProcedimiento() {
		return fechaProcedimiento;
	}
	/**
	 * @param fechaProcedimiento the fechaProcedimiento to set
	 */
	public final void setFechaProcedimiento(Date fechaProcedimiento) {
		this.fechaProcedimiento = fechaProcedimiento;
	}
	/**
	 * @return the fechaNormativa
	 */
	public final Date getFechaNormativa() {
		return fechaNormativa;
	}
	/**
	 * @param fechaNormativa the fechaNormativa to set
	 */
	public final void setFechaNormativa(Date fechaNormativa) {
		this.fechaNormativa = fechaNormativa;
	}
	/**
	 * @return the fechaTramite
	 */
	public final Date getFechaTramite() {
		return fechaTramite;
	}
	/**
	 * @param fechaTramite the fechaTramite to set
	 */
	public final void setFechaTramite(Date fechaTramite) {
		this.fechaTramite = fechaTramite;
	}
	/**
	 * @return the fechaUnidadAdministrativa
	 */
	public final Date getFechaUnidadAdministrativa() {
		return fechaUnidadAdministrativa;
	}
	/**
	 * @param fechaUnidadAdministrativa the fechaUnidadAdministrativa to set
	 */
	public final void setFechaUnidadAdministrativa(Date fechaUnidadAdministrativa) {
		this.fechaUnidadAdministrativa = fechaUnidadAdministrativa;
	}
	/**
	 * @return the totalFicha
	 */
	public final Float getTotalFicha() {
		return totalFicha;
	}
	/**
	 * @param totalFicha the totalFicha to set
	 */
	public final void setTotalFicha(Float totalFicha) {
		this.totalFicha = totalFicha;
	}
	/**
	 * @return the totalFichaDoc
	 */
	public final Float getTotalFichaDoc() {
		return totalFichaDoc;
	}
	/**
	 * @param totalFichaDoc the totalFichaDoc to set
	 */
	public final void setTotalFichaDoc(Float totalFichaDoc) {
		this.totalFichaDoc = totalFichaDoc;
	}
	/**
	 * @return the totalProcedimiento
	 */
	public final Float getTotalProcedimiento() {
		return totalProcedimiento;
	}
	/**
	 * @param totalProcedimiento the totalProcedimiento to set
	 */
	public final void setTotalProcedimiento(Float totalProcedimiento) {
		this.totalProcedimiento = totalProcedimiento;
	}
	/**
	 * @return the totalProcedimientoDoc
	 */
	public final Float getTotalProcedimientoDoc() {
		return totalProcedimientoDoc;
	}
	/**
	 * @param totalProcedimientoDoc the totalProcedimientoDoc to set
	 */
	public final void setTotalProcedimientoDoc(Float totalProcedimientoDoc) {
		this.totalProcedimientoDoc = totalProcedimientoDoc;
	}
	/**
	 * @return the totalNormativa
	 */
	public final Float getTotalNormativa() {
		return totalNormativa;
	}
	/**
	 * @param totalNormativa the totalNormativa to set
	 */
	public final void setTotalNormativa(Float totalNormativa) {
		this.totalNormativa = totalNormativa;
	}
	/**
	 * @return the totalNormativaDoc
	 */
	public final Float getTotalNormativaDoc() {
		return totalNormativaDoc;
	}
	/**
	 * @param totalNormativaDoc the totalNormativaDoc to set
	 */
	public final void setTotalNormativaDoc(Float totalNormativaDoc) {
		this.totalNormativaDoc = totalNormativaDoc;
	}
	/**
	 * @return the totalTramite
	 */
	public final Float getTotalTramite() {
		return totalTramite;
	}
	/**
	 * @param totalTramite the totalTramite to set
	 */
	public final void setTotalTramite(Float totalTramite) {
		this.totalTramite = totalTramite;
	}
	/**
	 * @return the totalUnidadAdministrativa
	 */
	public final Float getTotalUnidadAdministrativa() {
		return totalUnidadAdministrativa;
	}
	/**
	 * @param totalUnidadAdministrativa the totalUnidadAdministrativa to set
	 */
	public final void setTotalUnidadAdministrativa(Float totalUnidadAdministrativa) {
		this.totalUnidadAdministrativa = totalUnidadAdministrativa;
	}
	
	@Override
	public String toString() {
		StringBuffer texto = new StringBuffer();
		texto.append("SolrPendienteJob id:");
		texto.append(fechaIni);
		texto.append(" fechaIni:");
		texto.append(fechaFin);
		texto.append(" fechaFin:");
		return texto.toString();
	}
	
	/**
	 * Es actualizable si el modulo con 3 (para que se actualice cada 3%) es 0.
	 * @return
	 */
	public boolean isTotalFichaActualizable() {
		boolean retorno;
		if (totalFicha != null && (totalFicha % 3.0f) == 0.0f) {
			retorno = true;
		} else {
			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Es actualizable si el modulo con 3 (para que se actualice cada 3%) es 0.
	 * @return
	 */
	public boolean isTotalFichaDocActualizable() {
		boolean retorno;
		if (totalFichaDoc != null && (totalFichaDoc % 3.0f) == 0.0f) {
			retorno = true;
		} else {
			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Es actualizable si el modulo con 3 (para que se actualice cada 3%) es 0.
	 * @return
	 */
	public boolean isTotalProcedimientoActualizable() {
		boolean retorno;
		if (totalProcedimiento != null && (totalProcedimiento % 3.0f) == 0.0f) {
			retorno = true;
		} else {
			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Es actualizable si el modulo con 3 (para que se actualice cada 3%) es 0.
	 * @return
	 */
	public boolean isTotalProcedimientoDocActualizable() {
		boolean retorno;
		if (totalProcedimientoDoc != null && (totalProcedimientoDoc % 3.0f) == 0.0f) {
			retorno = true;
		} else {
			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Es actualizable si el modulo con 3 (para que se actualice cada 3%) es 0.
	 * @return
	 */
	public boolean isTotalNormativaActualizable() {
		boolean retorno;
		if (totalNormativa != null && (totalNormativa % 3.0f) == 0.0f) {
			retorno = true;
		} else {
			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Es actualizable si el modulo con 3 (para que se actualice cada 3%) es 0.
	 * @return
	 */
	public boolean isTotalNormativaDocActualizable() {
		boolean retorno;
		if (totalNormativaDoc != null && (totalNormativaDoc % 3.0f) == 0.0f) {
			retorno = true;
		} else {
			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Es actualizable si el modulo con 3 (para que se actualice cada 3%) es 0.
	 * @return
	 */
	public boolean isTotalTramiteActualizable() {
		boolean retorno;
		if (totalTramite != null && (totalTramite % 3.0f) == 0.0f) {
			retorno = true;
		} else {
			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Es actualizable si el modulo con 3 (para que se actualice cada 3%) es 0.
	 * @return
	 */
	public boolean isTotalUAActualizable() {
		boolean retorno;
		if (totalUnidadAdministrativa != null && (totalUnidadAdministrativa % 3.0f) == 0.0f) {
			retorno = true;
		} else {
			retorno = false;
		}
		return retorno;
	}
	
}
