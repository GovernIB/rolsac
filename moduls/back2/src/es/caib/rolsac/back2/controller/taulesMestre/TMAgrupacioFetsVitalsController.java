package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionAgrupacionHV;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.AgrupacionHVDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/agrupacioFetsVitals/")
public class TMAgrupacioFetsVitalsController extends PantallaBaseController {
	
	private static Log log = LogFactory.getLog(TMAgrupacioFetsVitalsController.class);
    
    @RequestMapping(value = "/agrupacioFetsVitals.do")
    public String pantallaAgrupacioFetsVitals(Map<String, Object> model, HttpServletRequest request) {
    	
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMAgrupacioFetsVitals.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        
        if (rolUtil.userIsAdmin()) {
        	
        	model.put("escriptori", "pantalles/taulesMestres/tmAgrupacioFetsVitals.jsp");
        	
        	String lang;
        	try {
        		lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
        	} catch (DelegateException dEx) {
        		log.error("Idioma por defecto no encontrado");
        		lang = "ca";
        	}
        	
        	PublicoObjetivoDelegate publicObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();
			List<IdNomDTO> llistaPublicObjectiuDTO = new ArrayList<IdNomDTO>();
			List<PublicoObjetivo> llistaPublicObjectiu = new ArrayList<PublicoObjetivo>();
			
			try {
				
				llistaPublicObjectiu = publicObjectiuDelegate.listarPublicoObjetivo();
				
				for (PublicoObjetivo publicObjectiu : llistaPublicObjectiu) {
					TraduccionPublicoObjetivo tpo = (TraduccionPublicoObjetivo) publicObjectiu.getTraduccion(lang);
					llistaPublicObjectiuDTO.add(new IdNomDTO(publicObjectiu
							.getId(),tpo == null ? null : tpo.getTitulo() 
					));
				}
				
			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException()) {
					log.error("Error de permiso: " + ExceptionUtils.getStackTrace(dEx)); 
				} else {
					log.error(ExceptionUtils.getStackTrace(dEx));
				}
				
			}

			model.put("llistaPublicObjectiu", llistaPublicObjectiuDTO);
			
			List<IdNomDTO> llistaFetsVitalsDTO = new ArrayList<IdNomDTO>();
			List<HechoVital> llistaFetsVitals = new ArrayList<HechoVital>();
			HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();
			
			try {
				
				llistaFetsVitals = fetVitalDelegate.listarHechosVitales();

				for (HechoVital fetVital : llistaFetsVitals) {
					TraduccionHechoVital tpo = (TraduccionHechoVital) fetVital.getTraduccion(lang);
					llistaFetsVitalsDTO.add(
							new IdNomDTO(fetVital.getId(),tpo == null ? null : tpo.getNombre() 
					));
				}
	            
	        } catch (DelegateException dEx) {
	        	
				if (dEx.isSecurityException()) {
					log.error("Error de permiso: " + ExceptionUtils.getStackTrace(dEx)); 
				} else {
					log.error(ExceptionUtils.getStackTrace(dEx));
				}
				
			}
			
			model.put("llistaFets", llistaFetsVitalsDTO);
        	
        } else {
        	
        	model.put("error", "permisos");
        	
        }

		loadIndexModel (model, request);
		
