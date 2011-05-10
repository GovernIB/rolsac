package org.ibit.rol.sac.back.action.archivo;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IconoFamiliaDelegate;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 23-ago-2004
 * Time: 11:36:48
 */

/**
 * @struts.action
 *  path="/icono/familia"
 *  scope="request"
 *  validate="false"
 */
public class IconoFamiliaAction extends ArchivoAction {
     public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate

        Long idIconoFamilia = new Long(request.getParameter("idIcoFam"));
        IconoFamiliaDelegate icoFamDelegate = DelegateUtil.getIconoFamiliaDelegate();

        return icoFamDelegate.obtenerIcono(idIconoFamilia);

    }
}
