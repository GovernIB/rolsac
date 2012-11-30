package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

		String nomLlinatges = (String) session.getAttribute("capNomLlinatges");
		if (nomLlinatges == null) {
	    	String username = request.getRemoteUser();
	    	nomLlinatges = username; 
		    try {

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