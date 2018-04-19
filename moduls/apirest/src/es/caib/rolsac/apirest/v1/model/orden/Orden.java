package es.caib.rolsac.apirest.v1.model.orden;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Orden
 * Clase encargada de almacenar los campos por los que ordenar
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "Orden", description = "Campos que se pueden usar para ordenar")
public class Orden {
	
	
	public static final String CAMPO_ORD_UA_ORDEN = "orden"; 
	public static final String CAMPO_ORD_DOC_ORDEN = "orden"; 
	
	public static final String SAMPLE_ORDEN_UA = Constantes.SALTO_LINEA + "{\"listaOrden\":[{\"campo\":\"" + CAMPO_ORD_UA_ORDEN + "\",\"tipoOrden\":\"ASC/DESC\"}]}";
	public static final String SAMPLE_ORDEN_DOC = Constantes.SALTO_LINEA + "{\"listaOrden\":[{\"campo\":\"" + CAMPO_ORD_DOC_ORDEN + "\",\"tipoOrden\":\"ASC/DESC\"}]}";
	
	
		
	/** Lista de campos a ordenar. **/
	@ApiModelProperty(value = "Lista de campos por los que ordenar", required = false)
	private List<CampoOrden> listaOrden;

	
	public static Orden valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Orden> typeRef = new TypeReference<Orden>() {
		};
		Orden obj;
		try {
			obj = (Orden) objectMapper.readValue(json, typeRef);
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
	 * @return the listaOrden
	 */
	public List<CampoOrden> getListaOrden() {
	return listaOrden;}

	/**
	 * @param listaOrden the listaOrden to set
	 */
	public void setListaOrden(List<CampoOrden> listaOrden) {
	this.listaOrden = listaOrden;}


}
