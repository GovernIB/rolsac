package es.caib.rolsac.api.v1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.TraduccionEdificio;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;

public class EdificioDTO implements Serializable, Comparable<EdificioDTO> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -416289593433530651L;

    private Long id;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String telefono;
    private String fax;
    private String email;
    /*
    private Archivo fotoPequenya;
    private Archivo fotoGrande;
    private Archivo plano;
    */
    private Long fotoPequenya;
    private Long fotoGrande;
    private Long plano;

    private Long[] unidadesAdministrativasIds;
    private String latitud;
    private String longitud;
    private String descripcion;

    public EdificioDTO(Edificio edificio, String idioma) {
    	this(edificio, idioma, true);
    }

	public EdificioDTO(Edificio edificio, String idioma, boolean incluirUnidadesAdministratias) {
    	this.id = edificio.getId();
    	this.direccion = edificio.getDireccion();
    	this.codigoPostal = edificio.getCodigoPostal();
    	this.poblacion = edificio.getPoblacion();
    	this.telefono = edificio.getTelefono();
    	this.fax = edificio.getFax();
    	this.email = edificio.getEmail();
    	/*
    	this.fotoPequenya = edificio.getFotoPequenya();
    	this.fotoGrande = edificio.getFotoGrande();
    	this.plano = edificio.getPlano();*/
    	if (edificio.getFotoPequenya()!=null) this.fotoPequenya = edificio.getFotoPequenya().getId();
    	if (edificio.getFotoGrande()!=null) this.fotoGrande = edificio.getFotoGrande().getId();
    	if (edificio.getPlano()!=null) this.plano = edificio.getPlano().getId();

    	if (incluirUnidadesAdministratias) {
	    	HashSet<Long> unidadesAdministrativas = new HashSet<Long>();
	    	Set uas = edificio.getUnidadesAdministrativas();
	    	for (Object ua:uas) {
	    		unidadesAdministrativas.add(((UnidadAdministrativa)ua).getId());
	    	}
			this.unidadesAdministrativasIds = new Long[unidadesAdministrativas.size()];
			this.unidadesAdministrativasIds = unidadesAdministrativas.toArray(this.unidadesAdministrativasIds);
    	}
    	
    	this.latitud = edificio.getLatitud();
    	this.longitud = edificio.getLongitud();
    	TraduccionEdificio trad = (TraduccionEdificio)edificio.getTraduccion(idioma);
		if (trad == null) {
			trad = (TraduccionEdificio)edificio.getTraduccion();
		}
    	this.descripcion = trad.getDescripcion();
	}

	public Long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    /*
    public Archivo getFotoPequenya() {
        return fotoPequenya;
    }

    public Archivo getFotoGrande() {
        return fotoGrande;
    }

    public Archivo getPlano() {
        return plano;
    }*/
    
    public Long getFotoPequenya() {
        return fotoPequenya;
    }

    public Long getFotoGrande() {
        return fotoGrande;
    }

    public Long getPlano() {
        return plano;
    }

    public Long[] getUnidadesAdministrativasIds() {
        return unidadesAdministrativasIds;
    }

    public String getLatitud() {
        return latitud;
    }
    
    public String getLongitud() {
        return longitud;
    }
    
    public String getDescripcion() {
    	return descripcion;
    }

	public int compareTo(EdificioDTO o) {
		return this.descripcion.compareToIgnoreCase( o.getDescripcion() );
	}
}
