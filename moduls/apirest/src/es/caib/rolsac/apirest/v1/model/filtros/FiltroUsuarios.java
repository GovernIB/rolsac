package es.caib.rolsac.apirest.v1.model.filtros;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * FiltroUsuarios.
 * 
 * @author Indra
 *
 * Filtro (donde se definen los filtros a usar): 
 */
@XmlRootElement
@ApiModel(value = "Filtro Usuarios", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_USUARIOS)
public class FiltroUsuarios {
	
	public static final String SAMPLE = "{\"codigoUA\":\"0\"}"; 
	
	 
	/** codigoAgrupacioFetVital. **/
	@ApiModelProperty(value = "Código UA", required = false)
	private Integer codigoUA;
	
		
	public static FiltroUsuarios valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroUsuarios> typeRef = new TypeReference<FiltroUsuarios>() {
		};
		FiltroUsuarios obj;
		try {
			obj = (FiltroUsuarios) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_USUARIOS_UA, this.codigoUA+"");
		}
			
		return fg;
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


	/**
	 * @return the codigoUA
	 */
	public Integer getCodigoUA() {
		return codigoUA;
	}


	/**
	 * @param codigoUA the codigoUA to set
	 */
	public void setCodigoUA(Integer codigoUA) {
		this.codigoUA = codigoUA;
	}


}
