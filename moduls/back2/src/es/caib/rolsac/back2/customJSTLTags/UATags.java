package es.caib.rolsac.back2.customJSTLTags;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.context.MessageSource;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class UATags extends TagSupport {

	private static Log log = LogFactory.getLog(UATags.class);
	
	private static final long serialVersionUID = -3991191110836413697L;

	@Override
	public int doStartTag() {
		crearMollapa();
		return SKIP_BODY;
	}
	
	/** Breadcrumbs de unidades administrativas.*/ 
 	private void crearMollapa() {
		try {
			Locale locale = pageContext.getRequest().getLocale();
			MessageSource messages = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
			
			String mollapa = "";
			String textoBotonCargarHijos;
			Object reqUA = pageContext.getSession().getAttribute("unidadAdministrativa");
			if (reqUA != null) {
				UnidadAdministrativa ua = (UnidadAdministrativa) reqUA;
				HttpServletRequest httpRequest=(HttpServletRequest)pageContext.getRequest();
				
				StringBuilder url = new StringBuilder("../unidadAdministrativa/cambiarUA.do?ua=");
				String UA_ID_PLACEHOLDER = "__UAID__";
				url.append(UA_ID_PLACEHOLDER);
				url.append("&redirectTo=");
				url.append(httpRequest.getRequestURL());
				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
				mollapa = uaDelegate.getUaMollaBack2(ua.getId(), locale.getLanguage(), url.toString(), UA_ID_PLACEHOLDER).toString();
				
				textoBotonCargarHijos = messages.getMessage("mollapa.unitats.filles.label", null, locale);
			} else {
				textoBotonCargarHijos = messages.getMessage("mollapa.unitats.arrel.label", null, locale); 
			}
			mollapa += "<li class=\"uaHijas\"><a href=\"javascript:;\" class=\"btn uaFilles\"><span><span>" + textoBotonCargarHijos + "</span></span></a></li>";
			
            pageContext.getOut().print(mollapa);
        } catch(IOException ioe) {
        	log.error(ioe);
        } catch(DelegateException de) {
        	log.error(de);
        }
    }
}
