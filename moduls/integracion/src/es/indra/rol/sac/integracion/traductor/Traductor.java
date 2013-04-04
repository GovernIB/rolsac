package  es.indra.rol.sac.integracion.traductor;

import java.net.URLDecoder;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

/**
 * Clase que traduce las propiedades de los beans del m�dulo Rolsac
 * @author Indra
 *
 */
public class Traductor extends AutomaticTranslationService implements Traduccion {

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
	 * Carga los c�digos de Idioma de la capa de negocio para la traducci�n
	 * e inicia una Hashtable para saber el origen-destino de la traducci�n
	 *  
	 */
	public Traductor() throws Exception {
		
    	IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
    	
    	_listLang = idiomaDelegate.listarLenguajes();
    	_listLangTraductor = idiomaDelegate.listarLenguajesTraductor();
 
		iniHshIdiomes();

	}

	/**
	 * M�todo que devuelve la url del servidor de traducci�n Lucy
	 * 
	 * @return _TranslationServerUrl	url del servidor de traducci�n Lucy	
	 */
    public String getTranslationServerUrl() {
        return _translationServerUrl;
    }

    /**
     * M�todo que guarda la url del servidor de traducci�n Lucy
     * 
     * @param translationServer		nombreServidor:puerto donde est� el servidor de traducci�n Lucy
     */
    public void setTranslationServerUrl(String translationServer) {
    	_translationServerUrl = "http://" + translationServer + "/jaxws-AutomaticTranslationService/AutoTranslate";
    } 	
	
	/**
	 * M�todo que devuelve la direcci�n de la traducci�n
	 * 
	 * @return _translationDirection	direcci�n de la traducci�n
	 */
    public String getDirTraduccio() {
        return _translationDirection;
    }

    /**
     * M�todo que guarda la direcci�n de la traducci�n
     * 
     * @param idiomaOrigen	id del idioma de origen (ca, es, en, de, fr)
     * @param idiomaDesti	id del idioma de destino (ca, es, en, de, fr)
     */
    public void setDirTraduccio(String idiomaOrigen, String idiomaDesti) {
    	_translationDirection = _hshIdiomes.get(idiomaOrigen) + "-" + _hshIdiomes.get(idiomaDesti);
    }      
    
    /**
     * M�todo que devuelve el listado de lenguajes de negocio
     * 
     * @return	_listLang	listado de lenguajes de negocio (ca, es, en, de, fr)
     */
	public List<String> getListLang() {
		return _listLang;
	}
	
	/**
	 * M�todo que devuelve el listado de ids de traductor
	 * 
	 * @return _listLangTraductor listado de ids de traductor (CATALAN, SPANISH, ENGLISH, GERMAN, FRENCH)
	 */
	public List<String> getListLangTraductor() {
		return _listLangTraductor;
	}

