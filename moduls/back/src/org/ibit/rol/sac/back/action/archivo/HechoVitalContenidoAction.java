package org.ibit.rol.sac.back.action.archivo;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @struts.action
 *  path="/hechovital/contenido"
 *  scope="request"
 *  validate="false"
 * (PORMAD)
 */
public class HechoVitalContenidoAction extends ArchivoAction{
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idNorm = new Long(request.getParameter("idHechoVital"));
        String lang = request.getParameter("lang");
        HechoVitalDelegate hecDelegate = DelegateUtil.getHechoVitalDelegate();

        return hecDelegate.obtenerContenido(idNorm, lang, false);
    }

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return true;
    }


}
