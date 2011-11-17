package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.transients.EdificioTransient;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/edificis/")
public class EdificisBackController {
	
	private static Log log = LogFactory.getLog(EdificisBackController.class);
    	
	@RequestMapping(value = "/llistat.do", method = POST)
    public @ResponseBody Map<String, Object> llistatEdificis(HttpServletRequest request) {
	    	    	    
	    Map<String,Object> resultats = new HashMap<String,Object>();
        List<Edificio> llistaEdificis = new ArrayList<Edificio>();
        List<EdificioTransient> llistaEdificioTransient = new ArrayList<EdificioTransient>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> tradMap = new HashMap<String, Object>();
        
        if (!"".equals(request.getParameter("adresa"))){
            paramMap.put("direccion", request.getParameter("adresa").toUpperCase());
        }
        if (!"".equals(request.getParameter("cp"))){
            paramMap.put("codigoPostal", request.getParameter("cp").toUpperCase());
        }
        if (!"".equals(request.getParameter("poblacio"))){
            paramMap.put("poblacion", request.getParameter("poblacio").toUpperCase());
        }
        
        //Requerimiento del metodo original de rolsac
        tradMap.put("idioma", request.getLocale().getLanguage());
        
        try {                               
                     
         EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
         
         llistaEdificis = edificiDelegate.buscarEdificios(paramMap, tradMap);
         
         for(Edificio edifici : llistaEdificis){                
             llistaEdificioTransient.add(new EdificioTransient(  edifici.getId(), 
                                                                 edifici.getDireccion(),
                                                                 edifici.getCodigoPostal(),
                                                                 edifici.getPoblacion()
                                                                    ));                
            }

     } catch (DelegateException dEx) {
    	 if (dEx.getCause() instanceof SecurityException) {
			log.error("Error de permisos: " + dEx.toString());
			resultats.put("id", -1);
		} else {
			log.error("Error: " + dEx.toString());
			resultats.put("id", -2);
		}
     }
               
        resultats.put("total", llistaEdificioTransient.size());        
        resultats.put("nodes", llistaEdificioTransient);        
        
        return resultats;
	}
}
