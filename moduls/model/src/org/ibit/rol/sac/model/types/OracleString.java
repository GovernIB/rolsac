package org.ibit.rol.sac.model.types;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 24-jul-2007
 * Time: 11:51:40
 * To change this template use File | Settings | File Templates.
 */
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * This class allows strings to be stored in an escaped form, so that they will never be
 * automatically converted to NULL values by the database, should they be empty.
 * Note that this class will not allow you to use NULL value strings when they are not allowed by
 * Hibernate (such as in Maps).
 *
 *
 * @author Erik Visser, Chess-iT B.V.
 *
 */
public class OracleString implements UserType {

    private static final char QUOTING_CHAR = '\"';

    private static final int[] TYPES = new int[] { Types.VARCHAR };

    /**
     * @see net.sf.hibernate.UserType#sqlTypes()
     */
    public int[] sqlTypes() {
        return TYPES;
    }

    /**
     * @see net.sf.hibernate.UserType#returnedClass()
     */
    public Class returnedClass() {
        return String.class;
    }

    /**
     * @see net.sf.hibernate.UserType#equals(java.lang.Object, java.lang.Object)
     */
    public boolean equals(Object x, Object y) throws HibernateException {
        if ( x == y ) {
            return true;
        }
        if ( x == null ) {
            return false;
        }
        return x.equals(y);
    }

    /**
     * @see net.sf.hibernate.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
     */
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
        throws HibernateException, SQLException {

        String dbValue = (String) Hibernate.STRING.nullSafeGet(rs, names[0]);

        if ( dbValue != null ) {
            return unescape(dbValue);
        }
        else {
            return null;
        }

    }

    /**
     * @see net.sf.hibernate.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
     */
    public void nullSafeSet(PreparedStatement st, Object value, int index)
        throws HibernateException, SQLException {

        if ( value != null ) {
            String v = escape((String) value);
            Hibernate.STRING.nullSafeSet(st, v, index);
        }
        else {
            Hibernate.STRING.nullSafeSet(st, value, index);
        }

    }


    /**
     * @see net.sf.hibernate.UserType#deepCopy(java.lang.Object)
     */
    public Object deepCopy(Object value) throws HibernateException {
        if ( value == null ) {
            return null;
        }
        else {
            return new String((String)value);
        }
    }

    /**
     * @see net.sf.hibernate.UserType#isMutable()
     */
    public boolean isMutable() {
        return false;
    }

    /**
     * Escape a string by quoting the string.
     */
    private String escape(String string) {
        return QUOTING_CHAR + string + QUOTING_CHAR;
    }


    /**
     * Unescape by removing the quotes
     */
    private Object unescape(String string) throws HibernateException {
        if ( !(string.charAt(0) == QUOTING_CHAR ) ||
             !(string.charAt(string.length() -1 ) == QUOTING_CHAR )) {
                throw new HibernateException("Persistent storage of " +
            OracleString.class.getName() +
            " corrupted, database contained string:" + string);
        }

        return string.substring(1, string.length() - 1);
    }
}