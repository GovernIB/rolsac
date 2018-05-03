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
 * FiltroMaterias.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroMaterias", description = "Filtro que permite buscar por diferentes campos")
public class FiltroMateries {
	
	public static final String SAMPLE = 
			  Constantes.SALTO_LINEA + "{\"codigoAgrupacionMaterias\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoUA\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoFicha\":\"0\""
		    + "}";
	 
	

	/** codigoAgrupacionMaterias. **/
	@ApiModelProperty(value = "codigoAgrupacionMaterias", required = false)
	private Integer codigoAgrupacionMaterias;

	/** codigoUA. **/
	@ApiModelProperty(value = "codigoUA", required = false)
	private Integer codigoUA;
	
	/** validacion. **/
	@ApiModelProperty(value = "codigoFicha", required = false)
	private Integer codigoFicha;
	
		
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();

		if(this.codigoAgrupacionMaterias!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_MATERIAS_AGRUPACIONMATERIAS, this.codigoAgrupacionMaterias+"");
		}
		
		
		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_MATERIAS_UA, this.codigoUA+"");
		}
		
		
		if(this.codigoFicha!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_MATERIAS_FICHA, this.codigoFicha+"");
		}
		
		return fg;
	}
	
	
	
	public static FiltroMateries valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroMateries> typeRef = new TypeReference<FiltroMateries>() {
		};
		FiltroMateries obj;
		try {
			obj = (FiltroMateries) objectMapper.readValue(json, typeRef);
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



	/**
	 * @return the codigoAgrupacionMaterias
	 */
	public Integer getCodigoAgrupacionMaterias() {
		return codigoAgrupacionMaterias;
	}

	/**
	 * @param codigoAgrupacionMaterias the codigoAgrupacionMaterias to set
	 */
	public void setCodigoAgrupacionMaterias(Integer codigoAgrupacionMaterias) {
		this.codigoAgrupacionMaterias = codigoAgrupacionMaterias;
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
