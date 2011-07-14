package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IconoFamiliaDelegate;

/**
 * @struts.action
 *  path="/familia/icono"
 *  scope="request"
 *  validate="false"
 *
 */
public class FamiliaIconoAction extends ArchivoAction {
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        Long idFam = new Long(request.getParameter("idFam"));
        Long idPerfil = new Long(request.getParameter("idPerfil"));
        log.debug("idFam" + idFam);

        IconoFamiliaDelegate icoFamDelegate = DelegateUtil.getIconoFamiliaDelegate();
        IconoFamilia iconoFamilia = icoFamDelegate.obtenerIconoFamilia(idPerfil, idFam);

        if (iconoFamilia == null) {
            return null;
        }

        return icoFamDelegate.obtenerIcono(iconoFamilia.getId());
    }
}
