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
 * FiltroNormatives.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroNormatives", description = "Filtro que permite buscar por diferentes campos")
public class FiltroNormatives {
	
	public static final String SAMPLE = 
			  Constantes.SALTO_LINEA + "{\"codigoUA\":\"0\"," 
			+ Constantes.SALTO_LINEA + "\"fechaBoletin\":\"DD/MM/YYYY\","
			+ Constantes.SALTO_LINEA + "\"codigoProcedimiento\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoServicio\":\"0\","
			+ Constantes.SALTO_LINEA + "\"numNorma\":\"0\","
			+ Constantes.SALTO_LINEA + "\"fechaPublicacion\":\"DD/MM/YYYY\","
			+ Constantes.SALTO_LINEA + "\"tipoPublicacion\":\"0\","
			+ Constantes.SALTO_LINEA + "\"texto\":\"0\""
		    + "}";
	
	
	/** codigoUA. **/
	@ApiModelProperty(value = "codigoUA", required = false)
	private Integer codigoUA; 
	
	/** fechaBoletin. **/
	@ApiModelProperty(value = "fechaBoletin", required = false)
	private String fechaBoletin;
	
	
	/** codigoProcedimiento **/
	@ApiModelProperty(value = "codigoProcedimiento", required = false)
	private Integer codigoProcedimiento;

	/** codigoServicio. **/
	@ApiModelProperty(value = "codigoServicio", required = false)
	private Integer codigoServicio;

	
	
	/** numNorma. **/
	@ApiModelProperty(value = "numNorma", required = false)
	private String numNorma;
	
	/** fechaPublicacion. **/
	@ApiModelProperty(value = "fechaPublicacion", required = false)
	private String fechaPublicacion;
		
	/** tipoPublicacion. **/
	@ApiModelProperty(value = "tipoPublicacion", required = false)
	private Integer tipoPublicacion;
	
	/** texto. **/
	@ApiModelProperty(value = "texto", required = false)
	private String texto;
	
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		
		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_NORMATIVA_UA, this.codigoUA+"");
		}
		
		if(this.fechaBoletin!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_NORMATIVA_FECHABOLETIN, this.fechaBoletin+"");
		}
		
		if(this.codigoProcedimiento!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_NORMATIVA_PROCEDIMIENTO, this.codigoProcedimiento+"");
		}
		
		if(this.codigoServicio!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_NORMATIVA_SERVICIO, this.codigoServicio+"");
		}
		
		if(this.numNorma!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_NORMATIVA_NUMERO_NORMA, this.numNorma+"");
		}
		
		if(this.fechaPublicacion!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_NORMATIVA_FECHA_PUBLICACION, this.fechaPublicacion+"");
		}
		if(this.tipoPublicacion!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_NORMATIVA_TIPO_PUBLICACION, this.tipoPublicacion+"");
		}
		
		if(this.texto!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_NORMATIVA_TEXTO, this.texto+"");
		}
		
		return fg;
	}
	
	
	
	public static FiltroNormatives valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroNormatives> typeRef = new TypeReference<FiltroNormatives>() {
		};
		FiltroNormatives obj;
		try {
			obj = (FiltroNormatives) objectMapper.readValue(json, typeRef);
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
	 * @return the fechaBoletin
	 */
	public String getFechaBoletin() {
		return fechaBoletin;
	}



	/**
	 * @param fechaBoletin the fechaBoletin to set
	 */
	public void setFechaBoletin(String fechaBoletin) {
		this.fechaBoletin = fechaBoletin;
	}



	/**
	 * @return the codigoProcedimiento
	 */
	public Integer getCodigoProcedimiento() {
		return codigoProcedimiento;
	}



	/**
	 * @param codigoProcedimiento the codigoProcedimiento to set
	 */
	public void setCodigoProcedimiento(Integer codigoProcedimiento) {
		this.codigoProcedimiento = codigoProcedimiento;
	}



	/**
	 * @return the codigoServicio
	 */
	public Integer getCodigoServicio() {
		return codigoServicio;
	}



	/**
	 * @param codigoServicio the codigoServicio to set
	 */
	public void setCodigoServicio(Integer codigoServicio) {
		this.codigoServicio = codigoServicio;
	}



	/**
	 * @return the numNorma
	 */
	public String getNumNorma() {
		return numNorma;
	}



	/**
	 * @param numNorma the numNorma to set
	 */
	public void setNumNorma(String numNorma) {
		this.numNorma = numNorma;
	}



	/**
	 * @return the fechaPublicacion
	 */
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}



	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}



	/**
	 * @return the tipoPublicacion
	 */
	public Integer getTipoPublicacion() {
		return tipoPublicacion;
	}



	/**
	 * @param tipoPublicacion the tipoPublicacion to set
	 */
	public void setTipoPublicacion(Integer tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}



	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}



	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}


	
	
	
}
