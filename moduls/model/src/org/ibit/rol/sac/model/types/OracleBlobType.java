package org.ibit.rol.sac.model.types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Arrays;

import org.apache.commons.lang.ObjectUtils;

/**
 * Implementació d'un tipus de dades BLOB per Oracle THIN.
 * @deprecated see {@link BinaryBlobType}
 */
public class OracleBlobType implements UserType {
    /**
     * Return the SQL type codes for the columns mapped by this type. 
     */
    public int[] sqlTypes() {
        return new int[] { Types.BLOB};
    }

    /**
     * The class returned by <tt>nullSafeGet()</tt>.
     */
    public Class returnedClass() {
        return String.class;
    }
    public boolean equals(Object x, Object y) {
        return ObjectUtils.equals(x, y);
    }

    /**
     * Retrieve an instance of the mapped class from a JDBC resultset.
     * Implementors should handle possibility of null values.
     */
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        /*
    	InputStream blobReader = rs.getBinaryStream(names[0]);
        if (blobReader == null) 
            return null;
        byte[] b = new byte[1024];

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            while ((blobReader.read(b)) != -1) 
                os.write(b);
        } catch (IOException e) {
            throw new SQLException(e.toString());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
            }
        }
        return os.toByteArray();
*/
    	
    	Blob blob = rs.getBlob(names[0]); 
    	if (blob == null) return null;
        return blob.getBytes(1, (int) blob.length()); 

    	
    }
    /**
     * Write an instance of the mapped class to a prepared statement.
Implementors
     * should handle possibility of null values. A multi-column type should
be written
     * to parameters starting from <tt>index</tt>.
     * 
     */
    public void nullSafeSet(PreparedStatement st, Object value, int index)
            throws HibernateException, SQLException {
        
        if (value == null) {
            st.setNull(index, sqlTypes()[0]);
            return;
        }
        
        try {
        	Connection conn = st.getConnection().getMetaData().getConnection();
            
            OutputStream tempBlobOutputStream = null;
            oracle.sql.BLOB tempBlob = oracle.sql.BLOB.createTemporary(conn, true,oracle.sql.BLOB.DURATION_SESSION);
            try {
                tempBlob.open(oracle.sql.BLOB.MODE_READWRITE);
                tempBlobOutputStream = tempBlob.getBinaryOutputStream();
                tempBlobOutputStream.write((byte[])value);
                tempBlobOutputStream.flush();
            } finally {
                if (tempBlobOutputStream != null)
                    tempBlobOutputStream.close();
                tempBlobOutputStream.close();
            }
            st.setBlob(index, (Blob) tempBlob);
        } catch (IOException e) {
            throw new HibernateException(e);
        }
    }
    /**
     * Return a deep copy of the persistent state, stopping at entities and at
     * collections.
     */
    public Object deepCopy(Object value) {
        return (byte[])value;
    }
    /**
     * Are objects of this type mutable?
     */
    public boolean isMutable() {
        return false;
    }

    /*
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
        Blob blob = resultSet.getBlob(names[0]);
        return (blob==null? null :blob.getBytes(1, (int) blob.length()));
    }

    public void nullSafeSet(PreparedStatement pst, Object value, int i) throws HibernateException, SQLException {
        Connection con = pst.getConnection().getMetaData().getConnection();
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
    }

    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) return null;

        byte[] bytes = (byte[]) value;
        byte[] result = new byte[bytes.length];
        System.arraycopy(bytes, 0, result, 0, bytes.length);

        return result;
    }

    public boolean isMutable() {
        return true;
    }
*/
	
	
	
	
}
