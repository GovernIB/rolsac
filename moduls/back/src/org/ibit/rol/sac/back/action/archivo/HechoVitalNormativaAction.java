package org.ibit.rol.sac.back.action.archivo;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @struts.action
 *  path="/hechovital/normativa"
 *  scope="request"
 *  validate="false"
 *  (PORMAD)
 */
public class HechoVitalNormativaAction extends ArchivoAction{
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idNorm = new Long(request.getParameter("idHechoVital"));
        String lang = request.getParameter("lang");
        HechoVitalDelegate hecDelegate = DelegateUtil.getHechoVitalDelegate();

        return hecDelegate.obtenerNormativa(idNorm, lang, false);
    }

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return true;
    }

}
