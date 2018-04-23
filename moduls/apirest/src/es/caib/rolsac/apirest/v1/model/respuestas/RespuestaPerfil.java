package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Perfil;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Perfil
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PERFIL, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PERFIL)
public class RespuestaPerfil extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Perfil> resultado;
	public RespuestaPerfil(String status, String mensaje, Integer numeroElementos,
			List<Perfil> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaPerfil() {
		super();
	}
	public List<Perfil> getResultado() {
		return resultado;
	}
	public void setResultado(List<Perfil> resultado) {
		this.resultado = resultado;
	}
}