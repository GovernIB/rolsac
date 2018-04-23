package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.AgrupacioMateries;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_AGRUPACIO_MATERIES, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_AGRUPACIO_MATERIES)
public class RespuestaAgrupacioMateries extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<AgrupacioMateries> resultado;
	public RespuestaAgrupacioMateries(String status, String mensaje, Integer numeroElementos,
			List<AgrupacioMateries> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaAgrupacioMateries() {
		super();
	}
	public List<AgrupacioMateries> getResultado() {
		return resultado;
	}
	public void setResultado(List<AgrupacioMateries> resultado) {
		this.resultado = resultado;
	}
}