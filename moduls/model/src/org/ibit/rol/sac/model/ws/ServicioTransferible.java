package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.ibit.rol.sac.model.*;

/**
 * Clase que representa la información a transferir de un Servicio(SERMAD)
 */
public class ServicioTransferible extends ActuacionTransferible implements Serializable {

	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;

	public static final String URL_PROC = "es.caib.rolsac.model.ws.urlServicio";
	
	private Long id;
    private String objeto;
    
    private Date fechaDespublicacion;
    private Date fechaPublicacion;
    private Date fechaActualizacion;
    
    private Integer validacion;
    private String info;

    private Long idOrganoResponsable;  //organ responsable
    private String[] codigoEstandarMaterias;
    private String[] codigoEstandarHV;
    private String urlRemota;
    private String responsable;

    //VUDS
    private Long idOrganInstructor;// servicioResponsable
    private String codigoEstandarIniciacion;
    private String indicador;
    private String ventanillaUnica;
    private String tasaUrl;
    private String url;
    private NormativaTransferible[] normativas;

    private TraduccionServicioTransferible[] traducciones;

    //marilen VUDS
   private Long[]  idsDocsInfor;

	public void rellenar(Servicio servicio){
    	//Relleno los campos
		this.setId(servicio.getId());
		this.setObjeto(servicio.getCodigo());
		this.setFechaDespublicacion(servicio.getFechaDespublicacion());
		this.setFechaPublicacion(servicio.getFechaPublicacion());
		this.setFechaActualizacion(servicio.getFechaActualizacion());
		this.setValidacion(servicio.getValidacion());
        this.setTasaUrl(servicio.getTasaUrl());
        this.setResponsable(servicio.getNombreResponsable());

        //VUDS
        this.setIdOrganInstructor(servicio.getOrganoInstructor()!=null?servicio.getOrganoInstructor().getId():null);
        this.setIdOrganoResponsable(servicio.getServicioResponsable()!=null?servicio.getServicioResponsable().getId():null);
        
         //Relleno el id de los docsInformativos
		final Set<DocumentoServicio> docs = servicio.getDocumentos();
		List<Long> idDocs = null;
		if(docs!=null && !docs.isEmpty()){
			idDocs = new ArrayList<Long>();
			for(final DocumentoServicio documento : docs){
				if(documento !=null){
					idDocs.add(documento.getId());
				}
			}
		}
		this.setIdsDocsInfor(idDocs !=null ? idDocs.toArray(new Long[0]) : null);

		//Relleno los campos con sus Codigos Estandar
		final List<String> materias = new ArrayList<String>();
        if(servicio.getMaterias()!=null){
		for(final Materia materia : (Collection<Materia>)servicio.getMaterias()){
			materias.add(materia.getCodigoEstandar());
		}
        }
		this.setCodigoEstandarMaterias(materias.toArray(new String[0]));
		
		final List<String> hechos = new ArrayList<String>();
        if(servicio.getHechosVitalesServicios()!=null){
		for(final HechoVitalServicio hechoProc : (Collection<HechoVitalServicio>)servicio.getHechosVitalesServicios()){
			hechos.add(hechoProc.getHechoVital().getCodigoEstandar());
		}
        }
		this.setCodigoEstandarHV(hechos.toArray(new String[0]));
		
		this.setUrlRemota(establecerIdEnUrl(servicio.getId().toString(), obtenerUrl(URL_PROC)));
		
		
		
        //Relleno las traducciones
		final List<TraduccionServicioTransferible> traducciones = new ArrayList<TraduccionServicioTransferible>(); 
		for (final String idioma : (Collection<String>)servicio.getLangs()){
            final TraduccionServicio traduccion = (TraduccionServicio)servicio.getTraduccion(idioma);
            if(traduccion!=null){
                final TraduccionServicioTransferible temp =  new TraduccionServicioTransferible();
                temp.setNombre(traduccion.getNombre());
                temp.setObjeto(traduccion.getObjeto());
                temp.setDestinatarios(traduccion.getDestinatarios());
                temp.setRequisitos(traduccion.getRequisitos());
                //#366temp.setSilencio(traduccion.getSilencio());
                temp.setObservaciones(traduccion.getObservaciones());
                
                temp.setCodigoEstandarIdioma(idioma);

                traducciones.add(temp);
            }
        }
        this.setTraducciones(traducciones.toArray(new TraduccionServicioTransferible[0]));
    }
    

    public static ServicioTransferible generar(Servicio proc){
    	ServicioTransferible procT = new ServicioTransferible();
    	if(proc!=null){
    		procT.rellenar(proc);
    	}
    	return procT;
    }
	
