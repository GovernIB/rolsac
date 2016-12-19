/**
 * EDICIONVOLUMENTRAMITACIONES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class EDICIONVOLUMENTRAMITACIONES  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES;

    public EDICIONVOLUMENTRAMITACIONES() {
    }

    public EDICIONVOLUMENTRAMITACIONES(
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES) {
           this.VOLUMENESTRAMITACIONES = VOLUMENESTRAMITACIONES;
    }


    /**
     * Gets the VOLUMENESTRAMITACIONES value for this EDICIONVOLUMENTRAMITACIONES.
     * 
     * @return VOLUMENESTRAMITACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] getVOLUMENESTRAMITACIONES() {
        return VOLUMENESTRAMITACIONES;
    }


    /**
     * Sets the VOLUMENESTRAMITACIONES value for this EDICIONVOLUMENTRAMITACIONES.
     * 
     * @param VOLUMENESTRAMITACIONES
     */
    public void setVOLUMENESTRAMITACIONES(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES) {
        this.VOLUMENESTRAMITACIONES = VOLUMENESTRAMITACIONES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONVOLUMENTRAMITACIONES)) return false;
        EDICIONVOLUMENTRAMITACIONES other = (EDICIONVOLUMENTRAMITACIONES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.VOLUMENESTRAMITACIONES==null && other.getVOLUMENESTRAMITACIONES()==null) || 
             (this.VOLUMENESTRAMITACIONES!=null &&
              java.util.Arrays.equals(this.VOLUMENESTRAMITACIONES, other.getVOLUMENESTRAMITACIONES())));
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
        if (getVOLUMENESTRAMITACIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVOLUMENESTRAMITACIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVOLUMENESTRAMITACIONES(), i);
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
        new org.apache.axis.description.TypeDesc(EDICIONVOLUMENTRAMITACIONES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONVOLUMENTRAMITACIONES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLUMENESTRAMITACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "VOLUMENESTRAMITACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "VOLUMENTRAMITACIONES"));
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
