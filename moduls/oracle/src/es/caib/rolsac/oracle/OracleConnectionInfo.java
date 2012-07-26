package es.caib.rolsac.oracle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.persistence.utils.ConnectionInfo;



public class OracleConnectionInfo implements ConnectionInfo {

    protected static Log log = LogFactory.getLog(OracleConnectionInfo.class);

	public int getConnectionID(Connection connection) {
		   try {
	        	
	        	oracle.jdbc.OracleConnection oracleConn = (oracle.jdbc.OracleConnection)connection.getClass().getMethod("getUnderlyingConnection").invoke(connection);
	        	Field  sessionIdField = oracleConn.getClass().getDeclaredField("sessionId");
	        	sessionIdField.setAccessible(true);
	        	int sessionId = (Integer) sessionIdField.get(oracleConn);
	        	return sessionId;
	        	
			} catch (IllegalArgumentException e) {
				log.error("",e);
			} catch (SecurityException e) {
				log.error("",e);
			} catch (IllegalAccessException e) {
				log.error("",e);
			} catch (InvocationTargetException e) {
				log.error("",e);			
			} catch (NoSuchMethodException e) {
				log.error("",e);			
			} catch (NoSuchFieldException e) {
				log.error("",e);			
			}
			return NO_CONNECTIONID;
	}

	public String databaseProductVersion(Connection connection) {
		//TODO 
		return "Oracle";
	}

}
