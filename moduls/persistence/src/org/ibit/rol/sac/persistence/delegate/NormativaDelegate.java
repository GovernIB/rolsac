package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.DocumentoNormativa;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.persistence.intf.NormativaFacade;
import org.ibit.rol.sac.persistence.intf.NormativaFacadeHome;
import org.ibit.rol.sac.persistence.util.NormativaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

/**
 * Business delegate para manipular normativas.
 */
public class NormativaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    public boolean autorizaCrearNormativa(Integer validacionNormativa) throws DelegateException {
        try {
            return getFacade().autorizaCrearNormativa(validacionNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    public boolean autorizaModificarNormativa(Long idNormativa) throws DelegateException {
        try {
            return getFacade().autorizaModificarNormativa(idNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Long grabarNormativa(Normativa norma, Long idUA) throws DelegateException {
        try {
            return getFacade().grabarNormativa(norma, idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Normativa obtenerNormativa(Long id) throws DelegateException {
        try {
            return getFacade().obtenerNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List buscarNormativas(Map parametros, Map traduccion, String tipo) throws DelegateException {
        try {
            return getFacade().buscarNormativas(parametros, traduccion, tipo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
	public ResultadoBusqueda buscarNormativas(Map parametros, Map traduccion, String tipo, Long idUA, boolean uaMeves, boolean uaFilles, String invalids, String campoOrdenacion, String orden, String pagina, String resultats, boolean soloIds) throws DelegateException {
        try {
            return getFacade().buscarNormativas(parametros, traduccion, tipo, idUA, uaMeves, uaFilles, invalids, campoOrdenacion, orden, pagina, resultats, soloIds);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    public List buscarNormativas(List<Long> ids) throws DelegateException {
        try {
            return getFacade().buscarNormativas(ids);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void anyadirAfectacion(Long normativaAfectada_id, Long tipafec_id, Long normativaQueAfecta_id) throws DelegateException {
        try {
            getFacade().anyadirAfectacion(normativaAfectada_id, tipafec_id, normativaQueAfecta_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void eliminarAfectacion(Long normativaQueAfecta_id, Long tipoAfec_id, Long normativaAeliminar_id) throws DelegateException {
        try {
            getFacade().eliminarAfectacion(normativaQueAfecta_id, tipoAfec_id, normativaAeliminar_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void anyadirProcedimiento(Long proc_id, Long norm_id) throws DelegateException {
        try {
            getFacade().anyadirProcedimiento(proc_id, norm_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Archivo obtenerArchivoNormativa(Long id, String lang, boolean useDefault) throws DelegateException {
        try {
            return getFacade().obtenerArchivoNormativa(id, lang, useDefault);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void borrarNormativa(Long id) throws DelegateException {
        try {
            getFacade().borrarNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
   	
   	public int buscarNormativasActivas(List<Long> llistaUnitatAdministrativaId) throws DelegateException {
   		try {
   			return getFacade().buscarNormativasActivas(llistaUnitatAdministrativaId);
   		} catch (RemoteException e) {
   			throw new DelegateException(e);
   		}
   	}
   	
    /**
     * Método para indexar en solr.
     * @param solrPendiente
     * @param solrIndexer
     * @throws DelegateException
     */
    public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
    	try {
            return getFacade().indexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /**
     * Método para indexar normativa documento en solr.
     * @param solrPendiente
     * @param solrIndexer
     * @throws DelegateException
     */
    public SolrPendienteResultado indexarSolrNormativaDocumento(SolrIndexer solrIndexer, Long idElemento, EnumCategoria categoria) throws DelegateException {
    	try {
            return getFacade().indexarSolrNormativaDocumento(solrIndexer, idElemento, categoria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /** 
     * Método para indexar normativa documento en solr.
     * @param solrIndexer
     * @param idElemento
     * @param categoria
     * @throws DelegateException
     */
    public SolrPendienteResultado indexarSolrNormativa(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) throws DelegateException {
    	try {
            return getFacade().indexarSolrNormativa(solrIndexer, idElemento, categoria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /**
     * Método para indexar en solr.
     * @param solrPendiente
     * @param solrIndexer
     * @throws DelegateException
     */
    public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
    	try {
            return getFacade().desindexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /**
     * Obtener documento normativa.
     * 
     * @param id
     * @return
     */
	public DocumentoNormativa obtenerDocumentoNormativa(Long id) throws DelegateException {
		try {
            return getFacade().obtenerDocumentoNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

   	
   	/* ========================================================= */
   	/* ======================== REFERENCIA AL FACADE  ========== */
   	/* ========================================================= */
   	
   	private Handle facadeHandle;
   	
   	private NormativaFacade getFacade() throws RemoteException {
        return (NormativaFacade) facadeHandle.getEJBObject();
    }
   	
    protected NormativaDelegate() throws DelegateException {
        try {
            NormativaFacadeHome home = NormativaFacadeUtil.getHome();
            NormativaFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /**
     * Buscar ids normativas.
     * @return
     * @throws DelegateException
     */
	public List<Long> buscarIdsNormativas() throws DelegateException{
		try {
			return getFacade().buscarIdsNormativas();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/**
	 * Reordenar documentos. 
	 * @param idNormativa
	 * @param idDocumentos
	 * @throws DelegateException
	 */
	public void reordenarDocumentos(final Long idNormativa, List<Long> idDocumentos) throws DelegateException {
		try {
             getFacade().reordenarDocumentos(idNormativa, idDocumentos);	
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

	/***
	 * Comprueba si el número de normativa es correcto. 
	 * @param normativa
	 * @return
	 */
	public boolean isNumNormativaCorrecto(Normativa normativa) throws DelegateException {
		try {
            return getFacade().isNumNormativaCorrecto(normativa);	
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}
   
}
