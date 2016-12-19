/**
 * EDICIONREGULACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class EDICIONREGULACION  implements java.io.Serializable {
    private java.lang.String ORGANOINICIA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.INICIOS INICIOS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.PLAZORESOLUCION PLAZORESOLUCION;

    private java.lang.String FINVIA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES;

    public EDICIONREGULACION() {
    }

    public EDICIONREGULACION(
           java.lang.String ORGANOINICIA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.INICIOS INICIOS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.PLAZORESOLUCION PLAZORESOLUCION,
           java.lang.String FINVIA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES) {
           this.ORGANOINICIA = ORGANOINICIA;
           this.INICIOS = INICIOS;
           this.PLAZORESOLUCION = PLAZORESOLUCION;
           this.FINVIA = FINVIA;
           this.NORMATIVAS = NORMATIVAS;
           this.REDUCCIONES = REDUCCIONES;
    }


    /**
     * Gets the ORGANOINICIA value for this EDICIONREGULACION.
     * 
     * @return ORGANOINICIA
     */
    public java.lang.String getORGANOINICIA() {
        return ORGANOINICIA;
    }


    /**
     * Sets the ORGANOINICIA value for this EDICIONREGULACION.
     * 
     * @param ORGANOINICIA
     */
    public void setORGANOINICIA(java.lang.String ORGANOINICIA) {
        this.ORGANOINICIA = ORGANOINICIA;
    }


    /**
     * Gets the INICIOS value for this EDICIONREGULACION.
     * 
     * @return INICIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.INICIOS getINICIOS() {
        return INICIOS;
    }


    /**
     * Sets the INICIOS value for this EDICIONREGULACION.
     * 
     * @param INICIOS
     */
    public void setINICIOS(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.INICIOS INICIOS) {
        this.INICIOS = INICIOS;
    }


    /**
     * Gets the PLAZORESOLUCION value for this EDICIONREGULACION.
     * 
     * @return PLAZORESOLUCION
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.PLAZORESOLUCION getPLAZORESOLUCION() {
        return PLAZORESOLUCION;
    }


    /**
     * Sets the PLAZORESOLUCION value for this EDICIONREGULACION.
     * 
     * @param PLAZORESOLUCION
     */
    public void setPLAZORESOLUCION(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.PLAZORESOLUCION PLAZORESOLUCION) {
        this.PLAZORESOLUCION = PLAZORESOLUCION;
    }


    /**
     * Gets the FINVIA value for this EDICIONREGULACION.
     * 
     * @return FINVIA
     */
    public java.lang.String getFINVIA() {
        return FINVIA;
    }


    /**
     * Sets the FINVIA value for this EDICIONREGULACION.
     * 
     * @param FINVIA
     */
    public void setFINVIA(java.lang.String FINVIA) {
        this.FINVIA = FINVIA;
    }


    /**
     * Gets the NORMATIVAS value for this EDICIONREGULACION.
     * 
     * @return NORMATIVAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.NORMATIVASNORMATIVA[] getNORMATIVAS() {
        return NORMATIVAS;
    }


    /**
     * Sets the NORMATIVAS value for this EDICIONREGULACION.
     * 
     * @param NORMATIVAS
     */
    public void setNORMATIVAS(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS) {
        this.NORMATIVAS = NORMATIVAS;
    }


    /**
     * Gets the REDUCCIONES value for this EDICIONREGULACION.
     * 
     * @return REDUCCIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.REDUCCIONESREDUCCION[] getREDUCCIONES() {
        return REDUCCIONES;
    }


    /**
     * Sets the REDUCCIONES value for this EDICIONREGULACION.
     * 
     * @param REDUCCIONES
     */
    public void setREDUCCIONES(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES) {
        this.REDUCCIONES = REDUCCIONES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONREGULACION)) return false;
        EDICIONREGULACION other = (EDICIONREGULACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ORGANOINICIA==null && other.getORGANOINICIA()==null) || 
             (this.ORGANOINICIA!=null &&
              this.ORGANOINICIA.equals(other.getORGANOINICIA()))) &&
            ((this.INICIOS==null && other.getINICIOS()==null) || 
             (this.INICIOS!=null &&
              this.INICIOS.equals(other.getINICIOS()))) &&
            ((this.PLAZORESOLUCION==null && other.getPLAZORESOLUCION()==null) || 
             (this.PLAZORESOLUCION!=null &&
              this.PLAZORESOLUCION.equals(other.getPLAZORESOLUCION()))) &&
            ((this.FINVIA==null && other.getFINVIA()==null) || 
             (this.FINVIA!=null &&
              this.FINVIA.equals(other.getFINVIA()))) &&
            ((this.NORMATIVAS==null && other.getNORMATIVAS()==null) || 
             (this.NORMATIVAS!=null &&
              java.util.Arrays.equals(this.NORMATIVAS, other.getNORMATIVAS()))) &&
            ((this.REDUCCIONES==null && other.getREDUCCIONES()==null) || 
             (this.REDUCCIONES!=null &&
              java.util.Arrays.equals(this.REDUCCIONES, other.getREDUCCIONES())));
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
        if (getORGANOINICIA() != null) {
            _hashCode += getORGANOINICIA().hashCode();
        }
        if (getINICIOS() != null) {
            _hashCode += getINICIOS().hashCode();
        }
        if (getPLAZORESOLUCION() != null) {
            _hashCode += getPLAZORESOLUCION().hashCode();
        }
        if (getFINVIA() != null) {
            _hashCode += getFINVIA().hashCode();
        }
        if (getNORMATIVAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNORMATIVAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNORMATIVAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONREGULACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONREGULACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGANOINICIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ORGANOINICIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INICIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "INICIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "INICIOS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PLAZORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "PLAZORESOLUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "PLAZORESOLUCION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FINVIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "FINVIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NORMATIVAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "NORMATIVAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">NORMATIVAS>NORMATIVA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "NORMATIVA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REDUCCIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "REDUCCIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">REDUCCIONES>REDUCCION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "REDUCCION"));
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