    @Override
	public String toString() {
		return "ServicioTransferible [id=" + id + ", objeto="
				+ objeto + ", fechaDespublicacion=" + fechaDespublicacion
				+ ", fechaPublicacion=" + fechaPublicacion
				+ ", fechaActualizacion=" + fechaActualizacion + ", validacion="
				+ validacion + ", info=" + info + ", idOrganoResponsable="
				+ idOrganoResponsable + ", codigoEstandarMaterias="
				+ Arrays.toString(codigoEstandarMaterias)
				+ ", codigoEstandarHV=" + Arrays.toString(codigoEstandarHV)
				+ ", urlRemota=" + urlRemota + ", responsable=" + responsable
				+ ", traducciones=" + Arrays.toString(traducciones) + "]";
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the objeto
	 */
	public String getObjeto() {
		return objeto;
	}


	/**
	 * @param objeto the objeto to set
	 */
	public void setObjeto(String objeto) {
		this.objeto = objeto;
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
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}


	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}


	/**
	 * @return the idOrganoResponsable
	 */
	public Long getIdOrganoResponsable() {
		return idOrganoResponsable;
	}


	/**
	 * @param idOrganoResponsable the idOrganoResponsable to set
	 */
	public void setIdOrganoResponsable(Long idOrganoResponsable) {
		this.idOrganoResponsable = idOrganoResponsable;
	}


	/**
	 * @return the codigoEstandarMaterias
	 */
	public String[] getCodigoEstandarMaterias() {
		return codigoEstandarMaterias;
	}


	/**
	 * @param codigoEstandarMaterias the codigoEstandarMaterias to set
	 */
	public void setCodigoEstandarMaterias(String[] codigoEstandarMaterias) {
		this.codigoEstandarMaterias = codigoEstandarMaterias;
	}


	/**
	 * @return the codigoEstandarHV
	 */
	public String[] getCodigoEstandarHV() {
		return codigoEstandarHV;
	}


	/**
	 * @param codigoEstandarHV the codigoEstandarHV to set
	 */
	public void setCodigoEstandarHV(String[] codigoEstandarHV) {
		this.codigoEstandarHV = codigoEstandarHV;
	}


	/**
	 * @return the urlRemota
	 */
	public String getUrlRemota() {
		return urlRemota;
	}


	/**
	 * @param urlRemota the urlRemota to set
	 */
	public void setUrlRemota(String urlRemota) {
		this.urlRemota = urlRemota;
	}


	/**
	 * @return the responsable
	 */
	public String getResponsable() {
		return responsable;
	}


	/**
	 * @param responsable the responsable to set
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}


	/**
	 * @return the idOrganInstructor
	 */
	public Long getIdOrganInstructor() {
		return idOrganInstructor;
	}


	/**
	 * @param idOrganInstructor the idOrganInstructor to set
	 */
	public void setIdOrganInstructor(Long idOrganInstructor) {
		this.idOrganInstructor = idOrganInstructor;
	}


	/**
	 * @return the codigoEstandarIniciacion
	 */
	public String getCodigoEstandarIniciacion() {
		return codigoEstandarIniciacion;
	}


	/**
	 * @param codigoEstandarIniciacion the codigoEstandarIniciacion to set
	 */
	public void setCodigoEstandarIniciacion(String codigoEstandarIniciacion) {
		this.codigoEstandarIniciacion = codigoEstandarIniciacion;
	}


	/**
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}


	/**
	 * @param indicador the indicador to set
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}


	/**
	 * @return the ventanillaUnica
	 */
	public String getVentanillaUnica() {
		return ventanillaUnica;
	}


	/**
	 * @param ventanillaUnica the ventanillaUnica to set
	 */
	public void setVentanillaUnica(String ventanillaUnica) {
		this.ventanillaUnica = ventanillaUnica;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the normativas
	 */
	public NormativaTransferible[] getNormativas() {
		return normativas;
	}


	/**
	 * @param normativas the normativas to set
	 */
	public void setNormativas(NormativaTransferible[] normativas) {
		this.normativas = normativas;
	}


	/**
	 * @return the traducciones
	 */
	public TraduccionServicioTransferible[] getTraducciones() {
		return traducciones;
	}


	/**
	 * @param traducciones the traducciones to set
	 */
	public void setTraducciones(TraduccionServicioTransferible[] traducciones) {
		this.traducciones = traducciones;
	}


	/**
	 * @return the idsDocsInfor
	 */
	public Long[] getIdsDocsInfor() {
		return idsDocsInfor;
	}


	/**
	 * @param idsDocsInfor the idsDocsInfor to set
	 */
	public void setIdsDocsInfor(Long[] idsDocsInfor) {
		this.idsDocsInfor = idsDocsInfor;
	}

    
    
}
