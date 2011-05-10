package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;

/**
 * @struts.action
 *  path="/edificio/foto/grande"
 *  scope="request"
 *  validate="false"
 *
 */
public class EdificioFotoGrandeAction extends ArchivoAction {
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idEdificio = new Long(request.getParameter("idEdificio"));
        EdificioDelegate ediDelegate = DelegateUtil.getEdificioDelegate();

        return ediDelegate.obtenerFotoGrandeEdificio(idEdificio);
    }
}
