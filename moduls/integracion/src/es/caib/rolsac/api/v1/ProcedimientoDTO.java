package es.caib.rolsac.api.v1;

import java.io.Serializable;
import java.util.Date;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;

public class ProcedimientoDTO implements Serializable 
{

	private static final long serialVersionUID = 8724656445784716638L;
	
	private Long id;
    private String signatura;
    //private Set<Tramite> tramites;
    //private List<Documento> documentos;
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
   // private UnidadAdministrativaDTO unidadAdministrativa;
    //private Set<Materia> materias;
    //private Iniciacion iniciacion;
    private String indicador;
    private String ventana;
    private String info;
    private String responsable;
    
    private String nombre;
    private String resumen;
    private String destinatarios;
    private String requisitos;
    private String plazos;
    private String resolucion;
    private String notificacion;
    private String silencio;
    private String recursos;
    private String observaciones;
    private String lugar;
    private String resultado;

    /* agarcia: no se usa en rolsac ayuntamientos
    private Set<HechoVitalProcedimiento> hechosVitalesProcedimientos;
    */

    public ProcedimientoDTO(ProcedimientoLocal pl, String idioma) {
    	this.id = pl.getId();
    	this.signatura = pl.getSignatura();
    	//this.tramites 
    	//this.documentos
    	this.fechaCaducidad = pl.getFechaCaducidad();
    	this.fechaPublicacion = pl.getFechaPublicacion();
    	this.fechaActualizacion = pl.getFechaActualizacion();
    	this.tramite = pl.getTramite();
    	this.version = pl.getVersion();
    	this.url = pl.getUrl();
    	this.orden = pl.getOrden();
    	this.orden2 = pl.getOrden2();
    	this.orden3 = pl.getOrden3();
    	this.validacion = pl.getValidacion();
    	//this.unidadAdministrativa = pl.getUnidadAdministrativa();
    	//this.unidadAdministrativa = new UnidadAdministrativaDTO(pl.getUnidadAdministrativa(), idioma, true);
    	//this.materias
    	//this.iniciacion
    	this.indicador = pl.getIndicador();
    	this.ventana = pl.getVentanillaUnica();
    	this.info = pl.getInfo();
    	this.responsable = pl.getResponsable();
    	
    	TraduccionProcedimientoLocal trad = (TraduccionProcedimientoLocal)pl.getTraduccion(idioma);
    	if (trad == null) {
    		trad = (TraduccionProcedimientoLocal)pl.getTraduccion();
    	}
    	this.nombre = trad.getNombre();
    	this.resumen = trad.getResumen();
    	this.destinatarios = trad.getDestinatarios();
    	this.requisitos = trad.getRequisitos();
    	this.plazos = trad.getPlazos();
    	this.resolucion = trad.getResolucion(); 
    	this.notificacion = trad.getNotificacion();
    	this.silencio = trad.getSilencio();
    	this.recursos = trad.getDestinatarios();
    	this.observaciones =  trad.getObservaciones();
    	this.lugar = trad.getLugar();
    	this.resultado = trad.getResultat();
	}
    

	public String getNombre() {
		return nombre;
	}


	public String getResumen() {
		return resumen;
	}


	public String getDestinatarios() {
		return destinatarios;
	}


	public String getRequisitos() {
		return requisitos;
	}


	public String getPlazos() {
		return plazos;
	}


	public String getResolucion() {
		return resolucion;
	}


	public String getNotificacion() {
		return notificacion;
	}


	public String getSilencio() {
		return silencio;
	}


	public String getRecursos() {
		return recursos;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public String getLugar() {
		return lugar;
	}


	public Long getId() {
        return id;
    }

    public String getSignatura() {
        return signatura;
    }

    /*
    public Set<Tramite> getTramites() {
        return tramites;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }*/

    public String getInfo() {
        return info;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Integer getValidacion() {
        return validacion;
    }

   /* public UnidadAdministrativaDTO getUnidadAdministrativa() {
        return unidadAdministrativa;
    }
   
    public UnidadAdministrativa getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public Set<Materia> getMaterias() {
        return materias;
    }

    public Iniciacion getIniciacion() {
        return iniciacion;
    }

    public Set<HechoVitalProcedimiento> getHechosVitalesProcedimientos() {
        return hechosVitalesProcedimientos;
    }
    */

    public String getTramite() {
		return tramite;
	}

	public Long getVersion() {
		return version;
	}

	public Long getOrden() {
	        return orden;
	}

	public String getIndicador() {
        return indicador;
	}
	public String getVentana() {
        return ventana;
	}
	public Long getOrden2() {
		return orden2;
	} 
	public Long getOrden3() {
		return orden3;
	} 
	public String getUrl() {
		return url;
	}

	public String getResponsable() {
		return responsable;
	}

	public String getResultado() {
		return resultado;
	}
}
