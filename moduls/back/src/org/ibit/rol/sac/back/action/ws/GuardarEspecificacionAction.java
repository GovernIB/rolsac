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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para crear una especificación.
 *
 * @struts.action
 *  name="especificacionForm"
 *  scope="request"
 *  validate="true"
 *  path="/ws/especificacion/guardar"
 *  input=".ws.especificacion.editar"
 *
 * @struts.action-forward
 *  name="success" path=".ws.especificacion.lista"
 * @struts.action-forward
 *  name="cancel" path=".ws.especificacion.lista"
 */
public class GuardarEspecificacionAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward("cancel");
        }

        Especificacion especificacion = new Especificacion();
        DynaActionForm dForm = (DynaActionForm) form;
        BeanUtils.populate(especificacion, dForm.getMap());

        UDDIManager manager = UDDIUtil.getManager(request);
        manager.publicarEspecificacion(especificacion);

        String clave = (String) dForm.get("clave");
        if (clave != null && clave.length() > 0) {
            addMessage(request, "confirmacion.modificacion");
        } else {
            addMessage(request, "confirmacion.alta");
        }

        return mapping.findForward("success");
    }
}
