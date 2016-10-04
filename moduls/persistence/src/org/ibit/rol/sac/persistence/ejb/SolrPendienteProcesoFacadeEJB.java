package org.ibit.rol.sac.persistence.ejb;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteJobDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import es.caib.solr.api.SolrFactory;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.exception.ExcepcionSolrApi;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;


/**
 * SessionBean para consultar idiomas.
 *
 * @ejb.bean
 *  name="sac/persistence/SolrPendienteProcesoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SolrPendienteProcesoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="NotSupported"
 */
public abstract class SolrPendienteProcesoFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}
	

	 /**
     * Obtiene los parámetros de configuración del indexer
     * @return SolrIndexer
     * 
     */
	private SolrIndexer obtenerParamIndexer() {
		String url = System.getProperty("es.caib.rolsac.solr.url");
		String index = System.getProperty("es.caib.rolsac.solr.index");
		String user = System.getProperty("es.caib.rolsac.solr.user");
		String pass = System.getProperty("es.caib.rolsac.solr.pass");
		SolrIndexer solrIndexer = SolrFactory.getIndexer(url, index, EnumAplicacionId.ROLSAC, user, pass);
		return solrIndexer;
	}

   
	
	/**
	 * Indexa todas las fichas 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @param solrPendienteJob
	 * @throws DelegateException
	 * @throws ExcepcionSolrApi
	 */
	public void indexarTodoFicha(final SolrPendienteJob solrPendienteJob)  {
		log.debug("INDEXAR TODO FICHA");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_FICHA);
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_FICHA_DOCUMENTO);
		} catch (Exception e) {
			log.debug("Error en indexarTodoFicha cuando se desindexa", e);
			return;
		}
		
		final FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
		final DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
		
		List<Long> listFicha = null;
		try {
			listFicha = fichaDelegate.buscarIdsFichas();
		} catch (Exception e) {
			log.debug("Error en indexarTodoFicha cuando se busca las fichas ids", e);
			return ;
		}
		
    	final int totalFichas = listFicha.size();
    	log.debug("Numero de fichas a revisar para indexar: " + totalFichas);
    	
		int iFicha = 0;
    	for (Long idFicha : listFicha) {
    		iFicha++;
    		
    		solrPendienteJob.setTotalFicha( Float.valueOf( iFicha * 100/ totalFichas ));
    		solrPendienteJob.setTotalFichaDoc(Float.valueOf( iFicha * 100/ totalFichas ));
    		try {
    			solrDelegate.indexarPendiente(solrIndexer, fichaDelegate, idFicha, EnumCategoria.ROLSAC_FICHA, solrPendienteJob);
        		
    			//Obtiene Documento ficha
        		List<Long> idDocumentos = docuDelegate.obtenerDocumentosFichaSolr(idFicha);
        		for (Long idDocumento : idDocumentos) {
        			try{
        				solrDelegate.indexarPendiente(solrIndexer, docuDelegate, idDocumento, EnumCategoria.ROLSAC_FICHA_DOCUMENTO, solrPendienteJob);	        				
        			} catch (Exception e) {
    					log.error("Se ha producido un error en documento ficha con id " + idDocumento, e);
    				}
				}
			} catch (Exception e) {
				log.error("Se ha producido un error en la ficha con id " + idFicha, e);
			}
		}
    	
    	log.debug("Se han terminado de revisar las fichas para indexar.");
    	
    	solrPendienteJob.setTotalFicha(100f);
    	solrPendienteJob.setTotalFichaDoc(100f);
    	solrPendienteJob.setFechaFicha(new Date());
    	
    	
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);						
		} catch (Exception e) {
			log.debug("Error en indexarTodoFicha cuando se actualiza el job", e);
		}
    	
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo fichas", e);				
		}
	}
	
	
	/**
	* Indexa todas los procedimientos. 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public void indexarTodoProcedimiento(final SolrPendienteJob solrPendienteJob)  {
		log.debug("INDEXAR TODO PROCEDIMIENTO");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_PROCEDIMIENTO);
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO);
		} catch (Exception e) {
			log.debug("Error en indexarTodoProcedimiento cuando se desindexa", e);
			return;
		}
		
		//Obtiene los procedimientos
    	final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
    	final DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
    	List<Long> listProc = null;
		try {
			listProc = procDelegate.buscarIdsProcedimientos();
		} catch (DelegateException e) {
			log.debug("Error en indexarTodoProcedimiento cuando se busca id procedimiento", e);
			return;
		}
		 
		//Recorremos la lista
    	final int totalProc = listProc.size();
    	
    	log.debug("Numero de procedimientos a revisar para indexar: " + totalProc);
    	
    	int iDoc = 0;
    	for (Long idProc : listProc) {
    		iDoc++;
			solrPendienteJob.setTotalProcedimiento( Float.valueOf( iDoc * 100 / totalProc ));
    		solrPendienteJob.setTotalProcedimientoDoc(Float.valueOf( iDoc * 100 / totalProc ));
    		try {
    			solrDelegate.indexarPendiente(solrIndexer, procDelegate, idProc, EnumCategoria.ROLSAC_PROCEDIMIENTO, solrPendienteJob);
        		
    			List<Long> idDocumentos = docuDelegate.obtenerDocumentosProcedimientoSolr(idProc);
        		for (Long idDocumento : idDocumentos) {
        			try{
        				solrDelegate.indexarPendiente(solrIndexer, docuDelegate, idDocumento, EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO, solrPendienteJob);	        				
	        		} catch (Exception e) {
    					log.error("Se ha producido un error en procedimiento " + idProc + " para documento  con id " + idDocumento, e);
    				}
				}
			} catch (Exception e) {
				log.error("Se ha producido un error en el procedimiento con id " + idProc, e);
			}
    		
		}

    	log.debug("Se han terminado de revisar los procedimientos para indexar.");
    	
    	solrPendienteJob.setTotalProcedimiento(100f);
    	solrPendienteJob.setTotalProcedimientoDoc(100f);
    	solrPendienteJob.setFechaProcedimiento(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoProcedimiento cuando se actualiza el job", e);
		}
    	
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo procedimiento", e);				
		}
	}
	
	
	/**
	 * Indexa todas las normativas. 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 
   	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public void indexarTodoNormativa(final SolrPendienteJob solrPendienteJob) {
		log.debug("INDEXAR TODO NORMATIVA");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_NORMATIVA);
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO);
		} catch (Exception e) {
			log.debug("Error en indexarTodoNormativa cuando se desindexa", e);
			return;
		}
		
		final NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
    	//Obtiene las normativas
    	List<Long> listNorm = null;
		try {
			listNorm = normDelegate.buscarIdsNormativas();
		} catch (DelegateException e) {
			log.debug("Error en indexarTodoNormativa cuando se busca las ids de normativa", e);
			return;
		}
		
		//Recorremos la lista
    	final int totalNorm = listNorm.size();
    	int iNor = 0;
    	for (Long idNorm : listNorm) {
    		iNor++;
    		solrPendienteJob.setTotalNormativa( Float.valueOf( iNor * 100/ totalNorm ));
    		solrPendienteJob.setTotalNormativaDoc(Float.valueOf( iNor * 100/ totalNorm ));
    		
    		Normativa normativa;
			try {
				solrDelegate.indexarPendiente(solrIndexer, normDelegate, idNorm, EnumCategoria.ROLSAC_NORMATIVA, solrPendienteJob);
        		
				//Obtiene documentos normativa
				normativa=normDelegate.obtenerNormativa(idNorm);
        		Iterator<Entry<String, Traduccion>> itTradNorm = normativa.getTraduccionMap().entrySet().iterator();
        		while (itTradNorm.hasNext()) {
        			Map.Entry mapTrad = (Map.Entry)itTradNorm.next();
        			TraduccionNormativa tradNorm=(TraduccionNormativa) mapTrad.getValue();
        			
        			Archivo arc = tradNorm != null && tradNorm.getArchivo() != null ? tradNorm.getArchivo() : null;
            		if (arc != null){
            			try{	
            				solrDelegate.indexarPendiente(solrIndexer, normDelegate, arc.getId(), EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO, solrPendienteJob);
            			} catch (Exception e) {
        					log.error("Se ha producido un error en normativa con id " + idNorm + " para archivo con id " + arc.getId(), e);
        				}
            		}
        		}
			} catch (Exception e) {
				log.error("Se ha producido un error en la normativa con id " + idNorm, e);
			} 	
    	}
    	
    	log.debug("Se han terminado de revisar las normativas para indexar.");
    	
    	solrPendienteJob.setTotalNormativa(100f);
    	solrPendienteJob.setFechaNormativa(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoNormativa cuando se actualiza el job", e);
		}
    	
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo normativa", e);				
		}
	}
	
	/***
	  * Indexa todas los trámites. . 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @param solrPendienteJob
	 * @throws DelegateException
	 * @throws ExcepcionSolrApi
	 */
	public  void indexarTodoTramite(final SolrPendienteJob solrPendienteJob)  {
		log.debug("INDEXAR TODO TRAMITE");
		SolrIndexer solrIndexer  = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_TRAMITE);
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_TRAMITE_DOCUMENTO);
		} catch (Exception e) {
			log.debug("Error en indexarTodoTramite cuando se desindexa", e);
			return;
		}
		
		final TramiteDelegate tramDelegate = DelegateUtil.getTramiteDelegate();
		final DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
    	
		//Obtiene los trámites
    	List<Long> listTram = null; 
		try {
			listTram = tramDelegate.buscarIdsTramites();
		} catch (DelegateException e) {
			log.debug("Error en indexarTodoTramite cuando se busca los ids de tramites", e);
			return;
		}
		
		//Recorremos la lista
    	final int totalTram = listTram.size();
    	
    	log.debug("Numero de tramites a revisar para indexar: " + totalTram);
    	
    	int iTram = 0;
    	for (Long idTramite : listTram) {
    		iTram++;
    		solrPendienteJob.setTotalTramite( Float.valueOf( iTram * 100/ totalTram ));
    		try {
    			solrDelegate.indexarPendiente(solrIndexer, tramDelegate, idTramite, EnumCategoria.ROLSAC_TRAMITE, solrPendienteJob);
    			List<Long> idDocumentos = docuDelegate.obtenerDocumentosTramiteSolr(idTramite);
        		for (Long idDocumento : idDocumentos) {
        			try{
        				solrDelegate.indexarPendiente(solrIndexer, tramDelegate, idDocumento, EnumCategoria.ROLSAC_TRAMITE_DOCUMENTO, solrPendienteJob);	        				
	        		} catch (Exception e) {
    					log.error("Se ha producido un error en tramite con id " + iTram + " para documento con id " + idDocumento, e);
    				}
				}
			} catch (Exception e) {
				log.error("Se ha producido un error en el trámite con id " + idTramite, e);
			}
		}
    	
    	
    	log.debug("Se han terminado de revisar los tramites para indexar.");
    	
    	solrPendienteJob.setTotalTramite(100f);
    	solrPendienteJob.setFechaTramite(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoTramite cuando se actualiza el job", e);
		}
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo tramite", e);				
		}
	}
	
	/**
	 * Indexa todas los UA.
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 
   	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi 
	 * @throws DelegateException 
	 */
	public void indexarTodoUA(final SolrPendienteJob solrPendienteJob)  {
		log.debug("INDEXAR TODO UA");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA);
		} catch (Exception e) {
			log.debug("Error en indexarTodoUA cuando se desindexa", e);
			return;
		}
		
		UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
    	SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
    	//Obtiene las UA´s
    	List<Long> listUas = null;
    	
		try { 
			listUas = uaDelegate.buscarIdsUas();
		} catch (DelegateException e) {
			log.debug("Error en indexarTodoUA cuando se buscan las ids", e);
			return;
		}
		
		//Recorremos la lista
    	final int totalUA = listUas.size();
    	log.debug("Numero de UAs a revisar para indexar: " + totalUA);
    	int iUA = 0;
    	for (Long idUa : listUas) {
    		iUA++;
    		solrPendienteJob.setTotalUnidadAdministrativa( Float.valueOf( iUA * 100 / totalUA ));
    		try {
    			solrDelegate.indexarPendiente(solrIndexer, uaDelegate, idUa, EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA, solrPendienteJob);    					
			} catch (Exception e) {
				log.error("Se ha producido un error en la unidad administrativa con id " + idUa, e);
			}
		}
    	
    	log.debug("Se han terminado de revisar las UAs para indexar.");
    	
    	solrPendienteJob.setTotalUnidadAdministrativa(100f);
    	solrPendienteJob.setFechaUnidadAdministrativa(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoUA cuando se actualiza el job", e);
		}
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo ua", e);				
		}
	}
   
    
	/**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .
     * @throws DelegateException 
   	 */
    public Boolean indexarPendientes() throws DelegateException {
    	 //org.apache.commons.logging.Log.class
    	Session session = null;
    	SolrIndexer solrIndexer = null;
        try {
        	
        	solrIndexer = obtenerParamIndexer();
        	final SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
        	List<SolrPendiente> solrPendientes = solrPendiente.getPendientes();
        	session = getSession();
        	SolrPendienteJobDelegate solrPendienteUnitario = DelegateUtil.getSolrPendienteJobDelegate();
			int i = 0;
        	for(SolrPendiente solrpendiente : solrPendientes) {
        			if (i % 20 == 0) {
        				session.flush();
        				try {
        	            	if (solrIndexer != null) {
        	            		solrIndexer.commit();
        	            	}
        				} catch (ExcepcionSolrApi e) {
        					log.error("Ha dado un error intentando comitear en pendientes con la iteracion i " + i, e);				
        				}
        			} 
        			i++;        			
        			final SolrPendienteResultado solrPendienteResultado = solrPendienteUnitario.indexarPendiente(solrIndexer, solrpendiente);  
        			solrPendienteUnitario.resolverPendiente(solrpendiente, solrPendienteResultado);
        	}        	
        	session.flush();
        	return true;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            if (session != null) {close(session);}
            try {
            	if (solrIndexer != null) {
            		solrIndexer.commit();
            	}
			} catch (ExcepcionSolrApi e) {
				log.error("Ha dado un error intentando comitear en pendientes", e);				
			}
        }
    }
  
    
    

    
}
