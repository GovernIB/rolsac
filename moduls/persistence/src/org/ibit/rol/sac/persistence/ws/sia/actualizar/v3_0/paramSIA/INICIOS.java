/**
 * INICIOS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA;

public class INICIOS  implements java.io.Serializable {
    private java.lang.String FORMAINICIACIONINTERESADO;

    private java.lang.String CODEFECTOSILENCIOINTERESADO;

    private java.lang.String FORMAINICIACIONOFICIO;

    private java.lang.String CODEFECTOSILENCIOOFICIO;

    public INICIOS() {
    }

    public INICIOS(
           java.lang.String FORMAINICIACIONINTERESADO,
           java.lang.String CODEFECTOSILENCIOINTERESADO,
           java.lang.String FORMAINICIACIONOFICIO,
           java.lang.String CODEFECTOSILENCIOOFICIO) {
           this.FORMAINICIACIONINTERESADO = FORMAINICIACIONINTERESADO;
           this.CODEFECTOSILENCIOINTERESADO = CODEFECTOSILENCIOINTERESADO;
           this.FORMAINICIACIONOFICIO = FORMAINICIACIONOFICIO;
           this.CODEFECTOSILENCIOOFICIO = CODEFECTOSILENCIOOFICIO;
    }


    /**
     * Gets the FORMAINICIACIONINTERESADO value for this INICIOS.
     * 
     * @return FORMAINICIACIONINTERESADO
     */
    public java.lang.String getFORMAINICIACIONINTERESADO() {
        return FORMAINICIACIONINTERESADO;
    }


    /**
     * Sets the FORMAINICIACIONINTERESADO value for this INICIOS.
     * 
     * @param FORMAINICIACIONINTERESADO
     */
    public void setFORMAINICIACIONINTERESADO(java.lang.String FORMAINICIACIONINTERESADO) {
        this.FORMAINICIACIONINTERESADO = FORMAINICIACIONINTERESADO;
    }


    /**
     * Gets the CODEFECTOSILENCIOINTERESADO value for this INICIOS.
     * 
     * @return CODEFECTOSILENCIOINTERESADO
     */
    public java.lang.String getCODEFECTOSILENCIOINTERESADO() {
        return CODEFECTOSILENCIOINTERESADO;
    }


    /**
     * Sets the CODEFECTOSILENCIOINTERESADO value for this INICIOS.
     * 
     * @param CODEFECTOSILENCIOINTERESADO
     */
    public void setCODEFECTOSILENCIOINTERESADO(java.lang.String CODEFECTOSILENCIOINTERESADO) {
        this.CODEFECTOSILENCIOINTERESADO = CODEFECTOSILENCIOINTERESADO;
    }


    /**
     * Gets the FORMAINICIACIONOFICIO value for this INICIOS.
     * 
     * @return FORMAINICIACIONOFICIO
     */
    public java.lang.String getFORMAINICIACIONOFICIO() {
        return FORMAINICIACIONOFICIO;
    }


    /**
     * Sets the FORMAINICIACIONOFICIO value for this INICIOS.
     * 
     * @param FORMAINICIACIONOFICIO
     */
    public void setFORMAINICIACIONOFICIO(java.lang.String FORMAINICIACIONOFICIO) {
        this.FORMAINICIACIONOFICIO = FORMAINICIACIONOFICIO;
    }


    /**
     * Gets the CODEFECTOSILENCIOOFICIO value for this INICIOS.
     * 
     * @return CODEFECTOSILENCIOOFICIO
     */
    public java.lang.String getCODEFECTOSILENCIOOFICIO() {
        return CODEFECTOSILENCIOOFICIO;
    }


    /**
     * Sets the CODEFECTOSILENCIOOFICIO value for this INICIOS.
     * 
     * @param CODEFECTOSILENCIOOFICIO
     */
    public void setCODEFECTOSILENCIOOFICIO(java.lang.String CODEFECTOSILENCIOOFICIO) {
        this.CODEFECTOSILENCIOOFICIO = CODEFECTOSILENCIOOFICIO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof INICIOS)) return false;
        INICIOS other = (INICIOS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.FORMAINICIACIONINTERESADO==null && other.getFORMAINICIACIONINTERESADO()==null) || 
             (this.FORMAINICIACIONINTERESADO!=null &&
              this.FORMAINICIACIONINTERESADO.equals(other.getFORMAINICIACIONINTERESADO()))) &&
            ((this.CODEFECTOSILENCIOINTERESADO==null && other.getCODEFECTOSILENCIOINTERESADO()==null) || 
             (this.CODEFECTOSILENCIOINTERESADO!=null &&
              this.CODEFECTOSILENCIOINTERESADO.equals(other.getCODEFECTOSILENCIOINTERESADO()))) &&
            ((this.FORMAINICIACIONOFICIO==null && other.getFORMAINICIACIONOFICIO()==null) || 
             (this.FORMAINICIACIONOFICIO!=null &&
              this.FORMAINICIACIONOFICIO.equals(other.getFORMAINICIACIONOFICIO()))) &&
            ((this.CODEFECTOSILENCIOOFICIO==null && other.getCODEFECTOSILENCIOOFICIO()==null) || 
             (this.CODEFECTOSILENCIOOFICIO!=null &&
              this.CODEFECTOSILENCIOOFICIO.equals(other.getCODEFECTOSILENCIOOFICIO())));
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
        if (getFORMAINICIACIONINTERESADO() != null) {
            _hashCode += getFORMAINICIACIONINTERESADO().hashCode();
        }
        if (getCODEFECTOSILENCIOINTERESADO() != null) {
            _hashCode += getCODEFECTOSILENCIOINTERESADO().hashCode();
        }
        if (getFORMAINICIACIONOFICIO() != null) {
            _hashCode += getFORMAINICIACIONOFICIO().hashCode();
        }
        if (getCODEFECTOSILENCIOOFICIO() != null) {
            _hashCode += getCODEFECTOSILENCIOOFICIO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(INICIOS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "INICIOS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FORMAINICIACIONINTERESADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "FORMAINICIACIONINTERESADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODEFECTOSILENCIOINTERESADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "CODEFECTOSILENCIOINTERESADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FORMAINICIACIONOFICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "FORMAINICIACIONOFICIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODEFECTOSILENCIOOFICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "CODEFECTOSILENCIOOFICIO"));
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
