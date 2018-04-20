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
 * FiltroDocumentsTramits.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "Filtro documentos normativas", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_DOCUMENTOS_NORMATIVAS)
public class FiltroDocumentsNormatives {
	
	public static final String SAMPLE = "{\"normativa\":\"0\"}"; 
	
	 
	/** tramite. **/
	@ApiModelProperty(value = "Código normativa", required = false)
	private Integer normativa;
	
		
	public static FiltroDocumentsNormatives valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroDocumentsNormatives> typeRef = new TypeReference<FiltroDocumentsNormatives>() {
		};
		FiltroDocumentsNormatives obj;
		try {
			obj = (FiltroDocumentsNormatives) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.normativa!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_DOC_NORMATIVA_NORMATIVA, this.normativa+"");
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
	 * @return the normativa
	 */
	public Integer getNormativa() {
		return normativa;
	}


	/**
	 * @param normativa the normativa to set
	 */
	public void setNormativa(Integer normativa) {
		this.normativa = normativa;
	}


}
