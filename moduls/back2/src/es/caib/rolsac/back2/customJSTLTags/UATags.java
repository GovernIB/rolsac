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
            MessageSource messages = WebApplicationContextUtils.getRequiredWebApplicationContext(
                    pageContext.getServletContext());

            StringBuffer mollapa = new StringBuffer();
            String textoBotonCargarHijos;
            Object reqUA = pageContext.getSession().getAttribute("unidadAdministrativa");

            UnidadAdministrativa ua = null;
            
            if (reqUA != null) {
            	
                ua = (UnidadAdministrativa)reqUA;

                HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest();

                StringBuilder url = new StringBuilder("../unidadAdministrativa/cambiarUA.do?ua=");
                String UA_ID_PLACEHOLDER = "__UAID__";
                url.append(UA_ID_PLACEHOLDER);
                url.append("&redirectTo=");
                url.append(httpRequest.getRequestURL());
                UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
                mollapa.append(uaDelegate.getUaMollaBack2(ua.getId(), DelegateUtil.getIdiomaDelegate().lenguajePorDefecto(), url.toString(), UA_ID_PLACEHOLDER));
                textoBotonCargarHijos = messages.getMessage("mollapa.unitats.filles.label", null, locale);
                                
            } else {
            	
                textoBotonCargarHijos = messages.getMessage("mollapa.unitats.arrel.label", null, locale);
                
            }

            // Crea el desplegable con las UAs hijas de la seleccionada.
            mollapa.append("<li class=\"uaHijas\" data-clave_ua_padre=\"")
                    .append(ua != null ? ua.getId() : "")
                    .append("\"><div><a class=\"btn uaFilles\" href=\"javascript:void(0);\">")
                    .append(textoBotonCargarHijos)
                    .append("</a></div></li>");

            pageContext.getOut().print(mollapa.toString());

        } catch (IOException ioe) {
        	
            log.error(ioe);
            
        } catch (DelegateException de) {
        	
            log.error(de);
            
        }
        
    }
    
}
