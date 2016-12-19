/**
 * EDICIONINFORMACIONTRAMITE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA;

public class EDICIONINFORMACIONTRAMITE  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.INICIOS INICIOS;

    private java.lang.String FINVIA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.PLAZORESOLUCION PLAZORESOLUCION;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS;

    public EDICIONINFORMACIONTRAMITE() {
    }

    public EDICIONINFORMACIONTRAMITE(
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.INICIOS INICIOS,
           java.lang.String FINVIA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.PLAZORESOLUCION PLAZORESOLUCION,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS) {
           this.INICIOS = INICIOS;
           this.FINVIA = FINVIA;
           this.PLAZORESOLUCION = PLAZORESOLUCION;
           this.NORMATIVAS = NORMATIVAS;
    }


    /**
     * Gets the INICIOS value for this EDICIONINFORMACIONTRAMITE.
     * 
     * @return INICIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.INICIOS getINICIOS() {
        return INICIOS;
    }


    /**
     * Sets the INICIOS value for this EDICIONINFORMACIONTRAMITE.
     * 
     * @param INICIOS
     */
    public void setINICIOS(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.INICIOS INICIOS) {
        this.INICIOS = INICIOS;
    }


    /**
     * Gets the FINVIA value for this EDICIONINFORMACIONTRAMITE.
     * 
     * @return FINVIA
     */
    public java.lang.String getFINVIA() {
        return FINVIA;
    }


    /**
     * Sets the FINVIA value for this EDICIONINFORMACIONTRAMITE.
     * 
     * @param FINVIA
     */
    public void setFINVIA(java.lang.String FINVIA) {
        this.FINVIA = FINVIA;
    }


    /**
     * Gets the PLAZORESOLUCION value for this EDICIONINFORMACIONTRAMITE.
     * 
     * @return PLAZORESOLUCION
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.PLAZORESOLUCION getPLAZORESOLUCION() {
        return PLAZORESOLUCION;
    }


    /**
     * Sets the PLAZORESOLUCION value for this EDICIONINFORMACIONTRAMITE.
     * 
     * @param PLAZORESOLUCION
     */
    public void setPLAZORESOLUCION(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.PLAZORESOLUCION PLAZORESOLUCION) {
        this.PLAZORESOLUCION = PLAZORESOLUCION;
    }


    /**
     * Gets the NORMATIVAS value for this EDICIONINFORMACIONTRAMITE.
     * 
     * @return NORMATIVAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.NORMATIVASNORMATIVA[] getNORMATIVAS() {
        return NORMATIVAS;
    }


    /**
     * Sets the NORMATIVAS value for this EDICIONINFORMACIONTRAMITE.
     * 
     * @param NORMATIVAS
     */
    public void setNORMATIVAS(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS) {
        this.NORMATIVAS = NORMATIVAS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONINFORMACIONTRAMITE)) return false;
        EDICIONINFORMACIONTRAMITE other = (EDICIONINFORMACIONTRAMITE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INICIOS==null && other.getINICIOS()==null) || 
             (this.INICIOS!=null &&
              this.INICIOS.equals(other.getINICIOS()))) &&
            ((this.FINVIA==null && other.getFINVIA()==null) || 
             (this.FINVIA!=null &&
              this.FINVIA.equals(other.getFINVIA()))) &&
            ((this.PLAZORESOLUCION==null && other.getPLAZORESOLUCION()==null) || 
             (this.PLAZORESOLUCION!=null &&
              this.PLAZORESOLUCION.equals(other.getPLAZORESOLUCION()))) &&
            ((this.NORMATIVAS==null && other.getNORMATIVAS()==null) || 
             (this.NORMATIVAS!=null &&
              java.util.Arrays.equals(this.NORMATIVAS, other.getNORMATIVAS())));
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
        if (getINICIOS() != null) {
            _hashCode += getINICIOS().hashCode();
        }
        if (getFINVIA() != null) {
            _hashCode += getFINVIA().hashCode();
        }
        if (getPLAZORESOLUCION() != null) {
            _hashCode += getPLAZORESOLUCION().hashCode();
        }
        if (getNORMATIVAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNORMATIVAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNORMATIVAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONINFORMACIONTRAMITE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "EDICIONINFORMACIONTRAMITE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INICIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "INICIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "INICIOS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FINVIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "FINVIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PLAZORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "PLAZORESOLUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "PLAZORESOLUCION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NORMATIVAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "NORMATIVAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">NORMATIVAS>NORMATIVA"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "NORMATIVA"));
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
