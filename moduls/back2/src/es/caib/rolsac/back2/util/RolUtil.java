package es.caib.rolsac.back2.util;

import javax.servlet.http.HttpServletRequest;

/*
 * Los roles de usuario s�n inclusivos. Los m�todos de esta clase permiten 
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
	
	/**
	 * Limpia caracteres no traducibles (normalmente de windows-1252, pegando desde MS Word) de UTF-8 a ISO-8859-1 de una cadena.
	 * @param s cadena a tratar.
	 * @return cadena tratada.
	 */
	public static String limpiaCadena(String s) {
		
		StringBuilder b = new StringBuilder();
		
		if (s != null) {
				
			for (int i = 0; i < s.length(); i++) {
					
				// Debug
				// System.out.println(i + ": " + s.charAt(i) + " => " + Integer.toHexString(s.charAt(i)));
				
				// Comillas simples: ‘ y ’ => '
				if ( s.charAt(i) == 0x2018 || s.charAt(i) == 0x2019 || s.charAt(i) == 0x201a )
					b.append((char)0x0027);
					
				// Comillas dobles: “ y ” => " 
				else if ( s.charAt(i) == 0x201c || s.charAt(i) == 0x201d || s.charAt(i) == 0x201e
					   || s.charAt(i) == 0x2039 || s.charAt(i) == 0x203a )
					b.append((char)0x0022);
				
				// Punto central: • => ·
				else if ( s.charAt(i) == 0x2022 )
					b.append((char)0x00b7);
				
				// Acento circunflejo 
				else if ( s.charAt(i) == 0x02c6 )
					b.append((char)0x005e);
				
				// Cualquier otro caso.
				else
					b.append(s.charAt(i));
				
			}
		
		}
			
		return b.toString();
		
	}
	
}