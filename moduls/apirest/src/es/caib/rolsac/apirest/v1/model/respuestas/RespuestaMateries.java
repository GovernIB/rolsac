package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Materies;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Materies
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_MATERIA, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_MATERIA)
public class RespuestaMateries extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Materies> resultado;
	public RespuestaMateries(String status, String mensaje, Integer numeroElementos,
			List<Materies> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaMateries() {
		super();
	}
	public List<Materies> getResultado() {
		return resultado;
	}
	public void setResultado(List<Materies> resultado) {
		this.resultado = resultado;
	}
}