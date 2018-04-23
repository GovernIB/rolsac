package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.MateriaAgrupacio;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Agrupación materias
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_MATERIA_AGRUPACION, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_MATERIA_AGRUPACION)
public class RespuestaMateriaAgrupacio extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<MateriaAgrupacio> resultado;
	public RespuestaMateriaAgrupacio(String status, String mensaje, Integer numeroElementos,
			List<MateriaAgrupacio> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaMateriaAgrupacio() {
		super();
	}
	public List<MateriaAgrupacio> getResultado() {
		return resultado;
	}
	public void setResultado(List<MateriaAgrupacio> resultado) {
		this.resultado = resultado;
	}
}