package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.CatalegDocumentsFacade;
import org.ibit.rol.sac.persistence.intf.CatalegDocumentsFacadeHome;
import org.ibit.rol.sac.persistence.util.CatalegDocumentsFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular el catalogo de documentos
 */
public class CatalegDocumentsDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long gravarDocumentCataleg(CatalegDocuments documentoCatalogo) throws DelegateException {
        try {
            return getFacade().gravarDocumentCataleg(documentoCatalogo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public CatalegDocuments obtenirDocumentoCataleg(Long id) throws DelegateException {
        try {
            return getFacade().obtenirDocumentoCataleg(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<CatalegDocuments> llistarCatalegDocuments() throws DelegateException {
    	try {
    		return getFacade().listarCatalogoDocumentos();
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public void esborrarDocumentCataleg(Long id) throws DelegateException {
        try {
            getFacade().esborrarDocumentCataleg(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public ResultadoBusqueda cercarDocumentsCatalegAmbMultiidioma(String descripcionBusqueda, Long administracionResponsable, Long excepcionDocumento , Integer pagina , Integer resultados, String idioma) throws DelegateException {
        try {
            return getFacade().cercarDocumentsCatalegAmbMultiidioma(descripcionBusqueda, administracionResponsable, excepcionDocumento, pagina, resultados, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /*=========================================================*/
    /*======================== REFERENCIA AL FACADE ==============*/
    /*=========================================================*/

    private Handle facadeHandle;

    private CatalegDocumentsFacade getFacade() throws RemoteException {
        return (CatalegDocumentsFacade) facadeHandle.getEJBObject();
    }

    protected CatalegDocumentsDelegate() throws DelegateException {
        try {
        	CatalegDocumentsFacadeHome home = CatalegDocumentsFacadeUtil.getHome();
        	CatalegDocumentsFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public ResultadoBusqueda consultaCatalegDocuments(FiltroGenerico filtro) throws DelegateException {
	       try {
	            return getFacade().consultaCatalegDocuments(filtro);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	}
    
}
