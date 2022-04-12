package es.caib.rolsac.api.v2.general;

import java.util.ResourceBundle;

import es.caib.rolsac.api.v2.exception.APIException;

public final class ConfiguracioServeis {

	public static final String NOM_SERVEI_ROLSAC = "RolsacWS";
	public static final String NOM_SERVEI_AGRUPACIO_FET_VITAL = "AgrupacioFetVitalWS";
	public static final String NOM_SERVEI_AGRUPACIO_MATERIA = "AgrupacioMateriaWS";
	public static final String NOM_SERVEI_BUTLLETI = "ButlletiWS";
	public static final String NOM_SERVEI_CATALEG_DOCUMENTS = "CatalegDocumentsWS";
	public static final String NOM_SERVEI_DOCUMENT_TRAMIT = "DocumentTramitWS";
	public static final String NOM_SERVEI_DOCUMENTO_NORMATIVA = "DocumentoNormativaWS";
	public static final String NOM_SERVEI_DOCUMENTO_SERVICIO = "DocumentoServicioWS";
	public static final String NOM_SERVEI_DOCUMENT = "DocumentWS";
	public static final String NOM_SERVEI_EDIFICI = "EdificiWS";
	public static final String NOM_SERVEI_ENLLAC = "EnllacWS";
	public static final String NOM_SERVEI_ENLLAC_TELEMATICO = "EnllacTelematicoWS";
	public static final String NOM_SERVEI_ESPAI_TERRITORIAL = "EspaiTerritorialWS";
	public static final String NOM_SERVEI_ESTADISTICA = "EstadisticaWS";
	public static final String NOM_SERVEI_EXCEPCIO_DOCUMENTACIO = "ExcepcioDocumentacioWS";
	public static final String NOM_SERVEI_FAMILIA = "FamiliaWS";
	public static final String NOM_SERVEI_FET_VITAL = "FetVitalWS";
	public static final String NOM_SERVEI_FITXA_UA = "FitxaUAWS";
	public static final String NOM_SERVEI_FITXA = "FitxaWS";
	public static final String NOM_SERVEI_FORMULARI = "FormulariWS";
	public static final String NOM_SERVEI_ICONA_FAMILIA = "IconaFamiliaWS";
	public static final String NOM_SERVEI_ICONA_MATERIA = "IconaMateriaWS";
	public static final String NOM_SERVEI_IDIOMA = "IdiomaWS";
	public static final String NOM_SERVEI_INICIACIO = "IniciacioWS";
	public static final String NOM_SERVEI_MATERIA_AGRUPACIO = "MateriaAgrupacioWS";
	public static final String NOM_SERVEI_MATERIA = "MateriaWS";
	public static final String NOM_SERVEI_NORMATIVA = "NormativaWS";
	public static final String NOM_SERVEI_PERFIL = "PerfilWS";
	public static final String NOM_SERVEI_PERSONAL = "PersonalWS";
	public static final String NOM_SERVEI_PROCEDIMENT = "ProcedimentWS";
	public static final String NOM_SERVEI_SERVICIO = "ServicioWS";
	public static final String NOM_SERVEI_PUBLIC_OBJECTIU = "PublicObjectiuWS";
	public static final String NOM_SERVEI_SECCIO = "SeccioWS";
	public static final String NOM_SERVEI_TAXA = "TaxaWS";
	public static final String NOM_SERVEI_TIPUS = "TipusWS";
	public static final String NOM_SERVEI_TRAMIT = "TramitWS";
	public static final String NOM_SERVEI_UNITAT_ADMINISTRATIVA = "UnitatAdministrativaWS";
	public static final String NOM_SERVEI_UNITAT_MATERIA = "UnitatMateriaWS";
	public static final String NOM_SERVEI_USUARI = "UsuariWS";
	public static final String NOM_SERVEI_SILENCI = "SilencioWS";
	public static final String NOM_SERVEI_PLATAFORMA = "PlataformaWS";
	public static final String NOM_SERVEI_PLANTILLA = "PlantillaWS";

	public static String getUrlServei(final String nomServei) throws APIException {

		String urlPrefix = System.getProperty("es.caib.rolsac.api.v2.urlPrefix");
		if (urlPrefix == null) {
			final ResourceBundle rb = ResourceBundle.getBundle("es/caib/rolsac/api/v2/resources/sac-api");
			urlPrefix = rb.getString("es.caib.rolsac.api.v2.urlPrefix");
		}

		if (urlPrefix == null || "".equals(urlPrefix))
			throw new APIException("URL de serveis web no trobada!");

		urlPrefix = urlPrefix.trim();
		return urlPrefix + nomServei;

	}

}
