package es.caib.rolsac.apirest.v1.utiles;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaAgrupacioFetVital;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaAgrupacioMateries;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaArxius;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaBase;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaBulletins;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaCatalegDocuments;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaDocuments;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaDocumentsNormatives;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaDocumentsTramits;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaEdificis;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaEnllacos;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaEspaisTerritorials;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaFamilies;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaFetsVitals;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaFitxes;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaFitxesUA;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaFormularis;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaIconesFamilies;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaIdioma;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaMateriaAgrupacio;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaMateries;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaNormatives;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaPerfil;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaPersonal;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaProcediments;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaPublicsObjectius;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaSeccions;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaServeis;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaSimple;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaTipus;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaTipusAfectacio;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaTramits;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaUA;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaUnitatsMateries;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaUsuarios;

/**
 * Clase para forzar en Swagger-ui a tipo lista cuando haya un elemento.
 *
 * @link http://tugdualgrall.blogspot.com/2011/09/jax-rs-jersey-and-single-element-arrays.html
 * @author slromero
 *
 */
@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext> {
	private final JAXBContext context;
	private final Class[] types = { RespuestaAgrupacioFetVital.class, RespuestaAgrupacioMateries.class,
			RespuestaArxius.class, RespuestaBase.class, RespuestaBulletins.class, RespuestaCatalegDocuments.class,
			RespuestaDocuments.class, RespuestaDocumentsNormatives.class, RespuestaDocumentsTramits.class,
			RespuestaEdificis.class, RespuestaEnllacos.class, RespuestaError.class, RespuestaEspaisTerritorials.class,
			RespuestaFamilies.class, RespuestaFetsVitals.class, RespuestaFitxes.class, RespuestaFitxesUA.class,
			RespuestaFormularis.class, RespuestaIconesFamilies.class, RespuestaIdioma.class,
			RespuestaMateriaAgrupacio.class, RespuestaMateries.class, RespuestaNormatives.class, RespuestaPerfil.class,
			RespuestaPersonal.class, RespuestaProcediments.class, RespuestaPublicsObjectius.class,
			RespuestaSeccions.class, RespuestaServeis.class, RespuestaSimple.class, RespuestaTipus.class,
			RespuestaTipusAfectacio.class, RespuestaTramits.class, RespuestaUA.class, RespuestaUnitatsMateries.class,
			RespuestaUsuarios.class };

	public JAXBContextResolver() throws Exception {
		this.context = new JSONJAXBContext(JSONConfiguration.mapped().arrays("resultado").build(), types);

	}

	@Override
	public JAXBContext getContext(final Class<?> objectType) {
		for (final Class type : types) {
			if (type == objectType) {
				return context;
			}
		}

		return null;
	}
}
