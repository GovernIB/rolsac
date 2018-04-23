package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.TipusAfectacio;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Tipos afectacion
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_TIPO_AFECTACION, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_TIPO_AFECTACION)
public class RespuestaTipusAfectacio extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<TipusAfectacio> resultado;
	public RespuestaTipusAfectacio(String status, String mensaje, Integer numeroElementos,
			List<TipusAfectacio> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaTipusAfectacio() {
		super();
	}
	public List<TipusAfectacio> getResultado() {
		return resultado;
	}
	public void setResultado(List<TipusAfectacio> resultado) {
		this.resultado = resultado;
	}
}