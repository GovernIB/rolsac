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
 * FiltroFitxes.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroFichas", description = "Filtro que permite buscar por diferentes campos")
public class FiltroFitxesUA {
	
	public static final String SAMPLE = 
			  Constantes.SALTO_LINEA + "{\"codigoSeccion\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoUA\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoFicha\":\"0\""
		    + "}";
	 
	

	/** codigoSeccion. **/
	@ApiModelProperty(value = "codigoSeccion", required = false)
	private Integer codigoSeccion;

	/** codigoUA. **/
	@ApiModelProperty(value = "codigoUA", required = false)
	private Integer codigoUA;
	
	/** validacion. **/
	@ApiModelProperty(value = "codigoFicha", required = false)
	private Integer codigoFicha;
	
		
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		
		
		
		if(this.codigoSeccion!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHASUA_SECCION, this.codigoSeccion+"");
		}
		
		
		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHASUA_UA, this.codigoUA+"");
		}
		
		
		if(this.codigoFicha!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHASUA_FICHA, this.codigoFicha+"");
		}
		
		return fg;
	}
	
	
	
	public static FiltroFitxesUA valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroFitxesUA> typeRef = new TypeReference<FiltroFitxesUA>() {
		};
		FiltroFitxesUA obj;
		try {
			obj = (FiltroFitxesUA) objectMapper.readValue(json, typeRef);
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
	 * @return the codigoSeccion
	 */
	public Integer getCodigoSeccion() {
		return codigoSeccion;
	}



	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(Integer codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
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
