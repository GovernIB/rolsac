/**
 * EDICIONPROCEDIMIENTOCOMUN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class EDICIONPROCEDIMIENTOCOMUN  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS;

    private java.lang.String CODNIVELADMINISTRACIONELECTRONICA;

    private java.lang.String URL;

    private java.lang.String ORGANOINICIA;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.INICIOS INICIOS;

    private java.lang.String FINVIA;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS;

    public EDICIONPROCEDIMIENTOCOMUN() {
    }

    public EDICIONPROCEDIMIENTOCOMUN(
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS,
           java.lang.String CODNIVELADMINISTRACIONELECTRONICA,
           java.lang.String URL,
           java.lang.String ORGANOINICIA,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.INICIOS INICIOS,
           java.lang.String FINVIA,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS) {
           this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
           this.DESTINATARIOS = DESTINATARIOS;
           this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
           this.URL = URL;
           this.ORGANOINICIA = ORGANOINICIA;
           this.INICIOS = INICIOS;
           this.FINVIA = FINVIA;
           this.NORMATIVAS = NORMATIVAS;
    }


    /**
     * Gets the ORGANISMORESPONSABLE value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @return ORGANISMORESPONSABLE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE getORGANISMORESPONSABLE() {
        return ORGANISMORESPONSABLE;
    }


    /**
     * Sets the ORGANISMORESPONSABLE value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @param ORGANISMORESPONSABLE
     */
    public void setORGANISMORESPONSABLE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE) {
        this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
    }


    /**
     * Gets the DESTINATARIOS value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @return DESTINATARIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] getDESTINATARIOS() {
        return DESTINATARIOS;
    }


    /**
     * Sets the DESTINATARIOS value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @param DESTINATARIOS
     */
    public void setDESTINATARIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS) {
        this.DESTINATARIOS = DESTINATARIOS;
    }


    /**
     * Gets the CODNIVELADMINISTRACIONELECTRONICA value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @return CODNIVELADMINISTRACIONELECTRONICA
     */
    public java.lang.String getCODNIVELADMINISTRACIONELECTRONICA() {
        return CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Sets the CODNIVELADMINISTRACIONELECTRONICA value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @param CODNIVELADMINISTRACIONELECTRONICA
     */
    public void setCODNIVELADMINISTRACIONELECTRONICA(java.lang.String CODNIVELADMINISTRACIONELECTRONICA) {
        this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Gets the URL value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @return URL
     */
    public java.lang.String getURL() {
        return URL;
    }


    /**
     * Sets the URL value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @param URL
     */
    public void setURL(java.lang.String URL) {
        this.URL = URL;
    }


    /**
     * Gets the ORGANOINICIA value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @return ORGANOINICIA
     */
    public java.lang.String getORGANOINICIA() {
        return ORGANOINICIA;
    }


    /**
     * Sets the ORGANOINICIA value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @param ORGANOINICIA
     */
    public void setORGANOINICIA(java.lang.String ORGANOINICIA) {
        this.ORGANOINICIA = ORGANOINICIA;
    }


    /**
     * Gets the INICIOS value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @return INICIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.INICIOS getINICIOS() {
        return INICIOS;
    }


    /**
     * Sets the INICIOS value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @param INICIOS
     */
    public void setINICIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.INICIOS INICIOS) {
        this.INICIOS = INICIOS;
    }


    /**
     * Gets the FINVIA value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @return FINVIA
     */
    public java.lang.String getFINVIA() {
        return FINVIA;
    }


    /**
     * Sets the FINVIA value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @param FINVIA
     */
    public void setFINVIA(java.lang.String FINVIA) {
        this.FINVIA = FINVIA;
    }


    /**
     * Gets the NORMATIVAS value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @return NORMATIVAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[] getNORMATIVAS() {
        return NORMATIVAS;
    }


    /**
     * Sets the NORMATIVAS value for this EDICIONPROCEDIMIENTOCOMUN.
     * 
     * @param NORMATIVAS
     */
    public void setNORMATIVAS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS) {
        this.NORMATIVAS = NORMATIVAS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONPROCEDIMIENTOCOMUN)) return false;
        EDICIONPROCEDIMIENTOCOMUN other = (EDICIONPROCEDIMIENTOCOMUN) obj;
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
              this.URL.equals(other.getURL()))) &&
            ((this.ORGANOINICIA==null && other.getORGANOINICIA()==null) || 
             (this.ORGANOINICIA!=null &&
              this.ORGANOINICIA.equals(other.getORGANOINICIA()))) &&
            ((this.INICIOS==null && other.getINICIOS()==null) || 
             (this.INICIOS!=null &&
              this.INICIOS.equals(other.getINICIOS()))) &&
            ((this.FINVIA==null && other.getFINVIA()==null) || 
             (this.FINVIA!=null &&
              this.FINVIA.equals(other.getFINVIA()))) &&
            ((this.NORMATIVAS==null && other.getNORMATIVAS()==null) || 
             (this.NORMATIVAS!=null &&
              java.util.Arrays.equals(this.NORMATIVAS, other.getNORMATIVAS())));
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
        if (getORGANOINICIA() != null) {
            _hashCode += getORGANOINICIA().hashCode();
        }
        if (getINICIOS() != null) {
            _hashCode += getINICIOS().hashCode();
        }
        if (getFINVIA() != null) {
            _hashCode += getFINVIA().hashCode();
        }
        if (getNORMATIVAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNORMATIVAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNORMATIVAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONPROCEDIMIENTOCOMUN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONPROCEDIMIENTOCOMUN"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGANOINICIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ORGANOINICIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INICIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "INICIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "INICIOS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FINVIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "FINVIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NORMATIVAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "NORMATIVAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">NORMATIVAS>NORMATIVA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "NORMATIVA"));
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
