/**
 * ParamSIAACTUACIONESACTUACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class ParamSIAACTUACIONESACTUACION  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONALTA ALTA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONEDICION EDICION;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONBAJA BAJA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVACION ACTIVACION;

    private java.lang.String CODIGOORIGEN;  // attribute

    private java.lang.String CODIGOACTUACION;  // attribute

    public ParamSIAACTUACIONESACTUACION() {
    }

    public ParamSIAACTUACIONESACTUACION(
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONALTA ALTA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONEDICION EDICION,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONBAJA BAJA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVACION ACTIVACION,
           java.lang.String CODIGOORIGEN,
           java.lang.String CODIGOACTUACION) {
           this.ALTA = ALTA;
           this.EDICION = EDICION;
           this.BAJA = BAJA;
           this.ACTIVACION = ACTIVACION;
           this.CODIGOORIGEN = CODIGOORIGEN;
           this.CODIGOACTUACION = CODIGOACTUACION;
    }


    /**
     * Gets the ALTA value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return ALTA
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONALTA getALTA() {
        return ALTA;
    }


    /**
     * Sets the ALTA value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param ALTA
     */
    public void setALTA(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONALTA ALTA) {
        this.ALTA = ALTA;
    }


    /**
     * Gets the EDICION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return EDICION
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONEDICION getEDICION() {
        return EDICION;
    }


    /**
     * Sets the EDICION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param EDICION
     */
    public void setEDICION(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONEDICION EDICION) {
        this.EDICION = EDICION;
    }


    /**
     * Gets the BAJA value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return BAJA
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONBAJA getBAJA() {
        return BAJA;
    }


    /**
     * Sets the BAJA value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param BAJA
     */
    public void setBAJA(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONBAJA BAJA) {
        this.BAJA = BAJA;
    }


    /**
     * Gets the ACTIVACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return ACTIVACION
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVACION getACTIVACION() {
        return ACTIVACION;
    }


    /**
     * Sets the ACTIVACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param ACTIVACION
     */
    public void setACTIVACION(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVACION ACTIVACION) {
        this.ACTIVACION = ACTIVACION;
    }


    /**
     * Gets the CODIGOORIGEN value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CODIGOORIGEN
     */
    public java.lang.String getCODIGOORIGEN() {
        return CODIGOORIGEN;
    }


    /**
     * Sets the CODIGOORIGEN value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CODIGOORIGEN
     */
    public void setCODIGOORIGEN(java.lang.String CODIGOORIGEN) {
        this.CODIGOORIGEN = CODIGOORIGEN;
    }


    /**
     * Gets the CODIGOACTUACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CODIGOACTUACION
     */
    public java.lang.String getCODIGOACTUACION() {
        return CODIGOACTUACION;
    }


    /**
     * Sets the CODIGOACTUACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CODIGOACTUACION
     */
    public void setCODIGOACTUACION(java.lang.String CODIGOACTUACION) {
        this.CODIGOACTUACION = CODIGOACTUACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamSIAACTUACIONESACTUACION)) return false;
        ParamSIAACTUACIONESACTUACION other = (ParamSIAACTUACIONESACTUACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ALTA==null && other.getALTA()==null) || 
             (this.ALTA!=null &&
              this.ALTA.equals(other.getALTA()))) &&
            ((this.EDICION==null && other.getEDICION()==null) || 
             (this.EDICION!=null &&
              this.EDICION.equals(other.getEDICION()))) &&
            ((this.BAJA==null && other.getBAJA()==null) || 
             (this.BAJA!=null &&
              this.BAJA.equals(other.getBAJA()))) &&
            ((this.ACTIVACION==null && other.getACTIVACION()==null) || 
             (this.ACTIVACION!=null &&
              this.ACTIVACION.equals(other.getACTIVACION()))) &&
            ((this.CODIGOORIGEN==null && other.getCODIGOORIGEN()==null) || 
             (this.CODIGOORIGEN!=null &&
              this.CODIGOORIGEN.equals(other.getCODIGOORIGEN()))) &&
            ((this.CODIGOACTUACION==null && other.getCODIGOACTUACION()==null) || 
             (this.CODIGOACTUACION!=null &&
              this.CODIGOACTUACION.equals(other.getCODIGOACTUACION())));
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
        if (getALTA() != null) {
            _hashCode += getALTA().hashCode();
        }
        if (getEDICION() != null) {
            _hashCode += getEDICION().hashCode();
        }
        if (getBAJA() != null) {
            _hashCode += getBAJA().hashCode();
        }
        if (getACTIVACION() != null) {
            _hashCode += getACTIVACION().hashCode();
        }
        if (getCODIGOORIGEN() != null) {
            _hashCode += getCODIGOORIGEN().hashCode();
        }
        if (getCODIGOACTUACION() != null) {
            _hashCode += getCODIGOACTUACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamSIAACTUACIONESACTUACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("CODIGOORIGEN");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CODIGOORIGEN"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("CODIGOACTUACION");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CODIGOACTUACION"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ALTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ALTA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EDICION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>EDICION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BAJA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "BAJA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>BAJA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTIVACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ACTIVACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ACTIVACION"));
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
