/**
 * EDICIONSERVICIO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA;

public class EDICIONSERVICIO  extends org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONTRAMITE  implements java.io.Serializable {
    public EDICIONSERVICIO() {
    }

    public EDICIONSERVICIO(
           java.lang.String INTERNO,
           java.lang.String ESCOMUN,
           java.lang.String TITULOCIUDADANO,
           java.lang.String DENOMINACION,
           java.lang.String DESCRIPCION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS,
           java.lang.String SUJETOATASAS,
           java.lang.String PERIODICIDAD) {
        super(
            INTERNO,
            ESCOMUN,
            TITULOCIUDADANO,
            DENOMINACION,
            DESCRIPCION,
            ORGANISMORESPONSABLE,
            DESTINATARIOS,
            SUJETOATASAS,
            PERIODICIDAD);
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONSERVICIO)) return false;
        EDICIONSERVICIO other = (EDICIONSERVICIO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONSERVICIO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONSERVICIO"));
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