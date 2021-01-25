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
 * Representación Lopd Legitimacion.
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_LOPD_LEGITIMACION, description = Constantes.TXT_DEFINICION_CLASE
		+ Constantes.ENTIDAD_LOPD_LEGITIMACION)
public class LopdLegitimacion extends EntidadBase {

	/** Identificador. **/
	@ApiModelProperty(value = "identificador", required = false)
	private String identificador;

	/** Nombre. **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;

	public LopdLegitimacion(final org.ibit.rol.sac.model.LopdLegitimacion elem, final String urlBase,
			final String idioma, final boolean hateoasEnabled) {
		super(elem, urlBase, idioma, hateoasEnabled);
	}

	public LopdLegitimacion() {
		super();
	}

	/**
	 * Plataforma.
	 *
	 * @param json
	 * @return
	 */
	public static LopdLegitimacion valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Plataforma> typeRef = new TypeReference<Plataforma>() {
		};
		LopdLegitimacion obj;
		try {
			obj = (LopdLegitimacion) objectMapper.readValue(json, typeRef);
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

	@Override
	protected void generaLinks(final String urlBase) {
		// Nada
	}

	@Override
	public void setId(final Long codigo) {
		// Vacio
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador
	 *            the identificador to set
	 */
	public void setIdentificador(final String identificador) {
		this.identificador = identificador;
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LopdLeg [nombre=" + nombre + ", identificador=" + identificador + "]";
	}

}
