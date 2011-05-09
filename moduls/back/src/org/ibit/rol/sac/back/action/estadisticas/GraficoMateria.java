package org.ibit.rol.sac.back.action.estadisticas;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Periodo;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.persistence.util.PeriodoUtil;
import org.ibit.rol.sac.persistence.delegate.EstadisticaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.back.utils.GraficoBarras;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.ByteArrayOutputStream;

/**
 * @struts.action
 *  path="/estadisticas/graficomat"
 *  scope="request"
 *  validate="false"
 *
 */
public class GraficoMateria extends Action {

    protected static Log log = LogFactory.getLog(GraficoMateria.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.info("Entramos en execute");

        String identificador = request.getParameter("id");
        if (identificador!=null){
            Long id = new Long(identificador);

            response.reset();
            Periodo periodo = PeriodoUtil.crearPeriodoAnual();
            EstadisticaDelegate eDelegate = DelegateUtil.getEstadisticaDelegate();
            List datosEstadistica = eDelegate.listarEstadisticasMateria(id,periodo.getFechaInicio(),periodo.getFechaFin());

            //Extraemos el título de la Materia
            MateriaDelegate mDelegate = DelegateUtil.getMateriaDelegate();
            Materia mat = mDelegate.obtenerMateria(id);

            //Creación del gráfico
            GraficoBarras grafico = new GraficoBarras(500,180);
            ByteArrayOutputStream imagen = grafico.crearGrafico(((TraduccionMateria)mat.getTraduccion()).getNombre(), datosEstadistica);

            response.setContentType("image/gif");
            response.setContentLength(imagen.toByteArray().length);
            response.getOutputStream().write(imagen.toByteArray());
        }

        return null;
    }
}
