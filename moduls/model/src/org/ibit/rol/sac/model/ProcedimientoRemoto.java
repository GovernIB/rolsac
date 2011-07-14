package org.ibit.rol.sac.model;

import java.util.*;

import org.ibit.rol.sac.model.ws.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 20-jun-2007
 * Time: 11:19:57
 * Bean que representa un procedimiento remoto(creado nuevo para PORMAD)
 */
public class ProcedimientoRemoto extends ProcedimientoLocal implements Remoto{

    protected static Log log = LogFactory.getLog(ProcedimientoRemoto.class);

	private static final long serialVersionUID = 1L;
	
	private AdministracionRemota administracionRemota;
    private Long idExterno;
    private String urlRemota;
    private String responsable;

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }

    public AdministracionRemota getAdministracionRemota() {
        return administracionRemota;
    }

    public void setAdministracionRemota(AdministracionRemota administracionRemota) {
        this.administracionRemota = administracionRemota;
    }

    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }
    
    public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public void rellenear(ProcedimientoTransferible procTransferible){
    	//Relleno los campos
		this.setIdExterno(procTransferible.getId());
        log.debug(procTransferible.getId());
		this.setSignatura(procTransferible.getSignatura());
		this.setFechaCaducidad(procTransferible.getFechaCaducidad());
		this.setFechaPublicacion(procTransferible.getFechaPublicacion());
    	this.setInfo(procTransferible.getInfo());
		this.setFechaActualizacion(procTransferible.getFechaActualizacion());
		this.setTramite(procTransferible.getTramite());
		this.setVersion(procTransferible.getVersion());
		this.setValidacion(procTransferible.getValidacion());
        this.setUrlRemota(procTransferible.getUrlRemota());
        this.setResponsable(procTransferible.getResponsable());
        

        //VUDS
        this.setIndicador(procTransferible.getIndicador());
        this.setVentanillaUnica(procTransferible.getVentanillaUnica());
        this.setTaxa(procTransferible.getTaxa());
        this.setUrl(procTransferible.getUrl());

        //Relleno las traducciones
		final Map<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
        if (procTransferible.getTraducciones() != null) {
            for (final TraduccionProcedimientoTransferible traduccion : procTransferible.getTraducciones()){
                if (traduccion != null) {
                    final TraduccionProcedimiento temp =  new TraduccionProcedimientoLocal();
                    temp.setNombre(traduccion.getNombre());
                    temp.setResumen(traduccion.getResumen());
                    temp.setDestinatarios(traduccion.getDestinatarios());
                    temp.setRequisitos(traduccion.getRequisitos());
                    temp.setPlazos(traduccion.getPlazos());
                    temp.setSilencio(traduccion.getSilencio());
                    temp.setRecursos(traduccion.getRecursos());
                    temp.setObservaciones(traduccion.getObservaciones());
                    temp.setLugar(traduccion.getLugar());

                    //VUDS
                    temp.setResultat(traduccion.getResultat());
                    temp.setResolucion(traduccion.getResolucion());
                    temp.setNotificacion(traduccion.getNotificacion());
                    traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                }
            }
        }
        this.setTraduccionMap(traducciones);
    }
    
    public static ProcedimientoRemoto generar(ProcedimientoTransferible procT){
    	ProcedimientoRemoto proc =  new ProcedimientoRemoto();
    	if(procT!=null){
    		proc.rellenear(procT);
    	}
    	return proc;
    }


     public Documento rellenarDocumento(final DocumentoTransferible documentoTransferible){
    	Documento documento =  new Documento();

      	documento.setId(documentoTransferible.getId());
      	documento.setOrden(documentoTransferible.getOrden());


  		 //Relleno las traducciones
  		final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
  		if (documentoTransferible.getTraducciones() != null) {
              for (final TraduccionDocumentoTransferible traduccion : documentoTransferible.getTraducciones()){
                  if (traduccion != null) {
                      final TraduccionDocumento temp =  new TraduccionDocumento();
                      temp.setDescripcion(traduccion.getDescripcion());
                      temp.setTitulo(traduccion.getTitulo());
                      traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                      temp.setArchivo(Archivo.generar(traduccion.getArchivoTransferible()));
                  }
              }
          }
  		documento.setTraduccionMap(traducciones);

  		return documento;
  	}
    
}
