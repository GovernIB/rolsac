package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.DocumentoResumen;
import org.ibit.rol.sac.persistence.intf.DocumentoResumenFacade;
import org.ibit.rol.sac.persistence.intf.DocumentoResumenFacadeHome;
import org.ibit.rol.sac.persistence.util.DocumentoResumenFacadeUtil;

/**
 * Business delegate para manipular Documentos.
 */
@SuppressWarnings("deprecation")
public class DocumentoResumenDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MéTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	private static final long serialVersionUID = 1L;

	public DocumentoResumen obtenerDocumentoResumen(Long id) throws DelegateException {
        try {
            return getFacade().obtenerDocumentoResumen(id);
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
    
    public void actualizarOrdenDocs(Map map, List documentosABorrar) throws DelegateException {
        try {
            getFacade().actualizarOrdenDocs(map, documentosABorrar);
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
    
    private DocumentoResumenFacade getFacade() throws RemoteException {
        return (DocumentoResumenFacade) facadeHandle.getEJBObject();
    }
    
    protected DocumentoResumenDelegate() throws DelegateException {
        try {
            DocumentoResumenFacadeHome home = DocumentoResumenFacadeUtil.getHome();
            DocumentoResumenFacade remote = home.create();
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
