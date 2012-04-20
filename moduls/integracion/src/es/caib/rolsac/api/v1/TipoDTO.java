package es.caib.rolsac.api.v1;

import java.io.Serializable;
import java.util.Set;

import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.TraduccionTipo;

public class TipoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

    private String nombre;

    public TipoDTO( Tipo tipo, String idioma )
    {
    	
    	TraduccionTipo traduccionTipo = null;
    	if( idioma != null && !"".equals( idioma ) )
    	{
    		traduccionTipo = ( TraduccionTipo )tipo.getTraduccion( idioma );
    	}
    	if (traduccionTipo == null) {
    		traduccionTipo = ( TraduccionTipo )tipo.getTraduccion();
    	}

    	this.id = tipo.getId();

    	this.nombre = traduccionTipo.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
