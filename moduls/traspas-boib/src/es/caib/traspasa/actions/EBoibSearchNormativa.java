package es.caib.traspasa.actions;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import es.caib.traspasa.actionsforms.SearchnormativaActionForm;
import es.caib.traspasa.bean.TrNormativaLocalBean;
import es.caib.traspasa.util.RdfProperties;
import es.caib.traspasa.util.SearchNormativa;
import es.caib.traspasa.util.SearchNormativaBase;

public class EBoibSearchNormativa extends SearchNormativaBase implements
SearchNormativa {

	private String eboibUrl = null;
	private int numeroregistros = 0;
	private Model seccionsCA = null;
	private Model seccionsES = null;
	
	public EBoibSearchNormativa(SearchnormativaActionForm f) {
		super(f);
		eboibUrl = System.getProperty("es.caib.rolsac.traspasboib.url");
		if (eboibUrl == null) {
			throw new IllegalStateException("No se ha configurado es.caib.rolsac.traspasboib.url");
		}
	}
	
	private String getSeccioCA (String rdfId) {
		if (seccionsCA == null) {
			seccionsCA = loadRdf(eboibUrl + "ca/seccio");
		}
		return getSeccio(seccionsCA, rdfId);
	}
	
	private String getSeccioES (String rdfId) {
		if (seccionsES == null) {
			seccionsES = loadRdf(eboibUrl + "es/seccio");
		}
		return getSeccio(seccionsES, rdfId);
	}	

	private String getSeccio(Model seccions, String rdfId) {
		
		Resource seccio = seccions.getResource(rdfId);
		String nom = seccio.getProperty(RdfProperties.NOM).getString();
		Statement idPare = seccio.getProperty(RdfProperties.ID_PARE);
		if (idPare != null) {
			nom = getSeccio(seccions, idPare.getResource().getURI()) + " | " + nom;
		}
		return nom;
		
	}

	public String getNumeroNormativas() {
	    return "" + numeroregistros;
	}

	private class BoibResult {
		private String rdfUrl;
		private String numBoib;
		private String anyo;
		private String num;
		private String url;
		private List<String> enviaments;
		private boolean historic;
	}
	
	
	public void makeSearch() {

		/*
		 * 1.- buscar el BOIB por fecha o n�mero en RSS
		 * 		- buscar por n�mero: /filtrerss.do?lang=ca&resultados=20&num_ini=1&num_fin=1&any_ini=2009&any_fin=2009
		 * 		- buscar por fecha: /filtrerss.do?lang=ca&resultados=20&fec_ini=01/01/2009&fec_fin=08/01/2009
		 * 2.- dada la url RDF del BOIB, buscar los edictos. Si hay num reg en la b�squeda, filtrar por �l.
		 */

		List<BoibResult> boibRdfUrls = this.getBoibRdfUrls();
		String numregboib = (!fsearch.getNumeroregistro().equals(""))?fsearch.getNumeroregistro():"";
		
		if (boibRdfUrls.size() < 1) {
			numeroregistros=-1;
			mensajeavisobean.setCabecera("ERROR EN ELS PAR�METRES");
			mensajeavisobean.setSubcabecera("Els par�metres de cerca tenen inconsist�ncies. Son erronis.");
			mensajeavisobean.setDescripcion("Las causes probables d'aquest error s�n que o b� el n�mero del boib introdu�t o la data s�n incorrectes. Es possible que el n�mero de registre contengui un valor incorrecte.");
		}
		for (BoibResult rdf : boibRdfUrls) {
			populateBoibResult(rdf);
		}

		normativabean = null;
		boolean abortar = false;
		for (BoibResult rdf : boibRdfUrls) {
			if (abortar) break;
			for (String enviamentUrl : rdf.enviaments) {
				if (abortar) break;
				TrNormativaLocalBean normativa =  getEnviament(rdf, enviamentUrl);
	            meterListaNormativa(normativa);
	            if (normativa.getValorRegistro().equals(numregboib)) {
	                abortar = true;;
	                boolean estaInsertadoEnSac = isInsertSAC(Integer.parseInt(rdf.numBoib), Integer.parseInt(numregboib));
	                if (estaInsertadoEnSac) {
	                    numeroregistros=-1;
	                    mensajeavisobean.setCabecera("ERROR EN ELS PAR�METRES");
	                    mensajeavisobean.setSubcabecera("El boib i el registre JA ESTAN introdu�ts en el SAC");
	                }
	                traza("ENCONTRADO REGISTRO EN BOIB. REGISTRO: " + numregboib);
	                normativabean = normativa;
	            }
			}
		}
		
		if (numeroregistros == 0) { //Si todav�a no est� fijado, calculamos numeroregistros
			if (normativabean == null) {
				this.numeroregistros = this.getListadonormativas().size();
			} else {
				this.numeroregistros = 1;
			}
		}
		
		


	}

	private TrNormativaLocalBean getEnviament ( BoibResult rdf, String inputFileName ) {

		TrNormativaLocalBean normbean = new TrNormativaLocalBean();
		Model m = loadRdf (inputFileName);

		//CATAL�
		Resource res = m.getResource(inputFileName.substring(0, inputFileName.length()-4));

		normbean.setNumeroboib(rdf.numBoib);
		normbean.setIdBoletin("1");
		normbean.setNombreBoletin("BOIB");
		normbean.setValorRegistro("" + res.getProperty(RdfProperties.NUM_REGISTRE).getString());

		/*
		 agarcia: el tipo no est� disponible por RDF
		//String tipo_sac = "" + tipusNorma.getTipusNormesArticle(registre.getRegistre());
		//normbean.setIdTipo(tipo_sac);
		if (!tipo_sac.equals("0")){
		//el tipo est� codificado en el web.xml con un punto (.)
		String txnombretipo=Configuracion.getPropiedad("norma_"+tipo_sac);
		txnombretipo = txnombretipo.substring(txnombretipo.indexOf(".")+1,txnombretipo.length());

		normbean.setNombreTipo(txnombretipo);
		}else{
		 	normbean.setNombreTipo("Varis");	
		 	normbean.setIdTipo("73508");
		 }
		 */

		java.text.SimpleDateFormat diamesanyo = new java.text.SimpleDateFormat("dd/MM/yyyy");
		java.text.SimpleDateFormat anyomesdia = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			normbean.setTextoFechaBoletin(diamesanyo.format(  anyomesdia.parse(res.getProperty(RdfProperties.DATE).getString() ) ) );
		} catch (ParseException e) {
			throw new IllegalArgumentException( "Data: " + res.getProperty(RdfProperties.DATE).getString() + " incorrecta");
		}

		//catal�
		normbean.setTra_titulo_c( limpiaSumario(res.getProperty(RdfProperties.SUMARI).getString()));
		normbean.setTra_apartado_c( getSeccioCA(res.getProperty(RdfProperties.SECCIO).getResource().getURI()) );
		normbean.setTra_paginaInicial_c(res.getProperty(RdfProperties.NUM_PAG_INICIAL).getString());
		normbean.setTra_paginaFinal_c(res.getProperty(RdfProperties.NUM_PAG_FINAL).getString());
		if (!rdf.historic) {
			normbean.setTra_enlace_c(res.getURI());
		}

		String inputFileNameEs = inputFileName.replace("/ca/", "/es/");
		//CASTELLANO
		// read the RDF/XML file
		Model mEs = loadRdf(inputFileNameEs);
		ResIterator resIter = mEs.listResourcesWithProperty(RdfProperties.NUM_PAG_INICIAL);
		if (resIter.hasNext()) {
			Resource resEs = resIter.nextResource();
			normbean.setTra_titulo_v(limpiaSumario(resEs.getProperty(RdfProperties.SUMARI).getString()));
			normbean.setTra_apartado_v( getSeccioES(resEs.getProperty(RdfProperties.SECCIO).getResource().getURI()) );
			normbean.setTra_paginaInicial_v(resEs.getProperty(RdfProperties.NUM_PAG_INICIAL).getString());
			normbean.setTra_paginaFinal_v(resEs.getProperty(RdfProperties.NUM_PAG_FINAL).getString());
			if (!rdf.historic) {
				normbean.setTra_enlace_v(resEs.getURI());
			}
		}

		return normbean;

	}


	private Model loadRdf(String inputFileName) {
		Model m = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
			throw new IllegalArgumentException( "Rdf: " + inputFileName + " no trobat");
		}
		// read the RDF/XML file
		m.read(in, "");
		return m;
	}


	private String limpiaSumario(String string) {
		if (string.startsWith("![CDATA[")) {
			return string.substring(8, string.length()-2);
		} else {
			return string;
		}
	}

	private void populateBoibResult( BoibResult rdf2 ) {
		rdf2.enviaments = new ArrayList<String>();
		Model m = loadRdf(rdf2.rdfUrl);

		//Obtenemos los datos de num butllet�
		Resource but = m.getResource(rdf2.url);
		rdf2.num = but.getProperty(RdfProperties.NUMERO).getString();
		String dataPublicacio = but.getProperty(RdfProperties.DATA_PUBLICACIO).getString();
		rdf2.anyo = dataPublicacio.substring(0, 4);
		rdf2.numBoib = rdf2.anyo + rdf2.num;
		Statement historico = but.getProperty(RdfProperties.HISTORIC);
		if (historico != null && historico.getString().equals("S")) {
			rdf2.historic = true;
		} else {
			rdf2.historic = false;
		}
					
		ResIterator iter = m.listResourcesWithProperty(RdfProperties.ACCES_RDF);        
		while (iter.hasNext()) {
			Resource rdf = iter.nextResource();
			//System.out.println("  " + rdf.getProperty(ACCES_RDF).getObject().toString() );
			//System.out.println(rdf.getProperty(ACCES_RDF).getResource().getURI());
			rdf2.enviaments.add(rdf.getProperty(RdfProperties.ACCES_RDF).getResource().getURI());
		}

	}	

	/** buscar el BOIB por fecha o n�mero en RSS<br/>
	 * 		- buscar por n�mero: /filtrerss.do?lang=ca&resultados=20&num_ini=1&num_fin=1&any_ini=2009&any_fin=2009<br/>
	 * 		- buscar por fecha: /filtrerss.do?lang=ca&resultados=20&fec_ini=01/01/2009&fec_fin=08/01/2009<br/>
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<BoibResult> getBoibRdfUrls() {
		List<BoibResult> boibRdfUrls = new ArrayList<BoibResult>();
		StringBuilder feedUrl = new StringBuilder(eboibUrl);
		feedUrl.append("/filtrerss.do?lang=ca&resultados=20");

		if (fsearch.getNumeroboletin() != null && !fsearch.getNumeroboletin().equals("")) {
			String anyo = fsearch.getNumeroboletin().substring(0, 4);
			String num = fsearch.getNumeroboletin().substring(4);
			feedUrl.append("&any_ini=").append(anyo).append("&any_fin=").append(anyo);
			feedUrl.append("&num_ini=").append(num).append("&num_fin=").append(num);
		} else {
			feedUrl.append("&fec_ini=").append(fsearch.getFecha()).append("&fec_fin=").append(fsearch.getFecha());
		}

		try {
			SyndFeedInput input = new SyndFeedInput();
			XmlReader reader = new XmlReader ( new URL(feedUrl.toString()));
			SyndFeed feed = input.build (reader);
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();
				BoibResult bResult = new BoibResult();
				bResult.url = entry.getLink();
				bResult.rdfUrl = bResult.url + "/rdf";
				boibRdfUrls.add( bResult );
			}		
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			traza("ERROR AL OBTENER EL BOIB A PARTIR DEL RSS " + e.getMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			traza("ERROR AL OBTENER EL BOIB A PARTIR DEL RSS " + e.getMessage());
		} catch (FeedException e) {
			e.printStackTrace();
			traza("ERROR AL OBTENER EL BOIB A PARTIR DEL RSS " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			traza("ERROR AL OBTENER EL BOIB A PARTIR DEL RSS " + e.getMessage());
		}
		return boibRdfUrls;
	}

	public void makeSearchFromBoibRegistro(String trcodificacion) {

		   int cadenaX= trcodificacion.indexOf("X");
		   String s_numeroboib = null;
		   String s_numeroregistro = null;
		   if (cadenaX!=-1) {
		     s_numeroboib= trcodificacion.substring(0,cadenaX);
		     s_numeroregistro=trcodificacion.substring(cadenaX+1,trcodificacion.length());
		   }

		   fsearch.setNumeroboletin(s_numeroboib);
		   fsearch.setNumeroregistro(s_numeroregistro);
		   this.makeSearch();
		

	}

}
