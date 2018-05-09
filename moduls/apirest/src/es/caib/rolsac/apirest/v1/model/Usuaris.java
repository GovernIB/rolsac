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
 * Usuarios: se define la estructura de la entidad 'Usuarios'
 * 
 * @author Indra
 * 
 *         Listado de Funciones de la entidad 'Usuarios':
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_USUARIOS, description = Constantes.TXT_DEFINICION_CLASE
		+ Constantes.ENTIDAD_USUARIOS)
public class Usuaris extends EntidadBase {

	/** email **/
	@ApiModelProperty(value = "email", required = false)
	private long email;

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	/** Codigo nombre. **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;

	/** observaciones. **/
	@ApiModelProperty(value = "observaciones", required = false)
	private String observaciones;

	/** idioma **/
	@ApiModelProperty(value = "password", required = false)
	private String password;

	/** nombre. **/
	@ApiModelProperty(value = "perfil", required = false)
	private String perfil;

	/** palabrasclave. **/
	@ApiModelProperty(value = "username", required = false)
	private String username;

	// -- LINKS--//
	// (Esta entidad no hace referencia a otras entidades)

	public Usuaris(org.ibit.rol.sac.model.Usuario elem, String urlBase,
			String idioma, boolean hateoasEnabled) {
		super(elem, urlBase, idioma, hateoasEnabled);
	}

	public Usuaris() {
		super();
	}

	@Override
	public void generaLinks(String urlBase) {
		
	}
	
	public static Usuaris valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Usuaris> typeRef = new TypeReference<Usuaris>() {
		};
		Usuaris obj;
		try {
			obj = (Usuaris) objectMapper.readValue(json, typeRef);
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
	 * @return the email
	 */
	public long getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(long email) {
		this.email = email;
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
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the perfil
	 */
	public String getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	protected void addSetersInvalidos() {
		// TODO Auto-generated method stub

	}



	
	
}
