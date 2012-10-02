package es.caib.rolsac.oracle.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Arrays;

import es.caib.rolsac.persistence.hibernate.LobCleanUpInterceptor;

/**
 * Tipus per manetjar objectes binaris amb codi especific per BLOBs d'Oracle.
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
        
            Blob blob = resultSet.getBlob(names[0]);
            if (blob == null) return null;
            return blob.getBytes(1, (int) blob.length());
    }



    public void nullSafeSet(PreparedStatement pst, Object value, int i) throws HibernateException, SQLException {
        String productName = getDatabaseProductName(pst);

        if (value == null) {
            pst.setNull(i, sqlTypes()[0]);
            return;
        }

        Blob blob = createAndRegisterTemporalBlob(value, pst);
        pst.setBlob(i, blob);
    }

	private Blob createAndRegisterTemporalBlob(
			Object value,
			PreparedStatement pst) throws SQLException {
		
		//Es crea un BLOB temporal. Per la documentació (*) entenc que en el contexte de
		//una operació INSERT, aquest BLOB temporal es fa permanent. 
		
		// (*) http://docs.oracle.com/cd/A97630_01/appdev.920/a96584/oci07lob.htm#433719
	    // To make a temporary LOB permanent, the application can use OCILobCopy() 
		// to copy the data from the temporary LOB into a permanent one. 
		// The application can also use the temporary LOB in the VALUES clause of an INSERT 
		// statement, use the temporary LOB as the source of the assignment in an UPDATE 
		// statement, or assign the temporary LOB to a persistent LOB attribute and 
		// the flush the object.

		
		// Codi especific Oracle
		DatabaseMetaData metaData = pst.getConnection().getMetaData();
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
		return blob;
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