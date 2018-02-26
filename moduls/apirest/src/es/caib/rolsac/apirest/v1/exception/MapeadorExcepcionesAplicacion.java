package es.caib.rolsac.apirest.v1.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
/**
 * Clase encargada de mapear los errores controlados de la aplicación 
 * 
 * @author Indra
 *
 */

@Provider
public class MapeadorExcepcionesAplicacion implements ExceptionMapper<ExcepcionAplicacion> {

	public Response toResponse(ExcepcionAplicacion ex) {
		return Response.status(ex.getStatus())
				.entity(new RespuestaError(ex))
				.type(MediaType.APPLICATION_JSON).
				build();
	}

}