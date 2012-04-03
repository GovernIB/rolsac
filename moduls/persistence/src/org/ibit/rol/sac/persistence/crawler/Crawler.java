package org.ibit.rol.sac.persistence.crawler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJBException;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.ibit.lucene.analysis.AlemanAnalyzer;
import org.ibit.lucene.analysis.CastellanoAnalyzer;
import org.ibit.lucene.analysis.CatalanAnalyzer;
import org.ibit.lucene.analysis.InglesAnalyzer;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;


/**
 * 
 * TODO @author enric
 * Falta documentar! Para alguien nuevo en rolsac, y ve la clase Crawler 
 * por primera vez:
 * Que objetivo tiene el crawler del rolsac?
 * Cuales son las entradas, salidas del crawler y que estado cambia?  
 * 
 */

public class Crawler{
    

    private IndexWriter writer;
    private  String dominio;
    protected  Log log = LogFactory.getLog(Crawler.class);
    private static List<Nodo> arbol;
    private static String tipoMicrosite="";
    private static String valorMicrosite="";
    private Analyzer analyzer;
    private String path;
    private Long idFicha;
    private Map<String,String> urls;
    private static int profundidadMS;
    private static int profundidadBasica;
    private boolean isMicrosite;
    private HTMLDocument htmlDocument=new HTMLDocument();
    
    //METODOS INDEXACI�N
    public Crawler(String path, Long idFicha, Map<String, String> urls){
    	this.path=path;
    	this.idFicha=idFicha;
    	this.urls=urls;
    }
    public Crawler(String path){
    	this.path=path;
    }
    
    
    public void indexarURLFicha() {
    	try {
    		
    		for (Iterator iterator = urls.keySet().iterator(); iterator.hasNext();) {  		
        		String idi = (String) iterator.next();
        		String url= urls.get(idi);
        		
        		log.debug("[IndexInsertarFicha:" + idFicha + "] Traduccion para el idioma: "+idi+" con URL: "+url);
            	dominio = getDomimio(url);
            	if(dominio!=""){
            	    if(!comprobarFormato(url)){
            	    	arbol = new ArrayList<Nodo>();
            	    	Nodo padre = htmlDocument.Document(idFicha.toString(),url,arbol,0,isMicrosite(url));	
            	    	if(padre!=null){
        		        	arbol.add(padre);
        		        	//Comprobamos que la URL sea un Microsite, en ese caso guardamos en la variable "valorMicrosite" el valor
        		        	//comprobarMicrosite(trad.getUrl());
        		        	if(isMicrosite(url)){
        		        		profundidadMS=Integer.parseInt(System.getProperty("es.caib.rolsac.crawler.profundidadMS"));
        		        		log.debug("[IndexInsertarFicha:" + idFicha + "] Indexamos un Microsite:"+url+" con clave: "+valorMicrosite+" con Profundidad Max.: "+profundidadMS);
        		        		//log.debug("Iniciando analisis arbol URLS...");
        		        		indexMS(padre,0,idFicha.toString(),idi);
        		        		//log.debug("Finalizando analisis arbol URLS...");
        		        	}
        		        	else{
        		        		profundidadBasica=Integer.parseInt(System.getProperty("es.caib.rolsac.crawler.profundidadBasica"));
        		        		log.debug("[IndexInsertarFicha:" + idFicha + "]Indexamos una URL Basica: "+url+" con Profundidad Max.: "+profundidadBasica);
        		        		//log.debug("Iniciando analisis arbol URLS...");
        		        		indexBasica(padre,0,idFicha.toString(),idi);
        		        		//log.debug("Finalizando analisis arbol URLS...");
        		        	}

        	    	    }

            	    }
            	    //Si se han encontrado resultados proseguimos a guardarlos en el indice de lucene para el crawler
            	    if(arbol.size()>0){
            	    	guardarResultadosIndice(url,idi);
            	    }
            	}
            	else{
        			log.warn("[IndexarURLFicha:" + idFicha + "] No se ha podido obtener el dominio." );                    		
            	}   
        		
        	}
		} catch (Exception e) {
			log.warn("[indexarURLFicha:" + idFicha + "] No se ha podido insertar en el indice crawl de la ficha. " + e.fillInStackTrace());

		}
    	
    }
    
    public void desindexarURLFicha(){
		try {

			List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
			for (int i = 0; i < langs.size(); i++) {
				String idioma=(String)langs.get(i);
		    	Directory directory = getHibernateDirectory(idioma);
		    	IndexReader reader = IndexReader.open(directory);
		        reader.deleteDocuments(new Term("idFicha",idFicha.toString()));
		        log.debug("Eliminamos del indice Crawl la ficha con id: "+idFicha.toString()+" Idioma: "+langs.get(i));
		        reader.close();        
		        directory.close();
			}

		}
		catch (Exception ex) {
			log.warn("[IndexBorraFicha:" + idFicha + "] No se ha podido borrar del indice crawl la ficha. " + ex.fillInStackTrace());
		}
    	
    }
    
