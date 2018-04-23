package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Tipus;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Tipos
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_TIPO, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_TIPO)
public class RespuestaTipus extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Tipus> resultado;
	public RespuestaTipus(String status, String mensaje, Integer numeroElementos,
			List<Tipus> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaTipus() {
		super();
	}
	public List<Tipus> getResultado() {
		return resultado;
	}
	public void setResultado(List<Tipus> resultado) {
		this.resultado = resultado;
	}
}