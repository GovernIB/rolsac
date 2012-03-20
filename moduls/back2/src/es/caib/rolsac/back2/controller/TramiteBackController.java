package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.remote.vuds.ActualizacionVudsException;
import org.ibit.rol.sac.persistence.remote.vuds.ValidateVudsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.utils.DateUtils;

@Controller
@RequestMapping("/tramit/")
public class TramiteBackController {

	private static Log log = LogFactory.getLog(TramiteBackController.class);	
	private MessageSource messageSource = null;
	
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
        
    @RequestMapping(value = "/tramite.do", method = GET)
    public void obtenerTramite(HttpServletRequest request, HttpServletResponse response) {}    
    
    @RequestMapping(value = "/carregarTramit.do", method = POST)
    public @ResponseBody Map<String, Object> recuperaDetall(HttpSession session, HttpServletRequest request) {    
    	    	
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
    	
    	Map<String, Object> resultats = new HashMap<String, Object>();
    	Long idTramite = new Long(request.getParameter("id"));
    	
    	try {    		    	
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> idiomas = idiomaDelegate.listarLenguajes();			
    		Tramite tramite = DelegateUtil.getTramiteDelegate().obtenerTramite(idTramite);
    		
    		ProcedimientoLocal procedimiento = tramite.getProcedimiento();
    		resultats.put("idiomas", idiomas);
    		resultats.put("idTramit", tramite.getId());
    		resultats.put("id_tramit_actual", tramite.getId());
    		resultats.put("id_procediment_tramit", procedimiento.getId());
    		resultats.put("nom_procediment_tramit", ((TraduccionProcedimiento) procedimiento.getTraduccion(request.getLocale().getLanguage())).getNombre() );
    		resultats.put("tramit_item_data_actualitzacio", DateUtils.formatDate(tramite.getDataActualitzacio()));
    		resultats.put("tramit_item_data_publicacio", DateUtils.formatDate(tramite.getDataPublicacio()));
    		resultats.put("tramit_item_data_caducitat", DateUtils.formatDate(tramite.getDataCaducitat()));
    		resultats.put("item_moment_tramit", tramite.getFase());
    		resultats.put("item_validacio_tramit", tramite.getValidacio());
    		resultats.put("item_url_tramit", tramite.getUrlExterna());
    		resultats.put("item_tramite_tramit", tramite.getIdTraTel());
    		resultats.put("item_version_tramit", tramite.getVersio());
    		resultats.put("item_codivuds_tramit", tramite.getCodiVuds());
    		resultats.put("tramit_item_data_vuds", tramite.getDataActualitzacioVuds());    		
    		resultats.put("item_finestreta_unica", procedimiento.getVentanillaUnica());
    		resultats.put("item_taxes", procedimiento.getTaxa());
    		resultats.put("tramits_item_organ_id", tramite.getOrganCompetent().getId());    		    		
    		
			// Idiomas
    		for ( String idioma : idiomas ) {
    			resultats.put(idioma, (TraduccionTramite) tramite.getTraduccion(idioma));
    			//TraduccionUA traduccionUA = ((TraduccionUA) tramite.getOrganCompetent().getTraduccion(idioma));
    			String nombreUA = tramite.getOrganCompetent().getNombreUnidadAdministrativa(idioma);    			
    			resultats.put("ua_" + idioma, nombreUA);
    		}
    	
    		// Documentos
    		Set<DocumentTramit> listaDocumentos = tramite.getDocsInformatius();
    		Set<DocumentTramit> listaFormularios = tramite.getFormularios();
    		Set<Taxa> listaTasas = tramite.getTaxes();
    		
    		// Documentos relacionados
    		List<IdNomDTO> listaDocumentosDTO = null;
    		if ( listaDocumentos != null ) {
    			
    			listaDocumentosDTO = new ArrayList<IdNomDTO>();
    			
	    		for (DocumentTramit document : listaDocumentos) {
	    			String nombreDocumento = ((TraduccionDocumento) document.getTraduccion(request.getLocale().getLanguage())).getTitulo();
	    			listaDocumentosDTO.add( new IdNomDTO( document.getId(), nombreDocumento));
	    		}   
    		}    		
    		resultats.put("documentosTramite", listaDocumentosDTO);
    		// Fin documentos relacionados
    		
    		// Formularios relacionados
    		List<IdNomDTO> listaFormulariosDTO = null;
    		if ( listaFormularios != null ) {
    			
    			listaFormulariosDTO = new ArrayList<IdNomDTO>();
    			
	    		for (DocumentTramit formulari : listaFormularios ) { 
	    			String nombreFormulario = ((TraduccionDocumento) formulari.getTraduccion(request.getLocale().getLanguage())).getTitulo();
	    			listaFormulariosDTO.add( new IdNomDTO(formulari.getId(), nombreFormulario) );
	    		}     
    		}
    		
    		resultats.put("formulariosTramite", listaFormulariosDTO);
    		// Fin formularios relacionados
    		
    		// Tasas relacionadas
    		List<IdNomDTO> listaTasasDTO = null;
    		if ( listaTasas != null ) {
    			
    			listaTasasDTO = new ArrayList<IdNomDTO>();
    			
    			for (Taxa tasa : listaTasas) {
    				String codificacionTasa = ((TraduccionTaxa) tasa.getTraduccion(request.getLocale().getLanguage())).getCodificacio();
    				listaTasasDTO.add( new IdNomDTO(tasa.getId(), codificacionTasa));
    			}    		
    		}
    		
    		resultats.put("tasasTramite", listaTasasDTO);
    		// Fin tasas relacionadas
    		    		
    	} catch (DelegateException dEx) {
			logException(log, dEx);

			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}            
    	}
    	
