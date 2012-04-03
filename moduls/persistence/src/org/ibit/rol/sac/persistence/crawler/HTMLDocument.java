package org.ibit.rol.sac.persistence.crawler;

import org.htmlparser.beans.StringBean;
import org.htmlparser.Parser;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.*;


/*
 * @author INDRA
 * 
 * @author enric - pongo comentarios.
 * Esta clase se conecta a una pagina html,
 * lee su contenido, y lo guarda en un Nodo (del
 * Crawler) para su posterior indexación. 
 * Si se trata de un Microsite lee unos tags 
 * determinados, o todo el contenido si no hay tags.
 * Si se trata de una pagina externa, lee
 * todo el contenido.
 * 
 */
public class HTMLDocument {
	protected  Log log = LogFactory.getLog(HTMLDocument.class);

	// TODO es proposa cambiar el nom d'aquesta funció por algo asi com: prepararIndexacionPaginaHtml()
	public Nodo Document(String idFicha, String url, List<Nodo> arbol,
			int counter,boolean isMicrosite) throws IOException, InterruptedException {
		
		// Para parsear la pagina html ...
		
		Document doc = null;
		Nodo hijo = null;
		
		// .. se establece conexion con la pagina
		StringBean sb = new StringBean();
		sb.setLinks(false);
		URL urlTem = new URL(url);
		int readTimeOut = Integer.parseInt(System.getProperty("es.caib.rolsac.crawler.readTimeOut"));
		int timeOut = Integer.parseInt(System.getProperty("es.caib.rolsac.crawler.timeOut"));
		URLConnection uRLConnection = urlTem.openConnection();
		uRLConnection.setReadTimeout(readTimeOut);
		uRLConnection.setConnectTimeout(timeOut);
		sb.setConnection(uRLConnection);

		// .. se lee el texto de la pagina y se comprueba en el arbol que el contenido no exista
		boolean repetida = false;
	    String md5="";
		try {
			md5 = Md5.getMD5(sb.getStrings());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
	    for (Nodo nodo : arbol) {
			if(nodo.getMd5().equals(md5)){
				repetida=true;
				break;
			}
		}
		if (!repetida) {
	
			try {
				
				// .. se indexa la pagina, solo si tiene contenido html. De
				// esta manera excluimos imagenes, pdfs , zips etc

				if (sb.getConnection() != null) {
					if (sb.getConnection().getContentType().startsWith(
							"text/html")) {
						doc = new Document();

						String title = new String();

						// .. se indexa la  url y el instante del tiempo
						
						doc.add(new Field("url", url, Field.Store.YES,
								Field.Index.UN_TOKENIZED));

						doc.add(new Field("timestamp", DateTools.timeToString(
								new Date().getTime(),
								DateTools.Resolution.MILLISECOND),
								Field.Store.YES, Field.Index.UN_TOKENIZED));
						StringReader contenido = null;
						Parser bParser=new Parser();
						bParser.setResource(url);
						
						try {
							
							// .. se comprueba si es un microsite, y en este caso se indexan solo los tags especificados, o todo si no hay tags o
							// los tags no tienen contenido.
							
							
							String[] tags = getTagsIndexacionMS(idFicha);
							if(isMicrosite && tags.length>0 ){
								StringBuffer tempContenido = new StringBuffer();
								for (String tag : tags) {
									 
									 if(tag.trim().length()>0){
										 log.debug("Analizando el contenido del MS para el tag : "+tag);
										 NodeList nTag = bParser.parse(new HasAttributeFilter("id",tag));
										 if(nTag.size()>0){
											 tempContenido.append(nTag.asString());
										 }
									 }
								}	
								if(tempContenido.length()>0){
									log.debug("[IndexInsertarFicha:" + idFicha + "] Se ha encontrado contenido , procedemos a indexarlo");
									contenido = new StringReader(tempContenido.toString());	
								}
								else{
									// .. si tags no tienen contenido, se indexa todo el texto
									log.debug("[IndexInsertarFicha:" + idFicha + "] No se ha encontrado contenido en tags, indexamos todo el contenido de la URL");
									contenido = new StringReader(sb.getStrings());
								}
								
							}
							else{
								//.. si no es MS, se indexa todo el texto
								log.debug("[IndexInsertarFicha:" + idFicha + "] Indexamos todo el contenido de la URL");
								contenido = new StringReader(sb.getStrings());
							}
							
						} catch (Exception e) {
							log.warn("Exception capturando los tags y su contenido para indexar: " + e);

						}
						
						//.. se indexa el título

						NodeFilter bFilter;

						try {
							bParser = new Parser();
							bFilter = new TagNameFilter("TITLE");
							bParser.setResource(url);
							title = bParser.parse(bFilter).asString();

						} catch (Exception e) {
							log.warn("Exception capturando title: " + e);

						}
						
						doc.add(new Field("contents", contenido));
						doc.add(new Field("title", title, Field.Store.YES,
								Field.Index.TOKENIZED));

						//.. se indexa el id de la ficha
						
						doc.add(new Field("idFicha", idFicha, Field.Store.YES,
								Field.Index.UN_TOKENIZED));
						
						//.. ponemos los datos a indexar en un nodo
						hijo = new Nodo(url, counter, doc,md5);
					}

				}

			}

			catch (Exception e) {
				log.warn("[IndexInsertarFicha:" + idFicha
						+ "] Error generando el documento de Lucene "
						+ e.getMessage());

			}
		}
		
		// .. se devuelve el hijo
		
		return hijo;
	}
	 
	 private String[] getTagsIndexacionMS(String idFicha){
		 	String[] aTags = null;
			try {
			 	String tags =System.getProperty("es.caib.rolsac.crawler.tagsIndexacionMS");
				aTags=tags.split(",");

			} catch (Exception e) {
				log.warn("[IndexInsertarFicha:" + idFicha + "] No se ha podido capturar los tags que indican que contenido a indexar del Microsite " + e.getMessage());
			}

		 	return aTags;
	}
	public HTMLDocument() {
	}
}
