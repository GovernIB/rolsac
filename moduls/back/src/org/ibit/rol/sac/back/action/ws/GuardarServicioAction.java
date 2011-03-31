package org.ibit.rol.sac.back.action.ws;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.ibit.rol.sac.back.action.BaseAction;
import org.ibit.rol.sac.back.utils.UDDIUtil;
import org.ibit.rol.sac.integracion.uddi.ServicioWeb;
import org.ibit.rol.sac.integracion.uddi.UDDIManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para crear un servicio web.
 *
 * @struts.action
 *  name="servicioForm"
 *  scope="request"
 *  validate="true"
 *  path="/ws/servicio/guardar"
 *  input=".ws.servicio.editar"
 *
 * @struts.action-forward
 *  name="success" path=".ws.servicio.lista"
 * @struts.action-forward
 *  name="cancel" path=".ws.servicio.lista"
 */
public class GuardarServicioAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward("cancel");
        }

        ServicioWeb servicio = new ServicioWeb();
        DynaActionForm dForm = (DynaActionForm) form;
        BeanUtils.populate(servicio, dForm.getMap());

        UDDIManager manager = UDDIUtil.getManager(request);
        manager.publicarServicio(servicio);

        return mapping.findForward("success");
    }
}
