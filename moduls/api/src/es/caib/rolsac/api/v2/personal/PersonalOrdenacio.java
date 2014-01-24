/**
 * PersonalOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.personal;

public class PersonalOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected PersonalOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _username_asc = "username_asc";
    public static final java.lang.String _username_desc = "username_desc";
    public static final java.lang.String _nombre_asc = "nombre_asc";
    public static final java.lang.String _nombre_desc = "nombre_desc";
    public static final java.lang.String _funciones_asc = "funciones_asc";
    public static final java.lang.String _funciones_desc = "funciones_desc";
    public static final java.lang.String _cargo_asc = "cargo_asc";
    public static final java.lang.String _cargo_desc = "cargo_desc";
    public static final java.lang.String _email_asc = "email_asc";
    public static final java.lang.String _email_desc = "email_desc";
    public static final PersonalOrdenacio id_asc = new PersonalOrdenacio(_id_asc);
    public static final PersonalOrdenacio id_desc = new PersonalOrdenacio(_id_desc);
    public static final PersonalOrdenacio username_asc = new PersonalOrdenacio(_username_asc);
    public static final PersonalOrdenacio username_desc = new PersonalOrdenacio(_username_desc);
    public static final PersonalOrdenacio nombre_asc = new PersonalOrdenacio(_nombre_asc);
    public static final PersonalOrdenacio nombre_desc = new PersonalOrdenacio(_nombre_desc);
    public static final PersonalOrdenacio funciones_asc = new PersonalOrdenacio(_funciones_asc);
    public static final PersonalOrdenacio funciones_desc = new PersonalOrdenacio(_funciones_desc);
    public static final PersonalOrdenacio cargo_asc = new PersonalOrdenacio(_cargo_asc);
    public static final PersonalOrdenacio cargo_desc = new PersonalOrdenacio(_cargo_desc);
    public static final PersonalOrdenacio email_asc = new PersonalOrdenacio(_email_asc);
    public static final PersonalOrdenacio email_desc = new PersonalOrdenacio(_email_desc);
    public java.lang.String getValue() { return _value_;}
    public static PersonalOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        PersonalOrdenacio enumeration = (PersonalOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static PersonalOrdenacio fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PersonalOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://personal.v2.api.rolsac.caib.es", "PersonalOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
