package test.unitario.back.mock;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.Globals;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.springframework.web.struts.DelegatingTilesRequestProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

/**
 * RequestProcessor amb adaptacions per només validar determinats requests.
 */
public class MockCustomRequestProcessor extends DelegatingTilesRequestProcessor {

    protected static Log log = LogFactory.getLog(MockCustomRequestProcessor.class);

    public void process(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("ISO-8859-15");
        }
        super.process(request, response);
    }

    protected boolean processValidate(HttpServletRequest request,
                                      HttpServletResponse response,
                                      ActionForm form,
                                      ActionMapping mapping)
            throws IOException, ServletException {

    	return true;
    	/*
        try {
            Class mappingClass = RequestUtils.applicationClass(mapping.getType());
            if (BaseDispatchAction.class.isAssignableFrom(mappingClass)) {
                String action = request.getParameter("action");
                if (action != null) {
                    log.debug("action=" + action);
                    MessageResources resources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
                    String insertar = resources.getMessage("boton.insertar");
                    String modificar = resources.getMessage("boton.modificar");
                    if (action.equals(insertar) || action.equals(modificar)) {
                        log.debug("processValidate");
                        return super.processValidate(request, response, form, mapping);
                    } else {
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            // ¿Es posible que se llegue aqui?
        }

        return super.processValidate(request, response, form, mapping);
        */
    }
}
