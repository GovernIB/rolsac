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
 * Unidad Administrativa.
 * 
 * @author indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_UA, description = Constantes.TXT_DEFINICION_CLASE + Constantes.ENTIDAD_UA)
public class UnitatAdministrativa extends EntidadBase {
	 
	
	/** abreviatura. **/
	@ApiModelProperty(value = "abreviatura", required = false)
    private java.lang.String abreviatura;

	/** . **/
	@ApiModelProperty(value = "businessKey", required = false)
    private java.lang.String businessKey;

	/** claveHita **/
	@ApiModelProperty(value = "claveHita", required = false)
    private java.lang.String claveHita;

	/** codigoEstandar **/
	@ApiModelProperty(value = "codigoEstandar", required = false)
    private java.lang.String codigoEstandar;

	/** dominio **/
	@ApiModelProperty(value = "dominio", required = false)
    private java.lang.String dominio;

	/** email **/
	@ApiModelProperty(value = "email", required = false)
    private java.lang.String email;

	/** fax **/
	@ApiModelProperty(value = "fax", required = false)
    private java.lang.String fax;
	
	
	/** codigo **/
	@ApiModelProperty(value = "codigo",required = false)
    private java.lang.Long codigo;

	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
    private java.lang.String nombre;

	/** numfoto1 **/
	@ApiModelProperty(value = "numfoto1 (fichaNivel1)", required = false)
    private java.lang.Integer numfoto1;

	/** numfoto2 **/
	@ApiModelProperty(value = "numfoto2 (fichaNivel2)", required = false)
    private java.lang.Integer numfoto2;

	/** numfoto3 **/
	@ApiModelProperty(value = "numfoto3  (fichaNivel3)", required = false)
    private java.lang.Integer numfoto3;

	/** numfoto4 **/
	@ApiModelProperty(value = "numfoto4  (nivelListado)", required = false)
    private java.lang.Integer numfoto4;

	/** orden **/
	@ApiModelProperty(value = "orden",  required = false)
    private java.lang.Long orden;
		
	/** presentacion **/
	@ApiModelProperty(value = "presentacion", required = false)
    private java.lang.String presentacion;

	/** responsable **/
	@ApiModelProperty(value = "responsable", required = false)
    private java.lang.String responsable;

	/** responsableEmail **/
	@ApiModelProperty(value = "responsableEmail", required = false)
    private java.lang.String responsableEmail;

	/** sexoResponsable **/
	@ApiModelProperty(value = "sexoResponsable", required = false)
    private java.lang.Integer sexoResponsable;

	/** cvResponsable **/
	@ApiModelProperty(value = "cvResponsable", required = false)
    private java.lang.String cvResponsable;

	/** telefono **/
	@ApiModelProperty(value = "telefono", required = false)
    private java.lang.String telefono;
	
	/** tratamiento **/
    private Tratamiento tratamiento;

	/** url **/
	@ApiModelProperty(value = "url", required = false)
    private java.lang.String url;

	/** validacion **/
	@ApiModelProperty(value = "validacion",required = false)
    private java.lang.Integer validacion;

	/** codigoDIR3 **/
	@ApiModelProperty(value = "codigoDIR3",required = false)
    private java.lang.String codigoDIR3;
	
	/**  **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
    
    
    //-- LINKS--//
  	//-- se duplican las entidades para poder generar la clase link en funcion de la propiedad principal (sin "link_")
	
	
	
	
	/** espacioTerrit **/
	@ApiModelProperty(value = "link_espacioTerritorial", required = false)
	private Link link_espacioTerritorial;
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long espacioTerrit;

	/** fotog **/
	@ApiModelProperty(value = "link_fotog", required = false)
	private Link link_fotog;
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long fotog;

	/** fotop **/
	@ApiModelProperty(value = "link_fotop", required = false)
	private Link link_fotop;
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long fotop;

	/** logoh **/
	@ApiModelProperty(value = "link_logoh", required = false)	
	private Link link_logoh;
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long logoh;

	/** logos **/
	@ApiModelProperty(value = "link_logos",  required = false)
	private Link link_logos;
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long logos;

	/** logot **/
	@ApiModelProperty(value = "link_logot", required = false)
	private Link link_logot;
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long logot;

	/** logov **/
	@ApiModelProperty(value = "link_logov", required = false)
	private Link link_logov;
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long logov;
	
