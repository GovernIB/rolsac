package org.ibit.rol.sac.back.action.auditoria;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
/**
 * Action para mostrar las auditorias de un Historico.
 *
 * @struts.action
 *  name="auditoriaForm"
 *  scope="request"
 *  validate="false"
 *  path="/auditoria/historico"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="lista" path=".auditoria.detalle"
 *
 */
public class AuditoriaHistoricoAction extends BaseDispatchAction {
    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.seleccionar", "seleccionar");
        return map;
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();


        if(request.getParameter("idSelect")!=null){

            Long idHist = new Long(request.getParameter("idSelect"));
            List auditorias = auditoriaDelegate.listarAuditoriasHistorico(idHist);
            request.setAttribute("listaAuditorias", auditorias);
        }

        return mapping.findForward("lista");
     }

}
