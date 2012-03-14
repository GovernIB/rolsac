package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

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
     * Obtiene referï¿½ncia al ejb de control de Acceso.
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
    		//Long docInftramite_id, Long docPreTramite_id) {
        Session session = getSession();
        try {
        	Ficha ficha = null;
        	ProcedimientoLocal procedimiento = null;
        	Tramite tramite=null;
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
/*                
                if (docInftramite_id != null) {
                    if (!getAccesoManager().tieneAccesoTramite(docInftramite_id)) {
                        throw new SecurityException("No tiene acceso al tramite.");
                    }  
                    tramite = (Tramite) session.load(Tramite.class, docInftramite_id);
                    tramite.addDocInformatiu(documento);
                }
                if (docPreTramite_id != null) {
                    if (!getAccesoManager().tieneAccesoTramite(docPreTramite_id)) {
                        throw new SecurityException("No tiene acceso al tramite.");
                    }  
                    tramite = (Tramite) session.load(Tramite.class, docPreTramite_id);
                    tramite.addDocPresentar(documento);
                }

  */              
                session.save(documento);
            } else {
                if (!getAccesoManager().tieneAccesoDocumento(documento.getId())) {
                    throw new SecurityException("No tiene acceso al documento.");
                }	
                
                session.update(documento);
            }
            session.flush();
            if (ficha_id != null) {
            	if (ficha_id != null) ficha = ficha = (Ficha) session.load(Ficha.class, ficha_id);       	           	
                FichaDelegate ficdel = null!=fichDel? fichDel: DelegateUtil.getFichaDelegate();
                try {  
                	ficdel.indexBorraFicha(ficha.getId());
                	ficdel.indexInsertaFicha(ficha,null);   
                } catch (DelegateException e) {
                    log.error("Error indexando ficha", e);
                }                                                        
            }
            if (procedimiento_id != null) {
                if (procedimiento_id != null) procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, procedimiento_id);
        		ProcedimientoDelegate pldel = null!=procDel? procDel : DelegateUtil.getProcedimientoDelegate();
                try {            	
                	pldel.indexBorraProcedimiento(procedimiento);
                	pldel.indexInsertaProcedimiento(procedimiento,null);   
                    log.debug("Actualizamos documento del procedimiento");
                    Actualizador.actualizar(documento, procedimiento.getId());
                } catch (DelegateException e) {
                    log.error("Error indexando procedimiento", e);
                }                    

            }           
            
            
            //TODO añadir if para el tramite
            
            return documento.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    public ProcedimientoLocal cargarDocumentos(ProcedimientoLocal procedimiento){
    	try {
    		if (visible(procedimiento)) {
               // Hibernate.initialize(procedimiento.getDocumentos());
                Hibernate.initialize(procedimiento.getMaterias());
                Hibernate.initialize(procedimiento.getNormativas());
                Hibernate.initialize(procedimiento.getTramites());
                List<Tramite> tramites = procedimiento.getTramites();
                for (Iterator iter = tramites.iterator(); iter.hasNext();) {
                    Tramite tramite = (Tramite) iter.next();
                    Hibernate.initialize(tramite.getFormularios());
                    Hibernate.initialize(tramite.getDocsInformatius());
                    Hibernate.initialize(tramite.getTaxes());
                }
	            Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
	    		Hibernate.initialize(procedimiento.getDocumentos());
		    	List<Documento> docs = procedimiento.getDocumentos();
		        for (Documento docInformatiu : procedimiento.getDocumentos()) {
			        for (final String idioma : (Collection<String>)procedimiento.getLangs()){
			        	log.debug("entra: "+docInformatiu.getId());
		                final TraduccionDocumento traduccion = (TraduccionDocumento)docInformatiu.getTraduccion(idioma);
		                 if(traduccion!=null){
				            Hibernate.initialize(traduccion.getArchivo());
		                 }
		            }
		        }
    		}
		} catch (HibernateException he) {
			throw new EJBException(he);
		}

    	return procedimiento;
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
            if (ficha != null) {
                FichaDelegate ficdel = DelegateUtil.getFichaDelegate();
                try {           
                	ficdel.indexBorraFicha(ficha.getId());
                	ficdel.indexInsertaFicha(ficha,null);   
                } catch (DelegateException e) {
                    log.error("Error indexando ficha", e);
                }                                                        
            }
            if (procedimiento != null) {
        		ProcedimientoDelegate pldel = DelegateUtil.getProcedimientoDelegate();
                try {  
                	pldel.indexBorraProcedimiento(procedimiento);
                	pldel.indexInsertaProcedimiento(procedimiento,null);
                    Actualizador.borrar(documento, procedimiento.getId());
                } catch (DelegateException e) {
                    log.error("Error indexando procedimiento", e);
                }                    	                    
            }                            
        } catch (HibernateException he) {
            throw new EJBException(he);     
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
     * Obtiene el archivo de un documento de trámite.
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
        	int valor_orden=0;
        	List doc_orden = new ArrayList();
        	
        	Iterator it = map.entrySet().iterator();
        	while (it.hasNext()) {
        		Map.Entry e = (Map.Entry)it.next();
        	
            	String paramName = e.getKey().toString();
            	if (paramName.startsWith("orden_doc")) {
            		id=  Long.valueOf(paramName.substring(9)).longValue();
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
            
            Long contador= Long.parseLong("1");
        	
        	Iterator itdoc=doc_orden.iterator();
    		Documento doc=null;
    		while (itdoc.hasNext()) {
    			doc=(Documento)itdoc.next();
    			doc.setOrden(contador);
    			contador+=1;
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
			Long x1=new Long (((Documento)o1).getOrden());
			Long x2=new Long (((Documento)o2).getOrden());
			return x1.compareTo( x2 ); 
		}
	}
}
