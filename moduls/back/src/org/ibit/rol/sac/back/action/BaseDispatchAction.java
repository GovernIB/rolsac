package org.ibit.rol.sac.back.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.RequestUtils;
import org.ibit.rol.sac.back.action.contenido.common.ActionParameters;
import org.ibit.rol.sac.model.Archivo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Extensio per afegir tractament de cancel.
 */
public abstract class BaseDispatchAction extends LookupDispatchAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return cancelled(mapping, form, request, response);
        } else {
            HttpServletRequestWrapper newRequest = new HttpServletRequestWrapper(request) {
                public String getParameter(String param) {
                    if ("action".equals(param)) {
                        String action = (String) this.getRequest().getAttribute("action");
                        if (action != null) {
                            return action;
                        }
                    }

                    return getRequest().getParameter(param);
                }
            };

            return super.execute(mapping, form, newRequest, response);
        }
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        return null;
    }

    protected static Archivo populateArchivo(Archivo archivo,
                                             FormFile formFile) throws IOException {

        if (archivo == null) archivo = new Archivo();
        archivo.setMime(formFile.getContentType());
        archivo.setNombre(formFile.getFileName());
        archivo.setPeso(formFile.getFileSize());
        archivo.setDatos(formFile.getFileData());
        return archivo;
    }

    protected boolean archivoValido(FormFile formFile) {
        return (formFile != null && formFile.getFileSize() > 0);
    }

    protected ActionForm obtenerActionForm(ActionMapping mapping, HttpServletRequest request, String path) {
        ModuleConfig config = mapping.getModuleConfig();
        ActionMapping newMapping = (ActionMapping) config.findActionConfig(path);
        ActionForm newForm = RequestUtils.createActionForm(request, newMapping, config, this.servlet);
        if ("session".equals(newMapping.getScope())) {
            request.getSession(true).setAttribute(newMapping.getAttribute(), newForm);
        } else {
            request.setAttribute(newMapping.getAttribute(), newForm);
        }
        newForm.reset(newMapping, request);
        return newForm;
    }

    protected boolean acceso(String role, HttpServletRequest request){
        if (!request.isUserInRole(role)){
            request.setAttribute("alert", "acceso.denegado");
            return false;
        }

        return true;
    }

    protected boolean hasRole(HttpServletRequest request, String role) {
        List roles = new ArrayList(4);
        roles.add("sacsystem");
        roles.add("sacadmin");
        roles.add("sacsuper");
        roles.add("sacoper");

        int i = roles.indexOf(role);
        for (int j = 0; j < i; j++) {
            if (request.isUserInRole((String)roles.get(j))) {
                return true;
            }
        }
        return request.isUserInRole((String)roles.get(i));
    }


    public ActionForward dispatchMethod(ActionParameters actionParams, String dispatchTo) throws Exception {
		return dispatchMethod(actionParams.mapping, actionParams.form, actionParams.request, actionParams.response, dispatchTo);
	}
}
