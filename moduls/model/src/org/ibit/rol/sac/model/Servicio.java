package org.ibit.rol.sac.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * Clase servicio.
 * @author slromero
 *
 */
public class Servicio extends Classificable implements Indexable, Validable, Comparator<Servicio> {

	public static final Integer ESTADO_PUBLICACION_PUBLICA = 1;
	public static final Integer ESTADO_PUBLICACION_INTERNA = 2;
	public static final Integer ESTADO_PUBLICACION_RESERVA = 3;
	
	private static final long serialVersionUID = 1L;
	/************ SECCION DADES. *********************/
	/** Id. **/
	private Long id;
	/** Código SIA. **/
	private String codigoSIA;
	/** Fecha SIA. **/
    private Date fechaSIA;
    /** Estado SIA. **/
    private String estadoSIA;
    /** Código. **/
	private String codigo;
	/** Organo instructor. **/
	private UnidadAdministrativa organoInstructor; 
	
    /************ SECCION DADES. *********************/
	/** Servicio responsable. **/
	private UnidadAdministrativa servicioResponsable; 
    /** Nombre del responsable. **/
    private String nombreResponsable;
    /** Correo. **/
    private String correo;
    /** Telefono. **/
    private String telefono;
    
    /************ SECCION TRAMITE. *******************/
	/** Tramite URL. **/
    private String tramiteUrl;
    /** Tramite ID. **/
    private String tramiteId;
    /** Trámite version. **/
    private String tramiteVersion;
    
    /************ MODUL TAXES. ***********************/
    /** Taxa url. **/
    private String tasaUrl;
    
    /************ MODUL PUBLICACIO. ******************/
    /** Validación. **/
    private Integer validacion;
    /** Fecha despublicación. **/
    private Date fechaDespublicacion;
    /** Fecha publicación. **/
    private Date fechaPublicacion;
    /** Fecha actualización. **/
    private Date fechaActualizacion;

    /** Documentos. **/
    private Set<DocumentoServicio> documentos = new HashSet<DocumentoServicio>();
    
    
    /** Normativas. **/
    private Set<Normativa> normativas;
    /** Hechos Vitales. **/
    private Set<HechoVitalServicio> hechosVitalesServicios;
    /** Publico objetivo. **/
    private Set<PublicoObjetivo> publicosObjetivo;
    
    
    //---------------------------------------------
    //Campos especiales para optimizar la búsqueda
    /** Nombre servicio que indica el order by. **/
    private String nombreServicio;
    /** Idioma de la busqueda. **/
    private String idioma;
  
  
	/** 
	 * Constructor para búsqueda optimizada.
	 * 
	 * @param id
	 * @param nombreServicio
	 * @param validacion
	 * @param fechaActualizacion
	 * @param fechaDespublicacion
	 * @param fechaPublicacion
	 * @param nombreServicio
	 * @param idioma
	 * @param nombreServicioResponsable
	 */
	public Servicio(Long id, String nombreServicio, Integer validacion, Date fechaActualizacion, Date fechaDespublicacion, Date fechaPublicacion, String idioma, String nombreServicioResponsable) {
		
    	super();
    	
    	this.id = id;
    	this.nombreServicio = nombreServicio != null ? nombreServicio : "";
    	this.validacion = validacion;
    	this.fechaActualizacion = fechaActualizacion;
    	this.fechaDespublicacion = fechaDespublicacion;
    	this.fechaPublicacion = fechaPublicacion;
    	this.idioma = idioma;
    	this.nombreResponsable = nombreServicioResponsable;
    }   
	
	
	//Constructores
	/**
	 * Constructor.
	 * @param id
	 */
    public Servicio(Long id) {
		super();
		this.id = id;
	}

    /**
     * Constructor.
     */
    public Servicio() {
		super();
	}
    
    /**
     * Idioma.
     * @return
     */
    public String getIdioma() {
    	return this.idioma;
    }
    
    /**
     * Get id.
     * @return
     */
	public Long getId() {
        return id;
    }

	/**
	 * Set id.
	 * @param id
	 */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Lista de documentos servicios.
     * @return
     */
    public Set<DocumentoServicio> getDocumentos() {
        return documentos;
    }

    /**
     * Set documentos servicios.
     * @param documentos
     */
    public void setDocumentos(Set<DocumentoServicio> documentos) {
        this.documentos = documentos;
    }

    /**
     * Add documento servicio.
     * @param documento
     */
    public final void addDocumentoServicio(final DocumentoServicio documento) {
        documento.setServicio(this);
        documento.setOrden(Long.valueOf(documentos.size()));
        documentos.add(documento);
    }

