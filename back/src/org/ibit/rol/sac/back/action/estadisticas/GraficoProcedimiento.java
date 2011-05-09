package org.ibit.rol.sac.back.action.estadisticas;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Periodo;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.util.PeriodoUtil;
import org.ibit.rol.sac.persistence.delegate.EstadisticaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.back.utils.GraficoBarras;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.ByteArrayOutputStream;

/**
 * @struts.action
 *  path="/estadisticas/graficopro"
 *  scope="request"
 *  validate="false"
 */
public class GraficoProcedimiento extends Action{
    protected static Log log = LogFactory.getLog(GraficoProcedimiento.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        log.info("Entramos en execute");

        String identificador = request.getParameter("id");
        if (identificador!=null){
            Long id = new Long(identificador);

            response.reset();
            Periodo periodo = PeriodoUtil.crearPeriodoSemestral();
            EstadisticaDelegate eDelegate = DelegateUtil.getEstadisticaDelegate();
            List datosEstadistica = eDelegate.listarEstadisticasProcedimiento(id,periodo.getFechaInicio(),periodo.getFechaFin());

            //Extraemos el nombre del Procedimiento
            ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
            ProcedimientoLocal procedimiento = procDelegate.consultarProcedimiento(id);

            //Creación del gráfico
            GraficoBarras grafico = new GraficoBarras(290,180);
            ByteArrayOutputStream imagen = grafico.crearGrafico(((TraduccionProcedimiento)procedimiento.getTraduccion()).getNombre(),datosEstadistica);

            response.setContentType("image/gif");
            response.setContentLength(imagen.toByteArray().length);
            response.getOutputStream().write(imagen.toByteArray());
        }

        return null;
    }
}
