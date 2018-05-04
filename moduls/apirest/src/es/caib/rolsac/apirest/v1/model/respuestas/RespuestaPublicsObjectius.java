package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.PublicsObjectius;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta PublicsObjectius
 *
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PUBLICO, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PUBLICO)
public class RespuestaPublicsObjectius extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<PublicsObjectius> resultado;
	public RespuestaPublicsObjectius(String status, String mensaje, Integer numeroElementos,
			List<PublicsObjectius> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaPublicsObjectius() {
		super();
	}
	public List<PublicsObjectius> getResultado() {
		return resultado;
	}
	public void setResultado(List<PublicsObjectius> resultado) {
		this.resultado = resultado;
	}
}