package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.EspaisTerritorials;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Espacios Territoriales
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_ESPACIO_TERRITORIAL, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_ESPACIO_TERRITORIAL)
public class RespuestaEspaisTerritorials extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<EspaisTerritorials> resultado;
	public RespuestaEspaisTerritorials(String status, String mensaje, Integer numeroElementos,
			List<EspaisTerritorials> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaEspaisTerritorials() {
		super();
	}
	public List<EspaisTerritorials> getResultado() {
		return resultado;
	}
	public void setResultado(List<EspaisTerritorials> resultado) {
		this.resultado = resultado;
	}
}