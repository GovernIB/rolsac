package org.ibit.rol.sac.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Modificado para (PORMAD)
 */
@SuppressWarnings("deprecation")
public class ProcedimientoLocal extends Classificable implements Procedimiento, Indexable, Validable, Comparator<ProcedimientoLocal> {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String signatura;
    private List<Tramite> tramites;
	private List<Documento> documentos;
    private Set<Normativa> normativas;
    private Date fechaCaducidad;
    private Date fechaPublicacion;
    private Date fechaActualizacion;

    private String tramite;
    private Long version;
    private String url;
    private Long orden;
    private Long orden2;
    private Long orden3;
    private Integer validacion;
    private UnidadAdministrativa unidadAdministrativa;  //organisme responsable
    private Familia familia;
    private Iniciacion iniciacion;
    private String indicador;
    private String ventanillaUnica;
    //#351 se cambia info por dirElectronica
	//private String info;
    private String responsable;
    private Set<HechoVitalProcedimiento> hechosVitalesProcedimientos;
    private Set<PublicoObjetivo> publicosObjetivo;
	private String taxa;
    private UnidadAdministrativa organResolutori;
    private UnidadAdministrativa servicioResponsable;
    private String dirElectronica;
    private String codigoSIA;
    private Date fechaSIA;
    private String estadoSIA;
    private SilencioAdm silencio;
    
    //---------------------------------------------
    //Campos especiales para optimizar la búsqueda
    private String nombreProcedimiento;
    private String nombreFamilia;
    private String idioma;
    
    //Constructor para búsqueda optimizada
	public ProcedimientoLocal(Long id, String nombreProcedimiento,
			Integer validacion, Date fechaActualizacion, Date fechaCaducidad,
			Date fechaPublicacion, String nombreFamilia, String idioma, UnidadAdministrativa ua) {
		
    	super();
    	
    	this.id = id;
    	this.nombreProcedimiento = nombreProcedimiento != null ? nombreProcedimiento : "";
    	this.validacion = validacion;
    	this.fechaActualizacion = fechaActualizacion;
    	this.fechaCaducidad = fechaCaducidad;
    	this.fechaPublicacion = fechaPublicacion;
    	this.nombreFamilia = nombreFamilia != null ? nombreFamilia : "";
    	this.unidadAdministrativa = ua;
    	this.idioma = idioma;
    }    
    //---------------------------------------------

    //Constructores
    public ProcedimientoLocal(Long id) {
		super();
		this.id = id;
	}

    public ProcedimientoLocal() {
		super();
	}

    public String getNombreFamilia() {
    	return this.nombreFamilia;
    }
    
    public String getNombreProcedimiento() {
    	return this.nombreProcedimiento;
    }
    
    public String getIdioma() {
    	return this.idioma;
    }
    //-------------------------------------------
    
    // get & set

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    public List<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    public void addTramite(Tramite tramite) {
    	tramite.setProcedimiento(this);
        tramite.setOrden((long)tramites.size());
    	tramites.add(tramite);

    }

