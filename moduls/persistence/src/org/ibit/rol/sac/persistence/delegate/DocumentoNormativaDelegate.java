package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.DocumentoNormativa;
import org.ibit.rol.sac.persistence.intf.DocumentoNormativaFacade;
import org.ibit.rol.sac.persistence.intf.DocumentoNormativaFacadeHome;
import org.ibit.rol.sac.persistence.util.DocumentoNormativaFacadeUtil;

/**
 * Business delegate para manipular Documento Normativa
 */
public class DocumentoNormativaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

	private static final long serialVersionUID = 1L;

	/**
	 * Obtener documento normativa.
	 * @param id
	 * @return
	 * @throws DelegateException
	 */
	public DocumentoNormativa obtenerDocumentoNormativa(Long id) throws DelegateException {
        try {
           return getFacade().obtenerDocumentoNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	/**
	 * Borrar documento. 
	 * @param id
	 * @throws DelegateException
	 */
	public void borrarDocumentoNormativa(Long id) throws DelegateException {
        try {
            getFacade().borrarDocumentoNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	/**
	 * Grabar documento. 
	 * @param doc
	 * @param idNormativa
	 * @return
	 * @throws DelegateException
	 */
	public Long grabarDocument(DocumentoNormativa doc, Long idNormativa) throws DelegateException {
		try {
	        return getFacade().grabarDocument(doc, idNormativa);
	     } catch (RemoteException e) {
	         throw new DelegateException(e);
	     }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private DocumentoNormativaFacade getFacade() throws RemoteException {
        return (DocumentoNormativaFacade) facadeHandle.getEJBObject();
    }
    
    
    
    protected DocumentoNormativaDelegate() throws DelegateException {
        try {
            DocumentoNormativaFacadeHome home = DocumentoNormativaFacadeUtil.getHome();
            DocumentoNormativaFacade remote = home.create();
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
