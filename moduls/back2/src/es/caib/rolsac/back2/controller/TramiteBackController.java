package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
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
    		
			// Idiomas    	
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> idiomas = idiomaDelegate.listarLenguajes();			
    		Tramite tramite = DelegateUtil.getTramiteDelegate().obtenerTramite(idTramite);

    		resultats.put("idiomas", idiomas);
    		resultats.put("idTramit", tramite.getId());
    		resultats.put("id_tramit_actual", tramite.getId());
    		resultats.put("id_procediment_tramit", tramite.getProcedimiento().getId());
    		resultats.put("tramit_item_data_actualitzacio", DateUtils.formatDate(tramite.getDataActualitzacio()));
    		resultats.put("tramit_item_data_publicacio", DateUtils.formatDate(tramite.getDataPublicacio()));
    		resultats.put("tramit_item_data_caducitat", DateUtils.formatDate(tramite.getDataCaducitat()));    		
    		resultats.put("item_url_tramit", tramite.getUrlExterna());
    		resultats.put("item_tramite_tramit", tramite.getIdTraTel());
    		resultats.put("item_version_tramit", tramite.getVersio());
    		
			// Idiomas
    		for ( String idioma : idiomas )
    			resultats.put(idioma, (TraduccionTramite) tramite.getTraduccion(idioma) );		
    		
			//TODO Cargar resto de información (formularios, etc)
    		
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
    public ResponseEntity<String> guardarTramite(HttpSession session, HttpServletRequest request) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		String error = null;
		IdNomDTO result = null;
		Tramite tramite = null;
		Long idProcedimiento = new Long( request.getParameter("id_procediment_tramit") );
		
		try {
			
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();			
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
			
			// Rellenar los campos
			Date fechaPublicacion = DateUtils.parseDate(request.getParameter("tramit_item_data_publicacio"));
			if (fechaPublicacion != null) {
				tramite.setDataPublicacio(fechaPublicacion);
			}
			
			Date fechaActualizacion = DateUtils.parseDate(request.getParameter("tramit_item_data_actualitzacio"));
			if (fechaActualizacion != null) {
				tramite.setDataActualitzacio(fechaActualizacion);
			}
			
			Date fechaCaducidad = DateUtils.parseDate(request.getParameter("tramit_item_data_caducitat"));
			if (fechaCaducidad != null) {
				tramite.setDataCaducitat(fechaCaducidad);
			}
			
			tramite.setProcedimiento(procedimientoDelegate.obtenerProcedimiento(idProcedimiento));			
			tramite.setDataActualitzacioVuds(""); 
			
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
				
				//tramite.setTraduccion(lang, traduccionTramite);
				traducciones.put(lang, traduccionTramite);				
			}
			
			tramite.setTraduccionMap(traducciones);
			// Fin idiomas
			
			String idOrganCompetent = request.getParameter("tramits_item_organ_id");
			
			//Se asigna uno manualmente hasta arreglar el componente de carga de UA			
			tramiteDelegate.grabarTramite(tramite, !"".equals(idOrganCompetent) ? new Long(idOrganCompetent) : 633160L );
			
        	if ( !edicion ) {
        		request.setAttribute("alert", "confirmacion.alta");
        		procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);
        	} else {
        		request.setAttribute("alert", "confirmacion.modificacion");
        	}
        	        	
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
			
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			
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
