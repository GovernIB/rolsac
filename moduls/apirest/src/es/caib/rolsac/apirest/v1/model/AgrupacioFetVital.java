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
@ApiModel(value = "AgrupacioFetVital", description = "Definición de la clase AgrupacioFetVital")
public class AgrupacioFetVital extends EntidadBase {
	 
	/** codigoEstandar. **/
	@ApiModelProperty(value = "codigoEstandar", dataType = "java.lang.String", required = false)
	private String codigoEstandar;
	
	/** contenido **/
	@ApiModelProperty(value = "contenido", dataType = "java.lang.Long", required = false)
	private Long contenido;
	
	/** descripcion **/
	@ApiModelProperty(value = "descripcion", dataType = "java.lang.String", required = false)
	private String descripcion;
	
	/** distribComp **/
	@ApiModelProperty(value = "distribComp", dataType = "java.lang.Long", required = false)
	private Long distribComp;
	
	/** foto **/
	@ApiModelProperty(value = "foto", dataType = "java.lang.Long", required = false)
	private Long foto;
	
	/** icono **/
	@ApiModelProperty(value = "icono", dataType = "java.lang.Long", required = false)
	private Long icono;
	
	/** iconoGrande **/
	@ApiModelProperty(value = "iconoGrande", dataType = "java.lang.Long", required = false)
	private Long iconoGrande;
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", dataType = "java.lang.Long", required = false)
	private long codigo;
	
	/** nombre **/
	@ApiModelProperty(value = "nombre", dataType = "java.lang.Long", required = false)
	private String nombre;
	
	/** normativa **/
	@ApiModelProperty(value = "normativa", dataType = "java.lang.Long", required = false)
	private Long normativa;
	
	/** palabrasclave **/
	@ApiModelProperty(value = "palabrasclave", dataType = "java.lang.Long", required = false)
	private String palabrasclave;
	
	/** publico **/
	@ApiModelProperty(value = "publico", dataType = "java.lang.Long", required = false)
	private Long publico;
	

	public AgrupacioFetVital (org.ibit.rol.sac.model.AgrupacionHechoVital ahv, String urlBase,String idioma,boolean hateoasEnabled ) {
		this.fill( ahv, urlBase, idioma, hateoasEnabled);
	}
	
	public AgrupacioFetVital() {
		super();
	}
	
	
	public void fill(org.ibit.rol.sac.model.AgrupacionHechoVital ahv, String urlBase,String idioma, boolean hateoasEnabled ) {
		this.setHateoasEnabled(hateoasEnabled);			
		super.copiaPropiedadesDeEntity(ahv, idioma);	
		generaLinks(urlBase);
	}
	
	public void generaLinks(String urlBase) {
		this.addLink(this.foto, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"foto" );
		this.addLink(this.icono, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"icono" );
		this.addLink(this.iconoGrande, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"iconoGrande" );
		
	}


	public static AgrupacioFetVital valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<AgrupacioFetVital> typeRef = new TypeReference<AgrupacioFetVital>() {
		};
		AgrupacioFetVital obj;
		try {
			obj = (AgrupacioFetVital) objectMapper.readValue(json, typeRef);
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
	 * @return the contenido
	 */
	public Long getContenido() {
		return contenido;
	}


	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(Long contenido) {
		this.contenido = contenido;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the distribComp
	 */
	public Long getDistribComp() {
		return distribComp;
	}


	/**
	 * @param distribComp the distribComp to set
	 */
	public void setDistribComp(Long distribComp) {
		this.distribComp = distribComp;
	}


	/**
	 * @return the foto
	 */
	public Long getFoto() {
		return foto;
	}


	/**
	 * @param foto the foto to set
	 */
	public void setFoto(Long foto) {
		this.foto = foto;
	}


	/**
	 * @return the icono
	 */
	public Long getIcono() {
		return icono;
	}


	/**
	 * @param icono the icono to set
	 */
	public void setIcono(Long icono) {
		this.icono = icono;
	}


	/**
	 * @return the iconoGrande
	 */
	public Long getIconoGrande() {
		return iconoGrande;
	}


	/**
	 * @param iconoGrande the iconoGrande to set
	 */
	public void setIconoGrande(Long iconoGrande) {
		this.iconoGrande = iconoGrande;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the normativa
	 */
	public Long getNormativa() {
		return normativa;
	}


	/**
	 * @param normativa the normativa to set
	 */
	public void setNormativa(Long normativa) {
		this.normativa = normativa;
	}


	/**
	 * @return the palabrasclave
	 */
	public String getPalabrasclave() {
		return palabrasclave;
	}


	/**
	 * @param palabrasclave the palabrasclave to set
	 */
	public void setPalabrasclave(String palabrasclave) {
		this.palabrasclave = palabrasclave;
	}


	/**
	 * @return the publico
	 */
	public Long getPublico() {
		return publico;
	}


	/**
	 * @param publico the publico to set
	 */
	public void setPublico(Long publico) {
		this.publico = publico;
	}
	
}