    private void guardarResultadosIndice(String url,String idioma) throws Exception {
        try{
        
        		log.debug("[IndexInsertarFicha:" + idFicha + "] Analisis URLS concluido. Nos disponemos a indexar los resultados");
        		//log.debug("Crawling...");
    	    	Directory directory = getHibernateDirectory(idioma);

                writer = new IndexWriter(directory, getAnalizador(idioma),false);
        	    
        	    
                for (Nodo nodo : arbol) {
                	String sDoc="NO contenido";
                	if(nodo.getDoc()!=null){sDoc="ok";}
                	log.debug("[IndexInsertarFicha:" + idFicha + "] Guardando URL: "+nodo.getURL()+" Profundidad: "+nodo.getProfundidad()+" Doc: "+sDoc);		
                	writer.addDocument(nodo.getDoc()); 
                }
                log.debug("Finish Crawling");

                //log.debug("Optimizing...");

        } catch (Exception e) {
			log.warn("[IndexInsertarFicha:" + idFicha + "] Se ha interrumpido la indexaci�n. Cerrando indice... " + e.fillInStackTrace());
        }
        finally{
            //writer.optimize();
            writer.close();
        }
    }
    
  //Funci�n recursiva que indexa el contenido de un MicroSite
    private void indexMS(Nodo padre, int counter,String idFicha,String idioma) throws Exception {


    	counter++;
		String sHijo="";
		// Comprobamos el nivel de profundidad que deseamos
		if (counter <= profundidadMS) {

			try {

				// se leen los links de la pagina apuntada por la URL 
				LinkParser lp = new LinkParser(padre.getURL());
				URL[] links = lp.ExtractLinks();

				List<Nodo> indexarList = new ArrayList<Nodo>();

				for (URL l : links) {

					//se convierte la URL a unicode
					String strEscapeHTML = StringEscapeUtils.unescapeHtml(l
							.toURI().toString());
					if (strEscapeHTML.endsWith("#")) {
						strEscapeHTML = l.toURI().toString().replace("#", "");
					}

						
						if (micrositeEsIndexable(idioma, strEscapeHTML)) {
							
							//se comprueba que sea un microsite aun no leido. 
							Nodo hijo = null;
							for (Nodo leido : arbol) {
								if (strEscapeHTML.toLowerCase().equals(leido.getURL().toLowerCase())) {
									hijo = leido;
									break;
								}
							}
	
							// se a�ade el contenido del microsite en la lista de indexacion. 
							if (hijo == null) {
								hijo = htmlDocument.Document(idFicha,strEscapeHTML,arbol,counter,true);
								
								if (hijo != null) {
									sHijo=hijo.getURL();
									arbol.add(hijo);
									indexarList.add(hijo);
									
									
								}
	
						}
					}

				}

				//recursivamente se indexan los microsite hijos
				for (Nodo indexarNodo : indexarList) {
					indexMS(indexarNodo,counter,idFicha,idioma);
				}

			} catch (Exception e) {
     			log.warn("[IndexInsertarFicha:" + idFicha + "] Error guardando documento en el �ndice crawl " + e.fillInStackTrace()+" URL Padre: "+padre.getURL()+" URL Hija: "+sHijo);
			}

		}

	}
    
    
	private boolean micrositeEsIndexable(String idioma, String strEscapeHTML)
			throws Exception {
		
		// Solo se indexan los microsites
		// que cumplan estas 3 condiciones = 1)
		// Pertenezca al dominio de la URL inicial 2) Que contenga
		// el valor de la clave de la URL inicial y la clave 3)Que est� en el
		// idioma de de la ficha
		return isMicrosite(strEscapeHTML)
			&& contieneClavesMS(strEscapeHTML)
			&& strEscapeHTML.toLowerCase().contains(
					"lang=" + idioma);
	}
	
