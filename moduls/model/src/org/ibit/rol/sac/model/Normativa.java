/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Normativa extends Traducible implements Indexable, Validable, Comparator {

	private Long id;
    private Long numero;
    private Long registro;
    private String ley;
    private Date fecha;
    private Date fechaBoletin;
    private Integer validacion;
    private Set afectadas;
    private Set afectantes;
    private Boletin boletin;
    private Set procedimientos = new HashSet();
    private Tipo tipo;
    private String codiVuds;  	 
    private String descCodiVuds;

//	//Campos usados para optimizar la busqueda
	private String traduccionTitulo;
	private String nombreBoletin;
	private String nombreTipo;    
	private String idioma;
    private UnidadAdministrativa unidadAdministrativa;

	
	private Map<String,Traduccion> traduccionesCombinadas = new HashMap<String,Traduccion>();

    protected Map<String,Traduccion> getTraduccionesCombinadas() {
        return traduccionesCombinadas;
    }

    protected void setTraduccionesCombinadas(Map<String,Traduccion> traducciones) {
        this.traduccionesCombinadas = traducciones;
    }

	
    public String getTraduccionTitulo() {
    	if (traduccionTitulo == null && this.idioma != null && this.getTraduccion(idioma)!=null) {
    		return ((TraduccionNormativa)this.getTraduccion(idioma)).getTitulo();
    	} else {
    		return traduccionTitulo;
    	}
		
	}

	public void setTraduccionTitulo(String traduccionTitulo) {
		this.traduccionTitulo = traduccionTitulo;
	}

	public String getNombreBoletin() {
		if (nombreBoletin == null && this.getBoletin() != null) {
			return this.getBoletin().getNombre();
		} else {
			return nombreBoletin;
		}
	}

	public void setNombreBoletin(String nombreBoletin) {
		this.nombreBoletin = nombreBoletin;
	}

	public String getNombreTipo() {
		if (nombreTipo == null && this.getTipo() != null && this.idioma != null && this.getTipo().getTraduccion(idioma) != null) {
			return ((TraduccionTipo)this.getTipo().getTraduccion(idioma)).getNombre();
		} else {
			return nombreTipo;
		}
	}

	public String getIdioma() {
		return idioma;
	}
		
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getRegistro() {
        return registro;
    }

    public void setRegistro(Long registro) {
        this.registro = registro;
    }

    public String getLey() {
        return ley;
    }

    public void setLey(String ley) {
        this.ley = ley;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaBoletin() {
        return fechaBoletin;
    }

    public void setFechaBoletin(Date fechaBoletin) {
        this.fechaBoletin = fechaBoletin;
    }

    public Integer getValidacion() {
        return validacion;
    }

    public void setValidacion(Integer validacion) {
        this.validacion = validacion;
    }

    public Set getAfectadas() {
        return afectadas;
    }

    public void setAfectadas(Set afectadas) {
        this.afectadas = afectadas;
    }

    public Set getAfectantes() {
        return afectantes;
    }

    public void setAfectantes(Set afectantes) {
        this.afectantes = afectantes;
    }

    public Boletin getBoletin() {
        return boletin;
    }

    public void setBoletin(Boletin boletin) {
        this.boletin = boletin;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Set getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(Set procedimientos) {
        this.procedimientos = procedimientos;
    }

    public String getCodiVuds() {
		return codiVuds;
	}

	public void setCodiVuds(String codiVuds) {
		this.codiVuds = codiVuds;
	}

	public String getDescCodiVuds() {
		return descCodiVuds;
	}

	public void setDescCodiVuds(String descCodiVuds) {
		this.descCodiVuds = descCodiVuds;
	}

    public UnidadAdministrativa getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }
	
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Normativa)) return false;

        final Normativa normativa = (Normativa) o;

        if (id != null ? !id.equals(normativa.id) : normativa.id != null) return false;

        return true;
    }

    public int compare(Object o1, Object o2) {
	    Normativa u1 = (Normativa) o1;
	    Normativa u2 = (Normativa) o2;
	    return u1.getId().intValue() - u2.getId().intValue();
	}
    
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }
    
    public Boolean isVisible() {
    	return this.getValidacion().equals(Validacion.PUBLICA);    			
    }
    
    // Metode creat per poder ser cridad des de la JSP atraves de jstl
	public Boolean getIsVisible() {
		return this.isVisible();
	}

}
