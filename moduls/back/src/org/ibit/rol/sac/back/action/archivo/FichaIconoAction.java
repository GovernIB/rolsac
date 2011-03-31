package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;

/**
 * @struts.action
 *  path="/ficha/icono"
 *  scope="request"
 *  validate="false"
 */
public class FichaIconoAction extends ArchivoAction {
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idFicha = new Long(request.getParameter("idFicha"));
        FichaDelegate delegate = DelegateUtil.getFichaDelegate();

        return delegate.obtenerIconoFicha(idFicha);
    }
}
