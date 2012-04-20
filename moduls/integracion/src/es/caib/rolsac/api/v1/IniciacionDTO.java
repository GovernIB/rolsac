package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionIniciacion;

public class IniciacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigoEstandar;
	private String nombre;
	private String descripcion;

	public IniciacionDTO( Iniciacion iniciacion, String idioma )
	{
		TraduccionIniciacion traduccionIniciacion = ( TraduccionIniciacion )iniciacion.getTraduccion( idioma );
		if (traduccionIniciacion == null) {
			traduccionIniciacion = (TraduccionIniciacion)iniciacion.getTraduccion();
		}
		this.id = iniciacion.getId();
		this.codigoEstandar = iniciacion.getCodigoEstandar();
		this.nombre = traduccionIniciacion.getNombre();
		this.descripcion = traduccionIniciacion.getDescripcion();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoEstandar() {
		return codigoEstandar;
	}

	public void setCodigoEstandar(String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
