package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.persistence.intf.DocumentoFacade;
import org.ibit.rol.sac.persistence.intf.DocumentoFacadeHome;
import org.ibit.rol.sac.persistence.util.DocumentoFacadeUtil;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 12-jul-2007
 * Time: 9:22:36
 * To change this template use File | Settings | File Templates.
 */
public class DocumentoDelegateImpl implements  StatelessDelegate, DocumentoDelegateI
{
	/* ========================================================= */
    /* ===================== MÉTODOS DE NEGOCIO ================ */
    /* ========================================================= */
	
	public Long grabarDocumento(Documento documento, Long procedimiento_id, Long ficha_id) throws DelegateException {
        try {
            return getFacade().grabarDocumento(documento,procedimiento_id, ficha_id);
            		
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	public Documento obtenerDocumento(Long id) throws DelegateException {
        try {
            return getFacade().obtenerDocumento(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	public void borrarDocumento(Long id) throws DelegateException {
        try {
            getFacade().borrarDocumento(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	public Archivo obtenerArchivoDocumento(Long id, String lang, boolean useDefault) throws DelegateException {
		try {
			return getFacade().obtenerArchivoDocumento(id, lang, useDefault);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public Archivo obtenerArchivoDocumentoTramite(Long id, String lang, boolean useDefault) throws DelegateException {
        try {
            return getFacade().obtenerArchivoDocumentoTramite(id, lang, useDefault);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void actualizarOrdenDocs(Map map) throws DelegateException {
        try {
            getFacade().actualizarOrdenDocs(map);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public SolrPendienteResultado indexarSolr(SolrIndexer solrIndexer,
			SolrPendiente solrPendiente) throws DelegateException {
    	try {
            return getFacade().indexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

	public SolrPendienteResultado indexarSolrProcedimientoDoc(
			SolrIndexer solrIndexer, Long idElemento, EnumCategoria categoria)
			throws DelegateException {
		try {
			return getFacade().indexarSolrProcedimientoDoc(solrIndexer, idElemento, categoria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

	public SolrPendienteResultado indexarSolrFichaDoc(SolrIndexer solrIndexer,
			Long idElemento, EnumCategoria categoria) throws DelegateException {
		try {
            return getFacade().indexarSolrFichaDoc(solrIndexer, idElemento, categoria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
		catch(java.lang.reflect.UndeclaredThrowableException e) {
			throw new DelegateException(e);
		} catch (Exception e) {
			throw new DelegateException(e);
		}	
		

	}

	public SolrPendienteResultado desindexarSolr(SolrIndexer solrIndexer,
			SolrPendiente solrPendiente) throws DelegateException {
		try {
            return getFacade().desindexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private DocumentoFacade getFacade() throws RemoteException {
        return (DocumentoFacade) facadeHandle.getEJBObject();
    }

    protected DocumentoDelegateImpl() throws DelegateException {
        try {
        	DocumentoFacadeHome home = DocumentoFacadeUtil.getHome();
        	DocumentoFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public List<Long> obtenerDocumentosFichaSolr(final Long idFicha)
			throws DelegateException {
		try {
			return getFacade().obtenerDocumentosFichaSolr(idFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

	public List<Long> obtenerDocumentosProcedimientoSolr(
			final Long idProcedimiento)	throws DelegateException {
		
	try {
		return getFacade().obtenerDocumentosFichaSolr(idProcedimiento);		
    } catch (RemoteException e) {
        throw new DelegateException(e);
    }
	}

	
    
}
