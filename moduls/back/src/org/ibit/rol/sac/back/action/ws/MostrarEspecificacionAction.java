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
 * Action para mostrar una especificación.
 *
 * @struts.action
 *  name="especificacionForm"
 *  scope="request"
 *  validate="false"
 *  path="/ws/especificacion/mostrar"
 *
 * @struts.action-forward
 *  name="success" path=".ws.especificacion.editar"
 */
public class MostrarEspecificacionAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        DynaActionForm dForm = (DynaActionForm) form;
        String clave = (String) dForm.get("clave");

        UDDIManager manager = UDDIUtil.getManager(request);

        Especificacion especificacion = manager.obtenerEspecificacion(clave);
        BeanUtils.populate(dForm, BeanUtils.describe(especificacion));

        return mapping.findForward("success");
    }
}
