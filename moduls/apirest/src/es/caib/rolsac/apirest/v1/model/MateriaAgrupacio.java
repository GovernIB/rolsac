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
 * Materias agrupaciones.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_MATERIA_AGRUPACION, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_MATERIA_AGRUPACION)
public class MateriaAgrupacio extends EntidadBase {
	 

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	/** orden **/
	@ApiModelProperty(value = "orden", required = false)
	private Integer orden;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** materia **/
	@ApiModelProperty(value = "link_materia", required = false)
	private Link link_materia;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long materia;

	
	/** agrupacion **/
	@ApiModelProperty(value = "link_agrupacion", required = false)
	private Link link_agrupacion;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long agrupacion;
		
	
		
	public MateriaAgrupacio (org.ibit.rol.sac.model.MateriaAgrupacionM elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public MateriaAgrupacio() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		
		link_agrupacion = this.generaLink(this.agrupacion, Constantes.ENTIDAD_AGRUPACIO_MATERIES, Constantes.URL_AGRUPACIO_MATERIES, urlBase,null );	
		link_materia = this.generaLink(this.materia, Constantes.ENTIDAD_MATERIA, Constantes.URL_MATERIA, urlBase,null );
	
	}


	public static MateriaAgrupacio valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<MateriaAgrupacio> typeRef = new TypeReference<MateriaAgrupacio>() {
		};
		MateriaAgrupacio obj;
		try {
			obj = (MateriaAgrupacio) objectMapper.readValue(json, typeRef);
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
	 * @return the agrupacion
	 */
	@XmlTransient
	public Long getAgrupacion() {
		return agrupacion;
	}

	/**
	 * @param agrupacion the agrupacion to set
	 */
	public void setAgrupacion(Long agrupacion) {
		this.agrupacion = agrupacion;
	}

	/**
	 * @return the materia
	 */
	@XmlTransient
	public Long getMateria() {
		return materia;
	}

	/**
	 * @param materia the materia to set
	 */
	public void setMateria(Long materia) {
		this.materia = materia;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the link_materia
	 */
	public Link getLink_materia() {
		return link_materia;
	}

	/**
	 * @param link_materia the link_materia to set
	 */
	public void setLink_materia(Link link_materia) {
		this.link_materia = link_materia;
	}

	/**
	 * @return the link_agrupacion
	 */
	public Link getLink_agrupacion() {
		return link_agrupacion;
	}

	/**
	 * @param link_agrupacion the link_agrupacion to set
	 */
	public void setLink_agrupacion(Link link_agrupacion) {
		this.link_agrupacion = link_agrupacion;
	}


	
}
