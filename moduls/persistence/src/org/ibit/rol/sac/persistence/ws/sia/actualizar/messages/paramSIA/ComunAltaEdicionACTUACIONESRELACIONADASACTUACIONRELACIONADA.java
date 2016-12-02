/**
 * ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA;

public class ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA  implements java.io.Serializable {
    private java.lang.String ARCODACTUACION;

    private java.lang.String ARORDEN;

    private java.lang.String ARCODCOINCIDENCIA;

    public ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA() {
    }

    public ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA(
           java.lang.String ARCODACTUACION,
           java.lang.String ARORDEN,
           java.lang.String ARCODCOINCIDENCIA) {
           this.ARCODACTUACION = ARCODACTUACION;
           this.ARORDEN = ARORDEN;
           this.ARCODCOINCIDENCIA = ARCODCOINCIDENCIA;
    }


    /**
     * Gets the ARCODACTUACION value for this ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.
     * 
     * @return ARCODACTUACION
     */
    public java.lang.String getARCODACTUACION() {
        return ARCODACTUACION;
    }


    /**
     * Sets the ARCODACTUACION value for this ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.
     * 
     * @param ARCODACTUACION
     */
    public void setARCODACTUACION(java.lang.String ARCODACTUACION) {
        this.ARCODACTUACION = ARCODACTUACION;
    }


    /**
     * Gets the ARORDEN value for this ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.
     * 
     * @return ARORDEN
     */
    public java.lang.String getARORDEN() {
        return ARORDEN;
    }


    /**
     * Sets the ARORDEN value for this ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.
     * 
     * @param ARORDEN
     */
    public void setARORDEN(java.lang.String ARORDEN) {
        this.ARORDEN = ARORDEN;
    }


    /**
     * Gets the ARCODCOINCIDENCIA value for this ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.
     * 
     * @return ARCODCOINCIDENCIA
     */
    public java.lang.String getARCODCOINCIDENCIA() {
        return ARCODCOINCIDENCIA;
    }


    /**
     * Sets the ARCODCOINCIDENCIA value for this ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.
     * 
     * @param ARCODCOINCIDENCIA
     */
    public void setARCODCOINCIDENCIA(java.lang.String ARCODCOINCIDENCIA) {
        this.ARCODCOINCIDENCIA = ARCODCOINCIDENCIA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA)) return false;
        ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA other = (ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ARCODACTUACION==null && other.getARCODACTUACION()==null) || 
             (this.ARCODACTUACION!=null &&
              this.ARCODACTUACION.equals(other.getARCODACTUACION()))) &&
            ((this.ARORDEN==null && other.getARORDEN()==null) || 
             (this.ARORDEN!=null &&
              this.ARORDEN.equals(other.getARORDEN()))) &&
            ((this.ARCODCOINCIDENCIA==null && other.getARCODCOINCIDENCIA()==null) || 
             (this.ARCODCOINCIDENCIA!=null &&
              this.ARCODCOINCIDENCIA.equals(other.getARCODCOINCIDENCIA())));
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
        if (getARCODACTUACION() != null) {
            _hashCode += getARCODACTUACION().hashCode();
        }
        if (getARORDEN() != null) {
            _hashCode += getARORDEN().hashCode();
        }
        if (getARCODCOINCIDENCIA() != null) {
            _hashCode += getARCODCOINCIDENCIA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>ACTUACIONESRELACIONADAS>ACTUACIONRELACIONADA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ARCODACTUACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "ARCODACTUACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ARORDEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "ARORDEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ARCODCOINCIDENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "ARCODCOINCIDENCIA"));
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
