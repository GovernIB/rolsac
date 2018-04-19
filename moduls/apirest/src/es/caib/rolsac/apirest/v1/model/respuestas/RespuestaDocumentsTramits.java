package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.DocumentsTramits;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Documentos Tramites
 * 
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_DOCUMENTOS, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_DOCUMENTOS)
public class RespuestaDocumentsTramits extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<DocumentsTramits> resultado;
	public RespuestaDocumentsTramits(String status, String mensaje, Integer numeroElementos,
			List<DocumentsTramits> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaDocumentsTramits() {
		super();
	}
	public List<DocumentsTramits> getResultado() {
		return resultado;
	}
	public void setResultado(List<DocumentsTramits> resultado) {
		this.resultado = resultado;
	}
}