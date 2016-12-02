/**
 * SEGUIMIENTOTRAMITACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class SEGUIMIENTOTRAMITACION  implements java.io.Serializable {
    private java.lang.String SEGTRAMITACION;

    private java.lang.String CODACTUACIONSEG;

    public SEGUIMIENTOTRAMITACION() {
    }

    public SEGUIMIENTOTRAMITACION(
           java.lang.String SEGTRAMITACION,
           java.lang.String CODACTUACIONSEG) {
           this.SEGTRAMITACION = SEGTRAMITACION;
           this.CODACTUACIONSEG = CODACTUACIONSEG;
    }


    /**
     * Gets the SEGTRAMITACION value for this SEGUIMIENTOTRAMITACION.
     * 
     * @return SEGTRAMITACION
     */
    public java.lang.String getSEGTRAMITACION() {
        return SEGTRAMITACION;
    }


    /**
     * Sets the SEGTRAMITACION value for this SEGUIMIENTOTRAMITACION.
     * 
     * @param SEGTRAMITACION
     */
    public void setSEGTRAMITACION(java.lang.String SEGTRAMITACION) {
        this.SEGTRAMITACION = SEGTRAMITACION;
    }


    /**
     * Gets the CODACTUACIONSEG value for this SEGUIMIENTOTRAMITACION.
     * 
     * @return CODACTUACIONSEG
     */
    public java.lang.String getCODACTUACIONSEG() {
        return CODACTUACIONSEG;
    }


    /**
     * Sets the CODACTUACIONSEG value for this SEGUIMIENTOTRAMITACION.
     * 
     * @param CODACTUACIONSEG
     */
    public void setCODACTUACIONSEG(java.lang.String CODACTUACIONSEG) {
        this.CODACTUACIONSEG = CODACTUACIONSEG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SEGUIMIENTOTRAMITACION)) return false;
        SEGUIMIENTOTRAMITACION other = (SEGUIMIENTOTRAMITACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SEGTRAMITACION==null && other.getSEGTRAMITACION()==null) || 
             (this.SEGTRAMITACION!=null &&
              this.SEGTRAMITACION.equals(other.getSEGTRAMITACION()))) &&
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
        if (getSEGTRAMITACION() != null) {
            _hashCode += getSEGTRAMITACION().hashCode();
        }
        if (getCODACTUACIONSEG() != null) {
            _hashCode += getCODACTUACIONSEG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SEGUIMIENTOTRAMITACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "SEGUIMIENTOTRAMITACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGTRAMITACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "SEGTRAMITACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODACTUACIONSEG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "CODACTUACIONSEG"));
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
