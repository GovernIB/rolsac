/**
 * EdificiOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.edifici;

public class EdificiOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EdificiOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _direccion_asc = "direccion_asc";
    public static final java.lang.String _direccion_desc = "direccion_desc";
    public static final java.lang.String _codigoPostal_asc = "codigoPostal_asc";
    public static final java.lang.String _codigoPostal_desc = "codigoPostal_desc";
    public static final java.lang.String _poblacion_asc = "poblacion_asc";
    public static final java.lang.String _poblacion_desc = "poblacion_desc";
    public static final java.lang.String _telefono_asc = "telefono_asc";
    public static final java.lang.String _telefono_desc = "telefono_desc";
    public static final java.lang.String _fax_asc = "fax_asc";
    public static final java.lang.String _fax_desc = "fax_desc";
    public static final java.lang.String _email_asc = "email_asc";
    public static final java.lang.String _email_desc = "email_desc";
    public static final EdificiOrdenacio id_asc = new EdificiOrdenacio(_id_asc);
    public static final EdificiOrdenacio id_desc = new EdificiOrdenacio(_id_desc);
    public static final EdificiOrdenacio direccion_asc = new EdificiOrdenacio(_direccion_asc);
    public static final EdificiOrdenacio direccion_desc = new EdificiOrdenacio(_direccion_desc);
    public static final EdificiOrdenacio codigoPostal_asc = new EdificiOrdenacio(_codigoPostal_asc);
    public static final EdificiOrdenacio codigoPostal_desc = new EdificiOrdenacio(_codigoPostal_desc);
    public static final EdificiOrdenacio poblacion_asc = new EdificiOrdenacio(_poblacion_asc);
    public static final EdificiOrdenacio poblacion_desc = new EdificiOrdenacio(_poblacion_desc);
    public static final EdificiOrdenacio telefono_asc = new EdificiOrdenacio(_telefono_asc);
    public static final EdificiOrdenacio telefono_desc = new EdificiOrdenacio(_telefono_desc);
    public static final EdificiOrdenacio fax_asc = new EdificiOrdenacio(_fax_asc);
    public static final EdificiOrdenacio fax_desc = new EdificiOrdenacio(_fax_desc);
    public static final EdificiOrdenacio email_asc = new EdificiOrdenacio(_email_asc);
    public static final EdificiOrdenacio email_desc = new EdificiOrdenacio(_email_desc);
    public java.lang.String getValue() { return _value_;}
    public static EdificiOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EdificiOrdenacio enumeration = (EdificiOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EdificiOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(EdificiOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://edifici.v2.api.rolsac.caib.es", "EdificiOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
