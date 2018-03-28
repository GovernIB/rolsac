package org.ibit.rol.sac.persistence.eboib;

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
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;


public class EBoibSearchNormativa extends SearchNormativaBase implements
SearchNormativa {

	private String eboibUrl = null;
	private int numeroregistros = 0;
	private Model seccionsCA = null;
	private Model seccionsES = null;
	private Model tipusPublicacionsCA = null;
	private Model tipusPublicacionsES = null;
	private String numeroregistro;
	private String fecha;
	private String numeroboletin;
	
	public EBoibSearchNormativa(String numeroboletin, String numeroregistro, String fecha) {
		this.numeroboletin = numeroboletin;
		this.numeroregistro = numeroregistro;
		this.fecha = fecha;
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
		String nom = seccio.getProperty(RdfProperties.SECCIO_NOM).getString();
		Statement idPare = seccio.getProperty(RdfProperties.SECCIO_ID_PARE);
		if (idPare != null) {
			nom = getSeccio(seccions, idPare.getResource().getURI()) + " | " + nom;
		}
		return nom;
		
	}
	
	private String getTipoPublicacionCA (String rdfId) {
		if (tipusPublicacionsCA == null) {
			tipusPublicacionsCA = loadRdf(eboibUrl + "ca/tipus-publicacio");
		}
		return getPublicacion(tipusPublicacionsCA, rdfId);
	}
	
	private String getTipoPublicacionES (String rdfId) {
		if (tipusPublicacionsES == null) {
			tipusPublicacionsES = loadRdf(eboibUrl + "es/tipus-publicacio");
		}
		return getPublicacion(tipusPublicacionsES, rdfId);
	}	

	private String getPublicacion(Model publicacions, String rdfId) {
		
		Resource seccio = publicacions.getResource(rdfId);
		String nom;
		if (seccio == null || seccio.getProperty(RdfProperties.TIPUS_PUBLICACIO_NOM) == null) {
			nom ="";
		} else {
			nom = seccio.getProperty(RdfProperties.TIPUS_PUBLICACIO_NOM).getString(); 
			Statement idPare = seccio.getProperty(RdfProperties.TIPUS_PUBLICACIO_ID_PARE);
			if (idPare != null) {
				nom = getPublicacion(publicacions, idPare.getResource().getURI()) + " | " + nom;
			}
		}
		return nom;
		
	}

	public long getNumeroNormativas() {
	    return numeroregistros;
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
	
	

	public void makeSearch()  {
		/*
		 * 1.- buscar el BOIB por fecha o número en RSS
		 * 		- buscar por número: /filtrerss.do?lang=ca&resultados=20&num_ini=1&num_fin=1&any_ini=2009&any_fin=2009
		 * 		- buscar por fecha: /filtrerss.do?lang=ca&resultados=20&fec_ini=01/01/2009&fec_fin=08/01/2009
		 * 2.- dada la url RDF del BOIB, buscar los edictos. Si hay num reg en la búsqueda, filtrar por él.
		 */

		List<BoibResult> boibRdfUrls = this.getBoibRdfUrls();
		String numregboib = StringUtils.isEmpty(numeroregistro)?"":numeroregistro;
		
		if (boibRdfUrls.size() < 1) {
			numeroregistros=-1;
			mensajeavisobean.setCabecera("ERROR EN ELS PARÀMETRES");
			mensajeavisobean.setSubcabecera("Els paràmetres de cerca tenen inconsisténcies. Son erronis.");
			mensajeavisobean.setDescripcion("Las causes probables d'aquest error són que o bé el número del boib introduït o la data són incorrectes. Es possible que el número de registre contengui un valor incorrecte.");
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
				TrNormativaBean normativa;
				try {
					normativa = getEnviament(rdf, enviamentUrl);
				} catch (Exception exception) {
					//Error recuperando el enviamente a pesar de la url.
					normativa = null;
				}
				
				if (normativa != null) {
		            if (numregboib.equals("")) {
		            	//No estamos buscando por numeroboib
		            	if (isNormativaValida(normativa)) {
			                meterListaNormativa(normativa);
		            	}
		            } else {
		            	//Estamos buscando por numeroboib
			            if (normativa.getValorRegistro().equals(numregboib)) {
			            	//Registro encontrado
			                boolean estaInsertadoEnSac = isInsertSAC(Integer.parseInt(rdf.numBoib), Integer.parseInt(numregboib));
			                if (estaInsertadoEnSac) {
			                    numeroregistros=-1;
			                    mensajeavisobean.setCabecera("ERROR EN ELS PAR&Agrave;METRES");
			                    mensajeavisobean.setSubcabecera("El boib i el registre JA ESTAN introdu&iuml;ts en el SAC");
			                }
			                traza("ENCONTRADO REGISTRO EN BOIB. REGISTRO: " + numregboib);
			                normativabean = normativa;
			                if (isNormativaValida(normativa)) {
			                	meterListaNormativa(normativa);
			                }
			                abortar = true;
			            }
		            }
				}
			}
		}
		
		if (numeroregistros == 0) { //Si todavía no está fijado, calculamos numeroregistros
			if (normativabean == null) {
				this.numeroregistros = this.getListadonormativas().size();
			} else {
				this.numeroregistros = 1;
			}
		}
		
		


	}
	
	/**
	 * Método que comprueba si la normativa tiene el id tipo correcto.
	 * @param normativa
	 * @return
	 * @throws DelegateException
	 */
	private boolean isNormativaValida(TrNormativaBean normativa) {
		 boolean correcto;
		 if (normativa.getIdTipoNormativa() == null) {
			 correcto = false;
		 } else {
         	Tipo tipo;
			try {
				tipo = DelegateUtil.getTipoNormativaDelegate().obtenerTipoNormativaByBOIB(normativa.getIdTipoNormativa());
				if (tipo == null) {
	         		correcto = false;
	         	} else {
	         		correcto = true;
	         	}
			} catch (DelegateException e) {
				correcto = false;
			}
         	
         }
		return correcto;
	}

	private TrNormativaBean getEnviament ( BoibResult rdf, String inputFileName ) {

		TrNormativaBean normbean = new TrNormativaBean();
		Model m = loadRdf (inputFileName);

		//CATALA
		Resource res = m.getResource(inputFileName.substring(0, inputFileName.length()-4));

		normbean.setNumeroboib(rdf.numBoib);
		normbean.setIdBoletin("1");
		normbean.setNombreBoletin("BOIB");
		normbean.setValorRegistro("" + res.getProperty(RdfProperties.NUM_REGISTRE).getString());
		
		
		
		/*
		 agarcia: el tipo no está disponible por RDF
		//String tipo_sac = "" + tipusNorma.getTipusNormesArticle(registre.getRegistre());
		//normbean.setIdTipo(tipo_sac);
		if (!tipo_sac.equals("0")){
		//el tipo está codificado en el web.xml con un punto (.)
		String txnombretipo=Configuracion.getPropiedad("norma_"+tipo_sac);
		txnombretipo = txnombretipo.substring(txnombretipo.indexOf(".")+1,txnombretipo.length());

		normbean.setNombreTipo(txnombretipo);
		}else{
		 	normbean.setNombreTipo("Varis");	
		 	normbean.setIdTipo("73508");
		 }
		 */

		java.text.SimpleDateFormat anyomesdia = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			normbean.setFechaBoletin( anyomesdia.parse(res.getProperty(RdfProperties.DATE).getString() ) );
		} catch (ParseException e) {
			throw new IllegalArgumentException( "Data: " + res.getProperty(RdfProperties.DATE).getString() + " incorrecta");
		}

		//català
		normbean.setTra_titulo_c( limpiaSumario(res.getProperty(RdfProperties.SUMARI).getString()));
		normbean.setTra_apartado_c( getSeccioCA(res.getProperty(RdfProperties.SECCIO).getResource().getURI()) );
		normbean.setIdTipoNormativa(extraerIdTipoNormativa(res.getProperty(RdfProperties.TIPUS_PUBLICACIO).getResource().getURI()));
		//normbean.setTipoPublicacion_c( getTipoPublicacionCA(res.getProperty(RdfProperties.TIPUS_PUBLICACIO).getResource().getURI()) );
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
			//normbean.setTipoPublicacion_v( getTipoPublicacionES(resEs.getProperty(RdfProperties.TIPUS_PUBLICACIO).getResource().getURI()) );
			normbean.setTra_paginaInicial_v(resEs.getProperty(RdfProperties.NUM_PAG_INICIAL).getString());
			normbean.setTra_paginaFinal_v(resEs.getProperty(RdfProperties.NUM_PAG_FINAL).getString());
			if (!rdf.historic) {
				normbean.setTra_enlace_v(resEs.getURI());
			}
		}

		return normbean;

	}

	/**
	 * Obtiene la id del tipo normativa.
	 * @param uri
	 * @return
	 */
	private String extraerIdTipoNormativa(String uri) {
		String id = "";
		if (uri != null) {
			String[] uriSplit = uri.split("#");
			if (uriSplit.length == 2) {
				id = uriSplit[1];
			}
		}
		return id;
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

		//Obtenemos los datos de num butlletí
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

	/** buscar el BOIB por fecha o número en RSS<br/>
	 * 		- buscar por número: /filtrerss.do?lang=ca&resultados=20&num_ini=1&num_fin=1&any_ini=2009&any_fin=2009<br/>
	 * 		- buscar por fecha: /filtrerss.do?lang=ca&resultados=20&fec_ini=01/01/2009&fec_fin=08/01/2009<br/>
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<BoibResult> getBoibRdfUrls() {
		List<BoibResult> boibRdfUrls = new ArrayList<BoibResult>();
		StringBuilder feedUrl = new StringBuilder(eboibUrl);
		feedUrl.append("/filtrerss.do?lang=ca&resultados=10");

		if (StringUtils.isNotEmpty(numeroboletin)) {
			String anyo = numeroboletin.substring(0, 4);
			String num = numeroboletin.substring(4);
			feedUrl.append("&any_ini=").append(anyo).append("&any_fin=").append(anyo);
			feedUrl.append("&num_ini=").append(num).append("&num_fin=").append(num);
		} else if (StringUtils.isNotEmpty(fecha)) {
			feedUrl.append("&fec_ini=").append(fecha).append("&fec_fin=").append(fecha);
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
		   this.numeroboletin = s_numeroboib;
		   this.numeroregistro = s_numeroregistro;
		   this.makeSearch();
		

	}

}
