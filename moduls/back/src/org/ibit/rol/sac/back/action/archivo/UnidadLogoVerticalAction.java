package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

/**
 * @struts.action
 *  path="/unidad/foto/logov"
 *  scope="request"
 *  validate="false"
 *
 */
public class UnidadLogoVerticalAction extends ArchivoAction {

    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idUnidad = new Long(request.getParameter("idUnidad"));
        UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();

        return uaDelegate.obtenerLogoVerticalUA(idUnidad);
    }
}
