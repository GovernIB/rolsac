package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FormularioDelegate;

/**
 * @struts.action
 *  path="/formulario/archivo"
 *  scope="request"
 *  validate="false"
 */
public class FormularioArchivoAction extends ArchivoAction {
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idForm = new Long(request.getParameter("idFormulario"));
        FormularioDelegate formDelegate = DelegateUtil.getFormularioDelegate();

        return formDelegate.obtenerArchivoFormulario(idForm);
    }

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return true;
    }
}
