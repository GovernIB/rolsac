package org.ibit.rol.sac.model;

import java.util.*;

import org.ibit.rol.sac.model.ws.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servicio remoto.
 * @author slromero
 *
 */
public class ServicioRemoto extends Servicio implements Remoto{

    protected static Log log = LogFactory.getLog(ServicioRemoto.class);

    private String paramName;
    private String paramValue;

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

	public void rellenear(ServicioTransferible procTransferible){
    	//Relleno los campos
		this.setIdExterno(procTransferible.getId());
        log.debug(procTransferible.getId());
		this.setFechaDespublicacion(procTransferible.getFechaDespublicacion());
		this.setFechaPublicacion(procTransferible.getFechaPublicacion());
    	this.setFechaActualizacion(procTransferible.getFechaActualizacion());
		this.setValidacion(procTransferible.getValidacion());
        this.setUrlRemota(procTransferible.getUrlRemota());
        this.setResponsable(procTransferible.getResponsable());
        this.setTasaUrl(procTransferible.getTasaUrl());
       
        //Relleno las traducciones
		final Map<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
        if (procTransferible.getTraducciones() != null) {
            for (final TraduccionServicioTransferible traduccion : procTransferible.getTraducciones()){
                if (traduccion != null) {
                    final TraduccionServicio temp =  new TraduccionServicio();
                    temp.setNombre(traduccion.getNombre());
                    temp.setObjeto(traduccion.getObjeto());
                    temp.setDestinatarios(traduccion.getDestinatarios());
                    temp.setRequisitos(traduccion.getRequisitos());
                    temp.setObservaciones(traduccion.getObservaciones());
                    traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                }
            }
        }
        this.setTraduccionMap(traducciones);
    }
    
    public static ServicioRemoto generar(ServicioTransferible procT){
    	ServicioRemoto proc =  new ServicioRemoto();
    	if(procT!=null){
    		proc.rellenear(procT);
    	}
    	return proc;
    }


     public DocumentoServicio rellenarDocumento(final DocumentoTransferible documentoTransferible){
    	 DocumentoServicio documento =  new DocumentoServicio();

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
    
     public String getParamValue() {
         return paramValue;
     }

     public void setParamValue(String paramValue) {
         this.paramValue = paramValue;
     }

	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}
