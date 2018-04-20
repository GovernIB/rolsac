package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.DocumentsNormatives;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Documentos Normativas
 * 
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_DOCUMENTOS_NORMATIVAS, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_DOCUMENTOS_NORMATIVAS)
public class RespuestaDocumentsNormatives extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<DocumentsNormatives> resultado;
	public RespuestaDocumentsNormatives(String status, String mensaje, Integer numeroElementos,
			List<DocumentsNormatives> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaDocumentsNormatives() {
		super();
	}
	public List<DocumentsNormatives> getResultado() {
		return resultado;
	}
	public void setResultado(List<DocumentsNormatives> resultado) {
		this.resultado = resultado;
	}
}