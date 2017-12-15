package org.ibit.rol.sac.model;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase normativa.
 * @author slromero
 *
 */
public class Normativa extends Traducible implements Indexable, Validable, Comparator<Normativa> {

	/** Serial Version UID.	 */
	private static final long serialVersionUID = 1L;
	/** Id. */
	private Long id;
	/** Numero. **/
    private Long numero;
    /** Registro. **/
    private Long registro;
    /** Ley. **/
    private String ley;
    /** Fecha. **/
    private Date fecha;
    /** Fecha boletin. **/
    private Date fechaBoletin;
    /** Validacion. **/
    private Integer validacion;
    /** Afectadas. **/
    private Set<Afectacion> afectadas;
    /** Afectantes. **/
    private Set<Afectacion> afectantes;
    /** Boletin. **/
    private Boletin boletin;
    /** Procedimientos. **/
    private Set<ProcedimientoLocal> procedimientos = new HashSet<ProcedimientoLocal>();
    /** Servicios. **/
    private Set<Servicio> servicios = new HashSet<Servicio>();
    /** Tipo. **/
    private Tipo tipo;
    /** Codi Vuds. */
    private String codiVuds;
    /** Desc codi vuds. **/
    private String descCodiVuds;
    /** Número normativa. **/
    private String numNormativa;
    /** Datos validos. **/
    private Integer datosValidos;
    
//	//Campos usados para optimizar la búsqueda
    /** Traduccion titulo. **/
	private String traduccionTitulo;
	/** Nombre boletin. **/
	private String nombreBoletin;
	/** Nombre tipo. **/
	private String nombreTipo;
	/** Idioma. **/
	private String idioma;
	/** Unidades administrativas **/
	private Set<UnidadNormativa> unidadesnormativas;
    
    /** Documentos. **/
    private Set<DocumentoNormativa> documentos = new HashSet<DocumentoNormativa>();	//set of documents
    

	/*
	private Map<String,Traduccion> traduccionesCombinadas = new HashMap<String,Traduccion>();

    protected Map<String,Traduccion> getTraduccionesCombinadas() {
        return traduccionesCombinadas;
    }

    protected void setTraduccionesCombinadas(Map<String,Traduccion> traducciones) {
        this.traduccionesCombinadas = traducciones;
    }*/

	
    public final String getTraduccionTitulo() {
    	if (traduccionTitulo == null && this.idioma != null && this.getTraduccion(idioma)!=null) {
    		return ((TraduccionNormativa)this.getTraduccion(idioma)).getTitulo();
    	} else {
    		return traduccionTitulo;
    	}
		
	}
	
	public final void setTraduccionTitulo(final String traduccionTitulo) {
		this.traduccionTitulo = traduccionTitulo;
	}

	public final String getNombreBoletin() {
		if (nombreBoletin == null && this.getBoletin() != null) {
			return this.getBoletin().getNombre();
		} else {
			return nombreBoletin;
		}
	}

	public final void setNombreBoletin(final String nombreBoletin) {
		this.nombreBoletin = nombreBoletin;
	}

	public final String getNombreTipo() {
		if (nombreTipo == null && this.getTipo() != null && this.idioma != null && this.getTipo().getTraduccion(idioma) != null) {
			return ((TraduccionTipo)this.getTipo().getTraduccion(idioma)).getNombre();
		} else {
			return nombreTipo;
		}
	}

	public final String getIdioma() {
		return idioma;
	}
		
	public final void setIdioma(final String idioma) {
		this.idioma = idioma;
	}
	
	public final void setNombreTipo(final String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public final Long getId() {
        return id;
    }

    public final void setId(final Long id) {
        this.id = id;
    }

    public final Long getNumero() {
        return numero;
    }

    public final void setNumero(final Long numero) {
        this.numero = numero;
    }

    public final Long getRegistro() {
        return registro;
    }

    public final void setRegistro(final Long registro) {
        this.registro = registro;
    }

    public final String getLey() {
        return ley;
    }

    public void setLey(final String ley) {
        this.ley = ley;
    }

    public final Date getFecha() {
        return fecha;
    }

    public final void setFecha(final Date fecha) {
        this.fecha = fecha;
    }

    public final Date getFechaBoletin() {
        return fechaBoletin;
    }

    public final void setFechaBoletin(final Date fechaBoletin) {
        this.fechaBoletin = fechaBoletin;
    }

    public final Integer getValidacion() {
        return validacion;
    }

    public final void setValidacion(final Integer validacion) {
        this.validacion = validacion;
    }

    public final Set<Afectacion> getAfectadas() {
        return afectadas;
    }

    public final void setAfectadas(final Set<Afectacion> afectadas) {
        this.afectadas = afectadas;
    }

    public final Set<Afectacion> getAfectantes() {
        return afectantes;
    }

    public final void setAfectantes(final Set<Afectacion> afectantes) {
        this.afectantes = afectantes;
    }

    public final Boletin getBoletin() {
        return boletin;
    }

    public final void setBoletin(final Boletin boletin) {
        this.boletin = boletin;
    }

    public final Tipo getTipo() {
        return tipo;
    }

    public final void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public final Set<ProcedimientoLocal> getProcedimientos() {
        return procedimientos;
    }

    public final void setProcedimientos(final Set<ProcedimientoLocal> procedimientos) {
        this.procedimientos = procedimientos;
    }

    /**
	 * @return the servicios
	 */
	public Set<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios the servicios to set
	 */
	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}

