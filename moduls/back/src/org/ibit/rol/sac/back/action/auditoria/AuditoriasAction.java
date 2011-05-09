package org.ibit.rol.sac.back.action.auditoria;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.AuditoriaForm;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Action para mostrar una lista de elementos con auditorias entre dos fechas.
 *
 * @struts.action
 *  name="auditoriaForm"
 *  scope="request"
 *  validate="false"
 *  path="/auditoria/buscar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="lista" path=".auditoria.lista"
 *
 */
public class AuditoriasAction extends BaseDispatchAction{

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.busqueda", "busqueda");
        return map;
    }
    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.info("Entramos en busqueda");
        AuditoriaForm dForm = (AuditoriaForm)form;
        AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();

        List historicos =auditoriaDelegate.listarHistoricosAuditorias(dForm.getFechaIni(),dForm.getFechaFin());
        request.setAttribute("listaHistoricos",historicos);
        return mapping.findForward("lista");
    }
}
