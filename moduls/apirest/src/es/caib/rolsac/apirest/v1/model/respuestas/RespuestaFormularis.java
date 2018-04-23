package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Formularis;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Formularios
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_FORMULARIO, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_FORMULARIO)
public class RespuestaFormularis extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Formularis> resultado;
	public RespuestaFormularis(String status, String mensaje, Integer numeroElementos,
			List<Formularis> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaFormularis() {
		super();
	}
	public List<Formularis> getResultado() {
		return resultado;
	}
	public void setResultado(List<Formularis> resultado) {
		this.resultado = resultado;
	}
}