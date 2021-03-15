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
 * 
 * @author Indra
 *
 */
@SuppressWarnings("deprecation")
public class ArxiuServlet extends HttpServlet {

	private static final long serialVersionUID = -7506098753146583591L;

	private static Log log = LogFactory.getLog(ArxiuServlet.class);

	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		final String idParam = request.getParameter("id");
		Archivo archivo = null;

		try {
			final long id = Long.parseLong(idParam);
			final ArxiuQueryServiceEJBRemote ejb = new ArxiuQueryServiceEJBLocator().getArxiuQueryServiceEJB();

			archivo = ejb.obtenirArxiu(id);

			if (archivo == null) {
				log.error("El id " + idParam + " no existe.");
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			boolean verified = false;

			// Verificamos si el documento pertenece a un procedimiento ficha
			final Documento docu = ejb.getDocumentArchiu(id);
			// Docu proc
			if (docu != null) {
				verified = true;
				if (docu.getProcedimiento() != null && !docu.getProcedimiento().isVisible()) {
					log.error("El archivo " + idParam + " no es de contingut públic (procedimiento no visible 1).");
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				if (docu.getFicha() != null && !docu.getFicha().isVisible()) {
					log.error("El archivo " + idParam + " no es de contingut públic (ficha no visible 2).");
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
			}

			if (!verified) {
				final DocumentTramit docuTramit = ejb.getDocumentTramitArchiu(id);

				// Docu tramit
				if (docuTramit != null && docuTramit.getTramit() != null
						&& docuTramit.getTramit().getProcedimiento() != null && (!docuTramit.getTramit().esPublico()
								|| !docuTramit.getTramit().getProcedimiento().isVisible())) {
					log.error("El archivo " + idParam + " no es de contingut públic.");
					if (!docuTramit.getTramit().esPublico()) {
						log.error(" - El tramite no es visible");
						log.error(" - Datos tramiteId:" + docuTramit.getTramit().getId() + "fechaCad:"
								+ docuTramit.getTramit().getDataCaducitat() + " fechaPub:"
								+ docuTramit.getTramit().getDataPublicacio() + " validacion:"
								+ docuTramit.getTramit().getProcedimiento().getValidacion());
					}

					if (!docuTramit.getTramit().getProcedimiento().isVisible()) {
						log.error(" - El procedimiento no es visible");
						log.error(" - Datos procedId:" + docuTramit.getTramit().getProcedimiento().getId()
								+ " fechaCad:" + docuTramit.getTramit().getProcedimiento().getFechaCaducidad()
								+ " fechaPub:" + docuTramit.getTramit().getProcedimiento().getFechaPublicacion()
								+ " validacion:" + docuTramit.getTramit().getProcedimiento().getValidacion());
					}
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
			}

			response.setContentType(archivo.getMime());
			response.setHeader("Content-Disposition", "attachment;filename=" + archivo.getNombre());
			response.setIntHeader("Content-Length", archivo.getDatos().length);

			final OutputStream out = response.getOutputStream();
			out.write(archivo.getDatos());
			out.close();

		} catch (final NumberFormatException e) {
			log.error("El id " + idParam + " no es numerico.");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} catch (final Exception e) {
			log.error("Error interno buscando el archivo id:" + idParam);
			if (archivo != null) {
				log.error("El archivo tiene id:" + archivo.getId());
			}
			log.error(e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
