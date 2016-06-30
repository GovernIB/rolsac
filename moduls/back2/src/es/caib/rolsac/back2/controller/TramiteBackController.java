package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionCatalegDocuments;
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

import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.DateUtils;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/tramit/")
public class TramiteBackController {
	
	private final String IDIOMA_ORIGEN_TRADUCTOR = "ca";
	
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
    		resultats.put("nom_procediment_tramit", ((TraduccionProcedimiento) procedimiento.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto())).getNombre());
    		resultats.put("tramit_item_data_actualitzacio", DateUtils.formatDate(tramite.getDataActualitzacio()));
    		resultats.put("tramit_item_data_publicacio", DateUtils.formatDate(tramite.getDataPublicacio()));
    		resultats.put("tramit_item_data_caducitat", DateUtils.formatDate(tramite.getDataCaducitat()));
    		resultats.put("tramit_item_data_inici", DateUtils.formatDate(tramite.getDataInici()));
    		resultats.put("tramit_item_data_tancament", DateUtils.formatDate(tramite.getDataTancament()));
    		resultats.put("item_moment_tramit", tramite.getFase());
    		resultats.put("item_validacio_tramit", tramite.getValidacio());
    		resultats.put("item_url_tramit", tramite.getUrlExterna());
    		resultats.put("item_tramite_tramit", tramite.getIdTraTel());
    		resultats.put("item_version_tramit", tramite.getVersio());
    		resultats.put("item_codivuds_tramit", tramite.getCodiVuds());
    		resultats.put("tramit_item_data_vuds", tramite.getDataActualitzacioVuds());    		
    		resultats.put("item_finestreta_unica", procedimiento.getVentanillaUnica());
    		resultats.put("item_taxes", procedimiento.getTaxa());
    		
    		if (tramite.getOrganCompetent() != null) {
    			resultats.put("tramits_item_organ_id", tramite.getOrganCompetent().getId());
    		}
    		    		    		
			// Idiomas
    		for ( String idioma : idiomas ) {
    			resultats.put(idioma, (TraduccionTramite) tramite.getTraduccion(idioma));
    			//TraduccionUA traduccionUA = ((TraduccionUA) tramite.getOrganCompetent().getTraduccion(idioma));
    			if (tramite.getOrganCompetent() != null) {
        			String nombreUA = tramite.getOrganCompetent().getNombreUnidadAdministrativa(idioma);    			
        			resultats.put("ua_" + idioma, nombreUA);
    			}
    		}
    		
    		String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
    		// Documentos relacionados
    		resultats.put("documentosTramite", getListaDocumentosDTO(request, tramite, lang));
    		// Formularios relacionados    		
    		resultats.put("formulariosTramite", getListaFormulariosDTO(request, tramite, lang));
    		// Documents Requerits relacionats
    		resultats.put("docRequeritsTramite", getListaRequeritsDTO(request, tramite, lang));
    		// Tasas relacionadas
    		resultats.put("tasasTramite", getListaTasasDTO(request, tramite, lang));
    		    		
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
    
	private List<IdNomDTO> getListaTasasDTO(HttpServletRequest request, Tramite tramite, String lang)
	{
		Set<Taxa> listaTasas = tramite.getTaxes();
		List<IdNomDTO> listaTasasDTO = null;
		
		if (listaTasas != null) {
			listaTasasDTO = new ArrayList<IdNomDTO>();
			for (Taxa tasa: listaTasas) {
				String codificacionTasa = ((TraduccionTaxa) tasa.getTraduccion(lang)).getCodificacio();
				listaTasasDTO.add( new IdNomDTO(tasa.getId(), codificacionTasa));
			}
		}
		
		return listaTasasDTO;
	}
	
	private List<IdNomDTO> getListaRequeritsDTO(HttpServletRequest request, Tramite tramite, String lang)
	{
		Set<DocumentTramit> listaDocumentsRequerits = tramite.getDocsRequerits();
		List<IdNomDTO> listaRequeritsDTO = null;
		
		if (listaDocumentsRequerits != null) {
			listaRequeritsDTO = new ArrayList<IdNomDTO>();
			String nomDocRequerit = "";
			
			for (DocumentTramit docReq: listaDocumentsRequerits) {
				if (docReq.getDocCatalogo() != null)
					nomDocRequerit = ((TraduccionCatalegDocuments)docReq.getDocCatalogo().getTraduccion(lang)).getNombre();
				else
					nomDocRequerit = ((TraduccionDocumento) docReq.getTraduccion(lang)).getTitulo();
				
				listaRequeritsDTO.add(new IdNomDTO(docReq.getId(), nomDocRequerit));
			}
		}
		
		return listaRequeritsDTO;
	}
	
	private List<IdNomDTO> getListaFormulariosDTO(HttpServletRequest request, Tramite tramite, String lang)
	{
		Set<DocumentTramit> listaFormularios = tramite.getFormularios();
		List<IdNomDTO> listaFormulariosDTO = null;
		
		if (listaFormularios != null) {
			listaFormulariosDTO = new ArrayList<IdNomDTO>();
			for (DocumentTramit formulari: listaFormularios) {
				String nombreFormulario = ((TraduccionDocumento) formulari.getTraduccion(lang)).getTitulo();
				listaFormulariosDTO.add(new IdNomDTO(formulari.getId(), nombreFormulario));
			}
		}
		
		return listaFormulariosDTO;
	}
	
	private List<IdNomDTO> getListaDocumentosDTO(HttpServletRequest request, Tramite tramite, String lang)
	{
		Set<DocumentTramit> listaDocumentos = tramite.getDocsInformatius();
		List<IdNomDTO> listaDocumentosDTO = null;
		
		if (listaDocumentos != null) {
			listaDocumentosDTO = new ArrayList<IdNomDTO>();
			for (DocumentTramit document: listaDocumentos) {
				String nombreDocumento = ((TraduccionDocumento) document.getTraduccion(lang)).getTitulo();
				listaDocumentosDTO.add( new IdNomDTO( document.getId(), nombreDocumento));
			}
		}
		
		return listaDocumentosDTO;
	}
	
	@RequestMapping(value = "/guardarTramit.do", method = POST)
	public @ResponseBody ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

		String error = null;
		IdNomDTO result = null;
		Tramite tramite = null;
		Long idProcedimiento = new Long( request.getParameter("id_procediment_tramit") );
		
		try {
			
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal procedimiento = procedimientoDelegate.obtenerProcedimientoNewBack(idProcedimiento);
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
			
			String idTramite = request.getParameter("id_tramit_actual"); 
			boolean edicion = !"".equals(idTramite);
			
			if (!edicion) {
				if (!tramiteDelegate.autorizaCrearTramite(idProcedimiento))
					throw new SecurityException("Avís: no té permis per a crear el tràmit");
				
				tramite = new Tramite();
				tramite.setOperativa(Tramite.Operativa.CREA);
				tramite.setOrden(0L);
				
			} else {
				
				tramite = tramiteDelegate.obtenerTramite(new Long(idTramite));

				if (!tramiteDelegate.autorizaModificarTramite( tramite.getId()))				
					throw new SecurityException("Avís: no té� permis per a crear el tràmit");
				
				tramite.setOperativa(Tramite.Operativa.MODIFICA);
				
			}
			
			String version = request.getParameter("item_version_tramit");
			tramite.setVersio( !"".equals(version) ? Integer.parseInt(version) : 0 );
			tramite.setUrlExterna( request.getParameter("item_url_tramit"));
			tramite.setIdTraTel( request.getParameter("item_tramite_tramit"));
			
			// 1 - Inicialización
			// 2 - Instrucción
			// 3 - Finalización
			int fase = Integer.parseInt(request.getParameter("item_moment_tramit"));
			boolean isProcedimientoConEstadoPublicacionPublica = DelegateUtil.getProcedimientoDelegate().isProcedimientoConEstadoPublicacionPublica(idProcedimiento);
			
			// Si se cambia el estado del trámite de iniciación a otro tipo y el procedimiento
			// al cual está asociado tiene como estado de publicación público, impedimos esta
			// edición, ya que en ese estado el procedimiento no puede quedarse sin trámite
			// de iniciación.
			if (edicion) {
				
				int faseAnterior = tramite.getFase();
			
				if (faseAnterior == Tramite.INICIACION && fase != faseAnterior && isProcedimientoConEstadoPublicacionPublica) {
					
					error = messageSource.getMessage("error.tramit_inici_no_es_pot_canviar_tipus", null, request.getLocale());
		            result = new IdNomDTO(-2l, error);
		            
		            return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.ACCEPTED);
					
				}
				
			}
			
			// Si el estado de publicación del procedimiento es público, valideremos que se intente
			// añadir de nuevo otro trámite de iniciación, lo cual impediremos si es el caso.
			if (fase == Tramite.INICIACION && procedimientoDelegate.existeOtroTramiteInicioProcedimiento(idProcedimiento, tramite.getId()) 
					&& isProcedimientoConEstadoPublicacionPublica) {
				
			    error = messageSource.getMessage("error.tramit_inici_ja_existeix", null, request.getLocale());
	            result = new IdNomDTO(-2l, error);
	            
	            return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.ACCEPTED);
	            
			} else {
				
			    tramite.setFase(fase);
			    
			}
			
			//#4 si el tramite tiene momento=ini, el procedimiento es p�blico debe tener modelo solicitud obligatoriamente
			if (edicion && isProcedimientoConEstadoPublicacionPublica && fase == 1 &&
					(request.getParameter("formularisTramit") == null || request.getParameter("formularisTramit").equals(""))) {
				
				error = messageSource.getMessage("error.tramit_inici_sin_model", null, request.getLocale());
	            result = new IdNomDTO(-3l, error);
	            
	            return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.ACCEPTED);
				
			}
			// 1 - Pública
			// 2 - Interna
			// 3 - Reserva
			if ( !"".equals(request.getParameter("item_validacio_tramit"))){
				tramite.setValidacio( new Long(request.getParameter("item_validacio_tramit")) );	
			}
			
			// Parsear fechas en request y asignarlas al trámite.
			procesarFechasTramite(request, tramite);
			// Rellenar los campos
			tramite.setCodiVuds(request.getParameter("item_id_codivuds_tramit"));
			// Traducciones.
			tramite.setTraduccionMap(getTraduccionesTramite(request, tramite));
						
			Scanner scanner = new Scanner(request.getParameter("tramits_item_organ_id"));
			if (scanner.hasNextLong()) {
				UnidadAdministrativa unidadAdministrativa = DelegateUtil.getUADelegate().obtenerUnidadAdministrativa(scanner.nextLong());
				tramite.setOrganCompetent(unidadAdministrativa);
			}
			
			tramite.setProcedimiento(procedimiento);
			String idOrganCompetent = request.getParameter("tramits_item_organ_id");			
			
			tramiteDelegate.grabarTramite(tramite, !"".equals(idOrganCompetent) ? new Long(idOrganCompetent) : procedimiento.getUnidadAdministrativa().getId());
			
        	if (!edicion) {
        		request.setAttribute("alert", "confirmacion.alta");
        		procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);
        	} else {
        		request.setAttribute("alert", "confirmacion.modificacion");
        		// Guardar tasas.
            	guardaTasasTramite(request, tramite, tramiteDelegate);
        		// Guardar documentos y formularios.
        		guardaDocumentosYFormularios(request, edicion, tramite, tramiteDelegate);
        	}

        	// TODO amartin: tras la refactorización usando métodos privados como soporte a los públicos,
        	// veo que esta instrucción ya se ejecuta un par de líneas antes. Desconozco si es 
        	// código duplicado o algo necesario. ACLARAR.
        	if (!edicion)
        		procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);
        	
        } catch (ValidateVudsException e) {
        	
        	String camps = "";
        	for (String camp: e.getCampsSenseValidar())
        		camps += "\\n-" + camp;
        	
        	error = messageSource.getMessage("error.validacio.tramit" + ".Camps sense validar: " + camps, null, request.getLocale());
        	result = new IdNomDTO(-2l, error);
        	log.error(ExceptionUtils.getStackTrace(e));
        	
        } catch (ActualizacionVudsException e) {
        	
        	try {
        		
        		// Ha fallado la actualización pero el trámite ha sido creado correctamente, así que se añade al procedimiento.
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
	
	private Map<String, Traduccion> getTraduccionesTramite(HttpServletRequest request, Tramite tramite) throws DelegateException
	{
		TraduccionTramite traduccionTramite;
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		Map traducciones = new HashMap(langs.size());
		for (String lang : langs) {
			traduccionTramite = (TraduccionTramite)tramite.getTraduccion(lang);
			if (traduccionTramite == null)
				traduccionTramite = new TraduccionTramite();
			
			agregaTraduccionTramite(request, lang, traducciones, traduccionTramite);
		}
		
		return traducciones;
	}
	
	private void guardaDocumentosYFormularios(HttpServletRequest request, boolean edicion, Tramite tramite, TramiteDelegate tramiteDelegate) throws NumberFormatException, DelegateException
	{
		// Recuperamos todos los documentos del request
		String documentosTramite = request.getParameter("documentsTramit");
		String separador = (!"".equals(documentosTramite))? "," : "";
		documentosTramite += (!"".equals(request.getParameter("formularisTramit"))? separador + request.getParameter("formularisTramit") :"");
		separador = (!"".equals(documentosTramite))? "," : "";
		documentosTramite += (!"".equals(request.getParameter("documentsRequerits"))? separador + request.getParameter("documentsRequerits") :"");
		
		// Unimos todos los documentos en un solo Set
		List<DocumentTramit> listaDocumentosOld = new ArrayList<DocumentTramit>();
		listaDocumentosOld.addAll(tramite.getDocsInformatius());
		listaDocumentosOld.addAll(tramite.getFormularios());
		listaDocumentosOld.addAll(tramite.getDocsRequerits());
		
		// Comprobamos de que tengamos tramites desde el request
		if ("".equals(documentosTramite) && listaDocumentosOld.size() > 0) {
			
			tramiteDelegate.borrarDocumentos(tramite, listaDocumentosOld);
			
		} else {
			
			List<DocumentTramit> documentosNuevos = new ArrayList<DocumentTramit>();
			String[] codigosDocumentosNuevos = documentosTramite.split(",");
			List<DocumentTramit> listaDocumentosBorrar = new ArrayList<DocumentTramit>();
			if (edicion) {
				
				for (DocumentTramit documentoTramite: listaDocumentosOld) {
					
					int i = 0;
					while (i < codigosDocumentosNuevos.length) {
						if (!"".equals(codigosDocumentosNuevos[i]) && documentoTramite.getId().equals(Long.valueOf(codigosDocumentosNuevos[i]))) {
							documentosNuevos.add(documentoTramite);
							i = codigosDocumentosNuevos.length;
						}
						i++;
					}
					
					// Eliminar los que se han quitado de la lista.
					if (!documentosNuevos.contains(documentoTramite)) {
						tramite.removeDocument(documentoTramite);
						listaDocumentosBorrar.add(documentoTramite);
					}
				}
				
				if (listaDocumentosBorrar.size() > 0)
					tramiteDelegate.borrarDocumentos(tramite, listaDocumentosBorrar);
				
			}
			
			// Actualizar el orden de la lista de documentos.
			HashMap<String,String[]> actualizadorDocs = new HashMap<String, String[]>();
			for (DocumentTramit documentTramit: documentosNuevos) {
				Long idDoc = documentTramit.getId();
				String ordenDocumento = "";
				
				if (documentTramit.getTipus() == 0)
					ordenDocumento = request.getParameter("documentsTramit_orden_" + idDoc);
				else if (documentTramit.getTipus() == 1)
					ordenDocumento = request.getParameter("formularisTramit_orden_" + idDoc);
				else
					ordenDocumento = request.getParameter("documentsRequerits_orden_" + idDoc);
				
				String[] orden = { ordenDocumento };
				actualizadorDocs.put("orden_doc" + idDoc, orden);
			}
			
			tramiteDelegate.actualizarOrdenDocs(actualizadorDocs, new Long(request.getParameter("id_tramit_actual")));
			
		}
		
	}

	private void guardaTasasTramite(HttpServletRequest request, Tramite tramite, TramiteDelegate tramiteDelegate) throws DelegateException
	{
		String tasasTramite = request.getParameter("taxesTramit");        		   
		Set<Taxa> listaTasasOld = tramite.getTaxes();
		
		if (!"".equals(tasasTramite)) {
			        			
			List<Taxa> listaTasasBorrar = new ArrayList<Taxa>();
			Set<Taxa> tasasNuevas = new HashSet<Taxa>();
			String[] codigosTasasNuevas = tasasTramite.split(",");
			
			for ( int i = 0; i < codigosTasasNuevas.length; i++ ) {        				
				for ( Taxa tasa : listaTasasOld ) {
					if ( !"".equals(codigosTasasNuevas[i]) && tasa.getId().equals(Long.valueOf(codigosTasasNuevas[i])) ) {
						
						tasasNuevas.add( tasa );
						codigosTasasNuevas[i] = null;
						
						break;
						
					}
				}
			}
			
			//Eliminar los que se han quitado de la lista
			for ( Taxa tasa : listaTasasOld ) {
				if ( !tasasNuevas.contains(tasa) )
					listaTasasBorrar.add(tasa);        				
			}
			
			for ( Taxa tasa : listaTasasBorrar ) { 
				tramite.removeTaxa(tasa);
				tramiteDelegate.borrarTaxa(tasa.getId());
			}
				
			// Crear los nuevos.
			if (!"".equals(codigosTasasNuevas)) {
				for (String codigoTasa : codigosTasasNuevas ) {
					if ( codigoTasa != null ) {
						for ( Taxa tasa : listaTasasOld ) {
							if ( !tasasNuevas.contains(tasa) )
								tasasNuevas.add(tasa);
						}
					}
				}
			}
			
			// Actualizamos el orden de la lista de tasas.
			HashMap<String, String[]> actualizadorTasas = new HashMap<String, String[]>();
			
			for ( Taxa tasa : tasasNuevas ) {
				String[] orden = { request.getParameter("taxesTramit_orden_" + tasa.getId()) }; 
				actualizadorTasas.put("orden_taxa" + tasa.getId(), orden);
			}
			
			DelegateUtil.getTramiteDelegate().actualizarOrdenTasas(actualizadorTasas, tramite.getId());

		} else {
			
			for ( Taxa taxa : listaTasasOld ) {        				
				tramite.removeTaxa(taxa);
				tramiteDelegate.borrarTaxa(taxa.getId());
			}
			
		}
		
	}

	private void procesarFechasTramite(HttpServletRequest request, Tramite tramite)
	{
		Date fechaInicio = DateUtils.parseDate(request.getParameter("tramit_item_data_inici"));
        tramite.setDataInici(fechaInicio);
        
        Date fechaCierre= DateUtils.parseDate(request.getParameter("tramit_item_data_tancament"));
        tramite.setDataTancament(fechaCierre);
        
		Date fechaPublicacion = DateUtils.parseDate(request.getParameter("tramit_item_data_publicacio"));
		tramite.setDataPublicacio(fechaPublicacion);
		
		Date fechaActualizacion = DateUtils.parseDate(request.getParameter("tramit_item_data_actualitzacio"));
		tramite.setDataActualitzacio(fechaActualizacion);
		
		Date fechaCaducidad = DateUtils.parseDate(request.getParameter("tramit_item_data_caducitat"));
		tramite.setDataCaducitat(fechaCaducidad);
		
		Date fechaVUDS = DateUtils.parseDate(request.getParameter("tramit_item_data_vuds"));
		String fechaActualizacionVUDS = (fechaVUDS != null) ? new SimpleDateFormat("dd/MM/yyyy").format(fechaVUDS) : "";			
		tramite.setDataActualitzacioVuds( fechaActualizacionVUDS );			
	}

	private void agregaTraduccionTramite(HttpServletRequest request, String lang, Map traducciones, TraduccionTramite traduccionTramite)
	{
		traduccionTramite.setNombre( RolUtil.limpiaCadena(request.getParameter("item_nom_tramit_" + lang)) );
		//#351 se cambia descripcion por observaciones
		//traduccionTramite.setDescripcion( RolUtil.limpiaCadena(request.getParameter("item_descripcio_tramit_" + lang)) );
		traduccionTramite.setRequisits( RolUtil.limpiaCadena(request.getParameter("item_requisits_tramit_" + lang)) );
		traduccionTramite.setDocumentacion( RolUtil.limpiaCadena(request.getParameter("item_documentacio_tramit_" + lang)) );				
		traduccionTramite.setPlazos( RolUtil.limpiaCadena(request.getParameter("item_termini_tramit_" + lang)) );
		traduccionTramite.setLugar( RolUtil.limpiaCadena(request.getParameter("item_lloc_tramit_" + lang)) );
		
		//TODO Este campo no existe en la tabla pero se deja por si se anyade en futuras implementaciones.
		traduccionTramite.setObservaciones( request.getParameter("item_descripcio_tramit_" + lang) );
		
		traducciones.put(lang, traduccionTramite);
	}
	
	@RequestMapping(value = "/esborrarTramit.do", method = POST)	
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		
		IdNomDTO resultatStatus = new IdNomDTO();
		
		Long idTramite = new Long(request.getParameter("id"));
		Long idProcedimiento = new Long(request.getParameter("idProcediment"));
		
		try {
			
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
			} else if (dEx.isIllegalStateException()) {
				resultatStatus.setId(-3l);
				logException(log, dEx);
			} else {
				resultatStatus.setId(-2l);
				logException(log, dEx);
			}
			
		}
		
		return resultatStatus;
		
	}
	
	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {
		
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			
			TraduccionTramite traduccioOrigen = getTraduccionOrigen(request);
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
			
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
			
			traduccions = traductor.translate(traduccioOrigen, idiomaOrigenTraductor);
			
			resultats.put("traduccions", traduccions);
			
		} catch (DelegateException dEx) {
			
			logException(log, dEx);
			
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
			
		} catch (NullPointerException npe) {
			
			log.error("tramiteBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		} catch (Exception e) {
			
			log.error("TramiteBackController.traduir: Error en al traducir tramite: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		}
		
		return resultats;
		
	}
	
	private TraduccionTramite getTraduccionOrigen(HttpServletRequest request) {
		
		TraduccionTramite traduccioOrigen = new TraduccionTramite();
		
		if (StringUtils.isNotEmpty(request.getParameter("item_nom_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			//#351 se cambia descripcion por observaciones
			//traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
			traduccioOrigen.setObservaciones(request.getParameter("item_descripcio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_requisits_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setRequisits(request.getParameter("item_requisits_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_documentacio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setDocumentacion(request.getParameter("item_documentacio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_termini_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setPlazos(request.getParameter("item_termini_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_lloc_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setLugar(request.getParameter("item_lloc_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		
		return traduccioOrigen;
		
	}
	
}