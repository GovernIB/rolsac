package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.io.FilenameUtils;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.ArchivoUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.IndexData;
import es.caib.solr.api.model.IndexFile;
import es.caib.solr.api.model.MultilangLiteral;
import es.caib.solr.api.model.PathUO;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;
import es.caib.solr.api.model.types.EnumIdiomas;

/**
 * SessionBean para mantener y consultar Documentos.
 *
 * @ejb.bean
 *  name="sac/persistence/DocumentoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.DocumentoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */

public abstract class DocumentoFacadeEJB extends HibernateEJB {

    /**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 4454278347074939666L;

	/**
     * Obtiene referencia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
    ProcedimientoDelegate procDel;
    FichaDelegate fichDel;
    
    public ProcedimientoDelegate getProcDel() {
		return procDel;
	}

	public void setProcDel(ProcedimientoDelegate procDel) {
		this.procDel = procDel;
	}

	public FichaDelegate getFichDel() {
		return fichDel;
	}

	public void setFichDel(FichaDelegate fichDel) {
		this.fichDel = fichDel;
	}

    /**
     * Crea o actualiza un Documento.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long grabarDocumento(Documento documento, Long procedimiento_id, Long ficha_id) {
    	
    	Session session = getSession();
    	
        try {
        	
        	Ficha ficha = null;
        	ProcedimientoLocal procedimiento = null;
        	Tramite tramite = null;
        	
            if (documento.getId() == null) {

                if (ficha_id != null) {
                    if (!getAccesoManager().tieneAccesoFicha(ficha_id)) {
                        throw new SecurityException("No tiene acceso a la ficha.");
                    }
                    ficha = (Ficha) session.load(Ficha.class, ficha_id);
                    ficha.addDocumento(documento);                    
                }
                
                if (procedimiento_id != null) {
                    if (!getAccesoManager().tieneAccesoProcedimiento(procedimiento_id)) {
                        throw new SecurityException("No tiene acceso al procedimiento.");
                    }  
                    procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, procedimiento_id);
                    procedimiento.addDocumento(documento);
                }
           
                session.save(documento);
                
            } else {
            	
                if (!getAccesoManager().tieneAccesoDocumento(documento.getId())) {
                    throw new SecurityException("No tiene acceso al documento.");
                }	
                
                session.update(documento);
                
            }
            
            session.flush();
            
            if (ficha_id != null) {
            	
            	if (ficha_id != null) 
            		ficha = (Ficha)session.load(Ficha.class, ficha_id);  
            	
                FichaDelegate ficdel = (null != fichDel) ? fichDel: DelegateUtil.getFichaDelegate();
                
                //SOLR Indexar ficha documento.
    			SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
                solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_FICHA_DOCUMENTO.toString(), documento.getId(), 1l);
            }
            
            if (procedimiento_id != null) {
            	
                if (procedimiento_id != null) 
                	procedimiento = (ProcedimientoLocal)session.load(ProcedimientoLocal.class, procedimiento_id);
                
        		ProcedimientoDelegate pldel = (null != procDel) ? procDel : DelegateUtil.getProcedimientoDelegate();
        		
                log.debug("Actualizamos documento del procedimiento");
                Actualizador.actualizar(documento, procedimiento.getId());
                                   
                //SOLR Desindexar procedimiento documento.
    			SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
                solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO.toString(), documento.getId(), 1l);
            }           
            session.flush();
            //TODO añadir if para el tramite
            
            return documento.getId();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } catch (DelegateException e) {
        	throw new EJBException(e);
		} finally {
        	
            close(session);
            
        }
        
    }
    
    /**
     * Obtiene un Documento.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Documento obtenerDocumento(Long id) {
        Session session = getSession();
        try {
            Documento doc =  (Documento) session.load(Documento.class, id);
            Hibernate.initialize(doc.getArchivo());
            for (Iterator iterator = doc.getLangs().iterator(); iterator.hasNext();) {
                String lang = (String) iterator.next();
                TraduccionDocumento traduccion = (TraduccionDocumento) doc.getTraduccion(lang);
                if(traduccion != null){
                    Hibernate.initialize(traduccion.getArchivo());
                }
            }
/*
            Tramite tramite=doc.getDocInformatiuTramite();
            if(null!=tramite) {
            	Hibernate.initialize(tramite.getFormularios()); 
            	Hibernate.initialize(tramite.getDocsInformatius());
            	Hibernate.initialize(tramite.getDocsPresentar());
            }
            
            tramite=doc.getDocPresentarTramite();
            if(null!=tramite) {
            	Hibernate.initialize(tramite.getFormularios()); 
            	Hibernate.initialize(tramite.getDocsInformatius());
            	Hibernate.initialize(tramite.getDocsPresentar());
            }
*/
            
