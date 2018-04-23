package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.IconesFamilies;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Iconos familia
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_ICONO_FAMILIA, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_ICONO_FAMILIA)
public class RespuestaIconesFamilies extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<IconesFamilies> resultado;
	public RespuestaIconesFamilies(String status, String mensaje, Integer numeroElementos,
			List<IconesFamilies> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaIconesFamilies() {
		super();
	}
	public List<IconesFamilies> getResultado() {
		return resultado;
	}
	public void setResultado(List<IconesFamilies> resultado) {
		this.resultado = resultado;
	}
}