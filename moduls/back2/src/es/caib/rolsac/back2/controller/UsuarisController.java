package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.PerfilGestor;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.UsuariDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PerfilGestorDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.RolUtil;

@Controller
@RequestMapping("/usuaris/")
public class UsuarisController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(UsuarisController.class);
	
    @RequestMapping(value = "/usuaris.do")
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
    	
    	model.put("menu", 2);
		model.put("submenu", "layout/submenu/submenuUsuaris.jsp");
		
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/Usuaris.jsp");
    		try {
    	       	String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
            	
        		// Perfil Gestor
        		PerfilGestorDelegate perfilDelegate = DelegateUtil.getPerfilGestorDelegate();
        		List<PerfilGestor> llistaPerfilsGestor = new ArrayList<PerfilGestor>();
        		List<IdNomDTO> llistaPerfilsGestorDTO = new ArrayList<IdNomDTO>();
				llistaPerfilsGestor = castList(PerfilGestor.class, perfilDelegate.listarPerfilesGestor());
	    		for (PerfilGestor perfil : llistaPerfilsGestor) {
	    			llistaPerfilsGestorDTO.add(new IdNomDTO(perfil.getId(), perfil.getNombrePerfilGestor(lang)));
	    		}
	    		model.put("llistaPerfilsGestor", llistaPerfilsGestorDTO);
	    		model.put("valorDefectoGestionNormativas", getPermisoGestionNormativasPorDefecto(true)?"true":"false");
			}catch (DelegateException dEx) {
        		
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
    public @ResponseBody Map<String, Object> llistat(HttpServletRequest request, HttpSession session) {
    	
    	List<UsuariDTO> llistaUsuarisDTO = new ArrayList<UsuariDTO>();
    	Map<String, Object> resultats = new HashMap<String, Object>();
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	String nombre = request.getParameter("nom");
    	if (nombre != null && !"".equals(nombre)) {
    		paramMap.put("nombre", nombre.toUpperCase());
    	}
    	
    	String username = request.getParameter("username");
    	if (username != null && !"".equals(username)) {
    		paramMap.put("username", username.toUpperCase());
    	}
    	
    	String perfil = request.getParameter("perfil");
    	if (perfil != null && !"".equals(perfil)) {
    		paramMap.put("perfil", perfil.toUpperCase());
    	}
    	    	
    	String email = request.getParameter("email");
    	if (email != null && !"".equals(email)) {
    		paramMap.put("email", email.toUpperCase());
    	}
    	
    	String observacions = request.getParameter("observacions");
    	if (observacions != null && !"".equals(observacions)) {
    		paramMap.put("observaciones", observacions.toUpperCase());
    	}
    	
    	String paramIdUA = request.getParameter("idUA");
    	Long idUA = (paramIdUA != null && !StringUtils.isBlank(paramIdUA)) ? Long.valueOf(request.getParameter("idUA")) : null;
    	if (idUA != null) {
    		paramMap.put("idUA", idUA);
    	}
    	
    	String paramIdPerfilGestor = request.getParameter("idPerfilGestor");
    	Long idPerfilGestor = (paramIdPerfilGestor != null && !StringUtils.isBlank(paramIdPerfilGestor)) ? Long.valueOf(request.getParameter("idPerfilGestor")) : null;
    	if (idPerfilGestor != null) {
    		paramMap.put("idPerfilGestor", idPerfilGestor);
    	}
    	
    	try {
    		
    		UsuarioDelegate usuarisDelegate = DelegateUtil.getUsuarioDelegate();
    		List<Usuario> llistaUsuaris = usuarisDelegate.buscarUsuarios(paramMap);
    		
    		for (Usuario usuari : llistaUsuaris) {
    			llistaUsuarisDTO.add(new UsuariDTO(
    					usuari.getId(),
    					usuari.getNombre(),
    					usuari.getUsername(),
    					usuari.getPerfil(),
    					usuari.getEmail()
    				));
    		}
    		
    	} catch (DelegateException dEx) {
    		
    		if (dEx.isSecurityException()) {
    			log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
    		} else {
    			log.error("Error: " + ExceptionUtils.getStackTrace(dEx));
    		}
    		
    	}
    	
    	resultats.put("total", llistaUsuarisDTO.size());
    	resultats.put("nodes", llistaUsuarisDTO);
    	
    	return resultats;
    	
    }
    
	private static boolean getPermisoGestionNormativasPorDefecto(boolean valorPorDefecto) {
		String val = System.getProperty("es.caib.rolsac.usuari.gestioNormativa");
		if(StringUtils.isEmpty(val) || val.trim().length()!=1) {
			return valorPorDefecto;
		}
		return val.trim().equals("S");
		
	}
    
    @RequestMapping(value = "/pagDetall.do")
    public @ResponseBody Map<String, Object> recuperaDetall(Long id, HttpServletRequest request) {
    	
    	Map<String, Object> resultats = new HashMap<String, Object>();
    	
    	try {
    		
    		UsuarioDelegate usuariDelegate = DelegateUtil.getUsuarioDelegate();
    		Usuario usuari = usuariDelegate.obtenerUsuario(id);
    		
    		resultats.put("item_id", usuari.getId());
    		resultats.put("item_nom", usuari.getNombre());
    		resultats.put("item_username", usuari.getUsername());
    		resultats.put("item_password", usuari.getPassword());
    		resultats.put("item_email", usuari.getEmail());
    		resultats.put("item_observacions", usuari.getObservaciones());
    		resultats.put("item_perfil", usuari.getPerfil());
    		resultats.put("item_check_permis_modificacio_normativa", usuari.tienePermiso(Usuario.PERMISO_MODIFICACION_NORMATIVA));
    		
    	} catch (DelegateException dEx) {
    		
    		log.error(ExceptionUtils.getStackTrace(dEx));
    		if (dEx.isSecurityException())
    			resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
    		else
    			resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
    		
    	}
    	
    	return resultats;
    	
    }
    
    @RequestMapping(value = "/modulos.do")
   	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {
   		
   		Map<String, Object> resultats = new HashMap<String, Object>();
   		
   		try {
   			
    		UsuarioDelegate usuariDelegate = DelegateUtil.getUsuarioDelegate();
    		Usuario usuari = usuariDelegate.obtenerUsuario(id);
    		
    		String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
    		
	        //Perfils Gestor
    		List<PerfilGestor> listaPerfilGestor = new ArrayList<PerfilGestor>();
    		if (usuari.getPerfilsGestor()!=null){
    			listaPerfilGestor.addAll(usuari.getPerfilsGestor());
    		}
			resultats.put("perfilsGestor", CargaModulosLateralesUtil.recuperaPerfilesGestorRelacionados(listaPerfilGestor, id, lang, false));
    		
    		// Pasamos el Set usuari.getUnidadesAdministrativas() a un elemento List.
    		List<UnidadAdministrativa> listaUAs = new ArrayList<UnidadAdministrativa>(usuari.getUnidadesAdministrativas());    		
    			
			// Pasamos las UAs a un DTO que "entienda" Spring.
			List<Map<String, Object>> listaUAsDTO = CargaModulosLateralesUtil.recuperaUAsRelacionadas(listaUAs, id, lang, false);
			
			resultats.put("uas", listaUAsDTO);
   			
   		} catch (DelegateException dEx) {

   			log.error(ExceptionUtils.getStackTrace(dEx));
   			
   			if (dEx.isSecurityException())
   				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
   			
   			else
   				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
   			
   		}
   		
   		return resultats;
   		
   	}
    
    @RequestMapping(value = "/esborrarUsuari.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();

		try {
			
			Long id = new Long(request.getParameter("id"));
			UsuarioDelegate usuarisDelegate = DelegateUtil.getUsuarioDelegate();
			usuarisDelegate.borrarUsuario(id);

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
    
    @RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(HttpSession session, HttpServletRequest request) {

    	IdNomDTO result = null;
		String error = null;

		try {
			
		    if (StringUtils.isBlank(request.getParameter("item_nom")) || 
                    StringUtils.isBlank(request.getParameter("item_username")) ||
                    StringUtils.isBlank(request.getParameter("item_password")) ||
                    StringUtils.isBlank(request.getParameter("item_perfil")) ) {
		    	
                throw new Exception(messageSource.getMessage("error.falten_camps", null, request.getLocale()));
                
            }
		    
			UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
			Usuario usuari = new Usuario();
			
			try {
			
				// Edición
				Long id = Long.parseLong(request.getParameter("item_id"));
				Usuario usuarioOld = usuarioDelegate.obtenerUsuario(id);
				
				// Conservamos ID y anteriores relaciones que se guardan de forma independiente.
				usuari.setId(id);
				usuari.setPerfilsGestor(usuarioOld.getPerfilsGestor());
				usuari.setUnidadesAdministrativas(usuarioOld.getUnidadesAdministrativas());
				
			} catch (NumberFormatException nfe) {
			
				// Creación
				usuari.setId(null);
				
			}
			
			usuari.setNombre(request.getParameter("item_nom"));
			usuari.setUsername(request.getParameter("item_username"));
			usuari.setPassword(request.getParameter("item_password"));
			usuari.setPerfil(request.getParameter("item_perfil"));
			usuari.setEmail(request.getParameter("item_email"));
			usuari.setObservaciones(request.getParameter("item_observacions"));
			usuari.setPermiso(Usuario.PERMISO_MODIFICACION_NORMATIVA, request.getParameter("item_check_permis_modificacio_normativa") != null && !"".equals(request.getParameter("item_check_permis_modificacio_normativa")));		            
			usuarioDelegate.grabarUsuario(usuari);
			
			String ok = messageSource.getMessage("usuari.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(usuari.getId(), ok);

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		} catch (Exception e) {
			
            result = new IdNomDTO(-3l, e.getMessage());
            
        }

		return result;
		
	}
    
	@RequestMapping(value = "/guardarUnidadesRelacionadas.do")
	public @ResponseBody IdNomDTO guardarUnidadesRelacionadas(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
						
			UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
			Usuario usuario = usuarioDelegate.obtenerUsuario(id);
									
			UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
			Set<UnidadAdministrativa> uasNuevas = new HashSet<UnidadAdministrativa>();
							
			if (elementos != null) {
				for ( int i = 0; i < elementos.length; i++ ) {
					
					if ( elementos[i] != null ) {
						
						UnidadAdministrativa ua = uaDelegate.consultarUnidadAdministrativaSinFichas(elementos[i]);
						uasNuevas.add(ua);
						
					}
					
				}
			}
			
			usuario.setUnidadesAdministrativas(uasNuevas);
			usuarioDelegate.grabarUsuario(usuario);
						
			String ok = messageSource.getMessage("usuari.guardat.uas.correcte", null, request.getLocale());
			result = new IdNomDTO(usuario.getId(), ok);

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		}

		return result;
		
	}
    
	@RequestMapping(value = "/guardarPerfilsGestorRelacionats.do")
	public @ResponseBody IdNomDTO guardarPerfilsGestorRelacionats(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
						
			UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
			Usuario usuario = usuarioDelegate.obtenerUsuario(id);
			Set<PerfilGestor> perfilesNuevos = new HashSet<PerfilGestor>();
									
			// Procesamos los elementos actuales.
			if ( elementos != null ) {
				
				PerfilGestorDelegate perfilDelegate = DelegateUtil.getPerfilGestorDelegate();
								
				for ( int i = 0; i < elementos.length; i++ ) {
					
					if ( elementos[i] != null ) {
						
						PerfilGestor perfil = perfilDelegate.obtenerPerfilGestor(elementos[i]);
						perfilesNuevos.add(perfil);
						
					}
					
				}
				
				
			}
						
			usuario.setPerfilsGestor(perfilesNuevos);
			usuarioDelegate.grabarUsuario(usuario);
						
			String ok = messageSource.getMessage("perfil.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(usuario.getId(), ok);

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		}

		return result;
		
	}
    
}
