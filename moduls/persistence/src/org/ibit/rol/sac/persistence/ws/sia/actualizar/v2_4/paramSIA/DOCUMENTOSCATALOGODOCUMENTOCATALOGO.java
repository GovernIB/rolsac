/**
 * DOCUMENTOSCATALOGODOCUMENTOCATALOGO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class DOCUMENTOSCATALOGODOCUMENTOCATALOGO  implements java.io.Serializable {
    private java.lang.String CODDOCUMENTO;

    private java.lang.String OBLIGADOAPORTARLOINTERESADO;

    public DOCUMENTOSCATALOGODOCUMENTOCATALOGO() {
    }

    public DOCUMENTOSCATALOGODOCUMENTOCATALOGO(
           java.lang.String CODDOCUMENTO,
           java.lang.String OBLIGADOAPORTARLOINTERESADO) {
           this.CODDOCUMENTO = CODDOCUMENTO;
           this.OBLIGADOAPORTARLOINTERESADO = OBLIGADOAPORTARLOINTERESADO;
    }


    /**
     * Gets the CODDOCUMENTO value for this DOCUMENTOSCATALOGODOCUMENTOCATALOGO.
     * 
     * @return CODDOCUMENTO
     */
    public java.lang.String getCODDOCUMENTO() {
        return CODDOCUMENTO;
    }


    /**
     * Sets the CODDOCUMENTO value for this DOCUMENTOSCATALOGODOCUMENTOCATALOGO.
     * 
     * @param CODDOCUMENTO
     */
    public void setCODDOCUMENTO(java.lang.String CODDOCUMENTO) {
        this.CODDOCUMENTO = CODDOCUMENTO;
    }


    /**
     * Gets the OBLIGADOAPORTARLOINTERESADO value for this DOCUMENTOSCATALOGODOCUMENTOCATALOGO.
     * 
     * @return OBLIGADOAPORTARLOINTERESADO
     */
    public java.lang.String getOBLIGADOAPORTARLOINTERESADO() {
        return OBLIGADOAPORTARLOINTERESADO;
    }


    /**
     * Sets the OBLIGADOAPORTARLOINTERESADO value for this DOCUMENTOSCATALOGODOCUMENTOCATALOGO.
     * 
     * @param OBLIGADOAPORTARLOINTERESADO
     */
    public void setOBLIGADOAPORTARLOINTERESADO(java.lang.String OBLIGADOAPORTARLOINTERESADO) {
        this.OBLIGADOAPORTARLOINTERESADO = OBLIGADOAPORTARLOINTERESADO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DOCUMENTOSCATALOGODOCUMENTOCATALOGO)) return false;
        DOCUMENTOSCATALOGODOCUMENTOCATALOGO other = (DOCUMENTOSCATALOGODOCUMENTOCATALOGO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODDOCUMENTO==null && other.getCODDOCUMENTO()==null) || 
             (this.CODDOCUMENTO!=null &&
              this.CODDOCUMENTO.equals(other.getCODDOCUMENTO()))) &&
            ((this.OBLIGADOAPORTARLOINTERESADO==null && other.getOBLIGADOAPORTARLOINTERESADO()==null) || 
             (this.OBLIGADOAPORTARLOINTERESADO!=null &&
              this.OBLIGADOAPORTARLOINTERESADO.equals(other.getOBLIGADOAPORTARLOINTERESADO())));
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
        if (getCODDOCUMENTO() != null) {
            _hashCode += getCODDOCUMENTO().hashCode();
        }
        if (getOBLIGADOAPORTARLOINTERESADO() != null) {
            _hashCode += getOBLIGADOAPORTARLOINTERESADO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DOCUMENTOSCATALOGODOCUMENTOCATALOGO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODDOCUMENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "CODDOCUMENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBLIGADOAPORTARLOINTERESADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "OBLIGADOAPORTARLOINTERESADO"));
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
