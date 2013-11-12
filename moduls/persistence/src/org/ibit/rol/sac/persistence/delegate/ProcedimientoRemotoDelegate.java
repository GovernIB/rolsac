package org.ibit.rol.sac.persistence.delegate;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.persistence.intf.ProcedimientoRemotoFacade;
import org.ibit.rol.sac.persistence.intf.ProcedimientoRemotoFacadeHome;
import org.ibit.rol.sac.persistence.util.ProcedimientoRemotoFacadeUtil;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.DocumentoRemoto;
import org.ibit.rol.sac.model.Documento;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.util.Set;
import java.util.List;
import java.rmi.RemoteException;

/**
 * Business delegate para manipular procedimientos.
 */
public class ProcedimientoRemotoDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	public Long grabarProcedimientoRemoto(ProcedimientoRemoto procedimientoRemoto, String[] ceMaterias, String[] ceHechos) throws DelegateException{
		try {
            return getFacade().grabarProcedimientoRemoto(procedimientoRemoto,ceMaterias,ceHechos);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public Long grabarProcedimientoRemoto(final String idRemoto, ProcedimientoRemoto procedimientoRemoto, String[] ceMaterias, String[] ceHechos) throws DelegateException{
		try {
            return getFacade().grabarProcedimientoRemoto(idRemoto, procedimientoRemoto,ceMaterias,ceHechos);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public ProcedimientoRemoto obtenerProcedimientoRemoto(final String idRemoto, final Long idExtProcedimiento) throws DelegateException{
		try {
            return getFacade().obtenerProcedimientoRemoto(idRemoto,idExtProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public ProcedimientoRemoto obtenerProcedimientoRemoto(final Long idExtProcedimiento, final Long idAdmin) throws DelegateException{
		try {
            return getFacade().obtenerProcedimientoRemoto(idExtProcedimiento,idAdmin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public void borrarProcedimientoRemoto(final String idRemoto, final Long idExtProcedimiento) throws DelegateException{
		try {
            getFacade().borrarProcedimientoRemoto(idRemoto,idExtProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
    public void indexInsertaProcedimientoRemoto(ProcedimientoRemoto proc, ModelFilterObject filter) throws DelegateException {
        try {
            getFacade().indexInsertaProcedimientoRemoto(proc, filter);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
	public Iniciacion obtenerIniciacion(final String ceIniciacion)
    throws DelegateException {
        try {
            return getFacade().obtenerIniciacion(ceIniciacion);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public DocumentoRemoto obtenerDocumentoRemoto(Long idExterno,Long idAdmin)  throws DelegateException{
		try {
            return getFacade().obtenerDocumentoRemoto(idExterno, idAdmin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

    public DocumentoRemoto obtenerDocumentoRemoto(final String idRemoto, final Long idExtDoc)  throws DelegateException{
		try {
            return getFacade().obtenerDocumentoRemoto(idRemoto,idExtDoc);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

     public Long grabarDocumentoRemoto(DocumentoRemoto documento, Long procedimiento_id, Long ficha_id)  throws DelegateException{
		try {
            return getFacade().grabarDocumentoRemoto(documento, procedimiento_id, ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

    public void borrarDocumentoRemoto(final String idRemoto ,final Long idExt)  throws DelegateException{
        try {
            getFacade().borrarDocumentoRemoto(idRemoto,idExt);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }



    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private ProcedimientoRemotoFacade getFacade() throws RemoteException {
        return (ProcedimientoRemotoFacade) facadeHandle.getEJBObject();
    }

    protected ProcedimientoRemotoDelegate() throws DelegateException {
        try {
            ProcedimientoRemotoFacadeHome home = ProcedimientoRemotoFacadeUtil.getHome();
            ProcedimientoRemotoFacade remote = home.create();
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
