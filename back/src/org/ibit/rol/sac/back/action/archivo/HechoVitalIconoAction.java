package org.ibit.rol.sac.back.action.archivo;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;

import javax.servlet.http.HttpServletRequest;

/**
 * @struts.action
 *  path="/hechovital/icono"
 *  scope="request"
 *  validate="false"
 *  (PORMAD)
 */
public class HechoVitalIconoAction extends ArchivoAction {

    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idHecho = new Long(request.getParameter("idHecho"));
        HechoVitalDelegate hechoDelegate = DelegateUtil.getHechoVitalDelegate();

        return hechoDelegate.obtenerIcono(idHecho);
    }
}
