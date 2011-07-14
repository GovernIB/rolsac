package org.ibit.rol.sac.back.action.estadisticas;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Action para editar las Estadisticas.
 *
 * @struts.action
 *  scope="request"
 *  validate="false"
 *  path="/estadisticas/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".estadisticas.visualizar"
 */
public class EditarEstadisticasAction extends Action{
    protected static Log log = LogFactory.getLog(EditarEstadisticasAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.debug("Entramos en execute");

        Long id = new Long(request.getParameter("idSelect"));

        ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
        NormativaDelegate normDeletate = DelegateUtil.getNormativaDelegate();
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
        MateriaDelegate matDelegate = DelegateUtil.getMateriaDelegate();

        Collection procedimientos = procDelegate.listarProcedimientosUA(id);
        Collection normativas = normDeletate.listarNormativasUA(id);
        Collection fichas = fichaDelegate.listarFichasUnidad(id);
        Collection materias = matDelegate.listarMateriasbyUA(id);
        
        request.setAttribute("listaProcedimientos",procedimientos);
        request.setAttribute("listaNormativas",normativas);
        request.setAttribute("listaFichas",fichas);
        request.setAttribute("listaMats",materias);

        request.setAttribute("idUAdmin",id);

        return mapping.findForward("success");
    }
}
