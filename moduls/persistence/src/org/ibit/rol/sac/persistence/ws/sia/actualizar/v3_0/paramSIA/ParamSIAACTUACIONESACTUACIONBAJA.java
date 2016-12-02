/**
 * ParamSIAACTUACIONESACTUACIONBAJA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA;

public class ParamSIAACTUACIONESACTUACIONBAJA  implements java.io.Serializable {
    private java.lang.String OPERACION;  // attribute

    public ParamSIAACTUACIONESACTUACIONBAJA() {
    }

    public ParamSIAACTUACIONESACTUACIONBAJA(
           java.lang.String OPERACION) {
           this.OPERACION = OPERACION;
    }


    /**
     * Gets the OPERACION value for this ParamSIAACTUACIONESACTUACIONBAJA.
     * 
     * @return OPERACION
     */
    public java.lang.String getOPERACION() {
        return OPERACION;
    }


    /**
     * Sets the OPERACION value for this ParamSIAACTUACIONESACTUACIONBAJA.
     * 
     * @param OPERACION
     */
    public void setOPERACION(java.lang.String OPERACION) {
        this.OPERACION = OPERACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamSIAACTUACIONESACTUACIONBAJA)) return false;
        ParamSIAACTUACIONESACTUACIONBAJA other = (ParamSIAACTUACIONESACTUACIONBAJA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
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
        if (getOPERACION() != null) {
            _hashCode += getOPERACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamSIAACTUACIONESACTUACIONBAJA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>BAJA"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("OPERACION");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OPERACION"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
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
