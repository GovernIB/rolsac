/**
 * EDICIONTRAMITECOMUN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class EDICIONTRAMITECOMUN  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONPROCEDIMIENTOCOMUN EDICIONPROCEDIMIENTOCOMUN;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONSERVICIOCOMUN EDICIONSERVICIOCOMUN;

    public EDICIONTRAMITECOMUN() {
    }

    public EDICIONTRAMITECOMUN(
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONPROCEDIMIENTOCOMUN EDICIONPROCEDIMIENTOCOMUN,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONSERVICIOCOMUN EDICIONSERVICIOCOMUN) {
           this.EDICIONPROCEDIMIENTOCOMUN = EDICIONPROCEDIMIENTOCOMUN;
           this.EDICIONSERVICIOCOMUN = EDICIONSERVICIOCOMUN;
    }


    /**
     * Gets the EDICIONPROCEDIMIENTOCOMUN value for this EDICIONTRAMITECOMUN.
     * 
     * @return EDICIONPROCEDIMIENTOCOMUN
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONPROCEDIMIENTOCOMUN getEDICIONPROCEDIMIENTOCOMUN() {
        return EDICIONPROCEDIMIENTOCOMUN;
    }


    /**
     * Sets the EDICIONPROCEDIMIENTOCOMUN value for this EDICIONTRAMITECOMUN.
     * 
     * @param EDICIONPROCEDIMIENTOCOMUN
     */
    public void setEDICIONPROCEDIMIENTOCOMUN(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONPROCEDIMIENTOCOMUN EDICIONPROCEDIMIENTOCOMUN) {
        this.EDICIONPROCEDIMIENTOCOMUN = EDICIONPROCEDIMIENTOCOMUN;
    }


    /**
     * Gets the EDICIONSERVICIOCOMUN value for this EDICIONTRAMITECOMUN.
     * 
     * @return EDICIONSERVICIOCOMUN
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONSERVICIOCOMUN getEDICIONSERVICIOCOMUN() {
        return EDICIONSERVICIOCOMUN;
    }


    /**
     * Sets the EDICIONSERVICIOCOMUN value for this EDICIONTRAMITECOMUN.
     * 
     * @param EDICIONSERVICIOCOMUN
     */
    public void setEDICIONSERVICIOCOMUN(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONSERVICIOCOMUN EDICIONSERVICIOCOMUN) {
        this.EDICIONSERVICIOCOMUN = EDICIONSERVICIOCOMUN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONTRAMITECOMUN)) return false;
        EDICIONTRAMITECOMUN other = (EDICIONTRAMITECOMUN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.EDICIONPROCEDIMIENTOCOMUN==null && other.getEDICIONPROCEDIMIENTOCOMUN()==null) || 
             (this.EDICIONPROCEDIMIENTOCOMUN!=null &&
              this.EDICIONPROCEDIMIENTOCOMUN.equals(other.getEDICIONPROCEDIMIENTOCOMUN()))) &&
            ((this.EDICIONSERVICIOCOMUN==null && other.getEDICIONSERVICIOCOMUN()==null) || 
             (this.EDICIONSERVICIOCOMUN!=null &&
              this.EDICIONSERVICIOCOMUN.equals(other.getEDICIONSERVICIOCOMUN())));
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
        if (getEDICIONPROCEDIMIENTOCOMUN() != null) {
            _hashCode += getEDICIONPROCEDIMIENTOCOMUN().hashCode();
        }
        if (getEDICIONSERVICIOCOMUN() != null) {
            _hashCode += getEDICIONSERVICIOCOMUN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONTRAMITECOMUN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONTRAMITECOMUN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EDICIONPROCEDIMIENTOCOMUN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONPROCEDIMIENTOCOMUN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONPROCEDIMIENTOCOMUN"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EDICIONSERVICIOCOMUN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONSERVICIOCOMUN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONSERVICIOCOMUN"));
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
