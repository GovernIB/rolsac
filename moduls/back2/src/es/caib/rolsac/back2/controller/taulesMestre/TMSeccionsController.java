package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/seccions/")
public class TMSeccionsController extends PantallaBaseController {

	private static Log log = LogFactory.getLog(TMSeccionsController.class);
	
	@RequestMapping(value = "/seccioBreadcrumb.do")
	public @ResponseBody Map<String, Object> getBrearcrumb(HttpServletRequest request) {
	
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			
    		// Breadcrumb del elemento
    		List<Map<String, Object>> breadcrumbDTO = new ArrayList<Map<String, Object>>();
    		Map<String, Object> elementoDTO;

    		if (request.getParameter("id") != null) {
    			
    			Long id = new Long(request.getParameter("id"));
    			SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
    			List<Seccion> antecesores = seccioDelegate.listarAntecesoresSeccion(id);
    			
    			for (Seccion seccion: antecesores) {
    				
    				TraduccionSeccion ts = (TraduccionSeccion) seccion.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
    				elementoDTO = new HashMap<String, Object>();
    				elementoDTO.put("id", seccion.getId());
    				elementoDTO.put("nom", ts == null ? "" : ts.getNombre());
    				
    				breadcrumbDTO.add(elementoDTO);
    				
    			}
    			
    		}
    		
    		resultats.put("breadcrumb", breadcrumbDTO);
    		
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
			
		}
    	
