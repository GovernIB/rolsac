package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;

import org.ibit.rol.sac.model.EspacioTerritorial;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/pantalles/")
public class PopupETBackController {

	private static Log log = LogFactory.getLog(PopupETBackController.class);
		
    private MessageSource messageSource = null;

    private EspacioTerritorialDelegate etDelegate = null;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/popArbreET.do", method = GET)
    public String popupET(Map<String, Object> model, HttpSession session, HttpServletRequest request) {               

        List tieneHijos = new ArrayList();

        if (request.getParameter("idET") != null) {
            request.setAttribute("idET", new Long(request.getParameter("idET")));
            
            model.put("id_input", request.getParameter("idInput"));
            model.put("id_hidden", request.getParameter("idHidden"));
            
            try {

                List<EspacioTerritorial> raices = buscarRaicesEspaciosTerritoriales(request.getParameter("padres") != null);

                model.put("raizOptions", raices);

                EspacioTerritorialDelegate etDelegate = null == this.etDelegate ? DelegateUtil.getEspacioTerritorialDelegate() : this.etDelegate;

                for (int i = 0; i < raices.size(); i++) {
                	EspacioTerritorial raizActual = (EspacioTerritorial) raices.get(i);
                    if (!etDelegate.listarHijosEspacioTerritorial(raizActual.getId()).isEmpty()) {
                        tieneHijos.add(raizActual.getId());
                    }
                }

            } catch (DelegateException dEx) {
                if (dEx.isSecurityException()) {
                	log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
                    // model.put("error", "permisos");
                } else {
                    // model.put("error", "altres");
                	log.error(ExceptionUtils.getStackTrace(dEx));
                }
            }
            /*
             * if (request.getParameter("ficha") != null){
             * request.setAttribute("ficha", "true"); }
             */
            model.put("tieneHijos", tieneHijos);

        } else {
            // return mapping.findForward("fail");
        }

        return "pantalles/popArbreET";
    }

    private List<EspacioTerritorial> buscarRaicesEspaciosTerritoriales(boolean padre) throws DelegateException {

    	EspacioTerritorialDelegate etDelegate = null == this.etDelegate ? DelegateUtil.getEspacioTerritorialDelegate() : this.etDelegate;

        List<EspacioTerritorial> raices = (List<EspacioTerritorial>) etDelegate.listarEspacioTerritorialesRaiz();

        // u92770(enric@dgtic) cas en que volem llistar els pares de les arrels:
        if (padre) {
            List<EspacioTerritorial> raicesPadre = new ArrayList<EspacioTerritorial>();
            for (EspacioTerritorial et : raices) {
                if (null == et.getPadre())
                    raicesPadre.add(et);
                else
                    raicesPadre.add(et.getPadre());
            }
            raices = raicesPadre;
        }
        return raices;
    }
    
    @RequestMapping(value = "/popArbreETExpandir.do")
    public String expandir(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //log.info("Entramos en expandir");
        List tieneHijos = new ArrayList();

        request.setAttribute("idET", new Long(request.getParameter("idET")));

        List<EspacioTerritorial> raices = buscarRaicesEspaciosTerritoriales(request.getParameter("padres") != null);
        
        model.put("raizOptions", raices);
        
        model.put("id_input", request.getParameter("idInput"));
        model.put("id_hidden", request.getParameter("idHidden"));        

        EspacioTerritorialDelegate etDelegate = null == this.etDelegate ? DelegateUtil.getEspacioTerritorialDelegate() : this.etDelegate;

        for (EspacioTerritorial raizActual : raices) {
            if (!etDelegate.listarHijosEspacioTerritorial(raizActual.getId()).isEmpty()) {
                tieneHijos.add(raizActual.getId());
            }
        }
        
        if (request.getParameter("idSelect") != null) {

            Long id = new Long(request.getParameter("idSelect"));
            List<EspacioTerritorial> antecesores = etDelegate.listarAntecesoresEspacioTerritorial(id);

            for (EspacioTerritorial antecesor : antecesores){

            	Collection<EspacioTerritorial> hijos = etDelegate.listarHijosEspacioTerritorial(antecesor.getId());
                request.setAttribute("id_"+antecesor.getId().toString(), hijos);

                for (EspacioTerritorial hijo : hijos) {
                    if (!etDelegate.listarHijosEspacioTerritorial(hijo.getId()).isEmpty()) {
                        tieneHijos.add(hijo.getId());
                    }
                }

            }
/*
            if (request.getParameter("ficha") != null) {
                request.setAttribute("ficha", "true");
            }
*/
        }
        /* else {
            return mapping.findForward("fail");
        }*/

        model.put("tieneHijos", tieneHijos);
      
       

        return "pantalles/popArbreET";
    }

    @RequestMapping(value = "/popArbreETContreure.do")
    public String contraer(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //log.info("Entramos en contraer");
        List tieneHijos = new ArrayList();

        request.setAttribute("idET", new Long(request.getParameter("idET")));

        List<EspacioTerritorial> raices = buscarRaicesEspaciosTerritoriales(request.getParameter("padres") != null);

        EspacioTerritorialDelegate etDelegate = null == this.etDelegate ? DelegateUtil.getEspacioTerritorialDelegate() : this.etDelegate;

        for (int i = 0; i < raices.size(); i++) {
        	EspacioTerritorial raizActual = (EspacioTerritorial) raices.get(i);
            if (!etDelegate.listarHijosEspacioTerritorial(raizActual.getId()).isEmpty()) {
                tieneHijos.add(raizActual.getId());
            }
        }

        if (request.getParameter("idSelect") != null) {

            Long id = new Long(request.getParameter("idSelect"));
            List antecesores = etDelegate.listarAntecesoresEspacioTerritorial(id);

            for (int i = 0; (i + 1) < antecesores.size(); i++) {

            	EspacioTerritorial antecesor = (EspacioTerritorial) antecesores.get(i);

            	Collection<EspacioTerritorial> hijos = etDelegate.listarHijosEspacioTerritorial(antecesor.getId());
                request.setAttribute("id_" + antecesor.getId().toString(), hijos);

                for (EspacioTerritorial hijo : hijos) {
                    if (!etDelegate.listarHijosEspacioTerritorial(hijo.getId()).isEmpty()) {
                        tieneHijos.add(hijo.getId());
                    }

                }

            }
/*
            if (request.getParameter("ficha") != null) {
                request.setAttribute("ficha", "true");
            }
*/
        } 
        /*else {
            return mapping.findForward("fail");
        }*/

        model.put("tieneHijos", tieneHijos);
        
        model.put("id_input", request.getParameter("idInput"));
        model.put("id_hidden", request.getParameter("idHidden"));

        return "pantalles/popArbreET";
    }
}
