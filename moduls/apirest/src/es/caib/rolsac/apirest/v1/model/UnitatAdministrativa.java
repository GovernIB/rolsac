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
@ApiModel(value = "Unidad Administrativa", description = "Definición de la clase Unidad Administrativa")
public class UnitatAdministrativa extends EntidadBase {
	 
	
	/** abreviatura. **/
	@ApiModelProperty(value = "abreviatura", dataType = "java.lang.String", required = false)
    private java.lang.String abreviatura;

	/** . **/
	@ApiModelProperty(value = "businessKey", dataType = "java.lang.String", required = false)
    private java.lang.String businessKey;

	/** claveHita **/
	@ApiModelProperty(value = "claveHita", dataType = "java.lang.String", required = false)
    private java.lang.String claveHita;

	/** codigoEstandar **/
	@ApiModelProperty(value = "codigoEstandar", dataType = "java.lang.String", required = false)
    private java.lang.String codigoEstandar;

	/** dominio **/
	@ApiModelProperty(value = "dominio", dataType = "java.lang.String", required = false)
    private java.lang.String dominio;

	/** email **/
	@ApiModelProperty(value = "email", dataType = "java.lang.String", required = false)
    private java.lang.String email;

    private java.lang.Long espacioTerrit;

	/** fax **/
	@ApiModelProperty(value = "fax", dataType = "java.lang.String", required = false)
    private java.lang.String fax;

	/** fotog **/
	@ApiModelProperty(value = "fotog", dataType = "java.lang.Long", required = false)
    private java.lang.Long fotog;

	/** fotop **/
	@ApiModelProperty(value = "fotop", dataType = "java.lang.Long", required = false)
    private java.lang.Long fotop;

	/** codigo **/
	@ApiModelProperty(value = "codigo", dataType = "java.lang.Long", required = false)
    private java.lang.Long codigo;

	/** logoh **/
	@ApiModelProperty(value = "logoh", dataType = "java.lang.Long", required = false)
    private java.lang.Long logoh;

	/** logos **/
	@ApiModelProperty(value = "logos", dataType = "java.lang.Long", required = false)
    private java.lang.Long logos;

	/** logot **/
	@ApiModelProperty(value = "logot", dataType = "java.lang.Long", required = false)
    private java.lang.Long logot;

	/** logov **/
	@ApiModelProperty(value = "logov", dataType = "java.lang.Long", required = false)
    private java.lang.Long logov;

	/** nombre **/
	@ApiModelProperty(value = "nombre", dataType = "java.lang.String", required = false)
    private java.lang.String nombre;

	/** numfoto1 **/
	@ApiModelProperty(value = "numfoto1", dataType = "java.lang.Integer", required = false)
    private java.lang.Integer numfoto1;

	/** numfoto2 **/
	@ApiModelProperty(value = "numfoto2", dataType = "java.lang.Integer", required = false)
    private java.lang.Integer numfoto2;

	/** numfoto3 **/
	@ApiModelProperty(value = "numfoto3", dataType = "java.lang.Integer", required = false)
    private java.lang.Integer numfoto3;

	/** numfoto4 **/
	@ApiModelProperty(value = "numfoto4", dataType = "java.lang.Integer", required = false)
    private java.lang.Integer numfoto4;

	/** orden **/
	@ApiModelProperty(value = "orden", dataType = "java.lang.Long", required = false)
    private java.lang.Long orden;
	
	/** padre **/
	@ApiModelProperty(value = "padre", dataType = "java.lang.Long", required = false)
    private java.lang.Long padre;

	/** presentacion **/
	@ApiModelProperty(value = "presentacion", dataType = "java.lang.String", required = false)
    private java.lang.String presentacion;

	/** responsable **/
	@ApiModelProperty(value = "responsable", dataType = "java.lang.String", required = false)
    private java.lang.String responsable;

	/** responsableEmail **/
	@ApiModelProperty(value = "responsableEmail", dataType = "java.lang.String", required = false)
    private java.lang.String responsableEmail;

	/** sexoResponsable **/
	@ApiModelProperty(value = "sexoResponsable", dataType = "java.lang.Integer", required = false)
    private java.lang.Integer sexoResponsable;

	/** cvResponsable **/
	@ApiModelProperty(value = "cvResponsable", dataType = "java.lang.String", required = false)
    private java.lang.String cvResponsable;

	/** telefono **/
	@ApiModelProperty(value = "telefono", dataType = "java.lang.String", required = false)
    private java.lang.String telefono;
	
	/** tratamiento **/
/*	@ApiModelProperty(value = "tratamiento", dataType = "java.lang.Long", required = false)
    private java.lang.Long tratamiento;
*/
	/** tratamiento **/
    private Tratamiento tratamiento;
	
	
	/** url **/
	@ApiModelProperty(value = "url", dataType = "java.lang.String", required = false)
    private java.lang.String url;

	/** validacion **/
	@ApiModelProperty(value = "validacion", dataType = "java.lang.Integer", required = false)
    private java.lang.Integer validacion;

	/** idioma **/
	@ApiModelProperty(value = "idioma", dataType = "java.lang.String", required = false)
    private java.lang.String idioma;

