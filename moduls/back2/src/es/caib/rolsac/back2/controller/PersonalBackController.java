package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.PersonalDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PersonalDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;


@Controller
@RequestMapping("/personal/")
public class PersonalBackController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(PersonalBackController.class);
	
    @RequestMapping(value = "/personal.do", method = GET)
    public String pantallaPersonal(Map<String, Object> model, HttpSession session, HttpServletRequest request) {

        model.put("menu", 0);
        model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 5);
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsSuper()) {
        	model.put("escriptori", "pantalles/personal.jsp");
            if (session.getAttribute("unidadAdministrativa")!=null){
                model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
                model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(request.getLocale().getLanguage()));            
            }
        } else {
        	model.put("error", "permisos");
        }        

		loadIndexModel (model, request);	
        return "index";
    }


    @RequestMapping(value = "/llistat.do", method = POST)
    public @ResponseBody Map<String, Object> llistatPersonal(HttpServletRequest request, HttpSession session) {
    	
    	List<PersonalDTO> llistaPersonalDTO = new ArrayList<PersonalDTO>();
    	Map<String,Object> resultats = new HashMap<String,Object>();
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	if (request.getParameter("idUA") == null || request.getParameter("idUA").equals(""))
    		return resultats;//Si no hay unidad administrativa se devuelve vacio
    	
    	
    	paramMap.put("username", request.getParameter("username"));
    	paramMap.put("nombre", request.getParameter("nom"));
    	paramMap.put("funciones", request.getParameter("funcions"));
    	paramMap.put("cargo", request.getParameter("carrec"));
    	paramMap.put("email", request.getParameter("email"));
    	paramMap.put("extensionPublica", request.getParameter("epui"));
    	paramMap.put("numeroLargoPublico", request.getParameter("nlpui"));
    	paramMap.put("extensionPrivada", request.getParameter("epri"));
    	paramMap.put("numeroLargoPrivado", request.getParameter("nlpri"));
    	paramMap.put("extensionMovil", request.getParameter("em"));
    	paramMap.put("numeroLargoMovil", request.getParameter("nlm"));
    	paramMap.put("unidadAdministrativa.id", new Long(request.getParameter("idUA")));
    	
    	boolean uaFilles = "1".equals(request.getParameter("uaFilles"));
    	boolean uaMeves = "1".equals(request.getParameter("uaMeves"));
    	
    	//Información de paginación
    	String pagPag = request.getParameter("pagPag");
    	String pagRes = request.getParameter("pagRes");
    	
    	if (pagPag == null) pagPag = String.valueOf(0);
    	if (pagRes == null) pagRes = String.valueOf(10);
    	
    	ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
    	
    	try {
    		PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
    		resultadoBusqueda = personalDelegate.buscadorListarPersonal(paramMap, Integer.parseInt(pagPag), Integer.parseInt(pagRes), uaFilles, uaMeves);
    		
    		for ( Personal persona : (List<Personal>) resultadoBusqueda.getListaResultados() ) {
    			llistaPersonalDTO.add(new PersonalDTO(
    						persona.getId(),
    						persona.getNombre(),
    						persona.getUsername(),
    						persona.getUnidadAdministrativa().getNombreUnidadAdministrativa(request.getLocale().getLanguage()),
    						persona.getEmail(),
    						persona.getExtensionPublica()
    					));
    		}
    		
    	} catch (DelegateException dEx) {
    		if (dEx.isSecurityException())
    			log.error("Permisos insuficients: " + dEx.getMessage());
    		
    		else
    			log.error("Error: " + dEx.getMessage());
    		
    	}
    	
    	resultats.put( "total", resultadoBusqueda.getTotalResultados() );
    	resultats.put( "nodes", llistaPersonalDTO );
    	
    	return resultats;
    }
	
	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    
	    Map<String, Object> personaDetall = new HashMap<String, Object>();
	    
	    try {
	        
	        Long id = new Long(request.getParameter("id"));
	        
	        PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
	        Personal persona = personalDelegate.obtenerPersonal(id);	        	        
	        
	        personaDetall.put("id", persona.getId());
	        personaDetall.put("nom", persona.getNombre());
	        personaDetall.put("codi", persona.getUsername());
	        personaDetall.put("funcions", persona.getFunciones());
	        personaDetall.put("carrec", persona.getCargo());
	        personaDetall.put("email", persona.getEmail());
	        personaDetall.put("ua", persona.getUnidadAdministrativa().getNombreUnidadAdministrativa(request.getLocale().getLanguage()));
	        personaDetall.put("uaId", persona.getUnidadAdministrativa().getId());
	        personaDetall.put("extensioPublicaIntranet", persona.getExtensionPublica());
	        personaDetall.put("numeroLlargPublicIntranet", persona.getNumeroLargoPublico());
	        personaDetall.put("extensioPrivadaIntranet", persona.getExtensionPrivada());
	        personaDetall.put("numeroLlargPrivatIntranet", persona.getNumeroLargoPrivado());
	        personaDetall.put("extensioMobil", persona.getExtensionMovil());
	        personaDetall.put("extensioLlargMobil", persona.getNumeroLargoMovil());
	        
	    } catch (DelegateException dEx) {
	    	log.error("Error: " + dEx.getMessage());
			if (dEx.isSecurityException()) {
				personaDetall.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				personaDetall.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
        }
	    
        return personaDetall;
	}

	
	@RequestMapping(value = "/esborrarPersonal.do", method = POST)
    public @ResponseBody IdNomDTO esborrarPersonal(HttpServletRequest request) {
	    
	    IdNomDTO resultatStatus = new IdNomDTO(); 
	    
	    try {
            
            Long id = new Long(request.getParameter("id"));
            
            PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
            personalDelegate.borrarPersonal(id);                                    
            
            resultatStatus.setId(1l);
            resultatStatus.setNom("correcte");
            
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                resultatStatus.setId(-1l);
                log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
                resultatStatus.setId(-2l);
                log.error("Error: " + dEx.getMessage());
            }
        }
	    
	    return resultatStatus;
	}
	
	
	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardarPersonal(HttpSession session, HttpServletRequest request) {
        
		IdNomDTO result = null;
        
        try {
//        	UnidadAdministrativa ua = (UnidadAdministrativa)session.getAttribute("unidadAdministrativa");
        	UnidadAdministrativa ua;
        	try {
        		Long uaId = Long.parseLong(request.getParameter("item_ua_id"));
        		UnidadAdministrativaDelegate udelegate = DelegateUtil.getUADelegate();
        		ua = udelegate.obtenerUnidadAdministrativa(uaId);
        	} catch (NumberFormatException nfe) {
        		ua = null;
        	}
        	
    	    String nom = request.getParameter("item_nom");
    	    String username = request.getParameter("item_codi");
    	    
        	if (ua == null || nom == null || username == null || "".equals(nom) || "".equals(username)) {
        		String error = messageSource.getMessage("persona.error.falten.camps", null, request.getLocale());
				result = new IdNomDTO(-3l, error);
        	} else {
        		PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
        		Personal persona = null;

        		try {
        			Long id = Long.parseLong(request.getParameter("item_id"));
        			persona = personalDelegate.obtenerPersonal(id); 
        		} catch (NumberFormatException nfe) {
        			persona = new Personal();
        		}

                persona.setNombre(nom);
                persona.setUsername(username);
                persona.setUnidadAdministrativa(ua);
                persona.setFunciones(request.getParameter("item_funcions"));
                persona.setCargo(request.getParameter("item_carrec"));
                persona.setEmail(request.getParameter("item_email"));
                persona.setExtensionPublica(request.getParameter("item_epui"));
                persona.setNumeroLargoPublico(request.getParameter("item_nlpui"));
                persona.setExtensionPrivada(request.getParameter("item_epri"));
                persona.setNumeroLargoPrivado(request.getParameter("item_nlpri"));
                persona.setExtensionMovil(request.getParameter("item_em"));
                persona.setNumeroLargoMovil(request.getParameter("item_nlm"));                
                
                personalDelegate.grabarPersonal(persona, ua.getId());
                
                String ok = messageSource.getMessage("personal.guardat.correcte", null, request.getLocale());
                result = new IdNomDTO(persona.getId(), ok);
    	    }
        	
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error("Error: " + dEx.getMessage());
			}
		}
        
		return result;
	}

}
