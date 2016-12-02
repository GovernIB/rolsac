/**
 * SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION  implements java.io.Serializable {
    private java.lang.String CODSISTEMAIDENTIFICACION;

    private java.lang.String OTROSISTEMAIDENTIFICACION;

    public SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION() {
    }

    public SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION(
           java.lang.String CODSISTEMAIDENTIFICACION,
           java.lang.String OTROSISTEMAIDENTIFICACION) {
           this.CODSISTEMAIDENTIFICACION = CODSISTEMAIDENTIFICACION;
           this.OTROSISTEMAIDENTIFICACION = OTROSISTEMAIDENTIFICACION;
    }


    /**
     * Gets the CODSISTEMAIDENTIFICACION value for this SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.
     * 
     * @return CODSISTEMAIDENTIFICACION
     */
    public java.lang.String getCODSISTEMAIDENTIFICACION() {
        return CODSISTEMAIDENTIFICACION;
    }


    /**
     * Sets the CODSISTEMAIDENTIFICACION value for this SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.
     * 
     * @param CODSISTEMAIDENTIFICACION
     */
    public void setCODSISTEMAIDENTIFICACION(java.lang.String CODSISTEMAIDENTIFICACION) {
        this.CODSISTEMAIDENTIFICACION = CODSISTEMAIDENTIFICACION;
    }


    /**
     * Gets the OTROSISTEMAIDENTIFICACION value for this SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.
     * 
     * @return OTROSISTEMAIDENTIFICACION
     */
    public java.lang.String getOTROSISTEMAIDENTIFICACION() {
        return OTROSISTEMAIDENTIFICACION;
    }


    /**
     * Sets the OTROSISTEMAIDENTIFICACION value for this SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.
     * 
     * @param OTROSISTEMAIDENTIFICACION
     */
    public void setOTROSISTEMAIDENTIFICACION(java.lang.String OTROSISTEMAIDENTIFICACION) {
        this.OTROSISTEMAIDENTIFICACION = OTROSISTEMAIDENTIFICACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION)) return false;
        SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION other = (SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODSISTEMAIDENTIFICACION==null && other.getCODSISTEMAIDENTIFICACION()==null) || 
             (this.CODSISTEMAIDENTIFICACION!=null &&
              this.CODSISTEMAIDENTIFICACION.equals(other.getCODSISTEMAIDENTIFICACION()))) &&
            ((this.OTROSISTEMAIDENTIFICACION==null && other.getOTROSISTEMAIDENTIFICACION()==null) || 
             (this.OTROSISTEMAIDENTIFICACION!=null &&
              this.OTROSISTEMAIDENTIFICACION.equals(other.getOTROSISTEMAIDENTIFICACION())));
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
        if (getCODSISTEMAIDENTIFICACION() != null) {
            _hashCode += getCODSISTEMAIDENTIFICACION().hashCode();
        }
        if (getOTROSISTEMAIDENTIFICACION() != null) {
            _hashCode += getOTROSISTEMAIDENTIFICACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODSISTEMAIDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "CODSISTEMAIDENTIFICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OTROSISTEMAIDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "OTROSISTEMAIDENTIFICACION"));
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
