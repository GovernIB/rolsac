package es.caib.rolsac.apirest.v1.model.respuestas;

import javax.xml.bind.annotation.XmlRootElement;

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
@ApiModel(value = Constantes.TXT_RESPUESTA + "Objeto simple", description = Constantes.TXT_RESPUESTA + "Objeto simple")
public class RespuestaSimple extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private String resultado ;
	public RespuestaSimple(String status, String mensaje, Integer numeroElementos,
			String resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaSimple() {
		super();
	}
		
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}