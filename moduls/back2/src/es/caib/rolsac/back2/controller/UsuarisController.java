package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.UsuariDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;

@Controller
@RequestMapping("/usuaris/")
public class UsuarisController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(UsuarisController.class);
	
    @RequestMapping(value = "/usuaris.do")
    public String pantallaUsuaris(Map<String, Object> model, HttpServletRequest request) {
    	model.put("menu", 2);
		model.put("submenu", "layout/submenu/submenuUsuaris.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/Usuaris.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }

    
    @RequestMapping(value = "/llistat.do")
    public @ResponseBody Map<String, Object> llistatUsuaris(HttpServletRequest request, HttpSession session) {
    	
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
    
    
    @RequestMapping(value = "/pagDetall.do")
    public @ResponseBody Map<String, Object> recuperaUsuari(HttpServletRequest request)
    {
    	Map<String, Object> resultats = new HashMap<String, Object>();
    	try {
    		Long id = new Long(request.getParameter("id"));
    		
    		UsuarioDelegate usuariDelegate = DelegateUtil.getUsuarioDelegate();
    		Usuario usuari = usuariDelegate.obtenerUsuario(id);
    		
    		resultats.put("item_id", usuari.getId());
    		resultats.put("item_nom", usuari.getNombre());
    		resultats.put("item_username", usuari.getUsername());
    		resultats.put("item_password", usuari.getPassword());
    		resultats.put("item_email", usuari.getEmail());
    		resultats.put("item_observacions", usuari.getObservaciones());
    		resultats.put("item_perfil", usuari.getPerfil());
    		
    		// UAs relacionades
    		if (usuari.getUnidadesAdministrativas() != null) {
    			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
    			Map<String, Object> uaDTO;
    			List<Map<String, Object>> llistaUAs = new ArrayList<Map<String, Object>>();
    			
    			Iterator<UnidadAdministrativa> uasIterator = usuari.getUnidadesAdministrativas().iterator();
    			while (uasIterator.hasNext()) {
    				UnidadAdministrativa ua = uasIterator.next();
    				uaDTO = new HashMap<String, Object>(2);
    				uaDTO.put("id", ua.getId());
    				uaDTO.put("nombre", ua.getNombreUnidadAdministrativa(lang));
    				llistaUAs.add(uaDTO);
    			}
    			resultats.put("uas", llistaUAs);
    			
    		} else {
    			resultats.put("uas", null);
    		}
    		// Fi UAs relacionades
    		
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
	public @ResponseBody IdNomDTO esborrarUsuaris(HttpServletRequest request) {
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
	public @ResponseBody IdNomDTO guardarUsuari(HttpSession session, HttpServletRequest request) {

    	IdNomDTO result = null;
		String error = null;

		try {
		    if (StringUtils.isBlank(request.getParameter("item_nom")) || 
                    StringUtils.isBlank(request.getParameter("item_username")) ||
                    StringUtils.isBlank(request.getParameter("item_password")) ||
                    StringUtils.isBlank(request.getParameter("item_perfil")) ) {
                throw new Exception(messageSource.getMessage("error.falten_camps", null, request.getLocale()));
            }
		    
			UsuarioDelegate usuariDelegate = DelegateUtil.getUsuarioDelegate();
			Usuario usuari = new Usuario();
			Usuario usuariOld;			
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				usuariOld = usuariDelegate.obtenerUsuario(id);
				// Mantenemos los valores originales que tiene el usuario.
				usuari.setId(id);
				edicion = true;
			} catch (NumberFormatException nfe) {
				usuariOld = null;
				edicion = false;
			}

			usuari.setNombre(request.getParameter("item_nom"));
			usuari.setUsername(request.getParameter("item_username"));
			usuari.setPassword(request.getParameter("item_password"));
			usuari.setPerfil(request.getParameter("item_perfil"));
			usuari.setEmail(request.getParameter("item_email"));
			usuari.setObservaciones(request.getParameter("item_observacions"));
			
            // UAs
			/* Para hacer menos accesos a BBDD se comprueba si es edicion o no. 
             * En el primer caso es bastante probable que se repitan la mayoria de UAs.
             */
			if (request.getParameter("unitatsAdministratives") != null && !"".equals(request.getParameter("unitatsAdministratives"))){
				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
                Set<UnidadAdministrativa> uasNoves = new HashSet<UnidadAdministrativa>();
                String[] codisUAsNoves = request.getParameter("unitatsAdministratives").split(",");
                
                if (edicion){
                    for (int i = 0; i<codisUAsNoves.length; i++){
                    	for (UnidadAdministrativa ua: (Set<UnidadAdministrativa>) usuariOld.getUnidadesAdministrativas()){
                          if(ua.getId().equals(Long.valueOf(codisUAsNoves[i]))) { // ua ya existente
                              uasNoves.add(ua);
                              codisUAsNoves[i] = null;
                              break;
                          }
                      }                            
                    }                         
                }                    
                
                for (String codiUA: codisUAsNoves){
                    if (codiUA != null){
                    	uasNoves.add(uaDelegate.obtenerUnidadAdministrativa(ParseUtil.parseLong(codiUA)));
                    }                        
                }
                
                usuari.setUnidadesAdministrativas(uasNoves);                                                 
            }
            // fin UAs
            
            
			usuariDelegate.grabarUsuario(usuari);
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
    
}
