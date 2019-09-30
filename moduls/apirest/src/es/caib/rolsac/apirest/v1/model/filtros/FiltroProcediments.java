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
 * FiltroProcedimientos.
 *
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "Filtropprocedimientos", description = "Filtro que permite buscar por diferentes campos")
public class FiltroProcediments {

	public static final String SAMPLE = Constantes.SALTO_LINEA + "{\"codigoUA\":\"0\"," + Constantes.SALTO_LINEA
			+ "\"codigoUADir3\":\"codigo\"," + Constantes.SALTO_LINEA + "\"buscarEnDescendientesUA\":\"0/1\","
			+ Constantes.SALTO_LINEA
			+ "\"activo\":\"0/1\", (1=procedimientos publicos y no caducados, 0=procedimientos no publicos o caducados)"
			+ Constantes.SALTO_LINEA + "\"estadoUA\":\"1/2/3\",(1=Pública,2=Interna,3=Reserva)" + Constantes.SALTO_LINEA
			+ "\"codigoAgrupacionHechoVital\":\"0\"," + Constantes.SALTO_LINEA + "\"codigoPublicoObjetivo\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoFamilia\":\"0\"," + Constantes.SALTO_LINEA
			+ "\"codigoAgrupacionMateria\":\"0\"," + Constantes.SALTO_LINEA + "\"codigoMateria\":\"0\","
			+ Constantes.SALTO_LINEA + "\"textos\":\"texto\"" + Constantes.SALTO_LINEA + "\"titulo\":\"texto\""
			+ Constantes.SALTO_LINEA + "\"fechaPublicacionDesde\":\"DD/MM/YYYY\"," + Constantes.SALTO_LINEA
			+ "\"fechaPublicacionHasta\":\"DD/MM/YYYY\"," + Constantes.SALTO_LINEA
			+ "\"vigente\":\"0/1\",  (1=procedimientos con tramites de inicio no caducados)" + Constantes.SALTO_LINEA
			+ "\"telematico\":\"0/1\", (1= procedimientos con trámites telemáticos)" + Constantes.SALTO_LINEA
			+ "\"comun\":\"0/1\", (1= procedimientos comunes)" + Constantes.SALTO_LINEA + "\"codigoSia\":\"codigo\","
			+ Constantes.SALTO_LINEA + "\"estadoSia\":\"A/B\", (A=Alta, B=Baja)" + Constantes.SALTO_LINEA
			+ "\"fechaActualizacionSia\":\"DD/MM/YYYY\"," + Constantes.SALTO_LINEA
			+ "\"codigoTramiteTelematico\":\"codigo\"," + Constantes.SALTO_LINEA + "\"codigoPlataforma\":\"codigo\","
			+ Constantes.SALTO_LINEA + "\"versionTramiteTelematico\":\"version\"" + "}";

	/** codigoUA. **/
	@ApiModelProperty(value = "codigoUA", required = false)
	private Integer codigoUA;

	/** codigoUADir3. **/
	@ApiModelProperty(value = "codigoUADir3", required = false)
	private String codigoUADir3;

	/** buscarEnDescendientesUA. **/
	@ApiModelProperty(value = "buscarEnDescendientesUA", required = false)
	private Integer buscarEnDescendientesUA;

	/** activo. **/
	@ApiModelProperty(value = "activo", required = false)
	private Integer activo;

	/** estadoUA. **/
	@ApiModelProperty(value = "estadoUA- validacion ua", required = false)
	private Integer estadoUA;

	/** codigoAgrupacionHechoVital. **/
	@ApiModelProperty(value = "codigoAgrupacionHechoVital", required = false)
	private Integer codigoAgrupacionHechoVital;

	/** codigoPublicoObjetivo. **/
	@ApiModelProperty(value = "codigoPublicoObjetivo", required = false)
	private Integer codigoPublicoObjetivo;

	/** codigoFamilia. **/
	@ApiModelProperty(value = "codigoFamilia", required = false)
	private Integer codigoFamilia;

