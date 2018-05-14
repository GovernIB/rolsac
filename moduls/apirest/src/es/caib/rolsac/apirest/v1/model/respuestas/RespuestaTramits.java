package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Tramits;
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
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_TRAMITE, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_TRAMITE)
public class RespuestaTramits extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Tramits> resultado;
	public RespuestaTramits(String status, String mensaje, Integer numeroElementos,
			List<Tramits> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaTramits() {
		super();
	}
	public List<Tramits> getResultado() {
		return resultado;
	}
	public void setResultado(List<Tramits> resultado) {
		this.resultado = resultado;
	}
}