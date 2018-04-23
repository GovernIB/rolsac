package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.UnitatsMateries;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Unidades Materias
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_UNIDAD_MATERIA, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_UNIDAD_MATERIA)
public class RespuestaUnitatsMateries extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<UnitatsMateries> resultado;
	public RespuestaUnitatsMateries(String status, String mensaje, Integer numeroElementos,
			List<UnitatsMateries> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaUnitatsMateries() {
		super();
	}
	public List<UnitatsMateries> getResultado() {
		return resultado;
	}
	public void setResultado(List<UnitatsMateries> resultado) {
		this.resultado = resultado;
	}
}