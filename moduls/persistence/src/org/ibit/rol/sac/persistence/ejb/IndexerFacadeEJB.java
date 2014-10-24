package org.ibit.rol.sac.persistence.ejb;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.persistence.crawler.Crawler;
import org.ibit.rol.sac.persistence.crawler.OperacionCrawler;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoRemotoDelegate;
import org.ibit.rol.sac.persistence.delegate.UARemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import es.caib.rolsac.lucene.analysis.AlemanAnalyzer;
import es.caib.rolsac.lucene.analysis.CastellaCatalaAnalyzer;
import es.caib.rolsac.lucene.analysis.CastellanoAnalyzer;
import es.caib.rolsac.lucene.analysis.CatalanAnalyzer;
import es.caib.rolsac.lucene.analysis.InglesAnalyzer;
import es.caib.rolsac.lucene.hibernate.Constants;
import es.caib.rolsac.lucene.hibernate.HibernateDirectory;
import es.caib.rolsac.lucene.model.Catalogo;
import es.caib.rolsac.lucene.model.IndexEncontrado;
import es.caib.rolsac.lucene.model.IndexResultados;

/**
 * SessionBean para mantener y consultar Boletin.
 *
 * @ejb.bean
 *  name="sac/persistence/IndexerFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IndexerFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.env-entry name="colaCrawler" value="queue/CrawlerQueue"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class IndexerFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = -9020134513422229360L;

	protected static Log log = LogFactory.getLog(IndexerFacadeEJB.class);

	public static final String FILESYSTEM_INDEX_TYPE = "filesystem";
	public static final String HIBERNATE_INDEX_TYPE = "hibernate";
	public static final int MIN_HITS = 1;
	public static final int MAX_HITS = 100;
	public static final double MIN_SCORE = 0; //0.20;
	public static final String CAMPO_BUSQUEDAS = "text";
	public static final String OPER_ADDDOCUMENT = "addDocument";
	public static final String OPER_DICCIONARIO = "diccionario";
	public static final String OPER_OPTIMIZAR = "optimizar";

	private Analyzer analyzer;

	/**
	 * Tipus de directori Lucene a emprar.
	 * @ejb.env-entry value="${index.type}"
	 */
	protected String indexType;

	/**
	 * Ubicación del directorio de Lucene a utilizar.
	 * @ejb.env-entry value="${index.location}"
	 */
	protected String indexLocation;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
		analyzer = new CastellaCatalaAnalyzer();
	}


	/*****************************************************************************/
	/*****************              INDEXAR                    *******************/
	/*****************************************************************************/   

	/**
	 * Indexa un objeto documento en un idioma.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void insertaObjeto(IndexObject indexObject, String idi, IndexWriter writer) {

	    try {
	        if (indexObject != null) {
	            if (indexObject.getTitulo() != null && indexObject.getTitulo().length() > 0) {
	                addDocumentoAlWriter(writer, indexObject, idi);
	            }
	        }

	    } catch (Exception ex) {
	        log.error(ex.getMessage());
	    }
	}


	/**
	 * Borra un objeto documento en un idioma.
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void borrarObjeto(String id, String idi) {

	    IndexReader reader = null;
		try {
			Directory directory = getHibernateDirectory(idi);
			reader = IndexReader.open(directory, false);
			reader.deleteDocuments(new Term(Catalogo.DESCRIPTOR_ID, id));

		} catch (IOException e) {
			throw new EJBException(e);
		} catch (Exception ex) {
		    log.error("No se ha podido borrar la entrada en el indice.");
		} finally {
		    try {
		        reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new EJBException(e);
            }
		}
	}


	/**
	 * Borra un objetos dependientes de un documento en un idioma.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void borrarObjetosDependientes(String id, String idi) {

        IndexReader reader = null;
        try {
			Directory directory = getHibernateDirectory(idi);
			reader = IndexReader.open(directory, false);
			reader.deleteDocuments(new Term(Catalogo.DESCRIPTOR_CLASIFICACION, id));

		} catch (IOException e) {
			throw new EJBException(e);
		} catch (Exception ex) {
            log.error("No se ha podido borrar la entrada en el indice.");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new EJBException(e);
            }
        }
	}


	/**
	 * Crea o actualiza un documento en el indexador.
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */    
	public void indexarObjeto(Object objeto) throws DelegateException {

		if (objeto instanceof Ficha) {
			FichaDelegate ficdel = DelegateUtil.getFichaDelegate();    		
			ficdel.indexBorraFicha( ( (Ficha) objeto ).getId() );
			ficdel.indexInsertaFicha( (Ficha) objeto, null );
			//solo indexamos la URL en caso de que la ficha sea Remota
			if (objeto instanceof FichaRemota) {
			    try {
			        Ficha ficha = (Ficha) objeto;
					envioColaCrawler("I",ficha);

				} catch (Exception e) {
					throw new EJBException(e);
				}
			}
		}

		if (objeto instanceof UnidadAdministrativa) {
		    UnidadAdministrativaDelegate uadel = DelegateUtil.getUADelegate();
			UARemotaDelegate uaREmdel = DelegateUtil.getUARemotaDelegate();
			uadel.indexBorraUA(((UnidadAdministrativa) objeto).getId());

			if (objeto instanceof UnidadAdministrativaRemota) {
				uaREmdel.indexInsertaUARemota((UnidadAdministrativa) objeto, null);
			} else {
			    uadel.indexInsertaUA((UnidadAdministrativa) objeto, null );
			}
		}

		if (objeto instanceof ProcedimientoLocal) {
			ProcedimientoDelegate pldel = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoRemotoDelegate pldelRem = DelegateUtil.getProcedimientoRemotoDelegate();
			pldel.indexBorraProcedimiento((ProcedimientoLocal) objeto);

			if (objeto instanceof ProcedimientoRemoto) {
				pldelRem.indexInsertaProcedimientoRemoto((ProcedimientoRemoto) objeto, null);
			} else {
				pldel.indexInsertaProcedimiento((ProcedimientoLocal) objeto, null);
			}
		}

		if (objeto instanceof NormativaLocal) {
			NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
			normadel.indexBorraNormativa((NormativaLocal) objeto);
			normadel.indexInsertaNormativa((NormativaLocal) objeto, null);
		}

		if (objeto instanceof NormativaExterna) {
			NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
			normadel.indexBorraNormativa((NormativaExterna) objeto);
			normadel.indexInsertaNormativa((NormativaExterna) objeto, null);
		}
	}


	/**
	 * Envía un objecto del tipo OperacionCrawler a la cola.
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */    
	public void envioColaCrawler(String tipo, Ficha ficha) throws DelegateException {

		try {
			if (tipo != null) {
			    if (ficha != null) {
			        Map<String, String> urls = new HashMap<String, String>();
					Iterator iterator = ficha.getLangs().iterator();
					while (iterator.hasNext()) {

						String idi = (String) iterator.next();
						TraduccionFicha trad = ((TraduccionFicha) ficha.getTraduccion(idi));

						if (trad != null) {
							if ((trad.getUrl() != null) && ( trad.getUrl().length() > 0)) {
							    urls.put( idi, trad.getUrl() );
							}
						}
					}

					if (!urls.isEmpty()) {
						log.debug("Envio a la cola del crawler --> Tipo:" + tipo + " Ficha: " + ficha.getId());

						OperacionCrawler operacionCrawler = new OperacionCrawler(tipo, ficha.getId(), "ficha", urls);
						InitialContext ctx = new InitialContext();
						String colaAvisos = (String) ctx.lookup("java:comp/env/colaCrawler");
						Queue queue = (Queue) ctx.lookup(colaAvisos);                             
						QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("java:/ConnectionFactory");
						QueueConnection cnn = factory.createQueueConnection();
						QueueSession sess = cnn.createQueueSession( false, javax.jms.Session.AUTO_ACKNOWLEDGE );                                
						ObjectMessage msg = sess.createObjectMessage(operacionCrawler);
						QueueSender sender = sess.createSender(queue);
						sender.send(msg);
						cnn.close();
					}
				}
			}

		} catch (Exception e) {
			throw new EJBException(e);
		}
	}


	/**
	 * Quita un documento en el indexador.
	 * @throws EJBException 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */    
	public void desindexarObjeto(Object objeto) throws DelegateException {

		if (objeto instanceof Ficha) {
			FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
			fichaDelegate.indexBorraFicha(((Ficha) objeto).getId());

			if (objeto instanceof FichaRemota) {
				try {
					String index = System.getProperty("es.caib.rolsac.crawler.indexLocation");
					Ficha ficha = (Ficha) objeto;
					Crawler crawler = new Crawler(index, ficha.getId(), null);
					crawler.desindexarURLFicha();

				} catch (Exception e) {
					throw new EJBException(e);
				}
			}
		}

		if (objeto instanceof UnidadAdministrativa) {
			UnidadAdministrativaDelegate uadel = DelegateUtil.getUADelegate();
			uadel.indexBorraUA(((UnidadAdministrativa) objeto).getId());
		}

		if (objeto instanceof ProcedimientoLocal) {
			ProcedimientoDelegate pldel = DelegateUtil.getProcedimientoDelegate();
			pldel.indexBorraProcedimiento((ProcedimientoLocal) objeto);
		}

		if (objeto instanceof NormativaLocal) {
			NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
			normadel.indexBorraNormativa((NormativaLocal) objeto);
		}

		if (objeto instanceof NormativaExterna) {
			NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
			normadel.indexBorraNormativa((NormativaExterna) objeto);
		}
	}


	/**
	 * Optimiza el indice de busquedas
	 * @throws IOException 
	 * @throws DelegateException 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */    
	public void optimizar(List<String> langs) throws DelegateException {

        Directory directory = null;
        IndexWriter writer = null;
        IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();
	    for (String lang : langs) {
            try {
                directory = indexerDelegate.getHibernateDirectory(lang);
                writer = new IndexWriter(directory, getAnalizador(lang), MaxFieldLength.UNLIMITED);
                writer.setMergeFactor(20);
                writer.setMaxMergeDocs(Integer.MAX_VALUE);
                optimizarIndice(writer);

            } catch (IOException e) {
                throw new EJBException(e);
            } catch (Exception ex) {
                log.error("No se ha podido borrar la entrada en el indice.");
            } finally {
                try {
                    writer.close();
                    directory.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new EJBException(e);
                }
            }
        }
	}


	/**
	 * Crea o actualiza el diccionario
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */    
	public void confeccionaDiccionario(String idioma) {

	    actualizarDiccionario(idioma);
	}


	/**
	 * Busca un documento.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Long[] buscarIds(String className, String text) {

	    return new Long[1];
	}


	/**
	 * Re-indexa todos los procedimientos.
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @jboss.method-attributes transaction-timeout="86400"
	 */
	public void reindexarProcedimentos() {

		Session session = getSession();
		try {
			log.debug("Inicio indexacion PROCEDIMIENTOS ");

			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
            ProcedimientoRemotoDelegate procedimientoRemotoDelegate = DelegateUtil.getProcedimientoRemotoDelegate();
			List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			net.sf.hibernate.Query query = session.createQuery("from ProcedimientoLocal as proc");
			Iterator iterador = query.iterate();
			StringBuffer stlog = new StringBuffer("");
			int count = 0;

			while (iterador.hasNext()) {
				Object obj = iterador.next();
				procedimientoDelegate.indexBorraProcedimiento((ProcedimientoLocal) obj);

				if (obj instanceof ProcedimientoRemoto) {
				    procedimientoRemotoDelegate.indexInsertaProcedimientoRemoto((ProcedimientoRemoto) obj, null);
				} else {
					procedimientoDelegate.indexInsertaProcedimiento((ProcedimientoLocal) obj, null);
				}

				stlog.append(((ProcedimientoLocal) obj).getId() + " ");

				if (++count % 30  ==  0) {
                    session.flush();
                    session.clear();
                }
			}

			// Optimizamos el indice y diccionario
			optimizar(langs);
            for (String lang : langs) {
                confeccionaDiccionario( "" + lang);
            }

			log.debug("Fin indexacion PROCEDIMIENTOS [" + stlog.toString() + "]");

		} catch (DelegateException e) {
			throw new EJBException(e);
		} catch (HibernateException e) {
			throw new EJBException(e);
        } finally {
			close(session);
		}
	}


	/**
	 * Re-indexa todas las normativas.
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @jboss.method-attributes transaction-timeout="86400"
	 */
	public void reindexarNormativas() {

		Session session = getSession();
		try {
			log.debug("Inicio indexación NORMATIVA ");

			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			net.sf.hibernate.Query query;
			Iterator iterador;
			int count = 0;

			// Normativa local
			query = session.createQuery("from NormativaLocal as nor");
			iterador = query.iterate();
			while (iterador.hasNext()) {
			    NormativaLocal nor = (NormativaLocal) normativaDelegate.obtenerNormativa(((NormativaLocal) iterador.next()).getId());
                normativaDelegate.indexBorraNormativa(nor);
				normativaDelegate.indexInsertaNormativa(nor, null);

				if (++count % 50  ==  0) {
                    session.flush();
                    session.clear();
                }
			}
			log.debug("index NORMATIVAS LOCALES");

			// Normativa externa
			query = session.createQuery("from NormativaExterna as nor");
			iterador = query.iterate();
			while (iterador.hasNext()) {
			    NormativaExterna nor = (NormativaExterna) normativaDelegate.obtenerNormativa(((NormativaExterna) iterador.next()).getId());
				normativaDelegate.indexBorraNormativa(nor);
				normativaDelegate.indexInsertaNormativa(nor, null);

				if (++count % 50  ==  0) {
                    session.flush();
                    session.clear();
                }
			}
			log.debug("index NORMATIVAS EXTERNAS");

			// Optimizamos el indice y diccionario
			optimizar(langs);
            for (String lang : langs) {
                confeccionaDiccionario( "" + lang);
            }

			log.debug("Fin indexacion NORMATIVAS");

		} catch (DelegateException e) {
			throw new EJBException(e);
		} catch (HibernateException e) {
			throw new EJBException(e);
        } finally {
			close(session);
		}
	}


	/**
	 * Re-indexa todas las fichas.
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @jboss.method-attributes transaction-timeout="86400"
	 */
	public void reindexarFichas() {

		Session session = getSession();
		try {
			log.debug("Inicio indexacion FICHAS");

			FichaDelegate fichadel = DelegateUtil.getFichaDelegate();
			List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			net.sf.hibernate.Query query = session.createQuery("from Ficha as fic");
			Iterator iterador = query.iterate();
			int count = 0;

			// Indexamos las WEB EXTERNAS por tanto adjuntamos una Hash para 
			// no repetir peticiones a URLs y agilizar el proceso
			Hashtable urls = new Hashtable(); 

			while (iterador.hasNext()) {
				Ficha fic = fichadel.obtenerFicha(((Ficha) iterador.next()).getId());

				fichadel.indexBorraFicha(fic.getId());
				fichadel.setContenidos_web(urls);
				fichadel.indexInsertaFicha(fic, null);
				urls = fichadel.getContenidos_web();

				if (++count % 10  ==  0) {
					session.flush();
					session.clear();
				}
			}

			// Optimizamos el indice y diccionario
			optimizar(langs);
			for (String lang : langs) {
				confeccionaDiccionario( "" + lang);
			}

			log.debug("Fin indexacion FICHAS");

		} catch (DelegateException e) {
			throw new EJBException(e);
		} catch (HibernateException e) {
			throw new EJBException(e);
        } finally {
			close(session);
		}
	}


	/**
	 * Re-indexa todas las fichas de PMA
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @jboss.method-attributes transaction-timeout="86400"
	 */
	public void reindexarFichasPMA() {
		
		Session session = getSession();
		try {
			log.debug("Inicio indexacion FICHAS ");

			FichaDelegate fichadel = DelegateUtil.getFichaDelegate();
			List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			net.sf.hibernate.Query query = session.createQuery("from Ficha as fic");
			Iterator iterador = query.iterate();
			int count = 0;

			// Indexamos las WEB EXTERNAS por tanto adjuntamos una Hash para
			// no repetir peticiones a URLs y agilizar el proceso
			Hashtable urls = new Hashtable();

			while (iterador.hasNext()) {
				Ficha ficha = fichadel.obtenerFichaPMA(((Ficha) iterador.next()).getId());

				if (ficha != null) {
					fichadel.indexBorraFicha(ficha.getId());
					fichadel.setContenidos_web(urls);
					fichadel.indexInsertaFicha(ficha, null);
					urls = fichadel.getContenidos_web();

					if (++count % 50 == 0) {
						session.flush();
						session.clear();
						//session.reconnect();
					}
				}
			}

			// Optimizamos el indice y diccionario
            optimizar(langs);
            for (String lang : langs) {
                confeccionaDiccionario( "" + lang);
            }

			log.debug("Fin indexacion FICHAS");

		} catch (DelegateException e) {
			throw new EJBException(e);
		} catch (HibernateException e) {
			throw new EJBException(e);
        } finally {
			close(session);
		}
	}


	/**
     * Re-indexa todas las unidades organicas.
     * @throws EJBException
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @jboss.method-attributes transaction-timeout="86400"
     */
    public void reindexarUOs() {

    	Session session = getSession();
    	try {
    		log.debug("Inicio indexacion UOs");

    		UnidadAdministrativaDelegate uadel = DelegateUtil.getUADelegate();
    		List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

    		net.sf.hibernate.Query query = session.createQuery("from UnidadAdministrativa as unidad");
    		Iterator iterador = query.iterate();

    		int count = 0;
    		while (iterador.hasNext()) {
    			UnidadAdministrativa ua = (UnidadAdministrativa) iterador.next();
    			ua = uadel.consultarUnidadAdministrativa(ua.getId());

    			uadel.indexBorraUA(ua.getId());
    			uadel.indexInsertaUA(ua, null);

    			if (++count % 50 == 0) {
    				session.flush();
    				session.clear();
    			}

    			log.debug("index " + ua.getId() + " " + (count++));
    		}

    		// Optimizamos el indice y diccionario
    		optimizar(langs);
            for (String lang : langs) {
                confeccionaDiccionario( "" + lang);
            }

    		log.debug("Fin indexacion UOs");

    	} catch (DelegateException e) {
    		throw new EJBException(e);
    	} catch (HibernateException e) {
    		throw new EJBException(e);
        } finally {
    		close(session);
    	}
    }


	/**
	 * Re-indexa todas las unidades organicas.
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @jboss.method-attributes transaction-timeout="86400"
	 */
	public void reindexarUOsPMA() {

		Session session = getSession();
		try {
			log.debug("Inicio indexacion UOs");

			UnidadAdministrativaDelegate uadel = DelegateUtil.getUADelegate();
			List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			net.sf.hibernate.Query query = session.createQuery("from UnidadAdministrativa as unidad");
			Iterator iterador = query.iterate();

			int count = 0;
			while (iterador.hasNext()) {
				UnidadAdministrativa ua = (UnidadAdministrativa) iterador.next();
				ua = uadel.consultarUnidadAdministrativaPMA(ua.getId());

				if (ua != null) {
					uadel.indexBorraUA(ua.getId());
					uadel.indexInsertaUA(ua, null);

					if (++count % 50 == 0) {
						session.flush();
						session.clear();
					}

					log.debug( "index " + ua.getId() + " " + (count++) );
				}
			}

			// Optimizamos el indice y diccionario
            optimizar(langs);
            for (String lang : langs) {
                confeccionaDiccionario( "" + lang);
            }

			log.debug("Fin indexacion UOs");

		} catch (DelegateException e) {
			throw new EJBException(e);
		} catch (HibernateException e) {
			throw new EJBException(e);
        } finally {
			close(session);
		}
	}


	/**
     * Abrir el directorio del indice.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public Directory getHibernateDirectory(String idi) {

	    Directory directory = null;
	    try {
	        if (FILESYSTEM_INDEX_TYPE.equals(indexType)) {
	            directory = FSDirectory.open(new File(System.getProperty(indexLocation) + "/" + idi));

	        } else if (HIBERNATE_INDEX_TYPE.equals(indexType)) {
	            directory = new HibernateDirectory(sessionFactory);

	        } else {
	            throw new EJBException("Tipo de Índice no valido: " + indexType);
	        }

	        if (!IndexReader.indexExists(directory)) {
	            new IndexWriter(directory, analyzer, true, MaxFieldLength.UNLIMITED).close();
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return directory;
	}


	/*****************************************************************************/
    /*****************      MÉTODOS  PRIVADOS                  *******************/
    /*****************************************************************************/
    
    private synchronized void addDocumentoAlWriter(IndexWriter writer, IndexObject indexObject, String idioma) {

        // Añade un documento
        try {
            Document document = getDocument(indexObject);
            writer.addDocument(document, getAnalizador(idioma));
    
        } catch (IOException e) {
            log.error("addDocumentoAlWriter [" + OPER_ADDDOCUMENT + "] de microsites: " + e.getMessage(), e);
        }
    }


    private synchronized void actualizarDiccionario(String idioma) {

	    // Crea/Actualiza el diccionario
	    try {
	        IndexReader indexReader = IndexReader.open(getHibernateDirectory(idioma));
	        Dictionary dictionary = new LuceneDictionary(indexReader, CAMPO_BUSQUEDAS);
	        SpellChecker spellChecker = new SpellChecker(getHibernateDirectory(idioma + "/dicc"));
	        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Constants.LUCENE_VERSION, getAnalizador(idioma));
	        spellChecker.indexDictionary(dictionary, indexWriterConfig, true);

	        if (indexReader != null) {
	            indexReader.close();
	        }

	    } catch (IOException e) {
	        log.error("actualizarDiccionario [" + OPER_DICCIONARIO + "] de microsites: " + e.getMessage(), e);
	    }
	}


	private synchronized void optimizarIndice(IndexWriter writer) {

	    // Optimiza el índice
	    try {
	        writer.optimize();

	    } catch (IOException e) {
	        log.error("operarConWriter [" + OPER_OPTIMIZAR + "] de microsites: " + e.getMessage(), e);
	    }
	}


	private Document getDocument(IndexObject indexObject) {

		Document document = new Document();
		document.setBoost(indexObject.getBoost());

		/* descriptores identificadores */
		if (indexObject.getId() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_ID, indexObject.getId(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getClasificacion() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_CLASIFICACION, indexObject.getClasificacion(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		/* descriptores filtro */
		if (indexObject.getForo() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_FORO, indexObject.getForo().toString(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getMicro() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_MICRO, indexObject.getMicro().toString(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getRestringido() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_RESTRINGIDO, indexObject.getRestringido(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getFamilia() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_FAMILIA, indexObject.getFamilia().toString(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getSeccion() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_SECCION, indexObject.getSeccion(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getMateria() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_MATERIA, indexObject.getMateria(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getUo() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_UO, indexObject.getUo(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getConforo() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_CONFORO, indexObject.getConforo(), Field.Store.YES, Field.Index.toIndex(true, false)));        
		}

		/* descriptores informacion del documento */
		if (indexObject.getTitulo() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_TITULO, indexObject.getTitulo(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getTituloserviciomain() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_TITULO_SRV_MAIN, indexObject.getTituloserviciomain(), Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getText() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_TEXT, indexObject.getText(), Field.Store.YES, Field.Index.toIndex(true, true)));
		}

		if (indexObject.getTextopcional() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_TEXTOPCIONAL, indexObject.getTextopcional(), Field.Store.YES, Field.Index.toIndex(true, true)));
		}

		if (indexObject.getUrl() != null) {
		    document.add(new Field(Catalogo.DESCRIPTOR_URL, indexObject.getUrl(), Field.Store.YES, Field.Index.NO));
		}

		if (!indexObject.getPublicacion().equals("")) {
			document.add(new Field(Catalogo.DESCRIPTOR_PUBLICACION, indexObject.getPublicacion(), Field.Store.YES, Field.Index.toIndex(true, false)));
		} else {
			document.add(new Field(Catalogo.DESCRIPTOR_PUBLICACION, "00000000", Field.Store.YES, Field.Index.toIndex(true, true)));
		}

		if (!indexObject.getCaducidad().equals("")) {
			document.add(new Field(Catalogo.DESCRIPTOR_CADUCIDAD, indexObject.getCaducidad(), Field.Store.YES, Field.Index.toIndex(true, false)));
		} else {
			document.add(new Field(Catalogo.DESCRIPTOR_CADUCIDAD, "99999999", Field.Store.YES, Field.Index.toIndex(true, false)));
		}

		if (indexObject.getDescripcion() != null) {
		    document.add(new Field( Catalogo.DESCRIPTOR_DESCRIPCION, indexObject.getDescripcion(), Field.Store.YES, Field.Index.toIndex(true, false)));        
		}

		return document;
	}


	private Analyzer getAnalizador(String idioma) {

		if (idioma.toLowerCase().equals("de")) {
			analyzer = new AlemanAnalyzer();
			
		} else if (idioma.toLowerCase().equals("en")) {
			analyzer = new InglesAnalyzer();

		} else if (idioma.toLowerCase().equals("ca")) {
			analyzer = new CatalanAnalyzer();

		} else {
			analyzer = new CastellanoAnalyzer();
		}

		return analyzer;
	}


	/*****************************************************************************/
    /*****************            BÚSQUEDAS AVANZADAS          *******************/
    /*****************************************************************************/    


	/**
     * Busca documentos para un idioma concreto, con opción de sugerir
     * en caso de haber encontrado nada interesante.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public IndexResultados buscarAvanzado(
                            String buscarTodas,
                            String buscarAlguna,
                            String buscarFrase,
                            String buscarNinguna,
                            String tipos,
                            String uo,
                            String materia,
                            Date fechaInicio,
                            Date fechaFin,
                            String ayudas,
                            String idioma,
                            boolean sugerir,
                            boolean restringido) {

        return buscarAvanzado(buscarTodas, buscarAlguna, buscarFrase, buscarNinguna, tipos, uo, materia, fechaInicio, fechaFin, ayudas, idioma, sugerir, restringido);
    }

    /**
     * Busca documentos para un idioma concreto, con opción de sugerir
     * en caso de haber encontrado nada interesante.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public IndexResultados buscarAvanzado(
                            String buscarTodas,
                            String buscarAlguna,
                            String buscarFrase,
                            String buscarNinguna,
                            String tipos,
                            String uo,
                            String materia,
                            Date fechaInicio,
                            Date fechaFin,
                            String ayudas,
                            String idioma,
                            boolean sugerir,
                            boolean restringido,
                            Directory directory) {

        long startTime = System.currentTimeMillis();

        IndexReader reader = null;
        IndexSearcher searcher = null;
        try {
            idioma = idioma.toLowerCase();
            if (directory == null) {
                directory = getHibernateDirectory(idioma);
            }
            reader = IndexReader.open(directory);
            searcher = new IndexSearcher(reader);

            // Montar la query para consultar en el indice de lucene
            Query query = querySearchAdv(idioma, buscarTodas, buscarAlguna, buscarFrase, buscarNinguna, tipos, uo, materia, fechaInicio, fechaFin, ayudas, restringido);

            // Realizar la búsqueda en el indice
            TopDocs hits = searcher.search(query, RESULTATS_CERCA_TOTS);

            long endTime = 0;
            // Si no hay resultados -> se hace un sugerir y se hace un OR
            String cadenaSugerida = null;
            String saltos = null;
            if (buscarTodas.length() > 0) {
                if ((hits.totalHits < MIN_HITS || hits.scoreDocs[0].score < MIN_SCORE ) && sugerir) {

                    Query quisoDecir = sugerir(buscarTodas, idioma, directory);               
                    if (quisoDecir != null) {
                        cadenaSugerida = quisoDecir.toString(CAMPO_BUSQUEDAS).replace('+',' ');
                        saltos = "1";
                    }
                }
            }

            endTime = System.currentTimeMillis();
            IndexResultados res = new IndexResultados(extractHits(hits, idioma, directory), hits.totalHits, (endTime - startTime), buscarTodas, cadenaSugerida, saltos);

            return res;

        } catch (IOException e) {
            log.error( e.getMessage() );
            return null;
        } catch (ParseException e) {
            log.error( e.getMessage() );
            return null;
        } finally {
            try {
                searcher.close();
                reader.close();
                directory.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new EJBException(e);
            }
        }
    }

    private Query querySearchAdv(
                    String idioma,
                    String buscarTodas,
                    String buscarAlguna,
                    String buscarFrase,
                    String buscarNinguna,
                    String tipos,
                    String uo,
                    String materia,
                    Date fechaInicio,
                    Date fechaFin,
                    String ayudas,
                    boolean restringido) {

        BooleanQuery queryTotal = new BooleanQuery();

        // Evitamos los documentos caducados o que no han sido publicados aun filtro de fechas o restringidos si no se trata de consulta interna.
        // Rango de fechas de publicación
        queryTotal = queryRangoFechas(queryTotal, fechaInicio, fechaFin, Catalogo.DESCRIPTOR_PUBLICACION);
        // Rango de fechas de caducidad
        queryTotal = queryRangoFechas(queryTotal, new Date(), null, Catalogo.DESCRIPTOR_CADUCIDAD);

        if (!restringido) {
            Query permitido = new PhraseQuery();
            permitido = new TermQuery(new Term(Catalogo.DESCRIPTOR_RESTRINGIDO, "S"));
            permitido.setBoost(2.0f);
            queryTotal.add(permitido, BooleanClause.Occur.MUST_NOT);
        }

        if (tipos != null && tipos.length() > 0 && !tipos.equals("GEN")) {
            String ltTipus = "";
            ltTipus = tipos.equals("AJU") ? "PRC" : tipos;  
            ltTipus = tipos.equals("FORO") ? "FRM" : tipos;

            Query queryWildcar = new WildcardQuery(new Term(Catalogo.DESCRIPTOR_CLASIFICACION, ltTipus + "*"));
            queryWildcar.setBoost(2.0f);
            queryTotal.add(queryWildcar, BooleanClause.Occur.MUST);
        }

        // Filtro de UO
        if (uo != null && uo.length() > 0) {
            Query queryWildcar = new WildcardQuery(new Term(Catalogo.DESCRIPTOR_UO, "*" + Catalogo.KEY_SEPARADOR + uo + Catalogo.KEY_SEPARADOR + "*"));
            queryWildcar.setBoost(2.0f);
            queryTotal.add(queryWildcar, BooleanClause.Occur.MUST);
        }

        // Filtro de materia
        if (materia != null  &&  materia.length() > 0) {
            Query queryWildcar = new WildcardQuery(new Term(Catalogo.DESCRIPTOR_MATERIA, "*" + Catalogo.KEY_SEPARADOR + materia + Catalogo.KEY_SEPARADOR + "*"));
            queryWildcar.setBoost(2.0f);
            queryTotal.add(queryWildcar, BooleanClause.Occur.MUST);
        }

        // Filtro de ajudes
        if (ayudas != null && ayudas.length() > 0 && !ayudas.equals("on")) {
            Query queryCompuestaAND = new PhraseQuery();
            queryCompuestaAND = new TermQuery(new Term(Catalogo.DESCRIPTOR_FAMILIA, ayudas));
            queryCompuestaAND.setBoost(2.0f);
            queryTotal.add(queryCompuestaAND, BooleanClause.Occur.MUST);
        }

        // Todas las palabras
        queryTotal = queryContenidoParaBuscar(queryTotal, buscarTodas, idioma, false);

        // Alguna de las palabras.
        queryTotal = queryContenidoParaBuscar(queryTotal, buscarAlguna, idioma, true);

        // Frase exacta.
        if (buscarFrase != null  && buscarFrase.length() > 0) {
            Vector words = null;
            words = getWords(buscarFrase, CAMPO_BUSQUEDAS, idioma);

            if (!words.isEmpty()) {
                //Phrasequery para campo Text
                Query queryCompuestaText = getQuery(words, CAMPO_BUSQUEDAS, 0, false);
                queryCompuestaText.setBoost(2.0f);

                Query QueryCompuestaTextoOpcional = getQuery(words, Catalogo.DESCRIPTOR_TEXTOPCIONAL, 0, false);
                QueryCompuestaTextoOpcional.setBoost(2.0f);

                BooleanQuery queryCompuesta = new BooleanQuery();
                queryCompuesta.add(new BooleanClause(queryCompuestaText, BooleanClause.Occur.SHOULD));
                queryCompuesta.add(new BooleanClause(QueryCompuestaTextoOpcional, BooleanClause.Occur.SHOULD));
                queryTotal.add(queryCompuesta, BooleanClause.Occur.MUST); 
            }
        }

        // Palabras que no debe contener
        if (buscarNinguna != null  &&  buscarNinguna.length() > 0) {
            Vector words = null;
            words = getWords(buscarNinguna, CAMPO_BUSQUEDAS, idioma);                

            if (!words.isEmpty()) {
                //Phrasequery para campo Text
                Query queryCompuestaText = getQuery(words, CAMPO_BUSQUEDAS, 200, false);
                queryCompuestaText.setBoost(2.0f);

                Query queryCompuestaTextoOpcional = getQuery(words, Catalogo.DESCRIPTOR_TEXTOPCIONAL, 200, false);
                queryCompuestaTextoOpcional.setBoost(2.0f);

                queryTotal.add(queryCompuestaText, BooleanClause.Occur.MUST_NOT);
                queryTotal.add(queryCompuestaTextoOpcional, BooleanClause.Occur.MUST_NOT);
            }
        }

        log.debug(queryTotal.toString());

        return queryTotal;
    }


    private BooleanQuery queryRangoFechas(BooleanQuery queryTotal, Date fechaInicio, Date fechaFin, String campo) {

        String fechaMinima = (fechaInicio != null) ? new SimpleDateFormat("yyyyMMdd").format(fechaInicio) : "00000000";
        String fechaMaxima = (fechaFin != null) ? new SimpleDateFormat("yyyyMMdd").format(fechaFin) : "99999999";
        TermRangeQuery rango = new TermRangeQuery(campo, fechaMinima, fechaMaxima, true, true);
        rango.setBoost(0.00001f);
        queryTotal.add(rango, BooleanClause.Occur.MUST);

        return queryTotal;
    }


    private BooleanQuery queryContenidoParaBuscar(BooleanQuery querytotal, String buscarCadena, String idioma, boolean combinada) {

        if (buscarCadena != null  && buscarCadena.length() > 0) {
            Vector words = null;
            words = getWords(buscarCadena, CAMPO_BUSQUEDAS, idioma);

            if (!words.isEmpty()) {
                // Phrasequery para campo Text
                Query queryCompuestaText = getQuery(words, CAMPO_BUSQUEDAS, 200, combinada);
                queryCompuestaText.setBoost(2.0f);

                Query queryCompuestaOpcional = getQuery( words, Catalogo.DESCRIPTOR_TEXTOPCIONAL, 200, combinada);
                queryCompuestaOpcional.setBoost(2.0f);

                BooleanQuery queryCompuesta = new BooleanQuery();
                queryCompuesta.add(new BooleanClause(queryCompuestaText, BooleanClause.Occur.SHOULD));
                queryCompuesta.add(new BooleanClause(queryCompuestaOpcional, BooleanClause.Occur.SHOULD));
                querytotal.add(queryCompuesta, BooleanClause.Occur.MUST);
            }
        }

        return querytotal;
    }


    private Query getQuery(Vector words, String campo, int slop, boolean combinada) {

        //Phrasequery para campo Text
        Query queryText = null;
        if (words.size() > 1) {
            Query[] expandedQueries = new Query[words.size()];
            queryText = new PhraseQuery();
            ((PhraseQuery) queryText).setSlop(slop);

            for (int i = 0; i < words.size(); i++) {
                if (combinada) {
                    expandedQueries[i] = new TermQuery(new Term(campo, (String) words.elementAt(i)));
                } else {
                    ((PhraseQuery) queryText).add(new Term(campo, (String) words.elementAt(i)));
                }
            }

            if (combinada) {
                // Combinarlas entre si. Es equivalente a un OR de todo con todo
                queryText = queryText.combine(expandedQueries);
            }

        } else if (words.size() == 1) {
            queryText = new TermQuery(new Term(campo, (String) words.elementAt(0)));
        }

        return queryText;
    }


    private Vector getWords(String cadena, String campo, String idioma) {

        Vector words = new Vector();                
        TokenStream tokens = getAnalizador(idioma).tokenStream(campo, new StringReader(cadena));

        CharTermAttribute cattr = tokens.addAttribute(CharTermAttribute.class);
        try {
            tokens.reset();
            while (tokens.incrementToken()) {
                words.add(cattr.toString());
            }
            tokens.end();
            tokens.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }


    private Query sugerir(String queryString, String idioma, Directory directory) throws ParseException {

        if (directory == null) {
            directory = getHibernateDirectory(idioma + "/dicc");
        }
        QuerySuggester querySuggester = new QuerySuggester(CAMPO_BUSQUEDAS, getAnalizador(idioma), directory, idioma);
        querySuggester.setDefaultOperator(QueryParser.AND_OPERATOR);
        Query query = querySuggester.parse((queryString.length() > 0) ? queryString : " ");

        return querySuggester.hasSuggestedQuery() ? query : null;
    }


    private List<IndexEncontrado> extractHits(TopDocs hits, String idioma, Directory directory) throws IOException {

        List<IndexEncontrado> hitList = new ArrayList<IndexEncontrado>();
        IndexReader reader = IndexReader.open(directory);

        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = reader.document(scoreDoc.doc);
            hitList.add(new IndexEncontrado( 
                    doc.get(Catalogo.DESCRIPTOR_ID), 
                    doc.get(Catalogo.DESCRIPTOR_CLASIFICACION ),
                    doc.get(Catalogo.DESCRIPTOR_MICRO) ,                                            
                    doc.get(Catalogo.DESCRIPTOR_FAMILIA),
                    doc.get(Catalogo.DESCRIPTOR_SECCION),
                    doc.get(Catalogo.DESCRIPTOR_MATERIA),
                    doc.get(Catalogo.DESCRIPTOR_UO),
                    doc.get(Catalogo.DESCRIPTOR_CONFORO),                                           
                    doc.get(Catalogo.DESCRIPTOR_TITULO), 
                    doc.get(Catalogo.DESCRIPTOR_DESCRIPCION),
                    doc.get(Catalogo.DESCRIPTOR_TITULO_SRV_MAIN),                                           
                    doc.get(Catalogo.DESCRIPTOR_URL) , 
                    new Float(100 * scoreDoc.score).intValue()) 
            );
        }

        reader.close();
        return hitList;
    }


	/* Clase que sobreescribe un método de QueryParser para permitir hacer sugerencias 
	 * cuando las búsquedas sean compuestas
	 */ 
	public class QuerySuggester extends QueryParser {

		private boolean suggestedQuery = false;
		private Directory spellIndexDirectory;
		private String idioma;


		public QuerySuggester(String field, Analyzer analyzer, Directory dir, String idi) {

			super(Constants.LUCENE_VERSION, field, analyzer);
			spellIndexDirectory = dir;
			idioma = idi;
		}


		
		public boolean hasSuggestedQuery() {

			return suggestedQuery;
		}

	}

}
