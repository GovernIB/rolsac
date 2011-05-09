package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Comentario;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.persistence.intf.ComentarioFacade;
import org.ibit.rol.sac.persistence.intf.ComentarioFacadeHome;
import org.ibit.rol.sac.persistence.util.ComentarioFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular Comentarios.
 */
public class ComentarioDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long comentarFicha(String motivo, String titulo, String contenido, Long idFicha,String idioma)
            throws DelegateException {
        try {
            return getFacade().comentarFicha(motivo, titulo, contenido, idFicha,idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Long comentarProcedimiento(String motivo, String titulo, String contenido, Long idProc,String idioma)
            throws DelegateException {
        try {
            return getFacade().comentarProcedimiento(motivo, titulo, contenido, idProc,idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarComentariosFicha(Long idFicha) throws DelegateException {
        try {
            return getFacade().listarComentariosFicha(idFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarComentariosFicha(Long idFicha, String motivo) throws DelegateException {
        try {
            return getFacade().listarComentariosFicha(idFicha, motivo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarComentariosFichaExceptoMotivo(Long idFicha, String motivo) throws DelegateException {
        try {
            return getFacade().listarComentariosFichaExceptoMotivo(idFicha, motivo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarComentariosProcedimiento(Long idProc) throws DelegateException {
        try {
            return getFacade().listarComentariosProcedimiento(idProc);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarComentariosProcedimiento(Long idProc, String motivo) throws DelegateException {
        try {
            return getFacade().listarComentariosProcedimiento(idProc, motivo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarComentariosProcedimientoExceptoMotivo(Long idProc, String motivo) throws DelegateException {
        try {
            return getFacade().listarComentariosProcedimientoExceptoMotivo(idProc, motivo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Comentario obtenerComentario(Long id) throws DelegateException {
        try {
            return getFacade().obtenerComentario(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarUltimosComentarios(int offset, int number) throws DelegateException {
        try {
            return getFacade().listarUltimosComentarios(offset, number);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarUltimosComentarios(String motivo, int offset, int number) throws DelegateException {
          try {
              return getFacade().listarUltimosComentarios(motivo, offset, number);
          } catch (RemoteException e) {
              throw new DelegateException(e);
          }
    }

    public Integer numeroComentarios() throws DelegateException {
        try {
            return getFacade().numeroComentarios();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Integer numeroComentarios(String motivo) throws DelegateException {
        try {
            return getFacade().numeroComentarios(motivo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }


    public void borrarComentario(Long id) throws DelegateException {
        try {
            getFacade().borrarComentario(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void subsanarComentario(Long id) throws DelegateException {
        try {
            getFacade().subsanarComentario(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    public void reportarErrorComentario(Long idFicha,String motivo, String titulo, String contenido,String idioma,String tipo) throws DelegateException {
        try {
            getFacade().reportarErrorComentario(idFicha,motivo,titulo,contenido,idioma,tipo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private ComentarioFacade getFacade() throws RemoteException {
        return (ComentarioFacade) facadeHandle.getEJBObject();
    }

    protected ComentarioDelegate() throws DelegateException {
        try {
            ComentarioFacadeHome home = ComentarioFacadeUtil.getHome();
            ComentarioFacade remote = home.create();
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
