package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.rolsac.back2.util.Parametros;
import es.caib.rolsac.back2.util.ParseUtil;

@Controller
@RequestMapping("/pantalles/")
public class PopupCoordenadesBackController {

	private static Log log = LogFactory.getLog(PopupCoordenadesBackController.class);
		
    @SuppressWarnings("unused")
	private MessageSource messageSource = null;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/popCoordenades.do", method = GET)
    public String popupCoordenades(Map<String, Object> model, HttpSession session, HttpServletRequest request) {               

    	Edificio edifici;
    	
    	try {
    	    
    		Long idEdifici = ParseUtil.parseLong(request.getParameter("id"));	
    		
    	    if (idEdifici != null) {
    	    	
    	    	EdificioDelegate delegate = DelegateUtil.getEdificioDelegate();
    	        edifici = delegate.obtenerEdificio(idEdifici);

    	        String lsDir = ""+ edifici.getDireccion();
    	        lsDir = lsDir.replaceAll("n�","");
    	        lsDir = lsDir.replaceAll(",","");
    	        String lsCP = ""+ edifici.getCodigoPostal();
    	        if (lsCP.equals("null")) lsCP= "";
    	        String lsPbl = ""+ edifici.getPoblacion();
    	        if (lsPbl.equals("null")) lsPbl= "";                
    	        if (edifici.getDireccion()!= null){
    	        	model.put("dirEdi",lsDir + " , " + lsCP + " , " + lsPbl + " , " + "Espa�a");
    	        }

    	        model.put("latEdi", edifici.getLatitud());
    	        model.put("lngEdi", edifici.getLongitud());

    	    } else {
    	    	model.put("latEdi", Parametros.COORDENADA_LATITUD);
    	        model.put("lngEdi", Parametros.COORDENADA_LONGITUD);
    	        model.put("dirEdi", "");
    	    }
    	        	    
    	    String googleMapKey = System.getProperty("es.indra.caib.rolsac.googleMapKey");          
    	    if (googleMapKey != null){
    	    	model.put("googleMapKey", googleMapKey);
    	    }
    	    
    	    // Camps a emplenar:
    	    String latitud = (String) request.getParameter("latitud");
    	    String longitud = (String) request.getParameter("longitud");
    	    model.put("latitud", latitud);
    	    model.put("longitud", longitud);
    	    
    	                
    	} catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
            	log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
                // model.put("error", "permisos");
            } else {
                // model.put("error", "altres");
            	log.error(ExceptionUtils.getStackTrace(dEx));
            }
    	}

        return "pantalles/popCoordenades";
    }
}


