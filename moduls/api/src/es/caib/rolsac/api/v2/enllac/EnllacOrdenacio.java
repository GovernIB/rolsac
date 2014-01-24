/**
 * EnllacOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.enllac;

public class EnllacOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EnllacOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _ficha_asc = "ficha_asc";
    public static final java.lang.String _ficha_desc = "ficha_desc";
    public static final java.lang.String _procedimiento_asc = "procedimiento_asc";
    public static final java.lang.String _procedimiento_desc = "procedimiento_desc";
    public static final java.lang.String _orden_asc = "orden_asc";
    public static final java.lang.String _orden_desc = "orden_desc";
    public static final EnllacOrdenacio id_asc = new EnllacOrdenacio(_id_asc);
    public static final EnllacOrdenacio id_desc = new EnllacOrdenacio(_id_desc);
    public static final EnllacOrdenacio ficha_asc = new EnllacOrdenacio(_ficha_asc);
    public static final EnllacOrdenacio ficha_desc = new EnllacOrdenacio(_ficha_desc);
    public static final EnllacOrdenacio procedimiento_asc = new EnllacOrdenacio(_procedimiento_asc);
    public static final EnllacOrdenacio procedimiento_desc = new EnllacOrdenacio(_procedimiento_desc);
    public static final EnllacOrdenacio orden_asc = new EnllacOrdenacio(_orden_asc);
    public static final EnllacOrdenacio orden_desc = new EnllacOrdenacio(_orden_desc);
    public java.lang.String getValue() { return _value_;}
    public static EnllacOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EnllacOrdenacio enumeration = (EnllacOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EnllacOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(EnllacOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enllac.v2.api.rolsac.caib.es", "EnllacOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
