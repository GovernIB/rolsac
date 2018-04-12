package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Idioma.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "AgrupacioMateries", description = "Definición de la clase AgrupacioMateries")
public class AgrupacioMateries extends EntidadBase {
	 
	/** codigoEstandar. **/
	@ApiModelProperty(value = "codigoEstandar", dataType = "java.lang.String", required = false)
	private String codigoEstandar;
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", dataType = "java.lang.Long", required = false)
	private long codigo;
	
	/** nombre **/
	@ApiModelProperty(value = "nombre", dataType = "java.lang.Long", required = false)
	private String nombre;
	

	/** contenido **/
	@ApiModelProperty(value = "seccion", dataType = "java.lang.Long", required = false)
	private Long seccion;
	

	public AgrupacioMateries (org.ibit.rol.sac.model.AgrupacionMateria ahv, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( ahv, urlBase, idioma, hateoasEnabled);
	}
	
	public AgrupacioMateries() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		this.addLink(this.seccion, Constantes.ENTIDAD_SECCION, Constantes.URL_SECCION, urlBase,"seccion" );
	}


	public static AgrupacioMateries valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<AgrupacioMateries> typeRef = new TypeReference<AgrupacioMateries>() {
		};
		AgrupacioMateries obj;
		try {
			obj = (AgrupacioMateries) objectMapper.readValue(json, typeRef);
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
	 * @return the codigoEstandar
	 */
	public String getCodigoEstandar() {
		return codigoEstandar;
	}

	/**
	 * @param codigoEstandar the codigoEstandar to set
	 */
	public void setCodigoEstandar(String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the seccion
	 */
	public Long getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}
	
}
