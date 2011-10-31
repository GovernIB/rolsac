package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import es.caib.rolsac.back2.util.Parametros;
import es.caib.rolsac.back2.util.ParseUtil;

import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionEnlace;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.transients.EnlaceTransient;
import org.ibit.rol.sac.model.transients.FichaUATransient;
import org.ibit.rol.sac.model.transients.IdNomTransient;
import org.ibit.rol.sac.model.transients.FichaTransient;
import org.ibit.rol.sac.model.transients.SeccionTransient;
import org.ibit.rol.sac.model.transients.UnidadTransient;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EnlaceDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
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
            Long codi = ParseUtil.parseLong(request.getParameter("codi"));
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
                                                             DateUtil.formatDate(fitxa.getFechaPublicacion()), 
                                                             DateUtil.formatDate(fitxa.getFechaCaducidad())));
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
        List<FichaUATransient> llistaFichaUATransient = new ArrayList<FichaUATransient>();
        List<EnlaceTransient> llistaEnllassosTransient = new ArrayList<EnlaceTransient>();
        
        FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
        
        Long id = new Long(request.getParameter("id"));
        String lang = request.getLocale().getLanguage();
        
        try {
        
            Ficha fitxa = fitxaDelegate.obtenerFicha(id);
        
            resultats.put("item_id", fitxa.getId());

            resultats.put("item_estat", fitxa.getValidacion());

            resultats.put("item_data_publicacio", DateUtil.formatDate(fitxa.getFechaPublicacion()));

            resultats.put("item_data_caducitat", DateUtil.formatDate(fitxa.getFechaCaducidad()));

            //resultats.put("caducat","S");
            
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
                    llistaFetsVitalsTransient.add(new IdNomTransient(fetVital.getId(), 
                                                                     thv == null ? "" : thv.getNombre()                                                                       
                                                                     ));                
                   }
                
                resultats.put("fetsVitals", llistaFetsVitalsTransient);
            
            } else {
                resultats.put("fetsVitals", null);
            }
            
            //Relació Ficha-Seccio-UA
            
            if (fitxa.getFichasua() != null){
                for(FichaUA fichaUA : fitxaDelegate.listFichasUA(fitxa.getId())){
                    TraduccionSeccion tse = (TraduccionSeccion) fichaUA.getSeccion().getTraduccion(lang);
                    llistaFichaUATransient.add(new FichaUATransient(fichaUA.getId(),
                                                                    fichaUA.getUnidadAdministrativa().getId(),
                                                                    fichaUA.getUnidadAdministrativa().getNombreUnidadAdministrativa(lang),
                                                                    fichaUA.getSeccion().getId(),
                                                                    tse == null ? "" : tse.getNombre(),
                                                                    null,
                                                                    null,
                                                                    fichaUA.getOrden(),
                                                                    fichaUA.getOrdenseccion())
                                                                    );                    
                }
                resultats.put("seccUA", llistaFichaUATransient);
            } else {
                resultats.put("seccUA", null);
            }
           
            //Enllaços
            
          
            if (fitxa.getEnlaces() != null){
                for (Enlace enllas : fitxa.getEnlaces()){
                    llistaEnllassosTransient.add(new EnlaceTransient(enllas.getId(),
                                                                    enllas.getOrden(),
                                                                    enllas.getTraduccionMap()));
                    
                }
                resultats.put("enllassos", llistaEnllassosTransient);
            } else {
                resultats.put("enllassos", null);
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
    public @ResponseBody IdNomTransient guardarFicha(HttpSession session, HttpServletRequest request) {

        IdNomTransient result = null;
        String error = null;
        Integer validacion = null;

        try {            

            // Validacio previa
            UnidadAdministrativa ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
            String titolCatala = request.getParameter("item_titol_ca");

            //Validacio del estat 
            
            try {
                validacion = Integer.parseInt(request.getParameter("item_estat"));                
            } catch (NumberFormatException e) {
                error = "L'estat és incorrecte.";
                throw new NumberFormatException();
            }            
            
            if (ua == null || titolCatala == null || "".equals(titolCatala)) {//Camps obligatoris
                error = messageSource.getMessage("fitxes.formulari.error.falten.camps", null, request.getLocale());
                result = new IdNomTransient(-3l, error);
            } else {
                FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
                Ficha fitxa = new Ficha();
                Ficha fitxaOld = new Ficha();
                boolean edicion;
                
                fitxa.setValidacion(validacion);                                               
                
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
                                if(materia.getId().equals(ParseUtil.parseLong(codisMateriaNous[i]))){//materia ya existente
                                    materiesNoves.add(materia);
                                    codisMateriaNous[i] = null;
                                    break;
                                }
                            }                            
                        }                         
                    }                    
                    
                    for (String codiMateria: codisMateriaNous){
                        if (codiMateria != null){
                            materiesNoves.add(materiaDelegate.obtenerMateria(ParseUtil.parseLong(codiMateria)));
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
                                if(fetVital.getId().equals(ParseUtil.parseLong(codisFetsNous[i]))){
                                    fetsVitalsNous.add(fetVital);
                                    codisFetsNous[i] = null;
                                    break;
                                }
                            }                            
                        }                         
                    }                    
                    
                    for (String codiFetVital: codisFetsNous){
                        if (codiFetVital != null){
                            fetsVitalsNous.add(fetVitalDelegate.obtenerHechoVital(ParseUtil.parseLong(codiFetVital)));
                        }                        
                    }
                    
                  fitxa.setHechosVitales(fetsVitalsNous);                                                 
                }
                
                Long idFitxa = fitxaDelegate.grabarFicha(fitxa);
                
                //Asociacion de ficha con Unidad administrativa                                
                
              
                String[] codisSeccUaNous = request.getParameter("seccUA").split(",");                                      
                boolean esborrarFichaUA = true;
                
                if (edicion){
                    for (FichaUA fichaUA: fitxaOld.getFichasua()){
                        esborrarFichaUA = true;
                        for (int i = 0; i<codisSeccUaNous.length; i++){
                            if (codisSeccUaNous[i] != null){//Per a no repetir cerques
                                String[] seccUA = codisSeccUaNous[i].split("#"); //En cas d'edicio es necesario verificar si les relacions anteriors se mantenen
                                if(fichaUA.getId().equals(ParseUtil.parseLong(seccUA[0]))){
                                    esborrarFichaUA = false;
                                    codisSeccUaNous[i] = null;
                                    break;
                                }    
                            }
                        }
                        if (esborrarFichaUA){
                            fitxaDelegate.borrarFichaUA(fichaUA.getId());
                        }                            
                    }
                }
                
                //Tots els que tenen id = -1, son nous i se poden afegir directament
                for (String codiSeccUa: codisSeccUaNous){
                    if (codiSeccUa != null){
                        String[] seccUA = codiSeccUa.split("#");                        
                        Long idSeccion = ParseUtil.parseLong(seccUA[1]);
                        Long idUA = ParseUtil.parseLong(seccUA[2]);
                        
                        fitxaDelegate.crearFichaUA(idUA, idSeccion, idFitxa);

                        String pidip = System.getProperty("es.caib.rolsac.pidip");
                        if(!((pidip == null) || pidip.equals("N"))) {
                            // Si se anyade una ficha a la seccion Actualidad, se añade tambien a Portada Actualidad (PIDIP)
                            if (idSeccion.longValue()== new Long(Parametros.ESDEVENIMENTS).longValue())
                            {   //comprobamos  antes si ya exite la ficha en actualidad  en portada en cuyo caso no la insertamos para no duplicarla.
                                int existe=0;Long ficodi;Long codficha;
                                List listac = fitxaDelegate.listarFichasSeccionTodas(new Long(Parametros.PORTADAS_ACTUALIDAD));
                                Iterator iter = listac.iterator();
                                while (iter.hasNext())
                                {
                                    Ficha ficac=(Ficha)iter.next();
                                    if((""+ficac.getId()).equals(""+idFitxa))
                                        existe=1;
                                }
                                if (existe==0)
                                    fitxaDelegate.crearFichaUA(idUA, new Long(Parametros.PORTADAS_ACTUALIDAD), idFitxa);
                            }
                        }                                                
                    }
                }                                                                                                           
                
                //Tractament d'enllaçós                
                
                Enumeration<String> nomsParametres = request.getParameterNames();
                
                List<Enlace> enllassosNous = new ArrayList<Enlace>();
                
                while(nomsParametres.hasMoreElements()) {
                    
                    String nomParameter = (String)nomsParametres.nextElement();                    
                    String[] elements = nomParameter.split("_");
                    
                    if (elements[0].equals("enllas") && elements[1].equals("id")){
                        //En aquest cas, elements[2] es igual al id del enllas                                                 
                                            
                        Enlace enllas = new Enlace();                                           
                        
                        if (elements[2].charAt(0) == 't'){//Element nou, amb id temporal
                            enllas.setId(null);                            
                        } else {
                            enllas.setId(ParseUtil.parseLong(request.getParameter(nomParameter)));
                        }
                        
                        enllas.setOrden(ParseUtil.parseLong(request.getParameter("enllas_orden_" + elements[2])));                        
                        
                        for (String lang: langs){
                         
                            TraduccionEnlace traduccio = new TraduccionEnlace();
                            
                            traduccio.setTitulo(request.getParameter("enllas_nombre_" + lang + "_" + elements[2]));
                            traduccio.setEnlace(request.getParameter("enllas_url_" + lang + "_" + elements[2]));
                            
                            enllas.setTraduccion(lang, traduccio);
                            
                        }
                        
                        enllas.setFicha(fitxa);
                        
                        enllassosNous.add(enllas);
                        
                    }                                                            
                }
                    
                EnlaceDelegate enllasDelegate = DelegateUtil.getEnlaceDelegate();
                
                for (Enlace enllas: enllassosNous){
                    enllasDelegate.grabarEnlace(enllas, null, idFitxa);
                }                
                
                //Cal triar dels enllassos antics que pogues haver, quins se conserven i quins no                
                if (edicion){
                    
                    List<Enlace> enllassosEliminar = fitxaOld.getEnlaces();                                    
                    
                    for(Enlace enllas: enllassosNous){
                        for (Iterator<Enlace> it = enllassosEliminar.iterator(); it.hasNext(); ){
                            if (it.next().getId().equals(enllas.getId())){
                                it.remove();
                            }
                        }
                    }                    
                    
                    for (Enlace enllas: enllassosEliminar){
                        enllasDelegate.borrarEnlace(enllas.getId());
                    }
                    
                }                                                                                
                
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
    
    @RequestMapping(value = "/seccions.htm", method = POST)
    public @ResponseBody Map<String, Object> arbreSeccions(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();
        List<Seccion> llistaSeccions = new ArrayList<Seccion>();
        List<SeccionTransient> llistaSeccionsTransient = new ArrayList<SeccionTransient>();
        
        SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
        
        String lang = request.getLocale().getLanguage();
        
        try {
        
            if (request.getParameter("pare") == null || "".equals(request.getParameter("pare"))){ 
                llistaSeccions = seccioDelegate.listarSeccionesRaizPerfil();
            } else {
                llistaSeccions = seccioDelegate.listarHijosSeccion(ParseUtil.parseLong(request.getParameter("pare")));
            }
            
            for (Seccion seccio: llistaSeccions){
                TraduccionSeccion tse = (TraduccionSeccion) seccio.getTraduccion(lang);
                llistaSeccionsTransient.add(new SeccionTransient(seccio.getId(),
                                                                tse == null ? "" : tse.getNombre().length() > 25 ? tse.getNombre().substring(0, 21)+"..." : tse.getNombre(),
                                                                seccio.getPadre() == null ? null : seccio.getPadre().getId(),                                                                        
                                                                seccioDelegate.listarHijosSeccion(seccio.getId()).size() > 0 ? true : false                                                                
                                                               ));
            }

            resultats.put("llistaSeccions", llistaSeccionsTransient);
            
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
    
    @RequestMapping(value = "/unitats.htm", method = POST)
    public @ResponseBody Map<String, Object> arbreUnitats(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();
        List<UnidadAdministrativa> llistaUnitats = new ArrayList<UnidadAdministrativa>();
        List<UnidadTransient> llistaUnitatsTransient = new ArrayList<UnidadTransient>();
        
        UnidadAdministrativaDelegate unitatDelegate = DelegateUtil.getUADelegate();
        
        String lang = request.getLocale().getLanguage();
        
        try {
        
            if (request.getParameter("pare") == null || "".equals(request.getParameter("pare"))){ 
                llistaUnitats = unitatDelegate.listarUnidadesAdministrativasRaiz();
            } else {
                llistaUnitats = unitatDelegate.listarHijosUA(ParseUtil.parseLong(request.getParameter("pare")));
            }
            
            for (UnidadAdministrativa unitat: llistaUnitats){
                String nomUnitat = unitat.getNombreUnidadAdministrativa(lang);
                llistaUnitatsTransient.add(new UnidadTransient(unitat.getId(),
                                                                nomUnitat == null ? "" : nomUnitat.length() > 25 ? nomUnitat.substring(0, 21)+"..." : nomUnitat,
                                                                unitat.getPadre() == null ? null : unitat.getPadre().getId(),                                                                        
                                                                unitatDelegate.listarHijosUA(unitat.getId()).size() > 0 ? true : false                                                                
                                                               ));
            }

            resultats.put("llistaUnitats", llistaUnitatsTransient);
            
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
