/**
 * TRAMITESRELACIONADOSTRAMITERELACIONADO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA;

public class TRAMITESRELACIONADOSTRAMITERELACIONADO  implements java.io.Serializable {
    private java.lang.String TRCODACTUACION;

    private java.lang.String TRORDEN;

    public TRAMITESRELACIONADOSTRAMITERELACIONADO() {
    }

    public TRAMITESRELACIONADOSTRAMITERELACIONADO(
           java.lang.String TRCODACTUACION,
           java.lang.String TRORDEN) {
           this.TRCODACTUACION = TRCODACTUACION;
           this.TRORDEN = TRORDEN;
    }


    /**
     * Gets the TRCODACTUACION value for this TRAMITESRELACIONADOSTRAMITERELACIONADO.
     * 
     * @return TRCODACTUACION
     */
    public java.lang.String getTRCODACTUACION() {
        return TRCODACTUACION;
    }


    /**
     * Sets the TRCODACTUACION value for this TRAMITESRELACIONADOSTRAMITERELACIONADO.
     * 
     * @param TRCODACTUACION
     */
    public void setTRCODACTUACION(java.lang.String TRCODACTUACION) {
        this.TRCODACTUACION = TRCODACTUACION;
    }


    /**
     * Gets the TRORDEN value for this TRAMITESRELACIONADOSTRAMITERELACIONADO.
     * 
     * @return TRORDEN
     */
    public java.lang.String getTRORDEN() {
        return TRORDEN;
    }


    /**
     * Sets the TRORDEN value for this TRAMITESRELACIONADOSTRAMITERELACIONADO.
     * 
     * @param TRORDEN
     */
    public void setTRORDEN(java.lang.String TRORDEN) {
        this.TRORDEN = TRORDEN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TRAMITESRELACIONADOSTRAMITERELACIONADO)) return false;
        TRAMITESRELACIONADOSTRAMITERELACIONADO other = (TRAMITESRELACIONADOSTRAMITERELACIONADO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TRCODACTUACION==null && other.getTRCODACTUACION()==null) || 
             (this.TRCODACTUACION!=null &&
              this.TRCODACTUACION.equals(other.getTRCODACTUACION()))) &&
            ((this.TRORDEN==null && other.getTRORDEN()==null) || 
             (this.TRORDEN!=null &&
              this.TRORDEN.equals(other.getTRORDEN())));
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
        if (getTRCODACTUACION() != null) {
            _hashCode += getTRCODACTUACION().hashCode();
        }
        if (getTRORDEN() != null) {
            _hashCode += getTRORDEN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TRAMITESRELACIONADOSTRAMITERELACIONADO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRCODACTUACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TRCODACTUACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRORDEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TRORDEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
