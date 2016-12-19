/**
 * DESTINATARIOSDESTINATARIO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class DESTINATARIOSDESTINATARIO  implements java.io.Serializable {
    private java.lang.String CODDESTINATARIO;

    public DESTINATARIOSDESTINATARIO() {
    }

    public DESTINATARIOSDESTINATARIO(
           java.lang.String CODDESTINATARIO) {
           this.CODDESTINATARIO = CODDESTINATARIO;
    }


    /**
     * Gets the CODDESTINATARIO value for this DESTINATARIOSDESTINATARIO.
     * 
     * @return CODDESTINATARIO
     */
    public java.lang.String getCODDESTINATARIO() {
        return CODDESTINATARIO;
    }


    /**
     * Sets the CODDESTINATARIO value for this DESTINATARIOSDESTINATARIO.
     * 
     * @param CODDESTINATARIO
     */
    public void setCODDESTINATARIO(java.lang.String CODDESTINATARIO) {
        this.CODDESTINATARIO = CODDESTINATARIO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DESTINATARIOSDESTINATARIO)) return false;
        DESTINATARIOSDESTINATARIO other = (DESTINATARIOSDESTINATARIO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODDESTINATARIO==null && other.getCODDESTINATARIO()==null) || 
             (this.CODDESTINATARIO!=null &&
              this.CODDESTINATARIO.equals(other.getCODDESTINATARIO())));
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
        if (getCODDESTINATARIO() != null) {
            _hashCode += getCODDESTINATARIO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DESTINATARIOSDESTINATARIO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">DESTINATARIOS>DESTINATARIO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODDESTINATARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "CODDESTINATARIO"));
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
