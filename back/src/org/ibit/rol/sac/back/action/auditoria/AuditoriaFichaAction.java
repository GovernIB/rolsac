package org.ibit.rol.sac.back.action.auditoria;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.model.Ficha;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
/**
 * Action para mostrar las auditorias de una Ficha.
 *
 * @struts.action
 *  name="auditoriaForm"
 *  scope="request"
 *  validate="false"
 *  path="/auditoria/ficha"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="lista" path=".auditoria.detalle"
 *
 */
public class AuditoriaFichaAction extends BaseDispatchAction{
    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.seleccionar", "seleccionar");
        return map;
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
        if(request.getParameter("idFicha")!=null){
            Long idFicha = new Long(request.getParameter("idFicha"));
            Ficha ficha = fichaDelegate.obtenerFicha(idFicha);
            List auditorias = auditoriaDelegate.listarAuditoriasFicha(idFicha);

            request.setAttribute("listaAuditorias", auditorias);
            request.setAttribute("ficha", ficha);
        }

        return mapping.findForward("lista");
     }
}