	/** codigoAgrupacionMateria. **/
	@ApiModelProperty(value = "codigoAgrupacionMateria", required = false)
	private Integer codigoAgrupacionMateria;

	/** codigoMateria. **/
	@ApiModelProperty(value = "codigoMateria", required = false)
	private Integer codigoMateria;

	/** textos. **/
	@ApiModelProperty(value = "textos", required = false)
	private String textos;

	/** textos. **/
	@ApiModelProperty(value = "titulo", required = false)
	private String titulo;

	/** fechaPublicacionDesde. **/
	@ApiModelProperty(value = "fechaPublicacionDesde", required = false)
	private String fechaPublicacionDesde;

	/** fechaPublicacionHasta. **/
	@ApiModelProperty(value = "fechaPublicacionHasta", required = false)
	private String fechaPublicacionHasta;

	/** vigente. **/
	@ApiModelProperty(value = "vigente", required = false)
	private Integer vigente;

	/** telematico. **/
	@ApiModelProperty(value = "telematico", required = false)
	private Integer telematico;

	/** comun. **/
	@ApiModelProperty(value = "comun", required = false)
	private Integer comun;

	/** codigoSia. **/
	@ApiModelProperty(value = "codigoSia", required = false)
	private Integer codigoSia;

	/** estadoSia. **/
	@ApiModelProperty(value = "estadoSia", required = false)
	private String estadoSia;

	/** fechaActualizacionSia. **/
	@ApiModelProperty(value = "fechaActualizacionSia", required = false)
	private String fechaActualizacionSia;

	/** codigoTramiteTelematico. **/
	@ApiModelProperty(value = "codigoTramiteTelematico", required = false)
	private String codigoTramiteTelematico;

	/** versionTramiteTelematico. **/
	@ApiModelProperty(value = "versionTramiteTelematico", required = false)
	private String versionTramiteTelematico;

	/** codigoPlataforma. **/
	@ApiModelProperty(value = "codigoPlataforma", required = false)
	private String codigoPlataforma;

	public FiltroGenerico toFiltroGenerico() {
		final FiltroGenerico fg = new FiltroGenerico();

		if (this.codigoUA != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_UA, this.codigoUA + "");
		}

