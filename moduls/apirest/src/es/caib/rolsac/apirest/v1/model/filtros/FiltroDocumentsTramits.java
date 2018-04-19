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
@ApiModel(value = "Filtro documentos tramites", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_DOCUMENTOS_TRAMITES)
public class FiltroDocumentsTramits {
	
	public static final String SAMPLE = "{\"tramite\":\"0\"," + Constantes.SALTO_LINEA + 
			"\"tipoDocumento\":\"0\"}"; 
	
	 
	/** tramite. **/
	@ApiModelProperty(value = "Código tramite", required = false)
	private Integer tramite;
	
	
	/** tipoDocumento. **/
	@ApiModelProperty(value = "Código tipo documento", required = false)
	private Integer tipoDocumento;
	
	
		
	public static FiltroDocumentsTramits valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroDocumentsTramits> typeRef = new TypeReference<FiltroDocumentsTramits>() {
		};
		FiltroDocumentsTramits obj;
		try {
			obj = (FiltroDocumentsTramits) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.tramite!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_DOC_TRAMITE_TRAMITE, this.tramite+"");
		}
		
		if(this.tipoDocumento!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_DOC_TRAMITE_TIPO_DOCUMENTO, this.tipoDocumento+"");
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
	 * @return the tramite
	 */
	public Integer getTramite() {
		return tramite;
	}


	/**
	 * @param tramite the tramite to set
	 */
	public void setTramite(Integer tramite) {
		this.tramite = tramite;
	}


	/**
	 * @return the tipoDocumento
	 */
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}


	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