            return doc;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Obtiene un Documento segun solr sin producir un ejbexception.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Documento obtenerDocumentoSolr(Long id) {
        Session session = getSession();
        try {
            Documento doc =  (Documento) session.load(Documento.class, id);
            Hibernate.initialize(doc.getArchivo());
            for (Iterator iterator = doc.getLangs().iterator(); iterator.hasNext();) {
                String lang = (String) iterator.next();
                TraduccionDocumento traduccion = (TraduccionDocumento) doc.getTraduccion(lang);
                if(traduccion != null){
                    Hibernate.initialize(traduccion.getArchivo());
                }
            }
            
            return doc;
        } catch (HibernateException he) {
            return null;
        } catch (Exception e) {
        	return null;
        } finally {
            close(session);
        }
    }

    /**
     * Borrar un documento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void borrarDocumento(Long id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoDocumento(id)) {
                throw new SecurityException("No tiene acceso al documento");
            }
        	Ficha ficha = null;
        	ProcedimientoLocal procedimiento = null;
            Documento documento = (Documento) session.load(Documento.class, id);
            if (documento.getFicha() != null) {
            	ficha = documento.getFicha();            	
                documento.getFicha().removeDocumento(documento);
            }
            if (documento.getProcedimiento() != null) {
            	procedimiento = documento.getProcedimiento();
                documento.getProcedimiento().removeDocumento(documento);
            }

            session.delete(documento);
            session.flush();
            
            //SOLR Desindexar ficha documento.
			if (ficha != null) {
            	SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
            	solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_FICHA_DOCUMENTO.toString(), documento.getId(), 2l);
            }
            
            //SOLR Desindexar procedimiento documento.
			if (procedimiento != null) {
            	SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
            	solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO.toString(), documento.getId(), 2l);
            }
            
		    
            if (procedimiento != null) {
        		ProcedimientoDelegate pldel = DelegateUtil.getProcedimientoDelegate();
                                  	                	
                Actualizador.borrar(documento, procedimiento.getId());
                                    	                    
            } 
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);     
        } catch (DelegateException e) {
        	throw new EJBException(e);
		} finally {
            close(session);
        }
    }

    /**
     * Obtiene el archivo de un documento.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerArchivoDocumento(Long id, String lang, boolean useDefault) {
        Session session = getSession();
        try {
            Documento documento = (Documento) session.load(Documento.class, id);
            TraduccionDocumento tradDocumento = (TraduccionDocumento) documento.getTraduccion(lang);
            if (tradDocumento == null || tradDocumento.getArchivo() == null) {
                if (useDefault) {
                	tradDocumento = (TraduccionDocumento) documento.getTraduccion();
                } else {
                    return null;
                }
            }
            Hibernate.initialize(tradDocumento.getArchivo());
            return tradDocumento.getArchivo();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Obtiene el archivo de un documento de tramite.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerArchivoDocumentoTramite(Long id, String lang, boolean useDefault) {
        Session session = getSession();
        try {
            DocumentTramit documento = (DocumentTramit) session.load(DocumentTramit.class, id);
            TraduccionDocumento tradDocumento = (TraduccionDocumento) documento.getTraduccion(lang);
            if (tradDocumento == null || tradDocumento.getArchivo() == null) {
                if (useDefault) {
                	tradDocumento = (TraduccionDocumento) documento.getTraduccion();
                } else {
                    return null;
                }
            }
            Hibernate.initialize(tradDocumento.getArchivo());
            return tradDocumento.getArchivo();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * 
     * Actualiza los ordenes de los documentos de una sección de una Ficha
	 *
     * FIXME enric@dgtic: este metodo lo pondria en procedimientoFacadeEJB 
     *  
     * @param Map <String,String[]>
     * eg. key= orden_doc396279
     * 	   value={"1"}
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void actualizarOrdenDocs(Map map) {
  
    	Session session = getSession();
    	
        try {
        	
        	Long id;
        	int valor_orden = 0;
        	List doc_orden = new ArrayList();
        	
        	Iterator it = map.entrySet().iterator();
        	
        	while ( it.hasNext() ) {
        		
        		Map.Entry e = (Map.Entry)it.next();
            	String paramName = e.getKey().toString();
            	
            	if ( paramName.startsWith("orden_doc") ) {
            		
            		id = Long.valueOf(paramName.substring(9)).longValue();
            		
             		String[] parametros=(String[])e.getValue();
            		valor_orden= Integer.parseInt(parametros[0]);            		
            		
            		if (!getAccesoManager().tieneAccesoDocumento(id)) {
            			throw new SecurityException("No tiene acceso al documento");
            		}
            		
            		Documento documento = (Documento) session.load(Documento.class, id);
            		documento.setOrden(valor_orden);
            		doc_orden.add(documento);
            		
            	}
            	
            }
        	
            session.flush();
            
            Collections.sort( doc_orden, new DocsFichaComparator() );
            
            Long contador = Long.parseLong("1");
        	
        	Iterator itdoc = doc_orden.iterator();
    		Documento doc = null;
    		
    		while (itdoc.hasNext()) {
    			doc = (Documento)itdoc.next();
    			doc.setOrden(contador);
    			contador += 1;
    		}
            
    		session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
            
    }
	
    class DocsFichaComparator implements Comparator {
		
    	public int compare(Object o1, Object o2) { 
		
			Long x1 = new Long (((Documento)o1).getOrden());
			Long x2 = new Long (((Documento)o2).getOrden());
			
			return x1.compareTo( x2 ); 
	
		}
	
    }
    
    /**
	 * Comprueba si es indexable una ficha.
	 * @return
	 */
	private boolean isIndexable(final Ficha ficha) {
		boolean indexable = true;
		if (ficha.getValidacion() != 1 ) {
			indexable = false;
		}
		
		return indexable;
	}
	
