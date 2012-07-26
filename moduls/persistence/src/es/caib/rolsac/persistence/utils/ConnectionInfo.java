package es.caib.rolsac.persistence.utils;

import java.sql.Connection;

public interface ConnectionInfo {

	public static final int NO_CONNECTIONID = -1; 
	
	public int getConnectionID(Connection connection);
	public String databaseProductVersion(Connection connection);

}