		if (this.codigoUADir3 != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_UA_DIR3, this.codigoUADir3 + "");
		}

		if (this.buscarEnDescendientesUA != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_UA_DESCENDIENTES, this.buscarEnDescendientesUA + "");
		}

		if (this.activo != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_ACTIVO, this.activo + "");
		}

		if (this.estadoUA != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_ESTADO_UA, this.estadoUA + "");
		}

		if (this.codigoAgrupacionHechoVital != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_AGRUPACION_HECHO_VITAL,
					this.codigoAgrupacionHechoVital + "");
		}

		if (this.codigoPublicoObjetivo != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_PUBLICO, this.codigoPublicoObjetivo + "");
		}

		if (this.codigoFamilia != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_FAMILIA, this.codigoFamilia + "");
		}

		if (this.codigoAgrupacionMateria != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_AGRUPACION_MATERIA, this.codigoAgrupacionMateria + "");
		}

		if (this.codigoMateria != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_MATERIA, this.codigoMateria + "");
		}

		if (this.textos != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_TEXTOS, this.textos + "");
		}

		if (this.titulo != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_TITULO, this.titulo + "");
		}

		if (this.fechaPublicacionDesde != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_FECHA_PUBLICACION_DESDE, this.fechaPublicacionDesde + "");
		}

		if (this.fechaPublicacionHasta != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_FECHA_PUBLICACION_HASTA, this.fechaPublicacionHasta + "");
		}

		if (this.vigente != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_VIGENTE, this.vigente + "");
		}

		if (this.telematico != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_TELEMATICO, this.telematico + "");
		}

		if (this.comun != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_COMUN, this.comun + "");
		}

		if (this.codigoSia != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_CODIGO_SIA, this.codigoSia + "");
		}

		if (this.estadoSia != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_ESTADO_SIA, this.estadoSia + "");
		}

		if (this.fechaActualizacionSia != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_FECHA_ACTUALIZACION_SIA, this.fechaActualizacionSia + "");
		}

		if (this.codigoTramiteTelematico != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_TRAMITE_TELEMATICO, this.codigoTramiteTelematico + "");
		}

		if (this.versionTramiteTelematico != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_VERSION_TRAMITE_TELEMATICO,
					this.versionTramiteTelematico + "");
		}

		if (this.codigoPlataforma != null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PROCEDIMIENTO_PLATAFORMA, this.codigoPlataforma + "");
		}

		return fg;
	}

	public static FiltroProcediments valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroProcediments> typeRef = new TypeReference<FiltroProcediments>() {
		};
		FiltroProcediments obj;
		try {
			obj = (FiltroProcediments) objectMapper.readValue(json, typeRef);
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
	 * @return the codigoUA
	 */
	public Integer getCodigoUA() {
		return codigoUA;
	}

	/**
	 * @param codigoUA
	 *            the codigoUA to set
	 */
	public void setCodigoUA(final Integer codigoUA) {
		this.codigoUA = codigoUA;
	}

	/**
	 * @return the activo
	 */
	public Integer getActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(final Integer activo) {
		this.activo = activo;
	}

	/**
	 * @return the estadoUA
	 */
	public Integer getEstadoUA() {
		return estadoUA;
	}

	/**
	 * @param estadoUA
	 *            the estadoUA to set
	 */
	public void setEstadoUA(final Integer estadoUA) {
		this.estadoUA = estadoUA;
	}

	/**
	 * @return the codigoAgrupacionHechoVital
	 */
	public Integer getCodigoAgrupacionHechoVital() {
		return codigoAgrupacionHechoVital;
	}

	/**
	 * @param codigoAgrupacionHechoVital
	 *            the codigoAgrupacionHechoVital to set
	 */
	public void setCodigoAgrupacionHechoVital(final Integer codigoAgrupacionHechoVital) {
		this.codigoAgrupacionHechoVital = codigoAgrupacionHechoVital;
	}

	/**
	 * @return the codigoPublicoObjetivo
	 */
	public Integer getCodigoPublicoObjetivo() {
		return codigoPublicoObjetivo;
	}

	/**
	 * @param codigoPublicoObjetivo
	 *            the codigoPublicoObjetivo to set
	 */
	public void setCodigoPublicoObjetivo(final Integer codigoPublicoObjetivo) {
		this.codigoPublicoObjetivo = codigoPublicoObjetivo;
	}

	/**
	 * @return the codigoFamilia
	 */
	public Integer getCodigoFamilia() {
		return codigoFamilia;
	}

	/**
	 * @param codigoFamilia
	 *            the codigoFamilia to set
	 */
	public void setCodigoFamilia(final Integer codigoFamilia) {
		this.codigoFamilia = codigoFamilia;
	}

	/**
	 * @return the codigoAgrupacionMateria
	 */
	public Integer getCodigoAgrupacionMateria() {
		return codigoAgrupacionMateria;
	}

	/**
	 * @param codigoAgrupacionMateria
	 *            the codigoAgrupacionMateria to set
	 */
	public void setCodigoAgrupacionMateria(final Integer codigoAgrupacionMateria) {
		this.codigoAgrupacionMateria = codigoAgrupacionMateria;
	}

	/**
	 * @return the codigoMateria
	 */
	public Integer getCodigoMateria() {
		return codigoMateria;
	}

	/**
	 * @param codigoMateria
	 *            the codigoMateria to set
	 */
	public void setCodigoMateria(final Integer codigoMateria) {
		this.codigoMateria = codigoMateria;
	}

	/**
	 * @return the textos
	 */
	public String getTextos() {
		return textos;
	}

	/**
	 * @param textos
	 *            the textos to set
	 */
	public void setTextos(final String textos) {
		this.textos = textos;
	}

	/**
	 * @return the fechaPublicacionDesde
	 */
	public String getFechaPublicacionDesde() {
		return fechaPublicacionDesde;
	}

	/**
	 * @param fechaPublicacionDesde
	 *            the fechaPublicacionDesde to set
	 */
	public void setFechaPublicacionDesde(final String fechaPublicacionDesde) {
		this.fechaPublicacionDesde = fechaPublicacionDesde;
	}

	/**
	 * @return the fechaPublicacionHasta
	 */
	public String getFechaPublicacionHasta() {
		return fechaPublicacionHasta;
	}

	/**
	 * @param fechaPublicacionHasta
	 *            the fechaPublicacionHasta to set
	 */
	public void setFechaPublicacionHasta(final String fechaPublicacionHasta) {
		this.fechaPublicacionHasta = fechaPublicacionHasta;
	}

	/**
	 * @return the vigente
	 */
	public Integer getVigente() {
		return vigente;
	}

	/**
	 * @param vigente
	 *            the vigente to set
	 */
	public void setVigente(final Integer vigente) {
		this.vigente = vigente;
	}

	/**
	 * @return the telematico
	 */
	public Integer getTelematico() {
		return telematico;
	}

	/**
	 * @param telematico
	 *            the telematico to set
	 */
	public void setTelematico(final Integer telematico) {
		this.telematico = telematico;
	}

	/**
	 * @return the codigoSia
	 */
	public Integer getCodigoSia() {
		return codigoSia;
	}

	/**
	 * @param codigoSia
	 *            the codigoSia to set
	 */
	public void setCodigoSia(final Integer codigoSia) {
		this.codigoSia = codigoSia;
	}

	/**
	 * @return the fechaActualizacionSia
	 */
	public String getFechaActualizacionSia() {
		return fechaActualizacionSia;
	}

	/**
	 * @param fechaActualizacionSia
	 *            the fechaActualizacionSia to set
	 */
	public void setFechaActualizacionSia(final String fechaActualizacionSia) {
		this.fechaActualizacionSia = fechaActualizacionSia;
	}

	/**
	 * @return the estadoSia
	 */
	public String getEstadoSia() {
		return estadoSia;
	}

	/**
	 * @param estadoSia
	 *            the estadoSia to set
	 */
	public void setEstadoSia(final String estadoSia) {
		this.estadoSia = estadoSia;
	}

	/**
	 * @return the codigoTramiteTelematico
	 */
	public String getCodigoTramiteTelematico() {
		return codigoTramiteTelematico;
	}

	/**
	 * @param codigoTramiteTelematico
	 *            the codigoTramiteTelematico to set
	 */
	public void setCodigoTramiteTelematico(final String codigoTramiteTelematico) {
		this.codigoTramiteTelematico = codigoTramiteTelematico;
	}

	/**
	 * @return the versionTramiteTelematico
	 */
	public String getVersionTramiteTelematico() {
		return versionTramiteTelematico;
	}

	/**
	 * @param versionTramiteTelematico
	 *            the versionTramiteTelematico to set
	 */
	public void setVersionTramiteTelematico(final String versionTramiteTelematico) {
		this.versionTramiteTelematico = versionTramiteTelematico;
	}

	/**
	 * @return the buscarEnDescendientesUA
	 */
	public Integer getBuscarEnDescendientesUA() {
		return buscarEnDescendientesUA;
	}

	/**
	 * @param buscarEnDescendientesUA
	 *            the buscarEnDescendientesUA to set
	 */
	public void setBuscarEnDescendientesUA(final Integer buscarEnDescendientesUA) {
		this.buscarEnDescendientesUA = buscarEnDescendientesUA;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the codigoUADir3
	 */
	public String getCodigoUADir3() {
		return codigoUADir3;
	}

	/**
	 * @param codigoUADir3
	 *            the codigoUADir3 to set
	 */
	public void setCodigoUADir3(final String codigoUADir3) {
		this.codigoUADir3 = codigoUADir3;
	}

	/**
	 * @return the comun
	 */
	public Integer getComun() {
		return comun;
	}

	/**
	 * @param comun
	 *            the comun to set
	 */
	public void setComun(final Integer comun) {
		this.comun = comun;
	}

}
