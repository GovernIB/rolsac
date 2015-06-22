package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.PerfilGestor;
import org.ibit.rol.sac.model.Seccion;

import org.ibit.rol.sac.model.TraduccionPerfilGestor;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.SeccionDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilGestorDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/perfilsGestor/")
public class TMPerfilsGestorController extends PantallaBaseController {
	
	private static Log log = LogFactory.getLog(TMPerfilsGestorController.class);
    
    @RequestMapping(value = "/perfilsGestor.do")
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMPerfilsGestor.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmPerfilsGestor.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
    public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {
    	
    	List<Map<String, Object>> llistaPerfilsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> perfilDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
       		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();		
		
		try {
			PerfilGestorDelegate perfilGestorDelegate = DelegateUtil.getPerfilGestorDelegate();
			String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			resultadoBusqueda = perfilGestorDelegate.listarPerfilesGestor(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPorDefecto);
			
			for (Object o : resultadoBusqueda.getListaResultados() ) {
				
				Long id = (Long) ((Object[]) o)[0];
				String codiEstandard = (String) ((Object[]) o)[1];
				String duplica = (String) ((Object[]) o)[2];
				String nom = (String) ((Object[]) o)[3];
				String descripcio = (String) ((Object[]) o)[4];
				
				perfilDTO = new HashMap<String, Object>();
				
				perfilDTO.put("id", id);
				perfilDTO.put("codi_estandard", codiEstandard);
				perfilDTO.put("duplica", (duplica == null || "0".equals(duplica)) ? false : true);
				perfilDTO.put("nom", nom == null ? "" : nom);
				perfilDTO.put("descripcio", descripcio == null? "" : descripcio);
				
				llistaPerfilsDTO.add(perfilDTO);				
			}
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaPerfilsDTO);

		return resultats;
    }
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();

	    try {
	        Long id = new Long(request.getParameter("id"));

	        PerfilGestorDelegate perfilGestorDelegate = DelegateUtil.getPerfilGestorDelegate();
	        PerfilGestor perfilGestor = perfilGestorDelegate.obtenerPerfilGestor(id);	        	        

	        resultats.put("item_id", perfilGestor.getId());
	        resultats.put("item_codi_estandard", perfilGestor.getCodigoEstandar());
	        resultats.put("item_duplica", (perfilGestor.getDuplica() == null || "0".equals(perfilGestor.getDuplica())) ? false : true);

	        // idiomes
	        for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
	            if (perfilGestor.getTraduccion(lang) != null) {
	                resultats.put(lang, (TraduccionPerfilGestor) perfilGestor.getTraduccion(lang));
	            } else {
	                resultats.put(lang, new TraduccionPerfilGestor());
	            }
	        }
	        // fi idiomes

