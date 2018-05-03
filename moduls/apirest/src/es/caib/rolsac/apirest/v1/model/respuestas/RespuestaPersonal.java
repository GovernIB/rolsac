package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Personal;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Personal
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PERSONAL, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PERSONAL)
public class RespuestaPersonal extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Personal> resultado;
	public RespuestaPersonal(String status, String mensaje, Integer numeroElementos,
			List<Personal> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaPersonal() {
		super();
	}
	public List<Personal> getResultado() {
		return resultado;
	}
	public void setResultado(List<Personal> resultado) {
		this.resultado = resultado;
	}
}