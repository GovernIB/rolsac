package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PersonalDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import org.ibit.rol.sac.model.transients.IdNomTransient;
import org.ibit.rol.sac.model.transients.PersonalTransient;

import es.caib.rolsac.back2.customJSTLTags.PrintRolTag;
import es.caib.rolsac.back2.util.RolUtil;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@Controller
@RequestMapping("/personal/")
public class PersonalBackController {
    
	private static Log log = LogFactory.getLog(PrintRolTag.class);
	
    private MessageSource messageSource = null;
    
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    
    @RequestMapping(value = "/personal.do", method = GET)
    public String pantallaPersonal(Map<String, Object> model, HttpSession session, HttpServletRequest request) {

        model.put("menu", 0);
        model.put("submenu", "layout/submenuOrganigrama.jsp");
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

        return "index";
    }

    
	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistatPersonal(HttpServletRequest request, HttpSession session) {

       List<Personal> llistaPersonal = new ArrayList<Personal>();
       List<PersonalTransient> llistaPersonalTransient = new ArrayList<PersonalTransient>();
       Map<String,Object> resultats = new HashMap<String,Object>();
	   Map<String, Object> paramMap = new HashMap<String, Object>();
	   
	   if (request.getParameter("idUA") == null || request.getParameter("idUA").equals("")){                      
           return resultats;//Si no hay unidad administrativa se devuelve vacio
       }
	   
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
       
       try {                      		   
		   		   		
			PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
			llistaPersonal = personalDelegate.listarPersonalFiltro(paramMap);
			
			for(Personal persona : llistaPersonal){                
	               llistaPersonalTransient.add(new PersonalTransient(  persona.getId(), 
	                                                                   persona.getNombre(),
	                                                                   persona.getUsername(),
	                                                                   persona.getUnidadAdministrativa().getNombreUnidadAdministrativa(request.getLocale().getLanguage()),
	                                                                   persona.getEmail(),
	                                                                   persona.getExtensionPublica()
	                                                                   ));                
	           }

		} catch (DelegateException dEx) {
			if (dEx.getCause() instanceof SecurityException) {
				log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
            	log.error("Error: " + dEx.getMessage());
            }
		}

		resultats.put("total", llistaPersonalTransient.size());
        resultats.put("nodes", llistaPersonalTransient);

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
            if (dEx.getCause() instanceof SecurityException) {
            	log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
            	log.error("Error: " + dEx.getMessage());
            }
        }
	    
        return personaDetall;
	}

	
	@RequestMapping(value = "/esborrarPersonal.do", method = POST)
    public @ResponseBody IdNomTransient esborrarPersonal(HttpServletRequest request) {
	    
	    IdNomTransient resultatStatus = new IdNomTransient(); 
	    
	    try {
            
            Long id = new Long(request.getParameter("id"));
            
            PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
            personalDelegate.borrarPersonal(id);                                    
            
            resultatStatus.setId(1l);
            resultatStatus.setNom("correcte");
            
        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
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
	public @ResponseBody IdNomTransient guardarPersonal(HttpSession session, HttpServletRequest request) {
        
		IdNomTransient result = null;
        
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
				result = new IdNomTransient(-3l, error);
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
                result = new IdNomTransient(persona.getId(), ok);
    	    }
        	
		} catch (DelegateException dEx) {
			if (dEx.getCause() instanceof SecurityException) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomTransient(-1l, error);
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomTransient(-2l, error);
				log.error("Error: " + dEx.getMessage());
			}
		}
        
		return result;
	}

}
