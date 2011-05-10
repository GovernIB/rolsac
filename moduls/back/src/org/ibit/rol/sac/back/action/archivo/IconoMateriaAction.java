package org.ibit.rol.sac.back.action.archivo;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IconoMateriaDelegate;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 23-ago-2004
 * Time: 14:09:00
 */
/**
 * @struts.action
 *  path="/icono/materia"
 *  scope="request"
 *  validate="false"
 */
public class IconoMateriaAction extends ArchivoAction {
     public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate

        Long idIconoMateria = new Long(request.getParameter("idIcoMat"));
        IconoMateriaDelegate icoFamDelegate = DelegateUtil.getIconoMateriaDelegate();

        return icoFamDelegate.obtenerIcono(idIconoMateria);

    }
}
