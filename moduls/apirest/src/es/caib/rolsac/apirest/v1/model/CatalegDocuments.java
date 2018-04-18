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
 * CatalegDocuments.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_CATALOGO_DOCUMENTOS, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_CATALOGO_DOCUMENTOS)
public class CatalegDocuments extends EntidadBase {
	 
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private Long codigo;
	
	/** admResponsable **/
	@ApiModelProperty(value = "admResponsable", required = false)
	private Integer admResponsable;
	
	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;
	
	/** distribComp **/
	@ApiModelProperty(value = "orden", required = false)
	private Long ordre;	
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion", required = false)
	private String descripcion;
	

	public CatalegDocuments (org.ibit.rol.sac.model.CatalegDocuments ahv, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( ahv, urlBase, idioma, hateoasEnabled);
	}
	
	public CatalegDocuments() {
		super();
	}
	
	public static CatalegDocuments valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<CatalegDocuments> typeRef = new TypeReference<CatalegDocuments>() {
		};
		CatalegDocuments obj;
		try {
			obj = (CatalegDocuments) objectMapper.readValue(json, typeRef);
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
	public void generaLinks(String urlBase) {}
	
	@Override
	protected void addSetersInvalidos() {}

	@Override
	public void setId(Long codigo) {
		this.codigo = codigo;		
	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the admResponsable
	 */
	public Integer getAdmResponsable() {
		return admResponsable;
	}

	/**
	 * @param admResponsable the admResponsable to set
	 */
	public void setAdmResponsable(Integer admResponsable) {
		this.admResponsable = admResponsable;
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
	 * @return the ordre
	 */
	public Long getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
}
