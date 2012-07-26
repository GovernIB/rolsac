package org.ibit.rol.sac.model.types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.*;


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

    	// Codi amb characterstream
    	Reader reader = resultSet.getCharacterStream(names[0]);
    	if (reader == null) return null;

    	StringBuffer text = new StringBuffer();
    	try {
    		char[] buffer = new char[4096];
    		int readed;
    		while ( (readed = reader.read(buffer)) != -1) {
    			text.append(buffer, 0, readed);
    		}
    		reader.close();
    	} catch (IOException e) {
    		throw new SQLException("Error llegint clob");
    	}
    	return text.toString();

    }

    public void nullSafeSet(PreparedStatement pst, Object value, int i) throws HibernateException, SQLException {
        
        String productName = getDatabaseProductName(pst);

        if ((value == null) || value.equals("")) {
        	pst.setNull(i, sqlTypes()[0]);
        	return;
        }

        // Codi genèric, funciona amb PostgreSQL, per exemple.
        StringReader reader = new StringReader((String) value);
        pst.setCharacterStream(i, reader, ((String) value).length());

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