    /**
     * Remove documento servicio.
     * @param documento
     */
    public final void removeDocumentoServicio(final DocumentoServicio documento) {
        documentos.remove(documento);
        Long i = 0l;
        for(DocumentoServicio documentoServicio : documentos) {
        	documentoServicio.setOrden(i);
        	i++;
        }
    }
    
    /**
     * Get normativas.
     * @return
     */
    public Set<Normativa> getNormativas() {
        return normativas;
    }

    /**
     * Set normativas.
     * @param normativas
     */
    public void setNormativas(Set<Normativa> normativas) {
        this.normativas = normativas;
    }

   

    
	
	@Override
	public String toString() {
		String pid=obtenirId();
		String nombre = obtenerNombre();
		return "Servicio [id="+pid+ 
				" nombre="+nombre+ "]";
	}
	
	/**
	 * Obtener nombre.
	 * @return
	 */
	private String obtenerNombre() {
		final TraduccionServicio traduccion = (TraduccionServicio)getTraduccion();
		if(null==traduccion) {
			return null;
		} else {
			return traduccion.getNombre();
		}
	}

	/**
	 * Obtiene id.
	 * @return
	 */
	private String obtenirId() {
		return null==id? null : id.toString();
	}
	
	
	/**
	 * Set publico objetivo.
	 * @param publicosObjetivo
	 */
	public void setPublicosObjetivo(Set<PublicoObjetivo> publicosObjetivo) {
		this.publicosObjetivo = publicosObjetivo;
	}
	
	/**
	 * Add publico objetivo.
	 * @param publicosObjetivo
	 */
	public void addPublicosObjetivo(PublicoObjetivo publicosObjetivo) {
		this.publicosObjetivo.add(publicosObjetivo);
		
	}
	
	/**
	 * Remove publico objetivo.
	 * @param id
	 */
	public void removePublicosObjetivo(long id) {
		PublicoObjetivo pob=new PublicoObjetivo();
		pob.setId(id);
		this.publicosObjetivo.remove(pob);
	}
	
	@Override
	public boolean equals(Object obj) {
		Servicio other=(Servicio)obj;
		return (other instanceof Servicio) && id.equals(other.id);
	}

	/**
	 * Es visible?
	 * @return
	 */
	public Boolean isVisible() {
		
		GregorianCalendar dataActual = new GregorianCalendar(); 
		Boolean visible;
		
		Boolean esPublic = Validacion.PUBLICA.equals(this.getValidacion());
		Boolean noCaducat = (this.getFechaDespublicacion() != null && this.getFechaDespublicacion().after(dataActual.getTime())) || this.getFechaDespublicacion() == null;
		Boolean esPublicat =  (this.getFechaPublicacion() != null && this.getFechaPublicacion().before(dataActual.getTime())) || this.getFechaDespublicacion() == null;
		
		if (esPublic && noCaducat && esPublicat) {
			visible = Boolean.TRUE;
		} else {
			visible = Boolean.FALSE;
		}
		return visible;
	}
	
	// Mètode creat per poder ser cridat des de la JSP a través de JSTL
	/** 
	 * Get isVisible.
	 * @return
	 */
	public Boolean getIsVisible() {
		return this.isVisible();
	}

	/**
	 * Set nombre servicio.
	 * @param nombreServicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Set idioma.
	 * @param idioma
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	
	@Override
	public int compare(Servicio o1, Servicio o2) {
		int resultado;
		if (o1 == null || o1.getId() == null) {
			resultado = -1;
		} else if (o2 == null || o2.getId() == null) {
			resultado = 1;
		} else {
			resultado = o1.getId().compareTo(o2.getId());
		}
		return resultado;
	}

	/**
	 * @return the codigoSIA
	 */
	public String getCodigoSIA() {
		return codigoSIA;
	}

	/**
	 * @param codigoSIA the codigoSIA to set
	 */
	public void setCodigoSIA(String codigoSIA) {
		this.codigoSIA = codigoSIA;
	}

	/**
	 * @return the fechaSIA
	 */
	public Date getFechaSIA() {
		return fechaSIA;
	}

	/**
	 * @param fechaSIA the fechaSIA to set
	 */
	public void setFechaSIA(Date fechaSIA) {
		this.fechaSIA = fechaSIA;
	}

	/**
	 * @return the estadoSIA
	 */
	public String getEstadoSIA() {
		return estadoSIA;
	}

