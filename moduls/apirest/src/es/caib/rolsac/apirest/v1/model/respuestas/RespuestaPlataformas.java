package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Plataformas;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Plataforma
 *
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PLATAFORMAS, description = Constantes.TXT_RESPUESTA
		+ Constantes.ENTIDAD_PLATAFORMAS)
public class RespuestaPlataformas extends RespuestaBase {
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado", required = false)
	private List<Plataformas> resultado;

	public RespuestaPlataformas(final String status, final String mensaje, final Integer numeroElementos,
			final List<Plataformas> resultado) {
		super(status, mensaje, numeroElementos);
		this.resultado = resultado;
	};

	public RespuestaPlataformas() {
		super();
	}

	public List<Plataformas> getResultado() {
		return resultado;
	}

	public void setResultado(final List<Plataformas> resultado) {
		this.resultado = resultado;
	}
}