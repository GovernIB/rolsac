/**
 * PRESTACIONESPRESTACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class PRESTACIONESPRESTACION  implements java.io.Serializable {
    private java.lang.String CODTIPOPRESTACION;

    private java.lang.String OTRO;

    public PRESTACIONESPRESTACION() {
    }

    public PRESTACIONESPRESTACION(
           java.lang.String CODTIPOPRESTACION,
           java.lang.String OTRO) {
           this.CODTIPOPRESTACION = CODTIPOPRESTACION;
           this.OTRO = OTRO;
    }


    /**
     * Gets the CODTIPOPRESTACION value for this PRESTACIONESPRESTACION.
     * 
     * @return CODTIPOPRESTACION
     */
    public java.lang.String getCODTIPOPRESTACION() {
        return CODTIPOPRESTACION;
    }


    /**
     * Sets the CODTIPOPRESTACION value for this PRESTACIONESPRESTACION.
     * 
     * @param CODTIPOPRESTACION
     */
    public void setCODTIPOPRESTACION(java.lang.String CODTIPOPRESTACION) {
        this.CODTIPOPRESTACION = CODTIPOPRESTACION;
    }


    /**
     * Gets the OTRO value for this PRESTACIONESPRESTACION.
     * 
     * @return OTRO
     */
    public java.lang.String getOTRO() {
        return OTRO;
    }


    /**
     * Sets the OTRO value for this PRESTACIONESPRESTACION.
     * 
     * @param OTRO
     */
    public void setOTRO(java.lang.String OTRO) {
        this.OTRO = OTRO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PRESTACIONESPRESTACION)) return false;
        PRESTACIONESPRESTACION other = (PRESTACIONESPRESTACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODTIPOPRESTACION==null && other.getCODTIPOPRESTACION()==null) || 
             (this.CODTIPOPRESTACION!=null &&
              this.CODTIPOPRESTACION.equals(other.getCODTIPOPRESTACION()))) &&
            ((this.OTRO==null && other.getOTRO()==null) || 
             (this.OTRO!=null &&
              this.OTRO.equals(other.getOTRO())));
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
        if (getCODTIPOPRESTACION() != null) {
            _hashCode += getCODTIPOPRESTACION().hashCode();
        }
        if (getOTRO() != null) {
            _hashCode += getOTRO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PRESTACIONESPRESTACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">PRESTACIONES>PRESTACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTIPOPRESTACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "CODTIPOPRESTACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OTRO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "OTRO"));
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
