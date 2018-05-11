package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Serveis;
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
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_SERVICIOS, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_SERVICIOS)
public class RespuestaServeis extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Serveis> resultado;
	public RespuestaServeis(String status, String mensaje, Integer numeroElementos,
			List<Serveis> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaServeis() {
		super();
	}
	public List<Serveis> getResultado() {
		return resultado;
	}
	public void setResultado(List<Serveis> resultado) {
		this.resultado = resultado;
	}
}