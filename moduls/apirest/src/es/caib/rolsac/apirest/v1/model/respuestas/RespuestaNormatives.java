package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Normatives;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Fitxes
 *
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_FICHA, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_FICHA)
public class RespuestaNormatives extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Normatives> resultado;
	public RespuestaNormatives(String status, String mensaje, Integer numeroElementos,
			List<Normatives> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaNormatives() {
		super();
	}
	public List<Normatives> getResultado() {
		return resultado;
	}
	public void setResultado(List<Normatives> resultado) {
		this.resultado = resultado;
	}
}