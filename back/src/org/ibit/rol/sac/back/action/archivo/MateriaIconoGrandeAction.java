package org.ibit.rol.sac.back.action.archivo;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @struts.action
 *  path="/materia/icono/grande"
 *  scope="request"
 *  validate="false"
 *  Action per obtenir l'icone gran d'una materia (PORMAD)
 */
public class MateriaIconoGrandeAction extends ArchivoAction {

     public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        Long idMateria = new Long(request.getParameter("idMateria"));
        MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();

        return materiaDelegate.obtenerIconoGrande(idMateria);
    }

}
