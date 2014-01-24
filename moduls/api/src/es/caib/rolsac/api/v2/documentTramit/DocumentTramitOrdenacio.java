/**
 * DocumentTramitOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.documentTramit;

public class DocumentTramitOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DocumentTramitOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _orden_asc = "orden_asc";
    public static final java.lang.String _orden_desc = "orden_desc";
    public static final java.lang.String _tipus_asc = "tipus_asc";
    public static final java.lang.String _tipus_desc = "tipus_desc";
    public static final DocumentTramitOrdenacio id_asc = new DocumentTramitOrdenacio(_id_asc);
    public static final DocumentTramitOrdenacio id_desc = new DocumentTramitOrdenacio(_id_desc);
    public static final DocumentTramitOrdenacio orden_asc = new DocumentTramitOrdenacio(_orden_asc);
    public static final DocumentTramitOrdenacio orden_desc = new DocumentTramitOrdenacio(_orden_desc);
    public static final DocumentTramitOrdenacio tipus_asc = new DocumentTramitOrdenacio(_tipus_asc);
    public static final DocumentTramitOrdenacio tipus_desc = new DocumentTramitOrdenacio(_tipus_desc);
    public java.lang.String getValue() { return _value_;}
    public static DocumentTramitOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DocumentTramitOrdenacio enumeration = (DocumentTramitOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DocumentTramitOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DocumentTramitOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://documentTramit.v2.api.rolsac.caib.es", "DocumentTramitOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
