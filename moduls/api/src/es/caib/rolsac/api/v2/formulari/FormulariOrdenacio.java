/**
 * FormulariOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.formulari;

public class FormulariOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected FormulariOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _nombre_asc = "nombre_asc";
    public static final java.lang.String _nombre_desc = "nombre_desc";
    public static final FormulariOrdenacio id_asc = new FormulariOrdenacio(_id_asc);
    public static final FormulariOrdenacio id_desc = new FormulariOrdenacio(_id_desc);
    public static final FormulariOrdenacio nombre_asc = new FormulariOrdenacio(_nombre_asc);
    public static final FormulariOrdenacio nombre_desc = new FormulariOrdenacio(_nombre_desc);
    public java.lang.String getValue() { return _value_;}
    public static FormulariOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        FormulariOrdenacio enumeration = (FormulariOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static FormulariOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(FormulariOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://formulari.v2.api.rolsac.caib.es", "FormulariOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
