package org.ibit.rol.sac.persistence.delegate;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.persistence.intf.IndexerFacade;
import org.ibit.rol.sac.persistence.intf.IndexerFacadeHome;
import org.ibit.rol.sac.persistence.util.IndexerFacadeUtil;

import es.caib.rolsac.lucene.model.IndexResultados;

/**
 * Business delegate para manipular el indice.
 */
public class IndexerDelegate implements StatelessDelegate {

	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public synchronized void insertaObjeto(IndexObject indexObject, String idi, IndexWriter writer) throws DelegateException {
        try {
            getFacade().insertaObjeto(indexObject, idi, writer);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public synchronized void borrarObjeto(String id, String idi) throws DelegateException {
        try {
            getFacade().borrarObjeto(id, idi);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public synchronized void borrarObjetosDependientes(String id, String idi) throws DelegateException {
        try {
            getFacade().borrarObjetosDependientes(id, idi);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public synchronized void indexarObjeto(Object objeto) throws DelegateException {
        try {
            getFacade().indexarObjeto(objeto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public synchronized void desindexarObjeto(Object objeto) throws DelegateException {
        try {
            getFacade().desindexarObjeto(objeto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public synchronized void optimizar(List<String> langs) throws DelegateException, IOException {
        try {
            getFacade().optimizar(langs);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public synchronized void confeccionaDiccionario(String idi) throws DelegateException {
        try {
            getFacade().confeccionaDiccionario(idi);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public synchronized Long[] buscarIds(String className, String text) throws DelegateException {
        try {
            return getFacade().buscarIds(className, text);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void reindexarProcedimentos() throws DelegateException {
        try {
            getFacade().reindexarProcedimentos();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void reindexarNormativas() throws DelegateException {
        try {
            getFacade().reindexarNormativas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void reindexarFichas() throws DelegateException {
        try {
            getFacade().reindexarFichas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void reindexarUOs() throws DelegateException {
        try {
            getFacade().reindexarUOs();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void reindexarUOsPMA() throws DelegateException {
        try {
            getFacade().reindexarUOsPMA();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void reindexarFichasPMA() throws DelegateException {
        try {
            getFacade().reindexarFichasPMA();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void envioColaCrawler(String tipo, Ficha ficha) throws DelegateException {
        try {
            getFacade().envioColaCrawler(tipo, ficha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Directory getHibernateDirectory(String idi) throws DelegateException, IOException {
        try {
            return getFacade().getHibernateDirectory(idi);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public IndexResultados buscarAvanzado(String buscarTodas, String buscarAlguna, String buscarFrase, String buscarNinguna, String tipos, String uo, String materia, Date fechaInicio, Date fechaFin, String ayudas, String idioma, boolean sugerir, boolean restringido) throws DelegateException {
        try {
            return getFacade().buscarAvanzado(buscarTodas, buscarAlguna, buscarFrase, buscarNinguna, tipos, uo, materia, fechaInicio, fechaFin, ayudas, idioma, sugerir, restringido);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private IndexerFacade getFacade() throws RemoteException {
        return (IndexerFacade) facadeHandle.getEJBObject();
    }

    protected IndexerDelegate() throws DelegateException {
        try {
            IndexerFacadeHome home = IndexerFacadeUtil.getHome();
            IndexerFacade remote = home.create();
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
