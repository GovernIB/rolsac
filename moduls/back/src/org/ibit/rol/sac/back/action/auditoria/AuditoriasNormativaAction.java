package org.ibit.rol.sac.back.action.auditoria;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.model.Normativa;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Action para mostrar las auditorias de una Normativa.
 *
 * @struts.action
 *  name="auditoriaForm"
 *  scope="request"
 *  validate="false"
 *  path="/auditoria/normativa"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="lista" path=".auditoria.detalle"
 *
 */
public class AuditoriasNormativaAction extends BaseDispatchAction{
    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.seleccionar", "seleccionar");
        return map;
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
        NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();

        if(request.getParameter("idNorm")!=null){
            Long idNorm = new Long(request.getParameter("idNorm"));
            Normativa normativa = normDelegate.obtenerNormativa(idNorm);
            List auditorias = auditoriaDelegate.listarAuditoriasNormativa(idNorm);

            request.setAttribute("listaAuditorias", auditorias);
            request.setAttribute("normativa",normativa);
        }

        return mapping.findForward("lista");
     }
}
