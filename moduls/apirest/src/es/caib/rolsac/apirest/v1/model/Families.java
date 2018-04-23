package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.UnidadAdministrativa;

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
@ApiModel(value = Constantes.ENTIDAD_FAMILIA, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_FAMILIA)
public class Families extends EntidadBase {
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion", required = false)
	private String descripcion;

	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;

	/** idioma 
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
**/
		
	public Families (org.ibit.rol.sac.model.Familia ahv, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( ahv, urlBase, idioma, hateoasEnabled);
	}
	
	public Families() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		
	}


	public static Families valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Families> typeRef = new TypeReference<Families>() {
		};
		Families obj;
		try {
			obj = (Families) objectMapper.readValue(json, typeRef);
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
	 * @return the idioma
	 
	public java.lang.String getIdioma() {
		return idioma;
	}*/

	/**
	 * @param idioma the idioma to set
	 
	public void setIdioma(java.lang.String idioma) {
		this.idioma = idioma;
	}*/


	
}
