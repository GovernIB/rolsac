package org.ibit.rol.sac.back.action.ws;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.ibit.rol.sac.back.action.BaseAction;
import org.ibit.rol.sac.back.utils.UDDIUtil;
import org.ibit.rol.sac.integracion.uddi.UDDIManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para borrar una especificación.
 *
 * @struts.action
 *  name="especificacionForm"
 *  scope="request"
 *  validate="false"
 *  path="/ws/especificacion/borrar"
 *
 * @struts.action-forward
 *  name="success" path=".ws.especificacion.lista"
 */
public class BorrarEspecificacionAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        DynaActionForm dForm = (DynaActionForm) form;
        String clave = (String) dForm.get("clave");

        UDDIManager manager = UDDIUtil.getManager(request);
        manager.borrarEspecificacion(clave);

        addMessage(request, "confirmacion.baja");

        return mapping.findForward("success");
    }
}
