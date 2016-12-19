/**
 * HECHOSVITALESHECHOVITAL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA;

public class HECHOSVITALESHECHOVITAL  implements java.io.Serializable {
    private java.lang.String CODHECHOVITAL;

    public HECHOSVITALESHECHOVITAL() {
    }

    public HECHOSVITALESHECHOVITAL(
           java.lang.String CODHECHOVITAL) {
           this.CODHECHOVITAL = CODHECHOVITAL;
    }


    /**
     * Gets the CODHECHOVITAL value for this HECHOSVITALESHECHOVITAL.
     * 
     * @return CODHECHOVITAL
     */
    public java.lang.String getCODHECHOVITAL() {
        return CODHECHOVITAL;
    }


    /**
     * Sets the CODHECHOVITAL value for this HECHOSVITALESHECHOVITAL.
     * 
     * @param CODHECHOVITAL
     */
    public void setCODHECHOVITAL(java.lang.String CODHECHOVITAL) {
        this.CODHECHOVITAL = CODHECHOVITAL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HECHOSVITALESHECHOVITAL)) return false;
        HECHOSVITALESHECHOVITAL other = (HECHOSVITALESHECHOVITAL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODHECHOVITAL==null && other.getCODHECHOVITAL()==null) || 
             (this.CODHECHOVITAL!=null &&
              this.CODHECHOVITAL.equals(other.getCODHECHOVITAL())));
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
        if (getCODHECHOVITAL() != null) {
            _hashCode += getCODHECHOVITAL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HECHOSVITALESHECHOVITAL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">HECHOSVITALES>HECHOVITAL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODHECHOVITAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "CODHECHOVITAL"));
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
