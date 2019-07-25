package org.ibit.rol.sac.model.criteria;

import java.io.Serializable;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;

public class BuscadorProcedimientoCriteria implements Serializable {

	private static final long serialVersionUID = -8158903773722414035L;

	private ProcedimientoLocal procedimiento;
	private UnidadAdministrativa unidadAdministrativa;
	private PaginacionCriteria paginacion;
	private Long idMateria;
	private Long idPublicoObjetivo;
	private Long idHechoVital;
	private int visibilidad;
	private Boolean enPlazo;
	private Boolean telematico;
	private Boolean uaPropias;
	private Boolean uaHijas;
	private String locale;
	private Boolean soloId = false;
	private Integer comun;

	public BuscadorProcedimientoCriteria() {

	}

	public BuscadorProcedimientoCriteria(final ProcedimientoLocal procedimiento, final PaginacionCriteria paginacion,
			final Long idMateria, final Long idPublicoObjetivo, final Long idHechoVital, final int visibilidad,
			final Boolean enPlazo, final Boolean telematico, final Boolean uaPropias, final Boolean uaHijas,
			final Integer comun) {

		this.procedimiento = procedimiento;
		this.paginacion = paginacion;
		this.idMateria = idMateria;
		this.idPublicoObjetivo = idPublicoObjetivo;
		this.idHechoVital = idHechoVital;
		this.visibilidad = visibilidad;
		this.enPlazo = enPlazo;
		this.telematico = telematico;
		this.uaPropias = uaPropias;
		this.uaHijas = uaHijas;
		this.comun = comun;
	}

	public ProcedimientoLocal getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(final ProcedimientoLocal procedimiento) {
		this.procedimiento = procedimiento;
	}

	public UnidadAdministrativa getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	public void setUnidadAdministrativa(final UnidadAdministrativa unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

	public PaginacionCriteria getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(final PaginacionCriteria paginacion) {
		this.paginacion = paginacion;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(final Long idMateria) {
		this.idMateria = idMateria;
	}

	public Long getIdPublicoObjetivo() {
		return idPublicoObjetivo;
	}

	public void setIdPublicoObjetivo(final Long idPublicoObjetivo) {
		this.idPublicoObjetivo = idPublicoObjetivo;
	}

	public Long getIdHechoVital() {
		return idHechoVital;
	}

	public void setIdHechoVital(final Long idHechoVital) {
		this.idHechoVital = idHechoVital;
	}

	public int getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(final int visibilidad) {
		this.visibilidad = visibilidad;
	}

	public Boolean getEnPlazo() {
		return enPlazo;
	}

	public void setEnPlazo(final Boolean enPlazo) {
		this.enPlazo = enPlazo;
	}

	public Boolean getTelematico() {
		return telematico;
	}

	public void setTelematico(final Boolean telematico) {
		this.telematico = telematico;
	}

	public Boolean getUaPropias() {
		return uaPropias;
	}

	public void setUaPropias(final Boolean uaPropias) {
		this.uaPropias = uaPropias;
	}

	public Boolean getUaHijas() {
		return uaHijas;
	}

	public void setUaHijas(final Boolean uaHijas) {
		this.uaHijas = uaHijas;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(final String locale) {
		this.locale = locale;
	}

	/**
	 * @return the soloId
	 */
	public Boolean getSoloId() {
		return soloId;
	}

	/**
	 * @param soloId
	 *            the soloId to set
	 */
	public void setSoloId(final Boolean soloId) {
		this.soloId = soloId;
	}

	/**
	 * @return the comun
	 */
	public Integer getComun() {
		return comun;
	}

	/**
	 * @param comun
	 *            the comun to set
	 */
	public void setComun(final Integer comun) {
		this.comun = comun;
	}

}
