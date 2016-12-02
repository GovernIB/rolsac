/**
 * NORMATIVASNORMATIVA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA;

public class NORMATIVASNORMATIVA  implements java.io.Serializable {
    private java.lang.String CODRANGO;

    private java.lang.String NUMERODISPOSICION;

    private java.lang.String TITULO;

    public NORMATIVASNORMATIVA() {
    }

    public NORMATIVASNORMATIVA(
           java.lang.String CODRANGO,
           java.lang.String NUMERODISPOSICION,
           java.lang.String TITULO) {
           this.CODRANGO = CODRANGO;
           this.NUMERODISPOSICION = NUMERODISPOSICION;
           this.TITULO = TITULO;
    }


    /**
     * Gets the CODRANGO value for this NORMATIVASNORMATIVA.
     * 
     * @return CODRANGO
     */
    public java.lang.String getCODRANGO() {
        return CODRANGO;
    }


    /**
     * Sets the CODRANGO value for this NORMATIVASNORMATIVA.
     * 
     * @param CODRANGO
     */
    public void setCODRANGO(java.lang.String CODRANGO) {
        this.CODRANGO = CODRANGO;
    }


    /**
     * Gets the NUMERODISPOSICION value for this NORMATIVASNORMATIVA.
     * 
     * @return NUMERODISPOSICION
     */
    public java.lang.String getNUMERODISPOSICION() {
        return NUMERODISPOSICION;
    }


    /**
     * Sets the NUMERODISPOSICION value for this NORMATIVASNORMATIVA.
     * 
     * @param NUMERODISPOSICION
     */
    public void setNUMERODISPOSICION(java.lang.String NUMERODISPOSICION) {
        this.NUMERODISPOSICION = NUMERODISPOSICION;
    }


    /**
     * Gets the TITULO value for this NORMATIVASNORMATIVA.
     * 
     * @return TITULO
     */
    public java.lang.String getTITULO() {
        return TITULO;
    }


    /**
     * Sets the TITULO value for this NORMATIVASNORMATIVA.
     * 
     * @param TITULO
     */
    public void setTITULO(java.lang.String TITULO) {
        this.TITULO = TITULO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NORMATIVASNORMATIVA)) return false;
        NORMATIVASNORMATIVA other = (NORMATIVASNORMATIVA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
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
        new org.apache.axis.description.TypeDesc(NORMATIVASNORMATIVA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">NORMATIVAS>NORMATIVA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODRANGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "CODRANGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERODISPOSICION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "NUMERODISPOSICION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TITULO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "TITULO"));
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
