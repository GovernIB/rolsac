package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

/**
 * @struts.action
 *  path="/materia/distribComp"
 *  scope="request"
 *  validate="false"
 *
 */
public class MateriaArchivoAction extends ArchivoAction {
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idMat = new Long(request.getParameter("idMateria"));
        String lang = request.getParameter("lang");
        MateriaDelegate matDelegate = DelegateUtil.getMateriaDelegate();

        return matDelegate.obtenerDistribComp(idMat, lang, false);
    }

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return true;
    }
}
