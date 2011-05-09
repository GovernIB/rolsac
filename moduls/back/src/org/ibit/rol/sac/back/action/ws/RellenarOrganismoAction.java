package org.ibit.rol.sac.back.action.ws;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.ibit.rol.sac.back.action.BaseAction;
import org.ibit.rol.sac.back.utils.UDDIUtil;
import org.ibit.rol.sac.integracion.uddi.Especificacion;
import org.ibit.rol.sac.integracion.uddi.UDDIManager;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para prerellenar un organismo.
 *
 * @struts.action
 *  name="organismoForm"
 *  scope="request"
 *  validate="false"
 *  path="/ws/organismo/rellenar"
 *
 * @struts.action-forward
 *  name="success" path=".ws.organismo.editar"
 * @struts.action-forward
 *  name="cancel" path=".ws.organismo.lista"
 * @struts.action-forward
 *  name="fail" path=".ws.organismo.nuevo"
 */
public class RellenarOrganismoAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward("cancel");
        }

        DynaActionForm dForm = (DynaActionForm) form;
        Long idUnidad = (Long) dForm.get("idUnidad");

        if (idUnidad == null) {
            ActionErrors errors = new ActionErrors();
            errors.add("idUnidad", new ActionError("organismo.error.unidad"));
            saveErrors(request, errors);
            return mapping.findForward("fail");
        }

        UnidadAdministrativa unidad = DelegateUtil.getUADelegate().obtenerUnidadAdministrativa(idUnidad);
        dForm.set("nombre", ((TraduccionUA) unidad.getTraduccion()).getNombre());
        dForm.set("descripcion", ((TraduccionUA) unidad.getTraduccion()).getPresentacion());
        dForm.set("responsable", unidad.getResponsable());
        dForm.set("telefono", unidad.getTelefono());
        dForm.set("email", unidad.getEmail());

        return mapping.findForward("success");
    }
}
