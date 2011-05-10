package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.ibit.rol.sac.model.*;

/**
 * Clase que representa la información a transferir de un Procedimiento(PORMAD)
 */
public class ProcedimientoTransferible extends ActuacionTransferible implements Serializable {

	public static final String URL_PROC = "es.caib.rolsac.model.ws.urlProcedimiento";
	
	private Long id;
    private String signatura;
    
    private Date fechaCaducidad;
    private Date fechaPublicacion;
    private Date fechaActualizacion;

    private String tramite;
    private Long version;
    
    private Integer validacion;
    private String info;

    private Long idUnidadAdministrativa;  //organ responsable
    private String[] codigoEstandarMaterias;
    private String[] codigoEstandarHV;
    private String urlRemota;
    private String responsable;

    //VUDS
    private Long idOrganResolutori;// organo competente
    private String codigoEstandarIniciacion;
    private String indicador;
    private String ventanillaUnica;
    private String taxa;
    private String url;
    private NormativaTransferible[] normativas;

    private TraduccionProcedimientoTransferible[] traducciones;

    //marilen VUDS
   private Long[]  idsDocsInfor;



    public Long getIdUnidadAdministrativa() {
        return idUnidadAdministrativa;
    }

    public void setIdUnidadAdministrativa(Long idUnidadAdministrativa) {
        this.idUnidadAdministrativa = idUnidadAdministrativa;
    }
    
	public String[] getCodigoEstandarMaterias() {
		return codigoEstandarMaterias;
	}
	public void setCodigoEstandarMaterias(String[] codigoEstandarMaterias) {
		this.codigoEstandarMaterias = codigoEstandarMaterias;
	}

    public String[] getCodigoEstandarHV() {
        return codigoEstandarHV;
    }

    public void setCodigoEstandarHV(String[] codigoEstandarHV) {
        this.codigoEstandarHV = codigoEstandarHV;
    }

