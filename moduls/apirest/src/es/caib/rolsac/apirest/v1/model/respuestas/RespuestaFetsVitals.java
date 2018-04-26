package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.FetsVitals;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * RespuestaFetsVitals
 * 
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_HECHOS_VITALES, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_HECHOS_VITALES)
public class RespuestaFetsVitals extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<FetsVitals> resultado;
	public RespuestaFetsVitals(String status, String mensaje, Integer numeroElementos,
			List<FetsVitals> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaFetsVitals() {
		super();
	}
	public List<FetsVitals> getResultado() {
		return resultado;
	}
	public void setResultado(List<FetsVitals> resultado) {
		this.resultado = resultado;
	}
}