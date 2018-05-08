package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Normatives.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_NORMATIVAS, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_NORMATIVAS)
public class Normatives extends EntidadBase {
	 

	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** codiVuds **/
	@ApiModelProperty(value = "codiVuds", required = false)
    private java.lang.String codiVuds;//

	/** descCodiVuds **/
	@ApiModelProperty(value = "descCodiVuds", required = false)
    private java.lang.String descCodiVuds;//

	/** enlace **/
	@ApiModelProperty(value = "enlace", required = false)
    private java.lang.String enlace;//

	/** fecha **/
	@ApiModelProperty(value = "fecha", required = false)
    private java.util.Calendar fecha;//

	/** fechaBoletin **/
	@ApiModelProperty(value = "fechaBoletin", required = false)
    private java.util.Calendar fechaBoletin;//

	/** observaciones **/
	@ApiModelProperty(value = "observaciones", required = false)
    private java.lang.String observaciones;//

	/** registro **/
	@ApiModelProperty(value = "registro", required = false)
    private java.lang.Long registro;//

	/** responsable **/
	@ApiModelProperty(value = "responsable", required = false)
    private java.lang.String responsable;//

	/** seccion **/
	@ApiModelProperty(value = "seccion", required = false)
    private java.lang.String seccion;//

	/** tipo **/
	@ApiModelProperty(value = "tipo", required = false)
    private java.lang.Long tipo;//

	/** titulo **/
	@ApiModelProperty(value = "titulo", required = false)
    private java.lang.String titulo;//

	/** validacion **/
	@ApiModelProperty(value = "validacion", required = false)
    private java.lang.Integer validacion;//

	/** datosValidos **/
	@ApiModelProperty(value = "datosValidos", required = false)
    private java.lang.Boolean datosValidos;//
    
	/** numero **/
	@ApiModelProperty(value = "numero", required = false)
    private java.lang.Long numero;
    
	/** numNormativa **/
	@ApiModelProperty(value = "numNormativa", required = false)
    private java.lang.String numNormativa;
    
	/** ley **/
	@ApiModelProperty(value = "ley", required = false)
    private java.lang.String ley;
    
 /*   
    private String apartado;
    private Integer paginaInicial;
    private Integer paginaFinal;
    private java.lang.Long unidadAdministrativa;
    
   */ 

	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** boletin **/
	@ApiModelProperty(value = "link_boletin", required = false)
	private Link link_boletin;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long boletin;
	
		
	public Normatives (org.ibit.rol.sac.model.Normativa elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Normatives() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_boletin = this.generaLink(this.boletin,Constantes.ENTIDAD_BOLETINES,Constantes.URL_BOLETINES, urlBase , null );
	}


	public static Normatives valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Normatives> typeRef = new TypeReference<Normatives>() {
		};
		Normatives obj;
		try {
			obj = (Normatives) objectMapper.readValue(json, typeRef);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(Long codigo) {
		this.codigo = codigo;		
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codiVuds
	 */
	public java.lang.String getCodiVuds() {
		return codiVuds;
	}

	/**
	 * @param codiVuds the codiVuds to set
	 */
	public void setCodiVuds(java.lang.String codiVuds) {
		this.codiVuds = codiVuds;
	}

	/**
	 * @return the descCodiVuds
	 */
	public java.lang.String getDescCodiVuds() {
		return descCodiVuds;
	}

	/**
	 * @param descCodiVuds the descCodiVuds to set
	 */
	public void setDescCodiVuds(java.lang.String descCodiVuds) {
		this.descCodiVuds = descCodiVuds;
	}

	/**
	 * @return the enlace
	 */
	public java.lang.String getEnlace() {
		return enlace;
	}

	/**
	 * @param enlace the enlace to set
	 */
	public void setEnlace(java.lang.String enlace) {
		this.enlace = enlace;
	}

	/**
	 * @return the fecha
	 */
	public java.util.Calendar getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(java.util.Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the fechaBoletin
	 */
	public java.util.Calendar getFechaBoletin() {
		return fechaBoletin;
	}

	/**
	 * @param fechaBoletin the fechaBoletin to set
	 */
	public void setFechaBoletin(java.util.Calendar fechaBoletin) {
		this.fechaBoletin = fechaBoletin;
	}

	/**
	 * @return the observaciones
	 */
	public java.lang.String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(java.lang.String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the registro
	 */
	public java.lang.Long getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(java.lang.Long registro) {
		this.registro = registro;
	}

	/**
	 * @return the responsable
	 */
	public java.lang.String getResponsable() {
		return responsable;
	}

	/**
	 * @param responsable the responsable to set
	 */
	public void setResponsable(java.lang.String responsable) {
		this.responsable = responsable;
	}

	/**
	 * @return the seccion
	 */
	public java.lang.String getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(java.lang.String seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return the tipo
	 */
	public java.lang.Long getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(java.lang.Long tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the titulo
	 */
	public java.lang.String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(java.lang.String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the validacion
	 */
	public java.lang.Integer getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion the validacion to set
	 */
	public void setValidacion(java.lang.Integer validacion) {
		this.validacion = validacion;
	}

	/**
	 * @return the datosValidos
	 */
	public java.lang.Boolean getDatosValidos() {
		return datosValidos;
	}

	/**
	 * @param datosValidos the datosValidos to set
	 */
	public void setDatosValidos(java.lang.Boolean datosValidos) {
		this.datosValidos = datosValidos;
	}

	/**
	 * @return the numero
	 */
	public java.lang.Long getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(java.lang.Long numero) {
		this.numero = numero;
	}

	/**
	 * @return the numNormativa
	 */
	public java.lang.String getNumNormativa() {
		return numNormativa;
	}

	/**
	 * @param numNormativa the numNormativa to set
	 */
	public void setNumNormativa(java.lang.String numNormativa) {
		this.numNormativa = numNormativa;
	}

	/**
	 * @return the ley
	 */
	public java.lang.String getLey() {
		return ley;
	}

	/**
	 * @param ley the ley to set
	 */
	public void setLey(java.lang.String ley) {
		this.ley = ley;
	}

	/**
	 * @return the link_boletin
	 */
	public Link getLink_boletin() {
		return link_boletin;
	}

	/**
	 * @param link_boletin the link_boletin to set
	 */
	public void setLink_boletin(Link link_boletin) {
		this.link_boletin = link_boletin;
	}

	/**
	 * @return the boletin
	 */
	@XmlTransient
	public Long getBoletin() {
		return boletin;
	}

	/**
	 * @param boletin the boletin to set
	 */
	public void setBoletin(Long boletin) {
		this.boletin = boletin;
	}

}
