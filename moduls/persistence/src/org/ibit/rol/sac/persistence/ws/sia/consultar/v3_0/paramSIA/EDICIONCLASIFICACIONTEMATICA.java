/**
 * EDICIONCLASIFICACIONTEMATICA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA;

public class EDICIONCLASIFICACIONTEMATICA  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.MATERIASMATERIA[] MATERIAS;

    private java.lang.String CODCLASETRAMITE;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.HECHOSVITALESHECHOVITAL[] HECHOSVITALES;

    public EDICIONCLASIFICACIONTEMATICA() {
    }

    public EDICIONCLASIFICACIONTEMATICA(
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.MATERIASMATERIA[] MATERIAS,
           java.lang.String CODCLASETRAMITE,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.HECHOSVITALESHECHOVITAL[] HECHOSVITALES) {
           this.MATERIAS = MATERIAS;
           this.CODCLASETRAMITE = CODCLASETRAMITE;
           this.HECHOSVITALES = HECHOSVITALES;
    }


    /**
     * Gets the MATERIAS value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @return MATERIAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.MATERIASMATERIA[] getMATERIAS() {
        return MATERIAS;
    }


    /**
     * Sets the MATERIAS value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @param MATERIAS
     */
    public void setMATERIAS(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.MATERIASMATERIA[] MATERIAS) {
        this.MATERIAS = MATERIAS;
    }


    /**
     * Gets the CODCLASETRAMITE value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @return CODCLASETRAMITE
     */
    public java.lang.String getCODCLASETRAMITE() {
        return CODCLASETRAMITE;
    }


    /**
     * Sets the CODCLASETRAMITE value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @param CODCLASETRAMITE
     */
    public void setCODCLASETRAMITE(java.lang.String CODCLASETRAMITE) {
        this.CODCLASETRAMITE = CODCLASETRAMITE;
    }


    /**
     * Gets the HECHOSVITALES value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @return HECHOSVITALES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.HECHOSVITALESHECHOVITAL[] getHECHOSVITALES() {
        return HECHOSVITALES;
    }


    /**
     * Sets the HECHOSVITALES value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @param HECHOSVITALES
     */
    public void setHECHOSVITALES(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.HECHOSVITALESHECHOVITAL[] HECHOSVITALES) {
        this.HECHOSVITALES = HECHOSVITALES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONCLASIFICACIONTEMATICA)) return false;
        EDICIONCLASIFICACIONTEMATICA other = (EDICIONCLASIFICACIONTEMATICA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MATERIAS==null && other.getMATERIAS()==null) || 
             (this.MATERIAS!=null &&
              java.util.Arrays.equals(this.MATERIAS, other.getMATERIAS()))) &&
            ((this.CODCLASETRAMITE==null && other.getCODCLASETRAMITE()==null) || 
             (this.CODCLASETRAMITE!=null &&
              this.CODCLASETRAMITE.equals(other.getCODCLASETRAMITE()))) &&
            ((this.HECHOSVITALES==null && other.getHECHOSVITALES()==null) || 
             (this.HECHOSVITALES!=null &&
              java.util.Arrays.equals(this.HECHOSVITALES, other.getHECHOSVITALES())));
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
        if (getMATERIAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMATERIAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMATERIAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODCLASETRAMITE() != null) {
            _hashCode += getCODCLASETRAMITE().hashCode();
        }
        if (getHECHOSVITALES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHECHOSVITALES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHECHOSVITALES(), i);
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
        new org.apache.axis.description.TypeDesc(EDICIONCLASIFICACIONTEMATICA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "EDICIONCLASIFICACIONTEMATICA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATERIAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "MATERIAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">MATERIAS>MATERIA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "MATERIA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODCLASETRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "CODCLASETRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HECHOSVITALES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "HECHOSVITALES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">HECHOSVITALES>HECHOVITAL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "HECHOVITAL"));
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
