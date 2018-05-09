package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Usuaris;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * RespuestaUsuarios
 * 
 * @author Indra
 *
 *Respuesta, (respuesta de la entidad, tiene una parte común y un listado de elementos de entidad): 
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_USUARIOS, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_USUARIOS)
public class RespuestaUsuarios extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Usuaris> resultado;
	public RespuestaUsuarios(String status, String mensaje, Integer numeroElementos,
			List<Usuaris> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaUsuarios() {
		super();
	}
	public List<Usuaris> getResultado() {
		return resultado;
	}
	public void setResultado(List<Usuaris> resultado) {
		this.resultado = resultado;
	}
}