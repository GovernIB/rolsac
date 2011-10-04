package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionEspacioTerritorial;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTratamiento;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.transients.IdNomTransient;
import org.ibit.rol.sac.model.transients.FichaTransient;
import org.ibit.rol.sac.model.transients.ProcedimientoLocalTransient;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PersonalDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.TratamientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.DateUtil;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/fitxainf/")
public class FitxaInfBackController {

    private MessageSource messageSource = null;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/fitxainf.htm", method = GET)
    public String pantallaFitxes(Map<String, Object> model, HttpServletRequest request, HttpSession session) {

        MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
        HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();

        List<Materia> llistaMateries = new ArrayList<Materia>();
        List<HechoVital> llistaFetsVitals = new ArrayList<HechoVital>();
        List<IdNomTransient> llistaMateriesTransient = new ArrayList<IdNomTransient>();
        List<IdNomTransient> llistaFetsVitalsTransient = new ArrayList<IdNomTransient>();

        try {
            llistaMateries = materiaDelegate.listarMaterias();

            for (Materia materia : llistaMateries) {
                llistaMateriesTransient.add(new IdNomTransient(materia.getId(), materia.getNombreMateria(request.getLocale().getLanguage())));
            }

            llistaFetsVitals = fetVitalDelegate.listarHechosVitales();
            
            for (HechoVital fetVital : llistaFetsVitals) {
                TraduccionHechoVital thv = (TraduccionHechoVital) fetVital.getTraduccion(request.getLocale().getLanguage());
                llistaFetsVitalsTransient.add(new IdNomTransient(fetVital.getId(), 
                                                                 thv == null ? null : thv.getNombre()));
            }

        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                // model.put("error", "permisos");//TODO:mensajes de error
            } else {
                // model.put("error", "altres");
                dEx.printStackTrace();
            }
        }

        model.put("menu", 0);
        model.put("submenu", "layout/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 3);
        model.put("titol_escriptori", messageSource.getMessage("submenu.fitxes_informatives", null, request.getLocale()));
        model.put("escriptori", "pantalles/fitxaInf.jsp");
        
        model.put("llistaMateries", llistaMateriesTransient);
        model.put("llistaFetsVitals", llistaFetsVitalsTransient);
        
        
        return "index";
    }

    @RequestMapping(value = "/llistat.htm", method = POST)
    public @ResponseBody Map<String, Object> llistatFitxes(HttpServletRequest request, HttpSession session) {
        
        List<Ficha> llistaFitxes = new ArrayList<Ficha>();
        List<FichaTransient> llistaFitxesTransient = new ArrayList<FichaTransient>();
        Map<String, Object> resultats = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> tradMap = new HashMap<String, String>();

        String lang = request.getLocale().getLanguage();
        
        UnidadAdministrativa ua = null;
        Long fetVital = null;
        Long materia = null;
        
        
        if (session.getAttribute("unidadAdministrativa") == null) {
            return resultats; // Si no hay unidad administrativa se devuelve vacio
        } else {
            ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
        }
          
        try {
            Long codi = Long.parseLong(request.getParameter("codi"));
            paramMap.put("id", codi);
        } catch (NumberFormatException e){
        }
        
        if (request.isUserInRole("sacoper")) {
            paramMap.put("validacion", ""); // En el back antiguo estaba asi.
        } else {
            String estat = request.getParameter("estat");
            try {
                Integer validacion = Integer.parseInt(estat);
                paramMap.put("validacion", validacion);
            } catch (NumberFormatException e){
            }
        }
        
        try {
            materia = Long.parseLong(request.getParameter("materia"));
        } catch (NumberFormatException e){}
        
        try {
            fetVital = Long.parseLong(request.getParameter("fetVital"));
        } catch (NumberFormatException e){}

        String url = request.getParameter("url");
        if (url != null && !"".equals(url)) {
            paramMap.put("urlForo", url.toUpperCase());
        }
        
        String responsable = request.getParameter("responsable");
        if (responsable != null && !"".equals(responsable)) {
            paramMap.put("responsable", responsable.toUpperCase());
        }

        Date fechaCaducidad = DateUtil.parseDate(request.getParameter("fechaCaducidad"));
        if (fechaCaducidad != null) {
            paramMap.put("fechaCaducidad", fechaCaducidad);
        }
             
        Date fechaPublicacion = DateUtil.parseDate(request.getParameter("fechaPublicacion"));
        if (fechaPublicacion != null) {
            paramMap.put("fechaPublicacion", fechaPublicacion);
        }
                
        Date fechaActualizacion = DateUtil.parseDate(request.getParameter("fechaActualizacion"));
        if (fechaActualizacion != null) {
            paramMap.put("fechaActualizacion", fechaActualizacion);
        }
        
        boolean uaFilles;
        if ("1".equals(request.getParameter("uaFilles"))) {
            uaFilles = true;
        } else {
            uaFilles = false;
        }
        
        boolean uaMeves;
        if ("1".equals(request.getParameter("uaMeves"))) {
            uaMeves = true;
        } else {
            uaMeves = false;
        }

    
     // Textes (en todos los campos todos los idiomas)
        String textes = request.getParameter("textes");
        if (textes != null && !"".equals(textes)) {
            textes = textes.toUpperCase();
            if (tradMap.get("titulo") == null) {
                tradMap.put("titulo", textes);
            }
            tradMap.put("descAbr", textes);
            tradMap.put("descripcion", textes);
            tradMap.put("url", textes);
        } else {
            tradMap.put("idioma", lang);
        }
        
        try {
            FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
            llistaFitxes = fitxaDelegate.buscarFichas(paramMap, tradMap, ua, fetVital, materia, uaFilles, uaMeves);
            //llistaFitxes = fitxaDelegate.listarFichasUnidad(ua.getId());

            for (Ficha fitxa : llistaFitxes) {
                TraduccionFicha tfi = (TraduccionFicha) fitxa.getTraduccion(request.getLocale().getLanguage());
                llistaFitxesTransient.add(new FichaTransient(fitxa.getId(), 
                                                             tfi == null ? null : tfi.getTitulo(), 
                                                             fitxa.getFechaPublicacion(), 
                                                             fitxa.getFechaCaducidad()));
            }

        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                // model.put("error", "permisos");
            } else {
                // model.put("error", "altres");
                dEx.printStackTrace();
            }
        }
        
