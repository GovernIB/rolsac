package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Seccions;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Secciones
 *
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_SECCION, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_SECCION)
public class RespuestaSeccions extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Seccions> resultado;
	public RespuestaSeccions(String status, String mensaje, Integer numeroElementos,
			List<Seccions> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaSeccions() {
		super();
	}
	public List<Seccions> getResultado() {
		return resultado;
	}
	public void setResultado(List<Seccions> resultado) {
		this.resultado = resultado;
	}
}