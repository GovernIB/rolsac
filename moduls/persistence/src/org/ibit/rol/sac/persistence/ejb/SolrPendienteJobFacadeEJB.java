package org.ibit.rol.sac.persistence.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
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
    		final EnumCategoria enumCategoria = EnumCategoria.fromString(solrpendiente.getTipo());
        	switch (enumCategoria) {
	        			case ROLSAC_FICHA:
	        				if (solrpendiente.getAccion() == SolrPendienteDelegate.REINDEXAR) {
	        					solrPendienteResultado = desindexarPendienteFicha(solrIndexer,
										solrpendiente);
	        					if (solrPendienteResultado.isCorrecto()) {	        						
	        						solrPendienteResultado = indexarPendienteFicha(solrIndexer, solrpendiente);
	        					}
							} else if (solrpendiente.getAccion() == SolrPendienteDelegate.DESINDEXAR) {
								solrPendienteResultado = desindexarPendienteFicha(solrIndexer,
										solrpendiente);
							}
	        				break;
	        			case ROLSAC_PROCEDIMIENTO:
							if (solrpendiente.getAccion() == SolrPendienteDelegate.REINDEXAR) {
								solrPendienteResultado = desindexarPendienteProcedimiento(solrIndexer,
										solrpendiente);
								if (solrPendienteResultado.isCorrecto()) {
									solrPendienteResultado = indexarPendienteProcedimiento(solrIndexer, solrpendiente);
								}
							} else if (solrpendiente.getAccion() == SolrPendienteDelegate.DESINDEXAR) {
								solrPendienteResultado = desindexarPendienteProcedimiento(solrIndexer,
										solrpendiente);
							}
	        				break;
	        			case ROLSAC_NORMATIVA:
	        				if (solrpendiente.getAccion() == SolrPendienteDelegate.REINDEXAR) { 
	        					solrPendienteResultado = desindexarPendienteNormativa(solrIndexer,
										solrpendiente);
	        					if (solrPendienteResultado.isCorrecto()) {
	        						solrPendienteResultado = indexarPendienteNormativa(solrIndexer, solrpendiente);
	        					}
							} else if (solrpendiente.getAccion() == SolrPendienteDelegate.DESINDEXAR) {
								solrPendienteResultado = desindexarPendienteNormativa(solrIndexer,
										solrpendiente);
							}
	        				break;
	        			case ROLSAC_UNIDAD_ADMINISTRATIVA: 
							if (solrpendiente.getAccion() == SolrPendienteDelegate.REINDEXAR) {
								solrPendienteResultado = desindexarPendienteUnidadAdministrativa(
										solrIndexer, solrpendiente);
								if (solrPendienteResultado.isCorrecto()) {
									solrPendienteResultado = indexarPendienteUnidadAdministrativa(solrIndexer, solrpendiente);
								}
							} else if (solrpendiente.getAccion() == SolrPendienteDelegate.DESINDEXAR) {
								solrPendienteResultado = desindexarPendienteUnidadAdministrativa(
										solrIndexer, solrpendiente);
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


	private SolrPendienteResultado desindexarPendienteNormativa(
			final SolrIndexer solrIndexer, final SolrPendiente solrpendiente) throws DelegateException {
		NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();
		SolrPendienteResultado solrPendienteResultado = normDelegate.desindexarSolr(solrIndexer, solrpendiente);
		
		if (!solrPendienteResultado.isCorrecto()) {
			log.error("Error desindexando Normativa con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
			return solrPendienteResultado;
		} else {
			log.debug("Resultado desindexando Normativa con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
		}
		
		// Devolvemos sin mensaje
		return new SolrPendienteResultado(true, "");
	}


	private SolrPendienteResultado desindexarPendienteUnidadAdministrativa(
			final SolrIndexer solrIndexer, final SolrPendiente solrpendiente) throws DelegateException {
		
		UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
		
		SolrPendienteResultado solrPendienteResultado = uaDelegate.desindexarSolr(solrIndexer, solrpendiente);
			
		if (!solrPendienteResultado.isCorrecto()) {
			log.error("Error desindexando UA con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
			return solrPendienteResultado;
		} else {
			log.debug("Resultado desindexando UA con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
		}
		
		// Devolvemos sin mensaje
		return new SolrPendienteResultado(true, "");
	}


	private SolrPendienteResultado desindexarPendienteProcedimiento(
			final SolrIndexer solrIndexer, final SolrPendiente solrpendiente) throws DelegateException {
		ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		SolrPendienteResultado solrPendienteResultado = procDelegate.desindexarSolr(solrIndexer, solrpendiente);		
		
		if (!solrPendienteResultado.isCorrecto()) {
			log.error("Error desindexando Procedimiento con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
			return solrPendienteResultado;
		} else {
			log.debug("Resultado desindexando Procedimiento con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
		}
		
		// Devolvemos sin mensaje
		return new SolrPendienteResultado(true, "");
	}


	private SolrPendienteResultado desindexarPendienteFicha(
			final SolrIndexer solrIndexer, final SolrPendiente solrpendiente) throws DelegateException {
		
		FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
		SolrPendienteResultado solrPendienteResultado = fichaDelegate.desindexarSolr(solrIndexer, solrpendiente);
		
		if (!solrPendienteResultado.isCorrecto()) {
			log.error("Error desindexando Ficha con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
			return solrPendienteResultado;
		} else {
			log.debug("Resultado desindexando Ficha con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
		}
		
		// Devolvemos sin mensaje
		return new SolrPendienteResultado(true, "");
	}


	private SolrPendienteResultado indexarPendienteUnidadAdministrativa(
			final SolrIndexer solrIndexer, final SolrPendiente solrpendiente) throws DelegateException {
		
		UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
		
		SolrPendienteResultado solrPendienteResultado;
		solrPendienteResultado = uaDelegate.indexarSolr(solrIndexer, solrpendiente);
		
		if (!solrPendienteResultado.isCorrecto()) {
			log.error("Error indexando UA con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
			return solrPendienteResultado;
		} else {
			log.debug("Resultado indexando UA con ID:" +solrpendiente.getIdElemento()+" :"+ solrPendienteResultado.toString());
		}
		
		// Devolvemos sin mensaje
		return new SolrPendienteResultado(true, "");
		
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
		if (!solrPendienteResultado.isCorrecto()) {
			log.error("Resultado indexando procedimiento(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
			return solrPendienteResultado;
		} else {
			log.debug("Resultado indexando procedimiento(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
		}
		
		//Paso 2. Recorremos documento y los indexamos
		// En caso de que falle un documento, lo dejamos pasar por si da error al indexar pero lo tenemos en cuenta en el mensaje de retorno
		String msgRetorno = "";
		ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(solrpendiente.getIdElemento());
		for (Documento documento : procedimiento.getDocumentos()) {
			try {
				if (documento != null) {
					solrPendienteResultado = docuDelegate.indexarSolrProcedimientoDoc(solrIndexer, documento.getId(), EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO);
					if (!solrPendienteResultado.isCorrecto()) {
						log.error("Error indexando documento " + documento.getId() + " de procedimiento " + procedimiento.getId() + ": " + solrPendienteResultado.getMensaje());
						msgRetorno += "Ha fallado al indexar el documento " + documento.getId() + " del procedimiento (revise el log) \n";
					} else {
						log.debug("Resultado indexando procedimientoDocumento(DOC:"+documento.getId()+"):"+ solrPendienteResultado.toString());
					}
				}
			} catch (Exception exception) {
				log.error("Error indexando pendiente un doc(id:"+documento.getId()+") procedimiento:" + procedimiento.getId(), exception);
				return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
			}
		}
		
		//Paso 3. Recorremos trámites y documentos y los reindexamos
		// Si falla al indexar un trámite tomamos la indexacion como no correcta
		// Si falla al indexar uno de los docs 
		for(Tramite tramite : procedimiento.getTramites()) {
			try {
				if (tramite != null) {
					
					solrPendienteResultado = tramDelegate.indexarSolr(solrIndexer, tramite.getId(), EnumCategoria.ROLSAC_TRAMITE);
					if (!solrPendienteResultado.isCorrecto()) {
						log.error("Error indexando tramite(ID:"+tramite.getId()+"):"+ solrPendienteResultado.toString());
						return solrPendienteResultado;
					} else {
						log.debug("Resultado indexando tramite(ID:"+tramite.getId()+"):"+ solrPendienteResultado.toString());
					}
					
				
					List<Long> idDocumentos = docuDelegate.obtenerDocumentosTramiteSolr(tramite.getId());
	        		for (Long idDocumento : idDocumentos) {
	        			try{
	        				solrPendienteResultado = tramDelegate.indexarDocSolr(solrIndexer, idDocumento, EnumCategoria.ROLSAC_TRAMITE_DOCUMENTO);
	        				if (!solrPendienteResultado.isCorrecto()) {
	    						log.error("Error indexando documento " + idDocumento + " de tramite " + tramite.getId() + ": " + solrPendienteResultado.getMensaje());
	    						msgRetorno += "Ha fallado al indexar el documento " + idDocumento + " de tramite " + tramite.getId() + " (revise el log) \n";
	    					} else {
	    						log.debug("Resultado indexando documento " + idDocumento + " de tramite " + tramite.getId() + ":"+ solrPendienteResultado.toString());
	    					}
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
		
		//Paso 4. Devolvemos resultado correcto con mensaje si ha fallado alguno de los documentos.
		return new SolrPendienteResultado(true, msgRetorno);
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
    	
    	if (!solrPendienteResultado.isCorrecto()) {
    		log.error("Error indexando procedimiento(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
    		return solrPendienteResultado;
    	} else {
    		log.debug("Resultado indexando procedimiento(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
    	}
    	
    	
		Normativa normativa = normDelegate.obtenerNormativa(solrpendiente.getIdElemento());
		TraduccionNormativa traduccion = null;
		String msgRetorno = "";
		
		//Paso 2. Recorremos las traducciones y reindexamos sus archivos.
		// En caso de que falle uno de los documentos continuamos pero lo tenemos en cuenta en el mensaje de retorno
		for (String keyIdioma :  normativa.getTraduccionMap().keySet()) {
			try {
				traduccion = (TraduccionNormativa) normativa.getTraduccionMap().get(keyIdioma);
				if (traduccion != null && traduccion.getArchivo() != null) { 
					solrPendienteResultado = normDelegate.indexarSolrNormativaDocumento(solrIndexer, traduccion.getArchivo().getId(), EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO);
					if (!solrPendienteResultado.isCorrecto()) {
			    		log.error("Error indexando archivo " + traduccion.getArchivo().getId() + ": " + solrPendienteResultado.toString());
			    		msgRetorno += "Error indexando archivo " + traduccion.getArchivo().getId() + " (revisar log) \n";
			    	} else {
			    		log.debug("Resultado indexando archivo " + traduccion.getArchivo().getId() + ": " + solrPendienteResultado.toString());
			    	}
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
		
		//Paso 4. Devolvemos resultado correcto con mensaje dependiendo si ha fallado algun documento.
		return new SolrPendienteResultado(true, msgRetorno);
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
		if (!solrPendienteResultado.isCorrecto()) {
			log.error("Error indexando ficha(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
			return solrPendienteResultado;
		} else {
			log.debug("Resultado indexando ficha(ID:"+solrpendiente.getIdElemento()+"):"+ solrPendienteResultado.toString());
		}
		
		//Paso 2. Reindexamos los documentos asociados a la ficha.
		// En caso de que falle la indexacion de algun documento seguimos pero lo tenemos en cuenta en el mensaje
		String msgRetorno = "";
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
						if (!solrPendienteResultado.isCorrecto()) {
							log.debug("Error indexando fichaDocumento(DOC:"+documento.getId()+"):"+ solrPendienteResultado.toString());
							msgRetorno += "Error indexando fichaDocumento "+documento.getId() + " (revise el log) \n";
						} else {
							log.debug("Resultado indexando fichaDocumento(DOC:"+documento.getId()+"):"+ solrPendienteResultado.toString());
						}						
					}
				} catch (Exception exception) {
					log.error("Error indexando pendiente un doc(id:"+documento.getId()+") de ficha:" + ficha.getId(), exception);
					return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));					
				}
			}
		}
		
		//Paso 4. Devolvemos resultado correcto con mensaje dependiendo si falla algun documento
		return new SolrPendienteResultado(true, msgRetorno);
		
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
					solrpendiente.setMensajeError(StringUtils.substring(solrPendienteResultado.getMensaje(), 0, 3000));
					solrpendiente.setFechaIndexacion(new Date());
					session.update(solrpendiente);
					session.flush();
				} else {
					final Calendar fechaCalendar  = Calendar.getInstance();
					fechaCalendar.setTime(solrpendiente.getFechaCreacion());
					final Calendar hoyCalendar  = Calendar.getInstance();
					hoyCalendar.setTime(new Date());
					
					final int dias = hoyCalendar.get(Calendar.DATE) - fechaCalendar.get(Calendar.DATE);					
					solrpendiente.setMensajeError(StringUtils.substring(solrPendienteResultado.getMensaje(), 0, 3000));
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
