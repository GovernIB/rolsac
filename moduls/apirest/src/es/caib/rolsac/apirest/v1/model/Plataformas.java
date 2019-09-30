package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import org.ibit.rol.sac.model.Plataforma;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Serveis.
 *
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_PLATAFORMAS, description = Constantes.TXT_DEFINICION_CLASE
		+ Constantes.ENTIDAD_PLATAFORMAS)
public class Plataformas extends EntidadBase {

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	@ApiModelProperty(value = "identificador", required = false)
	private java.lang.String identificador; // en el modelo se llama codigo

	@ApiModelProperty(value = "descripcion", required = false)
	private java.lang.String descripcion;

	@ApiModelProperty(value = "urlAcceso", required = false)
	private java.lang.String urlAcceso;

	public Plataformas(final org.ibit.rol.sac.model.Plataforma elem, final String urlBase, final String idioma,
			final boolean hateoasEnabled) {
		super(elem, urlBase, idioma, hateoasEnabled);
	}

	public Plataformas() {
		super();
	}

	/**
	 * Plataforma.
	 *
	 * @param json
	 * @return
	 */
	public static Plataformas valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Plataforma> typeRef = new TypeReference<Plataforma>() {
		};
		Plataformas obj;
		try {
			obj = (Plataformas) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}

	/**
	 * To JSON
	 *
	 * @return
	 */
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

	@Override
	protected void addSetersInvalidos() {
		if (!SETTERS_INVALIDS.contains("setCodigo")) {
			SETTERS_INVALIDS.add("setCodigo");
		}

	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(final long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the identificador
	 */
	public java.lang.String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador
	 *            the identificador to set
	 */
	public void setIdentificador(final java.lang.String identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the descripcion
	 */
	public java.lang.String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(final java.lang.String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the urlAcceso
	 */
	public java.lang.String getUrlAcceso() {
		return urlAcceso;
	}

	/**
	 * @param urlAcceso
	 *            the urlAcceso to set
	 */
	public void setUrlAcceso(final java.lang.String urlAcceso) {
		this.urlAcceso = urlAcceso;
	}

	@Override
	protected void generaLinks(final String urlBase) {
		// Nada
	}

	@Override
	public void setId(final Long codigo) {
		this.codigo = codigo;
	}

}
