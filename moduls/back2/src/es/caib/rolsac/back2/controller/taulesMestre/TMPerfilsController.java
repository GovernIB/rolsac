package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/perfils/")
public class TMPerfilsController extends PantallaBaseController {
	
	private static Log log = LogFactory.getLog(TMPerfilsController.class);
    
    @RequestMapping(value = "/perfils.do")
    public String pantallaPerfils(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMPerfils.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmPerfils.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
    public @ResponseBody Map<String, Object> llistatPerfils(HttpServletRequest request) {
    	
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
			PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
			String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			resultadoBusqueda = perfilDelegate.listarPerfiles(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPorDefecto);
			
			for (Object o : resultadoBusqueda.getListaResultados() ) {
				
				Long id = (Long) ((Object[]) o)[0];
				String codiEstandard = (String) ((Object[]) o)[1];
				String pathIconografia = (String) ((Object[]) o)[2];
				String nom = (String) ((Object[]) o)[3];
				String descripcio = (String) ((Object[]) o)[4];
				
				perfilDTO = new HashMap<String, Object>();
				
				perfilDTO.put("id", id);
				perfilDTO.put("codi_estandard", codiEstandard);
				perfilDTO.put("path_iconografia", pathIconografia == null ? "": pathIconografia);
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
	        
	        PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
	        PerfilCiudadano perfil = perfilDelegate.obtenerPerfil(id);	        	        
	        
	        resultats.put("item_id", perfil.getId());
	        resultats.put("item_codi_estandard", perfil.getCodigoEstandard());
	        resultats.put("item_path_iconografia", perfil.getPathIconografia());
	        
	        
	        // idiomes
	        if (perfil.getTraduccion("ca") != null) {
				resultats.put("ca", (TraduccionPerfilCiudadano) perfil.getTraduccion("ca"));
			} else {
				resultats.put("ca", new TraduccionPerfilCiudadano());
			}
	        if (perfil.getTraduccion("es") != null) {
				resultats.put("es", (TraduccionPerfilCiudadano) perfil.getTraduccion("es"));
			} else {
				resultats.put("es", new TraduccionPerfilCiudadano());
			}
	        if (perfil.getTraduccion("en") != null) {
				resultats.put("en", (TraduccionPerfilCiudadano) perfil.getTraduccion("en"));
			} else {
				resultats.put("en", new TraduccionPerfilCiudadano());
			}
	        if (perfil.getTraduccion("de") != null) {
				resultats.put("de", (TraduccionPerfilCiudadano) perfil.getTraduccion("de"));
			} else {
				resultats.put("de", new TraduccionPerfilCiudadano());
			}
	        if (perfil.getTraduccion("fr") != null) {
				resultats.put("fr", (TraduccionPerfilCiudadano) perfil.getTraduccion("fr"));
			} else {
				resultats.put("fr", new TraduccionPerfilCiudadano());
			}
	        // fi idiomes
	        
	        
	        //Icones Familia 
	        if (perfil.getIconosFamilia() != null) {
		        Map<String, Object> iconaDTO;
		        List<Map<String, Object>> llistaIcones = new ArrayList<Map<String, Object>>();		        
		        
		        for(IconoFamilia iconaFamilia: (Set<IconoFamilia>) perfil.getIconosFamilia()) {
			        if (iconaFamilia != null && iconaFamilia.getIcono() != null) {
			        	
			        	iconaDTO = new HashMap<String, Object>(3);
				        iconaDTO.put("id", iconaFamilia.getId());
				        iconaDTO.put("nombre", iconaFamilia.getIcono().getNombre());
				        iconaDTO.put("url", "iconesFamilia/archivo.do?id=" + iconaFamilia.getId());
				        
				        llistaIcones.add(iconaDTO);
			        } else {
			        	log.error("La fam�lia " + perfil.getId() + " te una icona null o sense arxiu.");
			        }
		        }
			    resultats.put("iconesFamilia", llistaIcones);
	        } else {
	        	resultats.put("iconesFamilia", null);
	        }
	        // Fi icones familia
	        
	        
	      //Icones Materia
	        if (perfil.getIconosMateria() != null) {
		        Map<String, Object> iconaDTO;
		        List<Map<String, Object>> llistaIcones = new ArrayList<Map<String, Object>>();		        
		        
		        for(IconoMateria iconaMateria: (Set<IconoMateria>) perfil.getIconosMateria()) {
			        if (iconaMateria != null && iconaMateria.getIcono() != null) {
			        	
			        	iconaDTO = new HashMap<String, Object>(3);
				        iconaDTO.put("id", iconaMateria.getId());
				        iconaDTO.put("nombre", iconaMateria.getIcono().getNombre());
				        iconaDTO.put("url", "iconesMateria/archivo.do?id=" + iconaMateria.getId());
				        
				        llistaIcones.add(iconaDTO);
			        } else {
			        	log.error("La mat�ria " + perfil.getId() + " te una icona null o sense arxiu.");
			        }
		        }
			    resultats.put("iconesMateria", llistaIcones);
	        } else {
	        	resultats.put("iconesMateria", null);
	        }
	        // Fi icones materia
	        			
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
	public @ResponseBody IdNomDTO guardarPerfil(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
			PerfilCiudadano perfil = new PerfilCiudadano();
			PerfilCiudadano perfilOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				perfilOld = perfilDelegate.obtenerPerfil(id);
				edicion = true;
				perfil.setId(perfilOld.getId());
			} catch (NumberFormatException nfe) {
				perfilOld = null;
				edicion = false;
			}
			
			perfil.setCodigoEstandard(request.getParameter("item_codi_estandard"));
			perfil.setPathIconografia(request.getParameter("item_path_iconografia"));
		
	        // TODO Recuperar IconosFamilia y IconosMateria
			
			// Idiomas
			TraduccionPerfilCiudadano tp;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tp = (TraduccionPerfilCiudadano) perfilOld.getTraduccion(lang);
					if (tp == null) {
						tp = new TraduccionPerfilCiudadano();
					}
				} else {
					tp = new TraduccionPerfilCiudadano();
				}

				tp.setNombre(request.getParameter("item_nom_" + lang));
				tp.setDescripcion(request.getParameter("item_descripcio_" + lang));
				perfil.setTraduccion(lang, tp);
			}
			// Fin idiomas
			
			Long tipusId = perfilDelegate.grabarPerfil(perfil);
			
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
	public @ResponseBody IdNomDTO esborrarTipusNormativa(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
			perfilDelegate.borrarPerfil(id);
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
}
