/**
 * EnviaSIA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA;

public class EnviaSIA  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIAACTUACIONESACTUACION[] ACTUACIONES;

    public EnviaSIA() {
    }

    public EnviaSIA(
           org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIAACTUACIONESACTUACION[] ACTUACIONES) {
           this.ACTUACIONES = ACTUACIONES;
    }


    /**
     * Gets the ACTUACIONES value for this EnviaSIA.
     * 
     * @return ACTUACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIAACTUACIONESACTUACION[] getACTUACIONES() {
        return ACTUACIONES;
    }


    /**
     * Sets the ACTUACIONES value for this EnviaSIA.
     * 
     * @param ACTUACIONES
     */
    public void setACTUACIONES(org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIAACTUACIONESACTUACION[] ACTUACIONES) {
        this.ACTUACIONES = ACTUACIONES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnviaSIA)) return false;
        EnviaSIA other = (EnviaSIA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
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
        new org.apache.axis.description.TypeDesc(EnviaSIA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", ">enviaSIA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTUACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", "ACTUACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", "ACTUACION"));
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
