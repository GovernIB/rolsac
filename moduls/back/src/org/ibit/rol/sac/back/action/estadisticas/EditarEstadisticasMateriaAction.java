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

/** Action para editar las Estadisticas.
*
* @struts.action
*  scope="request"
*  validate="false"
*  path="/estadisticas/materias"
*  parameter="action"
*
* @struts.action-forward
*  name="success" path=".estadisticas.materias"
*/

public class EditarEstadisticasMateriaAction extends Action{
    protected static Log log = LogFactory.getLog(EditarEstadisticasMateriaAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        MateriaDelegate matDelegate = DelegateUtil.getMateriaDelegate();

        Collection materias = matDelegate.listarMaterias();
        
        request.setAttribute("listaMats",materias);

        return mapping.findForward("success");
        
    }
}
