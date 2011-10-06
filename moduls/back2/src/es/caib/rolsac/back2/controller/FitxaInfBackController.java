package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.transients.IdNomTransient;
import org.ibit.rol.sac.model.transients.FichaTransient;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
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

        model.put("menu", 0);
        model.put("submenu", "layout/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 3);
        model.put("titol_escriptori", messageSource.getMessage("submenu.fitxes_informatives", null, request.getLocale()));
        model.put("escriptori", "pantalles/fitxaInf.jsp");
        if (session.getAttribute("unidadAdministrativa") != null) {
            String lang = request.getLocale().getLanguage();
            model.put("idUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getId());
            model.put("nomUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(lang));

            try {
                
                MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
                List<Materia> llistaMateries = new ArrayList<Materia>();
                List<IdNomTransient> llistaMateriesTransient = new ArrayList<IdNomTransient>();
                
                llistaMateries = materiaDelegate.listarMaterias();
    
                for (Materia materia : llistaMateries) {
                    llistaMateriesTransient.add(new IdNomTransient(materia.getId(), materia.getNombreMateria(lang)));
                }
    
                model.put("llistaMateries", llistaMateriesTransient);
                
                HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();  
                List<HechoVital> llistaFetsVitals = new ArrayList<HechoVital>();                
                List<IdNomTransient> llistaFetsVitalsTransient = new ArrayList<IdNomTransient>();
                
                llistaFetsVitals = fetVitalDelegate.listarHechosVitales();
                
                for (HechoVital fetVital : llistaFetsVitals) {
                    TraduccionHechoVital thv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
                    llistaFetsVitalsTransient.add(new IdNomTransient(fetVital.getId(), 
                                                                     thv == null ? null : thv.getNombre()));
                }
                
                model.put("llistaFetsVitals", llistaFetsVitalsTransient);
    
            } catch (DelegateException dEx) {
                if (dEx.getCause() instanceof SecurityException) {
                    // model.put("error", "permisos");//TODO:mensajes de error
                } else {
                    // model.put("error", "altres");
                    dEx.printStackTrace();
                }
            }            
        }
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
        List<IdNomTransient> llistaMateriesTransient = new ArrayList<IdNomTransient>();
        List<IdNomTransient> llistaFetsVitalsTransient = new ArrayList<IdNomTransient>();
        
        FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
        
        Long id = new Long(request.getParameter("id"));
        String lang = request.getLocale().getLanguage();
        
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
            
            //Materias asociadas
            
            if (fitxa.getMaterias() != null) {             
            
                for(Materia materia : fitxa.getMaterias()){                
                    llistaMateriesTransient.add(new IdNomTransient(  materia.getId(), 
                                                                     materia.getNombreMateria(lang)
                                                                           ));                
                   }
                
                resultats.put("materies", llistaMateriesTransient);
            
            } else {
                resultats.put("materies", null);
            } 
           
            //Fets vitals
            
            if (fitxa.getHechosVitales() != null) {             
                
                for(HechoVital fetVital : fitxa.getHechosVitales()){
                    TraduccionHechoVital thv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
                    llistaFetsVitalsTransient.add(new IdNomTransient(  fetVital.getId(), 
                                                                       thv == null ? "" : thv.getNombre()                                                                       
                                                                           ));                
                   }
                
                resultats.put("fetsVitals", llistaFetsVitalsTransient);
            
            } else {
                resultats.put("fetsVitals", null);
            }
            
            
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
        String error = null;

        try {
            // TODO pendent de quins camps son obligatoris
            UnidadAdministrativa ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
            if (ua == null) {//TODO:afegir els camps obligatoris
                error = messageSource.getMessage("fitxes.formulari.error.falten.camps", null, request.getLocale());
                result = new IdNomTransient(-3l, error);
            } else {
                FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
                Ficha fitxa = new Ficha();
                Ficha fitxaOld = new Ficha();
                boolean edicion;
                
                try {
                    Long id = Long.parseLong(request.getParameter("item_id"));
                    fitxaOld = fitxaDelegate.obtenerFicha(id);
                    edicion = true;
                } catch (NumberFormatException nfe) {
                    fitxaOld = null;
                    edicion = false;
                }

                if (edicion){
                    fitxa.setId(fitxaOld.getId());
                    fitxa.setBaner(fitxaOld.getBaner());
                    fitxa.setIcono(fitxaOld.getIcono());
                    fitxa.setImagen(fitxaOld.getImagen());                    
                    fitxa.setResponsable(fitxaOld.getResponsable());
                    fitxa.setForo_tema(fitxaOld.getForo_tema());                    
                    fitxa.setFichasua(fitxaOld.getFichasua());
                    fitxa.setDocumentos(fitxaOld.getDocumentos());
                    fitxa.setEnlaces(fitxaOld.getEnlaces());                    
                }
                
                try {
                    Integer validacion = Integer.parseInt(request.getParameter("item_estat"));
                    fitxa.setValidacion(validacion);
                } catch (NumberFormatException e) {
                    // String error = messageSource.getMessage("error.permisos", null, request.getLocale());
                    error = "L'estat és incorrecte.";
                    throw new NumberFormatException();
                }
                                
                Date data_publicacio = DateUtil.parseDate(request.getParameter("item_data_publicacio"));
                if (data_publicacio != null) {
                    fitxa.setFechaPublicacion(data_publicacio);
                }
                
                Date data_caducitat = DateUtil.parseDate(request.getParameter("item_data_caducitat"));
                if (data_caducitat != null) {
                    fitxa.setFechaCaducidad(data_caducitat);
                }
                
             // Idiomas
                TraduccionFicha tfi;
                IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
                List<String> langs = idiomaDelegate.listarLenguajes();

                for (String lang: langs) {
                    if (edicion) {
                        tfi = (TraduccionFicha) fitxa.getTraduccion(lang);
                        if (tfi == null) {
                            tfi = new TraduccionFicha();
                        }
                    } else {
                        tfi = new TraduccionFicha();
                    }

                    tfi.setTitulo(request.getParameter("item_titol_" + lang));
                    tfi.setDescAbr(request.getParameter("item_des_curta_" + lang));
                    tfi.setDescripcion(request.getParameter("item_des_llarga_" + lang));
                    tfi.setUrl(request.getParameter("item_url_" + lang));
                    
                    fitxa.setTraduccion(lang, tfi);
                }
                // Fin idiomas
                
                fitxa.setFechaActualizacion(new Date());
                
                fitxa.setUrlForo(request.getParameter("item_forum"));
                
                fitxa.setUrlVideo(request.getParameter("item_youtube"));
                
                fitxa.setInfo(request.getParameter("item_notes"));
                
                //Materies
                                
                //Para hacer menos accesos a BBDD se comprueba si es edicion o no, en el primer caso, es bastante
                //probable que se repitan la mayoria de materias.
                if (request.getParameter("materies") != null && !"".equals(request.getParameter("materies"))){
                    MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
                    Set<Materia> materiesNoves = new HashSet<Materia>();
                    String[] codisMateriaNous = request.getParameter("materies").split(",");
                    
                    if (edicion){
                        for (int i = 0; i<codisMateriaNous.length; i++){
                            for (Materia materia: fitxaOld.getMaterias()){
                                if(materia.getId().equals(Long.valueOf(codisMateriaNous[i]))){//materia ya existente
                                    materiesNoves.add(materia);
                                    codisMateriaNous[i] = null;
                                    break;
                                }
                            }                            
                        }                         
                    }                    
                    
                    for (String codiMateria: codisMateriaNous){
                        if (codiMateria != null){
                            materiesNoves.add(materiaDelegate.obtenerMateria(Long.valueOf(codiMateria)));
                        }                        
                    }
                    
                  fitxa.setMaterias(materiesNoves);                                                 
                }
                //Fets vitals
                
                if (request.getParameter("fetsVitals") != null && !"".equals(request.getParameter("fetsVitals"))){
                    HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();
                    Set<HechoVital> fetsVitalsNous = new HashSet<HechoVital>();
                    String[] codisFetsNous = request.getParameter("fetsVitals").split(",");
                    
                    if (edicion){
                        for (int i = 0; i<codisFetsNous.length; i++){
                            for (HechoVital fetVital: fitxaOld.getHechosVitales()){
                                if(fetVital.getId().equals(Long.valueOf(codisFetsNous[i]))){
                                    fetsVitalsNous.add(fetVital);
                                    codisFetsNous[i] = null;
                                    break;
                                }
                            }                            
                        }                         
                    }                    
                    
                    for (String codiFetVital: codisFetsNous){
                        if (codiFetVital != null){
                            fetsVitalsNous.add(fetVitalDelegate.obtenerHechoVital(Long.valueOf(codiFetVital)));
                        }                        
                    }
                    
                  fitxa.setHechosVitales(fetsVitalsNous);                                                 
                }
                
                //Asociacion de ficha con Unidad administrativa                                
                
                fitxaDelegate.grabarFicha(fitxa);
                
                String ok = "Fitxa guardada correctament.";
                result = new IdNomTransient(fitxa.getId(), ok);
            }

        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomTransient(-1l, error);
            } else {
                error = messageSource.getMessage("error.altres", null, request.getLocale());
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
