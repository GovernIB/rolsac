package org.ibit.rol.sac.back.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.*;
import org.apache.struts.config.ExceptionConfig;
import org.ibit.rol.sac.persistence.delegate.DelegateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler para excepciones del Delegate.
 */
public class DelegateExceptionHandler extends ExceptionHandler {

    protected static Log log = LogFactory.getLog(DelegateExceptionHandler.class);

    public ActionForward execute(Exception e, ExceptionConfig config,
                                 ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        log.debug("Excepci�n en la petici�n: " + mapping.getPath());

        ActionForward forward;
        ActionError error;
        String property;

        // Build the forward from the exception mapping if it exists
        // or from the form input
        if (config.getPath() != null) {
            forward = new ActionForward(config.getPath());
        } else {
            forward = mapping.getInputForward();
        }

        // Aquest handler solo trata con DelegateException
        DelegateException de = (DelegateException) e;

        // Figure out the error
        if (de.isSecurityException()) {
            error = new ActionError("errors.security", de.getSecurityException().getMessage());
        } else {
            error = new ActionError(config.getKey(), de.getMessage());
        }
        property = error.getKey();

        // Store the exception
        request.setAttribute(Globals.EXCEPTION_KEY, de);
        super.storeException(request, property, error, forward, config.getScope());

        return forward;
    }

}
