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
 * FiltroFetsVitals.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "Filtro hechos vitales", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_HECHOS_VITALES)
public class FiltroFetsVitals {
	
	public static final String SAMPLE = "{\"codigoAgrupacioFetVital\":\"0\"," + Constantes.SALTO_LINEA + 
			"\"ficha\":\"0\"}"; 
	
	 
	/** codigoAgrupacioFetVital. **/
	@ApiModelProperty(value = "Código agrupacion hecho vital", required = false)
	private Integer codigoAgrupacioFetVital;
	
	
	/** ficha. **/
	@ApiModelProperty(value = "Código ficha", required = false)
	private Integer ficha;
	
	
		
	public static FiltroFetsVitals valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroFetsVitals> typeRef = new TypeReference<FiltroFetsVitals>() {
		};
		FiltroFetsVitals obj;
		try {
			obj = (FiltroFetsVitals) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.codigoAgrupacioFetVital!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_HV_AGRUPACION_HV, this.codigoAgrupacioFetVital+"");
		}
		
		if(this.ficha!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_HV_FICHA, this.ficha+"");
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
	 * @return the codigoAgrupacioFetVital
	 */
	public Integer getCodigoAgrupacioFetVital() {
		return codigoAgrupacioFetVital;
	}


	/**
	 * @param codigoAgrupacioFetVital the codigoAgrupacioFetVital to set
	 */
	public void setCodigoAgrupacioFetVital(Integer codigoAgrupacioFetVital) {
		this.codigoAgrupacioFetVital = codigoAgrupacioFetVital;
	}


	/**
	 * @return the ficha
	 */
	public Integer getFicha() {
		return ficha;
	}


	/**
	 * @param ficha the ficha to set
	 */
	public void setFicha(Integer ficha) {
		this.ficha = ficha;
	}


}
