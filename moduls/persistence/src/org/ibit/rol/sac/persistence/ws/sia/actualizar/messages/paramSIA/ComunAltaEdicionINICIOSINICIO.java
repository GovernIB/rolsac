/**
 * ComunAltaEdicionINICIOSINICIO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA;

public class ComunAltaEdicionINICIOSINICIO  implements java.io.Serializable {
    private java.lang.String CODFORMAINICIACION;

    private java.lang.String CODEFECTOSILENCIO;

    public ComunAltaEdicionINICIOSINICIO() {
    }

    public ComunAltaEdicionINICIOSINICIO(
           java.lang.String CODFORMAINICIACION,
           java.lang.String CODEFECTOSILENCIO) {
           this.CODFORMAINICIACION = CODFORMAINICIACION;
           this.CODEFECTOSILENCIO = CODEFECTOSILENCIO;
    }


    /**
     * Gets the CODFORMAINICIACION value for this ComunAltaEdicionINICIOSINICIO.
     * 
     * @return CODFORMAINICIACION
     */
    public java.lang.String getCODFORMAINICIACION() {
        return CODFORMAINICIACION;
    }


    /**
     * Sets the CODFORMAINICIACION value for this ComunAltaEdicionINICIOSINICIO.
     * 
     * @param CODFORMAINICIACION
     */
    public void setCODFORMAINICIACION(java.lang.String CODFORMAINICIACION) {
        this.CODFORMAINICIACION = CODFORMAINICIACION;
    }


    /**
     * Gets the CODEFECTOSILENCIO value for this ComunAltaEdicionINICIOSINICIO.
     * 
     * @return CODEFECTOSILENCIO
     */
    public java.lang.String getCODEFECTOSILENCIO() {
        return CODEFECTOSILENCIO;
    }


    /**
     * Sets the CODEFECTOSILENCIO value for this ComunAltaEdicionINICIOSINICIO.
     * 
     * @param CODEFECTOSILENCIO
     */
    public void setCODEFECTOSILENCIO(java.lang.String CODEFECTOSILENCIO) {
        this.CODEFECTOSILENCIO = CODEFECTOSILENCIO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionINICIOSINICIO)) return false;
        ComunAltaEdicionINICIOSINICIO other = (ComunAltaEdicionINICIOSINICIO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODFORMAINICIACION==null && other.getCODFORMAINICIACION()==null) || 
             (this.CODFORMAINICIACION!=null &&
              this.CODFORMAINICIACION.equals(other.getCODFORMAINICIACION()))) &&
            ((this.CODEFECTOSILENCIO==null && other.getCODEFECTOSILENCIO()==null) || 
             (this.CODEFECTOSILENCIO!=null &&
              this.CODEFECTOSILENCIO.equals(other.getCODEFECTOSILENCIO())));
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
        if (getCODFORMAINICIACION() != null) {
            _hashCode += getCODFORMAINICIACION().hashCode();
        }
        if (getCODEFECTOSILENCIO() != null) {
            _hashCode += getCODEFECTOSILENCIO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionINICIOSINICIO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>INICIOS>INICIO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODFORMAINICIACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "CODFORMAINICIACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODEFECTOSILENCIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "CODEFECTOSILENCIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
