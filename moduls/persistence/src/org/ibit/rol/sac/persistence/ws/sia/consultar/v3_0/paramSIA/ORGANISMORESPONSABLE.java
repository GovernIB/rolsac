/**
 * ORGANISMORESPONSABLE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA;

public class ORGANISMORESPONSABLE  implements java.io.Serializable {
    private java.lang.String CODORGANISMORESPONSABLEN1;

    private java.lang.String CODORGANISMORESPONSABLEN2;

    public ORGANISMORESPONSABLE() {
    }

    public ORGANISMORESPONSABLE(
           java.lang.String CODORGANISMORESPONSABLEN1,
           java.lang.String CODORGANISMORESPONSABLEN2) {
           this.CODORGANISMORESPONSABLEN1 = CODORGANISMORESPONSABLEN1;
           this.CODORGANISMORESPONSABLEN2 = CODORGANISMORESPONSABLEN2;
    }


    /**
     * Gets the CODORGANISMORESPONSABLEN1 value for this ORGANISMORESPONSABLE.
     * 
     * @return CODORGANISMORESPONSABLEN1
     */
    public java.lang.String getCODORGANISMORESPONSABLEN1() {
        return CODORGANISMORESPONSABLEN1;
    }


    /**
     * Sets the CODORGANISMORESPONSABLEN1 value for this ORGANISMORESPONSABLE.
     * 
     * @param CODORGANISMORESPONSABLEN1
     */
    public void setCODORGANISMORESPONSABLEN1(java.lang.String CODORGANISMORESPONSABLEN1) {
        this.CODORGANISMORESPONSABLEN1 = CODORGANISMORESPONSABLEN1;
    }


    /**
     * Gets the CODORGANISMORESPONSABLEN2 value for this ORGANISMORESPONSABLE.
     * 
     * @return CODORGANISMORESPONSABLEN2
     */
    public java.lang.String getCODORGANISMORESPONSABLEN2() {
        return CODORGANISMORESPONSABLEN2;
    }


    /**
     * Sets the CODORGANISMORESPONSABLEN2 value for this ORGANISMORESPONSABLE.
     * 
     * @param CODORGANISMORESPONSABLEN2
     */
    public void setCODORGANISMORESPONSABLEN2(java.lang.String CODORGANISMORESPONSABLEN2) {
        this.CODORGANISMORESPONSABLEN2 = CODORGANISMORESPONSABLEN2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ORGANISMORESPONSABLE)) return false;
        ORGANISMORESPONSABLE other = (ORGANISMORESPONSABLE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODORGANISMORESPONSABLEN1==null && other.getCODORGANISMORESPONSABLEN1()==null) || 
             (this.CODORGANISMORESPONSABLEN1!=null &&
              this.CODORGANISMORESPONSABLEN1.equals(other.getCODORGANISMORESPONSABLEN1()))) &&
            ((this.CODORGANISMORESPONSABLEN2==null && other.getCODORGANISMORESPONSABLEN2()==null) || 
             (this.CODORGANISMORESPONSABLEN2!=null &&
              this.CODORGANISMORESPONSABLEN2.equals(other.getCODORGANISMORESPONSABLEN2())));
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
        if (getCODORGANISMORESPONSABLEN1() != null) {
            _hashCode += getCODORGANISMORESPONSABLEN1().hashCode();
        }
        if (getCODORGANISMORESPONSABLEN2() != null) {
            _hashCode += getCODORGANISMORESPONSABLEN2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ORGANISMORESPONSABLE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "ORGANISMORESPONSABLE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODORGANISMORESPONSABLEN1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "CODORGANISMORESPONSABLEN1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODORGANISMORESPONSABLEN2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "CODORGANISMORESPONSABLEN2"));
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
