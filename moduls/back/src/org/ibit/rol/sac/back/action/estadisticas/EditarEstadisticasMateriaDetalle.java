package org.ibit.rol.sac.back.action.estadisticas;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.Materia;
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
 *  path="/estadisticasmateria/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".estadisticasmateria.visualizar"
 */
public class EditarEstadisticasMateriaDetalle extends Action {
    protected static Log log = LogFactory.getLog(EditarEstadisticasMateriaDetalle.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("Entramos en execute");

        Long id = new Long(request.getParameter("idSelect"));

        MateriaDelegate matDelegate = DelegateUtil.getMateriaDelegate();
        Materia mat = matDelegate.obtenerMateriaFichasProced(id);
        
        request.setAttribute("listaFichas", mat.getFichas());
        request.setAttribute("listaProcedimientos", mat.getProcedimientosLocales());

        request.setAttribute("idMateria", id);

        return mapping.findForward("success");
    }
}
