package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.Boletin;

public class BoletinDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String enlace;

	public BoletinDTO( Boletin boletin, String idioma )
	{
		this.nombre = boletin.getNombre();
		this.id = boletin.getId();
		this.enlace = boletin.getEnlace();
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

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
}
