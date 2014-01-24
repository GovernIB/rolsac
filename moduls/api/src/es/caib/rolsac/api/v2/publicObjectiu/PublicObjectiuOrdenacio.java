/**
 * PublicObjectiuOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.publicObjectiu;

public class PublicObjectiuOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected PublicObjectiuOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _codigoEstandar_asc = "codigoEstandar_asc";
    public static final java.lang.String _codigoEstandar_desc = "codigoEstandar_desc";
    public static final java.lang.String _orden_asc = "orden_asc";
    public static final java.lang.String _orden_desc = "orden_desc";
    public static final PublicObjectiuOrdenacio id_asc = new PublicObjectiuOrdenacio(_id_asc);
    public static final PublicObjectiuOrdenacio id_desc = new PublicObjectiuOrdenacio(_id_desc);
    public static final PublicObjectiuOrdenacio codigoEstandar_asc = new PublicObjectiuOrdenacio(_codigoEstandar_asc);
    public static final PublicObjectiuOrdenacio codigoEstandar_desc = new PublicObjectiuOrdenacio(_codigoEstandar_desc);
    public static final PublicObjectiuOrdenacio orden_asc = new PublicObjectiuOrdenacio(_orden_asc);
    public static final PublicObjectiuOrdenacio orden_desc = new PublicObjectiuOrdenacio(_orden_desc);
    public java.lang.String getValue() { return _value_;}
    public static PublicObjectiuOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        PublicObjectiuOrdenacio enumeration = (PublicObjectiuOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static PublicObjectiuOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(PublicObjectiuOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicObjectiu.v2.api.rolsac.caib.es", "PublicObjectiuOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
