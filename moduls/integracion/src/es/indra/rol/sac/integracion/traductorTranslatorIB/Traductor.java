package es.indra.rol.sac.integracion.traductorTranslatorIB;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import es.caib.translatorib.api.v1.model.Idioma;
import es.caib.translatorib.api.v1.model.ParametrosTraduccionTexto;
import es.caib.translatorib.api.v1.model.ResultadoTraduccionTexto;
import es.caib.translatorib.api.v1.model.TipoEntrada;

/**
 * Clase que traduce las propiedades de los beans del módulo Rolsac
 *
 * @author Indra
 *
 */
public class Traductor implements Traduccion {
	private static final long serialVersionUID = 4007299757118205848L;
	protected static Log log = LogFactory.getLog(Traductor.class);
	private IdiomaDelegate _idiomaDelegate;
	private List<String> _listLang;
	private static final String MODE_TXT = "TXT", MODE_HTML = "HTML";

	/**
	 * Constructor por defecto de la clase.
	 *
	 * Carga los códigos de Idioma de la capa de negocio para la traducción e inicia
	 * una Hashtable para saber el origen-destino de la traducción
	 *
	 */
	public Traductor() throws Exception {
		// iniHshIdiomes();
	}

	//
	// /**
	// * Método que inicia la Hastable de relaci�n entre lengujes de negocio e ids
	// de
	// * traductor. Esta Hashtable se utiliza para guardar la propiedad
	// * _translationDirection
	// *
	// * @throws DelegateException
	// */
	// private void iniHshIdiomes() throws DelegateException {
	//
	// final IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
	//
	// List<String> _listLang = idiomaDelegate.listarLenguajes();
	// List<String> _listLangTraductor = idiomaDelegate.listarLenguajesTraductor();
	//
	// final Iterator<String> itLang = _listLang.iterator();
	// final Iterator<String> itLangTraductor = _listLangTraductor.iterator();
	//
	// while (itLang.hasNext()) {
	// _hshIdiomes.put(itLang.next(), itLangTraductor.next());
	// }
	// }

	/**
	 * Traductor que traduce para todos los idiomas indicandole el de origen
	 *
	 * @param traduccionOrigen
	 * @param langDefault
	 * @return
	 * @throws Exception
	 */
	// @Override
	public List<Map<String, Object>> translate(final Traduccion traduccionOrigen, final String langDefault)
			throws Exception {
		log.debug("Entra a translate");
		final List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
		for (final String lang : getListLang()) {
			if (!langDefault.equalsIgnoreCase(lang)) {
				final Traduccion traduccioDesti = traduccionOrigen.getClass().newInstance();
				this.traducir(traduccionOrigen, traduccioDesti);
				final HashMap<String, Object> traduccio = new HashMap<String, Object>();
				traduccio.put("lang", lang);
				traduccio.put("traduccio", traduccionOrigen.getClass().cast(traduccioDesti));

				traduccions.add(traduccio);
			}
		}
		log.debug("Sale a translate");

		return traduccions;
	}

	/**
	 * Traductor que traduce para todos los idiomas indicandole el de origen,
	 * especial para los que contienen campos tinys
	 *
	 * @param traduccionOrigen
	 * @param langDefault
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> translateTiny(final Traduccion traduccionOrigen, final String langDefault)
			throws Exception {
		log.debug("Entra a translateTiny");
		final List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();

		for (final String lang : getListLang()) {
			if (!langDefault.equalsIgnoreCase(lang)) {
				final HashMap<String, Object> traduccio = new HashMap<String, Object>();

				if (traduccionOrigen instanceof TraduccionFicha) {
					final TraduccionFicha traduccionFicha = (TraduccionFicha) traduccionOrigen;
					final TraduccionFicha traduccionDesti = new TraduccionFicha();
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
					final TraduccionUA traduccionUA = (TraduccionUA) traduccionOrigen;
					final TraduccionUA traduccionDesti = new TraduccionUA();
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

		log.debug("Sale a translateTiny");

		return traduccions;
	}

	private List<String> getListLang() throws DelegateException {

		if (_listLang == null) {
			if (_idiomaDelegate == null) {
				_idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			}
			_listLang = _idiomaDelegate.listarLenguajes();
		}
		return _listLang;
	}

	/**
	 * Método genérico que sustituye a todos los anteriores para realizar las
	 * traducciones Funciona si no hay campos tiny
	 *
	 * @param orig
	 *            bean de traducción de tramite origen
	 * @param dest
	 *            bean de traducción de tramite destino
	 * @return
	 * @throws Exception
	 */
	private void traducir(final Traduccion orig, final Traduccion dest) throws Exception {
		final Class<? extends Traduccion> c = orig.getClass();
		for (final Method m : c.getMethods()) {
			if (m.getName().startsWith("get") && !m.getName().contains("Class")) {
				final Object r = m.invoke(c.cast(orig));
				if (r != null) {
					final String t = traducir((String) r, MODE_TXT);
					final String setterMethodName = m.getName().replaceFirst("get", "set");
					for (final Method mDest : c.getMethods()) {
						if (mDest.getName().equals(setterMethodName)) {
							mDest.invoke(c.cast(dest), t);
						}
					}
				}
			}
		}
	}

	/**
	 * Método que parametriza el traductor según el tipo de texto y envía el texto
	 * al método translate.
	 *
	 * @param textTraduccio
	 *            texto a traducir.
	 * @param modeTraduccio
	 *            modo de traducción (HTML, TXT).
	 * @return String texto traducido.
	 * @throws Exception
	 *             lanza una nueva excepción proceso de traducción no ha funcionado.
	 */
	private String traducir(final String textTraduccio, final String modeTraduccio) throws Exception {
		log.debug("Entra a traduir");
		final Client client = Client.create();

		final String user = System.getProperty("es.caib.rolsac.translatorib.user");
		final String password = System.getProperty("es.caib.rolsac.translatorib.pass");
		final String url = System.getProperty("es.caib.rolsac.translatorib.url");
		client.addFilter(new HTTPBasicAuthFilter(user, password));
		final ParametrosTraduccionTexto parametros = new ParametrosTraduccionTexto();
		parametros.setIdiomaEntrada(Idioma.CATALAN);
		parametros.setIdiomaSalida(Idioma.CASTELLANO);
		parametros.setTextoEntrada(textTraduccio);
		if (modeTraduccio == MODE_TXT) {
			parametros.setTipoEntrada(TipoEntrada.TEXTO_PLANO);
		} else {
			parametros.setTipoEntrada(TipoEntrada.HTML);
		}
		final WebResource resource2 = client.resource(url);
		final ResultadoTraduccionTexto resultadoTexto = resource2.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.WILDCARD).post(ResultadoTraduccionTexto.class, parametros);

		log.debug("Sale a traduir");
		return resultadoTexto.getTextoTraducido();

	}

	/**
	 * Método que asigna las propiedades de objeto a null para su posterior
	 * eliminación.
	 */
	public void dispose() {
		_listLang = null;
	}

}
