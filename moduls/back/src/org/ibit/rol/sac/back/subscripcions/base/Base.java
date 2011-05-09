package org.ibit.rol.sac.back.subscripcions.base;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.back.subscripcions.Suscripcionback;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.TipoSuscripcionDelegate;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;


/**
 * Clase básica para manejar información.
 * 
 * Elementos que van a estar en sesion:
 * MVS_suscripcion: (TipoSuscripcion) El tipo de Suscripcion actual 
 * username: login del usuario logado
 * rolesnames: hashtable con los roles del usuario
 * rolesnamestxt: string informativo de los roles del usuario
 * MVS_usuario: bean de usuario
 * MVS_rol_sys_adm: 'yes' si es system o admin, 'no' si no tienen ninguno de esos perfiles 
 * @author vroca
 *
 */

public class Base {

	protected static Log log = LogFactory.getLog(Base.class);
	
	private static String[] roles = new String[]{"sacsystem", "sacadmin", "sacsuper"};	
	

	/**
	 * Método estático que recarga en sesion el site.
	 * Variables que mete en sesion:
	 * MVS_suscripcion: bean suscripcion completo
	 * tituloSuscripcion: string informativo con el titulo del suscripcion
	 * @param idTipo
	 * @param request
	 * @throws Exception
	 */
	public static void suscripcionRefresh(Long idTipo, HttpServletRequest request) throws Exception  {
		
		try {
			//meter en sesion el tipo de suscripcion
			TipoSuscripcionDelegate tipoSusDel = DelegateUtil.getTipoSuscripcionDelegate();
			TipoSuscripcion tipoSuscripcion = tipoSusDel.obtenerTipoSuscripcion(idTipo);
			request.getSession().setAttribute("MVS_suscripcion",tipoSuscripcion);
			request.getSession().setAttribute("tituloSuscripcion", tipoSuscripcion.getNombre());
			
			String value = System.getProperty("es.indra.caib.rolsac.oficina");
			String servidor;
			if ((value == null) || value.equals("N"))
				servidor = "http://"+ Suscripcionback.RESOURCE_HOST;
			else 
				servidor = "";
			
			request.getSession().setAttribute("servidorBoletin", servidor);
			
		} catch (Exception e) {
			throw new Exception("org.ibit.rol.sac.back.subscripcions.base.base.suscripcionRefresh-->Error: " + e.getMessage());				
		}
		
	}

	
	/**
	 * Método estatico que borra todas las variables de sesión relacionadas con el suscripcion
	 * @param request
	 */
	public static void borrarVSession(HttpServletRequest request) {
		request.getSession().removeAttribute("tituloSuscripcion");
		request.getSession().removeAttribute("MVS_suscripcion");
	}
	
	
	
	/**
	 * Método estatico que mete en sesion el usuario logado y sus roles.
	 * Variables que se meten en sesion:
	 * username: login del usuario logado
	 * rolesnames: hashtable con los roles del usuario
	 * rolesnamestxt: string informativo de los roles del usuario
	 * MVS_usuario: bean de usuario
	 * MVS_rol_sys_adm: 'yes' si es system o admin, 'no' si no tienen ninguno de esos perfiles
	 * @param request
	 * @throws Exception
	 */
	public static void usuarioRefresh(HttpServletRequest request) throws Exception {
		
    	//recoger usuario..... 
    	UsuarioDelegate usudel=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUsuarioDelegate();
    	Usuario usu = usudel.obtenerUsuariobyUsername(request.getRemoteUser());
    	
    	// meter USUARIO Y ROLES en sesion
		Hashtable rolenames=null;
		String rolenamestxt="";
    	if (request.getRemoteUser() != null) {
    		request.getSession().setAttribute("username", request.getRemoteUser());
            rolenames = new Hashtable();
            for (int i = 0; i < roles.length; i++) {
                if (request.isUserInRole(roles[i])) {
                    rolenames.put(roles[i],roles[i]);
                    rolenamestxt+= roles[i] + ", ";
                }
            }
            rolenamestxt=rolenamestxt.substring(0,rolenamestxt.length()-2);
            request.getSession().setAttribute("rolenames", rolenames);
            request.getSession().setAttribute("rolenamestxt", "["+rolenamestxt+"]");
            //meter en sesion si el usuario es system o admin
            if ((rolenames.contains(roles[0])) || (rolenames.contains(roles[1])))
            	request.getSession().setAttribute("MVS_rol_sys_adm", "yes");
            else 
            	request.getSession().setAttribute("MVS_rol_sys_adm", "no");
        }        
    	 
        
    	request.getSession().setAttribute("MVS_usuario", usu);	
		
	}
	



	/**
	 * Método que comprueba si un usuario tiene acceso a un tipo de suscripcion
	 * @param request
	 * @param idTipo
	 * @return 'true' en caso de tener acceso, 'false' en caso de no tener acceso o producirse un error
	 */
	public static boolean hasTipoSuscripcionPermiso(HttpServletRequest request, Long idTipo) {
		
		boolean retorno=false;
    	
		try {
			TipoSuscripcionDelegate tipoSusDel = DelegateUtil.getTipoSuscripcionDelegate();
			TipoSuscripcion tipoSuscripcion = tipoSusDel.obtenerTipoSuscripcion(idTipo);
			
			//obtener usuario
	    	UsuarioDelegate usudel=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUsuarioDelegate();
	    	Usuario usu = usudel.obtenerUsuariobyUsername(request.getRemoteUser());
	    	
	    	
	    	//obtener uo
	    	UnidadAdministrativaDelegate uniad=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate();
	        UnidadAdministrativa uo = null;
        
        	uo = uniad.obtenerUnidadAdministrativa(new Long(tipoSuscripcion.getUnidadAdministrativa()));
        	
            if (usu.hasAccess(uo)) retorno=true; 
        } catch (Exception e) {
        	log.warn("[AVISO] se ha producido un error comprobando [suscripcion, usuario registrado, uo]");
        }
    	      
		return retorno;
	}	
	

	
	
		
	

	
}
