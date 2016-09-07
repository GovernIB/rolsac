package org.ibit.rol.sac.persistence.ejb;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;


/**
 * SessionBean para ejecutar los solr pendientes de manera unitaria.
 *
 * @ejb.bean
 *  name="sac/persistence/SolrPendienteJobFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SolrPendienteJobFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="RequiresNew"
 */
public abstract class SolrPendienteJobFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}


	/**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @ejb.transaction type="RequiresNew"
   	 *
   	 * Actualiza el job si está activo
     * @param actualizarJob
     * @param sorlPendienteJob
     */
    public void actualizarJob(SolrPendienteJob sorlPendienteJob) {
    	try
    	{
			final Session session = getSession();
	    	session.update(sorlPendienteJob); 
			session.flush();
			session.close();
    	} catch(Exception exception) {
    		throw new EJBException(exception);
    	}
	}
	
	
	
    

	/**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @ejb.transaction type="RequiresNew"
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .
   	 */
    public void indexarPendiente(final SolrIndexer solrIndexer, final FichaDelegate fichaDelegate, final Long idElemento, final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob)  {
    	try
    	{
    		fichaDelegate.indexarSolr(solrIndexer, idElemento, categoria);
    		if (sorlPendienteJob.isTotalFichaActualizable()) {
    			actualizarJob(sorlPendienteJob);
    		}
    	} 
    	catch (Exception he) {
	            log.error("Error intentando indexar Ficha idElemento" + idElemento,he);
        }
    }
    
    
    /**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 	@ejb.transaction type="RequiresNew"
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .
   	 */
    public void indexarPendiente(final SolrIndexer solrIndexer, final ProcedimientoDelegate delegate, final Long idElemento, final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob)  {
    	try
    	{
    		delegate.indexarSolr(solrIndexer, idElemento, categoria);
    		if (sorlPendienteJob.isTotalProcedimientoActualizable()) {
    			actualizarJob(sorlPendienteJob);
    		}
    	} 
    	catch (Exception he) {
	            log.error("Error intentando indexar procedimiento idElemento" + idElemento,he);
        }
    }
    
    

	/**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @ejb.transaction type="RequiresNew"
   	 *  
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .
   	 */
    public void indexarPendiente(final SolrIndexer solrIndexer, DocumentoDelegate docuDelegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) {
    	
    	try
    	{
    		if (categoria == EnumCategoria.ROLSAC_FICHA_DOCUMENTO) {
    			docuDelegate.indexarSolrFichaDoc(solrIndexer,idElemento, categoria);
    			if (sorlPendienteJob.isTotalFichaDocActualizable()) {
        			actualizarJob(sorlPendienteJob);
        		}
    		} else if (categoria == EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO) {
    			docuDelegate.indexarSolrProcedimientoDoc(solrIndexer,idElemento, categoria);
    			if (sorlPendienteJob.isTotalProcedimientoDocActualizable()) {
        			actualizarJob(sorlPendienteJob);
        		}
    		}
    	} 
    	catch (Exception he) {
	            log.error("Error intentando indexar documento idElemento" + idElemento,he);
        }
    }
   

    
    /**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
     * @ejb.transaction type="RequiresNew"
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .
   	 */
    public void indexarPendiente(final SolrIndexer solrIndexer, NormativaDelegate normativaDelegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob)  {
    	
    	try
    	{
    		if (categoria == EnumCategoria.ROLSAC_NORMATIVA) {
    			normativaDelegate.indexarSolrNormativa(solrIndexer,idElemento, categoria);
    			if (sorlPendienteJob.isTotalNormativaActualizable()) {
        			actualizarJob(sorlPendienteJob);
        		}
    		} else if (categoria == EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO) {
    			normativaDelegate.indexarSolrNormativaDocumento(solrIndexer,idElemento, categoria);
    			if (sorlPendienteJob.isTotalNormativaDocActualizable()) {
        			actualizarJob(sorlPendienteJob);
        		}
    		} 
    	} 
    	catch (Exception he) {
	            log.error("Error intentando indexar normativa idElemento" + idElemento,he);
        }
    }
    
    
    	   
    
    /**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
     * @ejb.transaction type="RequiresNew"
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .
   	 */
    public void indexarPendiente(final SolrIndexer solrIndexer, TramiteDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob)  {
    	
    	try
    	{
    		delegate.indexarSolr(solrIndexer,idElemento, categoria);
			if (sorlPendienteJob.isTotalTramiteActualizable()) {
    			actualizarJob(sorlPendienteJob);
			}
    	} 
    	catch (Exception he) {
	            log.error("Error intentando indexar tramite idElemento" + idElemento,he);
        }
    }
    
    
    /**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @ejb.transaction type="RequiresNew"
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .
   	 */
    public void indexarPendiente(final SolrIndexer solrIndexer, UnidadAdministrativaDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) {
    	
    	try
    	{
    		delegate.indexarSolr(solrIndexer,idElemento, categoria);
			if (sorlPendienteJob.isTotalUAActualizable()) {
    			actualizarJob(sorlPendienteJob);
			}
    	} 
    	catch (Exception he) {
	            log.error("Error intentando indexar ua idElemento" + idElemento,he);
        }
    }
    
    
	/**
	 * Indexar pendiente. 
	 * @return 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
	 * @ejb.transaction type="RequiresNew" 
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .
     * @throws DelegateException 
     * */
    public SolrPendienteResultado indexarPendiente(final SolrIndexer solrIndexer, final SolrPendiente solrpendiente)  {
    	
    	SolrPendienteResultado solrPendienteResultado = null;
    	try {
    		FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
        	ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
        	NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();
        	TramiteDelegate tramDelegate = DelegateUtil.getTramiteDelegate();
        	UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
        	DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
        	
        			final EnumCategoria enumCategoria = EnumCategoria.fromString(solrpendiente.getTipo());
        			switch (enumCategoria) {
	        			case ROLSAC_FICHA:
	        				if (solrpendiente.getAccion() == 1) {
								solrPendienteResultado= fichaDelegate.indexarSolr(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == 2) {
								solrPendienteResultado= fichaDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
	        				break;
	        			case ROLSAC_PROCEDIMIENTO:
							if (solrpendiente.getAccion() == 1) {
								solrPendienteResultado= procDelegate.indexarSolr(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == 2) {
								solrPendienteResultado= procDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
	        				break;
	        			case ROLSAC_NORMATIVA:
	        			case ROLSAC_NORMATIVA_DOCUMENTO:
							if (solrpendiente.getAccion() == 1) { 
								solrPendienteResultado= normDelegate.indexarSolr(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == 2) {
								solrPendienteResultado= normDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
	        				break;
	        			case ROLSAC_TRAMITE:
	        			case ROLSAC_TRAMITE_DOCUMENTO:
							if (solrpendiente.getAccion() == 1) {
								solrPendienteResultado= tramDelegate.indexarSolr(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == 2) {
								solrPendienteResultado= tramDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
	        				break;
						case ROLSAC_UNIDAD_ADMINISTRATIVA: 
							if (solrpendiente.getAccion() == 1) {
								solrPendienteResultado= uaDelegate.indexarSolr(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == 2) {
								solrPendienteResultado= uaDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
							break;
						case ROLSAC_FICHA_DOCUMENTO: 
						case ROLSAC_PROCEDIMIENTO_DOCUMENTO: 					
							if (solrpendiente.getAccion() == 1) {
								solrPendienteResultado = docuDelegate.indexarSolr(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == 2) {
								solrPendienteResultado = docuDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
							break;
						default:
							break;
        			}
    	}
    	catch (Exception e) {
	            log.error("Error intentando indexar " + solrpendiente,e);
        } 
    	
    	return solrPendienteResultado;
        
    }
    
    
    /**
     * Método que se encarga de realizar las acciones segun si ha sido correcto o no.
     * @param solrpendiente
     * @param session
     * @param solrPendienteResultado
     * @throws HibernateException
     * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
	 * @ejb.transaction type="RequiresNew" 
   	 */
    public void resolverPendiente(final SolrPendiente solrpendiente, final SolrPendienteResultado solrPendienteResultado)  {
    	try {
	    	 Session session = getSession();
	    	 if (solrPendienteResultado != null) {
	    		if (solrPendienteResultado.isCorrecto()) {
					solrpendiente.setResultado(1);
					solrpendiente.setMensajeError(solrPendienteResultado.getMensaje());
					solrpendiente.setFechaIndexacion(new Date());
					session.update(solrpendiente);
					session.flush();
				} else {
					final Calendar fechaCalendar  = Calendar.getInstance();
					fechaCalendar.setTime(solrpendiente.getFechaCreacion());
					final Calendar hoyCalendar  = Calendar.getInstance();
					hoyCalendar.setTime(new Date());
					
					final int dias = hoyCalendar.get(Calendar.DATE) - fechaCalendar.get(Calendar.DATE);
					//Si hace 10 dias o + que se crea se marca como erronea porque no se ha podido endexar
					if ( dias >= 10){
						solrpendiente.setResultado(-1);
						solrpendiente.setMensajeError(solrPendienteResultado.getMensaje());
						solrpendiente.setFechaIndexacion(new Date());
						session.update(solrpendiente);
						session.flush();
					}else{
						log.error("No se ha podido realizar la operación (dias ejecutandose:"+dias+")con el registro : "+solrpendiente.getId());
					}
				}
	    	}
	    	session.close(); 
    	} catch(Exception e) {
    		log.error("Error resolver pendiente", e);
    	}
	}



	    
}
