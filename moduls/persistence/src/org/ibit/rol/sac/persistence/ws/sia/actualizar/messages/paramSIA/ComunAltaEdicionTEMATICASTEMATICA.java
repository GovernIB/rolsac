/**
 * ComunAltaEdicionTEMATICASTEMATICA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA;

public class ComunAltaEdicionTEMATICASTEMATICA  implements java.io.Serializable {
    private java.lang.String CODTEMATICA;

    public ComunAltaEdicionTEMATICASTEMATICA() {
    }

    public ComunAltaEdicionTEMATICASTEMATICA(
           java.lang.String CODTEMATICA) {
           this.CODTEMATICA = CODTEMATICA;
    }


    /**
     * Gets the CODTEMATICA value for this ComunAltaEdicionTEMATICASTEMATICA.
     * 
     * @return CODTEMATICA
     */
    public java.lang.String getCODTEMATICA() {
        return CODTEMATICA;
    }


    /**
     * Sets the CODTEMATICA value for this ComunAltaEdicionTEMATICASTEMATICA.
     * 
     * @param CODTEMATICA
     */
    public void setCODTEMATICA(java.lang.String CODTEMATICA) {
        this.CODTEMATICA = CODTEMATICA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionTEMATICASTEMATICA)) return false;
        ComunAltaEdicionTEMATICASTEMATICA other = (ComunAltaEdicionTEMATICASTEMATICA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODTEMATICA==null && other.getCODTEMATICA()==null) || 
             (this.CODTEMATICA!=null &&
              this.CODTEMATICA.equals(other.getCODTEMATICA())));
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
        if (getCODTEMATICA() != null) {
            _hashCode += getCODTEMATICA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionTEMATICASTEMATICA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>TEMATICAS>TEMATICA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTEMATICA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "CODTEMATICA"));
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
