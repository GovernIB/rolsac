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
import es.caib.rolsac.back2.controller.ApplicationContextProvider;

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
            
            /*
            MessageSource messages = WebApplicationContextUtils.getRequiredWebApplicationContext(
                    pageContext.getServletContext());
            */
            MessageSource messages = ApplicationContextProvider.getApplicationContext();

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
                
                try {
					// Tratamiento especial para compatibilidad con JBOSS 5 que parece tener un bug y el getRequestURL() retorna el jsp y no el action (la url real).
                	// guia de los atributos http://timjansen.github.io/jarfiller/guide/servlet25/attributes.xhtml
                	// si todo va ok añadimos la url absoluta
	                StringBuffer rurl = httpRequest.getRequestURL();
	                String ruri = httpRequest.getRequestURI();
	                String urlCompleta = rurl.substring(0, rurl.indexOf(ruri)); 
	                urlCompleta +=  httpRequest.getAttribute("javax.servlet.forward.request_uri"); 
	                url.append(urlCompleta);
				} catch (Exception e) {
					//si ocurre un error añadimos la url relativa
					url.append(httpRequest.getAttribute("javax.servlet.forward.servlet_path"));
				}

                
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
