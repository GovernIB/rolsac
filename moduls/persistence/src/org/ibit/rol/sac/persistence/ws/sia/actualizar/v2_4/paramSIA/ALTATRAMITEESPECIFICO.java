/**
 * ALTATRAMITEESPECIFICO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class ALTATRAMITEESPECIFICO  implements java.io.Serializable {
    private java.lang.String INTERNO;

    private java.lang.String TIPOTRAMITE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOESPECIFICO ALTAPROCEDIMIENTOESPECIFICO;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOESPECIFICO ALTASERVICIOESPECIFICO;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAACTUACION ALTAACTUACION;

    public ALTATRAMITEESPECIFICO() {
    }

    public ALTATRAMITEESPECIFICO(
           java.lang.String INTERNO,
           java.lang.String TIPOTRAMITE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOESPECIFICO ALTAPROCEDIMIENTOESPECIFICO,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOESPECIFICO ALTASERVICIOESPECIFICO,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAACTUACION ALTAACTUACION) {
           this.INTERNO = INTERNO;
           this.TIPOTRAMITE = TIPOTRAMITE;
           this.ALTAPROCEDIMIENTOESPECIFICO = ALTAPROCEDIMIENTOESPECIFICO;
           this.ALTASERVICIOESPECIFICO = ALTASERVICIOESPECIFICO;
           this.ALTAACTUACION = ALTAACTUACION;
    }


    /**
     * Gets the INTERNO value for this ALTATRAMITEESPECIFICO.
     * 
     * @return INTERNO
     */
    public java.lang.String getINTERNO() {
        return INTERNO;
    }


    /**
     * Sets the INTERNO value for this ALTATRAMITEESPECIFICO.
     * 
     * @param INTERNO
     */
    public void setINTERNO(java.lang.String INTERNO) {
        this.INTERNO = INTERNO;
    }


    /**
     * Gets the TIPOTRAMITE value for this ALTATRAMITEESPECIFICO.
     * 
     * @return TIPOTRAMITE
     */
    public java.lang.String getTIPOTRAMITE() {
        return TIPOTRAMITE;
    }


    /**
     * Sets the TIPOTRAMITE value for this ALTATRAMITEESPECIFICO.
     * 
     * @param TIPOTRAMITE
     */
    public void setTIPOTRAMITE(java.lang.String TIPOTRAMITE) {
        this.TIPOTRAMITE = TIPOTRAMITE;
    }


    /**
     * Gets the ALTAPROCEDIMIENTOESPECIFICO value for this ALTATRAMITEESPECIFICO.
     * 
     * @return ALTAPROCEDIMIENTOESPECIFICO
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOESPECIFICO getALTAPROCEDIMIENTOESPECIFICO() {
        return ALTAPROCEDIMIENTOESPECIFICO;
    }


    /**
     * Sets the ALTAPROCEDIMIENTOESPECIFICO value for this ALTATRAMITEESPECIFICO.
     * 
     * @param ALTAPROCEDIMIENTOESPECIFICO
     */
    public void setALTAPROCEDIMIENTOESPECIFICO(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOESPECIFICO ALTAPROCEDIMIENTOESPECIFICO) {
        this.ALTAPROCEDIMIENTOESPECIFICO = ALTAPROCEDIMIENTOESPECIFICO;
    }


    /**
     * Gets the ALTASERVICIOESPECIFICO value for this ALTATRAMITEESPECIFICO.
     * 
     * @return ALTASERVICIOESPECIFICO
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOESPECIFICO getALTASERVICIOESPECIFICO() {
        return ALTASERVICIOESPECIFICO;
    }


    /**
     * Sets the ALTASERVICIOESPECIFICO value for this ALTATRAMITEESPECIFICO.
     * 
     * @param ALTASERVICIOESPECIFICO
     */
    public void setALTASERVICIOESPECIFICO(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOESPECIFICO ALTASERVICIOESPECIFICO) {
        this.ALTASERVICIOESPECIFICO = ALTASERVICIOESPECIFICO;
    }


    /**
     * Gets the ALTAACTUACION value for this ALTATRAMITEESPECIFICO.
     * 
     * @return ALTAACTUACION
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAACTUACION getALTAACTUACION() {
        return ALTAACTUACION;
    }


    /**
     * Sets the ALTAACTUACION value for this ALTATRAMITEESPECIFICO.
     * 
     * @param ALTAACTUACION
     */
    public void setALTAACTUACION(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAACTUACION ALTAACTUACION) {
        this.ALTAACTUACION = ALTAACTUACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ALTATRAMITEESPECIFICO)) return false;
        ALTATRAMITEESPECIFICO other = (ALTATRAMITEESPECIFICO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INTERNO==null && other.getINTERNO()==null) || 
             (this.INTERNO!=null &&
              this.INTERNO.equals(other.getINTERNO()))) &&
            ((this.TIPOTRAMITE==null && other.getTIPOTRAMITE()==null) || 
             (this.TIPOTRAMITE!=null &&
              this.TIPOTRAMITE.equals(other.getTIPOTRAMITE()))) &&
            ((this.ALTAPROCEDIMIENTOESPECIFICO==null && other.getALTAPROCEDIMIENTOESPECIFICO()==null) || 
             (this.ALTAPROCEDIMIENTOESPECIFICO!=null &&
              this.ALTAPROCEDIMIENTOESPECIFICO.equals(other.getALTAPROCEDIMIENTOESPECIFICO()))) &&
            ((this.ALTASERVICIOESPECIFICO==null && other.getALTASERVICIOESPECIFICO()==null) || 
             (this.ALTASERVICIOESPECIFICO!=null &&
              this.ALTASERVICIOESPECIFICO.equals(other.getALTASERVICIOESPECIFICO()))) &&
            ((this.ALTAACTUACION==null && other.getALTAACTUACION()==null) || 
             (this.ALTAACTUACION!=null &&
              this.ALTAACTUACION.equals(other.getALTAACTUACION())));
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
        if (getINTERNO() != null) {
            _hashCode += getINTERNO().hashCode();
        }
        if (getTIPOTRAMITE() != null) {
            _hashCode += getTIPOTRAMITE().hashCode();
        }
        if (getALTAPROCEDIMIENTOESPECIFICO() != null) {
            _hashCode += getALTAPROCEDIMIENTOESPECIFICO().hashCode();
        }
        if (getALTASERVICIOESPECIFICO() != null) {
            _hashCode += getALTASERVICIOESPECIFICO().hashCode();
        }
        if (getALTAACTUACION() != null) {
            _hashCode += getALTAACTUACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ALTATRAMITEESPECIFICO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTATRAMITEESPECIFICO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTERNO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "INTERNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOTRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TIPOTRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTAPROCEDIMIENTOESPECIFICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAPROCEDIMIENTOESPECIFICO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAPROCEDIMIENTOESPECIFICO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTASERVICIOESPECIFICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTASERVICIOESPECIFICO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTASERVICIOESPECIFICO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTAACTUACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAACTUACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAACTUACION"));
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
