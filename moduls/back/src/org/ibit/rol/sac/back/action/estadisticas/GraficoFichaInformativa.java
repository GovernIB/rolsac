package org.ibit.rol.sac.back.action.estadisticas;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Periodo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.persistence.util.PeriodoUtil;
import org.ibit.rol.sac.persistence.delegate.EstadisticaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.back.utils.GraficoBarras;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.ByteArrayOutputStream;

/**
 * @struts.action
 *  path="/estadisticas/graficofi"
 *  scope="request"
 *  validate="false"
 *
 */
public class GraficoFichaInformativa extends Action {

    protected static Log log = LogFactory.getLog(GraficoFichaInformativa.class);

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
            List datosEstadistica = null;

            // Veremos las estadisticas de la ficha desde esa materia
            if (request.getParameter("idFicMat")!=null) {
            	datosEstadistica = eDelegate.listarEstadisticasFicha(id,periodo.getFechaInicio(),periodo.getFechaFin(), new Long(""+request.getParameter("idFicMat")), null );
            }
            // Veremos las estadisticas de la ficha desde esa UA
            else if (request.getParameter("idFicUA")!=null) {
            	datosEstadistica = eDelegate.listarEstadisticasFicha(id,periodo.getFechaInicio(),periodo.getFechaFin(), null, new Long(""+request.getParameter("idFicUA")) );
            }
            // Veremos las estadisticas de la ficha en general
            else {
            	datosEstadistica = eDelegate.listarEstadisticasFicha(id,periodo.getFechaInicio(),periodo.getFechaFin(), null, null);
            }
            
            //Extraemos el título de la Ficha
            FichaDelegate fDelegate = DelegateUtil.getFichaDelegate();
            Ficha ficha = fDelegate.obtenerFicha(id);

            //Creación del gráfico
            GraficoBarras grafico = new GraficoBarras(180,180);
            ByteArrayOutputStream imagen = grafico.crearGrafico(((TraduccionFicha)ficha.getTraduccion()).getTitulo(), datosEstadistica);

            response.setContentType("image/gif");
            response.setContentLength(imagen.toByteArray().length);
            response.getOutputStream().write(imagen.toByteArray());
        }

        return null;
    }
}