	/** padre **/
	@ApiModelProperty(value = "link_padre", required = false)
	private Link link_padre;
	@ApiModelProperty(hidden = true)
	@XmlTransient
    private java.lang.Long padre;
	
	
	
	
	
	public UnitatAdministrativa (org.ibit.rol.sac.model.UnidadAdministrativa ua, String urlBase,String idioma,boolean hateoasEnabled ) {
		super(ua, urlBase, idioma, hateoasEnabled);
	}
	
	
	public UnitatAdministrativa () {
		super();		
	}
	
	
	/**
	 * Necesario solo si queremos añadir propiedades que no serán copiadas automaticamente
	 * y deben copiarse manualmente.
	 */
	@Override
	protected void addSetersInvalidos() {
		if(!SETTERS_INVALIDS.contains("setTratamiento")) {
			SETTERS_INVALIDS.add("setTratamiento");
		}
	}
	
	@Override
	public <T> void fill(T ua, String urlBase,String idioma, boolean hateoasEnabled ) {
		super.fill(ua, urlBase, idioma, hateoasEnabled);	
		
		//copiamos los datos que no tienen la misma estructura:		
		if(((org.ibit.rol.sac.model.UnidadAdministrativa)ua).getTratamiento()!=null ) {					
			this.tratamiento = new Tratamiento(((org.ibit.rol.sac.model.UnidadAdministrativa)ua).getTratamiento(),urlBase,idioma,hateoasEnabled);				
		}		
		
		// si el padre es null lo convertimos a -1
		if(this.padre==null) {
			this.padre=new Long(-1);
		}		
				
	}	
	
	@Override
	protected void generaLinks(String urlBase) {		
		
		this.link_espacioTerritorial = this.generaLink(this.espacioTerrit, Constantes.ENTIDAD_ESPACIO_TERRITORIAL, Constantes.URL_ESPACIO_TERRITORIAL, urlBase,null);
		this.link_padre = this.generaLink(this.padre, Constantes.ENTIDAD_UA, Constantes.URL_UA, urlBase,null );
		
		this.link_fotog = this.generaLinkArchivo(this.fotog, urlBase,null);
		this.link_fotop = this.generaLinkArchivo(this.fotop, urlBase,null );
		this.link_logoh = this.generaLinkArchivo(this.logoh, urlBase,null );
		this.link_logos = this.generaLinkArchivo(this.logos, urlBase,null );		
		this.link_logot = this.generaLinkArchivo(this.logot, urlBase,null );		
		this.link_logov = this.generaLinkArchivo(this.logov, urlBase,null );
	}
	
