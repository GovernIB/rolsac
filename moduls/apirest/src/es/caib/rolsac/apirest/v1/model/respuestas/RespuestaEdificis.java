package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Edificis;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Edificis
 * 
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_EDIFICIO, description = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_EDIFICIO)
public class RespuestaEdificis extends RespuestaBase{	
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado",  required = false)
	private List<Edificis> resultado;
	public RespuestaEdificis(String status, String mensaje, Integer numeroElementos,
			List<Edificis> resultado) {
		super(status,  mensaje, numeroElementos);
		this.resultado=resultado;		
	};
	public RespuestaEdificis() {
		super();
	}
	public List<Edificis> getResultado() {
		return resultado;
	}
	public void setResultado(List<Edificis> resultado) {
		this.resultado = resultado;
	}
}