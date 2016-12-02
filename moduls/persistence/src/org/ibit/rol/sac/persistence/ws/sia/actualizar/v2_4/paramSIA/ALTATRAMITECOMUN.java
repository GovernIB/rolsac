/**
 * ALTATRAMITECOMUN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class ALTATRAMITECOMUN  implements java.io.Serializable {
    private java.lang.String TIPOTRAMITE;

    private java.lang.String CODTRAMITECOMUN;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOCOMUN ALTAPROCEDIMIENTOCOMUN;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOCOMUN ALTASERVICIOCOMUN;

    public ALTATRAMITECOMUN() {
    }

    public ALTATRAMITECOMUN(
           java.lang.String TIPOTRAMITE,
           java.lang.String CODTRAMITECOMUN,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOCOMUN ALTAPROCEDIMIENTOCOMUN,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOCOMUN ALTASERVICIOCOMUN) {
           this.TIPOTRAMITE = TIPOTRAMITE;
           this.CODTRAMITECOMUN = CODTRAMITECOMUN;
           this.ALTAPROCEDIMIENTOCOMUN = ALTAPROCEDIMIENTOCOMUN;
           this.ALTASERVICIOCOMUN = ALTASERVICIOCOMUN;
    }


    /**
     * Gets the TIPOTRAMITE value for this ALTATRAMITECOMUN.
     * 
     * @return TIPOTRAMITE
     */
    public java.lang.String getTIPOTRAMITE() {
        return TIPOTRAMITE;
    }


    /**
     * Sets the TIPOTRAMITE value for this ALTATRAMITECOMUN.
     * 
     * @param TIPOTRAMITE
     */
    public void setTIPOTRAMITE(java.lang.String TIPOTRAMITE) {
        this.TIPOTRAMITE = TIPOTRAMITE;
    }


    /**
     * Gets the CODTRAMITECOMUN value for this ALTATRAMITECOMUN.
     * 
     * @return CODTRAMITECOMUN
     */
    public java.lang.String getCODTRAMITECOMUN() {
        return CODTRAMITECOMUN;
    }


    /**
     * Sets the CODTRAMITECOMUN value for this ALTATRAMITECOMUN.
     * 
     * @param CODTRAMITECOMUN
     */
    public void setCODTRAMITECOMUN(java.lang.String CODTRAMITECOMUN) {
        this.CODTRAMITECOMUN = CODTRAMITECOMUN;
    }


    /**
     * Gets the ALTAPROCEDIMIENTOCOMUN value for this ALTATRAMITECOMUN.
     * 
     * @return ALTAPROCEDIMIENTOCOMUN
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOCOMUN getALTAPROCEDIMIENTOCOMUN() {
        return ALTAPROCEDIMIENTOCOMUN;
    }


    /**
     * Sets the ALTAPROCEDIMIENTOCOMUN value for this ALTATRAMITECOMUN.
     * 
     * @param ALTAPROCEDIMIENTOCOMUN
     */
    public void setALTAPROCEDIMIENTOCOMUN(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOCOMUN ALTAPROCEDIMIENTOCOMUN) {
        this.ALTAPROCEDIMIENTOCOMUN = ALTAPROCEDIMIENTOCOMUN;
    }


    /**
     * Gets the ALTASERVICIOCOMUN value for this ALTATRAMITECOMUN.
     * 
     * @return ALTASERVICIOCOMUN
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOCOMUN getALTASERVICIOCOMUN() {
        return ALTASERVICIOCOMUN;
    }


    /**
     * Sets the ALTASERVICIOCOMUN value for this ALTATRAMITECOMUN.
     * 
     * @param ALTASERVICIOCOMUN
     */
    public void setALTASERVICIOCOMUN(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOCOMUN ALTASERVICIOCOMUN) {
        this.ALTASERVICIOCOMUN = ALTASERVICIOCOMUN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ALTATRAMITECOMUN)) return false;
        ALTATRAMITECOMUN other = (ALTATRAMITECOMUN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TIPOTRAMITE==null && other.getTIPOTRAMITE()==null) || 
             (this.TIPOTRAMITE!=null &&
              this.TIPOTRAMITE.equals(other.getTIPOTRAMITE()))) &&
            ((this.CODTRAMITECOMUN==null && other.getCODTRAMITECOMUN()==null) || 
             (this.CODTRAMITECOMUN!=null &&
              this.CODTRAMITECOMUN.equals(other.getCODTRAMITECOMUN()))) &&
            ((this.ALTAPROCEDIMIENTOCOMUN==null && other.getALTAPROCEDIMIENTOCOMUN()==null) || 
             (this.ALTAPROCEDIMIENTOCOMUN!=null &&
              this.ALTAPROCEDIMIENTOCOMUN.equals(other.getALTAPROCEDIMIENTOCOMUN()))) &&
            ((this.ALTASERVICIOCOMUN==null && other.getALTASERVICIOCOMUN()==null) || 
             (this.ALTASERVICIOCOMUN!=null &&
              this.ALTASERVICIOCOMUN.equals(other.getALTASERVICIOCOMUN())));
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
        if (getTIPOTRAMITE() != null) {
            _hashCode += getTIPOTRAMITE().hashCode();
        }
        if (getCODTRAMITECOMUN() != null) {
            _hashCode += getCODTRAMITECOMUN().hashCode();
        }
        if (getALTAPROCEDIMIENTOCOMUN() != null) {
            _hashCode += getALTAPROCEDIMIENTOCOMUN().hashCode();
        }
        if (getALTASERVICIOCOMUN() != null) {
            _hashCode += getALTASERVICIOCOMUN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ALTATRAMITECOMUN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTATRAMITECOMUN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOTRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TIPOTRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTRAMITECOMUN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "CODTRAMITECOMUN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTAPROCEDIMIENTOCOMUN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAPROCEDIMIENTOCOMUN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAPROCEDIMIENTOCOMUN"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTASERVICIOCOMUN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTASERVICIOCOMUN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTASERVICIOCOMUN"));
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
