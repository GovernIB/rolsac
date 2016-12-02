/**
 * EDICIONCLASIFICACIONTEMATICA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class EDICIONCLASIFICACIONTEMATICA  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.TEMATICASTEMATICA[] TEMATICAS;

    private java.lang.String CODTIPOLOGIA;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PERFILESPERFIL[] PERFILES;

    public EDICIONCLASIFICACIONTEMATICA() {
    }

    public EDICIONCLASIFICACIONTEMATICA(
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.TEMATICASTEMATICA[] TEMATICAS,
           java.lang.String CODTIPOLOGIA,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PERFILESPERFIL[] PERFILES) {
           this.TEMATICAS = TEMATICAS;
           this.CODTIPOLOGIA = CODTIPOLOGIA;
           this.PERFILES = PERFILES;
    }


    /**
     * Gets the TEMATICAS value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @return TEMATICAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.TEMATICASTEMATICA[] getTEMATICAS() {
        return TEMATICAS;
    }


    /**
     * Sets the TEMATICAS value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @param TEMATICAS
     */
    public void setTEMATICAS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.TEMATICASTEMATICA[] TEMATICAS) {
        this.TEMATICAS = TEMATICAS;
    }


    /**
     * Gets the CODTIPOLOGIA value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @return CODTIPOLOGIA
     */
    public java.lang.String getCODTIPOLOGIA() {
        return CODTIPOLOGIA;
    }


    /**
     * Sets the CODTIPOLOGIA value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @param CODTIPOLOGIA
     */
    public void setCODTIPOLOGIA(java.lang.String CODTIPOLOGIA) {
        this.CODTIPOLOGIA = CODTIPOLOGIA;
    }


    /**
     * Gets the PERFILES value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @return PERFILES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PERFILESPERFIL[] getPERFILES() {
        return PERFILES;
    }


    /**
     * Sets the PERFILES value for this EDICIONCLASIFICACIONTEMATICA.
     * 
     * @param PERFILES
     */
    public void setPERFILES(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PERFILESPERFIL[] PERFILES) {
        this.PERFILES = PERFILES;
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
            ((this.TEMATICAS==null && other.getTEMATICAS()==null) || 
             (this.TEMATICAS!=null &&
              java.util.Arrays.equals(this.TEMATICAS, other.getTEMATICAS()))) &&
            ((this.CODTIPOLOGIA==null && other.getCODTIPOLOGIA()==null) || 
             (this.CODTIPOLOGIA!=null &&
              this.CODTIPOLOGIA.equals(other.getCODTIPOLOGIA()))) &&
            ((this.PERFILES==null && other.getPERFILES()==null) || 
             (this.PERFILES!=null &&
              java.util.Arrays.equals(this.PERFILES, other.getPERFILES())));
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
        if (getTEMATICAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTEMATICAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTEMATICAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODTIPOLOGIA() != null) {
            _hashCode += getCODTIPOLOGIA().hashCode();
        }
        if (getPERFILES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPERFILES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPERFILES(), i);
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
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONCLASIFICACIONTEMATICA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TEMATICAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TEMATICAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">TEMATICAS>TEMATICA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TEMATICA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTIPOLOGIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "CODTIPOLOGIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERFILES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "PERFILES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">PERFILES>PERFIL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "PERFIL"));
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
