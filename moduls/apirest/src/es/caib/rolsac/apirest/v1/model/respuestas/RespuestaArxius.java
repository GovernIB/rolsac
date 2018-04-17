package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Arxius;
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
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_ARCHIVO, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_ARCHIVO)
public class RespuestaArxius extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Arxius> resultado;
	public RespuestaArxius(String status, String mensaje, Integer numeroElementos,
			List<Arxius> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaArxius() {
		super();
	}
	public List<Arxius> getResultado() {
		return resultado;
	}
	public void setResultado(List<Arxius> resultado) {
		this.resultado = resultado;
	}
}