package org.ibit.rol.sac.model.types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import java.sql.*;
import java.util.Arrays;


/**
 * Tipus per manetjar objectes binaris amb qualsevol base de
 * dades amb una implementacio decent de Blob.
 */
public class BinaryBlobType implements UserType {

    public int[] sqlTypes() {
        return new int[]{Types.BLOB};
    }

    public Class returnedClass() {
        return byte[].class;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        return (x == y) || (x != null && y != null && Arrays.equals((byte[]) x, (byte[]) y));
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, Object o) throws HibernateException, SQLException {
        Statement statement = resultSet.getStatement();
		String productName = getDatabaseProductName(statement);
        
        // Codi específic per bytea de Postgresql
        return resultSet.getBytes(names[0]);
    }



    public void nullSafeSet(PreparedStatement pst, Object value, int i) throws HibernateException, SQLException {
        String productName = getDatabaseProductName(pst);

        if (value == null) {
            pst.setNull(i, sqlTypes()[0]);
            return;
        }

        // Codi genèric, funciona amb PostgreSQL, per exemple.
        pst.setBytes(i, (byte[]) value);

    }


    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) return null;

        byte[] bytes = (byte[]) value;
        byte[] result = new byte[bytes.length];
        System.arraycopy(bytes, 0, result, 0, bytes.length);

        return result;
    }

    public boolean isMutable() {
        return false;
    }

    private String getDatabaseProductName(Statement statement)
    throws SQLException {
    	return statement.getConnection().getMetaData().getDatabaseProductName();
    }
}