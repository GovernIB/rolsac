package org.ibit.rol.sac.model;

import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Modificado para (PORMAD)
 */

public class ProcedimientoLocal extends Traducible implements Procedimiento, Indexable, Validable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String signatura;
    private List<Tramite> tramites;
    private List<Documento> documentos = new ArrayList<Documento>();
    private Set<Normativa> normativas = new HashSet();
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
    private Set<Materia> materias;
    private Familia familia;
    private Iniciacion iniciacion;
    private String indicador;
    private String ventanillaUnica;
    private String info;
    private String responsable;
    private Set<HechoVitalProcedimiento> hechosVitalesProcedimientos;

    private String taxa;
    private UnidadAdministrativa organResolutori; 



    //Constructores
    public ProcedimientoLocal(Long id) {
		super();
		this.id = id;
	}

    public ProcedimientoLocal() {
		super();
	}

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
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public Set<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<Materia> materias) {
        this.materias = materias;
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

    public IndexObject indexObject() {
        final IndexObject io = new IndexObject();
        // io.setId(getId());
        // io.setClassName(ProcedimientoLocal.class.getName());
        for (Iterator iterator = getTraducciones().values().iterator(); iterator.hasNext();) {
            TraduccionProcedimientoLocal tr = (TraduccionProcedimientoLocal) iterator.next();
            if (tr != null) {
                io.addTextLine(tr.getDestinatarios());
                io.addTextLine(tr.getLugar());
                io.addTextLine(tr.getNombre());
                io.addTextLine(tr.getObservaciones());
                io.addTextLine(tr.getPlazos());
                io.addTextLine(tr.getPlazos());
                io.addTextLine(tr.getResolucion());
                io.addTextLine(tr.getNotificacion());
                io.addTextLine(tr.getRecursos());
                io.addTextLine(tr.getRequisitos());
                io.addTextLine(tr.getResumen());
                io.addTextLine(tr.getSilencio());
            }
        }
        return io;
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
		String pid=null==id?null:id.toString();
		return "ProcedimientoLocal [id="+pid+ "]";
/*				
		return "ProcedimientoLocal [documentos=" + documentos + ", familia="
				+ familia + ", fechaActualizacion=" + fechaActualizacion
				+ ", fechaCaducidad=" + fechaCaducidad + ", fechaPublicacion="
				+ fechaPublicacion + ", hechosVitalesProcedimientos="
				+ hechosVitalesProcedimientos + ", id=" + id + ", indicador="
				+ indicador + ", info=" + info + ", iniciacion=" + iniciacion
				+ ", materias=" + materias + ", normativas=" + normativas
				+ ", orden=" + orden + ", orden2=" + orden2 + ", orden3="
				+ orden3 + ", signatura=" + signatura + ", tramite=" + tramite
				+ ", tramites=" + tramites + ", unidadAdministrativa="
				+ unidadAdministrativa + ", url=" + url + ", validacion="
				+ validacion + ", ventanillaUnica=" + ventanillaUnica
				+ ", version=" + version + "]";
				*/
	}

	/*
	public String toString(){
		StringBuffer salida = new StringBuffer("---ProcedimientoLocal---\n");
		salida.append("  -id: ");
		salida.append(id);
		salida.append("\n  -nombre: ");
		if(getTraduccion()!=null)
			salida.append(((TraduccionProcedimientoLocal)getTraduccion()).getNombre());
		salida.append("\n");
		return salida.toString();
	}
*/
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

	//u92770[enric] añadido equals para que procedimiento pueda ser testeable con easyMock.
	@Override
	public boolean equals(Object obj) {
		ProcedimientoLocal other=(ProcedimientoLocal)obj;
		return (other instanceof ProcedimientoLocal) && id.equals(other.id);
	}
}
