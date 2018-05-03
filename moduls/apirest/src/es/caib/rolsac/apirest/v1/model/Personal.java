package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Personal.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_PERSONAL, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_PERSONAL)
public class Personal extends EntidadBase {
	 

	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	/** cargo **/
	@ApiModelProperty(value = "cargo", required = false)
	 private java.lang.String cargo;

	/** email **/
	@ApiModelProperty(value = "email", required = false)
	    private java.lang.String email;

	/** extensionMovil **/
	@ApiModelProperty(value = "extensionMovil", required = false)
	    private java.lang.String extensionMovil;

	/** extensionPrivada **/
	@ApiModelProperty(value = "extensionPrivada", required = false)
	    private java.lang.String extensionPrivada;

	/** extensionPublica **/
	@ApiModelProperty(value = "extensionPublica", required = false)
	    private java.lang.String extensionPublica;

	/** funciones **/
	@ApiModelProperty(value = "funciones", required = false)
	    private java.lang.String funciones;

	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
	    private java.lang.String nombre;

	/** numeroLargoMovil **/
	@ApiModelProperty(value = "numeroLargoMovil", required = false)
	    private java.lang.String numeroLargoMovil;

	/** numeroLargoPrivado **/
	@ApiModelProperty(value = "numeroLargoPrivado", required = false)
	    private java.lang.String numeroLargoPrivado;
	
	/** numeroLargoPublico **/
	@ApiModelProperty(value = "numeroLargoPublico", required = false)
	    private java.lang.String numeroLargoPublico;
	
	/** username **/
	@ApiModelProperty(value = "username", required = false)
	    private java.lang.String username;
	
	
	//-- LINKS--//
	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	/** unidadAdministrativa **/
	@ApiModelProperty(value = "link_unidadAdministrativa", required = false)
	private Link link_unidadAdministrativa;	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	private Long unidadAdministrativa;
	
		
	public Personal (org.ibit.rol.sac.model.Personal elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Personal() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		link_unidadAdministrativa = this.generaLink(this.unidadAdministrativa,Constantes.ENTIDAD_UA,Constantes.URL_UA, urlBase , null );
	}


	public static Personal valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Personal> typeRef = new TypeReference<Personal>() {
		};
		Personal obj;
		try {
			obj = (Personal) objectMapper.readValue(json, typeRef);
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
	 * @return the cargo
	 */
	public java.lang.String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(java.lang.String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the email
	 */
	public java.lang.String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * @return the extensionMovil
	 */
	public java.lang.String getExtensionMovil() {
		return extensionMovil;
	}

	/**
	 * @param extensionMovil the extensionMovil to set
	 */
	public void setExtensionMovil(java.lang.String extensionMovil) {
		this.extensionMovil = extensionMovil;
	}

	/**
	 * @return the extensionPrivada
	 */
	public java.lang.String getExtensionPrivada() {
		return extensionPrivada;
	}

	/**
	 * @param extensionPrivada the extensionPrivada to set
	 */
	public void setExtensionPrivada(java.lang.String extensionPrivada) {
		this.extensionPrivada = extensionPrivada;
	}

	/**
	 * @return the extensionPublica
	 */
	public java.lang.String getExtensionPublica() {
		return extensionPublica;
	}

	/**
	 * @param extensionPublica the extensionPublica to set
	 */
	public void setExtensionPublica(java.lang.String extensionPublica) {
		this.extensionPublica = extensionPublica;
	}

	/**
	 * @return the funciones
	 */
	public java.lang.String getFunciones() {
		return funciones;
	}

	/**
	 * @param funciones the funciones to set
	 */
	public void setFunciones(java.lang.String funciones) {
		this.funciones = funciones;
	}

	/**
	 * @return the nombre
	 */
	public java.lang.String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the numeroLargoMovil
	 */
	public java.lang.String getNumeroLargoMovil() {
		return numeroLargoMovil;
	}

	/**
	 * @param numeroLargoMovil the numeroLargoMovil to set
	 */
	public void setNumeroLargoMovil(java.lang.String numeroLargoMovil) {
		this.numeroLargoMovil = numeroLargoMovil;
	}

	/**
	 * @return the numeroLargoPrivado
	 */
	public java.lang.String getNumeroLargoPrivado() {
		return numeroLargoPrivado;
	}

	/**
	 * @param numeroLargoPrivado the numeroLargoPrivado to set
	 */
	public void setNumeroLargoPrivado(java.lang.String numeroLargoPrivado) {
		this.numeroLargoPrivado = numeroLargoPrivado;
	}

	/**
	 * @return the numeroLargoPublico
	 */
	public java.lang.String getNumeroLargoPublico() {
		return numeroLargoPublico;
	}

	/**
	 * @param numeroLargoPublico the numeroLargoPublico to set
	 */
	public void setNumeroLargoPublico(java.lang.String numeroLargoPublico) {
		this.numeroLargoPublico = numeroLargoPublico;
	}

	/**
	 * @return the username
	 */
	public java.lang.String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	/**
	 * @return the link_unidadAdministrativa
	 */
	public Link getLink_unidadAdministrativa() {
		return link_unidadAdministrativa;
	}

	/**
	 * @param link_unidadAdministrativa the link_unidadAdministrativa to set
	 */
	public void setLink_unidadAdministrativa(Link link_unidadAdministrativa) {
		this.link_unidadAdministrativa = link_unidadAdministrativa;
	}

	/**
	 * @return the unidadAdministrativa
	 */
	@XmlTransient
	public Long getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	/**
	 * @param unidadAdministrativa the unidadAdministrativa to set
	 */
	public void setUnidadAdministrativa(Long unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

}
