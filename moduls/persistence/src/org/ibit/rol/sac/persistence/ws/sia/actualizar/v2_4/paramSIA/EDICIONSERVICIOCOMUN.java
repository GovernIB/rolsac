/**
 * EDICIONSERVICIOCOMUN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class EDICIONSERVICIOCOMUN  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS;

    private java.lang.String CODNIVELADMINISTRACIONELECTRONICA;

    private java.lang.String URL;

    public EDICIONSERVICIOCOMUN() {
    }

    public EDICIONSERVICIOCOMUN(
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS,
           java.lang.String CODNIVELADMINISTRACIONELECTRONICA,
           java.lang.String URL) {
           this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
           this.DESTINATARIOS = DESTINATARIOS;
           this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
           this.URL = URL;
    }


    /**
     * Gets the ORGANISMORESPONSABLE value for this EDICIONSERVICIOCOMUN.
     * 
     * @return ORGANISMORESPONSABLE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE getORGANISMORESPONSABLE() {
        return ORGANISMORESPONSABLE;
    }


    /**
     * Sets the ORGANISMORESPONSABLE value for this EDICIONSERVICIOCOMUN.
     * 
     * @param ORGANISMORESPONSABLE
     */
    public void setORGANISMORESPONSABLE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE) {
        this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
    }


    /**
     * Gets the DESTINATARIOS value for this EDICIONSERVICIOCOMUN.
     * 
     * @return DESTINATARIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] getDESTINATARIOS() {
        return DESTINATARIOS;
    }


    /**
     * Sets the DESTINATARIOS value for this EDICIONSERVICIOCOMUN.
     * 
     * @param DESTINATARIOS
     */
    public void setDESTINATARIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS) {
        this.DESTINATARIOS = DESTINATARIOS;
    }


    /**
     * Gets the CODNIVELADMINISTRACIONELECTRONICA value for this EDICIONSERVICIOCOMUN.
     * 
     * @return CODNIVELADMINISTRACIONELECTRONICA
     */
    public java.lang.String getCODNIVELADMINISTRACIONELECTRONICA() {
        return CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Sets the CODNIVELADMINISTRACIONELECTRONICA value for this EDICIONSERVICIOCOMUN.
     * 
     * @param CODNIVELADMINISTRACIONELECTRONICA
     */
    public void setCODNIVELADMINISTRACIONELECTRONICA(java.lang.String CODNIVELADMINISTRACIONELECTRONICA) {
        this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Gets the URL value for this EDICIONSERVICIOCOMUN.
     * 
     * @return URL
     */
    public java.lang.String getURL() {
        return URL;
    }


    /**
     * Sets the URL value for this EDICIONSERVICIOCOMUN.
     * 
     * @param URL
     */
    public void setURL(java.lang.String URL) {
        this.URL = URL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONSERVICIOCOMUN)) return false;
        EDICIONSERVICIOCOMUN other = (EDICIONSERVICIOCOMUN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ORGANISMORESPONSABLE==null && other.getORGANISMORESPONSABLE()==null) || 
             (this.ORGANISMORESPONSABLE!=null &&
              this.ORGANISMORESPONSABLE.equals(other.getORGANISMORESPONSABLE()))) &&
            ((this.DESTINATARIOS==null && other.getDESTINATARIOS()==null) || 
             (this.DESTINATARIOS!=null &&
              java.util.Arrays.equals(this.DESTINATARIOS, other.getDESTINATARIOS()))) &&
            ((this.CODNIVELADMINISTRACIONELECTRONICA==null && other.getCODNIVELADMINISTRACIONELECTRONICA()==null) || 
             (this.CODNIVELADMINISTRACIONELECTRONICA!=null &&
              this.CODNIVELADMINISTRACIONELECTRONICA.equals(other.getCODNIVELADMINISTRACIONELECTRONICA()))) &&
            ((this.URL==null && other.getURL()==null) || 
             (this.URL!=null &&
              this.URL.equals(other.getURL())));
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
        if (getORGANISMORESPONSABLE() != null) {
            _hashCode += getORGANISMORESPONSABLE().hashCode();
        }
        if (getDESTINATARIOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDESTINATARIOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDESTINATARIOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODNIVELADMINISTRACIONELECTRONICA() != null) {
            _hashCode += getCODNIVELADMINISTRACIONELECTRONICA().hashCode();
        }
        if (getURL() != null) {
            _hashCode += getURL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONSERVICIOCOMUN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONSERVICIOCOMUN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGANISMORESPONSABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ORGANISMORESPONSABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ORGANISMORESPONSABLE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESTINATARIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "DESTINATARIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">DESTINATARIOS>DESTINATARIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "DESTINATARIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODNIVELADMINISTRACIONELECTRONICA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "CODNIVELADMINISTRACIONELECTRONICA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "URL"));
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
