package  es.indra.rol.sac.integracion.traductor;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;


/**
 * Clase que traduce las propiedades de los beans del módulo Rolsac
 * @author Indra
 *
 */
public class Traductor extends AutomaticTranslationService implements Traduccion
{
	private static final long serialVersionUID = 4007299757118205848L;
	protected static Log log = LogFactory.getLog(Traductor.class);
	private List<String> _listLang, _listLangTraductor;
	private Hashtable<String,String> _hshIdiomes = new Hashtable<String,String>();
	
	private static final String MODE_TXT = "TXT",
								MODE_HTML = "HTML",
								TAG_INI_HTML =  "<HTML><BODY>",
								TAG_FI_HTML = "</BODY></HTML>";
	
	
	/**
	 * Constructor por defecto de la clase. 
	 * 
	 * Carga los códigos de Idioma de la capa de negocio para la traducción
	 * e inicia una Hashtable para saber el origen-destino de la traducción
	 * 
	 */
	public Traductor() throws Exception
	{
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		
		_listLang = idiomaDelegate.listarLenguajes();
		_listLangTraductor = idiomaDelegate.listarLenguajesTraductor();
		
		iniHshIdiomes();
	}
	
	
	/**
	 * Método que devuelve la url del servidor de traducción Lucy
	 * 
	 * @return _TranslationServerUrl url del servidor de traducción Lucy	
	 */
    public String getTranslationServerUrl()
    {
        return _translationServerUrl;
    }
    
    
    /**
     * Método que guarda la url del servidor de traducción Lucy
     * 
     * @param translationServer	nombreServidor:puerto donde está el servidor de traducción Lucy
     */
    public void setTranslationServerUrl(String translationServer)
    {
    	_translationServerUrl = "http://" + translationServer + "/jaxws-AutomaticTranslationService/AutoTranslate";
    }
    
    
    /**
	 * Método que devuelve la dirección de la traducción
	 * 
	 * @return _translationDirection dirección de la traducción
	 */
    public String getDirTraduccio()
    {
        return _translationDirection;
    }
    
    
    /**
     * Método que guarda la direcci�n de la traducción
     * 
     * @param idiomaOrigen	id del idioma de origen (ca, es, en, de, fr)
     * @param idiomaDesti	id del idioma de destino (ca, es, en, de, fr)
     */
    public void setDirTraduccio(String idiomaOrigen, String idiomaDesti)
    {
    	_translationDirection = _hshIdiomes.get(idiomaOrigen) + "-" + _hshIdiomes.get(idiomaDesti);
    }
    
    
    /**
     * Método que devuelve el listado de lenguajes de negocio
     * 
     * @return	_listLang	listado de lenguajes de negocio (ca, es, en, de, fr)
     */
	public List<String> getListLang()
	{
		return _listLang;
	}
	
	
	/**
	 * Método que devuelve el listado de ids de traductor
	 * 
	 * @return _listLangTraductor listado de ids de traductor (CATALAN, SPANISH, ENGLISH, GERMAN, FRENCH)
	 */
	public List<String> getListLangTraductor()
	{
		return _listLangTraductor;
	}
	
	
	/**
	 * Método que devuelve la Hashtable que relaciona los lenguajes de negocio con los
	 * ids de traductor
	 * 
	 * @return _hshIdiomes	Hashtable con la relación entre lenguajes de negocio e ids de traductor
	 */
    public Hashtable<String,String> getHshIdiomes()
    {
    	return _hshIdiomes;
    }
    
    
    /**
     * Método que inicia la Hastable de relaci�n entre lengujes de negocio
     * e ids de traductor. Esta Hashtable se utiliza para guardar la propiedad
     * _translationDirection
     * 
     * @throws DelegateException
     */
    private void iniHshIdiomes() throws DelegateException
    {
    	Iterator<String> itLang =  _listLang.iterator();
    	Iterator<String> itLangTraductor = _listLangTraductor.iterator();
    	
    	while (itLang.hasNext()) {
    		_hshIdiomes.put(itLang.next(), itLangTraductor.next());
    	}
    }
    
    
    /**
     * Traductor que traduce para todos los idiomas indicandole el de origen
     * @param traduccionOrigen
     * @param langDefault
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> translate(Traduccion traduccionOrigen, String langDefault) throws Exception
    {
		List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
		
		for (String lang : getListLang()) {
			if (!langDefault.equalsIgnoreCase(lang)) {
				Traduccion traduccioDesti = traduccionOrigen.getClass().newInstance();
				this.setDirTraduccio(langDefault, lang);
				this.traducir(traduccionOrigen, traduccioDesti);
				HashMap<String, Object> traduccio = new HashMap<String, Object>();
				traduccio.put("lang", lang);
				traduccio.put("traduccio", traduccionOrigen.getClass().cast(traduccioDesti));
				
				traduccions.add(traduccio);
			}
		}
		
		return traduccions;
	}
    
    
    /**
     * Traductor que traduce para todos los idiomas indicandole el de origen, especial para los que contienen campos tinys
     * @param traduccionOrigen
     * @param langDefault
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> translateTiny(Traduccion traduccionOrigen, String langDefault) throws Exception
    {
		List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
		
		for (String lang : getListLang()) {
			if (!langDefault.equalsIgnoreCase(lang)) {
				this.setDirTraduccio(langDefault, lang);
				HashMap<String, Object> traduccio = new HashMap<String, Object>();
				
				if (traduccionOrigen instanceof TraduccionFicha) {
					TraduccionFicha traduccionFicha = (TraduccionFicha) traduccionOrigen;
					TraduccionFicha traduccionDesti = new TraduccionFicha();
					if (null != traduccionFicha.getTitulo()) {
						traduccionDesti.setTitulo(traducir(traduccionFicha.getTitulo(), MODE_TXT));
					}
					if (null != traduccionFicha.getDescAbr()) {
						traduccionDesti.setDescAbr(traducir(traduccionFicha.getDescAbr(), MODE_HTML));
					}
					if (null != traduccionFicha.getDescripcion()) {
						traduccionDesti.setDescripcion(traducir(traduccionFicha.getDescripcion(), MODE_HTML));
					}
					traduccio.put("traduccio", traduccionDesti);
					
				} else if (traduccionOrigen instanceof TraduccionUA) {
					TraduccionUA traduccionUA = (TraduccionUA) traduccionOrigen;
					TraduccionUA traduccionDesti = new TraduccionUA();
					if (null != traduccionUA.getNombre()) {
						traduccionDesti.setNombre(traducir(traduccionUA.getNombre(), MODE_TXT));
					}
					if (null != traduccionUA.getAbreviatura()) {
						traduccionDesti.setAbreviatura(traducir(traduccionUA.getAbreviatura(), MODE_TXT));
					}
					if (null != traduccionUA.getPresentacion()) {
						traduccionDesti.setPresentacion(traducir(traduccionUA.getPresentacion(), MODE_HTML));
					}
					if (null != traduccionUA.getCvResponsable()) {
						traduccionDesti.setCvResponsable(traducir(traduccionUA.getCvResponsable(), MODE_HTML));
					}
					traduccio.put("traduccio", traduccionDesti);
				} else {
					return traduccions;
				}
				
				traduccio.put("lang", lang);
				traduccions.add(traduccio);
			}
		}
		
		return traduccions;
    }
    
    
    /**
	 * Método genérico que sustituye a todos los anteriores para realizar las traducciones
	 * Funciona si no hay campos tiny
	 * @param orig	bean de traducción de tramite origen
	 * @param dest	bean de traducción de tramite destino
	 * @return
	 * @throws Exception
	 */
	private void traducir(Traduccion orig, Traduccion dest) throws Exception
	{
		Class<? extends Traduccion> c = orig.getClass();
		for (Method m : c.getMethods()) {
			if (m.getName().startsWith("get") && !m.getName().contains("Class")) {
				Object r = m.invoke(c.cast(orig));
				if (r != null) {
					String t = traducir((String) r, MODE_TXT);
					String setterMethodName = m.getName().replaceFirst("get", "set");
					for (Method mDest : c.getMethods()) {
						if (mDest.getName().equals(setterMethodName)) {
							mDest.invoke(c.cast(dest), t);
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Método que parametriza el traductor según el tipo de texto y envía el texto al método translate.
	 * 
	 * @param textTraduccio	texto a traducir.
	 * @param modeTraduccio	modo de traducción (HTML, TXT).
	 * @return String texto traducido.
	 * @throws Exception lanza una nueva excepción proceso de traducción no ha funcionado.
	 */
	private String traducir(String textTraduccio, String modeTraduccio) throws Exception
	{
		try {
			// El texto recibido está codificado con la función JavaScript nativa encodeURIComponent().
			// Toca decodificarlo primero antes de pasárselo al traductor.
			textTraduccio = URLDecoder.decode(textTraduccio, "UTF-8");
			boolean hasToAddTagsToTranslate = false;
			
			if (modeTraduccio == MODE_HTML) {
				textTraduccio = TAG_INI_HTML + textTraduccio + TAG_FI_HTML;
				_colorMarkups = ACTIVE;
				_markUnknowns = ACTIVE;
				_markAlternatives = ACTIVE;
				
			} else {
				hasToAddTagsToTranslate = !textTraduccio.startsWith("<p>");
				if (hasToAddTagsToTranslate) {
					textTraduccio = montarTranslate(textTraduccio);
				}
				
				_colorMarkups = INACTIVE;
				_markUnknowns = INACTIVE;
				_markAlternatives = INACTIVE;
			}
			
			// Condición añadida debido a un bug de la aplicación Lucy.
			// Si el texto que se pasa es inferior a 2 caracteres, no se traduce.
			if (textTraduccio.length() <= 2) {
				return textTraduccio;
				
			} else {
				String translation = translate(textTraduccio);
				if (hasToAddTagsToTranslate) {
					translation = desmontarTranslate(translation);
				}
				return translation;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e);
		}
	}
	
	
	/**
	 * Método que asigna las propiedades de objeto a null para su posterior eliminación.
	 */
	public void dispose()
	{
		_listLang = null;
    	_listLangTraductor = null;
		_hshIdiomes = null;
	}
	
	
	/**
	 * Este método resuelve un fallo en las traducciones, parece que en las trablas
	 * de traducciones hay puesto a piñon los tags "<p>" y "</p>
	 * 
	 * @param inPut testo que se quiere escapar
	 * @return String compuesto preparado para poder traducirlo correctamente
	 */
	private static String montarTranslate(String inPut)
	{
		StringBuilder encode = new StringBuilder("<p>");
		encode.append(StringEscapeUtils.escapeXml(inPut));
		encode.append("</p>");
		return encode.toString();
	}
	
	
	/**
	 * Este método desmonta los tags añadidos por el anterior
	 * @param inPut
	 * @return
	 */
	private static String desmontarTranslate(String inPut)
	{
		return inPut.subSequence(3, inPut.length()-4).toString();
	}
	
}