	/**
	 * M�todo que devuelve la Hashtable que relaciona los lenguajes de negocio con los
	 * ids de traductor
	 * 
	 * @return _hshIdiomes	Hashtable con la relaci�n entre lenguajes de negocio e ids de traductor
	 */
    public Hashtable<String,String> getHshIdiomes() {
    	return _hshIdiomes;
    }		

    
    /**
     * M�todo que inicia la Hastable de relaci�n entre lengujes de negocio
     * e ids de traductor. Esta Hashtable se utiliza para guardar la propiedad
     * _translationDirection
     * 
     * @throws DelegateException
     */
    private void iniHshIdiomes() throws DelegateException {
    	
    	Iterator<String> itLang =  _listLang.iterator();
    	Iterator<String> itLangTraductor = _listLangTraductor.iterator();

        while (itLang.hasNext()){

        	_hshIdiomes.put(itLang.next(), itLangTraductor.next());

        }
    }
    
	
	/**
	 * M�todo que traduce las propiedades de un bean TraduccionProcedimientoLocal origen
	 * y las guarda en un bean TraduccionProcedimientoLocal destino
	 * 
	 * @param procOrigen	bean de traducci�n de procedimiento origen
	 * @param procDesti		bean de traducci�n de procedimiento destino
	 * @return	boolean		devuelve verdadero si la traducci�n finaliza correctamente. Si no devuelve falso.
	 * @throws Exception
	 */
	public boolean traducir(TraduccionProcedimientoLocal procOrigen, TraduccionProcedimientoLocal procDesti) throws Exception {
		try {

			if(null!=procOrigen.getNombre())			procDesti.setNombre(traducir(procOrigen.getNombre(),MODE_TXT));
	    	if(null!=procOrigen.getPlazos())			procDesti.setPlazos(traducir(procOrigen.getPlazos(),MODE_TXT));
	    	if(null!=procOrigen.getResumen())			procDesti.setResumen(traducir(procOrigen.getResumen(),MODE_TXT));
	    	if(null!=procOrigen.getResultat())			procDesti.setResultat(traducir(procOrigen.getResultat(),MODE_TXT));
	    	if(null!=procOrigen.getLugar())				procDesti.setLugar(traducir(procOrigen.getLugar(),MODE_TXT));
	    	if(null!=procOrigen.getDestinatarios())		procDesti.setDestinatarios(traducir(procOrigen.getDestinatarios(),MODE_TXT));
	    	if(null!=procOrigen.getNotificacion())		procDesti.setNotificacion(traducir(procOrigen.getNotificacion(),MODE_TXT));
	    	if(null!=procOrigen.getRequisitos())		procDesti.setRequisitos(traducir(procOrigen.getRequisitos(),MODE_TXT));
	    	if(null!=procOrigen.getObservaciones())		procDesti.setObservaciones(traducir(procOrigen.getObservaciones(),MODE_TXT));
	    	if(null!=procOrigen.getRecursos())			procDesti.setRecursos(traducir(procOrigen.getRecursos(),MODE_TXT));
	    	if(null!=procOrigen.getResolucion())		procDesti.setResolucion(traducir(procOrigen.getResolucion(),MODE_TXT));
	    	if(null!=procOrigen.getSilencio())			procDesti.setSilencio(traducir(procOrigen.getSilencio(),MODE_TXT));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
    	return true;
	}
	
	/**
	 * M�todo que traduce las propiedades de un bean TraduccionFicha origen
	 * y las guarda en un bean TraduccionFicha destino
	 * 
	 * @param fichaOrigen	bean de traducci�n de ficha origen
	 * @param fichaDesti	bean de traducci�n de ficha destino
	 * @return boolean		devuelve verdadero si la traducci�n finaliza correctamente. Si no devuelve falso.
	 * @throws Exception
	 */
	public boolean traducir(TraduccionFicha fichaOrigen, TraduccionFicha fichaDesti) throws Exception {
		try {
	    	if(null!=fichaOrigen.getTitulo())			fichaDesti.setTitulo(traducir(fichaOrigen.getTitulo(),MODE_TXT));
	    	if(null!=fichaOrigen.getDescAbr())			fichaDesti.setDescAbr(traducir(fichaOrigen.getDescAbr(),MODE_HTML));
	    	if(null!=fichaOrigen.getDescripcion())		fichaDesti.setDescripcion(traducir(fichaOrigen.getDescripcion(),MODE_HTML));
			
		} catch (Exception e) {

			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
    	return true;
	}	
	
	/**
	 * M�todo que traduce las propiedades de un bean TraduccionNormativa origen
	 * y las guarda en un bean TraduccionNormativa destino
	 * 
	 * @param normOrigen	bean de traducci�n de normativa origen
	 * @param normDesti		bean de traducci�n de normativa destino
	 * @return boolean		devuelve verdadero si la traducci�n finaliza correctamente. Si no devuelve falso.
	 * @throws Exception
	 */
	public boolean traducir(TraduccionNormativa normOrigen, TraduccionNormativa normDesti) throws Exception {
		
		try {
	    	if(null!=normOrigen.getSeccion())			normDesti.setSeccion(traducir(normOrigen.getSeccion(),MODE_TXT));
	    	if(null!=normOrigen.getApartado())			normDesti.setApartado(traducir(normOrigen.getApartado(),MODE_TXT));
	    	if(null!=normOrigen.getTitulo())			normDesti.setTitulo(traducir(normOrigen.getTitulo(),MODE_TXT));
	    	if(null!=normOrigen.getEnlace())			normDesti.setEnlace(traducir(normOrigen.getEnlace(),MODE_TXT));
	    	if(null!=normOrigen.getObservaciones())		normDesti.setObservaciones(traducir(normOrigen.getObservaciones(),MODE_TXT));
			
		} catch (Exception e) {

			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
    	return true;
	}
	
	
	/**
	 * M�todo que traduce las propiedades de un bean TraduccionTramite origen
	 * y las guarda en un bean TraduccionTramite destino
	 * 
	 * @param normOrigen	bean de traducci�n de tramite origen
	 * @param normDesti		bean de traducci�n de tramite destino
	 * @return boolean		devuelve verdadero si la traducci�n finaliza correctamente. Si no devuelve falso.
	 * @throws Exception
	 */
	public boolean traducir(TraduccionTramite tramOrigen, TraduccionTramite tramDesti) throws Exception {
		
		try {
	    	if(null!=tramOrigen.getNombre())			tramDesti.setNombre(traducir(tramOrigen.getNombre(),MODE_TXT));
	    	if(null!=tramOrigen.getDescripcion())		tramDesti.setDescripcion(traducir(tramOrigen.getDescripcion(),MODE_TXT));
	    	if(null!=tramOrigen.getDocumentacion())		tramDesti.setDocumentacion(traducir(tramOrigen.getDocumentacion(),MODE_TXT));
	    	if(null!=tramOrigen.getPlazos())			tramDesti.setPlazos(traducir(tramOrigen.getPlazos(),MODE_TXT));
	    	if(null!=tramOrigen.getRequisits())			tramDesti.setRequisits(traducir(tramOrigen.getRequisits(),MODE_TXT));
	    	if(null!=tramOrigen.getLugar())				tramDesti.setLugar(traducir(tramOrigen.getLugar(),MODE_TXT));

	    	
		} catch (Exception e) {

			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
    	return true;
	}	
		

	/**
	 * Método que parametriza el traductor según el tipo de texto y envía el texto al método translate.
	 * 
	 * @param textTraduccio	texto a traducir.
	 * @param modeTraduccio	modo de traducción (HTML, TXT).
	 * @return String texto traducido.
	 * @throws Exception lanza una nueva excepción proceso de traducción no ha funcionado.
	 */
	private String traducir(String textTraduccio, String modeTraduccio) throws Exception {
  
		try {
			
			// El texto recibido está codificado con la función JavaScript nativa encodeURIComponent().
			// Toca decodificarlo primero antes de pasárselo al traductor.
			textTraduccio = URLDecoder.decode(textTraduccio, "UTF-8");

			if (modeTraduccio == MODE_HTML) {
				
				textTraduccio = TAG_INI_HTML + textTraduccio + TAG_FI_HTML;
				_colorMarkups = ACTIVE;
				_markUnknowns = ACTIVE;
				_markAlternatives = ACTIVE;
				
			} else {
					
				_colorMarkups = INACTIVE;
				_markUnknowns = INACTIVE;
				_markAlternatives = INACTIVE;
				
			}

			// Condición añadida debido a un bug de la aplicación Lucy.
			// Si el texto que se pasa es inferior a 2 caracteres, no se traduce.
			if (textTraduccio.length() <= 2)
				return textTraduccio;
			else
				return translate(textTraduccio);
    	
		} catch (Exception e) {

			log.error(e.getMessage());
			throw new Exception(e);
			
		}
		
	}
	
	/**
	 * Método que asigna las propiedades de objeto a null para su posterior eliminación.
	 */
	public void dispose() {
		
    	_listLang = null;
    	_listLangTraductor = null;
		_hshIdiomes = null;
		
	}
    
}
