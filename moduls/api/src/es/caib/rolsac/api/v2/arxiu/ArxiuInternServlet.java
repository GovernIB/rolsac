package es.caib.rolsac.api.v2.arxiu;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;

import es.caib.rolsac.api.v2.query.HibernateUtils;

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
        Session session = null;
        
        try {
            long id = Long.parseLong(idParam);
            session = HibernateUtils.getSessionFactory().openSession();
            Archivo archivo = (Archivo) session.get(Archivo.class, id);
            
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
        } catch (HibernateException e) {
            log.error("Error obteniendo session de Hibernate.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    if (session.isDirty()) {
                        log.warn("Cerrando sesion sucia!");
                    }
                    session.close();
                } catch (HibernateException e) {
                    log.error(e);
                }
            }
        }
    }
    
}
