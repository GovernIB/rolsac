/**
 * CatalegDocumentsOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.catalegDocuments;

public class CatalegDocumentsOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CatalegDocumentsOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _ordre_asc = "ordre_asc";
    public static final java.lang.String _ordre_desc = "ordre_desc";
    public static final java.lang.String _admResponsable_asc = "admResponsable_asc";
    public static final java.lang.String _admResponsable_desc = "admResponsable_desc";
    public static final CatalegDocumentsOrdenacio id_asc = new CatalegDocumentsOrdenacio(_id_asc);
    public static final CatalegDocumentsOrdenacio id_desc = new CatalegDocumentsOrdenacio(_id_desc);
    public static final CatalegDocumentsOrdenacio ordre_asc = new CatalegDocumentsOrdenacio(_ordre_asc);
    public static final CatalegDocumentsOrdenacio ordre_desc = new CatalegDocumentsOrdenacio(_ordre_desc);
    public static final CatalegDocumentsOrdenacio admResponsable_asc = new CatalegDocumentsOrdenacio(_admResponsable_asc);
    public static final CatalegDocumentsOrdenacio admResponsable_desc = new CatalegDocumentsOrdenacio(_admResponsable_desc);
    public java.lang.String getValue() { return _value_;}
    public static CatalegDocumentsOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        CatalegDocumentsOrdenacio enumeration = (CatalegDocumentsOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static CatalegDocumentsOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(CatalegDocumentsOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://catalegDocuments.v2.api.rolsac.caib.es", "CatalegDocumentsOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
