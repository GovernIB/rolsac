/**
 * TramitOrdenacio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.tramit;

public class TramitOrdenacio implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected TramitOrdenacio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _id_asc = "id_asc";
    public static final java.lang.String _id_desc = "id_desc";
    public static final java.lang.String _fase_asc = "fase_asc";
    public static final java.lang.String _fase_desc = "fase_desc";
    public static final java.lang.String _orden_asc = "orden_asc";
    public static final java.lang.String _orden_desc = "orden_desc";
    public static final java.lang.String _codiVuds_asc = "codiVuds_asc";
    public static final java.lang.String _codiVuds_desc = "codiVuds_desc";
    public static final java.lang.String _dataCaducitat_asc = "dataCaducitat_asc";
    public static final java.lang.String _dataCaducitat_desc = "dataCaducitat_desc";
    public static final java.lang.String _dataPublicacio_asc = "dataPublicacio_asc";
    public static final java.lang.String _dataPublicacio_desc = "dataPublicacio_desc";
    public static final java.lang.String _dataActualitzacio_asc = "dataActualitzacio_asc";
    public static final java.lang.String _dataActualitzacio_desc = "dataActualitzacio_desc";
    public static final java.lang.String _versio_asc = "versio_asc";
    public static final java.lang.String _versio_desc = "versio_desc";
    public static final java.lang.String _dataActualitzacioVuds_asc = "dataActualitzacioVuds_asc";
    public static final java.lang.String _dataActualitzacioVuds_desc = "dataActualitzacioVuds_desc";
    public static final java.lang.String _dataInici_asc = "dataInici_asc";
    public static final java.lang.String _dataInici_desc = "dataInici_desc";
    public static final java.lang.String _dataTancament_asc = "dataTancament_asc";
    public static final java.lang.String _dataTancament_desc = "dataTancament_desc";
    public static final TramitOrdenacio id_asc = new TramitOrdenacio(_id_asc);
    public static final TramitOrdenacio id_desc = new TramitOrdenacio(_id_desc);
    public static final TramitOrdenacio fase_asc = new TramitOrdenacio(_fase_asc);
    public static final TramitOrdenacio fase_desc = new TramitOrdenacio(_fase_desc);
    public static final TramitOrdenacio orden_asc = new TramitOrdenacio(_orden_asc);
    public static final TramitOrdenacio orden_desc = new TramitOrdenacio(_orden_desc);
    public static final TramitOrdenacio codiVuds_asc = new TramitOrdenacio(_codiVuds_asc);
    public static final TramitOrdenacio codiVuds_desc = new TramitOrdenacio(_codiVuds_desc);
    public static final TramitOrdenacio dataCaducitat_asc = new TramitOrdenacio(_dataCaducitat_asc);
    public static final TramitOrdenacio dataCaducitat_desc = new TramitOrdenacio(_dataCaducitat_desc);
    public static final TramitOrdenacio dataPublicacio_asc = new TramitOrdenacio(_dataPublicacio_asc);
    public static final TramitOrdenacio dataPublicacio_desc = new TramitOrdenacio(_dataPublicacio_desc);
    public static final TramitOrdenacio dataActualitzacio_asc = new TramitOrdenacio(_dataActualitzacio_asc);
    public static final TramitOrdenacio dataActualitzacio_desc = new TramitOrdenacio(_dataActualitzacio_desc);
    public static final TramitOrdenacio versio_asc = new TramitOrdenacio(_versio_asc);
    public static final TramitOrdenacio versio_desc = new TramitOrdenacio(_versio_desc);
    public static final TramitOrdenacio dataActualitzacioVuds_asc = new TramitOrdenacio(_dataActualitzacioVuds_asc);
    public static final TramitOrdenacio dataActualitzacioVuds_desc = new TramitOrdenacio(_dataActualitzacioVuds_desc);
    public static final TramitOrdenacio dataInici_asc = new TramitOrdenacio(_dataInici_asc);
    public static final TramitOrdenacio dataInici_desc = new TramitOrdenacio(_dataInici_desc);
    public static final TramitOrdenacio dataTancament_asc = new TramitOrdenacio(_dataTancament_asc);
    public static final TramitOrdenacio dataTancament_desc = new TramitOrdenacio(_dataTancament_desc);
    public java.lang.String getValue() { return _value_;}
    public static TramitOrdenacio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        TramitOrdenacio enumeration = (TramitOrdenacio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static TramitOrdenacio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(TramitOrdenacio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tramit.v2.api.rolsac.caib.es", "TramitOrdenacio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
