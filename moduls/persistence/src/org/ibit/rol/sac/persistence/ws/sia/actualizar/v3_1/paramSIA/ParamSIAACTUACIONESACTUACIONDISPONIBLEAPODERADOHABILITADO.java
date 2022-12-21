/**
 * ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA;

public class ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO  implements java.io.Serializable, org.apache.axis.encoding.SimpleType {
    private java.lang.String _value;
    public ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO() {
    }

    // Simple Types must have a String constructor
    public ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO(java.lang.String _value) {
        this._value = _value;
    }
    public ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.EmptyString _value) {
        setEmptyStringValue(_value);
    }

    // Simple Types must have a toString for serializing the value
    public java.lang.String toString() {
        return _value;
    }


    /**
     * Gets the booleanoValue value for this ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO.
     * 
     * @return booleanoValue
     */
    public java.lang.String getBooleanoValue() {
        return new java.lang.String(_value);
    }


    /**
     * Sets the _value value for this ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO.
     * 
     * @param _value
     */
    public void setBooleanoValue(java.lang.String _value) {
        this._value = _value == null ? null : _value.toString();
    }


    /**
     * Gets the emptyStringValue value for this ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO.
     * 
     * @return emptyStringValue
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.EmptyString getEmptyStringValue() {
        return org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.EmptyString.fromString(_value);
    }


    /**
     * Sets the _value value for this ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO.
     * 
     * @param _value
     */
    public void setEmptyStringValue(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.EmptyString _value) {
        this._value = _value == null ? null : _value.toString();
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO)) return false;
        ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO other = (ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&  this.toString().equals(obj.toString());
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
        if (this._value != null) {
            _hashCode += this._value.hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>DISPONIBLEAPODERADOHABILITADO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("booleanoValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "booleanoValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emptyStringValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emptyStringValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "emptyString"));
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
          new  org.apache.axis.encoding.ser.SimpleSerializer(
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
          new  org.apache.axis.encoding.ser.SimpleDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
