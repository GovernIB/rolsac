/**
 * FORMULARIOSFORMULARIO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA;

public class FORMULARIOSFORMULARIO  implements java.io.Serializable {
    private java.lang.String TITULOFORMUL;

    private java.lang.String URLFORMUL;

    public FORMULARIOSFORMULARIO() {
    }

    public FORMULARIOSFORMULARIO(
           java.lang.String TITULOFORMUL,
           java.lang.String URLFORMUL) {
           this.TITULOFORMUL = TITULOFORMUL;
           this.URLFORMUL = URLFORMUL;
    }


    /**
     * Gets the TITULOFORMUL value for this FORMULARIOSFORMULARIO.
     * 
     * @return TITULOFORMUL
     */
    public java.lang.String getTITULOFORMUL() {
        return TITULOFORMUL;
    }


    /**
     * Sets the TITULOFORMUL value for this FORMULARIOSFORMULARIO.
     * 
     * @param TITULOFORMUL
     */
    public void setTITULOFORMUL(java.lang.String TITULOFORMUL) {
        this.TITULOFORMUL = TITULOFORMUL;
    }


    /**
     * Gets the URLFORMUL value for this FORMULARIOSFORMULARIO.
     * 
     * @return URLFORMUL
     */
    public java.lang.String getURLFORMUL() {
        return URLFORMUL;
    }


    /**
     * Sets the URLFORMUL value for this FORMULARIOSFORMULARIO.
     * 
     * @param URLFORMUL
     */
    public void setURLFORMUL(java.lang.String URLFORMUL) {
        this.URLFORMUL = URLFORMUL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FORMULARIOSFORMULARIO)) return false;
        FORMULARIOSFORMULARIO other = (FORMULARIOSFORMULARIO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TITULOFORMUL==null && other.getTITULOFORMUL()==null) || 
             (this.TITULOFORMUL!=null &&
              this.TITULOFORMUL.equals(other.getTITULOFORMUL()))) &&
            ((this.URLFORMUL==null && other.getURLFORMUL()==null) || 
             (this.URLFORMUL!=null &&
              this.URLFORMUL.equals(other.getURLFORMUL())));
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
        if (getTITULOFORMUL() != null) {
            _hashCode += getTITULOFORMUL().hashCode();
        }
        if (getURLFORMUL() != null) {
            _hashCode += getURLFORMUL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FORMULARIOSFORMULARIO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">FORMULARIOS>FORMULARIO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TITULOFORMUL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "TITULOFORMUL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URLFORMUL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "URLFORMUL"));
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