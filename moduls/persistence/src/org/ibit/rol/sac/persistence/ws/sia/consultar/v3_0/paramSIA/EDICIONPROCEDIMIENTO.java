/**
 * EDICIONPROCEDIMIENTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA;

public class EDICIONPROCEDIMIENTO  extends org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.EDICIONTRAMITE  implements java.io.Serializable {
    private java.lang.String UNIDADGESTORATRAMITE;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES;

    public EDICIONPROCEDIMIENTO() {
    }

    public EDICIONPROCEDIMIENTO(
           java.lang.String INTERNO,
           java.lang.String ESCOMUN,
           java.lang.String TITULOCIUDADANO,
           java.lang.String DENOMINACION,
           java.lang.String DESCRIPCION,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS,
           java.lang.String SUJETOATASAS,
           java.lang.String PERIODICIDAD,
           java.lang.String UNIDADGESTORATRAMITE,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES) {
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
        this.UNIDADGESTORATRAMITE = UNIDADGESTORATRAMITE;
        this.NOTIFICACIONES = NOTIFICACIONES;
    }


    /**
     * Gets the UNIDADGESTORATRAMITE value for this EDICIONPROCEDIMIENTO.
     * 
     * @return UNIDADGESTORATRAMITE
     */
    public java.lang.String getUNIDADGESTORATRAMITE() {
        return UNIDADGESTORATRAMITE;
    }


    /**
     * Sets the UNIDADGESTORATRAMITE value for this EDICIONPROCEDIMIENTO.
     * 
     * @param UNIDADGESTORATRAMITE
     */
    public void setUNIDADGESTORATRAMITE(java.lang.String UNIDADGESTORATRAMITE) {
        this.UNIDADGESTORATRAMITE = UNIDADGESTORATRAMITE;
    }


    /**
     * Gets the NOTIFICACIONES value for this EDICIONPROCEDIMIENTO.
     * 
     * @return NOTIFICACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.NOTIFICACIONESNOTIFICACION[] getNOTIFICACIONES() {
        return NOTIFICACIONES;
    }


    /**
     * Sets the NOTIFICACIONES value for this EDICIONPROCEDIMIENTO.
     * 
     * @param NOTIFICACIONES
     */
    public void setNOTIFICACIONES(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES) {
        this.NOTIFICACIONES = NOTIFICACIONES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONPROCEDIMIENTO)) return false;
        EDICIONPROCEDIMIENTO other = (EDICIONPROCEDIMIENTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UNIDADGESTORATRAMITE==null && other.getUNIDADGESTORATRAMITE()==null) || 
             (this.UNIDADGESTORATRAMITE!=null &&
              this.UNIDADGESTORATRAMITE.equals(other.getUNIDADGESTORATRAMITE()))) &&
            ((this.NOTIFICACIONES==null && other.getNOTIFICACIONES()==null) || 
             (this.NOTIFICACIONES!=null &&
              java.util.Arrays.equals(this.NOTIFICACIONES, other.getNOTIFICACIONES())));
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
        if (getUNIDADGESTORATRAMITE() != null) {
            _hashCode += getUNIDADGESTORATRAMITE().hashCode();
        }
        if (getNOTIFICACIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNOTIFICACIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNOTIFICACIONES(), i);
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
        new org.apache.axis.description.TypeDesc(EDICIONPROCEDIMIENTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "EDICIONPROCEDIMIENTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UNIDADGESTORATRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "UNIDADGESTORATRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOTIFICACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "NOTIFICACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">NOTIFICACIONES>NOTIFICACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "NOTIFICACION"));
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
