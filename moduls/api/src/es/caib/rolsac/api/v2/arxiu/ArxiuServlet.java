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
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;

import es.caib.rolsac.api.v2.arxiu.ejb.ArxiuQueryServiceEJBLocator;
import es.caib.rolsac.api.v2.arxiu.ejb.intf.ArxiuQueryServiceEJBRemote;




/**
 * Accede a los ficheros aplicando restricciones de visibilidad.
 * @author Indra
 *
 */
@SuppressWarnings("deprecation")
public class ArxiuServlet extends HttpServlet {

    private static final long serialVersionUID = -7506098753146583591L;
    
    private static Log log = LogFactory.getLog(ArxiuServlet.class);


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
            
            boolean verified = false;
            
            // Verificamos si el documento pertenece a un procedimiento ficha            
           Documento docu = ejb.getDocumentArchiu(id);
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
	            DocumentTramit docuTramit = ejb.getDocumentTramitArchiu(id);
	            
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
        } catch (Exception e) { 
        	log.error(e);
        	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } 
    }

}
