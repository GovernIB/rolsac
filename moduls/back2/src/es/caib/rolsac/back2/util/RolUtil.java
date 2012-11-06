package es.caib.rolsac.back2.util;

import javax.servlet.http.HttpServletRequest;

/*
 * Los roles de usuario són inclusivos. Los mètodos de esta clase permiten 
 * saber si un usuario tiene los permisos de un determinado rol, bien porque 
 * sea su rol, o porque tenga uno superior. 
 */
public class RolUtil {

	// TODO: obtener estos valores de config_dgtic.properties
	public static final String PERMIS_SYSTEM = "RSC_SYSTEM";
	public static final String PERMIS_ADMIN = "RSC_ADMIN";
	public static final String PERMIS_SUPER = "RSC_SUPER";
	public static final String PERMIS_OPERADOR = "RSC_OPER";
	// public static final String PERMIS_INFO = "RSC_INFO";
	
	private HttpServletRequest request;
	
	public RolUtil(HttpServletRequest request) {
		this.request = request;
	}

//	public List<String> getRoles() {
//		List<String> rolesList = new ArrayList<String>(4);
//		rolesList.add(PERMIS_SYSTEM);
//		rolesList.add(PERMIS_ADMIN);
//		rolesList.add(PERMIS_SUPER);
//		rolesList.add(PERMIS_OPERADOR);
//		return rolesList;
//	}
	
	public static boolean userIsSystem(String role) {
		return PERMIS_SYSTEM.equals(role);
	}
	
	public static boolean userIsAdmin(String role) {
		return userIsSystem(role) || PERMIS_ADMIN.equals(role);
	}
	
	public static boolean userIsSuper(String role) {
		return userIsAdmin(role) || PERMIS_SUPER.equals(role);
	}

	public static boolean userIsOper(String role) {
		return userIsSuper(role) || PERMIS_OPERADOR.equals(role);
	}
		
	
	public boolean userIsSystem() {
		return request.isUserInRole(PERMIS_SYSTEM);
	}
	
	public boolean userIsAdmin() {
		return userIsSystem() || request.isUserInRole(PERMIS_ADMIN);
	}
	
	public boolean userIsSuper() {
		return userIsAdmin() || request.isUserInRole(PERMIS_SUPER);
	}
	
	public boolean userIsOper() {
		return userIsSuper() || request.isUserInRole(PERMIS_OPERADOR);
	}
	
//	public boolean userIsInfo() {
//		return userIsOper() || request.isUserInRole(PERMIS_INFO);
//	}
	
	public String getUserRol() {
		String rol;
		
		if (userIsSystem()) {
			rol = PERMIS_SYSTEM;
		} else if (userIsAdmin()) {
			rol = PERMIS_ADMIN;
		} else if (userIsSuper()) {
			rol = PERMIS_SUPER;
		} else if (userIsOper()) {
			rol = PERMIS_OPERADOR;
//		} else if (userIsInfo()) {
//			rol = PERMIS_INFO;
		} else {
			rol = null;
		}
		
		return rol;
	}
	
    public boolean userIs(String role) {
    	boolean userIsRol;
    	
//        if (PERMIS_INFO.equals(role)) {
//            userIsRol = userIsInfo();
    	if (PERMIS_OPERADOR.equals(role)) {
    		userIsRol = userIsOper();
        } else if (PERMIS_SUPER.equals(role)) {
        	userIsRol = userIsSuper();
        } else if (PERMIS_ADMIN.equals(role)) {
        	userIsRol = userIsAdmin();
        } else if (PERMIS_SYSTEM.equals(role)) {
        	userIsRol = userIsSystem();
        } else {
        	userIsRol = false;
        }
    	
    	return userIsRol;
    }

	public HttpServletRequest getRequest() {
    	return request;
    }


	public void setRequest(HttpServletRequest request) {
    	this.request = request;
    }
	
}