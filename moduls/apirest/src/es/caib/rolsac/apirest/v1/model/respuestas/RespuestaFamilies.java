package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Families;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Familias
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_FAMILIA, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_FAMILIA)
public class RespuestaFamilies extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Families> resultado;
	public RespuestaFamilies(String status, String mensaje, Integer numeroElementos,
			List<Families> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaFamilies() {
		super();
	}
	public List<Families> getResultado() {
		return resultado;
	}
	public void setResultado(List<Families> resultado) {
		this.resultado = resultado;
	}
}