package es.caib.rolsac.back2.util;

import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

public class RolsacSessionListener implements HttpSessionListener {

	private static Log log = LogFactory.getLog(UAServletFilter.class);
	
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		try {			
				List<String> listaIdiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();				
				sessionEvent.getSession().setAttribute("idiomes_aplicacio", listaIdiomas);
				
				StringBuilder sb = new StringBuilder();
				String sep = "";
				
				for (String s: listaIdiomas) {
					sb.append(sep).append(s.trim() );
					sep = ",";
				}
				
				sessionEvent.getSession().setAttribute("idiomes_aplicacio_string",  sb.toString() );				
				
		} catch (DelegateException e) {
			log.error("Error al cercar els idiomes de la base de dades - " + e);
		}		
	}

	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		log.debug("Sessió finalitzada: ID - " + sessionEvent.getSession().getId() );	
	}
}
