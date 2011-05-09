package org.ibit.rol.sac.back.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.apache.struts.Globals;
import org.ibit.rol.sac.model.Archivo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Action con métodos de utilidad.
 */
public abstract class BaseAction extends Action {

    protected void addMessage(HttpServletRequest request, String message) {
        addMessage(request, new ActionMessage(message));
    }

    protected void addMessage(HttpServletRequest request, String message, Object o1) {
        addMessage(request, new ActionMessage(message, o1));
    }

    protected void addMessage(HttpServletRequest request, String message, Object o1, Object o2) {
        addMessage(request, new ActionMessage(message, o1, o2));
    }

    protected void addMessage(HttpServletRequest request, String message, Object o1, Object o2, Object o3) {
        addMessage(request, new ActionMessage(message, o1, o2, o3));
    }

    protected void addMessage(HttpServletRequest request, String message, Object o1, Object o2, Object o3, Object o4) {
        addMessage(request, new ActionMessage(message, o1, o2, o3, o4));
    }

    protected void addMessage(HttpServletRequest request, String message, Object[] o) {
        addMessage(request, new ActionMessage(message, o));
    }

    protected void clearMessages(HttpServletRequest request) {
        saveMessages(request, new ActionMessages());
    }

    private void addMessage(HttpServletRequest request, ActionMessage message) {
        ActionMessages messages = (ActionMessages) request.getAttribute(Globals.MESSAGE_KEY);
        if (messages == null) {
            messages = new ActionMessages();
        }
        messages.add(ActionMessages.GLOBAL_MESSAGE, message);
        saveMessages(request, messages);
    }
}
