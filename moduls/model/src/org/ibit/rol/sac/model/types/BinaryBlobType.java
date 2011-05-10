package org.ibit.rol.sac.model.types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Arrays;

/**
 * Tipus per manetjar objectes binaris amb codi especific per BLOBs d'Oracle i
 * tipus BYTEA de PostgreSQL, pel demes hauria de funcionar amb qualsevol base de
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
        String productName = resultSet.getStatement().getConnection().getMetaData().getDatabaseProductName();
        
        if ("PostgreSQL".equalsIgnoreCase(productName)) {
            // Codi específic per bytea de Postgresql
            return resultSet.getBytes(names[0]);
        } else {
            Blob blob = resultSet.getBlob(names[0]);
            if (blob == null) return null;
            return blob.getBytes(1, (int) blob.length());
        }
    }

    public void nullSafeSet(PreparedStatement pst, Object value, int i) throws HibernateException, SQLException {
        DatabaseMetaData metaData = pst.getConnection().getMetaData();
        String productName = metaData.getDatabaseProductName();

        if (value == null) {
            pst.setNull(i, sqlTypes()[0]);
            return;
        }

        if ("Oracle".equals(productName) ) {                    	
            // Codi especific Oracle
            Connection con = metaData.getConnection();
            oracle.sql.BLOB blob = oracle.sql.BLOB.createTemporary(con, false, oracle.sql.BLOB.DURATION_SESSION);
            blob.open(oracle.sql.BLOB.MODE_READWRITE);
            OutputStream os = blob.getBinaryOutputStream();
            try {
                os.write((byte[]) value);
                os.flush();
                os.close();
            } catch (IOException e) {
                throw new SQLException("Error escrivint al blob");
            }
            blob.close();
            LobCleanUpInterceptor.registerTempLobs(blob);
            pst.setBlob(i, blob);
            
        } else {
            // Codi genèric, funciona amb PostgreSQL, per exemple.
            pst.setBytes(i, (byte[]) value);
        }
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

}