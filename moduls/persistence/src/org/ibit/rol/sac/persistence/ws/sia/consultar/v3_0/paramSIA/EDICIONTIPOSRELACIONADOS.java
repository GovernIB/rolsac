/**
 * EDICIONTIPOSRELACIONADOS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA;

public class EDICIONTIPOSRELACIONADOS  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[] TRAMITESRELACIONADOS;

    public EDICIONTIPOSRELACIONADOS() {
    }

    public EDICIONTIPOSRELACIONADOS(
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[] TRAMITESRELACIONADOS) {
           this.TRAMITESRELACIONADOS = TRAMITESRELACIONADOS;
    }


    /**
     * Gets the TRAMITESRELACIONADOS value for this EDICIONTIPOSRELACIONADOS.
     * 
     * @return TRAMITESRELACIONADOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[] getTRAMITESRELACIONADOS() {
        return TRAMITESRELACIONADOS;
    }


    /**
     * Sets the TRAMITESRELACIONADOS value for this EDICIONTIPOSRELACIONADOS.
     * 
     * @param TRAMITESRELACIONADOS
     */
    public void setTRAMITESRELACIONADOS(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[] TRAMITESRELACIONADOS) {
        this.TRAMITESRELACIONADOS = TRAMITESRELACIONADOS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONTIPOSRELACIONADOS)) return false;
        EDICIONTIPOSRELACIONADOS other = (EDICIONTIPOSRELACIONADOS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TRAMITESRELACIONADOS==null && other.getTRAMITESRELACIONADOS()==null) || 
             (this.TRAMITESRELACIONADOS!=null &&
              java.util.Arrays.equals(this.TRAMITESRELACIONADOS, other.getTRAMITESRELACIONADOS())));
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
        if (getTRAMITESRELACIONADOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTRAMITESRELACIONADOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTRAMITESRELACIONADOS(), i);
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
        new org.apache.axis.description.TypeDesc(EDICIONTIPOSRELACIONADOS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "EDICIONTIPOSRELACIONADOS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAMITESRELACIONADOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "TRAMITESRELACIONADOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "TRAMITERELACIONADO"));
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
