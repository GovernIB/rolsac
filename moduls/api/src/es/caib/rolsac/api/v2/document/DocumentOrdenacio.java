/**
 * DocumentOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.document;

public class DocumentOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DocumentOrdenacio(java.lang.String value) {
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
    public static final DocumentOrdenacio id_asc = new DocumentOrdenacio(_id_asc);
    public static final DocumentOrdenacio id_desc = new DocumentOrdenacio(_id_desc);
    public static final DocumentOrdenacio ficha_asc = new DocumentOrdenacio(_ficha_asc);
    public static final DocumentOrdenacio ficha_desc = new DocumentOrdenacio(_ficha_desc);
    public static final DocumentOrdenacio procedimiento_asc = new DocumentOrdenacio(_procedimiento_asc);
    public static final DocumentOrdenacio procedimiento_desc = new DocumentOrdenacio(_procedimiento_desc);
    public static final DocumentOrdenacio orden_asc = new DocumentOrdenacio(_orden_asc);
    public static final DocumentOrdenacio orden_desc = new DocumentOrdenacio(_orden_desc);
    public java.lang.String getValue() { return _value_;}
    public static DocumentOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DocumentOrdenacio enumeration = (DocumentOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DocumentOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DocumentOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://document.v2.api.rolsac.caib.es", "DocumentOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
