package es.caib.rolsac.back2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class PantallaBaseController {

	protected MessageSource messageSource = null;
	private static Log log = LogFactory.getLog(PantallaBaseController.class);

	public PantallaBaseController() {
		super();
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
	    this.messageSource = messageSource;
	}

	/**
	 * Carga en el modelo lo necesario para la pantalla index
	 * @param model
	 * @param request
	 */
	protected void loadIndexModel (Map<String, Object> model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String nomLlinatges = (String)session.getAttribute("capNomLlinatges");
		
		if (nomLlinatges == null) {
			
	    	String username = request.getRemoteUser();
	    	nomLlinatges = username; 
	    	
		    try {
		    	
		    	model.put("idiomaVal", DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
		    	
		    	UsuarioDelegate usuariDelegate = DelegateUtil.getUsuarioDelegate();
		    	Usuario usuari = usuariDelegate.obtenerUsuariobyUsername(username);
		    	if (usuari != null && !StringUtils.isEmpty(usuari.getNombre())) {
		    		nomLlinatges = usuari.getNombre();
		    	}
		    	
		    	session.setAttribute("nomLlinatges", nomLlinatges);
	        
		    } catch (DelegateException dEx) {
		    	
				log.error(ExceptionUtils.getStackTrace(dEx));
				
			}
		    
		}
			    
	    model.put("capNomLlinatges", nomLlinatges);
	    model.put("capUrlSortir", System.getProperty("es.caib.rolsac.back2.urlSortir"));
	    
	    String permisosSuperAddicionals = System.getProperty("es.caib.rolsac.permisosSuperAddicionals");
	    model.put("permisosSuperAddicionals", "Y".equalsIgnoreCase(permisosSuperAddicionals));
	    
	    // Comprobamos si hay que mostrar la opción de traducir:
 		Boolean traductorActivo = Boolean.FALSE;
 		
 		if (System.getProperty("es.caib.rolsac.integracion.traductor") != null) {
 			traductorActivo = Boolean.valueOf("S".equalsIgnoreCase(System.getProperty("es.caib.rolsac.integracion.traductor")));
 		}
 			
 		try {
			Properties versionsProperties = new Properties();
			versionsProperties.load(PantallaBaseController.class.getClassLoader().getResourceAsStream("version.properties"));
			
			model.put("rolsac_version", versionsProperties.getProperty("rolsac.version"));
			model.put("rolsac_einaversion",  " v" + versionsProperties.getProperty("rolsac.version") + " build: " + versionsProperties.getProperty("rolsac.build"));
	    	model.put("rolsac_einarevision","("+ versionsProperties.getProperty("git.revision") +")");
	    	model.put("rolsac_urlrevision", versionsProperties.getProperty("rolsac.urlrevision").replace("{0}",versionsProperties.getProperty("git.revision")));
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		request.setAttribute("traductorActivo", traductorActivo);
	    
	}

	
	/**
	 * Devuelve la UnidadAdministrativa actual.
	 * 
	 * @param session Sesión.
	 * @return UnidadAdministrativa en uso.
	 */
	protected UnidadAdministrativa getUAFromSession(HttpSession session) {
		return (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
	}	
	
    /**
     * Método encargado de realizar el casting de listas no tipadas a listas
     * tipadas
     * 
     * @param <T>
     * @param clazz Clase del tipo de objeto contenido en la lista
     * @param c Colección a ser tipada
     * @return Lista tipada
     */
    public <T>List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>();
        if (c != null) {     
            r = new ArrayList<T>(c.size());            
            for (Object o : c) {
            	r.add(clazz.cast(o));
            }
        }
        return r;
    }    	
	    
}