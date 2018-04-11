package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.AgrupacioFetVital;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Idioma
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = "Respuesta Agrupación hecho vital", description = "Respuesta Agrupación hecho vital")
public class RespuestaAgrupacioFetVital extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<AgrupacioFetVital> resultado;
	public RespuestaAgrupacioFetVital(String status, String mensaje, Integer numeroElementos,
			List<AgrupacioFetVital> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaAgrupacioFetVital() {
		super();
	}
	public List<AgrupacioFetVital> getResultado() {
		return resultado;
	}
	public void setResultado(List<AgrupacioFetVital> resultado) {
		this.resultado = resultado;
	}
}