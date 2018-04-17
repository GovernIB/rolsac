package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Idioma.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "Tratamiento", description = Constantes.TXT_DEFINICION_CLASE + "Tratamiento")
public class Tratamiento extends EntidadBase {
	 
	public Tratamiento(org.ibit.rol.sac.model.Tratamiento tr, String urlBase,String idioma,boolean hateoasEnabled) {
		super(tr, urlBase, idioma, hateoasEnabled);		
	}
	
	public Tratamiento() {
		super();
	}

	/** codigo. **/
	@ApiModelProperty(value = "codigo", dataType = "java.lang.Long")
	private Long codigo;

	/** cargoF. **/
	@ApiModelProperty(value = "cargoF", dataType = "java.lang.String")
	private String cargoF;

	/** cargoM. **/
	@ApiModelProperty(value = "cargoM", dataType = "java.lang.String")
	private String cargoM;

	/** codigoEstandar**/
	@ApiModelProperty(value = "codigoEstandar", dataType = "java.lang.String")
	private String codigoEstandar;
	
	/** tipo**/
	@ApiModelProperty(value = "tipo", dataType = "java.lang.String")
	private String tipo;
	
	/** tratamientoF**/
	@ApiModelProperty(value = "tratamientoF", dataType = "java.lang.String")
	private String tratamientoF;
	
	/** **/
	@ApiModelProperty(value = "tratamientoM", dataType = "java.lang.String")
	private String tratamientoM;
	
	
	public static Tratamiento valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Tratamiento> typeRef = new TypeReference<Tratamiento>() {
		};
		Tratamiento obj;
		try {
			obj = (Tratamiento) objectMapper.readValue(json, typeRef);
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
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}


	@Override
	public void setId(Long codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	/**
	 * @return the cargoF
	 */
	public String getCargoF() {
		return cargoF;
	}


	/**
	 * @param cargoF the cargoF to set
	 */
	public void setCargoF(String cargoF) {
		this.cargoF = cargoF;
	}


	/**
	 * @return the cargoM
	 */
	public String getCargoM() {
		return cargoM;
	}


	/**
	 * @param cargoM the cargoM to set
	 */
	public void setCargoM(String cargoM) {
		this.cargoM = cargoM;
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


	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}


	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	/**
	 * @return the tratamientoF
	 */
	public String getTratamientoF() {
		return tratamientoF;
	}


	/**
	 * @param tratamientoF the tratamientoF to set
	 */
	public void setTratamientoF(String tratamientoF) {
		this.tratamientoF = tratamientoF;
	}


	/**
	 * @return the tratamientoM
	 */
	public String getTratamientoM() {
		return tratamientoM;
	}


	/**
	 * @param tratamientoM the tratamientoM to set
	 */
	public void setTratamientoM(String tratamientoM) {
		this.tratamientoM = tratamientoM;
	}

	@Override
	protected void addSetersInvalidos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generaLinks(String urlBase) {
		// TODO Auto-generated method stub
		
	}
	
}
