package org.ibit.rol.sac.persistence.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;
import es.caib.solr.api.model.types.EnumIdiomas;


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
    		if (categoria == EnumCategoria.ROLSAC_TRAMITE) {
    			delegate.indexarSolr(solrIndexer,idElemento, categoria);
    		} else if (categoria == EnumCategoria.ROLSAC_TRAMITE_DOCUMENTO) {
    			delegate.indexarDocSolr(solrIndexer, idElemento, categoria);
    		}  
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
        	UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
        	
        	final EnumCategoria enumCategoria = EnumCategoria.fromString(solrpendiente.getTipo());
        	switch (enumCategoria) {
	        			case ROLSAC_FICHA:
	        				if (solrpendiente.getAccion() == SolrPendienteDelegate.REINDEXAR) {
	        					fichaDelegate.desindexarSolr(solrIndexer, solrpendiente);
	        					solrPendienteResultado = indexarPendienteFicha(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == SolrPendienteDelegate.DESINDEXAR) {
								solrPendienteResultado = fichaDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
	        				break;
	        			case ROLSAC_PROCEDIMIENTO:
							if (solrpendiente.getAccion() == SolrPendienteDelegate.REINDEXAR) {
								procDelegate.desindexarSolr(solrIndexer, solrpendiente);
								solrPendienteResultado = indexarPendienteProcedimiento(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == SolrPendienteDelegate.DESINDEXAR) {
								solrPendienteResultado = procDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
	        				break;
	        			case ROLSAC_NORMATIVA:
	        				if (solrpendiente.getAccion() == SolrPendienteDelegate.REINDEXAR) { 
	        					normDelegate.desindexarSolr(solrIndexer, solrpendiente);
	        					solrPendienteResultado = indexarPendienteNormativa(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == SolrPendienteDelegate.DESINDEXAR) {
								solrPendienteResultado = normDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
	        				break;
	        			case ROLSAC_UNIDAD_ADMINISTRATIVA: 
							if (solrpendiente.getAccion() == SolrPendienteDelegate.REINDEXAR) {
								uaDelegate.desindexarSolr(solrIndexer, solrpendiente);
								solrPendienteResultado = uaDelegate.indexarSolr(solrIndexer, solrpendiente);
							} else if (solrpendiente.getAccion() == SolrPendienteDelegate.DESINDEXAR) {
								solrPendienteResultado = uaDelegate.desindexarSolr(solrIndexer, solrpendiente);
							}
							break;						
						default:
							break;
        	 }
    	}
    	catch (Exception e) {
	            log.error("Error intentando indexar " + solrpendiente,e);
	            solrPendienteResultado = new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(e));
        } 
    	
    	return solrPendienteResultado;
        
    }
    
    /**
     * Indexa procedimiento e hijos/nietos (Doc Proc, Trámite y Doc Trámite).
     * @param solrIndexer
     * @param solrpendiente
     * @return
     * @throws DelegateException
     */
    private SolrPendienteResultado indexarPendienteProcedimiento(SolrIndexer solrIndexer, SolrPendiente solrpendiente) throws DelegateException {
    	ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
    	TramiteDelegate tramDelegate = DelegateUtil.getTramiteDelegate();
    	DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
    	
    	//Paso 1. Indexamos el procedimiento
    	SolrPendienteResultado solrPendienteResultado= procDelegate.indexarSolr(solrIndexer, solrpendiente.getIdElemento(), EnumCategoria.ROLSAC_PROCEDIMIENTO);
		log.debug("Resultado indexando procedimiento(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
		
		//Paso 2. Recorremos documento y los indexamos
		ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(solrpendiente.getIdElemento());
		for (Documento documento : procedimiento.getDocumentos()) {
			try {
				if (documento != null) {
					solrPendienteResultado = docuDelegate.indexarSolrProcedimientoDoc(solrIndexer, documento.getId(), EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO);
					log.debug("Resultado indexando procedimientoDocumento(DOC:"+documento.getId()+"):"+ solrPendienteResultado.toString());
				}
			} catch (Exception exception) {
				log.error("Error indexando pendiente un doc(id:"+documento.getId()+") procedimiento:" + procedimiento.getId(), exception);
				return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
			}
		}
		
		//Paso 3. Recorremos trámites y documentos y los reindexamos
		for(Tramite tramite : procedimiento.getTramites()) {
			try {
				if (tramite != null) {
					solrPendienteResultado = tramDelegate.indexarSolr(solrIndexer, tramite.getId(), EnumCategoria.ROLSAC_TRAMITE);
					log.debug("Resultado indexando tramite(ID:"+tramite.getId()+"):"+ solrPendienteResultado.toString());
				
					List<Long> idDocumentos = docuDelegate.obtenerDocumentosTramiteSolr(tramite.getId());
	        		for (Long idDocumento : idDocumentos) {
	        			try{
	        				tramDelegate.indexarDocSolr(solrIndexer, idDocumento, EnumCategoria.ROLSAC_TRAMITE_DOCUMENTO);	        				
		        		} catch (Exception exception2) {
		        			log.error("Error indexando pendiente un doc(id:"+tramite.getId()+") tramite:" + procedimiento.getId(), exception2);
		        			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception2));
	    				}
					}
				}
			} catch (Exception exception) {
				log.error("Error indexando pendiente un tramite(id:"+tramite.getId()+") procedimiento:" + procedimiento.getId(), exception);
				return new SolrPendienteResultado(false,ExceptionUtils.getStackTrace(exception));				
			}
		}
		
		//Paso 4. Devolvemos resultado correcto sin mensaje.
		return new SolrPendienteResultado(true, "");
	}


	/**
     * Indexar normativa e hijos (Doc Normativa).
     * @param solrIndexer
     * @param solrpendiente
     * @return
     * @throws DelegateException
     */
    private SolrPendienteResultado indexarPendienteNormativa(SolrIndexer solrIndexer, SolrPendiente solrpendiente) throws DelegateException {
    	NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();
    	
    	//Paso 1. Indexamos la normativa.
    	SolrPendienteResultado solrPendienteResultado= normDelegate.indexarSolrNormativa(solrIndexer, solrpendiente.getIdElemento(), EnumCategoria.ROLSAC_NORMATIVA);
    	log.debug("Resultado indexando procedimiento(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
		Normativa normativa = normDelegate.obtenerNormativa(solrpendiente.getIdElemento());
		TraduccionNormativa traduccion = null;
		
		//Paso 2. Recorremos las traducciones y reindexamos sus archivos.
		//Recorremos las traducciones
		for (String keyIdioma :  normativa.getTraduccionMap().keySet()) {
			try {
				traduccion = (TraduccionNormativa) normativa.getTraduccionMap().get(keyIdioma);
				if (traduccion != null && traduccion.getArchivo() != null) { 
					normDelegate.indexarSolrNormativaDocumento(solrIndexer, traduccion.getArchivo().getId(), EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO);
				}
			} catch (Exception exception ) {
				//Cuidado, si da un error obteniendo la traducción, estaría con el valor anterior.
				if (traduccion == null) {
					log.error("Error indexando pendiente con traducción a null de normativa:" + normativa.getId(), exception);
				} else if (traduccion.getArchivo() == null) {
					log.error("Error indexando pendiente un archivo a nulo de normativa:" + normativa.getId(), exception);
				} else {
					log.error("Error indexando pendiente un doc(parece que id:"+traduccion.getArchivo().getId()+") de normativa:" + normativa.getId(), exception);
				}
				
				solrPendienteResultado = new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
				return solrPendienteResultado;
			}
		}
		return new SolrPendienteResultado(true, "");
	}


	/**
     * Indexar ficha e hijos (Doc ficha).
     * @param solrIndexer
     * @param solrpendiente
     * @throws DelegateException 
     */
    private SolrPendienteResultado indexarPendienteFicha(SolrIndexer solrIndexer, SolrPendiente solrpendiente) throws DelegateException {
    	FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
    	DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
    	
    	//Paso 1. Indexamos la ficha.
    	SolrPendienteResultado solrPendienteResultado= fichaDelegate.indexarSolr(solrIndexer, solrpendiente.getIdElemento(), EnumCategoria.ROLSAC_FICHA);
		log.debug("Resultado indexando ficha(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
		
		if (!solrPendienteResultado.isCorrecto()) {
			return solrPendienteResultado;
		}
		
		//Paso 2. Reindexamos los documentos asociados a la ficha.
		Ficha ficha = fichaDelegate.obtenerFichaParaSolr(solrpendiente.getIdElemento());
		if (ficha == null) {
			log.error("No se encuentra ficha con id: " + solrpendiente.getIdElemento());
			return new SolrPendienteResultado(false, "No se encuentra ficha con id: " + solrpendiente.getIdElemento());
		}
		if (ficha.getDocumentos() != null) {
			for(Documento documento : ficha.getDocumentos()) {
				try {
					if (documento != null) {
						solrPendienteResultado = docuDelegate.indexarSolrFichaDoc(solrIndexer, documento.getId(), EnumCategoria.ROLSAC_FICHA_DOCUMENTO);
						log.debug("Resultado indexando fichaDocumento(DOC:"+documento.getId()+"):"+ solrPendienteResultado.toString());
					}
				} catch (Exception exception) {
					log.error("Error indexando pendiente un doc(id:"+documento.getId()+") de ficha:" + ficha.getId(), exception);
					return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));					
				}
			}
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
					solrpendiente.setMensajeError(solrPendienteResultado.getMensaje());
					solrpendiente.setFechaIndexacion(new Date());
					
					//Si hace 10 dias o + que se crea se marca como erronea porque no se ha podido endexar
					if ( dias >= 10){
						solrpendiente.setResultado(-1);
					} else {
						log.error("No se ha podido realizar la operación (dias ejecutandose:"+dias+")con el registro : "+solrpendiente.getId());
					}
					session.update(solrpendiente);
					session.flush();
						
					
				}
	    	}
	    	session.close(); 
    	} catch(Exception e) {
    		log.error("Error resolver pendiente", e);
    	}
	}
	    
}