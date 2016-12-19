/**
 * ComunAltaEdicionNORMATIVASNORMATIVA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA;

public class ComunAltaEdicionNORMATIVASNORMATIVA  implements java.io.Serializable {
    private java.lang.String CODTIPONORMATIVA;

    private java.lang.String CODRANGO;

    private java.lang.String NUMERODISPOSICION;

    private java.lang.String TITULO;

    public ComunAltaEdicionNORMATIVASNORMATIVA() {
    }

    public ComunAltaEdicionNORMATIVASNORMATIVA(
           java.lang.String CODTIPONORMATIVA,
           java.lang.String CODRANGO,
           java.lang.String NUMERODISPOSICION,
           java.lang.String TITULO) {
           this.CODTIPONORMATIVA = CODTIPONORMATIVA;
           this.CODRANGO = CODRANGO;
           this.NUMERODISPOSICION = NUMERODISPOSICION;
           this.TITULO = TITULO;
    }


    /**
     * Gets the CODTIPONORMATIVA value for this ComunAltaEdicionNORMATIVASNORMATIVA.
     * 
     * @return CODTIPONORMATIVA
     */
    public java.lang.String getCODTIPONORMATIVA() {
        return CODTIPONORMATIVA;
    }


    /**
     * Sets the CODTIPONORMATIVA value for this ComunAltaEdicionNORMATIVASNORMATIVA.
     * 
     * @param CODTIPONORMATIVA
     */
    public void setCODTIPONORMATIVA(java.lang.String CODTIPONORMATIVA) {
        this.CODTIPONORMATIVA = CODTIPONORMATIVA;
    }


    /**
     * Gets the CODRANGO value for this ComunAltaEdicionNORMATIVASNORMATIVA.
     * 
     * @return CODRANGO
     */
    public java.lang.String getCODRANGO() {
        return CODRANGO;
    }


    /**
     * Sets the CODRANGO value for this ComunAltaEdicionNORMATIVASNORMATIVA.
     * 
     * @param CODRANGO
     */
    public void setCODRANGO(java.lang.String CODRANGO) {
        this.CODRANGO = CODRANGO;
    }


    /**
     * Gets the NUMERODISPOSICION value for this ComunAltaEdicionNORMATIVASNORMATIVA.
     * 
     * @return NUMERODISPOSICION
     */
    public java.lang.String getNUMERODISPOSICION() {
        return NUMERODISPOSICION;
    }


    /**
     * Sets the NUMERODISPOSICION value for this ComunAltaEdicionNORMATIVASNORMATIVA.
     * 
     * @param NUMERODISPOSICION
     */
    public void setNUMERODISPOSICION(java.lang.String NUMERODISPOSICION) {
        this.NUMERODISPOSICION = NUMERODISPOSICION;
    }


    /**
     * Gets the TITULO value for this ComunAltaEdicionNORMATIVASNORMATIVA.
     * 
     * @return TITULO
     */
    public java.lang.String getTITULO() {
        return TITULO;
    }


    /**
     * Sets the TITULO value for this ComunAltaEdicionNORMATIVASNORMATIVA.
     * 
     * @param TITULO
     */
    public void setTITULO(java.lang.String TITULO) {
        this.TITULO = TITULO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionNORMATIVASNORMATIVA)) return false;
        ComunAltaEdicionNORMATIVASNORMATIVA other = (ComunAltaEdicionNORMATIVASNORMATIVA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODTIPONORMATIVA==null && other.getCODTIPONORMATIVA()==null) || 
             (this.CODTIPONORMATIVA!=null &&
              this.CODTIPONORMATIVA.equals(other.getCODTIPONORMATIVA()))) &&
            ((this.CODRANGO==null && other.getCODRANGO()==null) || 
             (this.CODRANGO!=null &&
              this.CODRANGO.equals(other.getCODRANGO()))) &&
            ((this.NUMERODISPOSICION==null && other.getNUMERODISPOSICION()==null) || 
             (this.NUMERODISPOSICION!=null &&
              this.NUMERODISPOSICION.equals(other.getNUMERODISPOSICION()))) &&
            ((this.TITULO==null && other.getTITULO()==null) || 
             (this.TITULO!=null &&
              this.TITULO.equals(other.getTITULO())));
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
        if (getCODTIPONORMATIVA() != null) {
            _hashCode += getCODTIPONORMATIVA().hashCode();
        }
        if (getCODRANGO() != null) {
            _hashCode += getCODRANGO().hashCode();
        }
        if (getNUMERODISPOSICION() != null) {
            _hashCode += getNUMERODISPOSICION().hashCode();
        }
        if (getTITULO() != null) {
            _hashCode += getTITULO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionNORMATIVASNORMATIVA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>NORMATIVAS>NORMATIVA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTIPONORMATIVA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODTIPONORMATIVA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODRANGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODRANGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERODISPOSICION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "NUMERODISPOSICION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TITULO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "TITULO"));
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
