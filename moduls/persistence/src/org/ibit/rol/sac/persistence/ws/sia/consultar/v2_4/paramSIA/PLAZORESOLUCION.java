/**
 * PLAZORESOLUCION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class PLAZORESOLUCION  implements java.io.Serializable {
    private java.lang.String NUMEROPLAZORESOLUCION;

    private java.lang.String CODTIPOPLAZORESOLUCION;

    public PLAZORESOLUCION() {
    }

    public PLAZORESOLUCION(
           java.lang.String NUMEROPLAZORESOLUCION,
           java.lang.String CODTIPOPLAZORESOLUCION) {
           this.NUMEROPLAZORESOLUCION = NUMEROPLAZORESOLUCION;
           this.CODTIPOPLAZORESOLUCION = CODTIPOPLAZORESOLUCION;
    }


    /**
     * Gets the NUMEROPLAZORESOLUCION value for this PLAZORESOLUCION.
     * 
     * @return NUMEROPLAZORESOLUCION
     */
    public java.lang.String getNUMEROPLAZORESOLUCION() {
        return NUMEROPLAZORESOLUCION;
    }


    /**
     * Sets the NUMEROPLAZORESOLUCION value for this PLAZORESOLUCION.
     * 
     * @param NUMEROPLAZORESOLUCION
     */
    public void setNUMEROPLAZORESOLUCION(java.lang.String NUMEROPLAZORESOLUCION) {
        this.NUMEROPLAZORESOLUCION = NUMEROPLAZORESOLUCION;
    }


    /**
     * Gets the CODTIPOPLAZORESOLUCION value for this PLAZORESOLUCION.
     * 
     * @return CODTIPOPLAZORESOLUCION
     */
    public java.lang.String getCODTIPOPLAZORESOLUCION() {
        return CODTIPOPLAZORESOLUCION;
    }


    /**
     * Sets the CODTIPOPLAZORESOLUCION value for this PLAZORESOLUCION.
     * 
     * @param CODTIPOPLAZORESOLUCION
     */
    public void setCODTIPOPLAZORESOLUCION(java.lang.String CODTIPOPLAZORESOLUCION) {
        this.CODTIPOPLAZORESOLUCION = CODTIPOPLAZORESOLUCION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLAZORESOLUCION)) return false;
        PLAZORESOLUCION other = (PLAZORESOLUCION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NUMEROPLAZORESOLUCION==null && other.getNUMEROPLAZORESOLUCION()==null) || 
             (this.NUMEROPLAZORESOLUCION!=null &&
              this.NUMEROPLAZORESOLUCION.equals(other.getNUMEROPLAZORESOLUCION()))) &&
            ((this.CODTIPOPLAZORESOLUCION==null && other.getCODTIPOPLAZORESOLUCION()==null) || 
             (this.CODTIPOPLAZORESOLUCION!=null &&
              this.CODTIPOPLAZORESOLUCION.equals(other.getCODTIPOPLAZORESOLUCION())));
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
        if (getNUMEROPLAZORESOLUCION() != null) {
            _hashCode += getNUMEROPLAZORESOLUCION().hashCode();
        }
        if (getCODTIPOPLAZORESOLUCION() != null) {
            _hashCode += getCODTIPOPLAZORESOLUCION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLAZORESOLUCION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "PLAZORESOLUCION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROPLAZORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "NUMEROPLAZORESOLUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTIPOPLAZORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "CODTIPOPLAZORESOLUCION"));
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