	 /**
	 * Comprueba si es indexable un procedimiento local.
	 * @param procedimiento
	 * @return
	 */
	private boolean isIndexable(final ProcedimientoLocal procedimiento) {
		boolean indexable = true;
		if (procedimiento.getValidacion() != 1 ) {
			indexable = false;
		}
				
		return indexable;
	}
	
	
	/**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
	 * @param solrIndexer
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		final EnumCategoria categoria = EnumCategoria.fromString(solrPendiente.getTipo()); 
		if (EnumCategoria.ROLSAC_FICHA_DOCUMENTO == categoria) {
			return indexarSolrFichaDoc(solrIndexer, solrPendiente.getIdElemento(), EnumCategoria.ROLSAC_FICHA_DOCUMENTO);
		} else if(EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO == categoria) {
			return indexarSolrProcedimientoDoc(solrIndexer, solrPendiente.getIdElemento(), EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para indexar un solrPendiente.
	 * 
	 * @param solrIndexer
	 * @param idElemento
	 * @param categoria
	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("deprecation")
	public SolrPendienteResultado indexarSolrProcedimientoDoc(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) {
		log.debug("DocumentoFacadeEJB.indexarSolrProcedimientoDoc. idElemento:" + idElemento+" categoria:"+ categoria);
		boolean indexacion = false;
		try {
			//Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			final Documento documento = obtenerDocumentoSolr(idElemento);
			if (documento == null) {
				return new SolrPendienteResultado(false, "Da problema al cargar la info del documento procedimiento.");
			}
			
			boolean isIndexable = this.isIndexable(documento.getProcedimiento());
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}
						
			//Obtenemos el procedimiento por separado porque daba un error de lazy hibernate
			ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimiento(documento.getProcedimiento().getId());
			
			//Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la categoria de tipo ficha.
			final IndexFile indexData = new IndexFile();
			indexData.setCategoria(categoria);
			indexData.setCategoriaPadre(EnumCategoria.ROLSAC_PROCEDIMIENTO);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.setElementoId(idElemento.toString());
			
			//materia
			final List<String> materiasId = new ArrayList<String>();		
			for(Materia materia : procedimiento.getMaterias()) {
	    		materiasId.add(materia.getId().toString());
			}
			indexData.setMateriaId(materiasId);
			
			//Publico Objetivo
			final List<String> publicoObjetivoId = new ArrayList<String>();		
			for( PublicoObjetivo publicoObjectivo :  procedimiento.getPublicosObjetivo()) {
	    		publicoObjetivoId.add(publicoObjectivo.getId().toString());
			}
			indexData.setPublicoId(publicoObjetivoId);
			
			//Fechas
			indexData.setFechaActualizacion(procedimiento.getFechaActualizacion());
			indexData.setFechaPublicacion(procedimiento.getFechaPublicacion());
			indexData.setFechaCaducidad(procedimiento.getFechaCaducidad());
			
			//UA
			UnidadAdministrativa unitatAdministrativa =procedimiento.getUnidadAdministrativa();
			if (unitatAdministrativa != null) {
				List<PathUO> uos = new ArrayList<PathUO>();
				PathUO uo = new PathUO();
				List<String> path = new ArrayList<String>();
				
				//Hay que extraer la id de los predecesores y luego el de uno mismo
				Set<UnidadAdministrativa> predecesores = unitatAdministrativa.getPredecesores();
				for(UnidadAdministrativa predecesor : predecesores) {
					path.add(predecesor.getId().toString());
				}
				path.add(unitatAdministrativa.getId().toString());
				
				uo.setPath(path);
				uos.add(uo);
				indexData.setUos(uos);
			}
			
			for(Tramite tramite : procedimiento.getTramites()) {
				if (tramite.getIdTraTel() != null && !"".equals(tramite.getIdTraTel())) {
					indexData.setTelematico(true);
				}
			}
			
			final String nomUnidadAministrativa;
			if (procedimiento.getUnidadAdministrativa() == null) {
				nomUnidadAministrativa = "";
			} else {
				nomUnidadAministrativa = procedimiento.getUnidadAdministrativa().getNombre();
			}
			
			//Traducciones
			final Map<String, Traduccion> traducciones = documento.getTraduccionMap();
			
			//Recorremos las traducciones
			for (String keyIdioma : traducciones.keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionDocumento traduccion = (TraduccionDocumento)traducciones.get(keyIdioma);
				final TraduccionProcedimiento traduccionProc = (TraduccionProcedimiento)procedimiento.getTraduccion(keyIdioma);
		    	
				if (traduccion != null && enumIdioma != null) {
					
					//Para saltarse los idiomas sin titulo.
					if ((traduccion.getTitulo() == null || traduccion.getTitulo().isEmpty())  && enumIdioma != EnumIdiomas.CATALA) {
						continue;
					}
					
					if (ArchivoUtils.isIndexableSolr(traduccion.getArchivo())) {
						log.debug("Es indexable con mime:" + documento.getArchivo().getMime()+" y tamanyo:" + documento.getArchivo().getPeso());
					} else {
						log.debug("NO Es indexable con mime:" + documento.getArchivo().getMime()+" y tamanyo:" + documento.getArchivo().getPeso());
						continue;
					}
					
					try {
						//Anyadimos idioma al enumerado.
						indexData.setIdioma(enumIdioma);
						
						final MultilangLiteral titulo = new MultilangLiteral();
						final MultilangLiteral descripcion = new MultilangLiteral();
						final MultilangLiteral descripcionPadre = new MultilangLiteral();
						final MultilangLiteral urls = new MultilangLiteral();
						final MultilangLiteral urlsPadres = new MultilangLiteral();		
						final MultilangLiteral searchTextOptional = new MultilangLiteral();
						
						final MultilangLiteral extension = new MultilangLiteral();
						
						//Seteamos los primeros campos multiidiomas: Titulo, Descripción y el search text.
						titulo.addIdioma(enumIdioma, traduccion.getTitulo());
						descripcion.addIdioma(enumIdioma, traduccion.getDescripcion());
						if (traduccionProc != null) {
							descripcionPadre.addIdioma(enumIdioma, traduccionProc.getNombre());
						}
					    
				    	
				    	//La entensión del archivo por mime
				    	if (documento.getArchivo() != null) {
				    		extension.addIdioma(enumIdioma, documento.getArchivo().getMime());
				    	}
				    	
				    	final StringBuffer textoOptional = new StringBuffer();
						
				    	//materia
				    	for(Materia materia : procedimiento.getMaterias()) {
				    		final TraduccionMateria traduccionMateria = (TraduccionMateria) materia.getTraduccion(keyIdioma);
				    		if (traduccionMateria != null) {
								textoOptional.append(" ");
								textoOptional.append(traduccionMateria.getNombre());
								textoOptional.append(" ");
								textoOptional.append(traduccionMateria.getDescripcion());
								textoOptional.append(" ");
								textoOptional.append(traduccionMateria.getPalabrasclave());
				    		}
						}
				    	
				    	//hechos vitales
						for(HechoVitalProcedimiento hecho : procedimiento.getHechosVitalesProcedimientos()) {
							final TraduccionHechoVital traduccionHechoVital =  (TraduccionHechoVital) hecho.getHechoVital().getTraduccionFront(keyIdioma);
							if (traduccionHechoVital != null) {
								textoOptional.append(" ");
								textoOptional.append(traduccionHechoVital.getNombre());
								textoOptional.append(" ");
								textoOptional.append(traduccionHechoVital.getDescripcion());
								textoOptional.append(" ");
								textoOptional.append(traduccionHechoVital.getPalabrasclave());
							}
						}
						
						//Publico objetivo, para añadirlo como id y para extraer el titulo para la url
						String nombrePubObjetivo = "";
						for( PublicoObjetivo publicoObjectivo :  procedimiento.getPublicosObjetivo()) {
							final TraduccionPublicoObjetivo traduccionPO = (TraduccionPublicoObjetivo) publicoObjectivo.getTraduccion(keyIdioma);
							if (traduccionPO != null) {
								nombrePubObjetivo = traduccionPO.getTitulo();
							}
						}
						
						
						//UO
						textoOptional.append(" ");
						textoOptional.append(nomUnidadAministrativa);
						
				    	//Nombre familia
						textoOptional.append(procedimiento.getNombreFamilia());
						
						//Normativa asociadas
						for(Normativa normativa : procedimiento.getNormativas()) {
							final TraduccionNormativa traduccionNormativa = (TraduccionNormativa) normativa.getTraduccion(keyIdioma);
							if (traduccionNormativa != null) {
								textoOptional.append(traduccionNormativa.getTitulo());
								textoOptional.append(" ");
							}
						}
				    	urlsPadres.addIdioma(enumIdioma, "/seucaib/"+keyIdioma+"/"+nombrePubObjetivo+"/tramites/tramite/"+procedimiento.getId());
				    	urls.addIdioma(enumIdioma, "govern/rest/arxiu/" + documento.getId());
				    	
				    	//Seteamos datos multidioma.
						indexData.setTitulo(titulo);
						indexData.setDescripcion(descripcion);
						indexData.setDescripcionPadre(descripcionPadre);
						indexData.setUrl(urls);
						indexData.setUrlPadre(urlsPadres);
						
						TraduccionProcedimientoLocal traduccionTramite = (TraduccionProcedimientoLocal) procedimiento.getTraduccion(enumIdioma.toString());
						if (traduccionTramite != null) {
							searchTextOptional.addIdioma(enumIdioma, traduccionTramite.getNombre() + " " + traduccion.getArchivo().getId() + " "+ traduccionTramite.getResumen());
						}
						indexData.setSearchTextOptional(searchTextOptional);
						indexData.setFileContent(traduccion.getArchivo().getDatos());
						solrIndexer.indexarFichero(indexData);
						indexacion = true;	
						
					} catch(Exception exceptionSolr) {
						log.error("Error indexando un documento de procedimiento. DocID:" + documento.getId()+" Idioma:" + enumIdioma, exceptionSolr);
					}
				}
			}
			
			
			
			
			
			
		
			return new SolrPendienteResultado(indexacion);
		} catch(Exception exception) {
			log.error("Error en documentofacade intentando indexar.", exception);
			String mensajeError;
			if (exception.getMessage() == null) {
				mensajeError = exception.toString();
			} else {
				mensajeError = exception.getMessage();
			}
			return new SolrPendienteResultado(false, mensajeError);
		}
	}
	
	/**
	 * Indexa ficha documento.
	 * @param solrPendiente
	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("deprecation")
	public SolrPendienteResultado indexarSolrFichaDoc(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) {
		log.debug("DocumentoFacadeEJB.indexarSolrFichaDoc. idElemento:" + idElemento+" categoria:"+ categoria);
		boolean indexacion = false;
		try {
			//Paso 0. Obtenemos la ficha documento y vemos si es indexable.
			final Documento documento = obtenerDocumentoSolr(idElemento);
			
			if (documento == null) {
				return new SolrPendienteResultado(false, "Da problema al cargar la info del documento ficha.");
			}
			
			boolean isIndexable = this.isIndexable(documento.getFicha());
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}
			
			
			//Obtenemos la ficha por separado porque daba un error de lazy hibernate
			FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
			Ficha ficha = fichaDelegate.obtenerFicha(documento.getFicha().getId());

			//Preparamos la información básica: id elemento, aplicacionID = ROLSAC, la categoria de tipo ficha documento, la categoria del padre de tipo ficha.
			final IndexFile indexData = new IndexFile();
			indexData.setCategoria(categoria);
			indexData.setCategoriaPadre(EnumCategoria.ROLSAC_FICHA);
			indexData.setElementoId(idElemento.toString());
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			
			//Datos de ids Materia
			final List<String> materiasId = new ArrayList<String>();		
			for(Materia materia : ficha.getMaterias()) {
				materiasId.add(materia.getId().toString());
			}
	    	indexData.setMateriaId(materiasId);
			
			//Datos de ids Publico objetivo
	    	final List<String> publicoObjetivoId = new ArrayList<String>();		
			for( PublicoObjetivo publicoObjectivo :  ficha.getPublicosObjetivo()) {
				publicoObjetivoId.add(publicoObjectivo.getId().toString());
			}
			indexData.setPublicoId(publicoObjetivoId);
			
			
			//Fechas
			indexData.setFechaActualizacion(ficha.getFechaActualizacion());
			indexData.setFechaPublicacion(ficha.getFechaPublicacion());
			indexData.setFechaCaducidad(ficha.getFechaCaducidad());
			indexData.setInterno(false);
					
			//Recorremos las traducciones
			for (String keyIdioma : documento.getTraduccionMap().keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionDocumento traduccionDocumento = (TraduccionDocumento) documento.getTraduccion(keyIdioma);
				final TraduccionFicha traduccionFicha = (TraduccionFicha) ficha.getTraduccion(keyIdioma);
					
				if (traduccionDocumento != null && enumIdioma != null) {
						
					//Para saltarse los idiomas sin titulo.
					if (traduccionDocumento.getTitulo() == null || traduccionDocumento.getTitulo().isEmpty()) {
						continue;
					}
						
					if (ArchivoUtils.isIndexableSolr(traduccionDocumento.getArchivo())) {
						log.debug("Es indexable tradDoc Ficha con id:" + traduccionDocumento.getArchivo().getId()+" y tamanyo:" + documento.getArchivo().getPeso());
					} else {
						log.debug("NO Es indexable tradDoc Ficha con id:" + traduccionDocumento.getArchivo().getId()+" y tamanyo:" + documento.getArchivo().getPeso());
						continue;
					}
					
					try {						
						//Iteramos las traducciones
						final MultilangLiteral titulo = new MultilangLiteral();
						final MultilangLiteral descripcion = new MultilangLiteral();
						final MultilangLiteral descripcionPadre = new MultilangLiteral();		
						final MultilangLiteral urls = new MultilangLiteral();
						final MultilangLiteral urlsPadre = new MultilangLiteral();
						final MultilangLiteral searchText = new MultilangLiteral();
						final MultilangLiteral searchTextOptional = new MultilangLiteral();
						
						//Anyadimos idioma al enumerado.
						indexData.setIdioma(enumIdioma);
						
						//Seteamos los primeros campos multiidiomas: Titulo, Descripción (y padre) y el search text.
						titulo.addIdioma(enumIdioma, traduccionDocumento.getTitulo());
						descripcion.addIdioma(enumIdioma, traduccionDocumento.getDescripcion());
				    	if (traduccionFicha != null) {
							descripcionPadre.addIdioma(enumIdioma, traduccionFicha.getTitulo());
						}
				    	
				    	if (documento.getArchivo() == null) {
				    		searchText.addIdioma(enumIdioma, traduccionDocumento.getTitulo()+ " "+ traduccionDocumento.getDescripcion() );
					    }  else {
				    		searchText.addIdioma(enumIdioma, traduccionDocumento.getTitulo()+ " "+ traduccionDocumento.getDescripcion() + " "+ documento.getArchivo().getNombre());
				    	}
				    	
				    	//StringBuffer que tendra el contenido a agregar en textOptional
				    	final StringBuffer textoOptional = new StringBuffer();
						
				    	String fichaUAId = "";
						//Unidades administrativas de las fichas.
						for (FichaUA fichaUA : ficha.getFichasua()) {
							if (fichaUAId.isEmpty()) {
								fichaUAId = fichaUA.getId().toString();
							}
							
							TraduccionUA traduccionUA = ((TraduccionUA)fichaUA.getUnidadAdministrativa().getTraduccion(keyIdioma));
							if (traduccionUA != null) {
								textoOptional.append(traduccionUA.getNombre());
								textoOptional.append(" ");
							}
							
							List<PathUO> uos = new ArrayList<PathUO>();
							PathUO uo = new PathUO();
							List<String> path = new ArrayList<String>();
							
							//Hay que extraer la id de los predecesores y luego el de uno mismo
							Set<UnidadAdministrativa> predecesores = fichaUA.getUnidadAdministrativa().getPredecesores();
							for(UnidadAdministrativa predecesor : predecesores) {
								path.add(predecesor.getId().toString());
							}
							path.add( fichaUA.getUnidadAdministrativa().getId().toString());
							
							uo.setPath(path);
							uos.add(uo);
							indexData.setUos(uos);
						}
						
						if (documento.getArchivo() == null) {
							urls.addIdioma(enumIdioma, "govern/rest/arxiu/" + documento.getId());
						} else {
							urls.addIdioma(enumIdioma, "govern/rest/arxiu/" + documento.getArchivo().getId());
						}
						
				    	if (traduccionFicha == null || (traduccionFicha.getUrl() == null || traduccionFicha.getUrl().isEmpty())) {
				    		urlsPadre.addIdioma(enumIdioma, "/govern/sac/fitxaRedirect.do?codi="+ficha.getId()+"&lang="+keyIdioma);
				    	} else {
				    		String idUA = "{#UA:"+fichaUAId+"}";
				    		urlsPadre.addIdioma(enumIdioma, "/govern/sac/fitxa.do?codi="+ documento.getId() + "&coduo=" + idUA + "&lang=" + keyIdioma);
				    	}
				    	
				    	//Seteamos datos multidioma.
						indexData.setTitulo(titulo);
						indexData.setDescripcion(descripcion);
						indexData.setDescripcionPadre(descripcionPadre);
						indexData.setUrl(urls);
						indexData.setUrlPadre(urlsPadre);
						TraduccionFicha traducionTramite = (TraduccionFicha) ficha.getTraduccion(enumIdioma.toString());
						if (traducionTramite != null) {
							searchTextOptional.addIdioma(enumIdioma, traducionTramite.getTitulo()+ " "+ traducionTramite.getDescAbr() + " "+ traducionTramite.getDescripcion());
						}
						indexData.setSearchTextOptional(searchTextOptional);
						
						indexData.setFileContent(traduccionDocumento.getArchivo().getDatos());
						solrIndexer.indexarFichero(indexData);
						indexacion = true;	
					} catch(Exception exceptionSolr) {
						log.error("Error indexando un documento de ficha. DocID:" + documento.getId()+" Idioma:" + enumIdioma, exceptionSolr);
					}
				}
			}
		
			
			
			return new SolrPendienteResultado(indexacion);
		} catch(Exception exception) {
			log.error("Error en documentofacade intentando indexar.", exception);
			String mensajeError;
			if (exception.getMessage() == null) {
				mensajeError = exception.toString();
			} else {
				mensajeError = exception.getMessage();
			}
			return new SolrPendienteResultado(false, mensajeError);
		}
	}
	
	/**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		try {
			solrIndexer.desindexar(solrPendiente.getIdElemento().toString(), EnumCategoria.fromString(solrPendiente.getTipo()));
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en documentoFacade intentando desindexar.", exception);
			return new SolrPendienteResultado(false, exception.getMessage());
		}
	}

	/**
	 * Metodo para obtener las ids de los documentos asociados a la id ficha.
	 * @param idFicha
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 * @return
	 */
	public List<Long> obtenerDocumentosFichaSolr(final Long idFicha) {
		final Session session = getSession();
    	try {
    		return this.castList(Long.class, session.createQuery("select doc.id from Documento doc where doc.ficha.id = "+idFicha).list());
    	} catch(HibernateException he) {
    		log.error("Error obteniendo los documentos asociados a la idFicha:"+idFicha, he);
    		return new ArrayList<Long>();
    	} finally{
    		try {
				session.close();
			} catch (HibernateException e) {
				log.error("Error cerrando en obteniendo los documentos asociados a la idFicha:"+idFicha, e);
			}
    	}
	}

