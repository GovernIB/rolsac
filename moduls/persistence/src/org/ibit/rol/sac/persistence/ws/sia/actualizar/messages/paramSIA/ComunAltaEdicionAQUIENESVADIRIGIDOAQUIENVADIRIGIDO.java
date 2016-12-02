/**
 * ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA;

public class ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO  implements java.io.Serializable {
    private java.lang.String CODAQUIENVADIRIGIDO;

    public ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO() {
    }

    public ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO(
           java.lang.String CODAQUIENVADIRIGIDO) {
           this.CODAQUIENVADIRIGIDO = CODAQUIENVADIRIGIDO;
    }


    /**
     * Gets the CODAQUIENVADIRIGIDO value for this ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO.
     * 
     * @return CODAQUIENVADIRIGIDO
     */
    public java.lang.String getCODAQUIENVADIRIGIDO() {
        return CODAQUIENVADIRIGIDO;
    }


    /**
     * Sets the CODAQUIENVADIRIGIDO value for this ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO.
     * 
     * @param CODAQUIENVADIRIGIDO
     */
    public void setCODAQUIENVADIRIGIDO(java.lang.String CODAQUIENVADIRIGIDO) {
        this.CODAQUIENVADIRIGIDO = CODAQUIENVADIRIGIDO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO)) return false;
        ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO other = (ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODAQUIENVADIRIGIDO==null && other.getCODAQUIENVADIRIGIDO()==null) || 
             (this.CODAQUIENVADIRIGIDO!=null &&
              this.CODAQUIENVADIRIGIDO.equals(other.getCODAQUIENVADIRIGIDO())));
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
        if (getCODAQUIENVADIRIGIDO() != null) {
            _hashCode += getCODAQUIENVADIRIGIDO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>AQUIENESVADIRIGIDO>AQUIENVADIRIGIDO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODAQUIENVADIRIGIDO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "CODAQUIENVADIRIGIDO"));
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
