package org.ibit.rol.sac.model.criteria;

import java.io.Serializable;

import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.UnidadAdministrativa;

/**
 * Buscador criteria de servicios.
 * @author slromero
 *
 */
public class BuscadorServicioCriteria implements Serializable {
	
	private static final long serialVersionUID = -8158903773722414035L;
	
	private Servicio servicio;
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
	
	
	public BuscadorServicioCriteria() {
		
		
	}
	
	public BuscadorServicioCriteria(Servicio servicio, PaginacionCriteria paginacion, Long idMateria, 
			Long idPublicoObjetivo, Long idHechoVital, int visibilidad, Boolean enPlazo, Boolean telematico, Boolean uaPropias, Boolean uaHijas) {

		this.servicio = servicio;
		this.paginacion = paginacion;
		this.idMateria = idMateria;
		this.idPublicoObjetivo = idPublicoObjetivo;
		this.idHechoVital = idHechoVital;
		this.visibilidad = visibilidad;
		this.enPlazo = enPlazo;
		this.telematico = telematico;
		this.uaPropias = uaPropias;
		this.uaHijas = uaHijas;
		
	}
	
	public Servicio getServicio() {
		return servicio;
	}
	
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	public UnidadAdministrativa getUnidadAdministrativa() {
		return unidadAdministrativa;
	}
	
	public void setUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}
	
	public PaginacionCriteria getPaginacion() {
		return paginacion;
	}
	
	public void setPaginacion(PaginacionCriteria paginacion) {
		this.paginacion = paginacion;
	}
	
	public Long getIdMateria() {
		return idMateria;
	}
	
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	
	public Long getIdPublicoObjetivo() {
		return idPublicoObjetivo;
	}
	
	public void setIdPublicoObjetivo(Long idPublicoObjetivo) {
		this.idPublicoObjetivo = idPublicoObjetivo;
	}
	
	public Long getIdHechoVital() {
		return idHechoVital;
	}
	
	public void setIdHechoVital(Long idHechoVital) {
		this.idHechoVital = idHechoVital;
	}
	
	public int getVisibilidad() {
		return visibilidad;
	}
	
	public void setVisibilidad(int visibilidad) {
		this.visibilidad = visibilidad;
	}
	
	public Boolean getEnPlazo() {
		return enPlazo;
	}
	
	public void setEnPlazo(Boolean enPlazo) {
		this.enPlazo = enPlazo;
	}
	
	public Boolean getTelematico() {
		return telematico;
	}
	
	public void setTelematico(Boolean telematico) {
		this.telematico = telematico;
	}
	
	public Boolean getUaPropias() {
		return uaPropias;
	}

	public void setUaPropias(Boolean uaPropias) {
		this.uaPropias = uaPropias;
	}

	public Boolean getUaHijas() {
		return uaHijas;
	}

	public void setUaHijas(Boolean uaHijas) {
		this.uaHijas = uaHijas;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the soloId
	 */
	public Boolean getSoloId() {
		return soloId;
	}

	/**
	 * @param soloId the soloId to set
	 */
	public void setSoloId(Boolean soloId) {
		this.soloId = soloId;
	}

}
