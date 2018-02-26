package es.caib.rolsac.apirest.v1.exception;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.sun.jersey.api.NotFoundException;

import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;

@Provider
public class MapeadorExcepcionesNotFound implements ExceptionMapper<NotFoundException> {

	public Response toResponse(NotFoundException ex) {
		return Response.status(ex.getResponse().getStatus())
				.entity(new RespuestaError(ex))
				.type(MediaType.APPLICATION_JSON) //this has to be set to get the generated JSON 
				.build();
	}

}
