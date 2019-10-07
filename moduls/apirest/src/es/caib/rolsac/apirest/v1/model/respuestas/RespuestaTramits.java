package es.caib.rolsac.apirest.v1.model.respuestas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rolsac.apirest.v1.model.Tramits;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta tramite
 *
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_TRAMITE, description = Constantes.TXT_RESPUESTA
		+ Constantes.ENTIDAD_TRAMITE)
public class RespuestaTramits extends RespuestaBase {
	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado", required = false)
	private List<Tramits> resultado;

	/** Resultado. **/
	@ApiModelProperty(value = "Enlace tramite telematico", required = false)
	private String url;

	public RespuestaTramits(final String status, final String mensaje, final Integer numeroElementos,
			final List<Tramits> resultado) {
		super(status, mensaje, numeroElementos);
		this.resultado = resultado;
	};

	public RespuestaTramits() {
		super();
	}

	public List<Tramits> getResultado() {
		return resultado;
	}

	public void setResultado(final List<Tramits> resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}
}