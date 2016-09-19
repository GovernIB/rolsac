package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.SilencioAdm;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionSilencio;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/silenciAdm/")
public class TMSilenciController extends PantallaBaseController {
	
	private static Log log = LogFactory.getLog(TMSilenciController.class);
	
	@RequestMapping(value = "/silenciAdm.do")
	public String pantalla(Map<String, Object> model, HttpServletRequest request) {
		
		model.put("menu", 1);
		model.put("submenu", "layout/submenu/submenuTMSilencio.jsp");
		
		RolUtil rolUtil= new RolUtil(request);
		
		if (rolUtil.userIsAdmin()) {
			
			model.put("escriptori", "pantalles/taulesMestres/tmSilenciAdm.jsp");
			
			// afegir llista de perfils
			String nombrePerfil;
			List<IdNomDTO> perfilsDTO = new LinkedList<IdNomDTO>();
			PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
			
			try {
				
				String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
				
				for (PerfilCiudadano perfil: (List<PerfilCiudadano>) perfilDelegate.listarPerfiles()) {
					Traduccion traPerfil = perfil.getTraduccion(lang);
					nombrePerfil = traPerfil == null ? "" : ((TraduccionPerfilCiudadano) traPerfil).getNombre();
					perfilsDTO.add(new IdNomDTO(perfil.getId(), nombrePerfil));
				}
				
				model.put("perfils", perfilsDTO);
				
			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException()) {
					log.error("Permisos insuficients: " + dEx.getMessage());
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
	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {
		
		List<Map<String, Object>> llistaSilencioDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> silencioDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		//Información de paginación
		String pagPag = request.getParameter("pagPag");
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
       		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();						
		
		try {
			
			SilencioAdmDelegate silencioDelegate = DelegateUtil.getSilencioAdmDelegate();
			String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			resultadoBusqueda = silencioDelegate.listarSilencioAdm(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPorDefecto);
			
			for (Object o : resultadoBusqueda.getListaResultados() ) {
				
				String codigo = (String) ((Object[]) o)[0];
				String nom = ((Object[]) o)[1] == null ? "" : (String) ((Object[]) o)[1];
				String descripcio = ((Object[]) o)[2] == null ? "" : (String) ((Object[]) o)[2];
				
				silencioDTO = new HashMap<String, Object>();
				silencioDTO.put("id", codigo);//366
				silencioDTO.put("nom", nom);
				silencioDTO.put("descripcio", descripcio);
				
				llistaSilencioDTO.add(silencioDTO);
				
			}
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
			
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaSilencioDTO);

		return resultats;
		
	}
	
	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(String id, HttpServletRequest request) {
		
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {

			SilencioAdmDelegate silencioDelegate = DelegateUtil.getSilencioAdmDelegate();
			SilencioAdm silencio = silencioDelegate.obtenerSilencioAdm(id);
			
			resultats.put("item_id", silencio.getId());
			omplirCampsTraduibles(resultats, silencio);

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
	
    private void omplirCampsTraduibles(Map<String, Object> resultats, SilencioAdm silencio)
    		throws DelegateException {
    	
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang : langs) {
			
		    if (null != silencio.getTraduccion(lang)) {
		    	HashMap<String, String> traduccionDTO = new HashMap<String, String>();
		    	TraduccionSilencio tm = (TraduccionSilencio) silencio.getTraduccion(lang);

				traduccionDTO.put("item_nombre", tm.getNombre());
				traduccionDTO.put("item_descripcio", tm.getDescripcion());
				
				resultats.put(lang, traduccionDTO);
		  
		    	
				//resultats.put(lang, (TraduccionSilencio)silencio.getTraduccion(lang));
			} else {
				resultats.put(lang, new TraduccionSilencio());
			}
		    
		}
		
	}
    
    @RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody Map<String, Object> guardar(HttpServletRequest request) {

		IdNomDTO result = null;
		Map<String, Object> resultats = new HashMap<String, Object>();
		String error = null;

		try {
			
			SilencioAdmDelegate silencioDelegate = DelegateUtil.getSilencioAdmDelegate();
			SilencioAdm silencio = new SilencioAdm();
			SilencioAdm silencioOld = null;
			boolean edicion;
			
		
			edicion = request.getParameter("item_idAnt") != null && !"".equals(request.getParameter("item_idAnt"));
			String id = request.getParameter("item_id");
			silencio.setId(id);
				
			if (edicion) {

				silencioOld = silencioDelegate.obtenerSilencioAdm(id);

			}
			
			
			// Idiomas
			TraduccionSilencio tf;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang : langs) {
				
				if (edicion) {
					
					tf = (TraduccionSilencio)silencioOld.getTraduccion(lang);
					if (tf == null) {
						tf = new TraduccionSilencio();
					}
					
				} else {
					
					tf = new TraduccionSilencio();
					
				}

				tf.setNombre( RolUtil.limpiaCadena(request.getParameter("item_nombre_" + lang)) );
				tf.setDescripcion( RolUtil.limpiaCadena(request.getParameter("item_descripcio_" + lang)) );
				
				silencio.setTraduccion(lang, tf);
				
			}
			// Fin idiomas
			
			silencioDelegate.grabarSilencioAdm(silencio, edicion);
			
			String ok = messageSource.getMessage("silencio.guardat.correcte", null, request.getLocale());
			//result = new IdNomDTO(silencioId, ok);
			result = new IdNomDTO(1L, ok);
			resultats.put("nodo", result);
			resultats.put("codigo", silencio.getId());

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				resultats.put("nodo", result);
				
			} else {
				
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				resultats.put("nodo", result);
				
				log.error(ExceptionUtils.getStackTrace(dEx));
				
			}
			
		} catch (NumberFormatException nfe) {
			
			result = new IdNomDTO(-3l, error);
			resultats.put("nodo", result);
			
		}

		return resultats;
		
	}	
    
    @RequestMapping(value = "/esborrarSilencioAdm.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
    	
		IdNomDTO resultatStatus = new IdNomDTO();
		
		try {
			String id = request.getParameter("id");
			SilencioAdmDelegate silencioDelegate = DelegateUtil.getSilencioAdmDelegate();
			silencioDelegate.borrarSilencioAdm(id);
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
			log.error("Error: Código de silencio no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		
		return resultatStatus;
		
	}
    
    @RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {
    	
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			TraduccionSilencio traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
			
			log.error("SilencioAdmBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		} catch (Exception e) {
			
			log.error("SilencioAdmBackController.traduir: Error  al traducir silencio: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		}
		
		return resultats;
		
	}
	
    private TraduccionSilencio getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {
    	
    	TraduccionSilencio traduccioOrigen = new TraduccionSilencio();
		
		if (StringUtils.isNotEmpty(request.getParameter("item_nombre_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nombre_" + idiomaOrigenTraductor));
		}
		
		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_" + idiomaOrigenTraductor));
		}
		
		return traduccioOrigen;
		
	}
    
}