    	return resultats;    	
    }
    
	@RequestMapping(value = "/guardarTramit.do", method = POST)
    //public ResponseEntity<String> guardarTramite(HttpSession session, HttpServletRequest request) {
	public @ResponseBody ResponseEntity<String> guardarTramite(HttpSession session, HttpServletRequest request) {		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

		String error = null;
		IdNomDTO result = null;
		Tramite tramite = null;
		Long idProcedimiento = new Long( request.getParameter("id_procediment_tramit") );
		
		try {
			
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal procedimiento = procedimientoDelegate.obtenerProcedimiento(idProcedimiento);
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
			
			String idTramite = request.getParameter("id_tramit_actual"); 
			boolean edicion = !"".equals(idTramite);
			
			if ( !edicion ) {
				if ( !tramiteDelegate.autorizaCrearTramite(idProcedimiento) )
					throw new SecurityException("Avís: no té permis per a crear el tràmit");
				
				tramite = new Tramite();
				tramite.setOperativa(Tramite.Operativa.CREA);
				tramite.setOrden(0L);
				
			} else {
				
				tramite = tramiteDelegate.obtenerTramite( new Long(idTramite) );

				if ( !tramiteDelegate.autorizaModificarTramite( tramite.getId() ) )				
					throw new SecurityException("Avís: no té permis per a crear el tràmit");
				
				tramite.setOperativa(Tramite.Operativa.MODIFICA);
			}
			
			String version = request.getParameter("item_version_tramit");
			tramite.setVersio( !"".equals(version) ? Integer.parseInt(version) : 0 );
			tramite.setUrlExterna( request.getParameter("item_url_tramit"));
			tramite.setIdTraTel( request.getParameter("item_tramite_tramit"));
			
			//1 - Inicialización
			//2 - Instrucción
			//3 - Finalización
			tramite.setFase( Integer.parseInt(request.getParameter("item_moment_tramit")) );
			
			//1 - Pública
			//2 - Interna
			//3 - Reserva
			tramite.setValidacio( new Long(request.getParameter("item_validacio_tramit")) );
			
			// Rellenar los campos
			Date fechaPublicacion = DateUtils.parseDate(request.getParameter("tramit_item_data_publicacio"));
			tramite.setDataPublicacio(fechaPublicacion);
			
			Date fechaActualizacion = DateUtils.parseDate(request.getParameter("tramit_item_data_actualitzacio"));
			tramite.setDataActualitzacio(fechaActualizacion);
			
			Date fechaCaducidad = DateUtils.parseDate(request.getParameter("tramit_item_data_caducitat"));
			tramite.setDataCaducitat(fechaCaducidad);
			
			Date fechaVUDS = DateUtils.parseDate(request.getParameter("tramit_item_data_vuds"));
			String fechaActualizacionVUDS  = fechaVUDS != null ? new SimpleDateFormat("dd/MM/yyyy").format(fechaVUDS) : "";			
			tramite.setDataActualitzacioVuds( fechaActualizacionVUDS );			
			
			tramite.setCodiVuds(request.getParameter("item_id_codivuds_tramit"));						 
						
			Scanner scanner = new Scanner( request.getParameter("tramits_item_organ_id") );

			if ( scanner.hasNextLong() ) {
				UnidadAdministrativa unidadAdministrativa = DelegateUtil.getUADelegate().obtenerUnidadAdministrativa( scanner.nextLong() );
				tramite.setOrganCompetent( unidadAdministrativa );				
			}
			
			// Idiomas
			TraduccionTramite traduccionTramite;			
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			Map traducciones = new HashMap(langs.size());			
			
			for (String lang: langs) {
				
				traduccionTramite = (TraduccionTramite) tramite.getTraduccion(lang);
				
				if ( traduccionTramite == null )
					traduccionTramite = new TraduccionTramite();
				
				traduccionTramite.setNombre( request.getParameter("item_nom_tramit_" + lang) );
				traduccionTramite.setDescripcion( request.getParameter("item_descripcio_tramit_" + lang));
				traduccionTramite.setRequisits( request.getParameter("item_requisits_tramit_" + lang));
				traduccionTramite.setDocumentacion( request.getParameter("item_documentacio_tramit_" + lang));				
				traduccionTramite.setPlazos( request.getParameter("item_termini_tramit_" + lang));
				
				//Este campo no existe en la tabla pero se deja por si se añade en futuras implementaciones)
				//traduccionTramite.setObservaciones( request.getParameter("item_observacions_tramit_" + lang) );
				traduccionTramite.setLugar( request.getParameter("item_lloc_tramit_" + lang));
								
				traducciones.put(lang, traduccionTramite);				
			}
			
			tramite.setTraduccionMap(traducciones);					
			// Fin idiomas
			
			String idOrganCompetent = request.getParameter("tramits_item_organ_id");			
			tramite.setProcedimiento( procedimiento );
			
			//Si no se recibe ningún valor se asigna por defecto la actual			
			tramiteDelegate.grabarTramite(tramite, !"".equals(idOrganCompetent) ? new Long(idOrganCompetent) : procedimiento.getUnidadAdministrativa().getId() );
			
        	if ( !edicion ) {
        		request.setAttribute("alert", "confirmacion.alta");
        		procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);
        	} else {
        		request.setAttribute("alert", "confirmacion.modificacion");        		
         	   
            	Tramite tramiteOld = tramiteDelegate.obtenerTramite( new Long(request.getParameter("id_tramit_actual")) );
            	
        		//Guardar tasas
        		String tasasTramite = request.getParameter("taxesTramit");
        		        		
        		if (!"".equals(tasasTramite)) {
        			
        			Set<Taxa> listaTasasOld = tramiteOld.getTaxes();
        			List<Long> listaTasasBorrar = new ArrayList<Long>();
        			Set<Taxa> tasasNuevas = new HashSet<Taxa>();
        			String[] codigosTasasNuevas = tasasTramite.split(",");
        			
        			for ( int i = 0; i < codigosTasasNuevas.length; i++ ) {        				
        				for ( Taxa tasa : listaTasasOld ) {
        					if ( !"".equals(codigosTasasNuevas[i]) && tasa.getId()
        							.equals(Long.valueOf(codigosTasasNuevas[i])) ) {
        						
        						tasasNuevas.add( tasa );
        						codigosTasasNuevas[i] = null;
        						
        						break;
        					}
        				}
        			}
        			
        			//Eliminar los que se han quitado de la lista
        			for ( Taxa tasa : listaTasasOld ) {
        				if ( !tasasNuevas.contains(tasa) )
        					listaTasasBorrar.add(tasa.getId());        				
        			}
        			
        			for ( Long id : listaTasasBorrar ) 
        				DelegateUtil.getTramiteDelegate().borrarTaxa(id);
        			
        			//Crear los nuevos
        			if (!"".equals(codigosTasasNuevas)) {
        				for (String codigoTasa : codigosTasasNuevas ) {
        					if ( codigoTasa != null ) {
        						for ( Taxa tasa : tramiteOld.getTaxes() ) {
        							if ( !tasasNuevas.contains(tasa) )
        								tasasNuevas.add(tasa);
        						}
        					}
        				}
        			}
        			
        			// Actualizamos el orden de la lista de tasas
        			HashMap<String, String[]> actualizadorTasas = new HashMap<String, String[]>();
        			
        			for ( Taxa tasa : tasasNuevas ) {
        				String[] orden = { request.getParameter("taxesTramit_orden_" + tasa.getId() ) }; 
        				actualizadorTasas.put("orden_taxa" + tasa.getId(), orden);
        			}
        			
        			DelegateUtil.getTramiteDelegate().actualizarOrdenTasas(actualizadorTasas, tramite.getId());
        			//tramite.setTaxes(tasasNuevas);        			
        		}           	        		
        		
            	//Guardar documentos y formularios
            	String formulariosTramite = request.getParameter("formularisTramit");
            	String documentosTramite = request.getParameter("documentsTramit");
            	String separador = (!"".equals(formulariosTramite) && !"".equals(documentosTramite)  ? "," : ""); 
            	
            	documentosTramite = documentosTramite + separador + formulariosTramite;

        		Set<DocumentTramit> listaDocumentosOld = tramiteOld.getDocsInformatius();
        		Set<DocumentTramit> listaFormulariosOld = tramiteOld.getFormularios();
            	
        		//Combinamos las dos listas para hacerlo todo en una misma operación
        		for (DocumentTramit documentTramit : listaFormulariosOld)
        			listaDocumentosOld.add(documentTramit);
        		
            	if ( !"".equals(documentosTramite) ) {
            		
            		Set<Long> listaDocumentosBorrar = new HashSet<Long>();
            		Set<DocumentTramit> documentosNuevos = new HashSet<DocumentTramit>();
            		String[] codigosDocumentosNuevos = documentosTramite.split(",");
            		            		        	
            		if ( edicion ) {
            			
            			for ( int i = 0; i < codigosDocumentosNuevos.length; i++ ) {
            				for ( DocumentTramit documentoTramite : listaDocumentosOld ) {
            					
            					if ( !"".equals(codigosDocumentosNuevos[i]) && documentoTramite.getId().equals(Long.valueOf(codigosDocumentosNuevos[i])) ) {
            						documentosNuevos.add(documentoTramite);
            						codigosDocumentosNuevos[i] = null;
            						
            						break;
            					}        					
            				}
            			}
            			
            			//Eliminar los que se han quitado de la lista
            			for ( DocumentTramit documentTramit : listaDocumentosOld ) {
            				if (!documentosNuevos.contains(documentTramit))
            					listaDocumentosBorrar.add(documentTramit.getId());
            			}
            			            		            		
            			for (Long id : listaDocumentosBorrar ) {
            				tramite.removeDocument( tramiteDelegate.obtenirDocument(id) );
            				tramiteDelegate.borrarDocument(id);
            			}
            		}
            		
            		//Crear los nuevos documentos/formularios
            		for ( String codigoDocumento : codigosDocumentosNuevos ) {
            			
            			if ( codigoDocumento != null ) {
            				for ( DocumentTramit documentTramit : listaDocumentosOld ) {
            					if ( !documentosNuevos.contains( documentTramit ) )
            						documentosNuevos.add(documentTramit);
            				}
            			}        			
            		}
            		            		
            		//Actualizar el orden de la lista de documentos
            		HashMap<String,String[]> actualizadorDocs = new HashMap<String, String[]>();
            		
            		for ( DocumentTramit documentTramit : documentosNuevos ) {
            			Long idDoc = documentTramit.getId();
            			String ordenDocumento = request.getParameter("formularisTramit_orden_" + idDoc) ;
            			
            			if ( ordenDocumento == null ) 
            				ordenDocumento = request.getParameter("documentsTramit_orden_" + idDoc);
            			
            			String[] orden = { ordenDocumento };
            			actualizadorDocs.put("orden_doc" + idDoc, orden);
            		}
            		
            		tramiteDelegate.actualizarOrdenDocs( actualizadorDocs, new Long(idTramite) );
            		
            	} else {
            		
        			for (DocumentTramit documentTramit : listaDocumentosOld ) {        				
        				tramite.removeDocument(documentTramit );
        				tramiteDelegate.borrarDocument(documentTramit.getId() );
        			}        		
            	}         		
        	}
        	
			if ( !edicion ) 
        		procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);			 
        	
		} catch (ValidateVudsException e) {
	    	
	    	String camps="";
	    	
	    	for(String camp: e.getCampsSenseValidar()) 
	    		camps += "\\n-" + camp;
	    		    		    	
	    	error = messageSource.getMessage("error.validacio.tramit" + ".Camps sense validar: " + camps, null, request.getLocale());
	    	result = new IdNomDTO(-2l, error);
	    	log.error(ExceptionUtils.getStackTrace(e));
	    	
	    } catch (ActualizacionVudsException e) {
	    	
	    	try {
		    	//Ha fallado la actualización pero el trámite ha sido creado correctamente, así que se añade al procedimiento
		    	ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();	    	
		    	procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);
		
		    	error = messageSource.getMessage("error.enviament.tramit", null, request.getLocale());
		    	result = new IdNomDTO(-2l, error);
		    	log.error(ExceptionUtils.getStackTrace(e));
		    	
				request.setAttribute("idSelect", tramite.getId());
				
	    	} catch (DelegateException dEx) {

	            if (dEx.isSecurityException()) {
	                error = messageSource.getMessage("error.permisos", null, request.getLocale());
	                result = new IdNomDTO(-1l, error);
	            } else {
	                error = messageSource.getMessage("error.altres", null, request.getLocale());
	                result = new IdNomDTO(-2l, error);
	                log.error(ExceptionUtils.getStackTrace(dEx)); 
	            }		

	    	}
	    	
        } catch (DelegateException dEx) {
        	
            if (dEx.isSecurityException()) {
                error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomDTO(-1l, error);
            } else {
                error = messageSource.getMessage("error.altres", null, request.getLocale());
                result = new IdNomDTO(-2l, error);
                log.error(ExceptionUtils.getStackTrace(dEx)); 
            }		
        
        }
        
        if (result == null) {
        	result = new IdNomDTO();
        	result.setId(tramite.getId());
        	result.setNom( ((TraduccionTramite) tramite.getTraduccion("ca")).getNombre() );        
        }
        
		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/esborrarTramit.do", method = POST)	
	public @ResponseBody IdNomDTO esborrarTramit(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		
		Long idTramite = new Long(request.getParameter("id"));
		Long idProcedimiento = new Long( request.getParameter("idProcediment") );
		
		try {
			
			//TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();			
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
			
			//Quita el tramite de la lista
			procedimientoDelegate.eliminarTramite(idTramite, idProcedimiento);  			
			tramiteDelegate.borrarTramite(idTramite);

			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				logException(log, dEx);
			}
		}

		return resultatStatus;
	}	
}