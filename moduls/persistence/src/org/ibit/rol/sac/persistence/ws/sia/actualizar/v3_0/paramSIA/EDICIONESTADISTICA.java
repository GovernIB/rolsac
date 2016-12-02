/**
 * EDICIONESTADISTICA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA;

public class EDICIONESTADISTICA  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES;

    private java.lang.String TIEMPOMEDIORESOLUCION;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[] VOLUMENNOTIFICACIONES;

    public EDICIONESTADISTICA() {
    }

    public EDICIONESTADISTICA(
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES,
           java.lang.String TIEMPOMEDIORESOLUCION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[] VOLUMENNOTIFICACIONES) {
           this.REDUCCIONES = REDUCCIONES;
           this.VOLUMENESTRAMITACIONES = VOLUMENESTRAMITACIONES;
           this.TIEMPOMEDIORESOLUCION = TIEMPOMEDIORESOLUCION;
           this.VOLUMENNOTIFICACIONES = VOLUMENNOTIFICACIONES;
    }


    /**
     * Gets the REDUCCIONES value for this EDICIONESTADISTICA.
     * 
     * @return REDUCCIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.REDUCCIONESREDUCCION[] getREDUCCIONES() {
        return REDUCCIONES;
    }


    /**
     * Sets the REDUCCIONES value for this EDICIONESTADISTICA.
     * 
     * @param REDUCCIONES
     */
    public void setREDUCCIONES(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES) {
        this.REDUCCIONES = REDUCCIONES;
    }


    /**
     * Gets the VOLUMENESTRAMITACIONES value for this EDICIONESTADISTICA.
     * 
     * @return VOLUMENESTRAMITACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] getVOLUMENESTRAMITACIONES() {
        return VOLUMENESTRAMITACIONES;
    }


    /**
     * Sets the VOLUMENESTRAMITACIONES value for this EDICIONESTADISTICA.
     * 
     * @param VOLUMENESTRAMITACIONES
     */
    public void setVOLUMENESTRAMITACIONES(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES) {
        this.VOLUMENESTRAMITACIONES = VOLUMENESTRAMITACIONES;
    }


    /**
     * Gets the TIEMPOMEDIORESOLUCION value for this EDICIONESTADISTICA.
     * 
     * @return TIEMPOMEDIORESOLUCION
     */
    public java.lang.String getTIEMPOMEDIORESOLUCION() {
        return TIEMPOMEDIORESOLUCION;
    }


    /**
     * Sets the TIEMPOMEDIORESOLUCION value for this EDICIONESTADISTICA.
     * 
     * @param TIEMPOMEDIORESOLUCION
     */
    public void setTIEMPOMEDIORESOLUCION(java.lang.String TIEMPOMEDIORESOLUCION) {
        this.TIEMPOMEDIORESOLUCION = TIEMPOMEDIORESOLUCION;
    }


    /**
     * Gets the VOLUMENNOTIFICACIONES value for this EDICIONESTADISTICA.
     * 
     * @return VOLUMENNOTIFICACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[] getVOLUMENNOTIFICACIONES() {
        return VOLUMENNOTIFICACIONES;
    }


    /**
     * Sets the VOLUMENNOTIFICACIONES value for this EDICIONESTADISTICA.
     * 
     * @param VOLUMENNOTIFICACIONES
     */
    public void setVOLUMENNOTIFICACIONES(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[] VOLUMENNOTIFICACIONES) {
        this.VOLUMENNOTIFICACIONES = VOLUMENNOTIFICACIONES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONESTADISTICA)) return false;
        EDICIONESTADISTICA other = (EDICIONESTADISTICA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.REDUCCIONES==null && other.getREDUCCIONES()==null) || 
             (this.REDUCCIONES!=null &&
              java.util.Arrays.equals(this.REDUCCIONES, other.getREDUCCIONES()))) &&
            ((this.VOLUMENESTRAMITACIONES==null && other.getVOLUMENESTRAMITACIONES()==null) || 
             (this.VOLUMENESTRAMITACIONES!=null &&
              java.util.Arrays.equals(this.VOLUMENESTRAMITACIONES, other.getVOLUMENESTRAMITACIONES()))) &&
            ((this.TIEMPOMEDIORESOLUCION==null && other.getTIEMPOMEDIORESOLUCION()==null) || 
             (this.TIEMPOMEDIORESOLUCION!=null &&
              this.TIEMPOMEDIORESOLUCION.equals(other.getTIEMPOMEDIORESOLUCION()))) &&
            ((this.VOLUMENNOTIFICACIONES==null && other.getVOLUMENNOTIFICACIONES()==null) || 
             (this.VOLUMENNOTIFICACIONES!=null &&
              java.util.Arrays.equals(this.VOLUMENNOTIFICACIONES, other.getVOLUMENNOTIFICACIONES())));
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
        if (getREDUCCIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getREDUCCIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getREDUCCIONES(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
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
        if (getTIEMPOMEDIORESOLUCION() != null) {
            _hashCode += getTIEMPOMEDIORESOLUCION().hashCode();
        }
        if (getVOLUMENNOTIFICACIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVOLUMENNOTIFICACIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVOLUMENNOTIFICACIONES(), i);
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
        new org.apache.axis.description.TypeDesc(EDICIONESTADISTICA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONESTADISTICA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REDUCCIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "REDUCCIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">REDUCCIONES>REDUCCION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "REDUCCION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLUMENESTRAMITACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "VOLUMENESTRAMITACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "VOLUMENTRAMITACIONES"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIEMPOMEDIORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "TIEMPOMEDIORESOLUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLUMENNOTIFICACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "VOLUMENNOTIFICACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">VOLUMENNOTIFICACIONES>VOLUMENNOTIFICACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "VOLUMENNOTIFICACION"));
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
