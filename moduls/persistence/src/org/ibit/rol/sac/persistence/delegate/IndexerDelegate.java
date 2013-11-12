package org.ibit.rol.sac.persistence.delegate;

import java.util.Date;

import org.ibit.lucene.indra.model.IndexResultados;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.persistence.intf.IndexerFacadeLocal;
import org.ibit.rol.sac.persistence.intf.IndexerFacadeLocalHome;
import org.ibit.rol.sac.persistence.util.IndexerFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.naming.NamingException;

/**
 * Business delegate para manipular el indice.
 */
public class IndexerDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    public synchronized void insertaObjeto(IndexObject indexObject, String idi) throws DelegateException {
        try {
            local.insertaObjeto(indexObject, idi);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public synchronized void borrarObjeto(String id, String idi) throws DelegateException {
        try {
            local.borrarObjeto(id, idi);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public synchronized void borrarObjetosDependientes(String id, String idi) throws DelegateException {
        try {
            local.borrarObjetosDependientes(id, idi);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public synchronized void indexarObjeto(Object objeto) throws DelegateException {
        try {
            local.indexarObjeto(objeto);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public synchronized void desindexarObjeto(Object objeto) throws DelegateException {
        try {
            local.desindexarObjeto(objeto);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public synchronized void optimizar(String idi) throws DelegateException {
        try {
            local.optimizar(idi);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public synchronized void confeccionaDiccionario(String idi) throws DelegateException {
        try {
            local.confeccionaDiccionario(idi);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public synchronized Long[] buscarIds(String className, String text) throws DelegateException {
        try {
            return local.buscarIds(className, text);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public void reindexarProcedimentos() throws DelegateException {
        try {
            local.reindexarProcedimentos();
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public void reindexarNormativas() throws DelegateException {
        try {
            local.reindexarNormativas();
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public void reindexarFichas() throws DelegateException {
        try {
            local.reindexarFichas();
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public void reindexarUOs() throws DelegateException {
        try {
            local.reindexarUOs();
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public void reindexarUOsPMA() throws DelegateException {
        try {
            local.reindexarUOsPMA();
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public void reindexarFichasPMA() throws DelegateException {
        try {
            local.reindexarFichasPMA();
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public IndexResultados buscaravanzado(String cerca_totes, String cerca_alguna, String cerca_frase, String cerca_cap, String tipus, String uo, String mat, Date fini, Date ffin, String ajudes, String idi, boolean sugerir, boolean restringido) throws DelegateException {
        try {
            return local.buscaravanzado(cerca_totes, cerca_alguna, cerca_frase, cerca_cap, tipus, uo, mat, fini, ffin, ajudes, idi, sugerir, restringido);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    public void envioColaCrawler(String tipo,Ficha ficha) throws DelegateException {
        try {
            local.envioColaCrawler(tipo,ficha);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private IndexerFacadeLocal local;
    
    protected IndexerDelegate() throws DelegateException {
        try {
            IndexerFacadeLocalHome home = IndexerFacadeUtil.getLocalHome();
            local = home.create();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (EJBException e) {
            throw new DelegateException(e);
        }
    }
    
}
