package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RangeQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.ibit.lucene.analysis.AlemanAnalyzer;
import org.ibit.lucene.analysis.CastellaCatalaAnalyzer;
import org.ibit.lucene.analysis.CastellanoAnalyzer;
import org.ibit.lucene.analysis.CatalanAnalyzer;
import org.ibit.lucene.analysis.InglesAnalyzer;
import org.ibit.lucene.hibernate.HibernateDirectory;
import org.ibit.lucene.indra.model.Catalogo;
import org.ibit.lucene.indra.model.IndexEncontrado;
import org.ibit.lucene.indra.model.IndexResultados;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoRemotoDelegate;
import org.ibit.rol.sac.persistence.delegate.UARemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.crawler.*;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * SessionBean para mantener y consultar Boletin.
 *
 * @ejb.bean
 *  name="sac/persistence/IndexerFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IndexerFacade"
 *  type="Stateless"
 *  view-type="local"
 *  transaction-type="Container"
 *
 * @ejb.env-entry name="colaCrawler" value="queue/CrawlerQueue"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class IndexerFacadeEJB extends HibernateEJB {

	protected static Log log = LogFactory.getLog(IndexerFacadeEJB.class);
	
    public static final String FILESYSTEM_INDEX_TYPE = "filesystem";
    public static final String HIBERNATE_INDEX_TYPE = "hibernate";
    public static final int MIN_HITS=1;
    public static final int MAX_HITS=100;
    public static final double MIN_SCORE=0; //0.20;
    public static final String CAMPO_BUSQUEDAS="text";
    public static final String OPER_ADDDOCUMENT="addDocument";
    public static final String OPER_DICCIONARIO="diccionario";
    public static final String OPER_OPTIMIZAR="optimizar";

    private Analyzer analyzer;

    /**
     * Tipus de directori Lucene a emprar.
     * @ejb.env-entry value="${index.type}"
     */
    protected String indexType;

    /**
     * Ubicació del directori de Lucene a emprar.
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
     * Indexa un objeto documento en un idioma
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void insertaObjeto(IndexObject indexObject, String idi) {
    	if (indexObject!=null)
    		if (indexObject.getTitulo()!=null && indexObject.getTitulo().length()>0)
    			operarConWriter(OPER_ADDDOCUMENT, indexObject, idi);
        
    }


    /**
     * Borra un objeto documento en un idioma
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void borrarObjeto(String id, String idi) {
        try {
        	Directory directory = getHibernateDirectory(idi);
            IndexReader reader = IndexReader.open(directory);
            reader.deleteDocuments(new Term(Catalogo.DESCRIPTOR_ID, id));
            reader.close();
        } catch (IOException e) {
            throw new EJBException(e);
        }
    }
    
    /**
     * Borra un objetos dependientes de un documento en un idioma
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void borrarObjetosDependientes(String id, String idi) {
        try {
        	Directory directory = getHibernateDirectory(idi);
            IndexReader reader = IndexReader.open(directory);
            reader.deleteDocuments(new Term(Catalogo.DESCRIPTOR_CLASIFICACION, id));
            reader.close();
        } catch (IOException e) {
            throw new EJBException(e);
        }
    }

    /**
     * Crea o actualiza un documento en el indexador
     * @throws DelegateException  
     * @throws IndexingException 
     * @throws IOException 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public void indexarObjeto(Object objeto) throws DelegateException {
    	
    	if (objeto instanceof Ficha) {
    		FichaDelegate ficdel = DelegateUtil.getFichaDelegate();    		
    		ficdel.indexBorraFicha(((Ficha)objeto).getId());
    		ficdel.indexInsertaFicha((Ficha)objeto, null);
    		//solo indexamos la URL en caso de que la ficha sea Remota
    		if (objeto instanceof FichaRemota) {
        		try {
    				Ficha ficha=(Ficha)objeto;
    				envioColaCrawler("I",ficha);

			} catch (Exception e) {
				 throw new EJBException(e);
				}
    		}
    	}
    	if (objeto instanceof UnidadAdministrativa) {
    		UnidadAdministrativaDelegate uadel = DelegateUtil.getUADelegate();
    		UARemotaDelegate uaREmdel = DelegateUtil.getUARemotaDelegate();
    		uadel.indexBorraUA(((UnidadAdministrativa)objeto).getId());
    		if(objeto instanceof UnidadAdministrativaRemota){
    			uaREmdel.indexInsertaUARemota((UnidadAdministrativa)objeto, null);
    		}
    		else{
    			uadel.indexInsertaUA((UnidadAdministrativa)objeto, null);
    		}
    		
    		
    	}    	
    	if (objeto instanceof ProcedimientoLocal) {
    		ProcedimientoDelegate pldel = DelegateUtil.getProcedimientoDelegate();
    		ProcedimientoRemotoDelegate pldelRem = DelegateUtil.getProcedimientoRemotoDelegate();
    		pldel.indexBorraProcedimiento((ProcedimientoLocal)objeto);
        	if(objeto instanceof ProcedimientoRemoto){
        		pldelRem.indexInsertaProcedimientoRemoto((ProcedimientoRemoto)objeto, null);
        	}else{
            	pldel.indexInsertaProcedimiento((ProcedimientoLocal)objeto, null);
        	}
    	}    	
    	if (objeto instanceof NormativaLocal) {
    		NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
    		normadel.indexBorraNormativa((NormativaLocal)objeto);
        	normadel.indexInsertaNormativa((NormativaLocal)objeto, null);
    	}    	
    	if (objeto instanceof NormativaExterna) {
    		NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
    		normadel.indexBorraNormativa((NormativaExterna)objeto);
        	normadel.indexInsertaNormativa((NormativaExterna)objeto, null);
    	}    	
    	
    }

    /**
     * Envia un objecto del tipo OperacionCrawler a la cola
     * @throws DelegateException 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public void envioColaCrawler(String tipo,Ficha ficha) throws DelegateException {
    	try {
    		if(tipo!=null){
    			if(ficha!=null){
    				    			    			  	
	    			Map<String, String> urls = new HashMap<String, String>();
					for (Iterator iterator = ficha.getLangs().iterator(); iterator.hasNext();) {  		
		        		String idi = (String) iterator.next();
		        		TraduccionFicha trad=((TraduccionFicha)ficha.getTraduccion(idi));
	    	        		
		        		if(trad!=null){
		                	if ( (trad.getUrl()!=null) && (trad.getUrl().length()>0) ) {
		                		urls.put(idi, trad.getUrl());
		                	}
		        		}
	    			}
					
					if (!urls.isEmpty()) {
						log.debug("Envio a la cola del crawler --> Tipo:"+tipo+" Ficha: "+ficha.getId());
						
		    			OperacionCrawler operacionCrawler = new OperacionCrawler(tipo,ficha.getId(),"ficha",urls);
		                InitialContext ctx = new InitialContext();
		                String colaAvisos = (String) ctx.lookup("java:comp/env/colaCrawler");
		                Queue queue = (Queue) ctx.lookup(colaAvisos);                             
		                QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("java:/ConnectionFactory");
		                QueueConnection cnn = factory.createQueueConnection();
		                QueueSession sess = cnn.createQueueSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);                                
		                ObjectMessage msg =sess.createObjectMessage(operacionCrawler);
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
     * Quita un documento en el indexador
     * @throws DelegateException 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public void desindexarObjeto(Object objeto) throws DelegateException {
    	if (objeto instanceof Ficha) {
    		FichaDelegate ficdel = DelegateUtil.getFichaDelegate();
    		ficdel.indexBorraFicha(((Ficha)objeto).getId());
    		
    		if (objeto instanceof FichaRemota) {
        		try {
        			String index=System.getProperty("es.caib.rolsac.crawler.indexLocation");
        			Ficha ficha=(Ficha)objeto;
            		Crawler crawler=new Crawler(index, ficha.getId(), null);
            		crawler.desindexarURLFicha();

        		} catch (Exception e) {
        			throw new EJBException(e);
				}
    		}
    	}
    	if (objeto instanceof UnidadAdministrativa) {
    		UnidadAdministrativaDelegate uadel = DelegateUtil.getUADelegate();
    		uadel.indexBorraUA(((UnidadAdministrativa)objeto).getId());
    	}
    	if (objeto instanceof ProcedimientoLocal) {
    		ProcedimientoDelegate pldel = DelegateUtil.getProcedimientoDelegate();
        	pldel.indexBorraProcedimiento((ProcedimientoLocal)objeto);
    	}    	
    	if (objeto instanceof NormativaLocal) {
    		NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
    		normadel.indexBorraNormativa((NormativaLocal)objeto);
    	}    	
    	if (objeto instanceof NormativaExterna) {
    		NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
    		normadel.indexBorraNormativa((NormativaExterna)objeto);
    	}    	
    	
    }
    
    /**
     * Optimiza el indice de busquedas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public void optimizar(String idioma) {
    	
    	operarConWriter(OPER_OPTIMIZAR, null, idioma);

    }    
    
    /**
     * Crea o actualiza el diccionario
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public void confeccionaDiccionario(String idioma) {
    	
    	operarConWriter(OPER_DICCIONARIO, null, idioma);

    }     


    /**
     * Busca un documento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Long[] buscarIds(String className, String text) {

    		return new Long[1];

    	/*
    	 *   //antigu buscarids 
    	 
        try {
            Directory directory = getHibernateDirectory();
            IndexSearcher searcher = new IndexSearcher(directory);

            Query classQuery = new TermQuery(new Term("className", className));

            QueryParser parser = new QueryParser("text", analyzer);
            //parser.setOperator(QueryParser.DEFAULT_OPERATOR_AND);
            parser.setDefaultOperator(QueryParser.AND_OPERATOR);
            Query textQuery = parser.parse(text);

            BooleanQuery query = new BooleanQuery();
            query.add(classQuery, BooleanClause.Occur.MUST);
            query.add(textQuery, BooleanClause.Occur.MUST);
            //query.add(classQuery, true, false);
            //query.add(textQuery, true, false);
            Hits hits = searcher.search(query);

            Long[] results = new Long[hits.length()];
            for (int i = 0; i < hits.length(); i++) {
                Document doc = hits.doc(i);
                results[i] = new Long(doc.get("id"));
            }
            searcher.close();
            return results;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
        */
    }

    /**
     * Re-indexa todos los procedimientos.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 * @jboss.method-attributes transaction-timeout="86400"
     */
    public void reindexarProcedimentos() {
        Session session = getSession();
        
        try {
        	
        	log.debug("Inicio indexacion PROCEDIMIENTOS ");
        	
        	String hql="";
        	net.sf.hibernate.Query query;
        	Iterator iter;
        	StringBuffer stlog = new StringBuffer("");
        	
            stlog = new StringBuffer("");
            hql="from ProcedimientoLocal as proc";
            query = session.createQuery(hql);
            iter = query.iterate();
            ProcedimientoDelegate procdel = DelegateUtil.getProcedimientoDelegate();
            ProcedimientoRemotoDelegate procremdel = DelegateUtil.getProcedimientoRemotoDelegate();
            while (iter.hasNext()) {
                Object obj = iter.next();
                procdel.indexBorraProcedimiento((ProcedimientoLocal) obj);
                if(obj instanceof ProcedimientoRemoto){
                    procremdel.indexInsertaProcedimientoRemoto((ProcedimientoRemoto)obj, null);
                }else{
                    procdel.indexInsertaProcedimiento((ProcedimientoLocal)obj, null);
                }
            	//ProcedimientoLocal proc2 = procdel.obtenerProcedimiento(proc1.getId());
            	stlog.append(((ProcedimientoLocal)obj).getId() + " ");
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @jboss.method-attributes transaction-timeout="86400"
     */
    public void reindexarNormativas() {
        Session session = getSession();
        
        try {
        	
        	log.debug("Inicio indexacion NORMATIVA ");
        	
        	String hql="";
        	net.sf.hibernate.Query query;
        	Iterator iter;
        	NormativaDelegate normadel = DelegateUtil.getNormativaDelegate();
        	
        	//normativa local
            hql="from NormativaLocal as nor";
            query = session.createQuery(hql);
            iter = query.iterate();
            while (iter.hasNext()) {
            	NormativaLocal nor = (NormativaLocal) iter.next();
            	normadel.indexBorraNormativa(nor);
            	normadel.indexInsertaNormativa(nor, null);
            }
            log.debug("index NORMATIVAS LOCALES");     
            
        	//normativa externa
            hql="from NormativaExterna as nor";
            query = session.createQuery(hql);
            iter = query.iterate();
            while (iter.hasNext()) {
            	NormativaExterna nor = (NormativaExterna) iter.next();
            	normadel.indexBorraNormativa(nor);
            	normadel.indexInsertaNormativa(nor, null);
            }
            
            //  Optimizamos el indice y diccionario
            List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
           	for (int i = 0; i < langs.size(); i++) {
            		optimizar(""+langs.get(i));
            		confeccionaDiccionario(""+langs.get(i));
           	}
            
            log.debug("index NORMATIVAS EXTERNAS");              
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @jboss.method-attributes transaction-timeout="86400"
     */
    public void reindexarFichas() {
        Session session = getSession();

        try {
        	
        	log.debug("Inicio indexacion FICHAS ");
        	
        	List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        	String hql="";
        	net.sf.hibernate.Query query;
        	Iterator iter;
        	
            hql="from Ficha as fic";
            query = session.createQuery(hql);
            iter = query.iterate();
            FichaDelegate fichadel = DelegateUtil.getFichaDelegate();
            int count = 0;

            // Indexamos las WEB EXTERNAS por tanto adjuntamos una Hash para 
            // no repetir peticiones a URLs y agilizar el proceso
            Hashtable urls = new Hashtable(); 

            while (iter.hasNext()) {
            	Ficha fic = fichadel.obtenerFicha( ((Ficha)iter.next()).getId() );
            	fichadel.indexBorraFicha(fic.getId());
            	fichadel.setContenidos_web(urls);
            	fichadel.indexInsertaFicha(fic, null);
            	urls=fichadel.getContenidos_web();
                if (++count % 50 == 0) {
                    session.flush();
                    session.clear();
                    //session.reconnect();
                }
            }
            
            // Optimizamos el indice y diccionario
           	for (int i = 0; i < langs.size(); i++) {
            		optimizar(""+langs.get(i));
            		confeccionaDiccionario(""+langs.get(i));
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
       * @ejb.interface-method
       * @ejb.permission unchecked="true"
       * @jboss.method-attributes transaction-timeout="86400"
       */
      public void reindexarFichasPMA() {
          Session session = getSession();

          try {

              log.debug("Inicio indexacion FICHAS ");

              List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
              String hql="";
              net.sf.hibernate.Query query;
              Iterator iter;

              hql="from Ficha as fic";
              query = session.createQuery(hql);
              iter = query.iterate();
              FichaDelegate fichadel = DelegateUtil.getFichaDelegate();
              int count = 0;

              // Indexamos las WEB EXTERNAS por tanto adjuntamos una Hash para
              // no repetir peticiones a URLs y agilizar el proceso
              Hashtable urls = new Hashtable();

              while (iter.hasNext()) {
                  Ficha fic = fichadel.obtenerFichaPMA(((Ficha)iter.next()).getId());
                  if(fic != null){
                      fichadel.indexBorraFicha(fic.getId());
                      fichadel.setContenidos_web(urls);
                      fichadel.indexInsertaFicha(fic, null);
                      urls=fichadel.getContenidos_web();
                      if (++count % 50 == 0) {
                          session.flush();
                          session.clear();
                          //session.reconnect();
                      }
                  }
              }

              // Optimizamos el indice y diccionario
                 for (int i = 0; i < langs.size(); i++) {
                      optimizar(""+langs.get(i));
                      confeccionaDiccionario(""+langs.get(i));
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @jboss.method-attributes transaction-timeout="86400"
     */
    public void reindexarUOs() {
        Session session = getSession();

        try {
        	
        	log.debug("Inicio indexacion UOs");
        	
        	List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        	String hql="";
        	net.sf.hibernate.Query query;
        	Iterator iter;
        	
            hql="from UnidadAdministrativa as unidad";
            query = session.createQuery(hql);
            iter = query.iterate();
            UnidadAdministrativaDelegate uadel = DelegateUtil.getUADelegate();
            //iter = uadel.buscarUnidadesAdministrativas(new HashMap(), new HashMap()).iterator();
            
            int count = 0;
            while (iter.hasNext()) {
            	UnidadAdministrativa ua = (UnidadAdministrativa) iter.next();
            	ua = uadel.consultarUnidadAdministrativa(ua.getId());
            	uadel.indexBorraUA(ua.getId());
            	uadel.indexInsertaUA(ua, null);
                if (++count % 50 == 0) {
                    session.flush();
                    session.clear();
                    //session.reconnect();
                }
            	log.debug("index " + ua.getId() + " " + (count++));
            }
                    	
        	
            // Optimizamos el indice y diccionario
           	for (int i = 0; i < langs.size(); i++) {
            		optimizar(""+langs.get(i));
            		confeccionaDiccionario(""+langs.get(i));
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @jboss.method-attributes transaction-timeout="86400"
     */
    public void reindexarUOsPMA() {
        Session session = getSession();

        try {

        	log.debug("Inicio indexacion UOs");

        	List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        	String hql="";
        	net.sf.hibernate.Query query;
        	Iterator iter;

            hql="from UnidadAdministrativa as unidad";
            query = session.createQuery(hql);
            iter = query.iterate();
            UnidadAdministrativaDelegate uadel = DelegateUtil.getUADelegate();
            //iter = uadel.buscarUnidadesAdministrativas(new HashMap(), new HashMap()).iterator();

            int count = 0;
            while (iter.hasNext()) {
            	UnidadAdministrativa ua = (UnidadAdministrativa) iter.next();
            	ua = uadel.consultarUnidadAdministrativaPMA(ua.getId());
            	if(ua != null){
	            	uadel.indexBorraUA(ua.getId());
	            	uadel.indexInsertaUA(ua, null);
	                if (++count % 50 == 0) {
	                    session.flush();
	                    session.clear();
	                    //session.reconnect();
	                }
	            	log.debug("index " + ua.getId() + " " + (count++));
            	}
            }


            // Optimizamos el indice y diccionario
           	for (int i = 0; i < langs.size(); i++) {
            		optimizar(""+langs.get(i));
            		confeccionaDiccionario(""+langs.get(i));
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
    
    
    /*****************************************************************************/
    /*****************              BUSQUEDAS                  *******************/
    /*****************************************************************************/    
    
    
    /**
     * Busca documentos para un idioma concreto, con opción de sugerir
     * en caso de haber encontrado nada interesante.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public IndexResultados buscar(String idi, String cadena, boolean sugerir, boolean restringido) {
    	long startTime = System.currentTimeMillis();
        try {
        	idi = idi.toLowerCase();
            Directory directory = getHibernateDirectory(idi);
            IndexSearcher searcher = new IndexSearcher(directory);

            Query query = null;
            IndexResultados res= null;

            //Buscamos la frase exacta que se ha introducido
           	query = QuerySearchAdv(idi, null, null, cadena, null, "GEN", null, null, null, null, null, restringido);
        
            Hits hits = searcher.search(query);
            long endTime=0;

            //si no hay resultados->se hace un sugerir y se hace un OR

			String cadenaSugerida = null;
			String saltos = null;
			if (cadena.length()>0)
				{
	            if ((hits.length() < MIN_HITS || hits.score(0) < MIN_SCORE) && sugerir) {
					Query quisoDecir = sugerir(cadena, idi );				
					if (quisoDecir != null)
					{
						cadenaSugerida = quisoDecir.toString(CAMPO_BUSQUEDAS).replace('+',' ');
						saltos = "1";
					}
		            //Buscamos alguna de las palabras que se ha introducido					
					query = QuerySearchAdv(idi, cadena, null, null, null, "GEN", null, null, null, null, null, restringido);
					hits = searcher.search(query);
					if ((hits.length() < MIN_HITS || hits.score(0) < MIN_SCORE)) {
			            //Buscamos cualquiera de las palabras que se ha introducido						
						query = QuerySearchAdv(idi, null, cadena, null, null, "GEN", null, null, null, null, null, restringido);
						hits = searcher.search(query);						
					}
						
				}
			}

        	endTime = System.currentTimeMillis();
        	res= new IndexResultados( extractHits(hits), hits.length(), (endTime - startTime), cadena, cadenaSugerida, saltos );
            
            searcher.close();
            
        	return res;            
            
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        } catch (ParseException e) {
        	log.error(e.getMessage());
        	return null;
		}
    }
    
    
    private Query QuerySearch1(String idi, String cadena) {
    	BooleanQuery querytotal = new BooleanQuery();
    	
        
            // Evitamos los documentos caducados o que no han sido publicados aun
            Term fechamin_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION,"00000000");
            Term fechamax_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD,"99999999");

            String hoy=new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
            Term hoy_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, hoy );
            Term hoy_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD, hoy );

            RangeQuery publica = new RangeQuery(fechamin_pub, hoy_pub , true);
            RangeQuery caduca = new RangeQuery(hoy_cad, fechamax_cad , true);
            publica.setBoost(0.00001f); caduca.setBoost(0.00001f);
            
            querytotal.add(publica, BooleanClause.Occur.MUST);
            querytotal.add(caduca, BooleanClause.Occur.MUST);

            //Sacamos las palabras pasando el analizador (con su extractor correspondiente)
            Vector words = new Vector();            	
            TokenStream tokens = getAnalizador(idi).tokenStream(CAMPO_BUSQUEDAS, new StringReader (cadena));
			Token token;
			while (true) {
				try {
					token = tokens.next();
				} catch (IOException e) {
					token = null;
				}
				if (token == null) break;
				words.addElement(token.termText());
			}
			try {
				tokens.close();
			} catch (IOException e) {}                  

			
            if (cadena!=null && cadena.length()>0) {
            	
            	//Phrasequery para campo Text
            	Query query_compuesta_text = null;
    			if (words.size() > 1) {
    				query_compuesta_text = new PhraseQuery();
    				((PhraseQuery)query_compuesta_text).setSlop(5);
    				for (int i = 0; i < words.size(); i++) {
    					((PhraseQuery)query_compuesta_text).add(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(i)));
    				}
    			} else if (words.size() == 1) {
    				query_compuesta_text = new TermQuery(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(0)));
    			} 
    			query_compuesta_text.setBoost(2.0f);
    			
            	//Phrasequery para campo Textopcional
    			Query query_compuesta_textopcional = null;            	
    			if (words.size() > 1) {
    				query_compuesta_textopcional = new PhraseQuery();
    				((PhraseQuery)query_compuesta_textopcional).setSlop(10);
    				for (int i = 0; i < words.size(); i++) {
    					((PhraseQuery)query_compuesta_textopcional).add(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(i)));
    				}
    			} else if (words.size() == 1) {
    				query_compuesta_textopcional = new TermQuery(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(0)));
    			} 
    			query_compuesta_textopcional.setBoost(2.0f);
    			
    			BooleanQuery query_compuestaOR = new BooleanQuery();
    			query_compuestaOR.add(new BooleanClause(query_compuesta_text, BooleanClause.Occur.SHOULD));
    			query_compuestaOR.add(new BooleanClause(query_compuesta_textopcional, BooleanClause.Occur.SHOULD));
    			querytotal.add(query_compuestaOR, BooleanClause.Occur.MUST);
    			
    			
            }
         
            log.debug(querytotal.toString());

    	
    	return querytotal;
    }
    
    
    private Query QuerySearch2(String idi, String cadena) {
    	BooleanQuery querytotal = new BooleanQuery();
    	
        
            // Evitamos los documentos caducados o que no han sido publicados aun
            Term fechamin_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION,"00000000");
            Term fechamax_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD,"99999999");

            String hoy=new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
            Term hoy_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, hoy );
            Term hoy_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD, hoy );

            RangeQuery publica = new RangeQuery(fechamin_pub, hoy_pub , true);
            RangeQuery caduca = new RangeQuery(hoy_cad, fechamax_cad , true);
            publica.setBoost(0.00001f); caduca.setBoost(0.00001f);
            
            querytotal.add(publica, BooleanClause.Occur.MUST);
            querytotal.add(caduca, BooleanClause.Occur.MUST);

            //Sacamos las palabras pasando el analizador (con su extractor correspondiente)
            Vector words = new Vector();            	
            TokenStream tokens = getAnalizador(idi).tokenStream(CAMPO_BUSQUEDAS, new StringReader (cadena));
			Token token;
			while (true) {
				try {
					token = tokens.next();
				} catch (IOException e) {
					token = null;
				}
				if (token == null) break;
				words.addElement(token.termText());
			}
			try {
				tokens.close();
			} catch (IOException e) {}                  

			
            if (cadena!=null && cadena.length()>0) {
            	
            	//Phrasequery para campo Text
            	Query query_compuesta_text = null;
    			if (words.size() > 1) {
    				Query[] expandedQueries=new Query[words.size()];
    				query_compuesta_text = new PhraseQuery();
    				((PhraseQuery)query_compuesta_text).setSlop(15);
    				for (int i = 0; i < words.size(); i++) {
    					((PhraseQuery)query_compuesta_text).add(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(i)));
    					expandedQueries[i] = new TermQuery(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(i)));
    				}
    				query_compuesta_text = query_compuesta_text.combine(expandedQueries); //combinarlas entre si. Es equivalente a un OR de todo con todo    				
    				
    			} else if (words.size() == 1) {
    				query_compuesta_text = new TermQuery(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(0)));
    			} 
    			query_compuesta_text.setBoost(2.0f);


    			
            	//Phrasequery para campo Textopcional
    			Query query_compuesta_textopcional = null;
    			if (words.size() > 1) {
    				Query[] expandedQueries=new Query[words.size()];
    				query_compuesta_textopcional = new PhraseQuery();
    				((PhraseQuery)query_compuesta_textopcional).setSlop(15);
    				for (int i = 0; i < words.size(); i++) {
    					((PhraseQuery)query_compuesta_textopcional).add(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(i)));
    					expandedQueries[i] = new TermQuery(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(i)));
    				}
    				query_compuesta_textopcional = query_compuesta_textopcional.combine(expandedQueries); //combinarlas entre si. Es equivalente a un OR de todo con todo    				
    				
    			} else if (words.size() == 1) {
    				query_compuesta_textopcional = new TermQuery(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(0)));
    			} 
    			query_compuesta_textopcional.setBoost(1.9f);    		
    			
    			BooleanQuery query_compuestaOR = new BooleanQuery();
    			query_compuestaOR.add(new BooleanClause(query_compuesta_text, BooleanClause.Occur.SHOULD));
    			query_compuestaOR.add(new BooleanClause(query_compuesta_textopcional, BooleanClause.Occur.SHOULD));
    			querytotal.add(query_compuestaOR, BooleanClause.Occur.MUST);    			
    			
            }
         
            log.debug(querytotal.toString());

    	
    	return querytotal;
    }    
    
    
    private Query QuerySearchPRUEBAS(String idi, String cadena) {
    	BooleanQuery querytotal = new BooleanQuery();
    	
        
            // Evitamos los documentos caducados o que no han sido publicados aun
            Term fechamin_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION,"00000000");
            Term fechamax_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD,"99999999");

            String hoy=new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
            Term hoy_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, hoy );
            Term hoy_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD, hoy );

            RangeQuery publica = new RangeQuery(fechamin_pub, hoy_pub , true);
            RangeQuery caduca = new RangeQuery(hoy_cad, fechamax_cad , true);
            publica.setBoost(0.00001f); caduca.setBoost(0.00001f);
            
            querytotal.add(publica, BooleanClause.Occur.MUST);
            querytotal.add(caduca, BooleanClause.Occur.MUST);

            //Sacamos las palabras pasando el analizador (con su extractor correspondiente)
            Vector words = new Vector();            	
            TokenStream tokens = getAnalizador(idi).tokenStream(CAMPO_BUSQUEDAS, new StringReader (cadena));
			Token token;
			while (true) {
				try {
					token = tokens.next();
				} catch (IOException e) {
					token = null;
				}
				if (token == null) break;
				words.addElement(token.termText());
			}
			try {
				tokens.close();
			} catch (IOException e) {}                  

			
            if (cadena!=null && cadena.length()>0) {
            	
            	//Phrasequery para campo Text
            	Query query_compuestaAND = null;
            	
    			if (words.size() > 1) {
    				query_compuestaAND = new PhraseQuery();
    				((PhraseQuery)query_compuestaAND).setSlop(5);
    				for (int i = 0; i < words.size(); i++) {
    					((PhraseQuery)query_compuestaAND).add(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(i)));
    				}
    			} else if (words.size() == 1) {
    				query_compuestaAND = new TermQuery(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(0)));
    			} 
    			query_compuestaAND.setBoost(0.0001f);
    			querytotal.add(query_compuestaAND, BooleanClause.Occur.MUST);
    			
    			
            	//Phrasequery para campo Textopcional
            	query_compuestaAND = null;
    			if (words.size() > 1) {
    				query_compuestaAND = new PhraseQuery();
    				((PhraseQuery)query_compuestaAND).setSlop(10);
    				for (int i = 0; i < words.size(); i++) {
    					((PhraseQuery)query_compuestaAND).add(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(i)));
    				}
    			} else if (words.size() == 1) {
    				query_compuestaAND = new TermQuery(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(0)));
    			} 
    			query_compuestaAND.setBoost(1.5f);
    			
    			querytotal.add(query_compuestaAND , BooleanClause.Occur.SHOULD);
    			
            }
         
            log.debug(querytotal.toString());

    	
    	return querytotal;
    }    
    
    
    /*****************************************************************************/
    /*****************            BUSQUEDAS AVANZADAS          *******************/
    /*****************************************************************************/    
    
    
    /**
     * Busca documentos para un idioma concreto, con opción de sugerir
     * en caso de haber encontrado nada interesante.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
        
    public IndexResultados buscaravanzado(String cerca_totes, String cerca_alguna, String cerca_frase, String cerca_cap, String tipus, String uo, String mat, Date fini, Date ffin, String ajudes, String idi, boolean sugerir, boolean restringido) {
    	long startTime = System.currentTimeMillis();
        try {
        	idi = idi.toLowerCase();
            Directory directory = getHibernateDirectory(idi);
            IndexSearcher searcher = new IndexSearcher(directory);

            Query query = null;
            IndexResultados res= null;

           	query = QuerySearchAdv(idi, cerca_totes, cerca_alguna, cerca_frase, cerca_cap, tipus, uo, mat, fini, ffin, ajudes, restringido);
        
            Hits hits = searcher.search(query);
            long endTime=0;

            //si no hay resultados->se hace un sugerir y se hace un OR

			String cadenaSugerida = null;
			String saltos = null;
			if (cerca_totes.length()>0)
				{
	            if ((hits.length() < MIN_HITS || hits.score(0) < MIN_SCORE) && sugerir) {
					Query quisoDecir = sugerir(cerca_totes, idi );				
					if (quisoDecir != null) 
					{
						cadenaSugerida = quisoDecir.toString(CAMPO_BUSQUEDAS).replace('+',' ');
						saltos = "1";
					}
				}
			}

        	endTime = System.currentTimeMillis();
        	res= new IndexResultados( extractHits(hits), hits.length(), (endTime - startTime), cerca_totes, cadenaSugerida, saltos );
            
            searcher.close();
            
        	return res;            
            
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        } catch (ParseException e) {
        	log.error(e.getMessage());
        	return null;
		}
    }
    
    
    
    private Query QuerySearchAdv(String idi, String cerca_totes, String cerca_alguna, String cerca_frase, String cerca_cap, String tipus, String uo, String mat, Date fini, Date ffin, String ajudes, boolean restringido) {
    	BooleanQuery querytotal = new BooleanQuery();
    	
        
            // Evitamos los documentos caducados o que no han sido publicados aun
    		// filtro de fechas o restringidos si no se trata de consulta interna.
            Term fechamin_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION,"00000000");
            Term fechamax_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION,"99999999");
            
            Term fechamax_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD,"99999999");

            if (fini!=null) {
            	String diaini=new java.text.SimpleDateFormat("yyyyMMdd").format(fini);
            	fechamin_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, diaini );
            }
            
            if (ffin!=null) {
            	String diafin=new java.text.SimpleDateFormat("yyyyMMdd").format(ffin);
            	fechamax_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, diafin );
            }
            
            if (!restringido) {
            	Query restringido_no = new PhraseQuery();
            	restringido_no = new TermQuery(new Term(Catalogo.DESCRIPTOR_RESTRINGIDO, "S"));
            	restringido_no.setBoost(2.0f);
    			querytotal.add(restringido_no, BooleanClause.Occur.MUST_NOT);
            }
            
            String hoy=new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
            //Term hoy_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, hoy );
            Term hoy_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD, hoy );

            //RangeQuery publica = new RangeQuery(fechamin_pub, hoy_pub , true);
            RangeQuery publica = new RangeQuery(fechamin_pub, fechamax_pub , true);
            
            RangeQuery caduca = new RangeQuery(hoy_cad, fechamax_cad , true);
            publica.setBoost(0.00001f); caduca.setBoost(0.00001f);
            
            querytotal.add(publica, BooleanClause.Occur.MUST);
            querytotal.add(caduca, BooleanClause.Occur.MUST);
            
            // filtro de tipo (clasificacion)
            /*
            if (tipus != null && tipus.length()>0 && tipus.equals("FORO")) {
            	Query query_wildcar = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_CONFORO, "S"));
   				query_wildcar.setBoost(2.0f);
    			querytotal.add(query_wildcar, BooleanClause.Occur.MUST);
    		} else {            
	            if (tipus != null && tipus.length()>0 && !tipus.equals("GEN")) {
	            	String ltTipus = "";
	            	if (tipus.equals("AJU")) ltTipus="PRC"; else ltTipus=tipus;
	            	Query query_wildcar = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_CLASIFICACION, ltTipus + "*"));
	   				query_wildcar.setBoost(2.0f);
	    			querytotal.add(query_wildcar, BooleanClause.Occur.MUST);
	    		}
    		}
    		*/
           
            if (tipus != null && tipus.length()>0 && !tipus.equals("GEN")) {
            	String ltTipus = "";
            	if (tipus.equals("AJU")) ltTipus="PRC"; else ltTipus=tipus;
            	if (tipus.equals("FORO")) ltTipus="FRM"; else ltTipus=tipus;
            	Query query_wildcar = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_CLASIFICACION, ltTipus + "*"));
   				query_wildcar.setBoost(2.0f);
    			querytotal.add(query_wildcar, BooleanClause.Occur.MUST);
    		}

            //OJO, IMPORTANTE: los comentarios de los foros NO DEBEN de salir
            /*
            Query query_wildcar_coment = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_CLASIFICACION, Catalogo.SRVC_FORO_TEMA_COMENTARIO + "*"));
            query_wildcar_coment.setBoost(2.0f);
			querytotal.add(query_wildcar_coment, BooleanClause.Occur.MUST_NOT);
            */
            
            // filtro de UO
            if (uo != null && uo.length() > 0) {
            	Query query_wildcar = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_UO, "*" + Catalogo.KEY_SEPARADOR + uo + Catalogo.KEY_SEPARADOR + "*"));
   				query_wildcar.setBoost(2.0f);
    			querytotal.add(query_wildcar, BooleanClause.Occur.MUST);
    		}
            
            // filtro de materia
            if (mat != null && mat.length() > 0) {
            	Query query_wildcar = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_MATERIA, "*" + Catalogo.KEY_SEPARADOR + mat + Catalogo.KEY_SEPARADOR + "*"));
   				query_wildcar.setBoost(2.0f);
    			querytotal.add(query_wildcar, BooleanClause.Occur.MUST);
    		}
            
            
            // filtro de ajudes
            if (ajudes != null && ajudes.length() > 0 && !ajudes.equals("on")) {
            	Query query_compuestaAND = new PhraseQuery();
   				query_compuestaAND = new TermQuery(new Term(Catalogo.DESCRIPTOR_FAMILIA, ajudes));
    			query_compuestaAND.setBoost(2.0f);
    			querytotal.add(query_compuestaAND, BooleanClause.Occur.MUST);
    		}
            
            //Todas las palabras.
            if (cerca_totes!=null && cerca_totes.length()>0) {            
	            
            	Vector words = null;
            	words = GetWords(cerca_totes, CAMPO_BUSQUEDAS, idi);            	
            	
            	if (!words.isEmpty()){
	            	//Phrasequery para campo Text
	            	Query query_compuesta_text = GetQuery(words, CAMPO_BUSQUEDAS, 200, false);
	    			query_compuesta_text.setBoost(2.0f);
	    			
	            	Query query_compuesta_textopcional = GetQuery(words, Catalogo.DESCRIPTOR_TEXTOPCIONAL, 200, false);
	            	query_compuesta_textopcional.setBoost(2.0f);
	    			    			
	    			BooleanQuery query_compuesta = new BooleanQuery();
	    			query_compuesta.add(new BooleanClause(query_compuesta_text, BooleanClause.Occur.SHOULD));
	    			query_compuesta.add(new BooleanClause(query_compuesta_textopcional, BooleanClause.Occur.SHOULD));
	    			querytotal.add(query_compuesta, BooleanClause.Occur.MUST);    			    			
            	}
            }
            
            //Alguna de las palabras.
            if (cerca_alguna!=null && cerca_alguna.length()>0) {            
	            
            	Vector words = null;
            	words = GetWords(cerca_alguna, CAMPO_BUSQUEDAS, idi);         
            	
            	if (!words.isEmpty()){           	
	            	//Phrasequery para campo Text
	            	Query query_compuesta_text = GetQuery(words, CAMPO_BUSQUEDAS, 200, true);
	    			query_compuesta_text.setBoost(2.0f);
	    			
	            	Query query_compuesta_textopcional = GetQuery(words, Catalogo.DESCRIPTOR_TEXTOPCIONAL, 200, true);
	            	query_compuesta_textopcional.setBoost(2.0f);
	    			    			
	    			BooleanQuery query_compuesta = new BooleanQuery();
	    			query_compuesta.add(new BooleanClause(query_compuesta_text, BooleanClause.Occur.SHOULD));
	    			query_compuesta.add(new BooleanClause(query_compuesta_textopcional, BooleanClause.Occur.SHOULD));
	    			querytotal.add(query_compuesta, BooleanClause.Occur.MUST);
            	}
            }

            //Frase exacta.
            if (cerca_frase!=null && cerca_frase.length()>0) {            
	            
            	Vector words = null;
            	words = GetWords(cerca_frase, CAMPO_BUSQUEDAS, idi);           	

            	if (!words.isEmpty()){            	
	            	//Phrasequery para campo Text
	            	Query query_compuesta_text = GetQuery(words, CAMPO_BUSQUEDAS, 0, false);
	    			query_compuesta_text.setBoost(2.0f);
	    			
	            	Query query_compuesta_textopcional = GetQuery(words, Catalogo.DESCRIPTOR_TEXTOPCIONAL, 0, false);
	            	query_compuesta_textopcional.setBoost(2.0f);
	    			    			
	    			BooleanQuery query_compuesta = new BooleanQuery();
	    			query_compuesta.add(new BooleanClause(query_compuesta_text, BooleanClause.Occur.SHOULD));
	    			query_compuesta.add(new BooleanClause(query_compuesta_textopcional, BooleanClause.Occur.SHOULD));
	    			querytotal.add(query_compuesta, BooleanClause.Occur.MUST);    			    			
            	}
            }
 
            //Palabras que no debe contener
            if (cerca_cap!=null && cerca_cap.length()>0) {            
	            
            	Vector words = null;
            	words = GetWords(cerca_cap, CAMPO_BUSQUEDAS, idi);            	
           	
            	if (!words.isEmpty()){            	
	            	//Phrasequery para campo Text
	            	Query query_compuesta_text = GetQuery(words, CAMPO_BUSQUEDAS, 200, false);
	    			query_compuesta_text.setBoost(2.0f);
	    			
	            	Query query_compuesta_textopcional = GetQuery(words, Catalogo.DESCRIPTOR_TEXTOPCIONAL, 200, false);
	            	query_compuesta_textopcional.setBoost(2.0f);
	    			    			
	    			querytotal.add(query_compuesta_text, BooleanClause.Occur.MUST_NOT);   
	    			querytotal.add(query_compuesta_textopcional, BooleanClause.Occur.MUST_NOT);
            	}
            }            
            
            log.debug(querytotal.toString());

    	
    	return querytotal;
    }
    
    private Query GetQuery(Vector words, String campo, int slop, boolean combinada)
    {
       	//Phrasequery para campo Text
    	Query query_text = null;
		if (words.size() > 1) {
			Query[] expandedQueries=new Query[words.size()];
			query_text = new PhraseQuery();
			((PhraseQuery)query_text).setSlop(slop);
			for (int i = 0; i < words.size(); i++) {				
				if (combinada) expandedQueries[i] = new TermQuery(new Term(campo, (String)words.elementAt(i)));
				else ((PhraseQuery)query_text).add(new Term(campo, (String)words.elementAt(i)));
			}
			if (combinada) query_text = query_text.combine(expandedQueries); //combinarlas entre si. Es equivalente a un OR de todo con todo    
		} else if (words.size() == 1) {
			query_text = new TermQuery(new Term(campo, (String)words.elementAt(0)));
		} 
		return query_text;
    }
    
    private Vector GetWords(String cadena, String campo, String idi)
    {
        Vector words = new Vector();            	
        TokenStream tokens = getAnalizador(idi).tokenStream(campo, new StringReader (cadena));
		Token token;
		while (true) {
			try {
				token = tokens.next();
			} catch (IOException e) {
				token = null;
			}
			if (token == null) break;
			words.addElement(token.termText());
		}
		try {
			tokens.close();
		} catch (IOException e) {} 
		
		return words;
    }
    
    private Query QuerySearchAdv2(String idi, String cerca_totes, String cerca_alguna, String cerca_frase, String cerca_cap, String tipus, String uo, String mat, Date fini, Date ffin, String ajudes) {
    	BooleanQuery querytotal = new BooleanQuery();
    	
        
            // Evitamos los documentos caducados o que no han sido publicados aun
    		// filtro de fechas
            Term fechamin_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION,"00000000");
            Term fechamax_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD,"99999999");

            if (fini!=null) {
            	String diaini=new java.text.SimpleDateFormat("yyyyMMdd").format(fini);
            	fechamin_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, diaini );
            }
            
            if (ffin!=null) {
            	String diafin=new java.text.SimpleDateFormat("yyyyMMdd").format(ffin);
            	fechamin_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, diafin );
            }
            
            String hoy=new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
            Term hoy_pub= new Term (Catalogo.DESCRIPTOR_PUBLICACION, hoy );
            Term hoy_cad= new Term (Catalogo.DESCRIPTOR_CADUCIDAD, hoy );

            RangeQuery publica = new RangeQuery(fechamin_pub, hoy_pub , true);
            RangeQuery caduca = new RangeQuery(hoy_cad, fechamax_cad , true);
            publica.setBoost(0.00001f); caduca.setBoost(0.00001f);
            
            querytotal.add(publica, BooleanClause.Occur.MUST);
            querytotal.add(caduca, BooleanClause.Occur.MUST);

            // filtro de tipo (clasificacion)
            if (tipus != null && tipus.length()>0 && !tipus.equals("GEN")) {
            	Query query_wildcar = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_CLASIFICACION, tipus + "*"));
   				query_wildcar.setBoost(2.0f);
    			querytotal.add(query_wildcar, BooleanClause.Occur.MUST);
    		}
            
            // filtro de UO
            if (uo != null && uo.length() > 0) {
            	Query query_wildcar = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_UO, "*" + Catalogo.KEY_SEPARADOR + uo + Catalogo.KEY_SEPARADOR + "*"));
   				query_wildcar.setBoost(2.0f);
    			querytotal.add(query_wildcar, BooleanClause.Occur.MUST);
    		}
            
            // filtro de materia
            if (mat != null && mat.length() > 0) {
            	Query query_wildcar = new WildcardQuery(new Term (Catalogo.DESCRIPTOR_MATERIA, "*" + Catalogo.KEY_SEPARADOR + mat + Catalogo.KEY_SEPARADOR + "*"));
   				query_wildcar.setBoost(2.0f);
    			querytotal.add(query_wildcar, BooleanClause.Occur.MUST);
    		}
            
            
            // filtro de ajudes
            if (ajudes != null && ajudes.length() > 0) {
            	Query query_compuestaAND = new PhraseQuery();
   				query_compuestaAND = new TermQuery(new Term(Catalogo.DESCRIPTOR_FAMILIA, ajudes));
    			query_compuestaAND.setBoost(2.0f);
    			querytotal.add(query_compuestaAND, BooleanClause.Occur.MUST);
    		}
            
            
            //Sacamos las palabras pasando el analizador (con su extractor correspondiente)
            Vector words = new Vector();            	
            TokenStream tokens = getAnalizador(idi).tokenStream(CAMPO_BUSQUEDAS, new StringReader (cerca_totes));
			Token token;
			while (true) {
				try {
					token = tokens.next();
				} catch (IOException e) {
					token = null;
				}
				if (token == null) break;
				words.addElement(token.termText());
			}
			try {
				tokens.close();
			} catch (IOException e) {}                  

			
            if (cerca_totes!=null && cerca_totes.length()>0) {
            	
            	//Phrasequery para campo Text
            	Query query_compuesta_text = null;
    			if (words.size() > 1) {
    				Query[] expandedQueries=new Query[words.size()];
    				query_compuesta_text = new PhraseQuery();
    				((PhraseQuery)query_compuesta_text).setSlop(15);
    				for (int i = 0; i < words.size(); i++) {
    					((PhraseQuery)query_compuesta_text).add(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(i)));
    					expandedQueries[i] = new TermQuery(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(i)));
    				}
    				query_compuesta_text = query_compuesta_text.combine(expandedQueries); //combinarlas entre si. Es equivalente a un OR de todo con todo    				
    				
    			} else if (words.size() == 1) {
    				query_compuesta_text = new TermQuery(new Term(CAMPO_BUSQUEDAS, (String)words.elementAt(0)));
    			} 
    			query_compuesta_text.setBoost(2.0f);

    			
            	//Phrasequery para campo Textopcional
    			Query query_compuesta_textopcional = null;
    			if (words.size() > 1) {
    				Query[] expandedQueries=new Query[words.size()];
    				query_compuesta_textopcional = new PhraseQuery();
    				((PhraseQuery)query_compuesta_textopcional).setSlop(15);
    				for (int i = 0; i < words.size(); i++) {
    					((PhraseQuery)query_compuesta_textopcional).add(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(i)));
    					expandedQueries[i] = new TermQuery(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(i)));
    				}
    				query_compuesta_textopcional = query_compuesta_textopcional.combine(expandedQueries); //combinarlas entre si. Es equivalente a un OR de todo con todo    				
    				
    			} else if (words.size() == 1) {
    				query_compuesta_textopcional = new TermQuery(new Term(Catalogo.DESCRIPTOR_TEXTOPCIONAL, (String)words.elementAt(0)));
    			} 
    			query_compuesta_textopcional.setBoost(1.9f);
       			
    			BooleanQuery query_compuestaOR = new BooleanQuery();
    			query_compuestaOR.add(new BooleanClause(query_compuesta_text, BooleanClause.Occur.SHOULD));
    			query_compuestaOR.add(new BooleanClause(query_compuesta_textopcional, BooleanClause.Occur.SHOULD));
    			querytotal.add(query_compuestaOR, BooleanClause.Occur.MUST);    			        			
    			
            }
         
            log.debug(querytotal.toString());

    	
    	return querytotal;
    }    
    
    /*****************************************************************************/
    /*****************      METODOS  PRIVADOS                  *******************/
    /*****************************************************************************/
    
    private Directory getHibernateDirectory(String idi) throws IOException {
        Directory directory;
        if (FILESYSTEM_INDEX_TYPE.equals(indexType)) {
        	directory = FSDirectory.getDirectory(new File(System.getProperty(indexLocation)+"/"+idi));
        } else if (HIBERNATE_INDEX_TYPE.equals(indexType)) {
            directory = new HibernateDirectory(sessionFactory);
        } else {
            throw new EJBException("Tipo de índice no valido: " + indexType);
        }
        if (!IndexReader.indexExists(directory)) {
            new IndexWriter(directory, analyzer, true).close();
        }
        return directory;
    }

    
    private synchronized void operarConWriter(String operacion, IndexObject indexObject, String idioma) {
    	try {
			Directory directory = getHibernateDirectory(idioma);
			IndexWriter writer = new IndexWriter(directory, getAnalizador(idioma), false);
			writer.setMergeFactor(25);
			writer.setMaxMergeDocs(625);
			
			//añade un documento
    		if (operacion.equals(OPER_ADDDOCUMENT)) {
                Document document = getDocument(indexObject);
                writer.addDocument(document);
    		}
    		
    		//crea/actualiza el diccionario
    		if (operacion.equals(OPER_DICCIONARIO)) {
    			IndexReader indexReader = IndexReader.open(getHibernateDirectory(idioma));
    			Dictionary dictionary = new LuceneDictionary(indexReader, CAMPO_BUSQUEDAS);
                SpellChecker spellChecker = new SpellChecker(getHibernateDirectory(idioma+"/dicc"));
                spellChecker.indexDictionary(dictionary);
                if (indexReader != null) indexReader.close();
    		}
    		
    		//optimiza el indice
    		if (operacion.equals(OPER_OPTIMIZAR)) {
    			writer.optimize();
    		}
    		
			writer.close();
    	} catch (IOException e) {
    		log.error("operarConWriter [" + operacion + "] de microsites: " + e.getMessage(),e);
    	}
    }    
    
    private Document getDocument(IndexObject indexObject) {
        Document document = new Document();
        
        document.setBoost(indexObject.getBoost());
        
        /* descriptores identificadores */
        if (indexObject.getId()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_ID, indexObject.getId(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getClasificacion()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_CLASIFICACION, indexObject.getClasificacion(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        
        /* descriptores filtro */
        if (indexObject.getForo()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_FORO, indexObject.getForo().toString(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getMicro()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_MICRO, indexObject.getMicro().toString(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getRestringido()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_RESTRINGIDO, indexObject.getRestringido(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getFamilia()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_FAMILIA, indexObject.getFamilia().toString(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getSeccion()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_SECCION, indexObject.getSeccion(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getMateria()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_MATERIA, indexObject.getMateria(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getUo()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_UO, indexObject.getUo(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        
        if (indexObject.getConforo()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_CONFORO, indexObject.getConforo(), Field.Store.YES, Field.Index.UN_TOKENIZED));        
        
        /* descriptores informacion del documento */
        if (indexObject.getTitulo()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_TITULO, indexObject.getTitulo(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getTituloserviciomain()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_TITULO_SRV_MAIN, indexObject.getTituloserviciomain(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getText()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_TEXT, indexObject.getText(), Field.Store.NO, Field.Index.TOKENIZED));
        if (indexObject.getTextopcional()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_TEXTOPCIONAL, indexObject.getTextopcional(), Field.Store.NO, Field.Index.TOKENIZED));
        if (indexObject.getUrl()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_URL, indexObject.getUrl(), Field.Store.YES, Field.Index.NO));
        if (!indexObject.getPublicacion().equals(""))	document.add(new Field(Catalogo.DESCRIPTOR_PUBLICACION, indexObject.getPublicacion(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        else 							        		document.add(new Field(Catalogo.DESCRIPTOR_PUBLICACION, "00000000", Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (!indexObject.getCaducidad().equals(""))	   	document.add(new Field(Catalogo.DESCRIPTOR_CADUCIDAD, indexObject.getCaducidad(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        else 								     	  	document.add(new Field(Catalogo.DESCRIPTOR_CADUCIDAD, "99999999", Field.Store.YES, Field.Index.UN_TOKENIZED));
        if (indexObject.getDescripcion()!=null)	document.add(new Field(Catalogo.DESCRIPTOR_DESCRIPCION, indexObject.getDescripcion(), Field.Store.YES, Field.Index.UN_TOKENIZED));        
        
        return document;
    }

	private Analyzer getAnalizador(String idi) {

		if (idi.toLowerCase().equals("de"))      	analyzer = new AlemanAnalyzer();
        else if (idi.toLowerCase().equals("en")) 	analyzer = new InglesAnalyzer();
        else if (idi.toLowerCase().equals("ca")) 	analyzer = new CatalanAnalyzer();
		else		            	analyzer = new CastellanoAnalyzer();

		return analyzer;
	}
	
	private List extractHits(Hits hits) throws IOException {
	    List hitList = new ArrayList();
	    for (int i = 0, count = 0; i < hits.length() && count++ < MAX_HITS; i++) {
	    	Document doc=hits.doc(i);
            hitList.add(new IndexEncontrado(doc.get(Catalogo.DESCRIPTOR_ID), 
            								doc.get(Catalogo.DESCRIPTOR_CLASIFICACION),
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
            								new Float(100*hits.score(i)).intValue()));
        }
	    return hitList;
	}	
	
	
	private Query sugerir(String queryString, String idi) throws ParseException {
	
		try {
			QuerySuggester querySuggester = new QuerySuggester(CAMPO_BUSQUEDAS, getAnalizador(idi), getHibernateDirectory(idi+"/dicc"), idi);
			querySuggester.setDefaultOperator(QueryParser.AND_OPERATOR);
			Query query = querySuggester.parse((queryString.length()>0)?queryString:" ");
			
			return querySuggester.hasSuggestedQuery() ? query : null;

		} catch (IOException e) {
			throw new ParseException(e.getMessage());
		}

	}
    
    
    
	/* Clase que sobreescribe un método de QueryParser para permitir hacer sugerencias 
	 * cuando las búsquedas sean compuestas
	 * 
	 */ 
	
	public class QuerySuggester extends QueryParser {
		private boolean suggestedQuery = false;
		private Directory spellIndexDirectory;
		private String idioma;
		public QuerySuggester(String field, Analyzer analyzer, Directory dir, String idi) {
			super(field, analyzer);
			spellIndexDirectory=dir;
			idioma=idi;
		}
		protected Query getFieldQuery(String field, String queryText) throws ParseException {
			// Copied from org.apache.lucene.queryParser.QueryParser
			// replacing construction of TermQuery with call to getTermQuery()
			// which finds close matches.
		    TokenStream source = getAnalyzer().tokenStream(field, new StringReader(queryText));
			Vector v = new Vector();
			Token t;

			while (true) {
				try {	t = source.next();	} 
				catch (IOException e) {	t = null;}
				if (t == null)	break;
				v.addElement(t.termText());
			}
			try {	source.close();	} 
			catch (IOException e) {	// ignore 
			}

			if (v.size() == 0) {
				return null;
			}
			else if (v.size() == 1)	{
				return new TermQuery(getTerm(field, (String) v.elementAt(0)));
			}
			else {
				PhraseQuery q = new PhraseQuery();
				q.setSlop(getPhraseSlop());
				for (int i = 0; i < v.size(); i++) {
					q.add(getTerm(field, (String) v.elementAt(i)));
				}
				return q;
			}
		}
		private Term getTerm(String field, String queryText) throws ParseException {
			
			try {
				SpellChecker spellChecker = new SpellChecker(spellIndexDirectory);
				if (spellChecker.exist(queryText)) 	return new Term(field, queryText);

				String[] similarWords = spellChecker.suggestSimilar(queryText, 1, IndexReader.open(getHibernateDirectory(idioma)), CAMPO_BUSQUEDAS, true);
				
				if (similarWords.length == 0) 	return new Term(field, queryText);
				suggestedQuery = true;
				return new Term(field, similarWords[0]);
			} catch (IOException e) {
				throw new ParseException(e.getMessage());
			}
		}		
		
		public boolean hasSuggestedQuery() {
			return suggestedQuery;
		}
		
	}    
}