	/**
	 * @param estadoSIA the estadoSIA to set
	 */
	public void setEstadoSIA(String estadoSIA) {
		this.estadoSIA = estadoSIA;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the organoInstructor
	 */
	public UnidadAdministrativa getOrganoInstructor() {
		return organoInstructor;
	}

	/**
	 * @param organoInstructor the organoInstructor to set
	 */
	public void setOrganoInstructor(UnidadAdministrativa organoInstructor) {
		this.organoInstructor = organoInstructor;
	}

	/**
	 * @return the servicioResponsable
	 */
	public UnidadAdministrativa getServicioResponsable() {
		return servicioResponsable;
	}

	/**
	 * @param servicioResponsable the servicioResponsable to set
	 */
	public void setServicioResponsable(UnidadAdministrativa servicioResponsable) {
		this.servicioResponsable = servicioResponsable;
	}

	/**
	 * @return the nombreResponsable
	 */
	public String getNombreResponsable() {
		return nombreResponsable;
	}

	/**
	 * @param nombreResponsable the nombreResponsable to set
	 */
	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the tramiteUrl
	 */
	public String getTramiteUrl() {
		return tramiteUrl;
	}

	/**
	 * @param tramiteUrl the tramiteUrl to set
	 */
	public void setTramiteUrl(String tramiteUrl) {
		this.tramiteUrl = tramiteUrl;
	}

	/**
	 * @return the tramiteId
	 */
	public String getTramiteId() {
		return tramiteId;
	}

	/**
	 * @param tramiteId the tramiteId to set
	 */
	public void setTramiteId(String tramiteId) {
		this.tramiteId = tramiteId;
	}

	/**
	 * @return the tramiteVersion
	 */
	public String getTramiteVersion() {
		return tramiteVersion;
	}

	/**
	 * @param tramiteVersion the tramiteVersion to set
	 */
	public void setTramiteVersion(String tramiteVersion) {
		this.tramiteVersion = tramiteVersion;
	}

	/**
	 * @return the tasaUrl
	 */
	public String getTasaUrl() {
		return tasaUrl;
	}

	/**
	 * @param tasaUrl the tasaUrl to set
	 */
	public void setTasaUrl(String tasaUrl) {
		this.tasaUrl = tasaUrl;
	}

	/**
	 * @return the validacion
	 */
	public Integer getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion the validacion to set
	 */
	public void setValidacion(Integer validacion) {
		this.validacion = validacion;
	}

	/**
	 * @return the fechaDespublicacion
	 */
	public Date getFechaDespublicacion() {
		return fechaDespublicacion;
	}

	/**
	 * @param fechaDespublicacion the fechaDespublicacion to set
	 */
	public void setFechaDespublicacion(Date fechaDespublicacion) {
		this.fechaDespublicacion = fechaDespublicacion;
	}

	/**
	 * @return the fechaPublicacion
	 */
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * @return the fechaActualizacion
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion the fechaActualizacion to set
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the hechosVitalesServicios
	 */
	public Set<HechoVitalServicio> getHechosVitalesServicios() {
		return hechosVitalesServicios;
	}

	/**
	 * @param hechosVitalesServicios the hechosVitalesServicios to set
	 */
	public void setHechosVitalesServicios(Set<HechoVitalServicio> hechosVitalesServicios) {
		this.hechosVitalesServicios = hechosVitalesServicios;
	}

	/**
	 * @return the publicosObjetivo
	 */
	public Set<PublicoObjetivo> getPublicosObjetivo() {
		return publicosObjetivo;
	}

	/**
	 * @return the nombreServicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Add documento.
	 * @param documento
	 */
	 public void addDocumento(DocumentoServicio documento) {
        documento.setServicio(this);
        documento.setOrden(Long.valueOf(documentos.size()));
        documentos.add(documento);
	 }
	 
	 
	/**
	 * Add Hecho Vitales Servicio.
	 * @param hpv
	 */
	 public void addHechoVitalServicio(HechoVitalServicio hpv) {
        hpv.setServicio(this);
        hechosVitalesServicios.add(hpv);
     }

	 /**
	  * Remove hecho vitales servicio.
	  * @param hechovp
	  */
     public void removeHechoVitalServicio(HechoVitalServicio hechovp) {
        hechovp.setServicio(null);
        hechosVitalesServicios.remove(hechovp);
     }

	    
     public ServicioRemoto crearRemoto() {
    	 ServicioRemoto remoto = new ServicioRemoto();
         try {
             remoto.setParamValue(getId().toString());
             remoto.setParamName("idServicio");

             PropertyUtils.copyProperties(remoto, this);
             PropertyUtils.copyProperties(remoto, this.getTraduccion());
         } catch (IllegalAccessException e) {
             ;
         } catch (InvocationTargetException e) {
             ;
         } catch (NoSuchMethodException e) {
             ;
         }

         return remoto;
     }
     

}
