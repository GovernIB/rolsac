package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.AdministracionRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

/**
 * @struts.action
 *  path="/administracionRemota/logog"
 *  scope="request"
 *  validate="false"
 *
 * Action para mostrar el logo grande de una administración remota(PORMAD)
 */
public class AdministracionRemotaLogogAction extends ArchivoAction {

    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long id = new Long(request.getParameter("idAdmin"));
        AdministracionRemotaDelegate adminDelegate = DelegateUtil.getAdministracionRemotaDelegate();

        return adminDelegate.obtenerLogog(id);
    }
}
