/**
 * NOTIFICACIONESNOTIFICACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA;

public class NOTIFICACIONESNOTIFICACION  implements java.io.Serializable {
    private java.lang.String CODNOTIFICACION;

    public NOTIFICACIONESNOTIFICACION() {
    }

    public NOTIFICACIONESNOTIFICACION(
           java.lang.String CODNOTIFICACION) {
           this.CODNOTIFICACION = CODNOTIFICACION;
    }


    /**
     * Gets the CODNOTIFICACION value for this NOTIFICACIONESNOTIFICACION.
     * 
     * @return CODNOTIFICACION
     */
    public java.lang.String getCODNOTIFICACION() {
        return CODNOTIFICACION;
    }


    /**
     * Sets the CODNOTIFICACION value for this NOTIFICACIONESNOTIFICACION.
     * 
     * @param CODNOTIFICACION
     */
    public void setCODNOTIFICACION(java.lang.String CODNOTIFICACION) {
        this.CODNOTIFICACION = CODNOTIFICACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NOTIFICACIONESNOTIFICACION)) return false;
        NOTIFICACIONESNOTIFICACION other = (NOTIFICACIONESNOTIFICACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODNOTIFICACION==null && other.getCODNOTIFICACION()==null) || 
             (this.CODNOTIFICACION!=null &&
              this.CODNOTIFICACION.equals(other.getCODNOTIFICACION())));
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
        if (getCODNOTIFICACION() != null) {
            _hashCode += getCODNOTIFICACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NOTIFICACIONESNOTIFICACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">NOTIFICACIONES>NOTIFICACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODNOTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODNOTIFICACION"));
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
