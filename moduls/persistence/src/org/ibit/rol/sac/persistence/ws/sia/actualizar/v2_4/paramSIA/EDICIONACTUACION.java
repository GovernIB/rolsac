/**
 * EDICIONACTUACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class EDICIONACTUACION  implements java.io.Serializable {
    private java.lang.String DENOMINACION;

    private java.lang.String FINALIDAD;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS;

    private java.lang.String ORGANOINICIA;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS;

    public EDICIONACTUACION() {
    }

    public EDICIONACTUACION(
           java.lang.String DENOMINACION,
           java.lang.String FINALIDAD,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS,
           java.lang.String ORGANOINICIA,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS) {
           this.DENOMINACION = DENOMINACION;
           this.FINALIDAD = FINALIDAD;
           this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
           this.DESTINATARIOS = DESTINATARIOS;
           this.ORGANOINICIA = ORGANOINICIA;
           this.NORMATIVAS = NORMATIVAS;
    }


    /**
     * Gets the DENOMINACION value for this EDICIONACTUACION.
     * 
     * @return DENOMINACION
     */
    public java.lang.String getDENOMINACION() {
        return DENOMINACION;
    }


    /**
     * Sets the DENOMINACION value for this EDICIONACTUACION.
     * 
     * @param DENOMINACION
     */
    public void setDENOMINACION(java.lang.String DENOMINACION) {
        this.DENOMINACION = DENOMINACION;
    }


    /**
     * Gets the FINALIDAD value for this EDICIONACTUACION.
     * 
     * @return FINALIDAD
     */
    public java.lang.String getFINALIDAD() {
        return FINALIDAD;
    }


    /**
     * Sets the FINALIDAD value for this EDICIONACTUACION.
     * 
     * @param FINALIDAD
     */
    public void setFINALIDAD(java.lang.String FINALIDAD) {
        this.FINALIDAD = FINALIDAD;
    }


    /**
     * Gets the ORGANISMORESPONSABLE value for this EDICIONACTUACION.
     * 
     * @return ORGANISMORESPONSABLE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE getORGANISMORESPONSABLE() {
        return ORGANISMORESPONSABLE;
    }


    /**
     * Sets the ORGANISMORESPONSABLE value for this EDICIONACTUACION.
     * 
     * @param ORGANISMORESPONSABLE
     */
    public void setORGANISMORESPONSABLE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE) {
        this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
    }


    /**
     * Gets the DESTINATARIOS value for this EDICIONACTUACION.
     * 
     * @return DESTINATARIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] getDESTINATARIOS() {
        return DESTINATARIOS;
    }


    /**
     * Sets the DESTINATARIOS value for this EDICIONACTUACION.
     * 
     * @param DESTINATARIOS
     */
    public void setDESTINATARIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS) {
        this.DESTINATARIOS = DESTINATARIOS;
    }


    /**
     * Gets the ORGANOINICIA value for this EDICIONACTUACION.
     * 
     * @return ORGANOINICIA
     */
    public java.lang.String getORGANOINICIA() {
        return ORGANOINICIA;
    }


    /**
     * Sets the ORGANOINICIA value for this EDICIONACTUACION.
     * 
     * @param ORGANOINICIA
     */
    public void setORGANOINICIA(java.lang.String ORGANOINICIA) {
        this.ORGANOINICIA = ORGANOINICIA;
    }


    /**
     * Gets the NORMATIVAS value for this EDICIONACTUACION.
     * 
     * @return NORMATIVAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[] getNORMATIVAS() {
        return NORMATIVAS;
    }


    /**
     * Sets the NORMATIVAS value for this EDICIONACTUACION.
     * 
     * @param NORMATIVAS
     */
    public void setNORMATIVAS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS) {
        this.NORMATIVAS = NORMATIVAS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONACTUACION)) return false;
        EDICIONACTUACION other = (EDICIONACTUACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DENOMINACION==null && other.getDENOMINACION()==null) || 
             (this.DENOMINACION!=null &&
              this.DENOMINACION.equals(other.getDENOMINACION()))) &&
            ((this.FINALIDAD==null && other.getFINALIDAD()==null) || 
             (this.FINALIDAD!=null &&
              this.FINALIDAD.equals(other.getFINALIDAD()))) &&
            ((this.ORGANISMORESPONSABLE==null && other.getORGANISMORESPONSABLE()==null) || 
             (this.ORGANISMORESPONSABLE!=null &&
              this.ORGANISMORESPONSABLE.equals(other.getORGANISMORESPONSABLE()))) &&
            ((this.DESTINATARIOS==null && other.getDESTINATARIOS()==null) || 
             (this.DESTINATARIOS!=null &&
              java.util.Arrays.equals(this.DESTINATARIOS, other.getDESTINATARIOS()))) &&
            ((this.ORGANOINICIA==null && other.getORGANOINICIA()==null) || 
             (this.ORGANOINICIA!=null &&
              this.ORGANOINICIA.equals(other.getORGANOINICIA()))) &&
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
        if (getDENOMINACION() != null) {
            _hashCode += getDENOMINACION().hashCode();
        }
        if (getFINALIDAD() != null) {
            _hashCode += getFINALIDAD().hashCode();
        }
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
        if (getORGANOINICIA() != null) {
            _hashCode += getORGANOINICIA().hashCode();
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
        new org.apache.axis.description.TypeDesc(EDICIONACTUACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONACTUACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DENOMINACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "DENOMINACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FINALIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "FINALIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("ORGANOINICIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ORGANOINICIA"));
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
