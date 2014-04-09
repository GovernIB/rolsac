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
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;

import org.ibit.rol.sac.model.Seccion;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/pantalles/")
public class PopupSBackController {

	private static Log log = LogFactory.getLog(PopupSBackController.class);
		
    @SuppressWarnings("unused")
	private MessageSource messageSource = null;

    private SeccionDelegate secDelegate = null;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/popArbreS.do", method = GET)
    public String popupS(Map<String, Object> model, HttpSession session, HttpServletRequest request) {               

        List tieneHijos = new ArrayList();

        if (request.getParameter("idSec") != null) {
            request.setAttribute("idSec", new Long(request.getParameter("idSec")));
            
            model.put("id_input", request.getParameter("idInput"));
            model.put("id_hidden", request.getParameter("idHidden"));
            
            try {

                List<Seccion> raices = buscarRaicesSecciones(request.getParameter("padres") != null);

                model.put("raizOptions", raices);

                SeccionDelegate secDelegate = null == this.secDelegate ? DelegateUtil.getSeccionDelegate() : this.secDelegate;

                for (int i = 0; i < raices.size(); i++) {
                	Seccion raizActual = (Seccion) raices.get(i);
                    if (!secDelegate.listarHijosSeccion(raizActual.getId()).isEmpty()) {
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

        return "pantalles/popArbreS";
    }

    private List<Seccion> buscarRaicesSecciones(boolean padre) throws DelegateException {

    	SeccionDelegate secDelegate = null == this.secDelegate ? DelegateUtil.getSeccionDelegate() : this.secDelegate;

        List<Seccion> raices = (List<Seccion>) secDelegate.listarSeccionesRaiz();

        // u92770(enric@dgtic) cas en que volem llistar els pares de les arrels:
        if (padre) {
            List<Seccion> raicesPadre = new ArrayList<Seccion>();
            for (Seccion sec : raices) {
                if (null == sec.getPadre())
                    raicesPadre.add(sec);
                else
                    raicesPadre.add(sec.getPadre());
            }
            raices = raicesPadre;
        }
        return raices;
    }
    
    @RequestMapping(value = "/popArbreSExpandir.do")
    public String expandir(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //log.info("Entramos en expandir");
        List tieneHijos = new ArrayList();

        request.setAttribute("idSec", new Long(request.getParameter("idSec")));

        List<Seccion> raices = buscarRaicesSecciones(request.getParameter("padres") != null);
        
        model.put("raizOptions", raices);
        
        model.put("id_input", request.getParameter("idInput"));
        model.put("id_hidden", request.getParameter("idHidden"));        

        SeccionDelegate secDelegate = null == this.secDelegate ? DelegateUtil.getSeccionDelegate() : this.secDelegate;

        for (Seccion raizActual : raices) {
            if (!secDelegate.listarHijosSeccion(raizActual.getId()).isEmpty()) {
                tieneHijos.add(raizActual.getId());
            }
        }
        
        if (request.getParameter("idSelect") != null) {

            Long id = new Long(request.getParameter("idSelect"));
            List<Seccion> antecesores = secDelegate.listarAntecesoresSeccion(id);

            for (Seccion antecesor : antecesores){

            	Collection<Seccion> hijos = secDelegate.listarHijosSeccion(antecesor.getId());
                request.setAttribute("id_" + antecesor.getId().toString(), hijos);

                for (Seccion hijo : hijos) {
                    if (!secDelegate.listarHijosSeccion(hijo.getId()).isEmpty()) {
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
        
        model.put("tieneHijos", tieneHijos);

        return "pantalles/popArbreS";
    }

    @RequestMapping(value = "/popArbreSContreure.do")
    public String contraer(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //log.info("Entramos en contraer");
        List tieneHijos = new ArrayList();

        request.setAttribute("idSec", new Long(request.getParameter("idSec")));

        List<Seccion> raices = buscarRaicesSecciones(request.getParameter("padres") != null);

        SeccionDelegate secDelegate = null == this.secDelegate ? DelegateUtil.getSeccionDelegate() : this.secDelegate;

        for (int i = 0; i < raices.size(); i++) {
        	Seccion raizActual = (Seccion) raices.get(i);
            if (!secDelegate.listarHijosSeccion(raizActual.getId()).isEmpty()) {
                tieneHijos.add(raizActual.getId());
            }
        }

        if (request.getParameter("idSelect") != null) {

            Long id = new Long(request.getParameter("idSelect"));
            List antecesores = secDelegate.listarAntecesoresSeccion(id);

            for (int i = 0; (i + 1) < antecesores.size(); i++) {

            	Seccion antecesor = (Seccion) antecesores.get(i);

            	Collection<Seccion> hijos = secDelegate.listarHijosSeccion(antecesor.getId());
                request.setAttribute("id_" + antecesor.getId().toString(), hijos);

                for (Seccion hijo : hijos) {
                    if (!secDelegate.listarHijosSeccion(hijo.getId()).isEmpty()) {
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
        
        model.put("tieneHijos", tieneHijos);
        
        model.put("id_input", request.getParameter("idInput"));
        model.put("id_hidden", request.getParameter("idHidden"));
        
        return "pantalles/popArbreS";
    }
}