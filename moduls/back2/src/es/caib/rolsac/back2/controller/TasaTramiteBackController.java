package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.hibernate.exception.ExceptionUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.RolUtil;

@Controller
@RequestMapping("/taxa/")
public class TasaTramiteBackController {

	private static Log log = LogFactory.getLog(TramiteBackController.class);	
	private MessageSource messageSource = null;
	
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
        
    @RequestMapping(value = "/taxa.do", method = GET)
    public void obtenerTasa(HttpServletRequest request, HttpServletResponse response) {}    
    
    @RequestMapping(value = "/carregarTaxaTramit.do", method = POST)
    public @ResponseBody Map<String, Object> recuperaDetall(HttpSession session, HttpServletRequest request) {    
    	    	
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
    	
    	Map<String, Object> resultats = new HashMap<String, Object>();
    	
    	try {   
    		
    		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> idiomas = idiomaDelegate.listarLenguajes();
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();    		    		
    		Taxa tasa = tramiteDelegate.obtenirTaxa( new Long(request.getParameter("id")) );    		
    		Map<String, Object> mapTaxa = new HashMap<String, Object>();
    		TraduccionTaxa traTasa; 
    		
			// Idiomas
    		for ( String idioma : idiomas ) {
    			
    			traTasa = (TraduccionTaxa) tasa.getTraduccion(idioma);
    		
    			if (traTasa != null) {
    				mapTaxa.put("idioma_codificacio_" + idioma, traTasa.getCodificacio());
    				mapTaxa.put("idioma_forma_pagament_" + idioma, traTasa.getFormaPagament());
    				
    				if ( traTasa.getDescripcio() != null )
    					mapTaxa.put("idioma_descripcio_" + idioma, traTasa.getDescripcio() );
    			}
    			
    		}
    		
    		mapTaxa.put("item_id", tasa.getId());

			resultats.put("taxa", mapTaxa);
			resultats.put("id", tasa.getId());
    		
    	} catch (DelegateException dEx) {
    		
			logException(log, dEx);

			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}    
			
    	} catch (NumberFormatException nFEx) {
    		
    		logException(log, nFEx);
    		resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));    	
    		
    	}
    	
    	return resultats;    
    	
    }
    
	@RequestMapping(value = "/guardarTaxa.do", method = POST)
	public @ResponseBody ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

		String error = null;		
		IdNomDTO result = null;
		Taxa tasa = null;
		TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();		

		try {
			
			Tramite tramite = tramiteDelegate.obtenerTramite( new Long(request.getParameter("idTramit")) );			
			
			String idTasa = request.getParameter("taxaTramitId"); 
			boolean edicion = !"".equals(idTasa);
			
			if ( !edicion ) {
				tasa = new Taxa();
				tasa.setOrden(0L);
			} else
				tasa = DelegateUtil.getTramiteDelegate().obtenirTaxa(new Long(idTasa));
						
			// Idiomas
			TraduccionTaxa traduccionTaxa;			
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			Map traducciones = new HashMap(langs.size());			
			
			for (String lang: langs) {
				
				traduccionTaxa = (TraduccionTaxa) tasa.getTraduccion(lang);
				
				if ( traduccionTaxa == null ) 
					traduccionTaxa = new TraduccionTaxa();
				
				traduccionTaxa.setCodificacio( RolUtil.limpiaCadena(request.getParameter("taxa_tramit_codi_" + lang)) );
				traduccionTaxa.setDescripcio( RolUtil.limpiaCadena(request.getParameter("taxa_tramit_descripcio_" + lang)) );
				traduccionTaxa.setFormaPagament( RolUtil.limpiaCadena(request.getParameter("taxa_tramit_forma_pagament_" + lang)) );
				
				traducciones.put(lang, traduccionTaxa);				
			}
			
			tasa.setTraduccionMap(traducciones);
			// Fin idiomas
			
			tramiteDelegate.grabarTaxa(tasa, tramite.getId());

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
        	result.setId(tasa.getId());
        	result.setNom( ((TraduccionTaxa) tasa.getTraduccion("ca")).getCodificacio() );        
        }
        
		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);	
		
	}
	
	@RequestMapping(value = "/esborrarTaxa.do", method = POST)	
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		
		IdNomDTO resultatStatus = new IdNomDTO();
		
		Long idTasa = new Long(request.getParameter("taxaTramitId"));
		
		try {
			
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
			tramiteDelegate.borrarTaxa(idTasa);
		
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