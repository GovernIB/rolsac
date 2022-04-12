package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Plantillas;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Plantillas
 *
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PLATAFORMAS, description = Constantes.TXT_RESPUESTA
		+ Constantes.ENTIDAD_PLATAFORMAS)
public class RespuestaPlantillas extends RespuestaBase {
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado", required = false)
	private List<Plantillas> resultado;

	public RespuestaPlantillas(final String status, final String mensaje, final Integer numeroElementos,
			final List<Plantillas> resultado) {
		super(status, mensaje, numeroElementos);
		this.resultado = resultado;
	};

	public RespuestaPlantillas() {
		super();
	}

	public List<Plantillas> getResultado() {
		return resultado;
	}

	public void setResultado(final List<Plantillas> resultado) {
		this.resultado = resultado;
	}
}