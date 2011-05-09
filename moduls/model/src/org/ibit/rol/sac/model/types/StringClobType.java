package org.ibit.rol.sac.model.types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
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
        String productName = resultSet.getStatement().getConnection().getMetaData().getDatabaseProductName();
        
        if ("Oracle".equals(productName)) {
            // Codi específic per Clobs
            Clob clob = resultSet.getClob(names[0]);
            if (clob == null) return null;
            if (clob.length() == 0) return null;
            return clob.getSubString(1, (int) clob.length());
        } else {
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
    }

    public void nullSafeSet(PreparedStatement pst, Object value, int i) throws HibernateException, SQLException {
        DatabaseMetaData metaData = pst.getConnection().getMetaData();
        String productName = metaData.getDatabaseProductName();

        if ((value == null) || value.equals("")) {
            pst.setNull(i, sqlTypes()[0]);
            return;
        }

        if ("Oracle".equals(productName) ) {
            // Codi especific Oracle
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
            pst.setClob(i, clob);
        } else {
            // Codi genèric, funciona amb PostgreSQL, per exemple.
            StringReader reader = new StringReader((String) value);
            pst.setCharacterStream(i, reader, ((String) value).length());
        }
    }

    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) return null;

        return new String((String)value);
    }

    public boolean isMutable() {
        return false;
    }

}