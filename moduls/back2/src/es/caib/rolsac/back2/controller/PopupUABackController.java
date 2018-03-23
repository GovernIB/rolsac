package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/pantalles/")
public class PopupUABackController {
	
	private static Log log = LogFactory.getLog(PopupUABackController.class);
	
    @SuppressWarnings("unused")
	private MessageSource messageSource = null;
    
    private UnidadAdministrativaDelegate uaDelegate = null;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/popArbreUA.do", method = GET)
    public String popupUA(Map<String, Object> model, HttpSession session, HttpServletRequest request) {               

        List tieneHijos = new ArrayList();

        if (request.getParameter("idUA") != null) {
            request.setAttribute("idUA",new Long(request.getParameter("idUA")));

            model.put("id_input", request.getParameter("idInput"));
            model.put("id_hidden", request.getParameter("idHidden"));
            
            try {

            	
                List<UnidadAdministrativa> raices;
                UnidadAdministrativaDelegate uaDelegate = null == this.uaDelegate ? DelegateUtil.getUADelegate() : this.uaDelegate;
                
                if (request.getParameter("idUAraiz") == null || request.getParameter("idUAraiz").isEmpty()) {
                	raices = buscarRaicesUnidadesAdministrativas(request.getParameter("padres") != null, false);
                } else {
                	raices = new ArrayList();
                	Long idUAraiz = Long.valueOf(request.getParameter("idUAraiz"));
                	raices.add(uaDelegate.obtenerUnidadAdministrativa(idUAraiz));
                	model.put("idUAraiz", request.getParameter("idUAraiz"));
                    
                }
                
                model.put("raizOptions", raices);

                
                for (int i = 0; i < raices.size(); i++) {
                    UnidadAdministrativa raizActual = (UnidadAdministrativa) raices.get(i);
                    if (!uaDelegate.listarHijosUA(raizActual.getId()).isEmpty()) {
                        tieneHijos.add(raizActual.getId());
                    }
                }

            } catch (DelegateException dEx) {
            	
                if (dEx.isSecurityException()) {
                	log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
                } else {
                	log.error(ExceptionUtils.getStackTrace(dEx));
                }
                
            }

            model.put("tieneHijos", tieneHijos);

        }
        
        return "pantalles/popArbreUA";
        
    }

    private List<UnidadAdministrativa> buscarRaicesUnidadesAdministrativas(boolean padre, boolean todas) throws DelegateException {

        UnidadAdministrativaDelegate uaDelegate = null == this.uaDelegate ? DelegateUtil.getUADelegate() : this.uaDelegate;

        List<UnidadAdministrativa> raices = (todas) 
        		? (List<UnidadAdministrativa>)uaDelegate.listarTodasUnidadesAdministrativasRaiz()
        		: (List<UnidadAdministrativa>)uaDelegate.listarUnidadesAdministrativasRaiz();

        // u92770(enric@dgtic) cas en que volem llistar els pares de les arrels:
        if (padre) {
            List<UnidadAdministrativa> raicesPadre = new ArrayList<UnidadAdministrativa>();
            for (UnidadAdministrativa ua : raices) {
                if (null == ua.getPadre())
                    raicesPadre.add(ua);
                else
                    raicesPadre.add(ua.getPadre());
            }
            raices = raicesPadre;
        }
        
        return raices;
        
    }
    
