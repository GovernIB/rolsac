/**
 * UnitatAdministrativaOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.unitatAdministrativa;

public class UnitatAdministrativaOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected UnitatAdministrativaOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _claveHita_asc = "claveHita_asc";
    public static final java.lang.String _claveHita_desc = "claveHita_desc";
    public static final java.lang.String _dominio_asc = "dominio_asc";
    public static final java.lang.String _dominio_desc = "dominio_desc";
    public static final java.lang.String _orden_asc = "orden_asc";
    public static final java.lang.String _orden_desc = "orden_desc";
    public static final java.lang.String _responsable_asc = "responsable_asc";
    public static final java.lang.String _responsable_desc = "responsable_desc";
    public static final java.lang.String _telefono_asc = "telefono_asc";
    public static final java.lang.String _telefono_desc = "telefono_desc";
    public static final java.lang.String _fax_asc = "fax_asc";
    public static final java.lang.String _fax_desc = "fax_desc";
    public static final java.lang.String _email_asc = "email_asc";
    public static final java.lang.String _email_desc = "email_desc";
    public static final java.lang.String _sexoResponsable_asc = "sexoResponsable_asc";
    public static final java.lang.String _sexoResponsable_desc = "sexoResponsable_desc";
    public static final java.lang.String _codigoEstandar_asc = "codigoEstandar_asc";
    public static final java.lang.String _codigoEstandar_desc = "codigoEstandar_desc";
    public static final UnitatAdministrativaOrdenacio id_asc = new UnitatAdministrativaOrdenacio(_id_asc);
    public static final UnitatAdministrativaOrdenacio id_desc = new UnitatAdministrativaOrdenacio(_id_desc);
    public static final UnitatAdministrativaOrdenacio claveHita_asc = new UnitatAdministrativaOrdenacio(_claveHita_asc);
    public static final UnitatAdministrativaOrdenacio claveHita_desc = new UnitatAdministrativaOrdenacio(_claveHita_desc);
    public static final UnitatAdministrativaOrdenacio dominio_asc = new UnitatAdministrativaOrdenacio(_dominio_asc);
    public static final UnitatAdministrativaOrdenacio dominio_desc = new UnitatAdministrativaOrdenacio(_dominio_desc);
    public static final UnitatAdministrativaOrdenacio orden_asc = new UnitatAdministrativaOrdenacio(_orden_asc);
    public static final UnitatAdministrativaOrdenacio orden_desc = new UnitatAdministrativaOrdenacio(_orden_desc);
    public static final UnitatAdministrativaOrdenacio responsable_asc = new UnitatAdministrativaOrdenacio(_responsable_asc);
    public static final UnitatAdministrativaOrdenacio responsable_desc = new UnitatAdministrativaOrdenacio(_responsable_desc);
    public static final UnitatAdministrativaOrdenacio telefono_asc = new UnitatAdministrativaOrdenacio(_telefono_asc);
    public static final UnitatAdministrativaOrdenacio telefono_desc = new UnitatAdministrativaOrdenacio(_telefono_desc);
    public static final UnitatAdministrativaOrdenacio fax_asc = new UnitatAdministrativaOrdenacio(_fax_asc);
    public static final UnitatAdministrativaOrdenacio fax_desc = new UnitatAdministrativaOrdenacio(_fax_desc);
    public static final UnitatAdministrativaOrdenacio email_asc = new UnitatAdministrativaOrdenacio(_email_asc);
    public static final UnitatAdministrativaOrdenacio email_desc = new UnitatAdministrativaOrdenacio(_email_desc);
    public static final UnitatAdministrativaOrdenacio sexoResponsable_asc = new UnitatAdministrativaOrdenacio(_sexoResponsable_asc);
    public static final UnitatAdministrativaOrdenacio sexoResponsable_desc = new UnitatAdministrativaOrdenacio(_sexoResponsable_desc);
    public static final UnitatAdministrativaOrdenacio codigoEstandar_asc = new UnitatAdministrativaOrdenacio(_codigoEstandar_asc);
    public static final UnitatAdministrativaOrdenacio codigoEstandar_desc = new UnitatAdministrativaOrdenacio(_codigoEstandar_desc);
    public java.lang.String getValue() { return _value_;}
    public static UnitatAdministrativaOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        UnitatAdministrativaOrdenacio enumeration = (UnitatAdministrativaOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static UnitatAdministrativaOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(UnitatAdministrativaOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://unitatAdministrativa.v2.api.rolsac.caib.es", "UnitatAdministrativaOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
