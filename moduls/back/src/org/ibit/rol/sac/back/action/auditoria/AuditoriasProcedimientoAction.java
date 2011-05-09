package org.ibit.rol.sac.back.action.auditoria;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;

/**
 * Action para mostrar las auditorias de un Procedimiento.
 *
 * @struts.action
 *  name="auditoriaForm"
 *  scope="request"
 *  validate="false"
 *  path="/auditoria/procedimiento"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="lista" path=".auditoria.detalle"
 *
 */
public class AuditoriasProcedimientoAction extends BaseDispatchAction{
    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.seleccionar", "seleccionar");
        return map;
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
        ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();

        if(request.getParameter("idProc")!=null){
            Long idProc = new Long(request.getParameter("idProc"));
            ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimiento(idProc);
            List auditorias = auditoriaDelegate.listarAuditoriasProcedimiento(idProc);

            request.setAttribute("listaAuditorias", auditorias);
            request.setAttribute("procedimiento",procedimiento);
        }

        return mapping.findForward("lista");
     }
}