    //Funci�n recursiva que indexa una URL B�sica
    private void indexBasica(Nodo padre, int counter,String idFicha,String idioma) throws Exception {
		counter++;
		
		// Comprobamos el nivel de profundidad que deseamos
		if (counter <= profundidadBasica) {

			try {

				LinkParser lp = new LinkParser(padre.getURL());
				URL[] links = lp.ExtractLinks();

				List<Nodo> indexarList = new ArrayList<Nodo>();

				for (URL l : links) {

					String strEscapeHTML = StringEscapeUtils.unescapeHtml(l
							.toURI().toString());
					if (strEscapeHTML.endsWith("#")) {
						strEscapeHTML = l.toURI().toString().replace("#", "");
					}

					// Comprobamos que cumpla que se encuentre en el dominio
						if (strEscapeHTML.contains(dominio)){
							Nodo hijo = null;
							for (Nodo leido : arbol) {
								if (strEscapeHTML.toLowerCase().equals(leido.getURL().toLowerCase())) {
									hijo = leido;
									break;
								}
							}
	
							if (hijo == null) {
								hijo = htmlDocument.Document(idFicha,strEscapeHTML,arbol,counter,false);
								if (hijo != null) {
									arbol.add(hijo);
									indexarList.add(hijo);
								}
	
							}
						}

				}

				for (Nodo indexarNodo : indexarList) {
					indexBasica(indexarNodo, counter,idFicha,idioma);
				}

			} catch (Exception e) {
     			log.warn("[IndexInsertarFicha:" + idFicha + "] Error guardando documento en el �ndice crawl " + e.fillInStackTrace()+" URL: "+padre.getURL());
			}

		}

	}
    
  
    private String getDomimio(String url)
    {	
   	 String valor="";
   	 if(url.length()>0){
   		 try {
   			 if(url.indexOf(".")!=-1){
   			     int firstDot = url.indexOf(".");
   			     int lastDot =  url.lastIndexOf(".");
   			     return url.substring(firstDot+1,lastDot); 	 
   			 }

   		} catch (Exception e) {
   			// TODO: handle exception
   			log.warn("Error: No se ha podido capturar el dominio");
   			valor="";
   		}

   	 }
   	 return valor;

    }

 private  boolean comprobarFormato(String  url){
 	boolean validez = false;
 	//formatos conocidos
 	String[] noValidos={"pdf","jpeg","jpg","zip","rar","gzip","gif","png"};
 	for (int i = 0; i < noValidos.length; i++) {
			if (url.toLowerCase().endsWith("."+noValidos[i])){
				validez=true;
				break;
			}
		}
 	return validez;

 }
 
 private boolean isMicrosite(String url){
	 boolean valido=false;
	 try {
		 	String dominioMS =System.getProperty("es.caib.rolsac.crawler.dominioMS");
		 	if(url.toLowerCase().contains(dominioMS)){valido=true;}
	} catch (Exception e) {
		log.warn("[IndexInsertarFicha:" + idFicha + "] Error capturando el dominio de un microsite " + e.getMessage());

	}
 	return valido;
 }
 
 private boolean contieneClavesMS(String url)throws Exception{
 	boolean valido=false;
	try {
	 	String claves =System.getProperty("es.caib.rolsac.crawler.clavesMS");
		String[] aClaves=claves.split(",");
		for (String clave : aClaves) {
			if(url.toLowerCase().contains(clave)){
		 		valido=true;break;
		 	}
		}
	} catch (Exception e) {
		log.warn("[IndexInsertarFicha:" + idFicha + "] No se ha podido capturar las claves del microsite del fichero de configuraci�n " + e.getMessage());
	}

 	return valido;
 }

	private  Analyzer getAnalizador(String idi) {

		if (idi.toLowerCase().equals("de"))      	analyzer = new AlemanAnalyzer();
        else if (idi.toLowerCase().equals("en")) 	analyzer = new InglesAnalyzer();
        else if (idi.toLowerCase().equals("ca")){ 	analyzer = new CatalanAnalyzer();}
		else analyzer = new CastellanoAnalyzer();

		return analyzer;
	}
	
	private  Directory getHibernateDirectory(String idi) throws IOException {
		Directory	directory;
		try {
	      directory = FSDirectory.getDirectory(new File(path+"/"+idi));
	      if (!IndexReader.indexExists(directory)) {
	    	    log.debug("Creado el indice crawl porque no existe: "+path+"/"+idi);
	            new IndexWriter(directory, analyzer, true).close();
	       }
		} catch (Exception e) {
			throw new EJBException("No se ha podido cargar el indice del Crawler");
		}


        return directory;
    }
	
    public void optimizarIndiceCrawler() throws DelegateException {
    	IndexWriter writer;
    	try {
    		log.debug("Optimizando indice Crawler...");
    		 List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
            	for (int i = 0; i < langs.size(); i++) {
            		String idioma = (String)langs.get(i);
        	    	Directory directory = getHibernateDirectory(idioma);
                    writer = new IndexWriter(directory, getAnalizador(idioma),false);
                    writer.optimize();
            	}
            log.debug("Indice Crawler optimizado...");
            
		} catch (Exception e) {
			 log.error("Error optimizando el indice crawler "+e.getStackTrace());
		}

    }

}
