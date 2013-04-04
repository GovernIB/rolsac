package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionEnlace;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.EnlaceDTO;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.dto.FichaUADTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.SeccionDTO;
import org.ibit.rol.sac.model.dto.UnidadDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.EnlaceDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.Parametros;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.DateUtils;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/fitxainf/")
public class FitxaInfBackController extends PantallaBaseController {
	
	private final String IDIOMA_ORIGEN_TRADUCTOR = "ca";	
	private static final String URL_PREVISUALIZACION = "es.caib.rolsac.previsualitzacio.fitxa.url"; 	
	private static Log log = LogFactory.getLog(FitxaInfBackController.class);
	
    @RequestMapping(value = "/fitxainf.do", method = GET)
    public String pantallaFitxes(Map<String, Object> model, HttpServletRequest request, HttpSession session) {
    	
        model.put("menu", 0);
        model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 3);
        model.put("titol_escriptori", messageSource.getMessage("submenu.fitxes_informatives", null, request.getLocale()));
        model.put("escriptori", "pantalles/fitxaInf.jsp");
        request.setAttribute("urlPrevisualitzacio", System.getProperty(URL_PREVISUALIZACION));
        
        String lang = request.getLocale().getLanguage();
        
        if (session.getAttribute("unidadAdministrativa") != null) {
            model.put("idUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getId());
            model.put("nomUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(lang));
        }
        
        try {
        	
        	MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
        	List<Materia> llistaMateries = new ArrayList<Materia>();
        	List<IdNomDTO> llistaMateriesDTO = new ArrayList<IdNomDTO>();
        	
        	llistaMateries = castList(Materia.class, materiaDelegate.listarMaterias() );
        	
        	for (Materia materia : llistaMateries) {
        		llistaMateriesDTO.add(new IdNomDTO(materia.getId(), materia.getNombreMateria(lang)));                }
        	
        	model.put("llistaMateries", llistaMateriesDTO);
        	
        	HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();  
        	List<HechoVital> llistaFetsVitals = new ArrayList<HechoVital>();                
        	List<IdNomDTO> llistaFetsVitalsDTO = new ArrayList<IdNomDTO>();
        	
        	llistaFetsVitals = fetVitalDelegate.listarHechosVitales();
        	
        	for (HechoVital fetVital : llistaFetsVitals) {
        		TraduccionHechoVital thv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
        		llistaFetsVitalsDTO.add(new IdNomDTO(fetVital.getId(), 
        				thv == null ? null : thv.getNombre()));
        	}
        	
        	model.put("llistaFetsVitals", llistaFetsVitalsDTO);
        	
        	PublicoObjetivoDelegate publicObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();  
        	List<PublicoObjetivo> llistaPublicsObjectiu = new ArrayList<PublicoObjetivo>();                
        	List<IdNomDTO> llistaPublicsObjectiuDTO = new ArrayList<IdNomDTO>();
        	
        	llistaPublicsObjectiu = publicObjectiuDelegate.listarPublicoObjetivo();
        	
        	for (PublicoObjetivo publicObjectiu : llistaPublicsObjectiu) {
        		TraduccionPublicoObjetivo tpo = (TraduccionPublicoObjetivo) publicObjectiu.getTraduccion(lang);
        		llistaPublicsObjectiuDTO.add(new IdNomDTO(publicObjectiu.getId(), 
        				tpo == null ? null : tpo.getTitulo()));
        	}
        	
        	model.put("llistaPublicsObjectiu", llistaPublicsObjectiuDTO);
        	
        } catch (DelegateException dEx) {
        	if (dEx.isSecurityException()) {
        		// model.put("error", "permisos");//TODO:mensajes de error
        		log.error("Error de permisos " + ExceptionUtils.getStackTrace(dEx));
        	} else {
        		// model.put("error", "altres");
        		log.error(ExceptionUtils.getStackTrace(dEx));
        	}
        }     
        
		loadIndexModel (model, request);	
        return "index";
    }

    @RequestMapping(value = "/llistat.do", method = POST)
    public @ResponseBody Map<String, Object> llistatFitxes(HttpServletRequest request, HttpSession session) {
        
        //List<Ficha> llistaFitxes = new ArrayList<Ficha>();
        List<FichaDTO> llistaFitxesDTO = new ArrayList<FichaDTO>();
        Map<String, Object> resultats = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> tradMap = new HashMap<String, String>();

        String lang = request.getLocale().getLanguage();
        
		//Obtenemos la ordenaci�n por par�metro
		String campoOrdenacion = request.getParameter("ordreCamp");
		String orden = request.getParameter("ordreTipus");
		
        UnidadAdministrativa ua = null;
        Long fetVital = null;
        Long materia = null;
        Long publicObjectiu = null;
        
        if (session.getAttribute("unidadAdministrativa") != null) {
        	ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
        }
        
		String idStr = request.getParameter("codi");
		Long id = -1l;
								
		if ( idStr != null && StringUtils.isNumeric(idStr.trim()) )
			id = ParseUtil.parseLong( idStr.trim() );
		
		paramMap.put("id", idStr != null ? id : null );
        
        if (request.isUserInRole("sacoper")) {
            paramMap.put("validacion", ""); // En el back antiguo estaba asi.
        } else {
            String estat = request.getParameter("estat");
            try {
                Integer validacion = Integer.parseInt(estat);
                paramMap.put("validacion", validacion);
            } catch (NumberFormatException e) {
            }
        }
        
        try {
            materia = Long.parseLong(request.getParameter("materia"));
        } catch (NumberFormatException e){}
        
        try {
            fetVital = Long.parseLong(request.getParameter("fetVital"));
        } catch (NumberFormatException e){}
        
        try {
        	publicObjectiu = Long.parseLong(request.getParameter("publicObjectiu"));
        } catch (NumberFormatException e){}

        boolean uaFilles = "1".equals(request.getParameter("uaFilles"));
        boolean uaMeves = "1".equals(request.getParameter("uaMeves"));
        
        // Textes (en todos los campos todos los idiomas)
        String textes = request.getParameter("textes");
        
        if ( textes != null && !"".equals(textes) ) {
        	
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

        ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
        
		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);                
		
        try {
        	
            FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();            
            
			resultadoBusqueda = fitxaDelegate.buscarFichas(
					paramMap, tradMap, ua, fetVital, materia, publicObjectiu,					
					uaFilles, uaMeves, campoOrdenacion, orden, pagPag, pagRes);
			            
            for (Ficha fitxa : castList(Ficha.class, resultadoBusqueda.getListaResultados() ) ) {
                TraduccionFicha tfi = (TraduccionFicha) fitxa.getTraduccion(request.getLocale().getLanguage());
                llistaFitxesDTO.add(new FichaDTO(fitxa.getId(), 
                                                             tfi == null ? null : tfi.getTitulo(), 
                                                             DateUtils.formatDate(fitxa.getFechaPublicacion()), 
                                                             DateUtils.formatDate(fitxa.getFechaCaducidad()),
                                                             DateUtils.formatDate(fitxa.getFechaActualizacion()),
                                                             fitxa.isVisible()));
            }   
            
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                // model.put("error", "permisos");
            	log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
            } else {
                // model.put("error", "altres");
            	log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }
        
        resultats.put("total",  resultadoBusqueda.getTotalResultados());
        resultats.put("nodes", llistaFitxesDTO);
        
        return resultats;
    
    }

    @RequestMapping(value = "/pagDetall.do", method = POST)
    public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();
        List<IdNomDTO> llistaMateriesDTO = new ArrayList<IdNomDTO>();
        List<IdNomDTO> llistaFetsVitalsDTO = new ArrayList<IdNomDTO>();
        List<IdNomDTO> llistaPublicObjectiuDTO = new ArrayList<IdNomDTO>();
        List<FichaUADTO> llistaFichaUADTO = new ArrayList<FichaUADTO>();
        List<EnlaceDTO> llistaEnllassosDTO = new ArrayList<EnlaceDTO>();
        
        FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
        
        Long id = new Long(request.getParameter("id"));
        String lang = request.getLocale().getLanguage();
        
        try {
        
            Ficha fitxa = fitxaDelegate.obtenerFicha(id);
        
            resultats.put("item_id", fitxa.getId());

            resultats.put("item_estat", fitxa.getValidacion());

            resultats.put("item_data_publicacio", DateUtils.formatDateSimpleTime(fitxa.getFechaPublicacion()));

            resultats.put("item_data_caducitat", DateUtils.formatDateSimpleTime(fitxa.getFechaCaducidad()));

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
            
            // Documentos relacionados
			if (fitxa.getDocumentos() != null) {
				Map<String, Object> mapDoc;
				List<Map<String, Object>> llistaDocuments = new ArrayList<Map<String, Object>>();
			
				for(Documento doc: fitxa.getDocumentos()) {
					if (doc != null) {
						// Montar map solo con los campos 'titulo' de las traducciones del documento.
						Map<String, String> titulos = new HashMap<String, String>();
						IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
						List<String> idiomas = idiomaDelegate.listarLenguajes();
						String nombre;
						TraduccionDocumento traDoc;
						
						for (String idioma: idiomas) {
							traDoc = (TraduccionDocumento) doc.getTraduccion(idioma);
							if (traDoc != null && traDoc.getTitulo() != null) {
								nombre = traDoc.getTitulo();
							} else {
								nombre = "";
							}
							titulos.put(idioma, nombre);
						}
		
						mapDoc = new HashMap<String, Object>(3);
						mapDoc.put("id", doc.getId());
						mapDoc.put("orden", doc.getOrden());
						mapDoc.put("nombre", titulos);
						
		                llistaDocuments.add(mapDoc);
					} else {
						log.error("La fitxa " + fitxa.getId() + " te un document null.");
					}
	            }
				
				resultats.put("documents", llistaDocuments);
			} else {
	            resultats.put("documents", null);
	        } 
            // Fin documentos relacionados
            
            
            // Icona
            if (fitxa.getIcono() != null){
            	resultats.put("item_icona_enllas_arxiu", "fitxainf/archivo.do?id=" + fitxa.getId() + "&tipus=1");
                resultats.put("item_icona", fitxa.getIcono().getNombre());
            } else {
            	resultats.put("item_icona_enllas_arxiu", "");
                resultats.put("item_icona", "");
            }
            
            // Banner
            if (fitxa.getBaner() != null) {
            	resultats.put("item_banner_enllas_arxiu", "fitxainf/archivo.do?id=" + fitxa.getId() + "&tipus=2");
                resultats.put("item_banner", fitxa.getBaner().getNombre());
            } else {
                resultats.put("item_banner_enllas_arxiu", "");
                resultats.put("item_banner", "");
            }  
            
            // Imatge
            if (fitxa.getImagen() != null) {
            	resultats.put("item_imatge_enllas_arxiu", "fitxainf/archivo.do?id=" + fitxa.getId() + "&tipus=3");
            	resultats.put("item_imatge", fitxa.getImagen().getNombre());
            } else {
            	resultats.put("item_imatge_enllas_arxiu", "");
            	resultats.put("item_imatge", "");
            }
            
            resultats.put("item_youtube", fitxa.getUrlVideo());
            
            resultats.put("item_forum", fitxa.getUrlForo());           

            resultats.put("item_responsable", fitxa.getResponsable());
            
            resultats.put("item_notes", fitxa.getInfo());
            
            //Materias asociadas
            
            if (fitxa.getMaterias() != null) {             
            
                for(Materia materia : fitxa.getMaterias()){                
                	llistaMateriesDTO.add(new IdNomDTO(  materia.getId(), 
                                                                     materia.getNombreMateria(lang)
                                                                           ));                
                   }
                
                resultats.put("materies", llistaMateriesDTO);
            
            } else {
                resultats.put("materies", null);
            } 
           
            //Fets vitals
            
            if (fitxa.getHechosVitales() != null) {             
                
                for(HechoVital fetVital : fitxa.getHechosVitales()){
                    TraduccionHechoVital thv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
                    llistaFetsVitalsDTO.add(new IdNomDTO(fetVital.getId(), 
                                                                     thv == null ? "" : thv.getNombre()                                                                       
                                                                     ));                
                   }
                
                resultats.put("fetsVitals", llistaFetsVitalsDTO);
            
            } else {
                resultats.put("fetsVitals", null);
            }
            
            //Publics Objectiu
            
            if (fitxa.getPublicosObjetivo() != null) {             
                
                for(PublicoObjetivo publicObj : fitxa.getPublicosObjetivo()){
                	TraduccionPublicoObjetivo tpob = (TraduccionPublicoObjetivo) publicObj.getTraduccion(lang);
                    llistaPublicObjectiuDTO.add(new IdNomDTO(publicObj.getId(), 
                    		tpob == null ? "" : tpob.getTitulo()));                
                   }
                
                resultats.put("publicsObjectiu", llistaPublicObjectiuDTO);
            
            } else {
                resultats.put("publicsObjectiu", null);
            }
            
            //Relaci� Ficha-Seccio-UA
            
            if (fitxa.getFichasua() != null){
                for(FichaUA fichaUA : fitxaDelegate.listFichasUA(fitxa.getId())){
                    TraduccionSeccion tse = (TraduccionSeccion) fichaUA.getSeccion().getTraduccion(lang);
                    llistaFichaUADTO.add(new FichaUADTO(fichaUA.getId(),
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
                resultats.put("seccUA", llistaFichaUADTO);
            } else {
                resultats.put("seccUA", null);
            }
           
            //Enlla�os
            if (fitxa.getEnlaces() != null){
                for (Enlace enllas : fitxa.getEnlaces()){
                	if (enllas != null) {
						llistaEnllassosDTO.add(new EnlaceDTO(enllas.getId(),
								enllas.getOrden(), enllas.getTraduccionMap()));
                	}
                    
                }
                resultats.put("enllassos", llistaEnllassosDTO);
            } else {
                resultats.put("enllassos", null);
            }
            
        } catch (DelegateException dEx) {
        	log.error("Error: " + ExceptionUtils.getStackTrace(dEx));
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
        }    
                
        return resultats;
    }

    @RequestMapping(value = "/guardar.do", method = POST)
    public ResponseEntity<String> guardarFicha(HttpSession session, HttpServletRequest request) {
    	/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result = null;
        
        Integer validacion = null;
        
        String error = null;

        Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
        
        try {      
        	
        	//Aqui nos llegar�a un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		//Iremos recopilando los parametros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
    		
    		List<FileItem> items = castList(FileItem.class, UploadUtil.obtenerServletFileUpload().parseRequest(request));

    		Set<String> enllasos = new HashSet<String>();
    		Set<String> docsIds = new HashSet<String>();
    		for (FileItem item : items) {
    			if (item.isFormField()) {
    				if (item.getFieldName().startsWith("enllas_")){
    					enllasos.add(item.getFieldName());
    				} 
    				if (item.getFieldName().startsWith("documents_id_")){
    					docsIds.add(item.getFieldName());
    				} 
    				valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
    			} else {
    				ficherosForm.put(item.getFieldName(), item);    				
    			}
    		}

    		FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
    		Ficha fitxa = new Ficha();
    		
            
            // Comprovam camps obligatoris 
    		String titolCatala = valoresForm.get("item_titol_ca");
//            UnidadAdministrativa ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
//            if ( ua == null || titolCatala == null || "".equals(titolCatala)) {
    		if (titolCatala == null || "".equals(titolCatala)) {
            	error = messageSource.getMessage("fitxes.formulari.error.falten.camps", null, request.getLocale());
                result = new IdNomDTO(-3l, error);
                return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
            }
            if (valoresForm.get("seccUA") == null || valoresForm.get("seccUA").split("#").length < 1) {
            	error = messageSource.getMessage("fitxes.missatge.es_necessari", null, request.getLocale());
                result = new IdNomDTO(-3l, error);
                return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
            }
            if (valoresForm.get("publicsObjectiu") == null || valoresForm.get("publicsObjectiu").equals("")) {
            	error = messageSource.getMessage("fitxes.missatge.es_necessari_public", null, request.getLocale());
                result = new IdNomDTO(-3l, error);
                return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
            }
            // Tiempos para trazas
            Date startTrace;
            long execTime;
            
            
            Ficha fitxaOld = null;
            boolean edicion;
            Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
			if (id != null) {
			    log.debug("Inici de obtenerFicha(" + id + ")");
			    startTrace = new Date();
				fitxaOld = fitxaDelegate.obtenerFicha(id);
				execTime = new Date().getTime() - startTrace.getTime();
                log.debug("Temps d'execucio de obtenerFicha(" + id + "): " + execTime + " milisegons.");
				edicion = true;
			} else {									
				fitxaOld = null;
				edicion = false;
			}
    		
			
			// Es comprova que l'estat es un estat permes
            try {
				validacion = Integer.parseInt(valoresForm.get("item_estat"));
				// Comprobar que no se haya cambiado la validacion/estado siendo operador
            	if (request.isUserInRole("sacoper") && fitxaOld != null && !fitxaOld.getValidacion().equals(validacion)) {
            		throw new DelegateException(new SecurityException());
            	}
            	fitxa.setValidacion(validacion);
			} catch (NumberFormatException e) {
				error = messageSource.getMessage("proc.error.estat.incorrecte", null, request.getLocale());
				throw new NumberFormatException();
			}
			
			
			if (edicion) {
				// Mantenim els valors que te la fitxa.
				fitxa.setId(fitxaOld.getId());
                fitxa.setBaner(fitxaOld.getBaner());
                fitxa.setIcono(fitxaOld.getIcono());
                fitxa.setImagen(fitxaOld.getImagen());                    
                fitxa.setResponsable(fitxaOld.getResponsable());
                fitxa.setForo_tema(fitxaOld.getForo_tema());                    
                fitxa.setFichasua(fitxaOld.getFichasua());
                fitxa.setDocumentos(fitxaOld.getDocumentos());
                fitxa.setEnlaces(fitxaOld.getEnlaces());
                fitxa.setMaterias(fitxaOld.getMaterias());
                fitxa.setHechosVitales(fitxaOld.getHechosVitales());
                fitxa.setPublicosObjetivo(fitxaOld.getPublicosObjetivo());
			} 
			
			if (!StringUtils.isEmpty(valoresForm.get("item_data_publicacio"))) {
	            Date data_publicacio = DateUtils.parseDateSimpleTime(valoresForm.get("item_data_publicacio"));
	            if (data_publicacio == null) throw new ParseException("error.data_publicacio", 0);
	            fitxa.setFechaPublicacion(data_publicacio);
			}
            
			if (!StringUtils.isEmpty(valoresForm.get("item_data_caducitat"))) {
	            Date data_caducitat = DateUtils.parseDateSimpleTime(valoresForm.get("item_data_caducitat"));
	            if (data_caducitat == null) throw new ParseException("error.data_caducitat", 0);
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

                tfi.setTitulo( RolUtil.limpiaCadena(valoresForm.get("item_titol_" + lang)) );
                tfi.setDescAbr( RolUtil.limpiaCadena(valoresForm.get("item_des_curta_" + lang)) );
                tfi.setDescripcion( RolUtil.limpiaCadena(valoresForm.get("item_des_llarga_" + lang)) );
                tfi.setUrl(valoresForm.get("item_url_" + lang));
                
                fitxa.setTraduccion(lang, tfi);
            }
            // Fin idiomas
            
            
            fitxa.setFechaActualizacion(new Date());
            
            // Icona
    		FileItem fileIcona = ficherosForm.get("item_icona");
    		if (fileIcona != null && fileIcona.getSize() > 0 ) {
    			fitxa.setIcono(UploadUtil.obtenerArchivo(fitxa.getIcono(), fileIcona));
    		} else
    		// borrar fichero si se solicita
    		if (valoresForm.get("item_icona_delete") != null && !"".equals(valoresForm.get("item_icona_delete"))){
    			fitxa.setIcono(null);
    		}
            
    		// Banner
    		FileItem fileBanner = ficherosForm.get("item_banner");
    		if (fileBanner != null && fileBanner.getSize() > 0 ) {
    			fitxa.setBaner(UploadUtil.obtenerArchivo(fitxa.getBaner(), fileBanner));
    		} else
    		// borrar fichero si se solicita
    		if (valoresForm.get("item_banner_delete") != null && !"".equals(valoresForm.get("item_banner_delete"))){
    			fitxa.setBaner(null);
    		}
    		
    		// Imatge
    		FileItem fileImatge = ficherosForm.get("item_imatge");
    		if (fileImatge != null && fileImatge.getSize() > 0 ) {
    			fitxa.setImagen(UploadUtil.obtenerArchivo(fitxa.getImagen(), fileImatge));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_imatge_delete") != null && !"".equals(valoresForm.get("item_imatge_delete"))){
    			fitxa.setImagen(null);
    		}
    		
    		
            fitxa.setUrlForo(valoresForm.get("item_forum"));
            
            fitxa.setUrlVideo(valoresForm.get("item_youtube"));
            
            fitxa.setResponsable(valoresForm.get("item_responsable"));
            
            fitxa.setInfo(valoresForm.get("item_notes"));

            
            //Materies
            
            //Para hacer menos accesos a BBDD se comprueba si es edicion o no, en el primer caso, es bastante
            //probable que se repitan la mayoria de materias.
            if (isModuloModificado("modulo_materias_modificado", valoresForm)) {
                if (valoresForm.get("materies") != null && !"".equals(valoresForm.get("materies"))){
                    MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
                    Set<Materia> materiesNoves = new HashSet<Materia>();
                    String[] codisMateriaNous = valoresForm.get("materies").split(",");
                    
                    if (edicion){
                        for (int i = 0; i < codisMateriaNous.length; i++) {
                            for (Materia materia : fitxaOld.getMaterias()) {
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
                            Long codi = ParseUtil.parseLong(codiMateria);
                            log.debug("Inici de obtenerMateria(" + codi + ")");
                            startTrace = new Date();
                            materiesNoves.add(materiaDelegate.obtenerMateria(codi));
                            execTime = new Date().getTime() - startTrace.getTime();
                            log.debug("Temps d'execucio de obtenerMateria(" + codi + "): " + execTime + " milisegons.");
                        }                        
                    }
                    
                  fitxa.setMaterias(materiesNoves);                                                 
                } else {
                    fitxa.setMaterias(new HashSet<Materia>());
                }
            }
            
            //Fets vitals
                
            if (isModuloModificado("modulo_hechos_modificado", valoresForm)) {
                if (valoresForm.get("fetsVitals") != null && !"".equals(valoresForm.get("fetsVitals"))){
                    HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();
                    Set<HechoVital> fetsVitalsNous = new HashSet<HechoVital>();
                    String[] codisFetsNous = valoresForm.get("fetsVitals").split(",");
                    
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
                            Long codi = ParseUtil.parseLong(codiFetVital);
                            log.debug("Inici de obtenerHechoVital(" + codi + ")");
                            startTrace = new Date();
                            fetsVitalsNous.add(fetVitalDelegate.obtenerHechoVital(codi));
                            execTime = new Date().getTime() - startTrace.getTime();
                            log.debug("Temps d'execucio de obtenerHechoVital(" + codi + "): " + execTime + " milisegons.");
                        }                        
                    }
                    
                    fitxa.setHechosVitales(fetsVitalsNous);                                                 
                } else {
                    fitxa.setHechosVitales(new HashSet<HechoVital>());
                }
            }

            //Public Objectiu
            
            if (isModuloModificado("modul_public_modificat", valoresForm)){
            	if (valoresForm.get("publicsObjectiu") != null && !"".equals(valoresForm.get("publicsObjectiu"))){
	            	PublicoObjetivoDelegate publicObjDelegate = DelegateUtil.getPublicoObjetivoDelegate();
	                Set<PublicoObjetivo> publicsNous = new HashSet<PublicoObjetivo>();
	                String[] codisPublicsNous = valoresForm.get("publicsObjectiu").split(",");
	                
	                if (edicion){
	                    for (int i = 0; i<codisPublicsNous.length; i++){
	                        for (PublicoObjetivo pob: fitxaOld.getPublicosObjetivo()){
	                            if(pob.getId().equals(ParseUtil.parseLong(codisPublicsNous[i]))){
	                            	publicsNous.add(pob);
	                            	codisPublicsNous[i] = null;
	                                break;
	                            }
	                        }                            
	                    }                         
	                }                    
	                
	                for (String codiPublic: codisPublicsNous){
	                    if (codiPublic != null){
	                        Long codi = ParseUtil.parseLong(codiPublic);
	                        log.debug("Inici de ObtenirPublicObjectiu(" + codi + ")");
	                        startTrace = new Date();
	                        publicsNous.add(publicObjDelegate.obtenerPublicoObjetivo(codi));
	                        execTime = new Date().getTime() - startTrace.getTime();
	                        log.debug("Temps d'execucio de ObtenirPublicObjectiu(" + codi + "): " + execTime + " milisegons.");
	                    }                        
	                }
	                
	                fitxa.setPublicosObjetivo(publicsNous);                                                 
	            }else {
	                fitxa.setPublicosObjetivo(new HashSet<PublicoObjetivo>());
	            }
            }
           // Documents
	        Documento document;
	        DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
	        List<Documento> documents = new ArrayList<Documento>();
	        Map <String,String[]> actulitzadorMap = new HashMap<String, String[]>();
	
	        // obtenim  els documents i els seus ordres
            for (Iterator<String> iterator = docsIds.iterator(); iterator.hasNext();) {
        	    String docParameter = (String)iterator.next();
                String[] elements = docParameter.split("_");
              
          	    Long idDoc = ParseUtil.parseLong(elements[2]);	// documents_id_xxx                	
          	    if (idDoc != null) {
              	    log.debug("Inici de obtenerDocumento(" + idDoc + ")");
                    startTrace = new Date();
                    document = docDelegate.obtenerDocumento(idDoc);
                    execTime = new Date().getTime() - startTrace.getTime();
                    log.debug("Temps d'execucio de obtenerDocumento(" + idDoc + "): " + execTime + " milisegons.");
              	    documents.add(document);
                    // Se coge el orden de la web. Si se quisiesen poner del 0 al x, hacer que orden valga 0 e ir incrementandolo.
                    String[] orden = {valoresForm.get("documents_orden_" + elements[2])};
                    actulitzadorMap.put("orden_doc" + idDoc, orden);
            	} else {
            		log.warn("S'ha rebut un id de document no n�meric: " + idDoc);
            	}
            }
	          
	        // actualitzam ordres
            log.debug("Inici de actualizarOrdenDocs()");
            startTrace = new Date();
	        docDelegate.actualizarOrdenDocs(actulitzadorMap);
	        execTime = new Date().getTime() - startTrace.getTime();
            log.debug("Temps d'execucio de actualizarOrdenDocs(): " + execTime + " milisegons.");
	        
	        // assignar els documents a la fitxa i eliminar els que ja no estiguin seleccionats.
	        fitxa.setDocumentos(documents);
	        if (edicion){
	            List<Documento> docsOld = fitxaOld.getDocumentos();                                    
	              
	            for(Documento doc: documents){
	                for (Iterator<Documento> it = docsOld.iterator(); it.hasNext(); ){
	                	Documento currentDoc = it.next();
	                    if (currentDoc != null && currentDoc.getId().equals(doc.getId())){
	                        it.remove();
	                    }
	                }
	            }                    
	             
	            for (Documento doc: docsOld){
	            	if (doc != null) {
	            	    Long codi = doc.getId();
	                    log.debug("Inici de borrarDocumento(" + codi + ")");
	                    startTrace = new Date();
	            	    docDelegate.borrarDocumento(codi);
	            	    execTime = new Date().getTime() - startTrace.getTime();
	                    log.debug("Temps d'execucio de borarDocumento(" + codi + "): " + execTime + " milisegons.");
	            	}
	            }
	        } 
	        // Fi documents 
            
	        
	        // Guardar
	        log.debug("Inici de grabarFicha()");
            startTrace = new Date();
            Long idFitxa = fitxaDelegate.grabarFicha(fitxa);
            execTime = new Date().getTime() - startTrace.getTime();
            log.debug("Temps d'execucio de grabarFicha(" + idFitxa + "): " + execTime + " milisegons.");
                
            //Asociacion de ficha con Unidad administrativa
            
            if(isModuloModificado("modulo_seccionesua_modificado", valoresForm)){
            
                String[] codisSeccUaNous = valoresForm.get("seccUA").split(",");                                      
                boolean esborrarFichaUA = true;
                
                if (edicion){
                    for (FichaUA fichaUA: fitxaOld.getFichasua()){
                        esborrarFichaUA = true;
                        for (int i = 0; i<codisSeccUaNous.length; i++){
                            if (codisSeccUaNous[i] != null){//Per a no repetir cerques
                                String[] seccUA = codisSeccUaNous[i].split("#"); //En cas d'edicio es necesari verificar si les relacions anteriors se mantenen
                                if(fichaUA.getId().equals(ParseUtil.parseLong(seccUA[0]))){
                                    esborrarFichaUA = false;
                                    codisSeccUaNous[i] = null;
                                    break;
                                }    
                            }
                        }
                        if (esborrarFichaUA){
                            Long codi = fichaUA.getId();
                            log.debug("Inici de borrarFichaUA(" + codi + ")");
                            startTrace = new Date();
                            fitxaDelegate.borrarFichaUA(codi);
                            execTime = new Date().getTime() - startTrace.getTime();
                            log.debug("Temps d'execucio de borrarFichaUA(" + codi + "): " + execTime + " milisegons.");
                        }                            
                    }
                }
                    
                //Tots els que tenen id = -1, son nous i se poden afegir directament
                for (String codiSeccUa: codisSeccUaNous){
                    if (codiSeccUa != null){
                        String[] seccUA = codiSeccUa.split("#");
                        Long idSeccion = ParseUtil.parseLong(seccUA[1]);
                        Long idUA = ParseUtil.parseLong(seccUA[2]);
    
                        log.debug("Inici de crearFichaUA(" + idUA + ", " + idSeccion + ", " + idFitxa + ")");
                        startTrace = new Date();
                        fitxaDelegate.crearFichaUA(idUA, idSeccion, idFitxa);
                        execTime = new Date().getTime() - startTrace.getTime();
                        log.debug("Temps d'execucio de crearFichaUA(" + idUA + ", " + idSeccion + ", " + idFitxa + "): " + execTime + " milisegons.");
    
                        String pidip = System.getProperty("es.caib.rolsac.pidip");
                        if(!((pidip == null) || pidip.equals("N"))) {
                            // Si se anyade una ficha a la seccion Actualidad, se a�ade tambien a Portada Actualidad (PIDIP)
                            if (idSeccion.longValue()== new Long(Parametros.ESDEVENIMENTS).longValue())
                            {   //comprobamos  antes si ya exite la ficha en actualidad  en portada en cuyo caso no la insertamos para no duplicarla.
                                int existe=0;
                                Long portadas = new Long(Parametros.PORTADAS_ACTUALIDAD);
                                
                                log.debug("Inici de listarFichasSeccionTodas(" + portadas + ")");
                                startTrace = new Date();
                                List listac = fitxaDelegate.listarFichasSeccionTodas(portadas);
                                execTime = new Date().getTime() - startTrace.getTime();
                                log.debug("Temps d'execucio de listarFichasSeccionTodas(" + portadas + "): " + execTime + " milisegons.");
                                
                                Iterator iter = listac.iterator();
                                while (iter.hasNext())
                                {
                                    Ficha ficac=(Ficha)iter.next();
                                    if((""+ficac.getId()).equals(""+idFitxa))
                                        existe=1;
                                }
                                if (existe==0) {
                                    log.debug("Inici de crearFichaUA(" + idUA + ", " + portadas + ", " + idFitxa + ")");
                                    startTrace = new Date();
                                    fitxaDelegate.crearFichaUA(idUA, portadas, idFitxa);
                                    execTime = new Date().getTime() - startTrace.getTime();
                                    log.debug("Temps d'execucio de crearFichaUA(" + idUA + ", " + portadas + ", " + idFitxa + "): " + execTime + " milisegons.");
                                }
                            }
                        }                                                
                    }
                }
            }
            
            //Tractament d'enllassos
            
            if (isModuloModificado("modulo_enlaces_modificado", valoresForm)){
            
                List<Enlace> enllassosNous = new ArrayList<Enlace>();
                
                for (Iterator<String> iterator = enllasos.iterator(); iterator.hasNext();) {
    				String nomParameter = (String)iterator.next();
    			               
                    String[] elements = nomParameter.split("_");
                    
                    if (elements[0].equals("enllas") && elements[1].equals("id")){
                        //En aquest cas, elements[2] es igual al id del enllas                                                 
                                     
                        Enlace enllas = new Enlace();                                           
                        
                        if (elements[2].charAt(0) == 't'){//Element nou, amb id temporal
                            enllas.setId(null);                            
                        } else {
                            enllas.setId(ParseUtil.parseLong(valoresForm.get(nomParameter)));
                        }
                        
                        enllas.setOrden(ParseUtil.parseLong(valoresForm.get("enllas_orden_" + elements[2])));                        
                        
                        for (String lang: langs){
                         
                            TraduccionEnlace traduccio = new TraduccionEnlace();
                            
                            traduccio.setTitulo(valoresForm.get("enllas_nombre_" + lang + "_" + elements[2]));
                            traduccio.setEnlace(valoresForm.get("enllas_url_" + lang + "_" + elements[2]));
                            
                            enllas.setTraduccion(lang, traduccio);
                            
                        }
                        
                        enllas.setFicha(fitxa);
                        
                        enllassosNous.add(enllas);
                    
                    }                                                            
                }
                    
                EnlaceDelegate enllasDelegate = DelegateUtil.getEnlaceDelegate();
                
                for (Enlace enllas: enllassosNous){
                    log.debug("Inici de grabarEnlace(" + enllas + ", " + null + ", " + idFitxa + ")");
                    startTrace = new Date();
                    enllasDelegate.grabarEnlace(enllas, null, idFitxa);
                    execTime = new Date().getTime() - startTrace.getTime();
                    log.debug("Temps d'execucio de grabarEnlace(" + enllas + ", " + null + ", " + idFitxa + "): " + execTime + " milisegons.");
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
                        Long codi = enllas.getId();
                        log.debug("Inici de borrarEnlace(" + codi + ")");
                        startTrace = new Date();
                        enllasDelegate.borrarEnlace(codi);
                        execTime = new Date().getTime() - startTrace.getTime();
                        log.debug("Temps d'execucio de borrarEnlace(" + codi + "): " + execTime + " milisegons.");
                    }                    
                }            
            }
            // Fi enllassos
            
            
            // Finalitzat correctament
            result = new IdNomDTO(fitxa.getId(), messageSource.getMessage("fitxes.guardat.correcte", null, request.getLocale()) );
            
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomDTO(-1l, error);
            } else {
                error = messageSource.getMessage("error.altres", null, request.getLocale());
                result = new IdNomDTO(-2l, error);
                log.error(ExceptionUtils.getStackTrace(dEx)); 
            }
        } catch (FileUploadException e) {
			error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomDTO(-3l, error);
			log.error(ExceptionUtils.getStackTrace(e));
			
		} catch (UnsupportedEncodingException e) {
			error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));
		} catch (NumberFormatException nfe) {
			result = new IdNomDTO(-3l, error);
		} catch (ParseException pe) {
			error = messageSource.getMessage(pe.getMessage(), null, request.getLocale());
			result = new IdNomDTO(-4l, error);
		}

        //return result;
        return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/seccions.do", method = POST)
    public @ResponseBody Map<String, Object> arbreSeccions(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();
        List<Seccion> llistaSeccions = new ArrayList<Seccion>();
        List<SeccionDTO> llistaSeccionsDTO = new ArrayList<SeccionDTO>();
        
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
                llistaSeccionsDTO.add(new SeccionDTO(seccio.getId(),
                                        tse == null ? "" : tse.getNombre(),
                                        seccio.getPadre() == null ? null : seccio.getPadre().getId(),                                                                        
                                        seccioDelegate.listarHijosSeccion(seccio.getId()).size() > 0 ? true : false                                                                
                ));
            }

            resultats.put("llistaSeccions", llistaSeccionsDTO);
            
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                // model.put("error", "permisos");
            	log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
            } else {
                // model.put("error", "altres");
            	log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }
        
        return resultats;
    }   
    
    @RequestMapping(value = "/unitats.do", method = POST)
    public @ResponseBody Map<String, Object> arbreUnitats(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();
        List<UnidadAdministrativa> llistaUnitats = new ArrayList<UnidadAdministrativa>();
        List<UnidadDTO> llistaUnitatsDTO = new ArrayList<UnidadDTO>();
        
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
                String abreviatura = unitat.getAbreviaturaUnidadAdministrativa(lang);
               
                llistaUnitatsDTO.add(new UnidadDTO(unitat.getId(),
                                        nomUnitat == null ? "" : nomUnitat,
                                        abreviatura == null ? "" : abreviatura,
                                        unitat.getPadre() == null ? null : unitat.getPadre().getId(),                                                                        
                                        unitatDelegate.listarHijosUA(unitat.getId()).size() > 0 ? true : false                                                                
                ));
            }

            resultats.put("llistaUnitats", llistaUnitatsDTO);
            
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                // model.put("error", "permisos");
            	log.error("Error de persimos: " + ExceptionUtils.getStackTrace(dEx));
            } else {
                // model.put("error", "altres");
            	log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }
        
        return resultats;
    }
    
    @RequestMapping(value = "/esborrarFitxa.do", method = POST)
    public @ResponseBody IdNomDTO esborrarFitxa(HttpServletRequest request) {
    	IdNomDTO resultatStatus = new IdNomDTO();

        try {
            Long id = new Long(request.getParameter("id"));
            FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
            fitxaDelegate.borrarFicha(id);

            resultatStatus.setId(1l);
            resultatStatus.setNom("correcte");
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                resultatStatus.setId(-1l);
            } else {
                resultatStatus.setId(-2l);
                log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }

        return resultatStatus;
    }
    
    
    
    /**
     * M�todo que comprueba si hay que mostrar los logos
     * 
     * @return boolean
     */
    private boolean mostrarLogosUA() {

        String value = System.getProperty("es.caib.rolsac.logos");
        return !((value == null) || value.equals("N"));
    }
    
    
    @RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			TraduccionFicha traduccioOrigen = new TraduccionFicha();
			
			if (StringUtils.isNotEmpty(request.getParameter("item_titol_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setTitulo(request.getParameter("item_titol_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_des_curta_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setDescAbr(request.getParameter("item_des_curta_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_des_llarga_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setDescripcion(request.getParameter("item_des_llarga_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			
			Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");					
			List<String> langs = traductor.getListLang();
			Map<String, Object> traduccio;
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
	        
	        for (String lang: langs){
	        	if (!IDIOMA_ORIGEN_TRADUCTOR.equalsIgnoreCase(lang)) {
	        		TraduccionFicha traduccioDesti = new TraduccionFicha();
	        		traductor.setDirTraduccio(IDIOMA_ORIGEN_TRADUCTOR, lang);
	        		if (traductor.traducir(traduccioOrigen, traduccioDesti)){
	        			traduccio = new HashMap<String, Object>();
	        			traduccio.put("lang", lang);
	        			traduccio.put("traduccio", traduccioDesti);
	        			traduccions.add(traduccio);
	        		} else {
	        			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
	        			break;
	        		}
	        	}
	        }
	        
			resultats.put("traduccions", traduccions);
			
		} catch (DelegateException dEx) {
			logException(log, dEx);
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
		} catch (NullPointerException npe) {
			log.error("FitxaBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		} catch (Exception e) {
			log.error("FitxaBackController.traduir: Error en al traducir ficha: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		}
		
		return resultats;
	}

    /**
     * Devuelve true si ha habido algun cambio en el modulo.
     * 
     * @param modulo
     * @param valoresForm
     * @return boolean
     */
    private boolean isModuloModificado(String modulo, Map<String, String> valoresForm) {
        return "1".equals(valoresForm.get(modulo));
    }
    
}
