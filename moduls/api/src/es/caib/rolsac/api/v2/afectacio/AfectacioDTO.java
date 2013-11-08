/**
 * AfectacioDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.afectacio;

public class AfectacioDTO  implements java.io.Serializable {
    private long afectante;

    private long normativa;

    private long tipoAfectacion;

    public AfectacioDTO() {
    }

    public AfectacioDTO(
           long afectante,
           long normativa,
           long tipoAfectacion) {
           this.afectante = afectante;
           this.normativa = normativa;
           this.tipoAfectacion = tipoAfectacion;
    }


    /**
     * Gets the afectante value for this AfectacioDTO.
     * 
     * @return afectante
     */
    public long getAfectante() {
        return afectante;
    }


    /**
     * Sets the afectante value for this AfectacioDTO.
     * 
     * @param afectante
     */
    public void setAfectante(long afectante) {
        this.afectante = afectante;
    }


    /**
     * Gets the normativa value for this AfectacioDTO.
     * 
     * @return normativa
     */
    public long getNormativa() {
        return normativa;
    }


    /**
     * Sets the normativa value for this AfectacioDTO.
     * 
     * @param normativa
     */
    public void setNormativa(long normativa) {
        this.normativa = normativa;
    }


    /**
     * Gets the tipoAfectacion value for this AfectacioDTO.
     * 
     * @return tipoAfectacion
     */
    public long getTipoAfectacion() {
        return tipoAfectacion;
    }


    /**
     * Sets the tipoAfectacion value for this AfectacioDTO.
     * 
     * @param tipoAfectacion
     */
    public void setTipoAfectacion(long tipoAfectacion) {
        this.tipoAfectacion = tipoAfectacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AfectacioDTO)) return false;
        AfectacioDTO other = (AfectacioDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.afectante == other.getAfectante() &&
            this.normativa == other.getNormativa() &&
            this.tipoAfectacion == other.getTipoAfectacion();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Long(getAfectante()).hashCode();
        _hashCode += new Long(getNormativa()).hashCode();
        _hashCode += new Long(getTipoAfectacion()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AfectacioDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://afectacio.v2.api.rolsac.caib.es", "AfectacioDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("afectante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "afectante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("normativa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "normativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoAfectacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoAfectacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