	/** codigoDIR3 **/
	@ApiModelProperty(value = "codigoDIR3", dataType = "java.lang.String", required = false)
    private java.lang.String codigoDIR3;
	
	
	public UnitatAdministrativa (org.ibit.rol.sac.model.UnidadAdministrativa ua, String urlBase,String idioma,boolean hateoasEnabled ) {
		super(ua, urlBase, idioma, hateoasEnabled);
		
		//this.fill( ua, urlBase, idioma, hateoasEnabled);
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
		
/*		if(!StringUtils.isEmpty(idioma)) {			
			TraduccionUA traUA = (TraduccionUA)ua.getTraduccion(idioma);
			if(traUA != null) {
				this.presentacion = traUA.getPresentacion();
				this.nombre = traUA.getNombre();
				this.abreviatura = traUA.getAbreviatura();
				this.cvResponsable = traUA.getCvResponsable();
				this.url = traUA.getUrl();
			}else {
				this.presentacion = "";
				this.nombre = "";
				this.abreviatura = "";
				this.cvResponsable = "";
				this.url = "";
			}			
		}else {
			this.presentacion = "";
			this.nombre = "";
			this.abreviatura = "";
			this.cvResponsable = "";
			this.url = "";
		}
		
		this.businessKey = ua.getBusinessKey();
		this.claveHita = ua.getClaveHita();
		this.codigoEstandar = ua.getCodigoEstandar();
		this.dominio = ua.getDominio();
		this.email = ua.getEmail();
		this.fax = ua.getFax();		
		this.orden = new Long(ua.getOrden());
		this.padre = (ua.getPadre()==null)?new Long(-1):ua.getPadre().getId();
		this.responsable = ua.getResponsable();
		this.responsableEmail = ua.getResponsableEmail();
		this.sexoResponsable = ua.getSexoResponsable();
		this.telefono = ua.getTelefono();
		
		if(ua.getTratamiento()!=null ) {
			this.tratamiento = new Tratamiento();
			this.tratamiento.setCodigo(ua.getTratamiento().getId());
			this.tratamiento.setCodigoEstandar(ua.getTratamiento().getCodigoEstandar());
			if(!StringUtils.isEmpty(idioma) && ua.getTratamiento().getTraduccion(idioma)!=null) {
				TraduccionTratamiento tt =(TraduccionTratamiento) ua.getTratamiento().getTraduccion(idioma);
				this.tratamiento.setCargoF(tt.getCargoF());
				this.tratamiento.setCargoM(tt.getCargoM());
				this.tratamiento.setTipo(tt.getTipo());
				this.tratamiento.setTratamientoF(tt.getTratamientoF());
				this.tratamiento.setTratamientoM(tt.getTratamientoM());				
			}
		}
				
		this.validacion = ua.getValidacion();		
		this.codigoDIR3 = ua.getCodigoDIR3();
		
		this.fotog = this.getIdArchivo(ua.getFotog());
		this.fotop = this.getIdArchivo(ua.getFotop());
		this.logoh = this.getIdArchivo(ua.getLogoh());
		this.logos = this.getIdArchivo(ua.getLogos());
		this.logot = this.getIdArchivo(ua.getLogot());
		this.logov = this.getIdArchivo(ua.getLogov());
		
		this.numfoto1 = ua.getNumfoto1();
		this.numfoto2 = ua.getNumfoto2();
		this.numfoto3 = ua.getNumfoto3();
		this.numfoto4 = ua.getNumfoto4();
		
		this.idioma = ua.getIdioma();
		this.codigo = ua.getId();
				
		if(ua.getEspacioTerrit()!=null) {
			this.espacioTerrit = ua.getEspacioTerrit().getId();		
		}*/
		
		//super.copiaPropiedadesDeEntity(ua, idioma);
		
		//generaLinks(urlBase);
		
		
		
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
		
		this.addLink(this.idioma, Constantes.ENTIDAD_IDIOMA, Constantes.URL_IDIOMA, urlBase);
		this.addLink(this.getCodigo(), Constantes.ENTIDAD_UA, Constantes.URL_UA, urlBase,"codigo");
		this.addLink(this.espacioTerrit, Constantes.ENTIDAD_ESPACIO_TERRITORIAL, Constantes.URL_ESPACIO_TERRITORIAL, urlBase,"espacioTerrit");
		
		this.addLink(this.fotog, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"fotog" );
		this.addLink(this.fotop, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"fotop" );
		this.addLink(this.logoh, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"logoh" );
		this.addLink(this.logos, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"logos" );		
		this.addLink(this.logot, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"logot" );		
		this.addLink(this.logov, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"logov" );
		
		this.addLink(this.numfoto1, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"numfoto1" );
		this.addLink(this.numfoto2, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"numfoto2" );
		this.addLink(this.numfoto3, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"numfoto3" );
		this.addLink(this.numfoto4, Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,"numfoto4" );

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
	public java.lang.Long getCodigo() {
		return codigo;
	}


	/**
	 * @param codigo the id to set
	 */
	public void setCodigo(java.lang.Long codigo) {
		this.codigo = codigo;
	}
	//Creamos este metodo para que se recupere el valor automaticamente por parte de copiproperty
	public void setId(java.lang.Long codigo) {
		this.codigo = codigo;
	}


	/**
	 * @return the logoh
	 */
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
	
	
	
	
}
