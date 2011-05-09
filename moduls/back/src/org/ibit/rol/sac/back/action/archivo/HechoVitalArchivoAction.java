package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;

/**
 * @struts.action
 *  path="/hechovital/distribComp"
 *  scope="request"
 *  validate="false"
 *
 * Action para obtener la distribución Competencial de un Hecho Vital(PORMAD)
 */
public class HechoVitalArchivoAction extends ArchivoAction {
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idNorm = new Long(request.getParameter("idHechoVital"));
        String lang = request.getParameter("lang");
        HechoVitalDelegate hecDelegate = DelegateUtil.getHechoVitalDelegate();

        return hecDelegate.obtenerDistribComp(idNorm, lang, false);
    }

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return true;
    }
}
