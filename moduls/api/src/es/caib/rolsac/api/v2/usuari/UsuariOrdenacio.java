/**
 * UsuariOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.usuari;

public class UsuariOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected UsuariOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _username_asc = "username_asc";
    public static final java.lang.String _username_desc = "username_desc";
    public static final java.lang.String _nombre_asc = "nombre_asc";
    public static final java.lang.String _nombre_desc = "nombre_desc";
    public static final java.lang.String _observaciones_asc = "observaciones_asc";
    public static final java.lang.String _observaciones_desc = "observaciones_desc";
    public static final java.lang.String _perfil_asc = "perfil_asc";
    public static final java.lang.String _perfil_desc = "perfil_desc";
    public static final java.lang.String _email_asc = "email_asc";
    public static final java.lang.String _email_desc = "email_desc";
    public static final UsuariOrdenacio id_asc = new UsuariOrdenacio(_id_asc);
    public static final UsuariOrdenacio id_desc = new UsuariOrdenacio(_id_desc);
    public static final UsuariOrdenacio username_asc = new UsuariOrdenacio(_username_asc);
    public static final UsuariOrdenacio username_desc = new UsuariOrdenacio(_username_desc);
    public static final UsuariOrdenacio nombre_asc = new UsuariOrdenacio(_nombre_asc);
    public static final UsuariOrdenacio nombre_desc = new UsuariOrdenacio(_nombre_desc);
    public static final UsuariOrdenacio observaciones_asc = new UsuariOrdenacio(_observaciones_asc);
    public static final UsuariOrdenacio observaciones_desc = new UsuariOrdenacio(_observaciones_desc);
    public static final UsuariOrdenacio perfil_asc = new UsuariOrdenacio(_perfil_asc);
    public static final UsuariOrdenacio perfil_desc = new UsuariOrdenacio(_perfil_desc);
    public static final UsuariOrdenacio email_asc = new UsuariOrdenacio(_email_asc);
    public static final UsuariOrdenacio email_desc = new UsuariOrdenacio(_email_desc);
    public java.lang.String getValue() { return _value_;}
    public static UsuariOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        UsuariOrdenacio enumeration = (UsuariOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static UsuariOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(UsuariOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://usuari.v2.api.rolsac.caib.es", "UsuariOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
