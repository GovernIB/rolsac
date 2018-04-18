package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Bulletins;
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
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_BOLETINES, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_BOLETINES)
public class RespuestaBulletins extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Bulletins> resultado;
	public RespuestaBulletins(String status, String mensaje, Integer numeroElementos,
			List<Bulletins> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaBulletins() {
		super();
	}
	public List<Bulletins> getResultado() {
		return resultado;
	}
	public void setResultado(List<Bulletins> resultado) {
		this.resultado = resultado;
	}
}