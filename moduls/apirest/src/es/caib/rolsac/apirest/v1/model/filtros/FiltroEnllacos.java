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
 * FiltroEnlaces.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "Filtro Enlaces", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_ENLACE)
public class FiltroEnllacos {
	
	public static final String SAMPLE = "{\"codigoFicha\":\"0\"}"; 
	
	 
	/** codigoFicha. **/
	@ApiModelProperty(value = "Código ficha", required = false)
	private Integer codigoFicha;
	
		
	public static FiltroEnllacos valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroEnllacos> typeRef = new TypeReference<FiltroEnllacos>() {
		};
		FiltroEnllacos obj;
		try {
			obj = (FiltroEnllacos) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.codigoFicha!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_ENLACES_FICHA, this.codigoFicha+"");
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
	 * @return the codigoFicha
	 */
	public Integer getCodigoFicha() {
		return codigoFicha;
	}


	/**
	 * @param codigoFicha the codigoFicha to set
	 */
	public void setCodigoFicha(Integer codigoFicha) {
		this.codigoFicha = codigoFicha;
	}

}
