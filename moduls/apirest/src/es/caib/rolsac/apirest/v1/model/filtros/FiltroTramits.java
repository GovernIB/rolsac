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
 * Filtro Tramites.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "Filtro tramites", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_TRAMITE)
public class FiltroTramits {
	
	public static final String SAMPLE = "{\"codigoUA\":\"0\","
			+ Constantes.SALTO_LINEA +"\"fase\":\"0\", "
			+ Constantes.SALTO_LINEA +"\"codigoProcedimiento\":\"0\", "
			+ Constantes.SALTO_LINEA +"\"codigoTramiteTelematico\":\"codigo\", "
			+ Constantes.SALTO_LINEA +"\"versionTramiteTelematico\":\"version\", "
			+ Constantes.SALTO_LINEA +"\"urlTramiteTelematico\":\"url\", "
			+ "}"; 
	
	 
	/** codigoUA. **/
	@ApiModelProperty(value = "Código unidad administrativa", required = false)
	private Integer codigoUA;
	
	@ApiModelProperty(value = "fase", required = false)
	private String fase;
	
	@ApiModelProperty(value = "codigoProcedimiento", required = false)
	private String codigoProcedimiento;
	
	@ApiModelProperty(value = "codigoTramiteTelematico", required = false)
	private String codigoTramiteTelematico;
	
	@ApiModelProperty(value = "versionTramiteTelematico", required = false)
	private String versionTramiteTelematico;
	
	@ApiModelProperty(value = "urlTramiteTelematico", required = false)
	private String urlTramiteTelematico;
	
		
	public static FiltroTramits valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroTramits> typeRef = new TypeReference<FiltroTramits>() {
		};
		FiltroTramits obj;
		try {
			obj = (FiltroTramits) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_TRAMITE_UA, this.codigoUA+"");
		}
		
		if(this.fase!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_TRAMITE_FASE, this.fase+"");
		}
		
		if(this.codigoProcedimiento!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_TRAMITE_PROCEDIMIENTO, this.codigoProcedimiento+"");
		}
		
		if(this.codigoTramiteTelematico!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_TRAMITE_TRAMITE_TELEMATICO, this.codigoTramiteTelematico+"");
		}
		
		if(this.versionTramiteTelematico!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_TRAMITE_VERSION_TRAMITE_TELEMATICO, this.versionTramiteTelematico+"");
		}
		
		if(this.urlTramiteTelematico!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_TRAMITE_URL_TRAMITE_TELEMATICO, this.urlTramiteTelematico+"");
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


	/**
	 * @return the fase
	 */
	public String getFase() {
		return fase;
	}


	/**
	 * @param fase the fase to set
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}


	/**
	 * @return the codigoProcedimiento
	 */
	public String getCodigoProcedimiento() {
		return codigoProcedimiento;
	}


	/**
	 * @param codigoProcedimiento the codigoProcedimiento to set
	 */
	public void setCodigoProcedimiento(String codigoProcedimiento) {
		this.codigoProcedimiento = codigoProcedimiento;
	}


	/**
	 * @return the codigoTramiteTelematico
	 */
	public String getCodigoTramiteTelematico() {
		return codigoTramiteTelematico;
	}


	/**
	 * @param codigoTramiteTelematico the codigoTramiteTelematico to set
	 */
	public void setCodigoTramiteTelematico(String codigoTramiteTelematico) {
		this.codigoTramiteTelematico = codigoTramiteTelematico;
	}


	/**
	 * @return the versionTramiteTelematico
	 */
	public String getVersionTramiteTelematico() {
		return versionTramiteTelematico;
	}


	/**
	 * @param versionTramiteTelematico the versionTramiteTelematico to set
	 */
	public void setVersionTramiteTelematico(String versionTramiteTelematico) {
		this.versionTramiteTelematico = versionTramiteTelematico;
	}


	/**
	 * @return the urlTramiteTelematico
	 */
	public String getUrlTramiteTelematico() {
		return urlTramiteTelematico;
	}


	/**
	 * @param urlTramiteTelematico the urlTramiteTelematico to set
	 */
	public void setUrlTramiteTelematico(String urlTramiteTelematico) {
		this.urlTramiteTelematico = urlTramiteTelematico;
	}



}
