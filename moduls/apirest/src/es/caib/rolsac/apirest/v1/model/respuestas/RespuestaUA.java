package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Idioma;
import es.caib.rolsac.apirest.v1.model.UnitatAdministrativa;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Idioma
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = "Respuesta Unidad Administrativa", description = "Respuesta Unidad Administrativa")
public class RespuestaUA extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<UnitatAdministrativa> resultado;
	public RespuestaUA(String status, String mensaje, Integer numeroElementos,
			List<UnitatAdministrativa> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaUA() {
		super();
	}
	public List<UnitatAdministrativa> getResultado() {
		return resultado;
	}
	public void setResultado(List<UnitatAdministrativa> resultado) {
		this.resultado = resultado;
	}
}