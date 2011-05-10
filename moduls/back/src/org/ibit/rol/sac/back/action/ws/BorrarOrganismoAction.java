package org.ibit.rol.sac.back.action.ws;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.ibit.rol.sac.back.action.BaseAction;
import org.ibit.rol.sac.back.utils.UDDIUtil;
import org.ibit.rol.sac.integracion.uddi.UDDIManager;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para borrar una especificación.
 *
 * @struts.action
 *  name="organismoForm"
 *  scope="request"
 *  validate="false"
 *  path="/ws/organismo/borrar"
 *
 * @struts.action-forward
 *  name="success" path=".ws.organismo.lista"
 */
public class BorrarOrganismoAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        DynaActionForm dForm = (DynaActionForm) form;
        String clave = (String) dForm.get("clave");

        // Borrar el BusinessKey en la base de datos local.
        DelegateUtil.getUADelegate().borrarBusinessKey(clave);

        UDDIManager manager = UDDIUtil.getManager(request);
        manager.borrarOrganismo(clave);

        addMessage(request, "confirmacion.baja");

        return mapping.findForward("success");
    }
}
