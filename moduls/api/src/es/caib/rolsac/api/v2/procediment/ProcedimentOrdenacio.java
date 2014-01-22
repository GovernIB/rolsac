/**
 * ProcedimentOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.procediment;

public class ProcedimentOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ProcedimentOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _signatura_asc = "signatura_asc";
    public static final java.lang.String _signatura_desc = "signatura_desc";
    public static final java.lang.String _fechaCaducidad_asc = "fechaCaducidad_asc";
    public static final java.lang.String _fechaCaducidad_desc = "fechaCaducidad_desc";
    public static final java.lang.String _fechaPublicacion_asc = "fechaPublicacion_asc";
    public static final java.lang.String _fechaPublicacion_desc = "fechaPublicacion_desc";
    public static final java.lang.String _fechaActualizacion_asc = "fechaActualizacion_asc";
    public static final java.lang.String _fechaActualizacion_desc = "fechaActualizacion_desc";
    public static final java.lang.String _version_asc = "version_asc";
    public static final java.lang.String _version_desc = "version_desc";
    public static final java.lang.String _familia_asc = "familia_asc";
    public static final java.lang.String _familia_desc = "familia_desc";
    public static final ProcedimentOrdenacio signatura_asc = new ProcedimentOrdenacio(_signatura_asc);
    public static final ProcedimentOrdenacio signatura_desc = new ProcedimentOrdenacio(_signatura_desc);
    public static final ProcedimentOrdenacio fechaCaducidad_asc = new ProcedimentOrdenacio(_fechaCaducidad_asc);
    public static final ProcedimentOrdenacio fechaCaducidad_desc = new ProcedimentOrdenacio(_fechaCaducidad_desc);
    public static final ProcedimentOrdenacio fechaPublicacion_asc = new ProcedimentOrdenacio(_fechaPublicacion_asc);
    public static final ProcedimentOrdenacio fechaPublicacion_desc = new ProcedimentOrdenacio(_fechaPublicacion_desc);
    public static final ProcedimentOrdenacio fechaActualizacion_asc = new ProcedimentOrdenacio(_fechaActualizacion_asc);
    public static final ProcedimentOrdenacio fechaActualizacion_desc = new ProcedimentOrdenacio(_fechaActualizacion_desc);
    public static final ProcedimentOrdenacio version_asc = new ProcedimentOrdenacio(_version_asc);
    public static final ProcedimentOrdenacio version_desc = new ProcedimentOrdenacio(_version_desc);
    public static final ProcedimentOrdenacio familia_asc = new ProcedimentOrdenacio(_familia_asc);
    public static final ProcedimentOrdenacio familia_desc = new ProcedimentOrdenacio(_familia_desc);
    public java.lang.String getValue() { return _value_;}
    public static ProcedimentOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ProcedimentOrdenacio enumeration = (ProcedimentOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ProcedimentOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(ProcedimentOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://procediment.v2.api.rolsac.caib.es", "ProcedimentOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
