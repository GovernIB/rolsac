package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.ibit.rol.sac.model.Taxa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Tramits.
 *
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_TRAMITE, description = Constantes.TXT_DEFINICION_CLASE
		+ Constantes.ENTIDAD_TRAMITE)
public class Tramits extends EntidadBase {

	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	@ApiModelProperty(value = "codiVuds", required = false)
	private java.lang.String codiVuds;

	@ApiModelProperty(value = "dataActualitzacio", required = false)
	private java.util.Calendar dataActualitzacio;

	@ApiModelProperty(value = "dataActualitzacioVuds", required = false)
	private java.lang.String dataActualitzacioVuds;

	@ApiModelProperty(value = "dataCaducitat", required = false)
	private java.util.Calendar dataCaducitat;

	@ApiModelProperty(value = "dataPublicacio", required = false)
	private java.util.Calendar dataPublicacio;

	@ApiModelProperty(value = "dataInici", required = false)
	private java.util.Calendar dataInici;

	@ApiModelProperty(value = "dataTancament", required = false)
	private java.util.Calendar dataTancament;

	@ApiModelProperty(value = "descCodiVuds", required = false)
	private java.lang.String descCodiVuds;

	@ApiModelProperty(value = "documentacion", required = false)
	private java.lang.String documentacion;

	@ApiModelProperty(value = "fase", required = false)
	private java.lang.Integer fase;

	@ApiModelProperty(value = "idTraTel", required = false)
	private java.lang.String idTraTel;

	@ApiModelProperty(value = "lugar", required = false)
	private java.lang.String lugar;

	@ApiModelProperty(value = "nombre", required = false)
	private java.lang.String nombre;

	@ApiModelProperty(value = "observaciones", required = false)
	private java.lang.String observaciones;

	@ApiModelProperty(value = "observaciones", required = false)
	private java.lang.Long orden;

	@ApiModelProperty(value = "plazos", required = false)
	private java.lang.String plazos;

	@ApiModelProperty(value = "requisits", required = false)
	private java.lang.String requisits;

	@ApiModelProperty(value = "urlExterna", required = false)
	private java.lang.String urlExterna;

	@ApiModelProperty(value = "validacio", required = false)
	private java.lang.Long validacio;

	@ApiModelProperty(value = "versio", required = false)
	private java.lang.Integer versio;

	@ApiModelProperty(value = "tasas", required = false)
	private ArrayList<Taxes> tasas;

	@ApiModelProperty(value = "presencial", required = false)
	private boolean presencial;

	@ApiModelProperty(value = "telematico", required = false)
	private boolean telematico;

	@ApiModelProperty(value = "plataforma", required = false)
	private Plataformas plataforma;

	@ApiModelProperty(value = "parametros", required = false)
	private java.lang.String parametros;

	// -- LINKS--//
	// -- se duplican las entidades para poder generar la clase link en funcion de
	// la propiedad principal (sin "link_")
	/**  **/
	@ApiModelProperty(value = "link_organCompetent", required = false)
	private Link link_organCompetent;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private java.lang.Long organCompetent;

	@ApiModelProperty(value = "link_procedimiento", required = false)
	private Link link_procedimiento;
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private java.lang.Long procedimiento;

	public Tramits(final org.ibit.rol.sac.model.Tramite elem, final String urlBase, final String idioma,
			final boolean hateoasEnabled) {
		super(elem, urlBase, idioma, hateoasEnabled);

		// rellenamos las tasas
		if (elem.getTaxes() != null && elem.getTaxes().size() > 0) {
			if (tasas == null) {
				tasas = new ArrayList<Taxes>();
			}
			for (final Taxa t : elem.getTaxes()) {
				final Taxes tx = new Taxes(t, urlBase, idioma, hateoasEnabled);
				if (tx != null) {
					tasas.add(tx);
				}
			}
		}

		// copiamos los datos que no tienen la misma estructura:
		if (elem.getPlataforma() != null) {
			this.plataforma = new Plataformas(elem.getPlataforma(), urlBase, idioma, hateoasEnabled);
		}
	}

	public Tramits() {
		super();
	}

