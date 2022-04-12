package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import org.ibit.rol.sac.model.TramitePlantilla;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Plantillas.
 *
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_PLANTILLAS, description = Constantes.TXT_DEFINICION_CLASE
		+ Constantes.ENTIDAD_PLANTILLAS)
public class Plantillas extends EntidadBase {

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	@ApiModelProperty(value = "identificador", required = false)
	private java.lang.String identificador; // en el modelo se llama codigo

	@ApiModelProperty(value = "nombre", required = false)
	private java.lang.String nombre;

	@ApiModelProperty(value = "version", required = false)
	private java.lang.String version;

	@ApiModelProperty(value = "parametros", required = false)
	private java.lang.String parametros;

	@ApiModelProperty(value = "plataforma", required = false)
	private Plataformas plataforma;

	public Plantillas(final org.ibit.rol.sac.model.TramitePlantilla elem, final String urlBase, final String idioma,
			final boolean hateoasEnabled) {
		super(elem, urlBase, idioma, hateoasEnabled);
		if (elem.getPlataforma() != null) {
			this.plataforma = new Plataformas(elem.getPlataforma(), urlBase, idioma, hateoasEnabled);
		}
	}

	public Plantillas() {
		super();

	}

	/**
	 * Plantilla.
	 *
	 * @param json
	 * @return
	 */
	public static Plantillas valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<TramitePlantilla> typeRef = new TypeReference<TramitePlantilla>() {
		};
		Plantillas obj;
		try {
			obj = (Plantillas) objectMapper.readValue(json, typeRef);
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

	@Override
	protected void generaLinks(final String urlBase) {
		// Nada
	}

	@Override
	public void setId(final Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public java.lang.String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(final java.lang.String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the version
	 */
	public java.lang.String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(final java.lang.String version) {
		this.version = version;
	}

	/**
	 * @return the parametros
	 */
	public java.lang.String getParametros() {
		return parametros;
	}

	/**
	 * @param parametros
	 *            the parametros to set
	 */
	public void setParametros(final java.lang.String parametros) {
		this.parametros = parametros;
	}

	/**
	 * @return the plataforma
	 */
	public Plataformas getPlataforma() {
		return plataforma;
	}

	/**
	 * @param plataforma
	 *            the plataforma to set
	 */
	public void setPlataforma(final Plataformas plataforma) {
		this.plataforma = plataforma;
	}

}
