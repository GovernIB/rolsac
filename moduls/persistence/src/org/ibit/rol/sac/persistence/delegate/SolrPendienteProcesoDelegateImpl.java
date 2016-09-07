package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.persistence.intf.SolrPendienteProcesoFacade;
import org.ibit.rol.sac.persistence.intf.SolrPendienteProcesoFacadeHome;
import org.ibit.rol.sac.persistence.util.SolrPendienteProcesoFacadeUtil;
import org.quartz.SchedulerException;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate pera consultar idiomas.
 */
public class SolrPendienteProcesoDelegateImpl extends SolrPendienteDelegate implements  StatelessDelegate, SolrPendienteProcesoDelegateI {

	
    private long timeLen = 0L;

    // Cache de lenguaje por defecto
    private String porDefecto = null;
    private long timeDef = 0L;

    private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {

        return ((System.currentTimeMillis() - time) > maxtime);
    } 
    
    
    private Handle facadeHandle;

    private SolrPendienteProcesoFacade getFacade() throws RemoteException {
        return (SolrPendienteProcesoFacade) facadeHandle.getEJBObject();
    }

    protected SolrPendienteProcesoDelegateImpl() throws DelegateException {
        try {
        	SolrPendienteProcesoFacadeHome home = SolrPendienteProcesoFacadeUtil.getHome();
        	SolrPendienteProcesoFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#indexarTodo()
	 */
    public void indexarTodoFicha(final SolrPendienteJob solrPendienteJob) throws DelegateException {
    	try {
             getFacade().indexarTodoFicha(solrPendienteJob);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#indexarTodo()
	 */
    public void indexarTodoProcedimiento(final SolrPendienteJob solrPendienteJob) throws DelegateException {
    	try {
             getFacade().indexarTodoProcedimiento(solrPendienteJob);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#indexarTodo()
	 */
    public void indexarTodoNormativa(final SolrPendienteJob solrPendienteJob) throws DelegateException {
    	try {
             getFacade().indexarTodoNormativa(solrPendienteJob);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#indexarTodo()
	 */
    public void indexarTodoTramite(final SolrPendienteJob solrPendienteJob) throws DelegateException {
    	try {
             getFacade().indexarTodoTramite(solrPendienteJob);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#indexarTodo()
	 */
    public void indexarTodoUA(final SolrPendienteJob solrPendienteJob) throws DelegateException {
    	try {
             getFacade().indexarTodoUA(solrPendienteJob);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#indexarPendientes()
	 */
    public Boolean indexarPendientes() throws DelegateException {

        try {
            return getFacade().indexarPendientes();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
}
