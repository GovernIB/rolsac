package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.ibit.rol.sac.model.Archivo;

/**
 * Action Base para enviar un archivo.
 */
public abstract class ArchivoAction extends Action {

    protected static Log log = LogFactory.getLog(ArchivoAction.class);

    public final ActionForward execute(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Archivo archivo = obtenerArchivo(mapping, form, request);
        if (archivo != null) {
            response.reset();
            if (!forzarDownload(mapping, form, request)) {
                response.setContentType(archivo.getMime());
                response.setHeader("Content-Disposition", "inline; filename=\"" + archivo.getNombre() + "\"");
            } else {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + archivo.getNombre() + "\"");
            }
            response.setContentLength(archivo.getDatos().length);
            response.getOutputStream().write(archivo.getDatos());
        }

        return null;
    }

    public abstract Archivo obtenerArchivo(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request) throws Exception;

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return false;
    }
}
