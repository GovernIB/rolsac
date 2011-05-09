package org.ibit.rol.sac.back.action.auditoria;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;
/**
 * Action para mostrar las auditorias de una Unidad Administrativa.
 *
 * @struts.action
 *  scope="request"
 *  validate="false"
 *  path="/auditoria/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="lista" path=".auditoria.visualizar"
 *
 */
public class AuditoriasUAAction extends Action{


    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
        AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
        ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
        NormativaDelegate normDeletate = DelegateUtil.getNormativaDelegate();
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();


        if(request.getParameter("idSelect")!=null){

            Long idUnidad = new Long(request.getParameter("idSelect"));
            UnidadAdministrativa unidad = uaDelegate.obtenerUnidadAdministrativa(idUnidad);

            List procedimientos = procDelegate.listarProcedimientosUA(idUnidad);
            Collection normativas = normDeletate.listarNormativasUA(idUnidad);
            Collection fichas = fichaDelegate.listarFichasUnidad(idUnidad);
            List auditoriasUA = auditoriaDelegate.listarAuditoriasUnidadAdministrativa(idUnidad);

            request.setAttribute("listaAuditorias", auditoriasUA);
            request.setAttribute("listaProcedimientos",procedimientos);
            request.setAttribute("listaNormativas",normativas);
            request.setAttribute("listaFichas",fichas);
            request.setAttribute("unidad",unidad);
        }

        return mapping.findForward("lista");
     }
}