        return "index";
        
    }
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatAgrupacioVetsFitals(HttpServletRequest request) {
    	
    	List<Map<String, Object>> llistaAgrupacioFetsVitalsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> agrupacioFetsVitalsDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
       		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();   					
		
		try {
			
			AgrupacionHVDelegate agrupacioFVDelegate = DelegateUtil.getAgrupacionHVDelegate();
			String idiomaPerDefecte = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			resultadoBusqueda = agrupacioFVDelegate.listarAgrupacionesHVHechosVitales(
					Integer.parseInt(pagPag),
					Integer.parseInt(pagRes),
					idiomaPerDefecte
			);
			
			for (Object o : resultadoBusqueda.getListaResultados()) {
				
				Long id = (Long) ((Object[]) o)[0];
				String codiEstandard = (String) ((Object[]) o)[1];
				String nom = (String) ((Object[]) o)[2];
				
				agrupacioFetsVitalsDTO = new HashMap<String, Object>();
				agrupacioFetsVitalsDTO.put("id",  id);
				agrupacioFetsVitalsDTO.put("codiEstandard", codiEstandard);
				agrupacioFetsVitalsDTO.put("nom", nom);
				
				llistaAgrupacioFetsVitalsDTO.add(agrupacioFetsVitalsDTO);
				
			}
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
			
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaAgrupacioFetsVitalsDTO);

		return resultats;
		
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

        IdNomDTO result = null;
        
        Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
        
        try {
        	
        	//Aqui nos llegara un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		//Iremos recopilando los parametros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
        	List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

        	Set<String> fetVitalsForm = new HashSet<String>();
        	
    		for (FileItem item : items) {
    			
    			if (item.isFormField()) {
    				if (item.getFieldName().startsWith("fetVital_")){
    					fetVitalsForm.add(item.getFieldName());
    				} 
    				valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
    			} else {
    				ficherosForm.put(item.getFieldName(), item);    				
    			}
    			
    		}
    		
    		//Campos obligatorios
            String codiEstandard = valoresForm.get("item_codi_estandard");
                       
            if (codiEstandard == null || "".equals(codiEstandard)) {
                String error = messageSource.getMessage("agrupacioFV.formulari.error.falten_camps", null, request.getLocale());
                result = new IdNomDTO(-3l, error);
                return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);                
            } 
    		
            AgrupacionHVDelegate agrupacionHVDelegate = DelegateUtil.getAgrupacionHVDelegate();
            Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
            
            AgrupacionHechoVital agrupacioFetVital = (id != null) ? agrupacionHVDelegate.obtenerAgrupacionHV(id) : new AgrupacionHechoVital();
			
			agrupacioFetVital.setCodigoEstandar(codiEstandard);
			
			//Public objectiu
			Long publicObjectiuId = ParseUtil.parseLong(valoresForm.get("item_public_objectiu"));
			if (publicObjectiuId != null) {
				PublicoObjetivoDelegate publicoObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();
				PublicoObjetivo publicObjectiu = publicoObjectiuDelegate.obtenerPublicoObjetivo(publicObjectiuId);
				agrupacioFetVital.setPublico(publicObjectiu);
			} else {
				agrupacioFetVital.setPublico(null);
			}
			
			//Foto 
    		FileItem fileFoto = ficherosForm.get("item_foto");
    		if ( fileFoto.getSize() > 0 ) {
    			agrupacioFetVital.setFoto(UploadUtil.obtenerArchivo(agrupacioFetVital.getFoto(), fileFoto));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_foto_delete") != null && !"".equals(valoresForm.get("item_foto_delete"))){
    			agrupacioFetVital.setFoto(null);
    		}
    		//Icona
    		FileItem fileIcona = ficherosForm.get("item_icona");
    		if ( fileIcona.getSize() > 0 ) {
    			agrupacioFetVital.setIcono(UploadUtil.obtenerArchivo(agrupacioFetVital.getIcono(), fileIcona));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_icona_delete") != null && !"".equals(valoresForm.get("item_icona_delete"))){
    			agrupacioFetVital.setIcono(null);
    		}
    		//IconaGran
    		FileItem fileIconaGran = ficherosForm.get("item_icona_gran");
    		if ( fileIconaGran.getSize() > 0 ) {
    			agrupacioFetVital.setIconoGrande(UploadUtil.obtenerArchivo(agrupacioFetVital.getIconoGrande(), fileIconaGran));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_icona_gran_delete") != null && !"".equals(valoresForm.get("item_icona_gran_delete"))){
    			agrupacioFetVital.setIconoGrande(null);
    		}
           
            // Idiomas
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();
			
			Map traduccions = new HashMap(langs.size());
			for (String lang: langs) {
				TraduccionAgrupacionHV tafv = new TraduccionAgrupacionHV();
				tafv.setNombre( RolUtil.limpiaCadena(valoresForm.get("item_nom_"+  lang )) );
				tafv.setDescripcion( RolUtil.limpiaCadena(valoresForm.get("item_descripcio_"+  lang )) );
				tafv.setPalabrasclave( RolUtil.limpiaCadena(valoresForm.get("item_paraules_clau_"+  lang )) );
				
				traduccions.put(lang, tafv);
			}
			agrupacioFetVital.setTraduccionMap(traduccions);
			
			agrupacionHVDelegate.guardarAgrupacionHV(agrupacioFetVital);
			            
            String ok = messageSource.getMessage("agrupacioFV.guardat.correcte", null, request.getLocale());
            result = new IdNomDTO(agrupacioFetVital.getId(), ok);
            
        } catch (DelegateException dEx) {
        	
            if (dEx.isSecurityException()) {
                String error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomDTO(-1l, error);
            } else {
                String error = messageSource.getMessage("error.altres", null, request.getLocale());
                result = new IdNomDTO(-2l, error);
                log.error(ExceptionUtils.getStackTrace(dEx));
            }
            
        } catch (UnsupportedEncodingException e) {
        	
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));
			
        } catch (FileUploadException e) {
        	
			String error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomDTO(-3l, error);
			log.error(ExceptionUtils.getStackTrace(e));
			
        }
        
        return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(Long id, HttpServletRequest request) {
    	
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        
	        AgrupacionHVDelegate agrupacioFVDelegate = DelegateUtil.getAgrupacionHVDelegate();
	        AgrupacionHechoVital agrupacioFetsVitals = agrupacioFVDelegate.obtenerAgrupacionHV(id);	        	        
	        
	        resultats.put("item_id", agrupacioFetsVitals.getId());
	        resultats.put("item_codi_estandard", agrupacioFetsVitals.getCodigoEstandar());
	        
	        if (agrupacioFetsVitals.getPublico() != null) {
	        	resultats.put("item_public_objectiu", agrupacioFetsVitals.getPublico().getId());
	        } else {
	        	resultats.put("item_public_objectiu", null);
	        }
	        
	        if (agrupacioFetsVitals.getFoto() != null){
            	resultats.put("item_foto_enllas_arxiu", "agrupacioFetsVitals/archivo.do?id=" + agrupacioFetsVitals.getId() + "&tipus=1");
                resultats.put("item_foto", agrupacioFetsVitals.getFoto().getNombre());
            } else {
            	resultats.put("item_foto_enllas_arxiu", "");
                resultats.put("item_foto", "");
            }
            
	        if (agrupacioFetsVitals.getIcono() != null) {
            	resultats.put("item_icona_enllas_arxiu", "agrupacioFetsVitals/archivo.do?id=" + agrupacioFetsVitals.getId() + "&tipus=2");
                resultats.put("item_icona", agrupacioFetsVitals.getIcono().getNombre());
            } else {
                resultats.put("item_icona_enllas_arxiu", "");
                resultats.put("item_icona", "");
            }
	        
	        if (agrupacioFetsVitals.getIconoGrande() != null) {
            	resultats.put("item_icona_gran_enllas_arxiu", "agrupacioFetsVitals/archivo.do?id=" + agrupacioFetsVitals.getId() + "&tipus=3");
                resultats.put("item_icona_gran", agrupacioFetsVitals.getIconoGrande().getNombre());
            } else {
                resultats.put("item_icona_gran_enllas_arxiu", "");
                resultats.put("item_icona_gran", "");
            } 
             
			omplirCampsTraduibles(resultats, agrupacioFetsVitals);
	        
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
	        
	        AgrupacionHVDelegate agrupacioFVDelegate = DelegateUtil.getAgrupacionHVDelegate();
	        AgrupacionHechoVital agrupacioFetsVitals = agrupacioFVDelegate.obtenerAgrupacionHV(id);
	        
	        // Hechos vitales relacionados.
	        List<HechoVitalAgrupacionHV> listaHechosVitales = agrupacioFetsVitals.getHechosVitalesAgrupacionHV();
	        resultats.put("fetsVitals", CargaModulosLateralesUtil.recuperaHechosVitalesRelacionados(listaHechosVitales, HechoVitalAgrupacionHV.class, id, lang, true));
   			   			
   		} catch (DelegateException dEx) {

   			log.error(ExceptionUtils.getStackTrace(dEx));
   			
   			if (dEx.isSecurityException())
   				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
   			
   			else
   				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
   			
   		}
   		
   		return resultats;
   		
   	}
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, AgrupacionHechoVital agrupacioFV) 
    		throws DelegateException {
    	
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
		    if (null!=agrupacioFV.getTraduccion(lang)) {
				resultats.put(lang, (TraduccionAgrupacionHV) agrupacioFV.getTraduccion(lang));
			} else {
				resultats.put(lang, new TraduccionAgrupacionHV());
			}
		}
		
	}    
    
    @RequestMapping(value = "/esborrarAgrupacioFetsVitals.do", method = POST)
	public @ResponseBody IdNomDTO esborrarAgrupacioFetsVitals(HttpServletRequest request) {
    	
		IdNomDTO resultatStatus = new IdNomDTO();
		
		try {
			
			Long id = new Long(request.getParameter("id"));
			AgrupacionHVDelegate agrupacioFVDelegate = DelegateUtil.getAgrupacionHVDelegate();
			agrupacioFVDelegate.borrarAgrupacionHV(id);
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
			log.error("Error: Id de pefil no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
			
		}
		
		return resultatStatus;
		
	}
    
    @RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {
    	
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			TraduccionAgrupacionHV traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
			
			log.error("AgrupacióFetsVitalsBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		} catch (Exception e) {
			
			log.error("AgrupacióFetsVitalsBackController.traduir: Error en al traducir agrupació: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		}
		
		return resultats;
		
	}
	
    private TraduccionAgrupacionHV getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {
    	
    	TraduccionAgrupacionHV traduccioOrigen = new TraduccionAgrupacionHV();
		
		if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
		}
		
		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_" + idiomaOrigenTraductor));
		}
		
		if (StringUtils.isNotEmpty(request.getParameter("item_paraules_clau_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setPalabrasclave(request.getParameter("item_paraules_clau_" + idiomaOrigenTraductor));
		}
		
		return traduccioOrigen;
		
	}
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarHechosVitalesRelacionados.do")
	public @ResponseBody IdNomDTO guardarHechosVitalesRelacionados(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
			
			AgrupacionHVDelegate agrupacionHVDelegate = DelegateUtil.getAgrupacionHVDelegate();
            AgrupacionHechoVital agrupacionHechoVital = agrupacionHVDelegate.obtenerAgrupacionHV(id);
			
			HechoVitalDelegate hechoVitalDelegate = DelegateUtil.getHechoVitalDelegate();
			List<HechoVitalAgrupacionHV> hechosVitalesNuevos = new ArrayList<HechoVitalAgrupacionHV>();
			List<HechoVitalAgrupacionHV> hechosVitalesOld = agrupacionHechoVital.getHechosVitalesAgrupacionHV();
			
			if (hechosVitalesOld == null) { // Si la lista es null saltara una excepcion en el EJB
			    hechosVitalesOld = new ArrayList<HechoVitalAgrupacionHV>();
			}
			
			// Es posible que hayan vaciado de elementos el módulo. En ese caso, elementos será null.
			if ( elementos != null ) {
				
				for (int i = 0; i < elementos.length; i++) {
					
					HechoVital hv = hechoVitalDelegate.obtenerHechoVital(elementos[i]);
					
					HechoVitalAgrupacionHV hva = new HechoVitalAgrupacionHV();
					hva.setAgrupacion(agrupacionHechoVital);
					hva.setHechoVital(hv);
					hva.setOrden(i + 1);
					
					hechosVitalesNuevos.add(hva);
					
				}
				
			}
						
			agrupacionHechoVital.setHechosVitalesAgrupacionHV(hechosVitalesNuevos);
            agrupacionHVDelegate.guardarAgrupacionHV(agrupacionHechoVital, hechosVitalesOld);
                        
            String ok = messageSource.getMessage("agrupacioFV.guardat.correcte", null, request.getLocale());
            result = new IdNomDTO(agrupacionHechoVital.getId(), ok);
			
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
