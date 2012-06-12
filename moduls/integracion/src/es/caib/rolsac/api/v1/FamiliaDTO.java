package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.TraduccionEdificio;
import org.ibit.rol.sac.model.TraduccionFamilia;

public class FamiliaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	private Long id;
    private String nombre;
    private String descripcion;

    public FamiliaDTO( Familia familia, String idioma )
    {
    	TraduccionFamilia traduccionFamilia = ( TraduccionFamilia )familia.getTraduccion( idioma );
		if (traduccionFamilia == null) {
			traduccionFamilia = (TraduccionFamilia)familia.getTraduccion();
		}
    	this.id = familia.getId();
    	this.nombre = traduccionFamilia.getNombre();
    	this.descripcion = traduccionFamilia.getDescripcion();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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