	public final String getCodiVuds() {
		return codiVuds;
	}

	public final void setCodiVuds(final String codiVuds) {
		this.codiVuds = codiVuds;
	}

	public final String getDescCodiVuds() {
		return descCodiVuds;
	}

	public final void setDescCodiVuds(final String descCodiVuds) {
		this.descCodiVuds = descCodiVuds;
	}
	
    public final boolean equals(final Object o) {
        if (this == o) {
        	return true;
        }
        if (!(o instanceof Normativa)) {
        	return false;
        }

        final Normativa normativa = (Normativa) o;

        if (id != null ? !id.equals(normativa.id) : normativa.id != null) {
        	return false;
        }

        return true;
    }

    public final int compare(Normativa u1, Normativa u2) {
    	if (u1 == null || u1.getId() == null) { return -1;}
 	    if (u2 == null || u2.getId() == null) { return 1;}
	    return u1.getId().compareTo(u2.getId());
	}
    
    public final int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }
    
    /**
     * Esta visible la normativa.
     * @return
     */
    public final Boolean isVisible() {
    	//#406 Ahora siempre es publica, solo es vigente o derogada. return this.getValidacion().equals(Validacion.PUBLICA);
    	return true;
    }
    
    /**
     * Esta vigente la normativa.
     * @return
     */
    public final Boolean isVigente() {
    	return this.getValidacion().equals(ValidacionNormativa.VIGENTE);
    }
    
    
    /**
	 * @return the datosValidos
	 */
	public Integer getDatosValidos() {
		return datosValidos;
	}

	/**
	 * @param datosValidos the datosValidos to set
	 */
	public void setDatosValidos(Integer datosValidos) {
		this.datosValidos = datosValidos;
	}
	
	/**
	 * Indica si la normativa tiene los datos validos.
	 * @return
	 */
	public boolean isDatosValidos() {
		boolean esValido;
		if (this.datosValidos != null && this.datosValidos == 1) {
			esValido = true;
		} else {
			esValido = false;
		}
		return esValido;
	}

	// Metode creat per poder ser cridad des de la JSP atraves de jstl
	public final Boolean getIsVisible() {
		return this.isVisible();
	}

	/**
	 * @return the numNormativa
	 */
	public final String getNumNormativa() {
		return numNormativa;
	}

	/**
	 * @param numNormativa the numNormativa to set
	 */
	public final void setNumNormativa(final String numNormativa) {
		this.numNormativa = numNormativa;
	}

	/**
	 * @return the documentos
	 */
	public final Set<DocumentoNormativa> getDocumentos() {
		return documentos;
	}

	/**
	 * @param documentos the documentos to set
	 */
	public final void setDocumentos(final Set<DocumentoNormativa> documentos) {
		this.documentos = documentos;
	}

	/**
	 * @return the unidadesnormativas
	 */
	public final Set<UnidadNormativa> getUnidadesnormativas() {
		return unidadesnormativas;
	}

	/**
	 * @param unidadesnormativas the unidadesnormativas to set
	 */
	public final void setUnidadesnormativas(final Set<UnidadNormativa> unidadesnormativas) {
		this.unidadesnormativas = unidadesnormativas;
	}

	/**
	 * Add unidad normativa
	 * @param unidadNormativa
	 */
	public final void addUnidadNormativa(final UnidadNormativa unidadNormativa) {
        unidadNormativa.setNormativa(this);
        unidadesnormativas.add(unidadNormativa);
    }

	/**
	 * Remove unidad normativa
	 * @param unidadNormativa
	 */
    public final void removeUnidadNormativa(final UnidadNormativa unidadNormativa) {
    	unidadesnormativas.remove(unidadNormativa);
    }

    /**
     * Add documento noramtiva. 
     * @param documentoNormativa
     */
    public void addDocumentoNormativa(DocumentoNormativa documentoNormativa) {
		this.documentos.add(documentoNormativa);							
	}
	
	/**
	 * Remove documento normativa
	 * @param documentoNormativa
	 */
    public void removeDocumentoNormativa(DocumentoNormativa documentoNormativa) {
		this.documentos.remove(documentoNormativa);		
    }
}
