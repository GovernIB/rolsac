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
 * FiltroDocuments.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "Filtro Documentos", description = "Filtro propio de la entidad " + Constantes.ENTIDAD_DOCUMENTOS)
public class FiltroDocuments {
	
	public static final String SAMPLE = "{\"archivo\":\"0\"," + Constantes.SALTO_LINEA + 
			"\"ficha\":\"0\"," + Constantes.SALTO_LINEA + 
			"\"procedimiento\":\"0\"}"; 
	
	 
	/** archivo. **/
	@ApiModelProperty(value = "Código archivo", required = false)
	private Integer archivo;
	
	
	/** ficha. **/
	@ApiModelProperty(value = "Código ficha", required = false)
	private Integer ficha;
	
	/** procedimiento. **/
	@ApiModelProperty(value = "Código procedimiento", required = false)
	private Integer procedimiento;
	
	
		
	public static FiltroDocuments valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroDocuments> typeRef = new TypeReference<FiltroDocuments>() {
		};
		FiltroDocuments obj;
		try {
			obj = (FiltroDocuments) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.archivo!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_DOC_ARCHIVO, this.archivo+"");
		}
		
		if(this.ficha!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_DOC_FICHA, this.ficha+"");
		}
		
		if(this.procedimiento!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_DOC_PROCEDIMIENTO, this.procedimiento+"");
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
	 * @return the archivo
	 */
	public Integer getArchivo() {
		return archivo;
	}


	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Integer archivo) {
		this.archivo = archivo;
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


	/**
	 * @return the procedimiento
	 */
	public Integer getProcedimiento() {
		return procedimiento;
	}


	/**
	 * @param procedimiento the procedimiento to set
	 */
	public void setProcedimiento(Integer procedimiento) {
		this.procedimiento = procedimiento;
	}


}