        resultats.put("total", llistaFitxesTransient.size());
        resultats.put("nodes", llistaFitxesTransient);

        return resultats;
        
        
        
    }

    @RequestMapping(value = "/pagDetall.htm", method = POST)
    public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();
        
        FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
        
        Long id = new Long(request.getParameter("id"));
        
        try {
        
            Ficha fitxa = fitxaDelegate.obtenerFicha(id);
        
            resultats.put("item_id", fitxa.getId());

            resultats.put("item_estat", fitxa.getValidacion());

            resultats.put("item_data_publicacio", DateUtil.formatDate(fitxa.getFechaPublicacion()));

            resultats.put("item_data_caducitat", DateUtil.formatDate(fitxa.getFechaCaducidad()));

            // Idiomas
            if (fitxa.getTraduccion("ca") != null) {
                resultats.put("ca", (TraduccionFicha) fitxa.getTraduccion("ca"));
            } else {
                resultats.put("ca", new TraduccionFicha());
            }

            if (fitxa.getTraduccion("es") != null) {
                resultats.put("es", (TraduccionFicha) fitxa.getTraduccion("es"));
            } else {
                resultats.put("es", new TraduccionFicha());
            }

            if (fitxa.getTraduccion("en") != null) {
                resultats.put("en", (TraduccionFicha) fitxa.getTraduccion("en"));
            } else {
                resultats.put("en", new TraduccionFicha());
            }

            if (fitxa.getTraduccion("de") != null) {
                resultats.put("de", (TraduccionFicha) fitxa.getTraduccion("de"));
            } else {
                resultats.put("de", new TraduccionFicha());
            }

            if (fitxa.getTraduccion("fr") != null) {
                resultats.put("fr", (TraduccionFicha) fitxa.getTraduccion("fr"));
            } else {
                resultats.put("fr", new TraduccionFicha());
            }
            
            // Fin idiomas

            resultats.put("item_youtube", fitxa.getUrlVideo());
            
            resultats.put("item_forum", fitxa.getUrlForo());           

            resultats.put("item_notes", fitxa.getInfo());
            
        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                // model.put("error", "permisos");
            } else {
                // model.put("error", "altres");
                dEx.printStackTrace();
            }
        }
        
        return resultats;
    }

    @RequestMapping(value = "/guardar.htm", method = POST)
    public @ResponseBody
    IdNomTransient guardarFicha(HttpSession session, HttpServletRequest request) {

        IdNomTransient result = null;

        try {
            // TODO pendent de quins camps son obligatoris
            UnidadAdministrativa ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
            String nom = request.getParameter("item_nom");
            String username = request.getParameter("item_codi");
            if (ua == null || nom == null || username == null || "".equals(nom) || "".equals(username)) {
                String error = messageSource.getMessage("persona.error.falten.camps", null, request.getLocale());
                result = new IdNomTransient(-3l, error);
            } else {
                UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();
                UnidadAdministrativa unitatAdministrativa = null;

                try {
                    Long id = Long.parseLong(request.getParameter("item_id"));
                    unitatAdministrativa = unitatAdministrativaDelegate.obtenerUnidadAdministrativa(id);
                } catch (NumberFormatException nfe) {
                    unitatAdministrativa = new UnidadAdministrativa();
                }

                //                 
                // unitatAdministrativa.se
                // unitatAdministrativa.setNombre(nom);
                // unitatAdministrativa.setUsername(username);
                // unitatAdministrativa.setUnidadAdministrativa(ua);
                // unitatAdministrativa.setFunciones(request.getParameter("item_funcions"));
                // unitatAdministrativa.setCargo(request.getParameter("item_carrec"));
                // unitatAdministrativa.setEmail(request.getParameter("item_email"));
                // unitatAdministrativa.setExtensionPublica(request.getParameter("item_epui"));
                // unitatAdministrativa.setNumeroLargoPublico(request.getParameter("item_nlpui"));
                // unitatAdministrativa.setExtensionPrivada(request.getParameter("item_epri"));
                // unitatAdministrativa.setNumeroLargoPrivado(request.getParameter("item_nlpri"));
                // unitatAdministrativa.setExtensionMovil(request.getParameter("item_em"));
                // unitatAdministrativa.setNumeroLargoMovil(request.getParameter("item_nlm"));
                //                

                String ok = messageSource.getMessage("personal.guardat.correcte", null, request.getLocale());
                result = new IdNomTransient(unitatAdministrativa.getId(), ok);
            }

        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                String error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomTransient(-1l, error);
            } else {
                String error = messageSource.getMessage("error.altres", null, request.getLocale());
                result = new IdNomTransient(-2l, error);
                dEx.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Método que comprueba si hay que mostrar los logos
     * 
     * @return boolean
     */
    private boolean mostrarLogosUA() {

        String value = System.getProperty("es.caib.rolsac.logos");
        return !((value == null) || value.equals("N"));
    }

}
