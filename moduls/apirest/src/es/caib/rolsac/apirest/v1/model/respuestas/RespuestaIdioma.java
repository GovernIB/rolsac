package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Idioma;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Idioma
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = "RespuestaIdioma", description = "Respuesta Idioma")
public class RespuestaIdioma extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Idioma> resultado;
	public RespuestaIdioma(String status, String mensaje, Integer numeroElementos,
			List<Idioma> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;
		
	};
	public RespuestaIdioma() {
		super();
	}
	public List<Idioma> getResultado() {
		return resultado;
	}
	public void setResultado(List<Idioma> resultado) {
		this.resultado = resultado;
	}
}