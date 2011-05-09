package org.ibit.rol.sac.back.action.archivo;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @struts.action
 *  path="/materia/normativa"
 *  scope="request"
 *  validate="false"
 *  (PORMAD)
 */
public class MateriaNormativaAction extends ArchivoAction{
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idMat = new Long(request.getParameter("idMateria"));
        String lang = request.getParameter("lang");
        MateriaDelegate matDelegate = DelegateUtil.getMateriaDelegate();

        return matDelegate.obtenerNormativa(idMat, lang, false);
    }

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return true;
    }

}
