package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.intf.DocumentoFacade;
import org.ibit.rol.sac.persistence.intf.DocumentoFacadeHome;
import org.ibit.rol.sac.persistence.util.DocumentoFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import java.rmi.RemoteException;
import java.util.Map;

/**
 * Business delegate para manipular Documentos.
 */
public class DocumentoDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
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
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private DocumentoFacade getFacade() throws RemoteException {
        return (DocumentoFacade) facadeHandle.getEJBObject();
    }

    protected DocumentoDelegate() throws DelegateException {
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
}
