package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.UnitatAdministrativa;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Idioma
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_UA, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_UA)
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