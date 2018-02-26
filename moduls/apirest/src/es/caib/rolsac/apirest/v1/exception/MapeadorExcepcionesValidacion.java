package es.caib.rolsac.apirest.v1.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
// Deberia funcionar pero de no se lanzan las excepciones de validacion.(parece que solo ira en jersey 2)
// Se mantiene por si se migra a jersey 2, disponer del mapeador adecuado.
@Provider
public class MapeadorExcepcionesValidacion implements ExceptionMapper<javax.validation.ValidationException> {

    @Override
    public Response toResponse(javax.validation.ValidationException ex) {
        final StringBuilder strBuilder = new StringBuilder();
        boolean primero = true;
        for (ConstraintViolation<?> cv : ((ConstraintViolationException) ex).getConstraintViolations()) {
        	if(primero) {
        		primero=false;
        	}else {
        		strBuilder.append(", ");
        	}
            strBuilder.append(cv.getPropertyPath().toString() + " " + cv.getMessage());
        }
        RespuestaError respuesta = new RespuestaError(Response.Status.BAD_REQUEST.getStatusCode()+"", strBuilder.toString());		
        
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
				.entity(respuesta)
				.type(MediaType.APPLICATION_JSON)
				.build();	
    }
}