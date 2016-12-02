/**
 * ParamSIA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA;

public class ParamSIA  implements java.io.Serializable {
    private java.lang.String user;

    private java.lang.String password;

    private java.lang.String certificado;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACION[] ACTUACIONES;

    public ParamSIA() {
    }

    public ParamSIA(
           java.lang.String user,
           java.lang.String password,
           java.lang.String certificado,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACION[] ACTUACIONES) {
           this.user = user;
           this.password = password;
           this.certificado = certificado;
           this.ACTUACIONES = ACTUACIONES;
    }


    /**
     * Gets the user value for this ParamSIA.
     * 
     * @return user
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this ParamSIA.
     * 
     * @param user
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }


    /**
     * Gets the password value for this ParamSIA.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ParamSIA.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the certificado value for this ParamSIA.
     * 
     * @return certificado
     */
    public java.lang.String getCertificado() {
        return certificado;
    }


    /**
     * Sets the certificado value for this ParamSIA.
     * 
     * @param certificado
     */
    public void setCertificado(java.lang.String certificado) {
        this.certificado = certificado;
    }


    /**
     * Gets the ACTUACIONES value for this ParamSIA.
     * 
     * @return ACTUACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACION[] getACTUACIONES() {
        return ACTUACIONES;
    }


    /**
     * Sets the ACTUACIONES value for this ParamSIA.
     * 
     * @param ACTUACIONES
     */
    public void setACTUACIONES(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACION[] ACTUACIONES) {
        this.ACTUACIONES = ACTUACIONES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamSIA)) return false;
        ParamSIA other = (ParamSIA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.certificado==null && other.getCertificado()==null) || 
             (this.certificado!=null &&
              this.certificado.equals(other.getCertificado()))) &&
            ((this.ACTUACIONES==null && other.getACTUACIONES()==null) || 
             (this.ACTUACIONES!=null &&
              java.util.Arrays.equals(this.ACTUACIONES, other.getACTUACIONES())));
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
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getCertificado() != null) {
            _hashCode += getCertificado().hashCode();
        }
        if (getACTUACIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getACTUACIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getACTUACIONES(), i);
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
        new org.apache.axis.description.TypeDesc(ParamSIA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">paramSIA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "user"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "certificado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTUACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ACTUACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ACTUACION"));
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
