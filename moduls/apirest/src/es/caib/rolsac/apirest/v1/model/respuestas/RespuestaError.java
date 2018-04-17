package es.caib.rolsac.apirest.v1.model.respuestas;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.api.ParamException;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;

/**
 * Respuesta Error
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + "Error", description = Constantes.TXT_RESPUESTA + "Error")
public class RespuestaError extends RespuestaBase{	

	public RespuestaError(String status, String mensaje) {
		super(status, mensaje, new Integer(0));
	};
	public RespuestaError() {
		super();
	}
	
	public RespuestaError(ExcepcionAplicacion ex){	
		super(ex.getStatus()+"", ex.getMensajeError(), new Integer(0));
	}
	
	public RespuestaError(NotFoundException ex){	
		super(ex.getResponse().getStatus()+"", Constantes.MSJ_404_GENERICO, new Integer(0));
	}
	public RespuestaError(ParamException ex){	
		super(ex.getResponse().getStatus() +"", Constantes.MSJ_400_GENERICO + "(parametro: "+ex.getParameterName()+" // Tipo esperado: "+ex.getParameterName()+")", new Integer(0));
	}
	
	public RespuestaError(ValidationException ex, String errores){	
		super(Response.Status.BAD_REQUEST.getStatusCode()+"", Constantes.MSJ_400_GENERICO + "(" + errores + ")", new Integer(0));
	}
	
	
	
	
}