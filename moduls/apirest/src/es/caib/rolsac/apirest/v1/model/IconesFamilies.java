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
 * Iconos familias.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_ICONO_FAMILIA, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_ICONO_FAMILIA)
public class IconesFamilies extends EntidadBase {
	 
	
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** familia **/
	@ApiModelProperty(value = "link_familia", required = false)
	private Link link_familia;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long familia;
	
	
	/** icono **/
	@ApiModelProperty(value = "link_icono", required = false)
	private Link link_icono;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long icono;


	
	/** perfil **/
	@ApiModelProperty(value = "link_perfil", required = false)
	private Link link_perfil;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long perfil;

	
		
	public IconesFamilies (org.ibit.rol.sac.model.IconoFamilia elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public IconesFamilies() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_icono = this.generaLinkArchivo(this.icono, urlBase , null );
		link_familia = this.generaLink(this.familia, Constantes.ENTIDAD_FAMILIA, Constantes.URL_FAMILIA, urlBase,null );
		link_perfil = this.generaLink(this.perfil, Constantes.ENTIDAD_PERFIL, Constantes.URL_PERFIL, urlBase,null );	
	}


	public static IconesFamilies valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<IconesFamilies> typeRef = new TypeReference<IconesFamilies>() {
		};
		IconesFamilies obj;
		try {
			obj = (IconesFamilies) objectMapper.readValue(json, typeRef);
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
	 * @return the familia
	 */
	@XmlTransient
	public Long getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Long familia) {
		this.familia = familia;
	}

	/**
	 * @return the icono
	 */
	@XmlTransient
	public Long getIcono() {
		return icono;
	}

	/**
	 * @param icono the icono to set
	 */
	public void setIcono(Long icono) {
		this.icono = icono;
	}

	/**
	 * @return the perfil
	 */
	@XmlTransient
	public Long getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(Long perfil) {
		this.perfil = perfil;
	}




	
}
