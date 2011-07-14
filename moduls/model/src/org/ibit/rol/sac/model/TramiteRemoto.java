package org.ibit.rol.sac.model;

import java.util.HashMap;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.ws.DocumentTramitTransferible;
import org.ibit.rol.sac.model.ws.TaxaTransferible;
import org.ibit.rol.sac.model.ws.TraduccionDocumentoTransferible;
import org.ibit.rol.sac.model.ws.TraduccionTaxaTransferible;
import org.ibit.rol.sac.model.ws.TraduccionTramiteTransferible;
import org.ibit.rol.sac.model.ws.TramiteTransferible;

/**
 * User: mgonzalez
 * Date: 15-jul-2010
 * Time: 13:55:44
 * Bean que representa un tramite remoto(creado nuevo para VUDS)
 */
public class TramiteRemoto  extends Tramite implements Remoto{

    private Long idExterno;
    private AdministracionRemota administracionRemota;
    private String urlRemota;
    
    protected static Log log = LogFactory.getLog(TramiteRemoto.class);
    
    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

    public AdministracionRemota getAdministracionRemota() {
        return administracionRemota;
    }

    public void setAdministracionRemota(AdministracionRemota administracionRemota) {
        this.administracionRemota = administracionRemota;
    }

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }


    public void rellenar(final TramiteTransferible tramiteTransferible){
    	log.debug("Relleno Tramite Remoto: "+tramiteTransferible.getId());
        this.setIdExterno(tramiteTransferible.getId());
        this.setCodiVuds(tramiteTransferible.getCodiVuds());
        this.setDescCodiVuds(tramiteTransferible.getDescCodiVuds());
        this.setFase(tramiteTransferible.getFase());
        this.setValidacio(tramiteTransferible.getValidacio());
        this.setDataCaducitat(tramiteTransferible.getDataCaducitat());
        this.setDataPublicacio(tramiteTransferible.getDataPublicacio());
        this.setDataActualitzacio(tramiteTransferible.getDataActualitzacio());
        this.setIdTraTel(tramiteTransferible.getIdTraTel());
        this.setVersio(tramiteTransferible.getVersio());
        this.setUrlExterna(tramiteTransferible.getUrlExterna());
        
        //Relleno las taxas
        if(tramiteTransferible.getTaxes()!=null){
            log.debug("Recojo taxas: "+tramiteTransferible.getTaxes().length);
			TaxaTransferible[] taxaTransferible = tramiteTransferible.getTaxes();
			
			Set<Taxa> listaTaxes = this.getTaxes();
			for (Taxa taxa : listaTaxes) {
				taxa.setTramit(null);
			}
			this.getTaxes().clear();
			for (TaxaTransferible taxaT : taxaTransferible) {
				if(taxaT!=null){
					Taxa taxa = rellenarTaxa(taxaT);
					taxa.setTramit(this);
					this.getTaxes().add(taxa);
				}
			}	
			
		}
        
        //Relleno los documentos informativos
        
        if(tramiteTransferible.getDocumentInformatiu()!=null){
        	log.debug("Recojo Documentos Informativos: "+tramiteTransferible.getDocumentInformatiu().length);
            DocumentTramitTransferible[] documentInformatiuTransferible = tramiteTransferible.getDocumentInformatiu();
			
			Set<DocumentTramit> listaDocumentosInf = this.getDocsInformatius();
			for (DocumentTramit documentInf : listaDocumentosInf) {
				documentInf.setTramit(null);
			}
			this.getDocsInformatius().clear();
            for (DocumentTramitTransferible docT : documentInformatiuTransferible) {
				if(docT!=null){
					DocumentTramit documentInformatiu = rellenarDocumentTramit(docT);
					documentInformatiu.setTramit(this);
					this.docsInformatius.add(documentInformatiu);
				}	
			}	
			
		}
        
        //Relleno los Formularios
        
        if(tramiteTransferible.getFormularios()!=null){
        	log.debug("Recojo Formularios: "+tramiteTransferible.getFormularios().length);

            DocumentTramitTransferible[] formularioTransferible = tramiteTransferible.getFormularios();
			Set<DocumentTramit> listaFormularios = this.getFormularios();

			for (DocumentTramit formulario : listaFormularios) {
				formulario.setTramit(null);
			}
            this.getFormularios().clear();
            for (DocumentTramitTransferible formT : formularioTransferible) {
				if(formT!=null){
					DocumentTramit formulario = rellenarDocumentTramit(formT);
					formulario.setTramit(this);
					this.formularios.add(formulario);
				}
			}	
		
		}
        
		//Relleno las traducciones del tramite
		final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
		if (tramiteTransferible.getTraducciones() != null) {
            for (final TraduccionTramiteTransferible traduccion : tramiteTransferible.getTraducciones()){
                if (traduccion != null) {
                    final TraduccionTramite temp =  new TraduccionTramite();
                    temp.setNombre(traduccion.getNombre());
                    temp.setDescripcion(traduccion.getDescripcion());
                    temp.setDocumentacion(traduccion.getDocumentacion());
                    temp.setRequisits(traduccion.getRequisits());
                    temp.setPlazos(traduccion.getPlazos());
                    temp.setObservaciones(traduccion.getObservaciones());
                    temp.setLugar(traduccion.getLugar());
                    traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                }
            }
        }
        this.setTraduccionMap(traducciones);
	}    
    
     public static TramiteRemoto generar(TramiteTransferible tramT){
    	TramiteRemoto tram =  new TramiteRemoto();
    	if(tramT!=null){
    		tram.rellenar(tramT);
    	}
    	return tram;
    }
     
     public Taxa rellenarTaxa(final TaxaTransferible taxaTransferible){
     	Taxa taxa =  new Taxa();
     	
     	taxa.setId(taxaTransferible.getId());
     	taxa.setOrden(taxaTransferible.getOrden());
     	
 		 //Relleno las traducciones
 		final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
 		if (taxaTransferible.getTraducciones() != null) {
             for (final TraduccionTaxaTransferible traduccion : taxaTransferible.getTraducciones()){
                 if (traduccion != null) {
                     final TraduccionTaxa temp =  new TraduccionTaxa();
                     temp.setCodificacio(traduccion.getCodificacio());
                     temp.setDescripcio(traduccion.getDescripcio());
                     temp.setFormaPagament(traduccion.getFormaPagament());
                     traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                 }
             }
         }
 		taxa.setTraduccionMap(traducciones);

 		return taxa;
 	}   
     
     public DocumentTramit rellenarDocumentTramit(final DocumentTramitTransferible documentTramitTransferible){
    	DocumentTramit documentTramit =  new DocumentTramit();
      	
      	documentTramit.setId(documentTramitTransferible.getId());
      	documentTramit.setOrden(documentTramitTransferible.getOrden());
      	documentTramit.setTipus(documentTramitTransferible.getTipus());
      	
  		 //Relleno las traducciones
  		final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
  		if (documentTramitTransferible.getTraducciones() != null) {
              for (final TraduccionDocumentoTransferible traduccion : documentTramitTransferible.getTraducciones()){
                  if (traduccion != null) {
                      final TraduccionDocumento temp =  new TraduccionDocumento();
                      temp.setDescripcion(traduccion.getDescripcion());
                      temp.setTitulo(traduccion.getTitulo());
                      traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                      temp.setArchivo(Archivo.generar(traduccion.getArchivoTransferible()));
                  }
              }
          }
  		documentTramit.setTraduccionMap(traducciones);
  		  		
  		return documentTramit;
  	}


}
