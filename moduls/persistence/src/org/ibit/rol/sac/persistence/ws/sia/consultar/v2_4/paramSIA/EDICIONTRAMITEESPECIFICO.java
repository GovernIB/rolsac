/**
 * EDICIONTRAMITEESPECIFICO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class EDICIONTRAMITEESPECIFICO  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONPROCEDIMIENTOESPECIFICO EDICIONPROCEDIMIENTOESPECIFICO;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONSERVICIOESPECIFICO EDICIONSERVICIOESPECIFICO;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONACTUACION EDICIONACTUACION;

    public EDICIONTRAMITEESPECIFICO() {
    }

    public EDICIONTRAMITEESPECIFICO(
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONPROCEDIMIENTOESPECIFICO EDICIONPROCEDIMIENTOESPECIFICO,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONSERVICIOESPECIFICO EDICIONSERVICIOESPECIFICO,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONACTUACION EDICIONACTUACION) {
           this.EDICIONPROCEDIMIENTOESPECIFICO = EDICIONPROCEDIMIENTOESPECIFICO;
           this.EDICIONSERVICIOESPECIFICO = EDICIONSERVICIOESPECIFICO;
           this.EDICIONACTUACION = EDICIONACTUACION;
    }


    /**
     * Gets the EDICIONPROCEDIMIENTOESPECIFICO value for this EDICIONTRAMITEESPECIFICO.
     * 
     * @return EDICIONPROCEDIMIENTOESPECIFICO
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONPROCEDIMIENTOESPECIFICO getEDICIONPROCEDIMIENTOESPECIFICO() {
        return EDICIONPROCEDIMIENTOESPECIFICO;
    }


    /**
     * Sets the EDICIONPROCEDIMIENTOESPECIFICO value for this EDICIONTRAMITEESPECIFICO.
     * 
     * @param EDICIONPROCEDIMIENTOESPECIFICO
     */
    public void setEDICIONPROCEDIMIENTOESPECIFICO(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONPROCEDIMIENTOESPECIFICO EDICIONPROCEDIMIENTOESPECIFICO) {
        this.EDICIONPROCEDIMIENTOESPECIFICO = EDICIONPROCEDIMIENTOESPECIFICO;
    }


    /**
     * Gets the EDICIONSERVICIOESPECIFICO value for this EDICIONTRAMITEESPECIFICO.
     * 
     * @return EDICIONSERVICIOESPECIFICO
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONSERVICIOESPECIFICO getEDICIONSERVICIOESPECIFICO() {
        return EDICIONSERVICIOESPECIFICO;
    }


    /**
     * Sets the EDICIONSERVICIOESPECIFICO value for this EDICIONTRAMITEESPECIFICO.
     * 
     * @param EDICIONSERVICIOESPECIFICO
     */
    public void setEDICIONSERVICIOESPECIFICO(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONSERVICIOESPECIFICO EDICIONSERVICIOESPECIFICO) {
        this.EDICIONSERVICIOESPECIFICO = EDICIONSERVICIOESPECIFICO;
    }


    /**
     * Gets the EDICIONACTUACION value for this EDICIONTRAMITEESPECIFICO.
     * 
     * @return EDICIONACTUACION
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONACTUACION getEDICIONACTUACION() {
        return EDICIONACTUACION;
    }


    /**
     * Sets the EDICIONACTUACION value for this EDICIONTRAMITEESPECIFICO.
     * 
     * @param EDICIONACTUACION
     */
    public void setEDICIONACTUACION(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.EDICIONACTUACION EDICIONACTUACION) {
        this.EDICIONACTUACION = EDICIONACTUACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONTRAMITEESPECIFICO)) return false;
        EDICIONTRAMITEESPECIFICO other = (EDICIONTRAMITEESPECIFICO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.EDICIONPROCEDIMIENTOESPECIFICO==null && other.getEDICIONPROCEDIMIENTOESPECIFICO()==null) || 
             (this.EDICIONPROCEDIMIENTOESPECIFICO!=null &&
              this.EDICIONPROCEDIMIENTOESPECIFICO.equals(other.getEDICIONPROCEDIMIENTOESPECIFICO()))) &&
            ((this.EDICIONSERVICIOESPECIFICO==null && other.getEDICIONSERVICIOESPECIFICO()==null) || 
             (this.EDICIONSERVICIOESPECIFICO!=null &&
              this.EDICIONSERVICIOESPECIFICO.equals(other.getEDICIONSERVICIOESPECIFICO()))) &&
            ((this.EDICIONACTUACION==null && other.getEDICIONACTUACION()==null) || 
             (this.EDICIONACTUACION!=null &&
              this.EDICIONACTUACION.equals(other.getEDICIONACTUACION())));
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
        if (getEDICIONPROCEDIMIENTOESPECIFICO() != null) {
            _hashCode += getEDICIONPROCEDIMIENTOESPECIFICO().hashCode();
        }
        if (getEDICIONSERVICIOESPECIFICO() != null) {
            _hashCode += getEDICIONSERVICIOESPECIFICO().hashCode();
        }
        if (getEDICIONACTUACION() != null) {
            _hashCode += getEDICIONACTUACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONTRAMITEESPECIFICO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONTRAMITEESPECIFICO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EDICIONPROCEDIMIENTOESPECIFICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONPROCEDIMIENTOESPECIFICO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONPROCEDIMIENTOESPECIFICO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EDICIONSERVICIOESPECIFICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONSERVICIOESPECIFICO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONSERVICIOESPECIFICO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EDICIONACTUACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONACTUACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONACTUACION"));
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