	public static UnitatAdministrativa valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<UnitatAdministrativa> typeRef = new TypeReference<UnitatAdministrativa>() {
		};
		UnitatAdministrativa obj;
		try {
			obj = (UnitatAdministrativa) objectMapper.readValue(json, typeRef);
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
	 * @return the abreviatura
	 */
	public java.lang.String getAbreviatura() {
		return abreviatura;
	}


	/**
	 * @param abreviatura the abreviatura to set
	 */
	public void setAbreviatura(java.lang.String abreviatura) {
		this.abreviatura = abreviatura;
	}


	/**
	 * @return the businessKey
	 */
	public java.lang.String getBusinessKey() {
		return businessKey;
	}


	/**
	 * @param businessKey the businessKey to set
	 */
	public void setBusinessKey(java.lang.String businessKey) {
		this.businessKey = businessKey;
	}


	/**
	 * @return the claveHita
	 */
	public java.lang.String getClaveHita() {
		return claveHita;
	}


	/**
	 * @param claveHita the claveHita to set
	 */
	public void setClaveHita(java.lang.String claveHita) {
		this.claveHita = claveHita;
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
	 * @return the dominio
	 */
	public java.lang.String getDominio() {
		return dominio;
	}


	/**
	 * @param dominio the dominio to set
	 */
	public void setDominio(java.lang.String dominio) {
		this.dominio = dominio;
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
	 * @return the espacioTerrit
	 */
	@XmlTransient
	public java.lang.Long getEspacioTerrit() {
		return espacioTerrit;
	}


	/**
	 * @param espacioTerrit the espacioTerrit to set
	 */
	public void setEspacioTerrit(java.lang.Long espacioTerrit) {
		this.espacioTerrit = espacioTerrit;
	}


	/**
	 * @return the fax
	 */
	public java.lang.String getFax() {
		return fax;
	}


	/**
	 * @param fax the fax to set
	 */
	public void setFax(java.lang.String fax) {
		this.fax = fax;
	}


	/**
	 * @return the fotog
	 */
	@XmlTransient
	public java.lang.Long getFotog() {
		return fotog;
	}


	/**
	 * @param fotog the fotog to set
	 */
	public void setFotog(java.lang.Long fotog) {
		this.fotog = fotog;
	}


	/**
	 * @return the fotop
	 */
	@XmlTransient
	public java.lang.Long getFotop() {
		return fotop;
	}


	/**
	 * @param fotop the fotop to set
	 */
	public void setFotop(java.lang.Long fotop) {
		this.fotop = fotop;
	}


	/**
	 * @return the codigo
	 */
	@XmlTransient
	public java.lang.Long getCodigo() {
		return codigo;
	}


	/**
	 * @param codigo the id to set
	 */
	public void setCodigo(java.lang.Long codigo) {
		this.codigo = codigo;
	}
	
	@Override	
	public void setId(java.lang.Long codigo) {
		this.codigo = codigo;
	}


	/**
	 * @return the logoh
	 */
	@XmlTransient
	public java.lang.Long getLogoh() {
		return logoh;
	}


	/**
	 * @param logoh the logoh to set
	 */
	public void setLogoh(java.lang.Long logoh) {
		this.logoh = logoh;
	}


	/**
	 * @return the logos
	 */
	@XmlTransient
	public java.lang.Long getLogos() {
		return logos;
	}


	/**
	 * @param logos the logos to set
	 */
	public void setLogos(java.lang.Long logos) {
		this.logos = logos;
	}


	/**
	 * @return the logot
	 */
	@XmlTransient
	public java.lang.Long getLogot() {
		return logot;
	}


	/**
	 * @param logot the logot to set
	 */
	public void setLogot(java.lang.Long logot) {
		this.logot = logot;
	}


	/**
	 * @return the logov
	 */
	@XmlTransient
	public java.lang.Long getLogov() {
		return logov;
	}


	/**
	 * @param logov the logov to set
	 */
	public void setLogov(java.lang.Long logov) {
		this.logov = logov;
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
	 * @return the numfoto1
	 */
	public java.lang.Integer getNumfoto1() {
		return numfoto1;
	}


	/**
	 * @param numfoto1 the numfoto1 to set
	 */
	public void setNumfoto1(java.lang.Integer numfoto1) {
		this.numfoto1 = numfoto1;
	}


	/**
	 * @return the numfoto2
	 */
	public java.lang.Integer getNumfoto2() {
		return numfoto2;
	}


	/**
	 * @param numfoto2 the numfoto2 to set
	 */
	public void setNumfoto2(java.lang.Integer numfoto2) {
		this.numfoto2 = numfoto2;
	}


	/**
	 * @return the numfoto3
	 */
	public java.lang.Integer getNumfoto3() {
		return numfoto3;
	}


	/**
	 * @param numfoto3 the numfoto3 to set
	 */
	public void setNumfoto3(java.lang.Integer numfoto3) {
		this.numfoto3 = numfoto3;
	}


	/**
	 * @return the numfoto4
	 */
	public java.lang.Integer getNumfoto4() {
		return numfoto4;
	}


	/**
	 * @param numfoto4 the numfoto4 to set
	 */
	public void setNumfoto4(java.lang.Integer numfoto4) {
		this.numfoto4 = numfoto4;
	}


	/**
	 * @return the orden
	 */
	public java.lang.Long getOrden() {
		return orden;
	}


	/**
	 * @param orden the orden to set
	 */
	public void setOrden(java.lang.Long orden) {
		this.orden = orden;
	}


	/**
	 * @return the padre
	 */
	@XmlTransient
	public java.lang.Long getPadre() {
		return padre;
	}


	/**
	 * @param padre the padre to set
	 */
	public void setPadre(java.lang.Long padre) {
		this.padre = padre;
	}


	/**
	 * @return the presentacion
	 */
	public java.lang.String getPresentacion() {
		return presentacion;
	}


	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(java.lang.String presentacion) {
		this.presentacion = presentacion;
	}


	/**
	 * @return the responsable
	 */
	public java.lang.String getResponsable() {
		return responsable;
	}


	/**
	 * @param responsable the responsable to set
	 */
	public void setResponsable(java.lang.String responsable) {
		this.responsable = responsable;
	}


	/**
	 * @return the responsableEmail
	 */
	public java.lang.String getResponsableEmail() {
		return responsableEmail;
	}


	/**
	 * @param responsableEmail the responsableEmail to set
	 */
	public void setResponsableEmail(java.lang.String responsableEmail) {
		this.responsableEmail = responsableEmail;
	}


	/**
	 * @return the sexoResponsable
	 */
	public java.lang.Integer getSexoResponsable() {
		return sexoResponsable;
	}


	/**
	 * @param sexoResponsable the sexoResponsable to set
	 */
	public void setSexoResponsable(java.lang.Integer sexoResponsable) {
		this.sexoResponsable = sexoResponsable;
	}


	/**
	 * @return the cvResponsable
	 */
	public java.lang.String getCvResponsable() {
		return cvResponsable;
	}


	/**
	 * @param cvResponsable the cvResponsable to set
	 */
	public void setCvResponsable(java.lang.String cvResponsable) {
		this.cvResponsable = cvResponsable;
	}


	/**
	 * @return the telefono
	 */
	public java.lang.String getTelefono() {
		return telefono;
	}


	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(java.lang.String telefono) {
		this.telefono = telefono;
	}


	


	/**
	 * @return the url
	 */
	public java.lang.String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}


	/**
	 * @return the validacion
	 */
	public java.lang.Integer getValidacion() {
		return validacion;
	}


	/**
	 * @param validacion the validacion to set
	 */
	public void setValidacion(java.lang.Integer validacion) {
		this.validacion = validacion;
	}


	/**
	 * @return the idioma
	 */
	public java.lang.String getIdioma() {
		return idioma;
	}


	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(java.lang.String idioma) {
		this.idioma = idioma;
	}


	/**
	 * @return the codigoDIR3
	 */
	public java.lang.String getCodigoDIR3() {
		return codigoDIR3;
	}


	/**
	 * @param codigoDIR3 the codigoDIR3 to set
	 */
	public void setCodigoDIR3(java.lang.String codigoDIR3) {
		this.codigoDIR3 = codigoDIR3;
	}

	/**
	 * @return the tratamiento
	 */
	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	/**
	 * @param tratamiento the tratamiento to set
	 */
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}


	/**
	 * @return the link_logoh
	 */
	public Link getLink_logoh() {
		return link_logoh;
	}


	/**
	 * @param link_logoh the link_logoh to set
	 */
	public void setLink_logoh(Link link_logoh) {
		this.link_logoh = link_logoh;
	}


	/**
	 * @return the link_espacioTerritorial
	 */
	public Link getLink_espacioTerritorial() {
		return link_espacioTerritorial;
	}


	/**
	 * @param link_espacioTerritorial the link_espacioTerritorial to set
	 */
	public void setLink_espacioTerritorial(Link link_espacioTerritorial) {
		this.link_espacioTerritorial = link_espacioTerritorial;
	}


	/**
	 * @return the link_fotog
	 */
	public Link getLink_fotog() {
		return link_fotog;
	}


	/**
	 * @param link_fotog the link_fotog to set
	 */
	public void setLink_fotog(Link link_fotog) {
		this.link_fotog = link_fotog;
	}


	/**
	 * @return the link_fotop
	 */
	public Link getLink_fotop() {
		return link_fotop;
	}


	/**
	 * @param link_fotop the link_fotop to set
	 */
	public void setLink_fotop(Link link_fotop) {
		this.link_fotop = link_fotop;
	}


	/**
	 * @return the link_logos
	 */
	public Link getLink_logos() {
		return link_logos;
	}


	/**
	 * @param link_logos the link_logos to set
	 */
	public void setLink_logos(Link link_logos) {
		this.link_logos = link_logos;
	}


	/**
	 * @return the link_logot
	 */
	public Link getLink_logot() {
		return link_logot;
	}


	/**
	 * @param link_logot the link_logot to set
	 */
	public void setLink_logot(Link link_logot) {
		this.link_logot = link_logot;
	}


	/**
	 * @return the link_logov
	 */
	public Link getLink_logov() {
		return link_logov;
	}


	/**
	 * @param link_logov the link_logov to set
	 */
	public void setLink_logov(Link link_logov) {
		this.link_logov = link_logov;
	}


	/**
	 * @return the link_padre
	 */
	public Link getLink_padre() {
		return link_padre;
	}


	/**
	 * @param link_padre the link_padre to set
	 */
	public void setLink_padre(Link link_padre) {
		this.link_padre = link_padre;
	}
	
	
	
	
}
