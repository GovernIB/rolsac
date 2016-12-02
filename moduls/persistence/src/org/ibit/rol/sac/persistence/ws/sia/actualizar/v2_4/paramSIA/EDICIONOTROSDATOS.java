/**
 * EDICIONOTROSDATOS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class EDICIONOTROSDATOS  implements java.io.Serializable {
    private java.lang.String REQUISITOS;

    private java.lang.String PRESENTACIONPRESENCIAL;

    private java.lang.String CODLUGARPRESENTACION;

    public EDICIONOTROSDATOS() {
    }

    public EDICIONOTROSDATOS(
           java.lang.String REQUISITOS,
           java.lang.String PRESENTACIONPRESENCIAL,
           java.lang.String CODLUGARPRESENTACION) {
           this.REQUISITOS = REQUISITOS;
           this.PRESENTACIONPRESENCIAL = PRESENTACIONPRESENCIAL;
           this.CODLUGARPRESENTACION = CODLUGARPRESENTACION;
    }


    /**
     * Gets the REQUISITOS value for this EDICIONOTROSDATOS.
     * 
     * @return REQUISITOS
     */
    public java.lang.String getREQUISITOS() {
        return REQUISITOS;
    }


    /**
     * Sets the REQUISITOS value for this EDICIONOTROSDATOS.
     * 
     * @param REQUISITOS
     */
    public void setREQUISITOS(java.lang.String REQUISITOS) {
        this.REQUISITOS = REQUISITOS;
    }


    /**
     * Gets the PRESENTACIONPRESENCIAL value for this EDICIONOTROSDATOS.
     * 
     * @return PRESENTACIONPRESENCIAL
     */
    public java.lang.String getPRESENTACIONPRESENCIAL() {
        return PRESENTACIONPRESENCIAL;
    }


    /**
     * Sets the PRESENTACIONPRESENCIAL value for this EDICIONOTROSDATOS.
     * 
     * @param PRESENTACIONPRESENCIAL
     */
    public void setPRESENTACIONPRESENCIAL(java.lang.String PRESENTACIONPRESENCIAL) {
        this.PRESENTACIONPRESENCIAL = PRESENTACIONPRESENCIAL;
    }


    /**
     * Gets the CODLUGARPRESENTACION value for this EDICIONOTROSDATOS.
     * 
     * @return CODLUGARPRESENTACION
     */
    public java.lang.String getCODLUGARPRESENTACION() {
        return CODLUGARPRESENTACION;
    }


    /**
     * Sets the CODLUGARPRESENTACION value for this EDICIONOTROSDATOS.
     * 
     * @param CODLUGARPRESENTACION
     */
    public void setCODLUGARPRESENTACION(java.lang.String CODLUGARPRESENTACION) {
        this.CODLUGARPRESENTACION = CODLUGARPRESENTACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONOTROSDATOS)) return false;
        EDICIONOTROSDATOS other = (EDICIONOTROSDATOS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.REQUISITOS==null && other.getREQUISITOS()==null) || 
             (this.REQUISITOS!=null &&
              this.REQUISITOS.equals(other.getREQUISITOS()))) &&
            ((this.PRESENTACIONPRESENCIAL==null && other.getPRESENTACIONPRESENCIAL()==null) || 
             (this.PRESENTACIONPRESENCIAL!=null &&
              this.PRESENTACIONPRESENCIAL.equals(other.getPRESENTACIONPRESENCIAL()))) &&
            ((this.CODLUGARPRESENTACION==null && other.getCODLUGARPRESENTACION()==null) || 
             (this.CODLUGARPRESENTACION!=null &&
              this.CODLUGARPRESENTACION.equals(other.getCODLUGARPRESENTACION())));
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
        if (getREQUISITOS() != null) {
            _hashCode += getREQUISITOS().hashCode();
        }
        if (getPRESENTACIONPRESENCIAL() != null) {
            _hashCode += getPRESENTACIONPRESENCIAL().hashCode();
        }
        if (getCODLUGARPRESENTACION() != null) {
            _hashCode += getCODLUGARPRESENTACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONOTROSDATOS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONOTROSDATOS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REQUISITOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "REQUISITOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRESENTACIONPRESENCIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "PRESENTACIONPRESENCIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODLUGARPRESENTACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "CODLUGARPRESENTACION"));
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
