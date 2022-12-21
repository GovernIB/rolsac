/**
 * SUBMATERIASSUBMATERIA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA;

public class SUBMATERIASSUBMATERIA  implements java.io.Serializable {
    private java.lang.String CODSUBMATERIA;

    public SUBMATERIASSUBMATERIA() {
    }

    public SUBMATERIASSUBMATERIA(
           java.lang.String CODSUBMATERIA) {
           this.CODSUBMATERIA = CODSUBMATERIA;
    }


    /**
     * Gets the CODSUBMATERIA value for this SUBMATERIASSUBMATERIA.
     * 
     * @return CODSUBMATERIA
     */
    public java.lang.String getCODSUBMATERIA() {
        return CODSUBMATERIA;
    }


    /**
     * Sets the CODSUBMATERIA value for this SUBMATERIASSUBMATERIA.
     * 
     * @param CODSUBMATERIA
     */
    public void setCODSUBMATERIA(java.lang.String CODSUBMATERIA) {
        this.CODSUBMATERIA = CODSUBMATERIA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SUBMATERIASSUBMATERIA)) return false;
        SUBMATERIASSUBMATERIA other = (SUBMATERIASSUBMATERIA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODSUBMATERIA==null && other.getCODSUBMATERIA()==null) || 
             (this.CODSUBMATERIA!=null &&
              this.CODSUBMATERIA.equals(other.getCODSUBMATERIA())));
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
        if (getCODSUBMATERIA() != null) {
            _hashCode += getCODSUBMATERIA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SUBMATERIASSUBMATERIA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">SUBMATERIAS>SUBMATERIA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODSUBMATERIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "CODSUBMATERIA"));
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
