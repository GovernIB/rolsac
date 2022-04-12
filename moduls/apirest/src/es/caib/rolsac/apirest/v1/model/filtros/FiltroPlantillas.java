package es.caib.rolsac.apirest.v1.model.filtros;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * FiltroPlantillas.
 *
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroPlantillas", description = "Filtro que permite buscar por diferentes campos")
public class FiltroPlantillas {

	public static final String SAMPLE = Constantes.SALTO_LINEA + "{\"codigo\":\"0\"," + Constantes.SALTO_LINEA
			+ "\"nombre\":\"texto\"" + Constantes.SALTO_LINEA + "\"urlAcceso\":\"texto\"" + Constantes.SALTO_LINEA;

	/** codigo. **/
	@ApiModelProperty(value = "codigo", required = false)
	private Integer codigo;

	/** nombre. **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;

	/** descripcion. **/
	@ApiModelProperty(value = "descripcion", required = false)
	private String descripcion;

	/** url. **/
	@ApiModelProperty(value = "urlAcceso", required = false)
	private String urlAcceso;

	public FiltroGenerico toFiltroGenerico() {
		final FiltroGenerico fg = new FiltroGenerico();

		if (this.codigo != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PLANTILLAS_CODIGO, this.codigo + "");
		}

		if (this.nombre != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PLANTILLAS_IDENTIFICADOR, this.nombre + "");
		}

		if (this.urlAcceso != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PLANTILLAS_URL_ACCESO, this.urlAcceso + "");
		}

		if (this.descripcion != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PLANTILLAS_NOMBRE, this.descripcion + "");
		}

		return fg;
	}

	public static FiltroPlantillas valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroPlantillas> typeRef = new TypeReference<FiltroPlantillas>() {
		};
		FiltroPlantillas obj;
		try {
			obj = (FiltroPlantillas) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}

	public String toJson() {
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			return objectMapper.writeValueAsString(this);
		} catch (final JsonProcessingException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(final Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the urlAcceso
	 */
	public String getUrlAcceso() {
		return urlAcceso;
	}

	/**
	 * @param urlAcceso
	 *            the urlAcceso to set
	 */
	public void setUrlAcceso(final String urlAcceso) {
		this.urlAcceso = urlAcceso;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

}
