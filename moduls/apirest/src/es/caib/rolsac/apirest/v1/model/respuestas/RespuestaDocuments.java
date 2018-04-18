package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Documents;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Documentos
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_DOCUMENTOS, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_DOCUMENTOS)
public class RespuestaDocuments extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Documents> resultado;
	public RespuestaDocuments(String status, String mensaje, Integer numeroElementos,
			List<Documents> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaDocuments() {
		super();
	}
	public List<Documents> getResultado() {
		return resultado;
	}
	public void setResultado(List<Documents> resultado) {
		this.resultado = resultado;
	}
}