    public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
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
	public TraduccionProcedimientoTransferible[] getTraducciones() {
		return traducciones;
	}
	public void setTraducciones(TraduccionProcedimientoTransferible[] traducciones) {
		this.traducciones = traducciones;
	}
	public String getTramite() {
		return tramite;
	}
	public void setTramite(String tramite) {
		this.tramite = tramite;
	}
	public Integer getValidacion() {
		return validacion;
	}
	public void setValidacion(Integer validacion) {
		this.validacion = validacion;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

    public Long getIdOrganResolutori() {
        return idOrganResolutori;
    }

    public void setIdOrganResolutori(Long idOrganResolutori) {
        this.idOrganResolutori = idOrganResolutori;
    }

    public String getCodigoEstandarIniciacion() {
        return codigoEstandarIniciacion;
    }

    public void setCodigoEstandarIniciacion(String codigoEstandarIniciacion) {
        this.codigoEstandarIniciacion = codigoEstandarIniciacion;
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

    public void setVentanillaUnica(String ventanillaUnica) {
        this.ventanillaUnica = ventanillaUnica;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public NormativaTransferible[] getNormativas() {
		return normativas;
	}

	public void setNormativas(NormativaTransferible[] normativas) {
		this.normativas = normativas;
	}

    public Long[] getIdsDocsInfor() {
        return idsDocsInfor;
    }

    public void setIdsDocsInfor(Long[] idsDocsInfor) {
        this.idsDocsInfor = idsDocsInfor;
    }

    @SuppressWarnings("unchecked")
	public void rellenar(ProcedimientoLocal proc){
    	//Relleno los campos
		this.setId(proc.getId());
		this.setSignatura(proc.getSignatura());
		this.setFechaCaducidad(proc.getFechaCaducidad());
		this.setFechaPublicacion(proc.getFechaPublicacion());
		this.setFechaActualizacion(proc.getFechaActualizacion());
		this.setTramite(proc.getTramite());
		this.setVersion(proc.getVersion());
		this.setValidacion(proc.getValidacion());
        this.setInfo(proc.getInfo());
        this.setTaxa(proc.getTaxa());
        this.setUrl(proc.getUrl());
        this.setIdUnidadAdministrativa(proc.getUnidadAdministrativa()!=null?proc.getUnidadAdministrativa().getId():null);
		this.setResponsable(proc.getResponsable());

        //VUDS
        this.setIdOrganResolutori(proc.getOrganResolutori()!=null?proc.getOrganResolutori().getId():null);
        this.setCodigoEstandarIniciacion(proc.getIniciacion()!=null?proc.getIniciacion().getCodigoEstandar():"");
        this.setIndicador(proc.getIndicador());
        this.setVentanillaUnica(proc.getVentanillaUnica());
        this.setTaxa(proc.getTaxa());

         //Relleno el id de los docsInformativos
		final List<Documento> docs = proc.getDocumentos();
		List<Long> idDocs = null;
		if(docs!=null && !docs.isEmpty()){
			idDocs = new ArrayList<Long>();
			for(final Documento documento : docs){
				if(documento !=null){
					idDocs.add(documento.getId());
				}
			}
		}
		this.setIdsDocsInfor(idDocs !=null ? idDocs.toArray(new Long[0]) : null);

		//Relleno los campos con sus Codigos Estandar
		final List<String> materias = new ArrayList<String>();
        if(proc.getMaterias()!=null){
		for(final Materia materia : (Collection<Materia>)proc.getMaterias()){
			materias.add(materia.getCodigoEstandar());
		}
        }
		this.setCodigoEstandarMaterias(materias.toArray(new String[0]));
		
		final List<String> hechos = new ArrayList<String>();
        if(proc.getHechosVitalesProcedimientos()!=null){
		for(final HechoVitalProcedimiento hechoProc : (Collection<HechoVitalProcedimiento>)proc.getHechosVitalesProcedimientos()){
			hechos.add(hechoProc.getHechoVital().getCodigoEstandar());
		}
        }
		this.setCodigoEstandarHV(hechos.toArray(new String[0]));
		
		this.setUrlRemota(establecerIdEnUrl(proc.getId().toString(), obtenerUrl(URL_PROC)));
		
        //Relleno las traducciones
		final List<TraduccionProcedimientoTransferible> traducciones = new ArrayList<TraduccionProcedimientoTransferible>(); 
		for (final String idioma : (Collection<String>)proc.getLangs()){
            final TraduccionProcedimiento traduccion = (TraduccionProcedimiento)proc.getTraduccion(idioma);
            if(traduccion!=null){
                final TraduccionProcedimientoTransferible temp =  new TraduccionProcedimientoTransferible();
                temp.setNombre(traduccion.getNombre());
                temp.setResumen(traduccion.getResumen());
                temp.setDestinatarios(traduccion.getDestinatarios());
                temp.setRequisitos(traduccion.getRequisitos());
                temp.setPlazos(traduccion.getPlazos());
                temp.setSilencio(traduccion.getSilencio());
                temp.setRecursos(traduccion.getRecursos());
                temp.setObservaciones(traduccion.getObservaciones());
                temp.setLugar(traduccion.getLugar());

                temp.setCodigoEstandarIdioma(idioma);

                //VUDS
                temp.setResultat(traduccion.getResultat());
                temp.setResolucion(traduccion.getResolucion());
                temp.setNotificacion(traduccion.getNotificacion());
                traducciones.add(temp);
            }
        }
        this.setTraducciones(traducciones.toArray(new TraduccionProcedimientoTransferible[0]));
    }
    

    public static ProcedimientoTransferible generar(ProcedimientoLocal proc){
    	ProcedimientoTransferible procT = new ProcedimientoTransferible();
    	if(proc!=null){
    		procT.rellenar(proc);
    	}
    	return procT;
    }
	
    @Override
	public String toString() {
		return "ProcedimientoTransferible [id=" + id + ", signatura="
				+ signatura + ", fechaCaducidad=" + fechaCaducidad
				+ ", fechaPublicacion=" + fechaPublicacion
				+ ", fechaActualizacion=" + fechaActualizacion + ", tramite="
				+ tramite + ", version=" + version + ", validacion="
				+ validacion + ", info=" + info + ", idUnidadAdministrativa="
				+ idUnidadAdministrativa + ", codigoEstandarMaterias="
				+ Arrays.toString(codigoEstandarMaterias)
				+ ", codigoEstandarHV=" + Arrays.toString(codigoEstandarHV)
				+ ", urlRemota=" + urlRemota + ", responsable=" + responsable
				+ ", traducciones=" + Arrays.toString(traducciones) + "]";
	}

    
    
}
