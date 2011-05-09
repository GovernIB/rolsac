package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;

/**
 * @struts.action
 *  path="/espacio/logo"
 *  scope="request"
 *  validate="false"
 *  (PORMAD)
 */
public class EspacioTerritorialLogoAction extends ArchivoAction {

    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long id = new Long(request.getParameter("idEspacio"));
        EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();

        return espacioDelegate.obtenerLogoEspacio(id);
    }
}
