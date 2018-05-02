package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.FitxesUA;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta FitxesUA
 *
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_FICHAUA, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_FICHAUA)
public class RespuestaFitxesUA extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<FitxesUA> resultado;
	public RespuestaFitxesUA(String status, String mensaje, Integer numeroElementos,
			List<FitxesUA> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaFitxesUA() {
		super();
	}
	public List<FitxesUA> getResultado() {
		return resultado;
	}
	public void setResultado(List<FitxesUA> resultado) {
		this.resultado = resultado;
	}
}