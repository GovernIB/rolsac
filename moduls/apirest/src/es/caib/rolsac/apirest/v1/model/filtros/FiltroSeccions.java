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
 * Filtro Secciones.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "Filtro Secciones", description = "Filtro propio de las secciones " + Constantes.ENTIDAD_SECCION)
public class FiltroSeccions {
	
	public static final String SAMPLE = "{\"codigoUA\":\"0\","+
			"\"codigoEstandar\":\"INFO\"}"; 
	
	 
	/** codigoUA. **/
	@ApiModelProperty(value = "Código unidad administrativa", required = false)
	private Integer codigoUA;
	
	/** codigoEstandar. **/
	@ApiModelProperty(value = "Código Estandar", required = false)
	private String codigoEstandar;
	
	
		
	public static FiltroSeccions valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroSeccions> typeRef = new TypeReference<FiltroSeccions>() {
		};
		FiltroSeccions obj;
		try {
			obj = (FiltroSeccions) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SECCIONES_UA, this.codigoUA+"");
		}
		
		if(this.codigoEstandar!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SECCIONES_CODIGO_ESTANDAR, this.codigoEstandar+"");
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
	 * @return the codigoEstandar
	 */
	public String getCodigoEstandar() {
		return codigoEstandar;
	}


	/**
	 * @param codigoEstandar the codigoEstandar to set
	 */
	public void setCodigoEstandar(String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
	}

}
