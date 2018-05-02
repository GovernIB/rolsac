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
 * FitxesUA.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_FICHAUA, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_FICHAUA)
public class FitxesUA extends EntidadBase {
	 

	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** orden **/
	@ApiModelProperty(value = "orden", required = false)
    private int orden;
	
	/** ordenseccion **/
	@ApiModelProperty(value = "ordenseccion", required = false)
    private int ordenseccion;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** seccion **/
	@ApiModelProperty(value = "link_seccion", required = false)
	private Link link_seccion;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long seccion;
	
	/** unidadAdministrativa **/
	@ApiModelProperty(value = "link_unidadAdministrativa", required = false)
	private Link link_unidadAdministrativa;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long unidadAdministrativa;
	
	/** ficha **/
	@ApiModelProperty(value = "link_ficha", required = false)
	private Link link_ficha;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long ficha;
	
		
	public FitxesUA (org.ibit.rol.sac.model.FichaUA elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public FitxesUA() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		this.link_seccion= this.generaLink(this.seccion, Constantes.ENTIDAD_SECCION, Constantes.URL_SECCION, urlBase,null );
		this.link_unidadAdministrativa= this.generaLink(this.unidadAdministrativa, Constantes.ENTIDAD_UA, Constantes.URL_UA, urlBase,null );
		this.link_ficha= this.generaLink(this.ficha, Constantes.ENTIDAD_FICHA, Constantes.URL_FICHA, urlBase,null );		
	}


	public static FitxesUA valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FitxesUA> typeRef = new TypeReference<FitxesUA>() {
		};
		FitxesUA obj;
		try {
			obj = (FitxesUA) objectMapper.readValue(json, typeRef);
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
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(int orden) {
		this.orden = orden;
	}

	/**
	 * @return the ordenseccion
	 */
	public int getOrdenseccion() {
		return ordenseccion;
	}

	/**
	 * @param ordenseccion the ordenseccion to set
	 */
	public void setOrdenseccion(int ordenseccion) {
		this.ordenseccion = ordenseccion;
	}

	/**
	 * @return the link_seccion
	 */
	public Link getLink_seccion() {
		return link_seccion;
	}

	/**
	 * @param link_seccion the link_seccion to set
	 */
	public void setLink_seccion(Link link_seccion) {
		this.link_seccion = link_seccion;
	}

	/**
	 * @return the seccion
	 */
	@XmlTransient
	public Long getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return the link_unidadAdministrativa
	 */
	public Link getLink_unidadAdministrativa() {
		return link_unidadAdministrativa;
	}

	/**
	 * @param link_unidadAdministrativa the link_unidadAdministrativa to set
	 */
	public void setLink_unidadAdministrativa(Link link_unidadAdministrativa) {
		this.link_unidadAdministrativa = link_unidadAdministrativa;
	}

	/**
	 * @return the unidadAdministrativa
	 */
	@XmlTransient
	public Long getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	/**
	 * @param unidadAdministrativa the unidadAdministrativa to set
	 */
	public void setUnidadAdministrativa(Long unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

	/**
	 * @return the link_ficha
	 */
	public Link getLink_ficha() {
		return link_ficha;
	}

	/**
	 * @param link_ficha the link_ficha to set
	 */
	public void setLink_ficha(Link link_ficha) {
		this.link_ficha = link_ficha;
	}

	/**
	 * @return the ficha
	 */
	@XmlTransient
	public Long getFicha() {
		return ficha;
	}

	/**
	 * @param ficha the ficha to set
	 */
	public void setFicha(Long ficha) {
		this.ficha = ficha;
	}

}
