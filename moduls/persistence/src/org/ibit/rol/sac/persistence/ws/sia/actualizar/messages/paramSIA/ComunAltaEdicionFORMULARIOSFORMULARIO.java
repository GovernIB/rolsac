/**
 * ComunAltaEdicionFORMULARIOSFORMULARIO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA;

public class ComunAltaEdicionFORMULARIOSFORMULARIO  implements java.io.Serializable {
    private java.lang.String CODFORM;

    private java.lang.String TITULO;

    private java.lang.String URLFORM;

    public ComunAltaEdicionFORMULARIOSFORMULARIO() {
    }

    public ComunAltaEdicionFORMULARIOSFORMULARIO(
           java.lang.String CODFORM,
           java.lang.String TITULO,
           java.lang.String URLFORM) {
           this.CODFORM = CODFORM;
           this.TITULO = TITULO;
           this.URLFORM = URLFORM;
    }


    /**
     * Gets the CODFORM value for this ComunAltaEdicionFORMULARIOSFORMULARIO.
     * 
     * @return CODFORM
     */
    public java.lang.String getCODFORM() {
        return CODFORM;
    }


    /**
     * Sets the CODFORM value for this ComunAltaEdicionFORMULARIOSFORMULARIO.
     * 
     * @param CODFORM
     */
    public void setCODFORM(java.lang.String CODFORM) {
        this.CODFORM = CODFORM;
    }


    /**
     * Gets the TITULO value for this ComunAltaEdicionFORMULARIOSFORMULARIO.
     * 
     * @return TITULO
     */
    public java.lang.String getTITULO() {
        return TITULO;
    }


    /**
     * Sets the TITULO value for this ComunAltaEdicionFORMULARIOSFORMULARIO.
     * 
     * @param TITULO
     */
    public void setTITULO(java.lang.String TITULO) {
        this.TITULO = TITULO;
    }


    /**
     * Gets the URLFORM value for this ComunAltaEdicionFORMULARIOSFORMULARIO.
     * 
     * @return URLFORM
     */
    public java.lang.String getURLFORM() {
        return URLFORM;
    }


    /**
     * Sets the URLFORM value for this ComunAltaEdicionFORMULARIOSFORMULARIO.
     * 
     * @param URLFORM
     */
    public void setURLFORM(java.lang.String URLFORM) {
        this.URLFORM = URLFORM;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionFORMULARIOSFORMULARIO)) return false;
        ComunAltaEdicionFORMULARIOSFORMULARIO other = (ComunAltaEdicionFORMULARIOSFORMULARIO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODFORM==null && other.getCODFORM()==null) || 
             (this.CODFORM!=null &&
              this.CODFORM.equals(other.getCODFORM()))) &&
            ((this.TITULO==null && other.getTITULO()==null) || 
             (this.TITULO!=null &&
              this.TITULO.equals(other.getTITULO()))) &&
            ((this.URLFORM==null && other.getURLFORM()==null) || 
             (this.URLFORM!=null &&
              this.URLFORM.equals(other.getURLFORM())));
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
        if (getCODFORM() != null) {
            _hashCode += getCODFORM().hashCode();
        }
        if (getTITULO() != null) {
            _hashCode += getTITULO().hashCode();
        }
        if (getURLFORM() != null) {
            _hashCode += getURLFORM().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionFORMULARIOSFORMULARIO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>FORMULARIOS>FORMULARIO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODFORM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "CODFORM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TITULO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "TITULO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URLFORM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "URLFORM"));
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
