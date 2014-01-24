/**
 * NormativaOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.normativa;

public class NormativaOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected NormativaOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _numero_asc = "numero_asc";
    public static final java.lang.String _numero_desc = "numero_desc";
    public static final java.lang.String _registro_asc = "registro_asc";
    public static final java.lang.String _registro_desc = "registro_desc";
    public static final java.lang.String _ley_asc = "ley_asc";
    public static final java.lang.String _ley_desc = "ley_desc";
    public static final java.lang.String _fecha_asc = "fecha_asc";
    public static final java.lang.String _fecha_desc = "fecha_desc";
    public static final java.lang.String _fechaBoletin_asc = "fechaBoletin_asc";
    public static final java.lang.String _fechaBoletin_desc = "fechaBoletin_desc";
    public static final java.lang.String _codiVuds_asc = "codiVuds_asc";
    public static final java.lang.String _codiVuds_desc = "codiVuds_desc";
    public static final java.lang.String _descCodiVuds_asc = "descCodiVuds_asc";
    public static final java.lang.String _descCodiVuds_desc = "descCodiVuds_desc";
    public static final NormativaOrdenacio id_asc = new NormativaOrdenacio(_id_asc);
    public static final NormativaOrdenacio id_desc = new NormativaOrdenacio(_id_desc);
    public static final NormativaOrdenacio numero_asc = new NormativaOrdenacio(_numero_asc);
    public static final NormativaOrdenacio numero_desc = new NormativaOrdenacio(_numero_desc);
    public static final NormativaOrdenacio registro_asc = new NormativaOrdenacio(_registro_asc);
    public static final NormativaOrdenacio registro_desc = new NormativaOrdenacio(_registro_desc);
    public static final NormativaOrdenacio ley_asc = new NormativaOrdenacio(_ley_asc);
    public static final NormativaOrdenacio ley_desc = new NormativaOrdenacio(_ley_desc);
    public static final NormativaOrdenacio fecha_asc = new NormativaOrdenacio(_fecha_asc);
    public static final NormativaOrdenacio fecha_desc = new NormativaOrdenacio(_fecha_desc);
    public static final NormativaOrdenacio fechaBoletin_asc = new NormativaOrdenacio(_fechaBoletin_asc);
    public static final NormativaOrdenacio fechaBoletin_desc = new NormativaOrdenacio(_fechaBoletin_desc);
    public static final NormativaOrdenacio codiVuds_asc = new NormativaOrdenacio(_codiVuds_asc);
    public static final NormativaOrdenacio codiVuds_desc = new NormativaOrdenacio(_codiVuds_desc);
    public static final NormativaOrdenacio descCodiVuds_asc = new NormativaOrdenacio(_descCodiVuds_asc);
    public static final NormativaOrdenacio descCodiVuds_desc = new NormativaOrdenacio(_descCodiVuds_desc);
    public java.lang.String getValue() { return _value_;}
    public static NormativaOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        NormativaOrdenacio enumeration = (NormativaOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static NormativaOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(NormativaOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