	@Override
	public void generaLinks(final String urlBase) {
		link_organCompetent = this.generaLink(this.organCompetent, Constantes.ENTIDAD_UA, Constantes.URL_UA, urlBase,
				null);
		link_procedimiento = this.generaLink(this.procedimiento, Constantes.ENTIDAD_PROCEDIMIENTO,
				Constantes.URL_PROCEDIMIENTO, urlBase, null);
	}

	public static Tramits valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Tramits> typeRef = new TypeReference<Tramits>() {
		};
		Tramits obj;
		try {
			obj = (Tramits) objectMapper.readValue(json, typeRef);
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

	@Override
	protected void addSetersInvalidos() {
		if (!SETTERS_INVALIDS.contains("setPlataforma")) {
			SETTERS_INVALIDS.add("setPlataforma");
		}
	}

	@Override
	public void setId(final Long codigo) {
		this.codigo = codigo;
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
	 * @return the codiVuds
	 */
	public java.lang.String getCodiVuds() {
		return codiVuds;
	}

	/**
	 * @param codiVuds
	 *            the codiVuds to set
	 */
	public void setCodiVuds(final java.lang.String codiVuds) {
		this.codiVuds = codiVuds;
	}

	/**
	 * @return the dataActualitzacio
	 */
	public java.util.Calendar getDataActualitzacio() {
		return dataActualitzacio;
	}

	/**
	 * @param dataActualitzacio
	 *            the dataActualitzacio to set
	 */
	public void setDataActualitzacio(final java.util.Calendar dataActualitzacio) {
		this.dataActualitzacio = dataActualitzacio;
	}

	/**
	 * @return the dataActualitzacioVuds
	 */
	public java.lang.String getDataActualitzacioVuds() {
		return dataActualitzacioVuds;
	}

	/**
	 * @param dataActualitzacioVuds
	 *            the dataActualitzacioVuds to set
	 */
	public void setDataActualitzacioVuds(final java.lang.String dataActualitzacioVuds) {
		this.dataActualitzacioVuds = dataActualitzacioVuds;
	}

	/**
	 * @return the dataCaducitat
	 */
	public java.util.Calendar getDataCaducitat() {
		return dataCaducitat;
	}

	/**
	 * @param dataCaducitat
	 *            the dataCaducitat to set
	 */
	public void setDataCaducitat(final java.util.Calendar dataCaducitat) {
		this.dataCaducitat = dataCaducitat;
	}

	/**
	 * @return the dataPublicacio
	 */
	public java.util.Calendar getDataPublicacio() {
		return dataPublicacio;
	}

	/**
	 * @param dataPublicacio
	 *            the dataPublicacio to set
	 */
	public void setDataPublicacio(final java.util.Calendar dataPublicacio) {
		this.dataPublicacio = dataPublicacio;
	}

	/**
	 * @return the dataInici
	 */
	public java.util.Calendar getDataInici() {
		return dataInici;
	}

	/**
	 * @param dataInici
	 *            the dataInici to set
	 */
	public void setDataInici(final java.util.Calendar dataInici) {
		this.dataInici = dataInici;
	}

	/**
	 * @return the dataTancament
	 */
	public java.util.Calendar getDataTancament() {
		return dataTancament;
	}

	/**
	 * @param dataTancament
	 *            the dataTancament to set
	 */
	public void setDataTancament(final java.util.Calendar dataTancament) {
		this.dataTancament = dataTancament;
	}

	/**
	 * @return the descCodiVuds
	 */
	public java.lang.String getDescCodiVuds() {
		return descCodiVuds;
	}

	/**
	 * @param descCodiVuds
	 *            the descCodiVuds to set
	 */
	public void setDescCodiVuds(final java.lang.String descCodiVuds) {
		this.descCodiVuds = descCodiVuds;
	}

	/**
	 * @return the documentacion
	 */
	public java.lang.String getDocumentacion() {
		return documentacion;
	}

	/**
	 * @param documentacion
	 *            the documentacion to set
	 */
	public void setDocumentacion(final java.lang.String documentacion) {
		this.documentacion = documentacion;
	}

	/**
	 * @return the fase
	 */
	public java.lang.Integer getFase() {
		return fase;
	}

	/**
	 * @param fase
	 *            the fase to set
	 */
	public void setFase(final java.lang.Integer fase) {
		this.fase = fase;
	}

	/**
	 * @return the idTraTel
	 */
	public java.lang.String getIdTraTel() {
		return idTraTel;
	}

	/**
	 * @param idTraTel
	 *            the idTraTel to set
	 */
	public void setIdTraTel(final java.lang.String idTraTel) {
		this.idTraTel = idTraTel;
	}

	/**
	 * @return the lugar
	 */
	public java.lang.String getLugar() {
		return lugar;
	}

	/**
	 * @param lugar
	 *            the lugar to set
	 */
	public void setLugar(final java.lang.String lugar) {
		this.lugar = lugar;
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
	 * @return the observaciones
	 */
	public java.lang.String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(final java.lang.String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the orden
	 */
	public java.lang.Long getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(final java.lang.Long orden) {
		this.orden = orden;
	}

	/**
	 * @return the plazos
	 */
	public java.lang.String getPlazos() {
		return plazos;
	}

	/**
	 * @param plazos
	 *            the plazos to set
	 */
	public void setPlazos(final java.lang.String plazos) {
		this.plazos = plazos;
	}

	/**
	 * @return the requisits
	 */
	public java.lang.String getRequisits() {
		return requisits;
	}

	/**
	 * @param requisits
	 *            the requisits to set
	 */
	public void setRequisits(final java.lang.String requisits) {
		this.requisits = requisits;
	}

	/**
	 * @return the urlExterna
	 */
	public java.lang.String getUrlExterna() {
		return urlExterna;
	}

	/**
	 * @param urlExterna
	 *            the urlExterna to set
	 */
	public void setUrlExterna(final java.lang.String urlExterna) {
		this.urlExterna = urlExterna;
	}

	/**
	 * @return the validacio
	 */
	public java.lang.Long getValidacio() {
		return validacio;
	}

	/**
	 * @param validacio
	 *            the validacio to set
	 */
	public void setValidacio(final java.lang.Long validacio) {
		this.validacio = validacio;
	}

	/**
	 * @return the versio
	 */
	public java.lang.Integer getVersio() {
		return versio;
	}

	/**
	 * @param versio
	 *            the versio to set
	 */
	public void setVersio(final java.lang.Integer versio) {
		this.versio = versio;
	}

	/**
	 * @return the tasas
	 */
	public ArrayList<Taxes> getTasas() {
		return tasas;
	}

	/**
	 * @param tasas
	 *            the tasas to set
	 */
	public void setTasas(final ArrayList<Taxes> tasas) {
		this.tasas = tasas;
	}

	/**
	 * @return the link_organCompetent
	 */
	public Link getLink_organCompetent() {
		return link_organCompetent;
	}

	/**
	 * @param link_organCompetent
	 *            the link_organCompetent to set
	 */
	public void setLink_organCompetent(final Link link_organCompetent) {
		this.link_organCompetent = link_organCompetent;
	}

	/**
	 * @return the organCompetent
	 */
	@XmlTransient
	public java.lang.Long getOrganCompetent() {
		return organCompetent;
	}

	/**
	 * @param organCompetent
	 *            the organCompetent to set
	 */
	public void setOrganCompetent(final java.lang.Long organCompetent) {
		this.organCompetent = organCompetent;
	}

	/**
	 * @return the link_procedimiento
	 */
	public Link getLink_procedimiento() {
		return link_procedimiento;
	}

	/**
	 * @param link_procedimiento
	 *            the link_procedimiento to set
	 */
	public void setLink_procedimiento(final Link link_procedimiento) {
		this.link_procedimiento = link_procedimiento;
	}

	/**
	 * @return the procedimiento
	 */
	@XmlTransient
	public java.lang.Long getProcedimiento() {
		return procedimiento;
	}

	/**
	 * @param procedimiento
	 *            the procedimiento to set
	 */
	public void setProcedimiento(final java.lang.Long procedimiento) {
		this.procedimiento = procedimiento;
	}

	/**
	 * @return the presencial
	 */
	public boolean isPresencial() {
		return presencial;
	}

	/**
	 * @param presencial
	 *            the presencial to set
	 */
	public void setPresencial(final boolean presencial) {
		this.presencial = presencial;
	}

	/**
	 * @return the telematico
	 */
	public boolean isTelematico() {
		return telematico;
	}

	/**
	 * @param telematico
	 *            the telematico to set
	 */
	public void setTelematico(final boolean telematico) {
		this.telematico = telematico;
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
