package test.unitario.persistence.mock;

import java.sql.Connection;

import es.caib.rolsac.persistence.utils.ConnectionInfo;

public class MockConnectionInfo implements ConnectionInfo {

	public int getConnectionID(Connection connection) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String databaseProductVersion(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}
	
}