	/**
	 *  Metodo para obtener las ids de los documentos asociados a la id procedimiento. 
	 * @param idProcedimiento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
	 * @return
	 */
	public List<Long> obtenerDocumentosProcedimientoSolr(final Long idProcedimiento)	{
		final Session session = getSession();
    	try {
    		return this.castList(Long.class, session.createQuery("select doc.id from Documento doc where doc.procedimiento.id = "+idProcedimiento).list());
    	} catch(HibernateException he) {
    		log.error("Error obteniendo los documentos asociados a la idProcedimiento:"+idProcedimiento, he);
    		return new ArrayList<Long>();
    	} finally{
    		try {
				session.close();
			} catch (HibernateException e) {
				log.error("Error cerrando en obteniendo los documentos asociados a la idProcedimiento:"+idProcedimiento, e);
			}
    	}
	}
	
	
	/**
	 *  Metodo para obtener las ids de los documentos asociados a la id procedimiento. 
	 * @param idProcedimiento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
	 * @return
	 */
	public List<Long> obtenerDocumentosTramiteSolr(final Long idTramite)	{
		final Session session = getSession();
    	try {
    		return this.castList(Long.class, session.createQuery("select doc.id from DocumentTramit doc where doc.tramit.id = "+idTramite).list());
    	} catch(HibernateException he) {
    		log.error("Error obteniendo los documentos asociados a la idTramite:"+idTramite, he);
    		return new ArrayList<Long>();
    	} finally{
    		try {
				session.close();
			} catch (HibernateException e) {
				log.error("Error cerrando en obteniendo los documentos asociados a la idTramite:"+idTramite, e);
			}
    	}
	}	
	
}
