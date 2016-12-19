/**
 * EnviaSIAACTUACIONESACTUACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.enviaSIA;

public class EnviaSIAACTUACIONESACTUACION  implements java.io.Serializable {
    private java.lang.String CORRECTO;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.enviaSIA.ERRORESERROR[] ERRORES;

    private java.lang.String CODIGOORIGEN;  // attribute

    private java.lang.String OPERACION;  // attribute

    private java.lang.String CODIGOACTUACION;  // attribute

    public EnviaSIAACTUACIONESACTUACION() {
    }

    public EnviaSIAACTUACIONESACTUACION(
           java.lang.String CORRECTO,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.enviaSIA.ERRORESERROR[] ERRORES,
           java.lang.String CODIGOORIGEN,
           java.lang.String OPERACION,
           java.lang.String CODIGOACTUACION) {
           this.CORRECTO = CORRECTO;
           this.ERRORES = ERRORES;
           this.CODIGOORIGEN = CODIGOORIGEN;
           this.OPERACION = OPERACION;
           this.CODIGOACTUACION = CODIGOACTUACION;
    }


    /**
     * Gets the CORRECTO value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @return CORRECTO
     */
    public java.lang.String getCORRECTO() {
        return CORRECTO;
    }


    /**
     * Sets the CORRECTO value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @param CORRECTO
     */
    public void setCORRECTO(java.lang.String CORRECTO) {
        this.CORRECTO = CORRECTO;
    }


    /**
     * Gets the ERRORES value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @return ERRORES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.enviaSIA.ERRORESERROR[] getERRORES() {
        return ERRORES;
    }


    /**
     * Sets the ERRORES value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @param ERRORES
     */
    public void setERRORES(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.enviaSIA.ERRORESERROR[] ERRORES) {
        this.ERRORES = ERRORES;
    }


    /**
     * Gets the CODIGOORIGEN value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @return CODIGOORIGEN
     */
    public java.lang.String getCODIGOORIGEN() {
        return CODIGOORIGEN;
    }


    /**
     * Sets the CODIGOORIGEN value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @param CODIGOORIGEN
     */
    public void setCODIGOORIGEN(java.lang.String CODIGOORIGEN) {
        this.CODIGOORIGEN = CODIGOORIGEN;
    }


    /**
     * Gets the OPERACION value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @return OPERACION
     */
    public java.lang.String getOPERACION() {
        return OPERACION;
    }


    /**
     * Sets the OPERACION value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @param OPERACION
     */
    public void setOPERACION(java.lang.String OPERACION) {
        this.OPERACION = OPERACION;
    }


    /**
     * Gets the CODIGOACTUACION value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @return CODIGOACTUACION
     */
    public java.lang.String getCODIGOACTUACION() {
        return CODIGOACTUACION;
    }


    /**
     * Sets the CODIGOACTUACION value for this EnviaSIAACTUACIONESACTUACION.
     * 
     * @param CODIGOACTUACION
     */
    public void setCODIGOACTUACION(java.lang.String CODIGOACTUACION) {
        this.CODIGOACTUACION = CODIGOACTUACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnviaSIAACTUACIONESACTUACION)) return false;
        EnviaSIAACTUACIONESACTUACION other = (EnviaSIAACTUACIONESACTUACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CORRECTO==null && other.getCORRECTO()==null) || 
             (this.CORRECTO!=null &&
              this.CORRECTO.equals(other.getCORRECTO()))) &&
            ((this.ERRORES==null && other.getERRORES()==null) || 
             (this.ERRORES!=null &&
              java.util.Arrays.equals(this.ERRORES, other.getERRORES()))) &&
            ((this.CODIGOORIGEN==null && other.getCODIGOORIGEN()==null) || 
             (this.CODIGOORIGEN!=null &&
              this.CODIGOORIGEN.equals(other.getCODIGOORIGEN()))) &&
            ((this.OPERACION==null && other.getOPERACION()==null) || 
             (this.OPERACION!=null &&
              this.OPERACION.equals(other.getOPERACION()))) &&
            ((this.CODIGOACTUACION==null && other.getCODIGOACTUACION()==null) || 
             (this.CODIGOACTUACION!=null &&
              this.CODIGOACTUACION.equals(other.getCODIGOACTUACION())));
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
        if (getCORRECTO() != null) {
            _hashCode += getCORRECTO().hashCode();
        }
        if (getERRORES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getERRORES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getERRORES(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODIGOORIGEN() != null) {
            _hashCode += getCODIGOORIGEN().hashCode();
        }
        if (getOPERACION() != null) {
            _hashCode += getOPERACION().hashCode();
        }
        if (getCODIGOACTUACION() != null) {
            _hashCode += getCODIGOACTUACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnviaSIAACTUACIONESACTUACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("CODIGOORIGEN");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CODIGOORIGEN"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("OPERACION");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OPERACION"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("CODIGOACTUACION");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CODIGOACTUACION"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CORRECTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA", "CORRECTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ERRORES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA", "ERRORES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA", ">ERRORES>ERROR"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA", "ERROR"));
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
