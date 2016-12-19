/**
 * VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES  implements java.io.Serializable {
    private java.lang.String ANIO;

    private java.lang.String PERIODO;

    private java.lang.String VOLTOTAL;

    private java.lang.String VOLELEC;

    private java.lang.String VOLCERTIFICADOELEC;

    private java.lang.String VOLDNIELEC;

    public VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES() {
    }

    public VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES(
           java.lang.String ANIO,
           java.lang.String PERIODO,
           java.lang.String VOLTOTAL,
           java.lang.String VOLELEC,
           java.lang.String VOLCERTIFICADOELEC,
           java.lang.String VOLDNIELEC) {
           this.ANIO = ANIO;
           this.PERIODO = PERIODO;
           this.VOLTOTAL = VOLTOTAL;
           this.VOLELEC = VOLELEC;
           this.VOLCERTIFICADOELEC = VOLCERTIFICADOELEC;
           this.VOLDNIELEC = VOLDNIELEC;
    }


    /**
     * Gets the ANIO value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @return ANIO
     */
    public java.lang.String getANIO() {
        return ANIO;
    }


    /**
     * Sets the ANIO value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @param ANIO
     */
    public void setANIO(java.lang.String ANIO) {
        this.ANIO = ANIO;
    }


    /**
     * Gets the PERIODO value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @return PERIODO
     */
    public java.lang.String getPERIODO() {
        return PERIODO;
    }


    /**
     * Sets the PERIODO value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @param PERIODO
     */
    public void setPERIODO(java.lang.String PERIODO) {
        this.PERIODO = PERIODO;
    }


    /**
     * Gets the VOLTOTAL value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @return VOLTOTAL
     */
    public java.lang.String getVOLTOTAL() {
        return VOLTOTAL;
    }


    /**
     * Sets the VOLTOTAL value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @param VOLTOTAL
     */
    public void setVOLTOTAL(java.lang.String VOLTOTAL) {
        this.VOLTOTAL = VOLTOTAL;
    }


    /**
     * Gets the VOLELEC value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @return VOLELEC
     */
    public java.lang.String getVOLELEC() {
        return VOLELEC;
    }


    /**
     * Sets the VOLELEC value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @param VOLELEC
     */
    public void setVOLELEC(java.lang.String VOLELEC) {
        this.VOLELEC = VOLELEC;
    }


    /**
     * Gets the VOLCERTIFICADOELEC value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @return VOLCERTIFICADOELEC
     */
    public java.lang.String getVOLCERTIFICADOELEC() {
        return VOLCERTIFICADOELEC;
    }


    /**
     * Sets the VOLCERTIFICADOELEC value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @param VOLCERTIFICADOELEC
     */
    public void setVOLCERTIFICADOELEC(java.lang.String VOLCERTIFICADOELEC) {
        this.VOLCERTIFICADOELEC = VOLCERTIFICADOELEC;
    }


    /**
     * Gets the VOLDNIELEC value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @return VOLDNIELEC
     */
    public java.lang.String getVOLDNIELEC() {
        return VOLDNIELEC;
    }


    /**
     * Sets the VOLDNIELEC value for this VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.
     * 
     * @param VOLDNIELEC
     */
    public void setVOLDNIELEC(java.lang.String VOLDNIELEC) {
        this.VOLDNIELEC = VOLDNIELEC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES)) return false;
        VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES other = (VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ANIO==null && other.getANIO()==null) || 
             (this.ANIO!=null &&
              this.ANIO.equals(other.getANIO()))) &&
            ((this.PERIODO==null && other.getPERIODO()==null) || 
             (this.PERIODO!=null &&
              this.PERIODO.equals(other.getPERIODO()))) &&
            ((this.VOLTOTAL==null && other.getVOLTOTAL()==null) || 
             (this.VOLTOTAL!=null &&
              this.VOLTOTAL.equals(other.getVOLTOTAL()))) &&
            ((this.VOLELEC==null && other.getVOLELEC()==null) || 
             (this.VOLELEC!=null &&
              this.VOLELEC.equals(other.getVOLELEC()))) &&
            ((this.VOLCERTIFICADOELEC==null && other.getVOLCERTIFICADOELEC()==null) || 
             (this.VOLCERTIFICADOELEC!=null &&
              this.VOLCERTIFICADOELEC.equals(other.getVOLCERTIFICADOELEC()))) &&
            ((this.VOLDNIELEC==null && other.getVOLDNIELEC()==null) || 
             (this.VOLDNIELEC!=null &&
              this.VOLDNIELEC.equals(other.getVOLDNIELEC())));
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
        if (getANIO() != null) {
            _hashCode += getANIO().hashCode();
        }
        if (getPERIODO() != null) {
            _hashCode += getPERIODO().hashCode();
        }
        if (getVOLTOTAL() != null) {
            _hashCode += getVOLTOTAL().hashCode();
        }
        if (getVOLELEC() != null) {
            _hashCode += getVOLELEC().hashCode();
        }
        if (getVOLCERTIFICADOELEC() != null) {
            _hashCode += getVOLCERTIFICADOELEC().hashCode();
        }
        if (getVOLDNIELEC() != null) {
            _hashCode += getVOLDNIELEC().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ANIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "PERIODO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLTOTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "VOLTOTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLELEC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "VOLELEC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLCERTIFICADOELEC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "VOLCERTIFICADOELEC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLDNIELEC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "VOLDNIELEC"));
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
