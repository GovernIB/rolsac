/**
 * EDICIONDOCUMENTACIONASOCIADA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA;

public class EDICIONDOCUMENTACIONASOCIADA  implements java.io.Serializable {
    private java.lang.String DOCUMENTACION;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES;

    public EDICIONDOCUMENTACIONASOCIADA() {
    }

    public EDICIONDOCUMENTACIONASOCIADA(
           java.lang.String DOCUMENTACION,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES) {
           this.DOCUMENTACION = DOCUMENTACION;
           this.ALTADOCUMENTOSESPECIFICOS = ALTADOCUMENTOSESPECIFICOS;
           this.DOCUMENTOSCATALOGO = DOCUMENTOSCATALOGO;
           this.REDUCCIONES = REDUCCIONES;
    }


    /**
     * Gets the DOCUMENTACION value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @return DOCUMENTACION
     */
    public java.lang.String getDOCUMENTACION() {
        return DOCUMENTACION;
    }


    /**
     * Sets the DOCUMENTACION value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @param DOCUMENTACION
     */
    public void setDOCUMENTACION(java.lang.String DOCUMENTACION) {
        this.DOCUMENTACION = DOCUMENTACION;
    }


    /**
     * Gets the ALTADOCUMENTOSESPECIFICOS value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @return ALTADOCUMENTOSESPECIFICOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] getALTADOCUMENTOSESPECIFICOS() {
        return ALTADOCUMENTOSESPECIFICOS;
    }


    /**
     * Sets the ALTADOCUMENTOSESPECIFICOS value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @param ALTADOCUMENTOSESPECIFICOS
     */
    public void setALTADOCUMENTOSESPECIFICOS(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS) {
        this.ALTADOCUMENTOSESPECIFICOS = ALTADOCUMENTOSESPECIFICOS;
    }


    /**
     * Gets the DOCUMENTOSCATALOGO value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @return DOCUMENTOSCATALOGO
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] getDOCUMENTOSCATALOGO() {
        return DOCUMENTOSCATALOGO;
    }


    /**
     * Sets the DOCUMENTOSCATALOGO value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @param DOCUMENTOSCATALOGO
     */
    public void setDOCUMENTOSCATALOGO(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO) {
        this.DOCUMENTOSCATALOGO = DOCUMENTOSCATALOGO;
    }


    /**
     * Gets the REDUCCIONES value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @return REDUCCIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.REDUCCIONESREDUCCION[] getREDUCCIONES() {
        return REDUCCIONES;
    }


    /**
     * Sets the REDUCCIONES value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @param REDUCCIONES
     */
    public void setREDUCCIONES(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.REDUCCIONESREDUCCION[] REDUCCIONES) {
        this.REDUCCIONES = REDUCCIONES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EDICIONDOCUMENTACIONASOCIADA)) return false;
        EDICIONDOCUMENTACIONASOCIADA other = (EDICIONDOCUMENTACIONASOCIADA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DOCUMENTACION==null && other.getDOCUMENTACION()==null) || 
             (this.DOCUMENTACION!=null &&
              this.DOCUMENTACION.equals(other.getDOCUMENTACION()))) &&
            ((this.ALTADOCUMENTOSESPECIFICOS==null && other.getALTADOCUMENTOSESPECIFICOS()==null) || 
             (this.ALTADOCUMENTOSESPECIFICOS!=null &&
              java.util.Arrays.equals(this.ALTADOCUMENTOSESPECIFICOS, other.getALTADOCUMENTOSESPECIFICOS()))) &&
            ((this.DOCUMENTOSCATALOGO==null && other.getDOCUMENTOSCATALOGO()==null) || 
             (this.DOCUMENTOSCATALOGO!=null &&
              java.util.Arrays.equals(this.DOCUMENTOSCATALOGO, other.getDOCUMENTOSCATALOGO()))) &&
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
        if (getDOCUMENTACION() != null) {
            _hashCode += getDOCUMENTACION().hashCode();
        }
        if (getALTADOCUMENTOSESPECIFICOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getALTADOCUMENTOSESPECIFICOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getALTADOCUMENTOSESPECIFICOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDOCUMENTOSCATALOGO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDOCUMENTOSCATALOGO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDOCUMENTOSCATALOGO(), i);
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
        new org.apache.axis.description.TypeDesc(EDICIONDOCUMENTACIONASOCIADA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "EDICIONDOCUMENTACIONASOCIADA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCUMENTACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "DOCUMENTACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTADOCUMENTOSESPECIFICOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ALTADOCUMENTOSESPECIFICOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "ALTADOCUMENTOESPECIFICO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCUMENTOSCATALOGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "DOCUMENTOSCATALOGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v2_4/ParamSIA", "DOCUMENTOCATALOGO"));
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
