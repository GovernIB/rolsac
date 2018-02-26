package es.caib.rolsac.apirest.v1.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.persistence.hibernate.ConnectionInfoInterceptor;

@Provider
public class MapeadorExcepcionesGenericas implements ExceptionMapper<Exception> {
	protected static Log log = LogFactory.getLog(ConnectionInfoInterceptor.class);
	public Response toResponse(Exception ex) {
		
		int status = getHttpStatus(ex);
		String mensaje = StringUtils.isEmpty(ex.getMessage())?Response.Status.fromStatusCode(status).getReasonPhrase():ex.getMessage();
		RespuestaError respuesta = new RespuestaError(status+"",mensaje);		
		log.error("",ex);		
		return Response.status(status)
				.entity(respuesta)
				.type(MediaType.APPLICATION_JSON)
				.build();	
	}

	private int getHttpStatus(Throwable ex) {
		if(ex instanceof WebApplicationException ) { 
			return((WebApplicationException)ex).getResponse().getStatus();
		} else {
			return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(); 
		}
	}
}
