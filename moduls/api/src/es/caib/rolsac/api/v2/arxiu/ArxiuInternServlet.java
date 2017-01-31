package es.caib.rolsac.api.v2.arxiu;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;

import es.caib.rolsac.api.v2.arxiu.ejb.ArxiuQueryServiceEJBLocator;
import es.caib.rolsac.api.v2.arxiu.ejb.intf.ArxiuQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.LocatorException;

/**
 * Accede a los ficheros sin aplicar restricciones de visibilidad.
 * @author Indra
 *
 */
public class ArxiuInternServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;
	
	private static Log log = LogFactory.getLog(ArxiuInternServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParam = request.getParameter("id");
        
        try {
            long id = Long.parseLong(idParam);
            ArxiuQueryServiceEJBRemote ejb = new ArxiuQueryServiceEJBLocator().getArxiuQueryServiceEJB();
            
            Archivo archivo = ejb.obtenirArxiu(id);
            
            if (archivo == null) {
                log.error("El id " + idParam + " no existe.");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            response.setContentType(archivo.getMime());
            response.setHeader("Content-Disposition", "attachment;filename=" + archivo.getNombre());
            response.setIntHeader("Content-Length", (int) archivo.getDatos().length);
            
            OutputStream out = response.getOutputStream();
            out.write(archivo.getDatos()); 
            out.close(); 

        } catch (NumberFormatException e) {
            log.error("El id " + idParam + " no es numerico.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (LocatorException e) {
            log.error("Error obteniendo ArxiuQueryServiceEJBLocator.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } 
        
    }
    
}
