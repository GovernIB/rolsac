/**
 * ComunAltaEdicionSEGUISERVICIO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA;

public class ComunAltaEdicionSEGUISERVICIO  implements java.io.Serializable {
    private java.lang.String SEGSERVICIO;

    private java.lang.String CODACTUACIONSEG;

    public ComunAltaEdicionSEGUISERVICIO() {
    }

    public ComunAltaEdicionSEGUISERVICIO(
           java.lang.String SEGSERVICIO,
           java.lang.String CODACTUACIONSEG) {
           this.SEGSERVICIO = SEGSERVICIO;
           this.CODACTUACIONSEG = CODACTUACIONSEG;
    }


    /**
     * Gets the SEGSERVICIO value for this ComunAltaEdicionSEGUISERVICIO.
     * 
     * @return SEGSERVICIO
     */
    public java.lang.String getSEGSERVICIO() {
        return SEGSERVICIO;
    }


    /**
     * Sets the SEGSERVICIO value for this ComunAltaEdicionSEGUISERVICIO.
     * 
     * @param SEGSERVICIO
     */
    public void setSEGSERVICIO(java.lang.String SEGSERVICIO) {
        this.SEGSERVICIO = SEGSERVICIO;
    }


    /**
     * Gets the CODACTUACIONSEG value for this ComunAltaEdicionSEGUISERVICIO.
     * 
     * @return CODACTUACIONSEG
     */
    public java.lang.String getCODACTUACIONSEG() {
        return CODACTUACIONSEG;
    }


    /**
     * Sets the CODACTUACIONSEG value for this ComunAltaEdicionSEGUISERVICIO.
     * 
     * @param CODACTUACIONSEG
     */
    public void setCODACTUACIONSEG(java.lang.String CODACTUACIONSEG) {
        this.CODACTUACIONSEG = CODACTUACIONSEG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionSEGUISERVICIO)) return false;
        ComunAltaEdicionSEGUISERVICIO other = (ComunAltaEdicionSEGUISERVICIO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SEGSERVICIO==null && other.getSEGSERVICIO()==null) || 
             (this.SEGSERVICIO!=null &&
              this.SEGSERVICIO.equals(other.getSEGSERVICIO()))) &&
            ((this.CODACTUACIONSEG==null && other.getCODACTUACIONSEG()==null) || 
             (this.CODACTUACIONSEG!=null &&
              this.CODACTUACIONSEG.equals(other.getCODACTUACIONSEG())));
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
        if (getSEGSERVICIO() != null) {
            _hashCode += getSEGSERVICIO().hashCode();
        }
        if (getCODACTUACIONSEG() != null) {
            _hashCode += getCODACTUACIONSEG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionSEGUISERVICIO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">comunAltaEdicion>SEGUISERVICIO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGSERVICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "SEGSERVICIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODACTUACIONSEG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODACTUACIONSEG"));
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