    @RequestMapping(value = "/popArbreUAExpandir.do")
    public String expandir(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List tieneHijos = new ArrayList();
        
        request.setAttribute("idUA", new Long(request.getParameter("idUA")));
        request.setAttribute("idSelect", request.getParameter("idSelect"));
        request.setAttribute("totes", request.getParameter("totes"));

        List<UnidadAdministrativa> raices;
        UnidadAdministrativaDelegate uaDelegate = null == this.uaDelegate ? DelegateUtil.getUADelegate() : this.uaDelegate;
        if (request.getParameter("idUAraiz") == null || request.getParameter("idUAraiz").isEmpty()) {
    	    
	        raices = buscarRaicesUnidadesAdministrativas(request.getParameter("padres") != null,
	        		"1".equals(request.getParameter("totes")));
        } else {
        	raices = new ArrayList();
        	Long idUAraiz = Long.valueOf(request.getParameter("idUAraiz"));
        	raices.add(uaDelegate.obtenerUnidadAdministrativa(idUAraiz));
        	model.put("idUAraiz", request.getParameter("idUAraiz"));
        }
        model.put("raizOptions", raices);
        
        model.put("id_input", request.getParameter("idInput"));
        model.put("id_hidden", request.getParameter("idHidden"));        

        
        for (int i = 0; i < raices.size(); i++) {
            UnidadAdministrativa raizActual = (UnidadAdministrativa) raices.get(i);
            if (!uaDelegate.listarHijosUA(raizActual.getId()).isEmpty()) {
                tieneHijos.add(raizActual.getId());
            }
        }

        if (request.getParameter("idSelect") != null) {

            Long id = new Long(request.getParameter("idSelect"));
            List antecesores = uaDelegate.listarPadresUnidadAdministrativa(id);

            for (int i = 0; i < antecesores.size(); i++) {

                UnidadAdministrativa antecesor = (UnidadAdministrativa) antecesores.get(i);

                List hijos = uaDelegate.listarHijosUA(antecesor.getId());
                request.setAttribute("id_"+antecesor.getId().toString(), hijos);

                for (int j = 0; j < hijos.size(); j++) {

                    UnidadAdministrativa hijo = (UnidadAdministrativa) hijos.get(j);

                    if (!uaDelegate.listarHijosUA(hijo.getId()).isEmpty()) {
                        tieneHijos.add(hijo.getId());
                    }
                }

            }

        }

        model.put("tieneHijos", tieneHijos);

        return "pantalles/popArbreUA";

    }

    @RequestMapping(value = "/popArbreUAContreure.do")
    public String contraer(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List tieneHijos = new ArrayList();

        request.setAttribute("idUA", new Long(request.getParameter("idUA")));
        request.setAttribute("totes", request.getParameter("totes"));

        List<UnidadAdministrativa> raices;
        UnidadAdministrativaDelegate uaDelegate = null == this.uaDelegate ? DelegateUtil.getUADelegate() : this.uaDelegate;
        if (request.getParameter("idUAraiz") == null || request.getParameter("idUAraiz").isEmpty()) {
    	    
	        raices = buscarRaicesUnidadesAdministrativas(request.getParameter("padres") != null,
	        		"1".equals(request.getParameter("totes")));
        } else {
        	raices = new ArrayList();
        	Long idUAraiz = Long.valueOf(request.getParameter("idUAraiz"));
        	raices.add(uaDelegate.obtenerUnidadAdministrativa(idUAraiz));
        	model.put("idUAraiz", request.getParameter("idUAraiz"));
        }
        model.put("raizOptions", raices);

        for (int i = 0; i < raices.size(); i++) {
            UnidadAdministrativa raizActual = (UnidadAdministrativa) raices.get(i);
            if (!uaDelegate.listarHijosUA(raizActual.getId()).isEmpty()) {
                tieneHijos.add(raizActual.getId());
            }
        }

        if (request.getParameter("idSelect") != null) {

            Long id = new Long(request.getParameter("idSelect"));
            List antecesores = uaDelegate.listarPadresUnidadAdministrativa(id);

            for (int i = 0; (i + 1) < antecesores.size(); i++) {

                UnidadAdministrativa antecesor = (UnidadAdministrativa) antecesores.get(i);

                List hijos = uaDelegate.listarHijosUA(antecesor.getId());
                request.setAttribute("id_" + antecesor.getId().toString(), hijos);

                for (int j = 0; j < hijos.size(); j++) {

                    UnidadAdministrativa hijo = (UnidadAdministrativa) hijos.get(j);

                    if (!uaDelegate.listarHijosUA(hijo.getId()).isEmpty()) {
                        tieneHijos.add(hijo.getId());
                    }

                }

            }

        } 

        model.put("tieneHijos", tieneHijos);
        
        model.put("id_input", request.getParameter("idInput"));
        model.put("id_hidden", request.getParameter("idHidden"));        

        return "pantalles/popArbreUA";
        
    }
    
}