		return resultats;
		
    }
    
    @RequestMapping(value = "/seccio.do")
    public String pantallaSeccio(Map<String, Object> model, HttpServletRequest request) {
    	
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMSeccions.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        
        if (rolUtil.userIsAdmin()) {
        	
        	try {
        		
				model.put("llistaPerfil", getJSONPerfiles(request));
				model.put("escriptori", "pantalles/taulesMestres/tmSeccions.jsp");
				
				// Listado padres
	    		List<Map<String, Object>> llistaSeccioDTO = new ArrayList<Map<String, Object>>();
	    		Map<String, Object> seccioDTO;
				
				SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
				List<Seccion> seccions = seccioDelegate.listarSeccionesRaiz();
				
				for (Seccion seccion : seccions) {
					
					TraduccionSeccion ts = (TraduccionSeccion) seccion.getTraduccion(request.getLocale().getLanguage());
					seccioDTO = new HashMap<String, Object>();
					seccioDTO.put("id", seccion.getId());
					seccioDTO.put("nom", ts == null ? "" : ts.getNombre());
					
					llistaSeccioDTO.add(seccioDTO);
					
				}
				
	    		model.put("llistaPares", llistaSeccioDTO);
	    		
        	} catch (DelegateException dEx) {
        		
    			if (dEx.isSecurityException()) {
    				model.put("error", "permisos");
    			} else {
    				log.error("Error: " + dEx.getMessage());
    				model.put("error", "altres");
    			}
    			
    		}
        	
        } else {
        	
        	model.put("error", "permisos");
        	
        }
        
		loadIndexModel (model, request);	
		
        return "index";
        
    }
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatSeccio(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaSeccioDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> seccioDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
       		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		
		try {
			
			SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
			String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			resultadoBusqueda = seccioDelegate.listarSeccionesRaiz(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPorDefecto);
			
			for ( Object o : resultadoBusqueda.getListaResultados() ) {

				Long id = (Long) ((Object[]) o)[0];
				int ordre = ((Integer) ((Object[]) o)[1]).intValue();
				String nom = ((Object[]) o)[2] == null ? "" : (String) ((Object[]) o)[2];
						
				seccioDTO = new HashMap<String, Object>();
				seccioDTO.put("id", id);
				seccioDTO.put("nom", nom);
				seccioDTO.put("ordre", ordre);
				
				llistaSeccioDTO.add(seccioDTO);
				
			}
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
			
		}
		
		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaSeccioDTO);

		return resultats;
		
	}
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(Long id, HttpServletRequest request) {
    	
    	Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	    	
	        SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
	        Seccion seccio = seccionDelegate.obtenerSeccion(id);
	        
	        omplirCampsTraduibles(resultats, seccio);
	        
	        resultats.put("item_id", seccio.getId());

	        String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
	        
	        // Perfil
            resultats.put("item_perfils", getJSONPerfiles(request));
            resultats.put("item_perfil", seccio.getPerfil() != null ? seccio.getPerfil() : null);
            
            Long idPadre = null;
            String nomPadre = "";
            TraduccionSeccion traS;
            
            if (seccio.getPadre() != null) {
            	idPadre = seccio.getPadre().getId();
            	traS = (TraduccionSeccion) seccio.getPadre().getTraduccion(lang);
            	nomPadre = traS.getNombre();
            } else if (seccio.getPadre() == null) {
            	nomPadre = messageSource.getMessage("seccio.arrel", null, request.getLocale());
            }
            
            resultats.put("item_codi_pare", idPadre);
            resultats.put("item_pare", nomPadre);
            resultats.put("item_codi_estandard", seccio.getCodigoEstandard());
            
            // Fitxes informatives relacionades
            if (seccio.getFichasUA() != null) {
            	
            	Map<String, String> map;
            	List<Map<String, String>> llistaFitxes = new ArrayList<Map<String, String>>();
            	TraduccionFicha traF;
            	Traduccion traUA;
				String nombre;
                String nombreUA;
                String idUA;
                
                Iterator<FichaUA> it = seccio.getFichasUA().iterator();
                
				while ( it.hasNext() ) {
					
					FichaUA ficha = it.next();
					
					if (ficha != null && ficha.getFicha() != null) {
						
						traF = (TraduccionFicha) ficha.getFicha().getTraduccion(lang);
						nombre = "";
						idUA = "";
						nombreUA = "";
						
	    				if (traF != null) {
	    					//Retirar posible enlace incrustado en titulo
	    					nombre = HtmlUtils.obtenerTituloDeEnlaceHtml(traF.getTitulo());
	    				}
	    				
	    				if (ficha.getUnidadAdministrativa() != null) {
	    				    traUA = ficha.getUnidadAdministrativa().getTraduccion(lang);
	    				    nombreUA = traUA != null ? ((TraduccionUA)traUA).getAbreviatura() : "";
	    					idUA = ficha.getUnidadAdministrativa().getId().toString();
	    					if (nombreUA == null || "".equals(nombreUA)) {
	    					    nombreUA = idUA;
	    					}
	    				}
	    					    				
 	    				map = new HashMap<String, String>(2);
	    				map.put("id", ficha.getFicha().getId().toString());
	    				map.put("nombre", nombre);
	    				map.put("orden", String.valueOf(ficha.getOrdenseccion()));
	    				map.put("caducado", ficha.getFicha().isVisible().booleanValue() ? "S" : "N");
	    				map.put("idUA", idUA);
	    				map.put("nomUA", nombreUA);
	    				
	    				llistaFitxes.add(map);
	    				
					}
					
				}
				
				resultats.put("fitxesInformatives", llistaFitxes);
				
            } else {
            	
                resultats.put("fitxesInformatives", null);
                
            }
            // Fi Fitxes informatives relacionades
            
	    } catch (DelegateException dEx) {
	    	
			log.error(ExceptionUtils.getStackTrace(dEx));
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
			
		}
	    
        return resultats;
        
	}
    
    @RequestMapping(value = "/modulos.do")
   	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {
   		
   		Map<String, Object> resultats = new HashMap<String, Object>();
   		
   		try {
   			
	        String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
	        SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
	        Seccion seccion = seccionDelegate.obtenerSeccion(id);
	        
	        List<Seccion> hijosSeccion = seccion.getHijos();
   			
            if (hijosSeccion != null) {
            	
            	// Ordenamos i.e. poner cada sección ordenada por su valor "orden" en el array.
            	Seccion[] arraySecciones = new Seccion[hijosSeccion.size()];
            	Iterator<Seccion> it = hijosSeccion.iterator();
            	
            	while ( it.hasNext() ) {
            		
            		Seccion s = it.next();
            		
            		if (s != null)
            			arraySecciones[s.getOrden()] = s;
            		
            	}
            	
            	Map<String, String> map;
            	List<Map<String, String>> llistaSeccions = new ArrayList<Map<String, String>>();
            	TraduccionSeccion traSec;
				String nombre;
				              
				for ( int i = 0; i < arraySecciones.length; i++ ) {
					
					Seccion s = arraySecciones[i];
					
					if (s != null) {
						
						traSec = (TraduccionSeccion)s.getTraduccion(lang);
						nombre = "";
						
	    				if (traSec != null) {
	    					//Retirar posible enlace incrustado en titulo
	    					nombre = HtmlUtils.obtenerTituloDeEnlaceHtml(traSec.getNombre());
	    				}
	    				
	    				map = new HashMap<String, String>(2);
	    				map.put("id", s.getId().toString());
	    				map.put("nombre", nombre);
	    				map.put("orden", String.valueOf(s.getOrden()));
	    				map.put("idMainItem", id.toString());
	    				map.put("idRelatedItem", s.getId().toString());
	    				
	    				llistaSeccions.add(map);
	    				
					}
					
				}
				
				resultats.put("seccionsRelacionades", llistaSeccions);
				
            } else {
            	
                resultats.put("seccionsRelacionades", null);
                
            }
   			
   		} catch (DelegateException dEx) {

   			log.error(ExceptionUtils.getStackTrace(dEx));
   			
   			if (dEx.isSecurityException())
   				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
   			
   			else
   				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
   			
   		}
   		
   		return resultats;
   		
   	}
    
    // Devuelve lista de unidades administrativas materias (id uamateria y nombre ua).  
    private List<Map<String, String>> getJSONPerfiles(HttpServletRequest request) {
    	
		List<Map<String, String>> perfiles = new ArrayList<Map<String, String>>();
		Map<String, String> perfil;
		
		perfil = new HashMap<String, String>();
        perfil.put("id", "sacadmin");
        perfil.put("nom", messageSource.getMessage("usuari.sacadmin", null, request.getLocale()));
        perfiles.add(perfil);
        
        perfil = new HashMap<String, String>();
        perfil.put("id", "sacsuper");
        perfil.put("nom", messageSource.getMessage("usuari.sacsuper", null, request.getLocale()));
        perfiles.add(perfil);
        
        perfil = new HashMap<String, String>();
        perfil.put("id", "sacoper");
        perfil.put("nom", messageSource.getMessage("usuari.sacoper", null, request.getLocale()));
        perfiles.add(perfil);

		return perfiles;
		
    }
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, Seccion seccio) 
    		throws DelegateException {
    	
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
			
		    if (null != seccio.getTraduccion(lang)) {
		    	
		    	HashMap<String, String> traduccionSeccionDTO = new HashMap<String, String>();
		    	TraduccionSeccion ts = (TraduccionSeccion) seccio.getTraduccion(lang);
		    	
		    	traduccionSeccionDTO.put("item_nombre", ts.getNombre());
		    	traduccionSeccionDTO.put("item_descripcio", ts.getDescripcion());
		    	
	        	resultats.put(lang, traduccionSeccionDTO);
	        	
			} else {
				
				resultats.put(lang, new HashMap<String, String>());
				
			}
		    
		}
		
	}

    @RequestMapping(value = "/guardar.do", method = POST)
	public ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
    	Seccion seccion = new Seccion();
		IdNomDTO result = null;
        
		Map<String, String> valoresForm = new HashMap<String, String>();
		
        try {
        	
    		//Aqu� nos llegar� un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		//Iremos recopilando los par�metros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
    		
        	Set<String> seccioForm = new HashSet<String>();
        	Set<String> fitxaForm = new HashSet<String>();
        	Iterator<String> itParams = request.getParameterMap().keySet().iterator();
        	
        	while (itParams.hasNext()) {
        		
        		String key = itParams.next();
        		String value = request.getParameter(key);
        		
        		if (key.startsWith("seccio_")){
        			seccioForm.add(key);
				} 
        		
        		if (key.startsWith("fitxa_")){
        			fitxaForm.add(key);
        		} 
        		
        		valoresForm.put(key, value);
        		
        	}
        	
        	SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();

        	boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));
        	if (edicion) {
				Long idSeccio = ParseUtil.parseLong(valoresForm.get("item_id"));
        		seccion = seccionDelegate.obtenerSeccion(idSeccio);
        	}
        	
        	// Obtener campos por idioma
        	List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        	Map traduccions = new HashMap(idiomas.size());
        	
        	for (String idioma : idiomas) {
        		
        		TraduccionSeccion traSec = new TraduccionSeccion();
        		traSec.setNombre( RolUtil.limpiaCadena(valoresForm.get("item_nom_" + idioma)) );
        		traSec.setDescripcion( RolUtil.limpiaCadena(valoresForm.get("item_descripcio_" + idioma)) );
        		
        		traduccions.put(idioma, traSec);
        		
        	}
        	
        	seccion.setTraduccionMap(traduccions);
        	
        	//Obtener los dem�s campos
        	Long idSeccioPare = null;
        	if (valoresForm.get("item_codi_pare") != null && !"".equals(valoresForm.get("item_codi_pare"))) {
        		idSeccioPare = ParseUtil.parseLong(valoresForm.get("item_codi_pare"));
        	}
        	
    		seccion.setPerfil(valoresForm.get("item_perfil"));
    		seccion.setCodigoEstandard(valoresForm.get("item_codi_estandard"));
                    	
        	// Fichas informativas
        	if (fitxaForm.size() > 0) {
        		for (String nomParametre : fitxaForm) {
        			String[] elements = nomParametre.split("_");
					if (elements[1].equals("id")) {
						Long idFitxa = ParseUtil.parseLong(elements[2]);
						
						if (seccion.getFichasUA() != null) {
							for (Iterator<FichaUA> it = seccion.getFichasUA().iterator(); it.hasNext();) {
								FichaUA ficha = it.next();
								
								if (ficha != null && ficha.getFicha() != null && ficha.getFicha().getId().equals(idFitxa)) {
									ficha.setOrdenseccion(ParseUtil.parseInt(valoresForm.get("fitxa_orden_" + elements[2])));
								}
							}
						}
					}
        		}
        	}
        	
    		seccionDelegate.grabarSeccion(seccion, idSeccioPare);
        	result = new IdNomDTO(seccion.getId(), messageSource.getMessage("seccio.guardat.correcte", null, request.getLocale()) );
        	
        } catch (DelegateException dEx) {
        	
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}  
			
		}
		
		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
		
	}
    
    @RequestMapping(value = "/esborrarSeccio.do", method = POST)
	public @ResponseBody IdNomDTO esborrarSeccio(HttpServletRequest request) {
    	
		IdNomDTO resultatStatus = new IdNomDTO();
		
		try {
			
			Long id = new Long(request.getParameter("id"));
			SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
			seccioDelegate.borrarSeccion(id);
			resultatStatus.setId(1l);
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		} catch (NumberFormatException nfEx) {
			
			resultatStatus.setId(-3l);
			log.error("Error: Id de secci� no num�ric: " + ExceptionUtils.getStackTrace(nfEx));
			
		}
		
		return resultatStatus;
		
	}
    
    @RequestMapping(value = "/pujarSeccio.do", method = POST)
	public @ResponseBody IdNomDTO pujarSeccio(HttpServletRequest request) {
    	
		IdNomDTO resultatStatus = new IdNomDTO();
		
		try {
			
			Long id = new Long(request.getParameter("id"));
			SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
			seccioDelegate.subirOrden(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de secci� no num�ric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		
		return resultatStatus;
		
	}
    
    @RequestMapping(value = "/reordenarSeccio.do", method = POST)
    public @ResponseBody IdNomDTO reordenarSeccio(HttpServletRequest request) {
    	
    	IdNomDTO resultatStatus = new IdNomDTO();
    	
		try {
			
			Long id = new Long(request.getParameter("id"));
			Integer nuevoOrden = new Integer(request.getParameter("orden"));
			Integer ordenOld = new Integer(request.getParameter("ordenAnterior"));
			
			SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
			seccionDelegate.reordenar(id, nuevoOrden, ordenOld);
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		} catch (NumberFormatException nfEx) {
			
			resultatStatus.setId(-3l);
			log.error("Error: Id de secció no numèric: " + ExceptionUtils.getStackTrace(nfEx));
			
		}
		
		return resultatStatus;
		
    }
    
    @RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {
		
    	Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			TraduccionSeccion traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
			Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
			traduccions = traductor.translate(traduccioOrigen, idiomaOrigenTraductor);
			
			resultats.put("traduccions", traduccions);
	        
	    } catch (DelegateException dEx) {
	    	
			logException(log, dEx);
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
			
		} catch (NullPointerException npe) {
			
			log.error("SeccionBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		} catch (Exception e) {
			
			log.error("SeccionBackController.traduir: Error en al traducir Seccion: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		}
		
		return resultats;
		
	}
	
    private TraduccionSeccion getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {
    	
    	TraduccionSeccion traduccioOrigen = new TraduccionSeccion();
		
		if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
		}
		
		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_" + idiomaOrigenTraductor));
		}
		
		return traduccioOrigen;
		
	}
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarOrdenSeccionesRelacionadas.do")
	public @ResponseBody IdNomDTO guardarOrdenSeccionesRelacionadas(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
			
			// Guardamos el orden en el que se han dejado las secciones relacionadas.
			SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
			Seccion seccion = seccionDelegate.obtenerSeccionSinFichasUA(id);
			List<Seccion> seccionesRelacionadas = seccion.getHijos();
			
			// Es posible que hayan vaciado de elementos el módulo. En ese caso, elementos será null.
			if ( elementos != null ) {
				
				for (int i = 0; i < elementos.length; i++) {
					
					Iterator<Seccion> it = seccionesRelacionadas.iterator();
					boolean found = false;
					
					// Aplicamos el orden en función de cómo nos llegan por parámetro, que será el orden en el
					// cual están colocadas en la lista ordenable del módulo lateral.
					while ( it.hasNext() && !found ) {
						
						Seccion secHija = it.next();
						
						if ( secHija != null && elementos[i].equals(secHija.getId()) ) {
							found = true;
							secHija.setOrden(i + 1);
						}
						
					}
					
				}
				
			}
			
			seccionDelegate.grabarSeccion(seccion, seccion.getPadre() != null ? seccion.getPadre().getId() : null);
			
			result = new IdNomDTO(seccion.getId(), messageSource.getMessage("seccio.guardat.correcte", null, request.getLocale()) );
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		}
		
		return result;
		
    }
    
}
