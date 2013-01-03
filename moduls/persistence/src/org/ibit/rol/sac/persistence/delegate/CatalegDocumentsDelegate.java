package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.persistence.intf.CatalegDocumentsFacade;
import org.ibit.rol.sac.persistence.intf.CatalegDocumentsFacadeHome;
import org.ibit.rol.sac.persistence.util.CatalegDocumentsFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Business delegate para manipular el catalogo de documentos
 */
public class CatalegDocumentsDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long gravarDocumentCataleg(CatalegDocuments docCataleg) throws DelegateException {
        try {
            return getFacade().gravarDocumentCataleg(docCataleg);
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
    
    public ResultadoBusqueda llistarCatalegDocuments(int pagina, int resultats) throws DelegateException {
      try {
        return getFacade().llistarCatalegDocuments(pagina,resultats);
      } catch (RemoteException e) {
        throw new DelegateException(e);
      }
    }
    
    public List<CatalegDocuments> llistarCatalegDocuments() throws DelegateException {
        try {
            return getFacade().llistarCatalegDocuments();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List cercarCatalegDocuments(Map parametros, Map traduccion) throws DelegateException {
        try {
            return getFacade().cercarCatalegDocuments(parametros, traduccion);
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

   
    
    public ResultadoBusqueda cercarDocumentsCatalegAmbMultiidioma(Map parametros, Map traduccion, Long idExcepcio, String pagina, String resultats) throws DelegateException {
        try {
            return getFacade().cercarDocumentsCatalegAmbMultiidioma(parametros, traduccion,idExcepcio, pagina, resultats);
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

}
