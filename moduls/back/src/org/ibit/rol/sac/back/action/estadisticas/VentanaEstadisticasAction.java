package org.ibit.rol.sac.back.action.estadisticas;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para mostrar ventana con estadisticas.
 *
 * @struts.action
 *  scope="request"
 *  validate="false"
 *  path="/estadisticas/popventana"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path="/estadisticas/popEstadisticas.jsp"
 *
 */
public class VentanaEstadisticasAction extends Action{
    protected static Log log = LogFactory.getLog(VentanaEstadisticasAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("Entramos en execute");

        String idProcedimiento = request.getParameter("idProcedimiento");
        String idNormativa = request.getParameter("idNormativa");
        String idFicha = request.getParameter("idFicha");
        String idMateria = request.getParameter("idMateria");

        if (idProcedimiento!=null){
            Long iProcedimiento = new Long(idProcedimiento);
            ProcedimientoDelegate pDelegate = DelegateUtil.getProcedimientoDelegate();
            ProcedimientoLocal procedimiento = pDelegate.obtenerProcedimiento(iProcedimiento);
            String titulo = ((TraduccionProcedimiento)procedimiento.getTraduccion()).getNombre();

            request.setAttribute("idProcedimiento", iProcedimiento);
            request.setAttribute("titulo", titulo);
        } else if (idNormativa!=null){
            Long iNormativa = new Long(idNormativa);
            NormativaDelegate nDelegate = DelegateUtil.getNormativaDelegate();
            Normativa normativa = nDelegate.obtenerNormativa(iNormativa);
            String titulo = ((TraduccionNormativa)normativa.getTraduccion()).getTitulo();

            request.setAttribute("idNormativa", iNormativa);
            request.setAttribute("titulo", titulo);
        } else if (idFicha!=null){
            Long iFicha = new Long(idFicha);
            FichaDelegate fDelegate = DelegateUtil.getFichaDelegate();
            Ficha ficha = fDelegate.obtenerFicha(iFicha);
            String titulo = ((TraduccionFicha)ficha.getTraduccion()).getTitulo();

            request.setAttribute("idFicha", iFicha);
            
            // Para grafico de la ficha segun la materia
            if (request.getParameter("idFicMat")!=null) {
            	request.setAttribute("idFicMat", request.getParameter("idFicMat"));
            }

            // Para grafico de la ficha segun la UA
            if (request.getParameter("idFicUA")!=null) {
            	request.setAttribute("idFicUA", request.getParameter("idFicUA"));
            }

            
            request.setAttribute("titulo", titulo);
	    } else if (idMateria!=null){
	        Long iMateria = new Long(idMateria);
	        MateriaDelegate mDelegate = DelegateUtil.getMateriaDelegate();
	        Materia mat = mDelegate.obtenerMateria(iMateria);
	        String titulo = ((TraduccionMateria)mat.getTraduccion()).getNombre() +"<br/>" + ((TraduccionMateria)mat.getTraduccion()).getDescripcion();
	
	        request.setAttribute("idMateria", iMateria);
	        request.setAttribute("titulo", titulo);
	    }

        return mapping.findForward("success");
    }
}
