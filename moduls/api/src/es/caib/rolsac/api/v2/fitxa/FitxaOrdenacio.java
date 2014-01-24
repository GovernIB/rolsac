/**
 * FitxaOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.fitxa;

public class FitxaOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected FitxaOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _fechaPublicacion_asc = "fechaPublicacion_asc";
    public static final java.lang.String _fechaPublicacion_desc = "fechaPublicacion_desc";
    public static final java.lang.String _fechaCaducidad_asc = "fechaCaducidad_asc";
    public static final java.lang.String _fechaCaducidad_desc = "fechaCaducidad_desc";
    public static final java.lang.String _fechaActualizacion_asc = "fechaActualizacion_asc";
    public static final java.lang.String _fechaActualizacion_desc = "fechaActualizacion_desc";
    public static final java.lang.String _responsable_asc = "responsable_asc";
    public static final java.lang.String _responsable_desc = "responsable_desc";
    public static final java.lang.String _fua_ordenseccion_asc = "fua_ordenseccion_asc";
    public static final java.lang.String _fua_ordenseccion_desc = "fua_ordenseccion_desc";
    public static final FitxaOrdenacio id_asc = new FitxaOrdenacio(_id_asc);
    public static final FitxaOrdenacio id_desc = new FitxaOrdenacio(_id_desc);
    public static final FitxaOrdenacio fechaPublicacion_asc = new FitxaOrdenacio(_fechaPublicacion_asc);
    public static final FitxaOrdenacio fechaPublicacion_desc = new FitxaOrdenacio(_fechaPublicacion_desc);
    public static final FitxaOrdenacio fechaCaducidad_asc = new FitxaOrdenacio(_fechaCaducidad_asc);
    public static final FitxaOrdenacio fechaCaducidad_desc = new FitxaOrdenacio(_fechaCaducidad_desc);
    public static final FitxaOrdenacio fechaActualizacion_asc = new FitxaOrdenacio(_fechaActualizacion_asc);
    public static final FitxaOrdenacio fechaActualizacion_desc = new FitxaOrdenacio(_fechaActualizacion_desc);
    public static final FitxaOrdenacio responsable_asc = new FitxaOrdenacio(_responsable_asc);
    public static final FitxaOrdenacio responsable_desc = new FitxaOrdenacio(_responsable_desc);
    public static final FitxaOrdenacio fua_ordenseccion_asc = new FitxaOrdenacio(_fua_ordenseccion_asc);
    public static final FitxaOrdenacio fua_ordenseccion_desc = new FitxaOrdenacio(_fua_ordenseccion_desc);
    public java.lang.String getValue() { return _value_;}
    public static FitxaOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        FitxaOrdenacio enumeration = (FitxaOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static FitxaOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(FitxaOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fitxa.v2.api.rolsac.caib.es", "FitxaOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
