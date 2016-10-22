package es.caib.rolsac.api.v2.arxiu;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Document;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.TraduccionDocumentTramit;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.Validacion;

import es.caib.rolsac.api.v2.query.HibernateUtils;


/**
 * Accede a los ficheros aplicando restricciones de visibilidad.
 * @author Indra
 *
 */
public class ArxiuServlet extends HttpServlet {

    private static final long serialVersionUID = -7506098753146583591L;
    
    private static Log log = LogFactory.getLog(ArxiuServlet.class);

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
            
            boolean verified = false;
            
            // Verificamos si el documento pertenece a un procedimiento ficha
            Documento docu = getDocumentArchiu(session, id);
            //Docu proc
            if(docu != null) {
            	verified = true;
            	if (docu.getProcedimiento() !=null && !docu.getProcedimiento().isVisible()){
            		log.error("El archivo " + idParam + " no es de contingut públic (procedimiento no visible 1).");
                	response.sendError(HttpServletResponse.SC_NOT_FOUND);
                	return;
            	}
            	if(docu.getFicha() !=null && !docu.getFicha().isVisible() ){       		
            		log.error("El archivo " + idParam + " no es de contingut públic (ficha no visible 2).");
            		response.sendError(HttpServletResponse.SC_NOT_FOUND); 
            		return;
                }            	
            }
            
            if (!verified) {
	            DocumentTramit docuTramit = getDocumentTramitArchiu(session, id);
	            //Docu tramit
	            if(docuTramit != null && docuTramit.getTramit() !=null && docuTramit.getTramit().getProcedimiento() != null 
	            		&& (!docuTramit.getTramit().esPublico() || !docuTramit.getTramit().getProcedimiento().isVisible())){
	            	log.error("El archivo " + idParam + " no es de contingut públic.");
	            	if (!docuTramit.getTramit().esPublico()) {
	            		log.error(" - El tramite no es visible");
	            		log.error(" - Datos tramiteId:" + docuTramit.getTramit().getId() 
	            						+ "fechaCad:" +docuTramit.getTramit().getDataCaducitat() 
	            				        + " fechaPub:"+docuTramit.getTramit().getDataPublicacio()
	            					    + " validacion:"+ docuTramit.getTramit().getProcedimiento().getValidacion());            		
	            	}
	            	
	            	if (!docuTramit.getTramit().getProcedimiento().isVisible()) {
	            		log.error(" - El procedimiento no es visible");
	            		log.error(" - Datos procedId:" + docuTramit.getTramit().getProcedimiento().getId() 
	            				+ " fechaCad:"+docuTramit.getTramit().getProcedimiento().getFechaCaducidad() 
	    				        + " fechaPub:"+docuTramit.getTramit().getProcedimiento().getFechaPublicacion() 
	    					    + " validacion:"+ docuTramit.getTramit().getProcedimiento().getValidacion());   
	            	}
	            	response.sendError(HttpServletResponse.SC_NOT_FOUND);
	            	return;
	            }
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
        } catch (Exception e) { 
        	log.error(e);
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

    /**
     * Se obtiene el documento a partir del archivo
     * 
     * @param session
     * @param id
     * @return
     * @throws HibernateException
     */
	private Documento getDocumentArchiu(Session session, long id) throws HibernateException {
		
        Query query = session.createQuery("from Documento as docu join docu.traducciones as tradDocu where tradDocu.archivo.id=:code");
        query.setParameter("code", id);
        Documento archivo = (Documento) query.uniqueResult();
           
        return archivo;
		
	}
    
	
	 /**
     * Se obtiene el documento trámite a partir del archivo
     * 
     * @param session
     * @param id
     * @return
     * @throws HibernateException
     */
	private DocumentTramit getDocumentTramitArchiu(Session session, long id) throws HibernateException {
		
        Query query = session.createQuery("from DocumentTramit docu join docu.traducciones as tradDocu where tradDocu.archivo.id=:code");
        query.setParameter("code", id);
        DocumentTramit archivo = (DocumentTramit) query.uniqueResult();
           
        return archivo;
		
	}	
}
