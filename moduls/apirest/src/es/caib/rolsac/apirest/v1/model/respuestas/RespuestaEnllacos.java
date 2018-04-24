package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Enllacos;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Enllacos
 * 
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_ENLACE, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_ENLACE)
public class RespuestaEnllacos extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Enllacos> resultado;
	public RespuestaEnllacos(String status, String mensaje, Integer numeroElementos,
			List<Enllacos> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaEnllacos() {
		super();
	}
	public List<Enllacos> getResultado() {
		return resultado;
	}
	public void setResultado(List<Enllacos> resultado) {
		this.resultado = resultado;
	}
}