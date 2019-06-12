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
 * PublicsObjectius.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_PUBLICO, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_PUBLICO)
public class PublicsObjectius extends EntidadBase {
	 

	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** codigoEstandar **/
	@ApiModelProperty(value = "codigoEstandar", required = false)
	 private java.lang.String codigoEstandar;
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion", required = false)
	    private java.lang.String descripcion;
	
	/** orden **/
	@ApiModelProperty(value = "orden", required = false)
	    private int orden;
	
	/** interno **/
	@ApiModelProperty(value = "interno", required = false)
	    private boolean interno;
	
	/** palabrasclave **/
	@ApiModelProperty(value = "palabrasclave", required = false)
	    private java.lang.String palabrasclave;
	
	/** titulo **/
	@ApiModelProperty(value = "titulo", required = false)
	    private java.lang.String titulo;
	
		
	public PublicsObjectius (org.ibit.rol.sac.model.PublicoObjetivo elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public PublicsObjectius() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		
	}


	public static PublicsObjectius valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<PublicsObjectius> typeRef = new TypeReference<PublicsObjectius>() {
		};
		PublicsObjectius obj;
		try {
			obj = (PublicsObjectius) objectMapper.readValue(json, typeRef);
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


	@Override
	protected void addSetersInvalidos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(Long codigo) {
		this.codigo = codigo;		
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codigoEstandar
	 */
	public java.lang.String getCodigoEstandar() {
		return codigoEstandar;
	}

	/**
	 * @param codigoEstandar the codigoEstandar to set
	 */
	public void setCodigoEstandar(java.lang.String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
	}

	/**
	 * @return the descripcion
	 */
	public java.lang.String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(java.lang.String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(int orden) {
		this.orden = orden;
	}

	/**
	 * @return the palabrasclave
	 */
	public java.lang.String getPalabrasclave() {
		return palabrasclave;
	}

	/**
	 * @param palabrasclave the palabrasclave to set
	 */
	public void setPalabrasclave(java.lang.String palabrasclave) {
		this.palabrasclave = palabrasclave;
	}

	/**
	 * @return the titulo
	 */
	public java.lang.String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(java.lang.String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the interno
	 */
	public boolean isInterno() {
		return interno;
	}

	/**
	 * @param interno the interno to set
	 */
	public void setInterno(boolean interno) {
		this.interno = interno;
	}

}
