package org.ibit.rol.sac.model;

/**
 * Silencio administrativo. 
 * 
 * @author slromero
 *
 */
public class SilencioAdm extends Traducible {

    /**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	 public String getNombreSilencio(String idioma) {
	        TraduccionSilencio tet = (TraduccionSilencio) getTraduccion(idioma);
	        return tet == null ? null : tet.getNombre();
	    }
}
