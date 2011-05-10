package org.ibit.rol.sac.back.action.ws;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.ibit.rol.sac.back.action.BaseAction;
import org.ibit.rol.sac.back.utils.UDDIUtil;
import org.ibit.rol.sac.integracion.uddi.Especificacion;
import org.ibit.rol.sac.integracion.uddi.UDDIManager;
import org.ibit.rol.sac.integracion.uddi.Organismo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para guardar un organismo.
 *
 * @struts.action
 *  name="organismoForm"
 *  scope="request"
 *  validate="true"
 *  path="/ws/organismo/guardar"
 *  input=".ws.organismo.editar"
 *
 * @struts.action-forward
 *  name="success" path=".ws.organismo.lista"
 * @struts.action-forward
 *  name="cancel" path=".ws.organismo.lista"
 */
public class GuardarOrganismoAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward("cancel");
        }

        Organismo organismo = new Organismo();
        DynaActionForm dForm = (DynaActionForm) form;
        BeanUtils.populate(organismo, dForm.getMap());

        UDDIManager manager = UDDIUtil.getManager(request);
        manager.publicarOrganismo(organismo);

        String clave = (String) dForm.get("clave");
        if (clave != null && clave.length() > 0) {
            addMessage(request, "confirmacion.modificacion");
        } else {
            Long idUnidad = (Long) dForm.get("idUnidad");
            // Fijar el BusinessKey en la base de datos local.
            DelegateUtil.getUADelegate().fijarBusinessKey(idUnidad, organismo.getClave());
            addMessage(request, "confirmacion.alta");
        }

        return mapping.findForward("success");
    }
}