    public void removeTramite(Tramite tramite) {
    	int ind = tramites.indexOf(tramite);
        tramite.setProcedimiento(null);
        tramites.remove(tramite);
        for (int i = ind; i < tramites.size(); i++) {
            Tramite t = (Tramite) tramites.get(i);
            if (t != null)
            	t.setOrden((long)i);
        }
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public void addDocumento(Documento documento) {
        documento.setProcedimiento(this);
        documento.setOrden(documentos.size());
        documentos.add(documento);
    }

    public void removeDocumento(Documento documento) {
        int ind = documentos.indexOf(documento);
        documentos.remove(ind);
        for (int i = ind; i < documentos.size(); i++) {
            Documento d = (Documento) documentos.get(i);
            d.setOrden(i);
        }
    }
    
    public Set<Normativa> getNormativas() {
        return normativas;
    }

    public void setNormativas(Set<Normativa> normativas) {
        this.normativas = normativas;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getValidacion() {
        return validacion;
    }

    public void setValidacion(Integer validacion) {
        this.validacion = validacion;
    }

    public UnidadAdministrativa getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
    public Iniciacion getIniciacion() {
        return iniciacion;
    }

    public void setIniciacion(Iniciacion iniciacion) {
        this.iniciacion = iniciacion;
    }

    public Set<HechoVitalProcedimiento> getHechosVitalesProcedimientos() {
        return hechosVitalesProcedimientos;
    }

    public void setHechosVitalesProcedimientos(Set<HechoVitalProcedimiento> hechosVitalesProcedimientos) {
        this.hechosVitalesProcedimientos = hechosVitalesProcedimientos;
    }

    public void addHechoVitalProcedimiento(HechoVitalProcedimiento hpv) {
        hpv.setProcedimiento(this);
        hechosVitalesProcedimientos.add(hpv);
    }

    public void removeHechoVitalProcedimiento(HechoVitalProcedimiento hechovp) {
        hechovp.setProcedimiento(null);
        hechosVitalesProcedimientos.remove(hechovp);
    }

    

    public ProcedimientoRemotoAntiguo crearRemoto() {
        ProcedimientoRemotoAntiguo remoto = new ProcedimientoRemotoAntiguo();
        try {
            remoto.setParamValue(getId().toString());
            remoto.setParamName("idProcedimiento");

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

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	 public Long getOrden() {
	        return orden;
	}

	public void setOrden(Long orden) {
	        this.orden = orden;
	}
	public String getIndicador() {
        return indicador;
	}
	public void setIndicador(String indicador) {
        this.indicador = indicador;
	}
	public String getVentanillaUnica() {
        return ventanillaUnica;
	}
	public void setVentanillaUnica(String ventana) {
        this.ventanillaUnica = ventana;
	}
	public Long getOrden2() {
		return orden2;
	} 
	public void setOrden2(Long orden2) {
		this.orden2 = orden2;
	}
	public Long getOrden3() {
		return orden3;
	} 
	public void setOrden3(Long orden3) {
		this.orden3 = orden3;
	}
	
	
	@Override
	public String toString() {
		String pid=obtenirId();
		String nombre = obtenerNombre();
		return "ProcedimientoLocal [id="+pid+ 
				" nombre="+nombre+ "]";
	}
	
	private String obtenerNombre() {
		TraduccionProcedimientoLocal traduccion = (TraduccionProcedimientoLocal)getTraduccion();
		if(null==traduccion) return null;

		return traduccion.getNombre();
	}

	private String obtenirId() {
		return null==id? null : id.toString();
	}
	
	
	public int compare(ProcedimientoLocal u1, ProcedimientoLocal u2) {
	    
	    int res = 0;
	    if (u1 == null ) {
	    	res = -1;
		} else if (u2 == null) {
			res = 1;
		}  else if (u1.getOrden()!=null && u2.getOrden()!=null){
	    	res = u1.getOrden().intValue() - u2.getOrden().intValue();
	    } else {
	    	res = u1.getId().intValue() - u2.getId().intValue();
	    }
	    return res;
	}

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}

	public UnidadAdministrativa getOrganResolutori() {
		return organResolutori;
	}

	public void setOrganResolutori(UnidadAdministrativa organResolutori) {
		this.organResolutori = organResolutori;
	}
    public Set<PublicoObjetivo> getPublicosObjetivo() {
		return publicosObjetivo;
	}

	public void setPublicosObjetivo(Set<PublicoObjetivo> publicosObjetivo) {
		this.publicosObjetivo = publicosObjetivo;
	}
	
	public void addPublicosObjetivo(PublicoObjetivo publicosObjetivo) {
		this.publicosObjetivo.add(publicosObjetivo);
		
	}

	public void removePublicosObjetivo(long id) {
		PublicoObjetivo pob=new PublicoObjetivo();
		pob.setId(id);
		this.publicosObjetivo.remove(pob);
	}
	
	//u92770[enric] anyadido equals para que procedimiento pueda ser testeable con easyMock.
	@Override
	public boolean equals(Object obj) {
		ProcedimientoLocal other=(ProcedimientoLocal)obj;
		return (other instanceof ProcedimientoLocal) && id.equals(other.id);
	}

	String getNombreUnidadAdministrativa(String idioma) {
		return ((TraduccionUA)unidadAdministrativa.getTraduccion(idioma)).getNombre();
	}

	
	public boolean esVentanillaUnica() {
		 return "1".equals(getVentanillaUnica());
	}
	

	public Boolean isVisible() {
		
		GregorianCalendar dataActual = new GregorianCalendar(); 
		Boolean visible;
		
		Boolean esPublic = Validacion.PUBLICA.equals(this.getValidacion());
		Boolean noCaducat = (this.getFechaCaducidad() != null && this.getFechaCaducidad().after(dataActual.getTime())) || this.getFechaCaducidad() == null;
		Boolean esPublicat =  (this.getFechaPublicacion() != null && this.getFechaPublicacion().before(dataActual.getTime())) || this.getFechaPublicacion() == null;
		
		if (esPublic && noCaducat && esPublicat) {
			visible = Boolean.TRUE;
		} else {
			visible = Boolean.FALSE;
		}
		return visible;
	}
	
	// Mètode creat per poder ser cridat des de la JSP a través de JSTL
	public Boolean getIsVisible() {
		return this.isVisible();
	}

	public void setNombreProcedimiento(String nombreProcedimiento) {
		this.nombreProcedimiento = nombreProcedimiento;
	}

	public void setNombreFamilia(String nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public UnidadAdministrativa getServicioResponsable() {
		return servicioResponsable;
	}

	public void setServicioResponsable(UnidadAdministrativa servicioResponsable) {
		this.servicioResponsable = servicioResponsable;
	}

	public String getDirElectronica() {
		return dirElectronica;
	}

	public void setDirElectronica(String dirElectronica) {
		this.dirElectronica = dirElectronica;
	}

	public String getCodigoSIA() {
		return codigoSIA;
	}

	public void setCodigoSIA(String codigoSIA) {
		this.codigoSIA = codigoSIA;
	}

	public SilencioAdm getSilencio() {
		return silencio;
	}

	public void setSilencio(SilencioAdm silencio) {
		this.silencio = silencio;
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

	
}
