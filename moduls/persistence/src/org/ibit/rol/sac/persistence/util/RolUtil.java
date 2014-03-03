package org.ibit.rol.sac.persistence.util;

/*
 * Los roles de usuario són inclusivos. Los métodos de esta clase permiten 
 * saber si un usuario tiene los permisos de un determinado rol, bien porque 
 * sea su rol, o porque tenga uno superior. 
 */
public class RolUtil {

	// TODO: obtener estos valores de config_dgtic.properties
	public static final String PERMIS_SYSTEM = "RSC_SYSTEM";
	public static final String PERMIS_ADMIN = "RSC_ADMIN";
	public static final String PERMIS_SUPER = "RSC_SUPER";
	public static final String PERMIS_OPERADOR = "RSC_OPER";
	
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
}