package es.caib.rolsac.oracle.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import java.io.IOException;
import java.io.Writer;
import java.sql.*;

import es.caib.rolsac.persistence.hibernate.LobCleanUpInterceptor;

/**
 * Tipus per manetjar objectes de text llargs amb codi especific per Clobs d'Oracle
 * Pel demes hauria de funcionar amb qualsevol base de dades que tengui get/setCharacterStream
 */
public class StringClobType implements UserType {

    public int[] sqlTypes() {
        return new int[]{Types.CLOB};
    }

    public Class returnedClass() {
        return String.class;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        return (x == y) || (x != null && y != null && x.equals(y));
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, Object o) throws HibernateException, SQLException {
    	String productName = getDatabaseProductName(resultSet.getStatement());

    	if(!"Oracle".equals(productName))
    		return null;
    	
    	// Codi específic per Clobs
    	Clob clob = resultSet.getClob(names[0]);
    	if (clob == null) return null;
    	if (clob.length() == 0) return null;
    	return clob.getSubString(1, (int) clob.length());
    }

    public void nullSafeSet(PreparedStatement pst, Object value, int i) throws HibernateException, SQLException {
    	String productName = getDatabaseProductName(pst);

    	if(!"Oracle".equals(productName))
    		return;


    	if ((value == null) || value.equals("")) {
    		pst.setNull(i, sqlTypes()[0]);
    		return;
    	}

    	// Codi especific Oracle
    	Clob clob = createAndRegisterTemporalClob(value,pst);
    	pst.setClob(i, clob);
    }

	private Clob createAndRegisterTemporalClob(Object value,
			PreparedStatement pst) throws SQLException {
		DatabaseMetaData metaData = pst.getConnection().getMetaData();
		Connection con = metaData.getConnection();
		oracle.sql.CLOB clob = oracle.sql.CLOB.createTemporary(con, false, oracle.sql.CLOB.DURATION_SESSION);
		clob.open(oracle.sql.BLOB.MODE_READWRITE);
		Writer writer = clob.getCharacterOutputStream();
		try {
		    writer.write((String)value);
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
		    throw new SQLException("Error escrivint al clob");
		}
		clob.close();
		LobCleanUpInterceptor.registerTempLobs(clob);
		return clob;
	}

    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) return null;

        return new String((String)value);
    }

    public boolean isMutable() {
        return false;
    }

    private String getDatabaseProductName(Statement statement)
    throws SQLException {
    	return statement.getConnection().getMetaData().getDatabaseProductName();
    }
}