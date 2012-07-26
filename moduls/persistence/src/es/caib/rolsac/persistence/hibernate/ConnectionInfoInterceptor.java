package es.caib.rolsac.persistence.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Iterator;

import javax.ejb.SessionContext;
import javax.transaction.Transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.persistence.utils.ConnectionInfo;
import es.caib.rolsac.persistence.utils.JBossUtils;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.Session;
import net.sf.hibernate.type.Type;

public class ConnectionInfoInterceptor implements SessionInterceptor {

	protected static Log log = LogFactory.getLog(ConnectionInfoInterceptor.class);
	ConnectionInfo connInfo;
	Session session;
	SessionContext ctx;
	
	
	public ConnectionInfoInterceptor(ConnectionInfo connInfo) {
		this.connInfo = connInfo;
	}
	

	public void postFlush(Iterator arg0) throws CallbackException {
		logConnectionId("postFlush");
	}


	public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2,
			Object[] arg3, String[] arg4, Type[] arg5) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object instantiate(Class arg0, Serializable arg1)
			throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}


	public Boolean isUnsaved(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public void onDelete(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		
	}


	public boolean onFlushDirty(Object arg0, Serializable arg1, Object[] arg2,
			Object[] arg3, String[] arg4, Type[] arg5) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean onSave(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}


	public void preFlush(Iterator arg0) throws CallbackException {
		logConnectionId("preFlush");
		
	}


	public void setSession(Session session) {
		this.session=session;
		
	}


	public void setSessionContext(SessionContext ctx) {
		this.ctx = ctx;
		
	}

	String getCallerName() {
		return ctx.getCallerPrincipal().getName();
	}
	

	private void logConnectionId(String callbackName) {
		int connId;
		try {
			Connection connection = session.connection();
			if(null==connection) {
				log.debug("["+callbackName+"] no hay conexion aun.");
				return;
			}
				
			connId = connInfo.getConnectionID(connection);
			
			if(ConnectionInfo.NO_CONNECTIONID == connId) {
				log.debug("["+callbackName+"] no hay conexion aun.");
				return;
			}
			String productVersion = connInfo.databaseProductVersion(connection);  
			Transaction tx = JBossUtils.getCurrentTransaction();
			String callerName = getCallerName();
			log.debug("["+callbackName+"] "+ 
								callerName+ " " + 
									tx + 
								" database " + productVersion + 
								" connection id = "+ connId);
		} catch (HibernateException e) {
			log.error("",e);
		}
	}

	

}