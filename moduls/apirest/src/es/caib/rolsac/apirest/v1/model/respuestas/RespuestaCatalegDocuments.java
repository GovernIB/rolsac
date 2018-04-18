package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.CatalegDocuments;
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
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_CATALOGO_DOCUMENTOS, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_CATALOGO_DOCUMENTOS)
public class RespuestaCatalegDocuments extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<CatalegDocuments> resultado;
	public RespuestaCatalegDocuments(String status, String mensaje, Integer numeroElementos,
			List<CatalegDocuments> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaCatalegDocuments() {
		super();
	}
	public List<CatalegDocuments> getResultado() {
		return resultado;
	}
	public void setResultado(List<CatalegDocuments> resultado) {
		this.resultado = resultado;
	}
}