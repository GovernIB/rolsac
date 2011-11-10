package es.caib.rolsac.back2.util;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;


public class UAServletFilter implements Filter {
	
	private static Log log = LogFactory.getLog(UAServletFilter.class);
	
	private FilterConfig _filterConfig = null;

	
	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;
	}

	public void destroy() {
		_filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			try {
				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session = req.getSession();
				
				if (session.getAttribute("unidadAdministrativa") == null) {
					// Si se ha pulsado en "inici" en la molla de pa no se ha de seleccionar ninguna UA.
					Boolean forzarUA;
					if (session.getAttribute("forzarUA") != null) {
						forzarUA = (Boolean) session.getAttribute("forzarUA");
					} else {
						forzarUA = Boolean.TRUE;
					}
					
					if (forzarUA) {
						UsuarioDelegate uDelegate = DelegateUtil.getUsuarioDelegate();
						Usuario usuario = uDelegate.obtenerUsuariobyUsername(req.getRemoteUser());
		
						Set<UnidadAdministrativa> uas = usuario.getUnidadesAdministrativas();
						if (uas.size() > 0) {
							session.setAttribute("unidadAdministrativa", uas.iterator().next());
						}
					}
				}
	        } catch(DelegateException de) {
	        	log.error("Error al cercar UA de l'usuari - " + de);
	        }
		}
		chain.doFilter(request, response);
	}
}
