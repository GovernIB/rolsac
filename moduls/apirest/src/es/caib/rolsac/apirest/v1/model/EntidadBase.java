package es.caib.rolsac.apirest.v1.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * RespuestaBase. Estructura de respuesta que contiene la información comun a todas las respuestas.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "EntidadBase", description = "Entidad Base")
public class EntidadBase {
	
    protected List<String> SETTERS_INVALIDS = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

	{
        add("setSerializer");
        add("setDeserializer");
        add("setTypeDesc");
    }};
    
	 
    private static Log log = LogFactory.getLog(EntidadBase.class);
    
    

	
	

	

	
	@ApiModelProperty(hidden = true)
	@XmlTransient
	protected boolean hateoasEnabled;
	
	
	
	public EntidadBase() {		
		super();
		addSetersInvalidos();
	}
		
	/**
	 * Permite generar una clase a partir de los resultados del EJB (tr)
	 * @param tr Origen de datos, clase de la que copiar las propiedades (deben llamarse igual que en la clase actual)
	 * @param urlBase
	 * @param idioma
	 * @param hateoasEnabled
	 */
	public <T> EntidadBase(T tr, String urlBase,String idioma,boolean hateoasEnabled) {
		super();
		this.fill( tr, urlBase, idioma, hateoasEnabled);
	}
	
	/**
	 *  Función que permite copiar los valores de las propiedades de tr en la clase actual
	 *  asigna el hateoas,copia las propiedades y genera los links
	 * @param tr
	 * @param urlBase
	 * @param idioma
	 * @param hateoasEnabled
	 */
	public <T> void fill(T tr, String urlBase, String idioma, boolean hateoasEnabled) {
		setHateoasEnabled(hateoasEnabled);
		copiaPropiedadesDeEntity(tr, idioma);
		generaLinks(urlBase);
	}

	/** Status a retornar. **/
	@ApiModelProperty(value = "Links", required = false)
	private ArrayList<Link> links=null;
	

	/**
	 * @return the links
	 */
	public ArrayList<Link> getLinks() {
		return this.links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}
	
	
	/**
	 * Añade el link especificado si el modo hateoas está activado y el codigo no es null
	 * 
	 * @param codigo codigo a usar
	 * @param entidad entidad a la que hace referencia el enlace
	 * @param url url relativa de acceso al objeto
	 * @param urlBase urlbase que unida a la url forman una url real. campo opcional, por defecto se usa la existente en el fichero properties
	 * @param descripcion campo al que hace referencia el enlace.
	 */
	public void addLink(String codigo, String entidad, String url, String urlBase, String descripcion) {
		if(hateoasEnabled) {
			if(this.links==null) {
				this.links = new ArrayList<Link>();
			}		
			if(codigo!=null) {
				this.links.add(new Link(entidad,codigo,(StringUtils.isEmpty(urlBase)?Constantes.URL_BASE:urlBase) + url.replace("{0}", codigo),descripcion));
			}	
		}
		
	}
	/**
	 *  Añade el link especificado si el modo hateoas está activado y el codigo no es null
	 */
	public void addLink(Long codigo, String entidad, String url, String urlBase, String descripcion) {	
		if(codigo!=null) {
			this.addLink(codigo.toString(),entidad,url,urlBase,descripcion);
		}
	}
	/**
	 *  Añade el link especificado si el modo hateoas está activado y el codigo no es null
	 */
	public void addLink(Integer codigo, String entidad, String url, String urlBase, String descripcion) {	
		if(codigo!=null) {
			this.addLink(codigo.toString(),entidad,url,urlBase,descripcion);
		}
	}
	/**
	 *  Añade el link especificado si el modo hateoas está activado y el codigo no es null. 
	 */
	public void addLink(String codigo, String entidad, String url, String urlBase) {
		this.addLink(codigo, entidad, url, urlBase,null);
	}
	
	/**
	 *  Añade el link especificado si el modo hateoas está activado y el codigo no es null. incluyendo la 
	 */
	public void addLinkArchivo(Long codigo,String descripcion, String urlBase) {
		if(codigo!=null) {
			this.addLink(codigo.toString(), Constantes.ENTIDAD_ARCHIVO, Constantes.URL_ARCHIVO, urlBase,descripcion );
		}
	}
	
	/**
	 * Si el objeto no es nulo retorna el Id (codigo) del archivo
	 * @param archivo
	 * @return
	 */
	public Long getIdArchivo(org.ibit.rol.sac.model.Archivo archivo) {		
		return (archivo!=null)?archivo.getId():null;
	}

	/**
	 * @return the hateoasEnabled
	 */
	@XmlTransient
	public boolean isHateoasEnabled() {
		return hateoasEnabled;
	}

	/**
	 * @param hateoasEnabled the hateoasEnabled to set
	 */
	public void setHateoasEnabled(boolean hateoasEnabled) {
		this.hateoasEnabled = hateoasEnabled;
	}
	
	public void copiaPropiedadesDeEntity(Object entity,String lang) {
		if (entity == null) {
            return;
        }
		try {			
			Method[] dtoMethods = this.getClass().getMethods();
			Method method;
			StringBuilder errores = new StringBuilder();

			for (int i = 0; i < dtoMethods.length; i++) {
				method = dtoMethods[i];
				if (method.getName().startsWith("set") && !SETTERS_INVALIDS.contains(method.getName())) {
					try {
						Utiles.copyProperty(entity, this, method, lang);
					}catch (Exception e) {
						errores.append("Error al copiar la propiedad:");
						errores.append(method.getName());
						errores.append(";");						
					}
					
				}
			}
			if (errores.length()>0) {
				throw new Exception(errores.toString());
			}
		} catch (Exception e) {
			log.error("Error al copiar las propiedades en " + this.getClass() + " a partir de " + entity.getClass() + ".-->" +  e.getMessage(), e);
		}
	}
	
    /**
     * Función que permite añadir nuevos setters a la lista de setters a omitir al volcar las propiedades de
     * la clase referenciada en el constructor como origen de datos  
     */
	protected void addSetersInvalidos() {
	}
	
	/**
	 * Función que permite especificar los links a las diferentes entidades.
	 * debe sobreescribirse para incluirlos.
	 * @param urlBase
	 */
	protected void generaLinks(String urlBase) {
		
		
	}
	
	
	
	
	
}
