package org.ibit.rol.sac.back.action.archivo;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.AgrupacionHVDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @struts.action
 *  path="/agrupacion/icono/grande"
 *  scope="request"
 *  validate="false"
 *  Action per obtenir l'icone gran d'una agrupació(PORMAD)
 */
public class AgrupacionIconoGrandeAction extends ArchivoAction{
     public Archivo obtenerArchivo(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request) throws Exception {

           //obtener archivo concreto con el delegate
           Long idAgrup = new Long(request.getParameter("idAgrup"));
           AgrupacionHVDelegate agrupacionHVDelegate = DelegateUtil.getAgrupacionHVDelegate();

           return agrupacionHVDelegate.obtenerIconoGrande(idAgrup);
       }
}
