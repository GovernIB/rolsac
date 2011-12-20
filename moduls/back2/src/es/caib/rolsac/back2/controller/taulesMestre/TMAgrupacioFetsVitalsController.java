package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;

@Controller
@RequestMapping("/agrupacioFetsVitals/")
public class TMAgrupacioFetsVitalsController {
	
	private static Log log = LogFactory.getLog(TMAgrupacioFetsVitalsController.class);
    
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/agrupacioFetsVitals.do")
    public String pantallaAgrupacioFetsVitals(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMAgrupacioFetsVitals.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmAgrupacioFetsVitals.jsp");
        	
        	String lang = request.getLocale().getLanguage();
        	
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

        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatAgrupacioVetsFitals(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaAgrupacioFetsVitalsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> agrupacioFetsVitalsDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, String> tradMap = new HashMap<String, String>();

		String lang = request.getLocale().getLanguage();
		
		// Parametres de cerca
		// Textes (en todos los campos todos los idiomas)
//		String texte = request.getParameter("texte");
//		if (texte != null && !"".equals(texte)) {
//			texte = texte.toUpperCase();
//			tradMap.put("nombre", texte);
//			tradMap.put("descripcion", texte);
//			tradMap.put("palabrasclave", texte);
//		} else {
//			tradMap.put("idioma", lang);
//		}
		
		
		try {
			AgrupacionHVDelegate agrupacioFVDelegate = DelegateUtil.getAgrupacionHVDelegate();
			List<AgrupacionHechoVital> llistaAgrupacioFetsVitals = agrupacioFVDelegate.listarAgrupacionHV();
			for (AgrupacionHechoVital agrupacioFetVital: llistaAgrupacioFetsVitals) {
				TraduccionAgrupacionHV tvf = (TraduccionAgrupacionHV) agrupacioFetVital.getTraduccion(request.getLocale().getLanguage());
				agrupacioFetsVitalsDTO = new HashMap<String, Object>();
				agrupacioFetsVitalsDTO.put("id", agrupacioFetVital.getId());
				agrupacioFetsVitalsDTO.put("nom", tvf == null ? "" : tvf.getNombre());
				agrupacioFetsVitalsDTO.put("codiEstandard", agrupacioFetVital.getCodigoEstandar());
				llistaAgrupacioFetsVitalsDTO.add(agrupacioFetsVitalsDTO);
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", llistaAgrupacioFetsVitalsDTO.size());
		resultats.put("nodes", llistaAgrupacioFetsVitalsDTO);

		return resultats;
	}
    
    @RequestMapping(value = "/guardar.do", method = POST)
    public ResponseEntity<String> guardarAgrupacioFetsVitals(HttpSession session, HttpServletRequest request) {
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petición.
		 * Esto se debe a que como esta petición es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicación. 
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
    		
            AgrupacionHVDelegate agrupacioVFDelegate = DelegateUtil.getAgrupacionHVDelegate();
            
            AgrupacionHechoVital agrupacioFetVital = new AgrupacionHechoVital();
            
			Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
			if (id != null) { 
				agrupacioFetVital = agrupacioVFDelegate.obtenerAgrupacionHV(id);
			} else {									
				agrupacioFetVital = new AgrupacionHechoVital();
			}
			
			agrupacioFetVital.setCodigoEstandar(codiEstandard);
			
			//Public objectiu
			Long publicObjectiuId = ParseUtil.parseLong(valoresForm .get("item_public_objectiu"));
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
				tafv.setNombre(valoresForm.get("item_nom_"+  lang ));
				tafv.setDescripcion(valoresForm.get("item_descripcio_"+  lang ));
				tafv.setPalabrasclave(valoresForm.get("item_paraules_clau_"+  lang ));
				
				traduccions.put(lang, tafv);
			}
			agrupacioFetVital.setTraduccionMap(traduccions);
			
			
			HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();
			List<HechoVitalAgrupacionHV> fetsVitalsNous = new ArrayList<HechoVitalAgrupacionHV>();
			
			List<HechoVitalAgrupacionHV> fetsVitalsOld = agrupacioFetVital.getHechosVitalesAgrupacionHV();
			
			if (agrupacioFetVital.getHechosVitalesAgrupacionHV() != null || fetVitalsForm.size()>0 ) {
				int sizefetsVitalsNous = fetsVitalsNous.size();
				int sizefetsVitalsNousOld = 0;
				// Recorrem el formulari
				for (Iterator<String> iterator = fetVitalsForm.iterator(); iterator.hasNext();) {
					String nomParameter = (String)iterator.next();
					String[] elements = nomParameter.split("_");
					if (elements[0].equals("fetVital") && elements[1].equals("id")){
	                    //En aquest cas, elements[2] es igual al id del fetVital
						
						Long idFetVitalForm = ParseUtil.parseLong(elements[2]);
						
						// Recorrem els elements de BBDD
						for (Iterator<HechoVitalAgrupacionHV> it = agrupacioFetVital.getHechosVitalesAgrupacionHV().iterator(); it.hasNext();) {
							HechoVitalAgrupacionHV fetVitalAgrupacioFV = it.next();
							
							// Coincidencia de la llista nova amb actual de BBDD
							if (fetVitalAgrupacioFV != null && fetVitalAgrupacioFV.getHechoVital().getId().equals(idFetVitalForm)) {
								fetVitalAgrupacioFV.setOrden(ParseUtil.parseInt(valoresForm.get("fetVital_orden_" + elements[2])));
								fetsVitalsNous.add(fetVitalAgrupacioFV);
								sizefetsVitalsNous++;
								
							} 
						}
						// Si no es troba l,element es un de nou 
						if (sizefetsVitalsNous <= sizefetsVitalsNousOld ) {
							HechoVitalAgrupacionHV hechoVitalAgrupacionHV = new HechoVitalAgrupacionHV();
							
							hechoVitalAgrupacionHV.setAgrupacion(agrupacioFetVital);
							hechoVitalAgrupacionHV.setHechoVital(fetVitalDelegate.obtenerHechoVital(idFetVitalForm));
							hechoVitalAgrupacionHV.setOrden(ParseUtil.parseInt(valoresForm.get("fetVital_orden_" + elements[2])));
							
							fetsVitalsNous.add(hechoVitalAgrupacionHV);
							
							sizefetsVitalsNousOld = sizefetsVitalsNous;
						}
					}	
				}
			}
			
			
			// Objectiu
			agrupacioFetVital.setHechosVitalesAgrupacionHV(fetsVitalsNous);
					
            agrupacioVFDelegate.guardarAgrupacionHV(agrupacioFetVital, fetsVitalsOld);
            
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
			log.error(ExceptionUtils.getStackTrace(e));;
        }
        
        return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
    }
    
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
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
	        
	        
	        String lang = request.getLocale().getLanguage();
	        // Fets vitals asociats
            if (agrupacioFetsVitals.getHechosVitalesAgrupacionHV() != null) {             
            	Map<String, String> map;
            	List<Map<String, String>> llistaFetsVitalsAgrupacio = new ArrayList<Map<String, String>>();            	
            	TraduccionHechoVital traFV;
				String nombre;
                
				
				for (Iterator<HechoVitalAgrupacionHV> it = agrupacioFetsVitals.getHechosVitalesAgrupacionHV().iterator(); it.hasNext();) {
					HechoVitalAgrupacionHV fetVitalAgrupacioFV = it.next();
					
					if (fetVitalAgrupacioFV != null) {
						traFV = (TraduccionHechoVital) fetVitalAgrupacioFV.getHechoVital().getTraduccion(lang);
						nombre = "";
	    				if (traFV != null) {
	    					//Retirar posible enlace incrustado en titulo
	    					nombre = HtmlUtils.obtenerTituloDeEnlaceHtml(traFV.getNombre());
	    				}
	    				map = new HashMap<String, String>(2);
	    				map.put("id", fetVitalAgrupacioFV.getHechoVital().getId().toString());
	    				map.put("nombre", nombre);
	    				map.put("orden", Integer.toString(fetVitalAgrupacioFV.getOrden()));
	                    llistaFetsVitalsAgrupacio.add(map);
					}
				}
				resultats.put("fetsVitals", llistaFetsVitalsAgrupacio);
            } else {
                resultats.put("fetsVitals", null);
            } 
            // Fi Fets vitals asociats
	        
	        
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
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, AgrupacionHechoVital agrupacioFV) throws DelegateException {
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
			log.error("Error: Id de pefil no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
}
