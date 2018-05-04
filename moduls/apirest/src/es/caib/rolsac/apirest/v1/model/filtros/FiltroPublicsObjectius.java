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
@ApiModel(value = "Filtro Publicos Objetivo", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_PUBLICO)
public class FiltroPublicsObjectius {
	
	public static final String SAMPLE = "{\"codigos\":\"0,1,2,3\"}"; 
	
	 
	/** codigoUA. **/
	@ApiModelProperty(value = "Lista separada por comas de los publicos objetivo", required = false)
	private String codigosPO;
	
	
		
	public static FiltroPublicsObjectius valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroPublicsObjectius> typeRef = new TypeReference<FiltroPublicsObjectius>() {
		};
		FiltroPublicsObjectius obj;
		try {
			obj = (FiltroPublicsObjectius) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.codigosPO!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_PUBLICO_LISTA_CODIGOS, this.codigosPO+"");
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
	 * @return the codigosPO
	 */
	public String getCodigosPO() {
		return codigosPO;
	}


	/**
	 * @param codigosPO the codigosPO to set
	 */
	public void setCodigosPO(String codigosPO) {
		this.codigosPO = codigosPO;
	}


	

}
