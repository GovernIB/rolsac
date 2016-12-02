/**
 * PERFILESPERFIL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class PERFILESPERFIL  implements java.io.Serializable {
    private java.lang.String CODTIPOPERFIL;

    public PERFILESPERFIL() {
    }

    public PERFILESPERFIL(
           java.lang.String CODTIPOPERFIL) {
           this.CODTIPOPERFIL = CODTIPOPERFIL;
    }


    /**
     * Gets the CODTIPOPERFIL value for this PERFILESPERFIL.
     * 
     * @return CODTIPOPERFIL
     */
    public java.lang.String getCODTIPOPERFIL() {
        return CODTIPOPERFIL;
    }


    /**
     * Sets the CODTIPOPERFIL value for this PERFILESPERFIL.
     * 
     * @param CODTIPOPERFIL
     */
    public void setCODTIPOPERFIL(java.lang.String CODTIPOPERFIL) {
        this.CODTIPOPERFIL = CODTIPOPERFIL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PERFILESPERFIL)) return false;
        PERFILESPERFIL other = (PERFILESPERFIL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODTIPOPERFIL==null && other.getCODTIPOPERFIL()==null) || 
             (this.CODTIPOPERFIL!=null &&
              this.CODTIPOPERFIL.equals(other.getCODTIPOPERFIL())));
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
        if (getCODTIPOPERFIL() != null) {
            _hashCode += getCODTIPOPERFIL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PERFILESPERFIL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">PERFILES>PERFIL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTIPOPERFIL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "CODTIPOPERFIL"));
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