	        //Seccions
	        if (perfilGestor.getSeccions() != null) {
		        Map<String, Object> seccioDTO;
		        List<Map<String, Object>> llistaSeccionsDTO = new ArrayList<Map<String, Object>>();		        

		        for(Seccion seccio: (Set<Seccion>) perfilGestor.getSeccions()) {
			        if (seccio != null) {
			        	seccioDTO = new HashMap<String, Object>(3);
			        	seccioDTO.put("id", seccio.getId());
			        	seccioDTO.put("nombre", seccio.getNombre());
			        	llistaSeccionsDTO.add(seccioDTO);
			        }
		        }
			    resultats.put("seccions", llistaSeccionsDTO);
	        } else {
	        	resultats.put("seccions", null);
	        }
	        // Fi Seccions
	        			
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
    
	
    @RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			PerfilGestorDelegate perfilGestorDelegate = DelegateUtil.getPerfilGestorDelegate();
			PerfilGestor perfil = new PerfilGestor();
			PerfilGestor perfilOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				perfilOld = perfilGestorDelegate.obtenerPerfilGestor(id);
				edicion = true;
				perfil.setId(perfilOld.getId());
				perfil.setSeccions(perfilOld.getSeccions());
				perfil.setUsuaris(perfilOld.getUsuaris());
			} catch (NumberFormatException nfe) {
				perfilOld = null;
				edicion = false;
			}
			
			perfil.setCodigoEstandar(request.getParameter("item_codi_estandard"));
			perfil.setDuplica("on".equalsIgnoreCase(request.getParameter("item_duplica")) ? "1" : "0");	
					
			// Idiomas
			TraduccionPerfilGestor tp;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tp = (TraduccionPerfilGestor) perfilOld.getTraduccion(lang);
					if (tp == null) {
						tp = new TraduccionPerfilGestor();
					}
				} else {
					tp = new TraduccionPerfilGestor();
				}
				
				tp.setNombre( RolUtil.limpiaCadena(request.getParameter("item_nom_" + lang)) );
				tp.setDescripcion( RolUtil.limpiaCadena(request.getParameter("item_descripcio_" + lang)) );
				perfil.setTraduccion(lang, tp);
			}
			// Fin idiomas
			
			Long tipusId = perfilGestorDelegate.grabarPerfilGestor(perfil);
			
			String ok = messageSource.getMessage("perfil.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(tipusId, ok);

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfe) {
			result = new IdNomDTO(-3l, error);
		}

		return result;
	}	
	
	
	@RequestMapping(value = "/esborrarPerfil.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			PerfilGestorDelegate perfilGestorDelegate = DelegateUtil.getPerfilGestorDelegate();
			perfilGestorDelegate.borrarPerfilGestor(id);
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
			log.error("Error: Id de perfil gestor no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
	
	
	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request)
	{
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			TraduccionPerfilGestor traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
			log.error("TMPerfilsGestorController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		} catch (Exception e) {
			log.error("TMPerfilsGestorController.traduir: Error en al traducir perfil gestor: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		}
		
		return resultats;
	}
	
	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			PerfilGestorDelegate perfilGestorDelegate = DelegateUtil.getPerfilGestorDelegate();
			PerfilGestor perfilGestor = perfilGestorDelegate.obtenerPerfilGestor(id);

			List<Seccion> lista = castList(Seccion.class, perfilGestor.getSeccions());
			List<Seccion> listaSecciones= new LinkedList<Seccion>();
			
			// Secciones que pasaremos como resultado.
			List<SeccionDTO> secciones = new LinkedList<SeccionDTO>();

			// Evitamos posibles nulos iniciales devueltos en la variable "lista" por "seccion.getPerfilsGestor()".
			for (Seccion secPerfil : lista) {
				if (secPerfil != null)
					listaSecciones.add(secPerfil);
			}
			
			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			for (Seccion secPerfil : listaSecciones) {

				TraduccionSeccion traSec = (TraduccionSeccion)secPerfil.getTraduccion(lang); 
				
				secciones.add(
					new  SeccionDTO(
						secPerfil.getId(),
						traSec != null ? traSec.getNombre() : "",
						null, 
						false
					)
				);

			}

			resultats.put("seccions", secciones);
			

		} catch (DelegateException dEx) {

			log.error(ExceptionUtils.getStackTrace(dEx));

			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));

			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));

		}

		return resultats;

	}
	
    private TraduccionPerfilGestor getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor)
	{
    	TraduccionPerfilGestor traduccioOrigen = new TraduccionPerfilGestor();
    	
		if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
		}
		
		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_" + idiomaOrigenTraductor));
		}
		
		return traduccioOrigen;
	}
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarSeccions.do")
	public @ResponseBody IdNomDTO guardarSeccions(Long id, Long[] elementos, HttpServletRequest request) {
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type, Spring lo calcula y curiosamente depende del navegador desde el que se hace la petición.
		 * Esto se debe a que como esta petición es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicación. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String error = null;       
		IdNomDTO result = null;

		try{
			
			boolean edicion=true;
			PerfilGestorDelegate perfilDelegate = DelegateUtil.getPerfilGestorDelegate();
			PerfilGestor perfilGestor = new PerfilGestor();
			PerfilGestor perfilGestorOld;
			
			try {
				perfilGestorOld = perfilDelegate.obtenerPerfilGestor(id);
				perfilGestor.setId(perfilGestorOld.getId());
				perfilGestor.setCodigoEstandar(perfilGestorOld.getCodigoEstandar());
				perfilGestor.setDuplica(perfilGestorOld.getDuplica());
		        for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
		            perfilGestor.setTraduccion(lang, perfilGestorOld.getTraduccion(lang));

		        }
		        perfilGestor.setSeccions(perfilGestorOld.getSeccions());
		        perfilGestor.setUsuaris(perfilGestorOld.getUsuaris());
			 } catch (NumberFormatException nfe) {
				 perfilGestorOld = null;
					edicion = false;
			 }

	        //Seccions
			if (elementos != null) {
				SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
	            Set<Seccion> seccionsNoves = new HashSet<Seccion>();
	            /*if (edicion){
	            	//Revisió seccions eliminades
	            	for (int i = 0; i<elementos.length; i++){
	            		for (Seccion secc:(Set<Seccion>)perfilGestorOld.getSeccions()){
	            			if(secc.getId().equals(elementos[i])){
	            				seccionsNoves.add(secc);
	                            elementos[i] = null;
	                            break;
	                        }
	                     }                            
	                 }                         
	             }*/
	             for (Long codiSecc: elementos){
                	if (codiSecc != null){
                        seccionsNoves.add(seccioDelegate.obtenerSeccionSinFichasUA(codiSecc));
                    }                        
	             }
	             perfilGestor.setSeccions(seccionsNoves);  
			 } else {
				perfilGestor.setSeccions(new HashSet<Seccion>());  
			 }
			// Fi Seccions
			
			Long tipusId = perfilDelegate.grabarPerfilGestor(perfilGestor);
			result = new IdNomDTO(tipusId, messageSource.getMessage("perfil.guardat.correcte", null, request.getLocale()));
			
			}  catch (DelegateException dEx) {
				if (dEx.isSecurityException()) {
					error = messageSource.getMessage("error.permisos", null, request.getLocale());
					result = new IdNomDTO(-1l, error);
				} else {
					error = messageSource.getMessage("error.altres", null, request.getLocale());
					result = new IdNomDTO(-2l, error);
					log.error(ExceptionUtils.getStackTrace(dEx));
				}
			}
			return result;
    }
    
	@RequestMapping(value = "/arbreSeccions.do", method = POST)
	public @ResponseBody Map<String, Object> arbreSeccionsDescendents(HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();
		List<Seccion> llistaSeccions = new ArrayList<Seccion>();
		List<SeccionDTO> llistaSeccionsDTO = new ArrayList<SeccionDTO>();

		try {
			
			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
			
			if (request.getParameter("pare") == null  || "".equals(request.getParameter("pare"))) {
				llistaSeccions = seccioDelegate.listarSeccionesRaizPerfil();
			} else {
				llistaSeccions = seccioDelegate.listarHijosSeccion(ParseUtil.parseLong(request.getParameter("pare")));
				
			}

			for (Seccion seccio: llistaSeccions) {
				
				TraduccionSeccion tse = (TraduccionSeccion)seccio.getTraduccion(lang);
				
				llistaSeccionsDTO.add(
					new SeccionDTO(
						seccio.getId(),
						tse == null ? "" : tse.getNombre(),
						seccio.getPadre() == null ? null : seccio.getPadre().getId(),
						seccioDelegate.listarHijosSeccion(seccio.getId()).size() > 0 ? true : false
					)
				);
			}
			
			resultats.put("llistaSeccions", llistaSeccionsDTO);

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
			} else {
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		}

		return resultats;
	}
	
	
    
}
