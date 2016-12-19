/**
 * ParamSIAACTUACIONESACTUACIONALTA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class ParamSIAACTUACIONESACTUACIONALTA  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTATRAMITEESPECIFICO ALTATRAMITEESPECIFICO;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTATRAMITECOMUN ALTATRAMITECOMUN;

    private java.lang.String OPERACION;  // attribute

    public ParamSIAACTUACIONESACTUACIONALTA() {
    }

    public ParamSIAACTUACIONESACTUACIONALTA(
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTATRAMITEESPECIFICO ALTATRAMITEESPECIFICO,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTATRAMITECOMUN ALTATRAMITECOMUN,
           java.lang.String OPERACION) {
           this.ALTATRAMITEESPECIFICO = ALTATRAMITEESPECIFICO;
           this.ALTATRAMITECOMUN = ALTATRAMITECOMUN;
           this.OPERACION = OPERACION;
    }


    /**
     * Gets the ALTATRAMITEESPECIFICO value for this ParamSIAACTUACIONESACTUACIONALTA.
     * 
     * @return ALTATRAMITEESPECIFICO
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTATRAMITEESPECIFICO getALTATRAMITEESPECIFICO() {
        return ALTATRAMITEESPECIFICO;
    }


    /**
     * Sets the ALTATRAMITEESPECIFICO value for this ParamSIAACTUACIONESACTUACIONALTA.
     * 
     * @param ALTATRAMITEESPECIFICO
     */
    public void setALTATRAMITEESPECIFICO(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTATRAMITEESPECIFICO ALTATRAMITEESPECIFICO) {
        this.ALTATRAMITEESPECIFICO = ALTATRAMITEESPECIFICO;
    }


    /**
     * Gets the ALTATRAMITECOMUN value for this ParamSIAACTUACIONESACTUACIONALTA.
     * 
     * @return ALTATRAMITECOMUN
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTATRAMITECOMUN getALTATRAMITECOMUN() {
        return ALTATRAMITECOMUN;
    }


    /**
     * Sets the ALTATRAMITECOMUN value for this ParamSIAACTUACIONESACTUACIONALTA.
     * 
     * @param ALTATRAMITECOMUN
     */
    public void setALTATRAMITECOMUN(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTATRAMITECOMUN ALTATRAMITECOMUN) {
        this.ALTATRAMITECOMUN = ALTATRAMITECOMUN;
    }


    /**
     * Gets the OPERACION value for this ParamSIAACTUACIONESACTUACIONALTA.
     * 
     * @return OPERACION
     */
    public java.lang.String getOPERACION() {
        return OPERACION;
    }


    /**
     * Sets the OPERACION value for this ParamSIAACTUACIONESACTUACIONALTA.
     * 
     * @param OPERACION
     */
    public void setOPERACION(java.lang.String OPERACION) {
        this.OPERACION = OPERACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamSIAACTUACIONESACTUACIONALTA)) return false;
        ParamSIAACTUACIONESACTUACIONALTA other = (ParamSIAACTUACIONESACTUACIONALTA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ALTATRAMITEESPECIFICO==null && other.getALTATRAMITEESPECIFICO()==null) || 
             (this.ALTATRAMITEESPECIFICO!=null &&
              this.ALTATRAMITEESPECIFICO.equals(other.getALTATRAMITEESPECIFICO()))) &&
            ((this.ALTATRAMITECOMUN==null && other.getALTATRAMITECOMUN()==null) || 
             (this.ALTATRAMITECOMUN!=null &&
              this.ALTATRAMITECOMUN.equals(other.getALTATRAMITECOMUN()))) &&
            ((this.OPERACION==null && other.getOPERACION()==null) || 
             (this.OPERACION!=null &&
              this.OPERACION.equals(other.getOPERACION())));
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
        if (getALTATRAMITEESPECIFICO() != null) {
            _hashCode += getALTATRAMITEESPECIFICO().hashCode();
        }
        if (getALTATRAMITECOMUN() != null) {
            _hashCode += getALTATRAMITECOMUN().hashCode();
        }
        if (getOPERACION() != null) {
            _hashCode += getOPERACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamSIAACTUACIONESACTUACIONALTA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ALTA"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("OPERACION");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OPERACION"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTATRAMITEESPECIFICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ALTATRAMITEESPECIFICO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ALTATRAMITEESPECIFICO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTATRAMITECOMUN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ALTATRAMITECOMUN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ALTATRAMITECOMUN"));
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
