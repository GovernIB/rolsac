/**
 * EDICIONDOCUMENTACIONASOCIADA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA;

public class EDICIONDOCUMENTACIONASOCIADA  implements java.io.Serializable {
    private java.lang.String NOREQUIEREDOCUMENTACION;

    private java.lang.String DOCUMENTACION;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO;

    public EDICIONDOCUMENTACIONASOCIADA() {
    }

    public EDICIONDOCUMENTACIONASOCIADA(
           java.lang.String NOREQUIEREDOCUMENTACION,
           java.lang.String DOCUMENTACION,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO) {
           this.NOREQUIEREDOCUMENTACION = NOREQUIEREDOCUMENTACION;
           this.DOCUMENTACION = DOCUMENTACION;
           this.ALTADOCUMENTOSESPECIFICOS = ALTADOCUMENTOSESPECIFICOS;
           this.DOCUMENTOSCATALOGO = DOCUMENTOSCATALOGO;
    }


    /**
     * Gets the NOREQUIEREDOCUMENTACION value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @return NOREQUIEREDOCUMENTACION
     */
    public java.lang.String getNOREQUIEREDOCUMENTACION() {
        return NOREQUIEREDOCUMENTACION;
    }


    /**
     * Sets the NOREQUIEREDOCUMENTACION value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @param NOREQUIEREDOCUMENTACION
     */
    public void setNOREQUIEREDOCUMENTACION(java.lang.String NOREQUIEREDOCUMENTACION) {
        this.NOREQUIEREDOCUMENTACION = NOREQUIEREDOCUMENTACION;
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
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] getALTADOCUMENTOSESPECIFICOS() {
        return ALTADOCUMENTOSESPECIFICOS;
    }


    /**
     * Sets the ALTADOCUMENTOSESPECIFICOS value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @param ALTADOCUMENTOSESPECIFICOS
     */
    public void setALTADOCUMENTOSESPECIFICOS(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS) {
        this.ALTADOCUMENTOSESPECIFICOS = ALTADOCUMENTOSESPECIFICOS;
    }


    /**
     * Gets the DOCUMENTOSCATALOGO value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @return DOCUMENTOSCATALOGO
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] getDOCUMENTOSCATALOGO() {
        return DOCUMENTOSCATALOGO;
    }


    /**
     * Sets the DOCUMENTOSCATALOGO value for this EDICIONDOCUMENTACIONASOCIADA.
     * 
     * @param DOCUMENTOSCATALOGO
     */
    public void setDOCUMENTOSCATALOGO(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO) {
        this.DOCUMENTOSCATALOGO = DOCUMENTOSCATALOGO;
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
            ((this.NOREQUIEREDOCUMENTACION==null && other.getNOREQUIEREDOCUMENTACION()==null) || 
             (this.NOREQUIEREDOCUMENTACION!=null &&
              this.NOREQUIEREDOCUMENTACION.equals(other.getNOREQUIEREDOCUMENTACION()))) &&
            ((this.DOCUMENTACION==null && other.getDOCUMENTACION()==null) || 
             (this.DOCUMENTACION!=null &&
              this.DOCUMENTACION.equals(other.getDOCUMENTACION()))) &&
            ((this.ALTADOCUMENTOSESPECIFICOS==null && other.getALTADOCUMENTOSESPECIFICOS()==null) || 
             (this.ALTADOCUMENTOSESPECIFICOS!=null &&
              java.util.Arrays.equals(this.ALTADOCUMENTOSESPECIFICOS, other.getALTADOCUMENTOSESPECIFICOS()))) &&
            ((this.DOCUMENTOSCATALOGO==null && other.getDOCUMENTOSCATALOGO()==null) || 
             (this.DOCUMENTOSCATALOGO!=null &&
              java.util.Arrays.equals(this.DOCUMENTOSCATALOGO, other.getDOCUMENTOSCATALOGO())));
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
        if (getNOREQUIEREDOCUMENTACION() != null) {
            _hashCode += getNOREQUIEREDOCUMENTACION().hashCode();
        }
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EDICIONDOCUMENTACIONASOCIADA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "EDICIONDOCUMENTACIONASOCIADA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOREQUIEREDOCUMENTACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "NOREQUIEREDOCUMENTACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCUMENTACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "DOCUMENTACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTADOCUMENTOSESPECIFICOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "ALTADOCUMENTOSESPECIFICOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "ALTADOCUMENTOESPECIFICO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCUMENTOSCATALOGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "DOCUMENTOSCATALOGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/v3_0/ParamSIA", "DOCUMENTOCATALOGO"));
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
