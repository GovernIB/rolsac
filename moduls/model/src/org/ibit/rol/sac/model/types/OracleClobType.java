package org.ibit.rol.sac.model.types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 
 * @deprecated see {@link StringClobType}
 */
public class OracleClobType implements UserType
{
    protected static Log log = LogFactory.getLog(OracleClobType.class);

    /** Name of the oracle driver -- used to support Oracle clobs as a special case */
    private static final String ORACLE_DRIVER_NAME = "Oracle JDBC driver";

    /** Version of the oracle driver being supported with clob. */
    private static final int ORACLE_DRIVER_MAJOR_VERSION = 9;
    private static final int ORACLE_DRIVER_MINOR_VERSION = 0;

    /**
     * @see net.sf.hibernate.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
     */
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
        throws SQLException {
        //Get the clob field we are interested in from the result set 
        Clob clob = rs.getClob(names[0]);

        return ((clob == null) ? null : clob.getSubString(1, (int) clob.length()));
    }

    /**
     * oracleClasses independent (at compile time); based on http://forum.hibernate.org/viewtopic.php?p=2173150,
     * changes: changed line:  Connection conn = ps.getConnection(); to: Connection conn = dbMetaData.getConnection();
     *
     * @see net.sf.hibernate.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
     */
    public void nullSafeSet(PreparedStatement ps, Object value, int index)
        throws SQLException, HibernateException {
        DatabaseMetaData dbMetaData = ps.getConnection().getMetaData();
        log.debug(dbMetaData.getDriverName());
        log.debug(dbMetaData.getDriverMajorVersion() + " " + dbMetaData.getDriverMinorVersion());
        log.debug(dbMetaData.getConnection().getClass().getName());

        if (value == null) {
            ps.setNull(index, sqlTypes()[0]);
        } else if (ORACLE_DRIVER_NAME.equals(dbMetaData.getDriverName())) {
            if ((dbMetaData.getDriverMajorVersion() >= ORACLE_DRIVER_MAJOR_VERSION) &&
                    (dbMetaData.getDriverMinorVersion() >= ORACLE_DRIVER_MINOR_VERSION)) {
                try {
                    // Code compliments of Scott Miller 
                    // support oracle clobs without requiring oracle libraries 
                    // at compile time 
                    // Note this assumes that if you are using the Oracle Driver. 
                    // then you have access to the oracle.sql.CLOB class 
                    // First get the oracle clob class 
                    Class oracleClobClass = Class.forName("oracle.sql.CLOB");

                    // Get the oracle connection class for checking 
                    Class oracleConnectionClass = Class.forName("oracle.jdbc.OracleConnection");

                    // now get the static factory method 
                    Class[] partypes = new Class[3];
                    partypes[0] = Connection.class;
                    partypes[1] = Boolean.TYPE;
                    partypes[2] = Integer.TYPE;

                    Method createTemporaryMethod = oracleClobClass.getDeclaredMethod("createTemporary", partypes);

                    // now get ready to call the factory method 
                    Field durationSessionField = oracleClobClass.getField("DURATION_SESSION");
                    Object[] arglist = new Object[3];

                    //changed from: Connection conn = ps.getConnection();
                    Connection conn = dbMetaData.getConnection();

                    // Make sure connection object is right type 
                    if (!oracleConnectionClass.isAssignableFrom(conn.getClass())) {
                        throw new HibernateException("JDBC connection object must be a oracle.jdbc.OracleConnection. " +
                            "Connection class is " + conn.getClass().getName());
                    }

                    arglist[0] = conn;
                    arglist[1] = Boolean.TRUE;
                    arglist[2] = durationSessionField.get(null); //null is valid because of static field 

                    // Create our CLOB 
                    Object tempClob = createTemporaryMethod.invoke(null, arglist); //null is valid because of static method 

                    // get the open method 
                    partypes = new Class[1];
                    partypes[0] = Integer.TYPE;

                    Method openMethod = oracleClobClass.getDeclaredMethod("open", partypes);

                    // prepare to call the method 
                    Field modeReadWriteField = oracleClobClass.getField("MODE_READWRITE");
                    arglist = new Object[1];
                    arglist[0] = modeReadWriteField.get(null); //null is valid because of static field 

                    // call open(CLOB.MODE_READWRITE); 
                    openMethod.invoke(tempClob, arglist);

                    // get the getCharacterOutputStream method 
                    Method getCharacterOutputStreamMethod = oracleClobClass.getDeclaredMethod("getCharacterOutputStream",
                            null);

                    // call the getCharacterOutpitStream method 
                    Writer tempClobWriter = (Writer) getCharacterOutputStreamMethod.invoke(tempClob, null);

                    // write the string to the clob 
                    tempClobWriter.write((String) value);
                    tempClobWriter.flush();
                    tempClobWriter.close();

                    // get the close method 
                    Method closeMethod = oracleClobClass.getDeclaredMethod("close", null);

                    // call the close method 
                    closeMethod.invoke(tempClob, null);

                    // add the clob to the statement 
                    ps.setClob(index, (Clob) tempClob);

                    LobCleanUpInterceptor.registerTempLobs(tempClob);
                } catch (ClassNotFoundException e) {
                    // could not find the class with reflection 
                    throw new HibernateException("Unable to find a required class.\n" + e.getMessage());
                } catch (NoSuchMethodException e) {
                    // could not find the metho with reflection 
                    throw new HibernateException("Unable to find a required method.\n" + e.getMessage());
                } catch (NoSuchFieldException e) {
                    // could not find the field with reflection 
                    throw new HibernateException("Unable to find a required field.\n" + e.getMessage());
                } catch (IllegalAccessException e) {
                    throw new HibernateException("Unable to access a required method or field.\n" + e.getMessage());
                } catch (InvocationTargetException e) {
                    throw new HibernateException(e.getMessage());
                } catch (IOException e) {
                    throw new HibernateException(e.getMessage());
                }
            } else {
                throw new HibernateException("No CLOBS support. Use driver version " + ORACLE_DRIVER_MAJOR_VERSION +
                    ", minor " + ORACLE_DRIVER_MINOR_VERSION);
            }
        } else {
            String str = (String) value;
            StringReader r = new StringReader(str);
            ps.setCharacterStream(index, r, str.length());
        }
    }

    public Object deepCopy(Object value) {
        if (value == null) {
            return null;
        }

        return new String((String) value);
    }
    public boolean isMutable() {
        return false;
    }
    public int[] sqlTypes() {
        return new int[] { Types.CLOB };
    }
    public Class returnedClass() {
        return String.class;
    }
    public boolean equals(Object x, Object y) {
        return ObjectUtils.equals(x, y);
    }

}
