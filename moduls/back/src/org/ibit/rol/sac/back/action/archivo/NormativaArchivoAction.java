package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;

/**
 * @struts.action
 *  path="/normativa/archivo"
 *  scope="request"
 *  validate="false"
 *
 */
public class NormativaArchivoAction extends ArchivoAction {
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idNorm = new Long(request.getParameter("idNormativa"));
        String lang = request.getParameter("lang");
        NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();

        return normDelegate.obtenerArchivoNormativa(idNorm, lang, false);
    }

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return true;
    }
}
