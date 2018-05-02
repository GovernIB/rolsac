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
 * Fitxes.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_FICHA, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_FICHA)
public class plantillaVacia extends EntidadBase {
	 

	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** baner **/
	@ApiModelProperty(value = "link_archivo", required = false)
	private Link link_archivo;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long archivo;
	
		
	public plantillaVacia (org.ibit.rol.sac.model.Ficha elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public plantillaVacia() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_archivo = this.generaLinkArchivo(this.archivo, urlBase , null );
	}


	public static plantillaVacia valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<plantillaVacia> typeRef = new TypeReference<plantillaVacia>() {
		};
		plantillaVacia obj;
		try {
			obj = (plantillaVacia) objectMapper.readValue(json, typeRef);
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

}
