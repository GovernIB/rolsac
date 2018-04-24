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
 * FiltroEdificios.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "Filtro Edificios", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_EDIFICIO)
public class FiltroEdificis {
	
	public static final String SAMPLE = "{\"codigoUA\":\"0\"}"; 
	
	 
	/** codigoUA. **/
	@ApiModelProperty(value = "Código unidad administrativa", required = false)
	private Integer codigoUA;
	
	
		
	public static FiltroEdificis valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroEdificis> typeRef = new TypeReference<FiltroEdificis>() {
		};
		FiltroEdificis obj;
		try {
			obj = (FiltroEdificis) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_EDIFICIO_UA, this.codigoUA+"");
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
