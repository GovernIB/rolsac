/**
 * ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA;

public class ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO  implements java.io.Serializable {
    private java.lang.String IDIOMA;

    private java.lang.String OTRO;

    private java.lang.String TIPOCONTENIDO;

    public ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO() {
    }

    public ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO(
           java.lang.String IDIOMA,
           java.lang.String OTRO,
           java.lang.String TIPOCONTENIDO) {
           this.IDIOMA = IDIOMA;
           this.OTRO = OTRO;
           this.TIPOCONTENIDO = TIPOCONTENIDO;
    }


    /**
     * Gets the IDIOMA value for this ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.
     * 
     * @return IDIOMA
     */
    public java.lang.String getIDIOMA() {
        return IDIOMA;
    }


    /**
     * Sets the IDIOMA value for this ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.
     * 
     * @param IDIOMA
     */
    public void setIDIOMA(java.lang.String IDIOMA) {
        this.IDIOMA = IDIOMA;
    }


    /**
     * Gets the OTRO value for this ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.
     * 
     * @return OTRO
     */
    public java.lang.String getOTRO() {
        return OTRO;
    }


    /**
     * Sets the OTRO value for this ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.
     * 
     * @param OTRO
     */
    public void setOTRO(java.lang.String OTRO) {
        this.OTRO = OTRO;
    }


    /**
     * Gets the TIPOCONTENIDO value for this ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.
     * 
     * @return TIPOCONTENIDO
     */
    public java.lang.String getTIPOCONTENIDO() {
        return TIPOCONTENIDO;
    }


    /**
     * Sets the TIPOCONTENIDO value for this ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.
     * 
     * @param TIPOCONTENIDO
     */
    public void setTIPOCONTENIDO(java.lang.String TIPOCONTENIDO) {
        this.TIPOCONTENIDO = TIPOCONTENIDO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO)) return false;
        ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO other = (ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IDIOMA==null && other.getIDIOMA()==null) || 
             (this.IDIOMA!=null &&
              this.IDIOMA.equals(other.getIDIOMA()))) &&
            ((this.OTRO==null && other.getOTRO()==null) || 
             (this.OTRO!=null &&
              this.OTRO.equals(other.getOTRO()))) &&
            ((this.TIPOCONTENIDO==null && other.getTIPOCONTENIDO()==null) || 
             (this.TIPOCONTENIDO!=null &&
              this.TIPOCONTENIDO.equals(other.getTIPOCONTENIDO())));
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
        if (getIDIOMA() != null) {
            _hashCode += getIDIOMA().hashCode();
        }
        if (getOTRO() != null) {
            _hashCode += getOTRO().hashCode();
        }
        if (getTIPOCONTENIDO() != null) {
            _hashCode += getTIPOCONTENIDO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>MULTILINGUISMOS>MULTILINGUISMO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDIOMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "IDIOMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OTRO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "OTRO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOCONTENIDO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "TIPOCONTENIDO"));
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
