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
 * Silencios.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "Silencio", description = Constantes.TXT_DEFINICION_CLASE + "Silencio")
public class Silencis extends EntidadBase {
	 
	public Silencis(org.ibit.rol.sac.model.SilencioAdm tr, String urlBase,String idioma,boolean hateoasEnabled) {
		super(tr, urlBase, idioma, hateoasEnabled);		
	}
	
	public Silencis() {
		super();
	}

	/** codigo. **/
	@ApiModelProperty(value = "codigo")
	private Long codigo;

	/** cargoF. **/
	@ApiModelProperty(value = "descripcion")
	private String descripcion;

	/** cargoM. **/
	@ApiModelProperty(value = "nombre")
	private String nombre;
	
	
	public static Silencis valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Silencis> typeRef = new TypeReference<Silencis>() {
		};
		Silencis obj;
		try {
			obj = (Silencis) objectMapper.readValue(json, typeRef);
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
	protected void generaLinks(String urlBase) {
		// TODO Auto-generated method stub
		
	}

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
	
}
