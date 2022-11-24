package org.ibit.rol.sac.persistence.eboib;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class RdfProperties {
	
	private static final String HOST = getUrlBase();
	
	public static final String DCNAMESPACE = "http://purl.org/dc/elements/1.1/";
	
	public static final Property CONTRIBUTOR = ResourceFactory.createProperty(DCNAMESPACE + "contributor");
	public static final Property COVERAGE = ResourceFactory.createProperty(DCNAMESPACE + "coverage");
	public static final Property CREATOR = ResourceFactory.createProperty(DCNAMESPACE + "creator");
	public static final Property FORMAT = ResourceFactory.createProperty(DCNAMESPACE + "format");
	public static final Property DATE = ResourceFactory.createProperty(DCNAMESPACE + "date");
	public static final Property DESCRIPTION = ResourceFactory.createProperty(DCNAMESPACE + "description");
	public static final Property IDENTIFIER = ResourceFactory.createProperty(DCNAMESPACE + "identifier");
	public static final Property LANGUAGE = ResourceFactory.createProperty(DCNAMESPACE + "language");
	public static final Property PUBLISHER = ResourceFactory.createProperty(DCNAMESPACE + "publisher");
	public static final Property RELATION = ResourceFactory.createProperty(DCNAMESPACE + "relation");
	public static final Property RIGHTS = ResourceFactory.createProperty(DCNAMESPACE + "rights");
	public static final Property SOURCE = ResourceFactory.createProperty(DCNAMESPACE + "source");
	public static final Property SUBJECT = ResourceFactory.createProperty(DCNAMESPACE + "subject");
	public static final Property TITLE = ResourceFactory.createProperty(DCNAMESPACE + "title");
	public static final Property TYPE = ResourceFactory.createProperty(DCNAMESPACE + "type");
	
	public static final String ENVNAMESPACE = HOST + "rdf/schema/enviament/1.0/";

	public static final Property LANG = ResourceFactory.createProperty(ENVNAMESPACE + "lang");
	public static final Property ID_ENVIAMENT = ResourceFactory.createProperty(ENVNAMESPACE + "idEnviament");
	public static final Property ENVIAT_TO = ResourceFactory.createProperty(ENVNAMESPACE + "enviatTo");
	public static final Property DATA_REGISTRE = ResourceFactory.createProperty(ENVNAMESPACE + "dataRegistre");
	public static final Property NUM_REGISTRE = ResourceFactory.createProperty(ENVNAMESPACE + "numeroRegistre");
	public static final Property TIPUS_PUBLICACIO = ResourceFactory.createProperty(ENVNAMESPACE + "tipusPublicacio");
	public static final Property NUM_DECRET_LLEI = ResourceFactory.createProperty(ENVNAMESPACE + "numDecretLlei");
	public static final Property ORDRE = ResourceFactory.createProperty(ENVNAMESPACE + "ordre");
	public static final Property SUMARI = ResourceFactory.createProperty(ENVNAMESPACE + "sumariEnviament");
	public static final Property CONTINGUT = ResourceFactory.createProperty(ENVNAMESPACE + "contingut");
	public static final Property NUM_PAG_INICIAL = ResourceFactory.createProperty(ENVNAMESPACE + "numPaginaInicial");
	public static final Property NUM_PAG_FINAL = ResourceFactory.createProperty(ENVNAMESPACE + "numPaginaFinal");
	public static final Property VERSIO_PDF = ResourceFactory.createProperty(ENVNAMESPACE + "versioPdf");
	public static final Property ORGANISME = ResourceFactory.createProperty(ENVNAMESPACE + "organisme");
	public static final Property SECCIO = ResourceFactory.createProperty(ENVNAMESPACE + "seccio");
	public static final Property HTML = ResourceFactory.createProperty(ENVNAMESPACE + "html");
	public static final Property ANNEXES = ResourceFactory.createProperty(ENVNAMESPACE + "annexes");

	public static final String RENVNAMESPACE = HOST + "rdf/schema/relEnviament/1.0/";

	public static final Property ACCES_RDF = ResourceFactory.createProperty(RENVNAMESPACE + "accesRdf");
	
	public static final String BUTNAMESPACE = HOST + "rdf/schema/butlleti/1.0/";

	public static final Property ID_BOIB = ResourceFactory.createProperty(BUTNAMESPACE + "idBoib");
	public static final Property ORDINARI = ResourceFactory.createProperty(BUTNAMESPACE + "ordinari");
	public static final Property HISTORIC = ResourceFactory.createProperty(BUTNAMESPACE + "historic");
	public static final Property DATA_PUBLICACIO = ResourceFactory.createProperty(BUTNAMESPACE + "dataPublicacio");
	public static final Property NUMERO = ResourceFactory.createProperty(BUTNAMESPACE + "numero");
	
	
	public static final String SECNAMESPACE = HOST + "rdf/schema/seccio/1.0/";
	public static final Property SECCIO_ID_PARE = ResourceFactory.createProperty(SECNAMESPACE + "idSeccioPare");
	public static final Property SECCIO_NOM = ResourceFactory.createProperty(SECNAMESPACE + "nom");
	
	public static final String TIPUS_PUBLICACIO_NAMESPACE = HOST + "rdf/schema/tipus-publicacio/1.0/";
	public static final Property TIPUS_PUBLICACIO_ID_PARE = ResourceFactory.createProperty(TIPUS_PUBLICACIO_NAMESPACE + "idSeccioPare");
	public static final Property TIPUS_PUBLICACIO_NOM = ResourceFactory.createProperty(TIPUS_PUBLICACIO_NAMESPACE + "nom");
	
	
	private static String getUrlBase() {
		return System.getProperty("es.caib.rolsac.traspasboib.url");
	}
	
	
}
