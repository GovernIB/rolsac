/**
 * SeccioOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.seccio;

public class SeccioOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SeccioOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _codigoEstandard_asc = "codigoEstandard_asc";
    public static final java.lang.String _codigoEstandard_desc = "codigoEstandard_desc";
    public static final java.lang.String _perfil_asc = "perfil_asc";
    public static final java.lang.String _perfil_desc = "perfil_desc";
    public static final java.lang.String _orden_asc = "orden_asc";
    public static final java.lang.String _orden_desc = "orden_desc";
    public static final SeccioOrdenacio id_asc = new SeccioOrdenacio(_id_asc);
    public static final SeccioOrdenacio id_desc = new SeccioOrdenacio(_id_desc);
    public static final SeccioOrdenacio codigoEstandard_asc = new SeccioOrdenacio(_codigoEstandard_asc);
    public static final SeccioOrdenacio codigoEstandard_desc = new SeccioOrdenacio(_codigoEstandard_desc);
    public static final SeccioOrdenacio perfil_asc = new SeccioOrdenacio(_perfil_asc);
    public static final SeccioOrdenacio perfil_desc = new SeccioOrdenacio(_perfil_desc);
    public static final SeccioOrdenacio orden_asc = new SeccioOrdenacio(_orden_asc);
    public static final SeccioOrdenacio orden_desc = new SeccioOrdenacio(_orden_desc);
    public java.lang.String getValue() { return _value_;}
    public static SeccioOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        SeccioOrdenacio enumeration = (SeccioOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static SeccioOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(SeccioOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://seccio.v2.api.rolsac.caib.es", "SeccioOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
