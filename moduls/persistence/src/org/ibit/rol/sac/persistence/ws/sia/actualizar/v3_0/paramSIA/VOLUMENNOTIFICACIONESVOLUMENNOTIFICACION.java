/**
 * VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA;

public class VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION  implements java.io.Serializable {
    private java.lang.String ANIO;

    private java.lang.String PERIODO;

    private java.lang.String NUMPAPEL;

    private java.lang.String NUMCOMPARECENCIA;

    private java.lang.String NUMDEH;

    public VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION() {
    }

    public VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION(
           java.lang.String ANIO,
           java.lang.String PERIODO,
           java.lang.String NUMPAPEL,
           java.lang.String NUMCOMPARECENCIA,
           java.lang.String NUMDEH) {
           this.ANIO = ANIO;
           this.PERIODO = PERIODO;
           this.NUMPAPEL = NUMPAPEL;
           this.NUMCOMPARECENCIA = NUMCOMPARECENCIA;
           this.NUMDEH = NUMDEH;
    }


    /**
     * Gets the ANIO value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @return ANIO
     */
    public java.lang.String getANIO() {
        return ANIO;
    }


    /**
     * Sets the ANIO value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @param ANIO
     */
    public void setANIO(java.lang.String ANIO) {
        this.ANIO = ANIO;
    }


    /**
     * Gets the PERIODO value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @return PERIODO
     */
    public java.lang.String getPERIODO() {
        return PERIODO;
    }


    /**
     * Sets the PERIODO value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @param PERIODO
     */
    public void setPERIODO(java.lang.String PERIODO) {
        this.PERIODO = PERIODO;
    }


    /**
     * Gets the NUMPAPEL value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @return NUMPAPEL
     */
    public java.lang.String getNUMPAPEL() {
        return NUMPAPEL;
    }


    /**
     * Sets the NUMPAPEL value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @param NUMPAPEL
     */
    public void setNUMPAPEL(java.lang.String NUMPAPEL) {
        this.NUMPAPEL = NUMPAPEL;
    }


    /**
     * Gets the NUMCOMPARECENCIA value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @return NUMCOMPARECENCIA
     */
    public java.lang.String getNUMCOMPARECENCIA() {
        return NUMCOMPARECENCIA;
    }


    /**
     * Sets the NUMCOMPARECENCIA value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @param NUMCOMPARECENCIA
     */
    public void setNUMCOMPARECENCIA(java.lang.String NUMCOMPARECENCIA) {
        this.NUMCOMPARECENCIA = NUMCOMPARECENCIA;
    }


    /**
     * Gets the NUMDEH value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @return NUMDEH
     */
    public java.lang.String getNUMDEH() {
        return NUMDEH;
    }


    /**
     * Sets the NUMDEH value for this VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.
     * 
     * @param NUMDEH
     */
    public void setNUMDEH(java.lang.String NUMDEH) {
        this.NUMDEH = NUMDEH;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION)) return false;
        VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION other = (VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION) obj;
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
            ((this.NUMPAPEL==null && other.getNUMPAPEL()==null) || 
             (this.NUMPAPEL!=null &&
              this.NUMPAPEL.equals(other.getNUMPAPEL()))) &&
            ((this.NUMCOMPARECENCIA==null && other.getNUMCOMPARECENCIA()==null) || 
             (this.NUMCOMPARECENCIA!=null &&
              this.NUMCOMPARECENCIA.equals(other.getNUMCOMPARECENCIA()))) &&
            ((this.NUMDEH==null && other.getNUMDEH()==null) || 
             (this.NUMDEH!=null &&
              this.NUMDEH.equals(other.getNUMDEH())));
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
        if (getNUMPAPEL() != null) {
            _hashCode += getNUMPAPEL().hashCode();
        }
        if (getNUMCOMPARECENCIA() != null) {
            _hashCode += getNUMCOMPARECENCIA().hashCode();
        }
        if (getNUMDEH() != null) {
            _hashCode += getNUMDEH().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">VOLUMENNOTIFICACIONES>VOLUMENNOTIFICACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ANIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "PERIODO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMPAPEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "NUMPAPEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMCOMPARECENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "NUMCOMPARECENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMDEH");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "NUMDEH"));
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
