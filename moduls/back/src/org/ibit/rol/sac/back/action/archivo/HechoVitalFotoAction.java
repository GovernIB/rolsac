package org.ibit.rol.sac.back.action.archivo;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @struts.action
 *  path="/hechovital/foto"
 *  scope="request"
 *  validate="false"
 *  Obtiene la foto de un hecho vital (PORMAD)
 */
public class HechoVitalFotoAction extends ArchivoAction{
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idHecho = new Long(request.getParameter("idHecho"));
        HechoVitalDelegate hechoDelegate = DelegateUtil.getHechoVitalDelegate();

        return hechoDelegate.obtenerFoto(idHecho);
    }
}
