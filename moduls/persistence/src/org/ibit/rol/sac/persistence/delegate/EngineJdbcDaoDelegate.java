package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.intf.EngineJdbcDaoFacade;
import org.ibit.rol.sac.persistence.intf.EngineJdbcDaoFacadeHome;
import org.ibit.rol.sac.persistence.util.EngineJdbcDaoFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Business delegate para manipular normativas.
 */
public class EngineJdbcDaoDelegate implements StatelessDelegate
{
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	/**
	 * Inicializa el EJB con un datasource determinado.
	 * Es imprescindible inicializarlo. 
	 */
    public void init(String jndi) throws DelegateException {
        try {
            getFacade().init(jndi);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /**
  	 * Método para ejecutar SQL de inserci�n, actualizaci�n o borrado.  
     * Devuelve el n�mero de registros modificados o 0 en caso de error.
     * @param sql, String con la sentencia sql
     * @param datos, Vector con los par�metros que se meten en la sentencia.
     * @return int, numero de registros
     * @throws DelegateException
     */
    public int ejecuta(String sql, String[] datos) throws DelegateException {
        try {
            return getFacade().ejecuta(sql, datos);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /**
     * Método que devuelve el resultado de una sentencia "select count(*)".
     * Se debe pasar como parametro la consulta completa.
     * @param sql
     * @return String, numero resultado de ejecutar la sentencia.
     * @throws DelegateException
     */
    public String ejecutaCount(String sql) throws DelegateException {
        try {
            return getFacade().ejecutaCount(sql);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private EngineJdbcDaoFacade getFacade() throws RemoteException {
        return (EngineJdbcDaoFacade) facadeHandle.getEJBObject();
    }
    
    protected EngineJdbcDaoDelegate() throws DelegateException {
        try {
        	EngineJdbcDaoFacadeHome home = EngineJdbcDaoFacadeUtil.getHome();
        	EngineJdbcDaoFacade remote = home.create();
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
