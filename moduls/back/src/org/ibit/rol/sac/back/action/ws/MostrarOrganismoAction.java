package org.ibit.rol.sac.back.action.ws;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.ibit.rol.sac.back.action.BaseAction;
import org.ibit.rol.sac.back.utils.UDDIUtil;
import org.ibit.rol.sac.integracion.uddi.Organismo;
import org.ibit.rol.sac.integracion.uddi.UDDIManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para mostrar un organismo.
 *
 * @struts.action
 *  name="organismoForm"
 *  scope="request"
 *  validate="false"
 *  path="/ws/organismo/mostrar"
 *
 * @struts.action-forward
 *  name="success" path=".ws.organismo.editar"
 */
public class MostrarOrganismoAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        DynaActionForm dForm = (DynaActionForm) form;
        String clave = (String) dForm.get("clave");

        UDDIManager manager = UDDIUtil.getManager(request);

        Organismo organismo = manager.obtenerOrganismo(clave);
        BeanUtils.populate(dForm, BeanUtils.describe(organismo));

        return mapping.findForward("success");
    }
}
