/**
 * ComunAltaEdicionAGRUPACIONSERV.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA;

public class ComunAltaEdicionAGRUPACIONSERV  implements java.io.Serializable {
    private java.lang.String AGRUPADO;

    private java.lang.String NUMSERAGRUPADOS;

    public ComunAltaEdicionAGRUPACIONSERV() {
    }

    public ComunAltaEdicionAGRUPACIONSERV(
           java.lang.String AGRUPADO,
           java.lang.String NUMSERAGRUPADOS) {
           this.AGRUPADO = AGRUPADO;
           this.NUMSERAGRUPADOS = NUMSERAGRUPADOS;
    }


    /**
     * Gets the AGRUPADO value for this ComunAltaEdicionAGRUPACIONSERV.
     * 
     * @return AGRUPADO
     */
    public java.lang.String getAGRUPADO() {
        return AGRUPADO;
    }


    /**
     * Sets the AGRUPADO value for this ComunAltaEdicionAGRUPACIONSERV.
     * 
     * @param AGRUPADO
     */
    public void setAGRUPADO(java.lang.String AGRUPADO) {
        this.AGRUPADO = AGRUPADO;
    }


    /**
     * Gets the NUMSERAGRUPADOS value for this ComunAltaEdicionAGRUPACIONSERV.
     * 
     * @return NUMSERAGRUPADOS
     */
    public java.lang.String getNUMSERAGRUPADOS() {
        return NUMSERAGRUPADOS;
    }


    /**
     * Sets the NUMSERAGRUPADOS value for this ComunAltaEdicionAGRUPACIONSERV.
     * 
     * @param NUMSERAGRUPADOS
     */
    public void setNUMSERAGRUPADOS(java.lang.String NUMSERAGRUPADOS) {
        this.NUMSERAGRUPADOS = NUMSERAGRUPADOS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicionAGRUPACIONSERV)) return false;
        ComunAltaEdicionAGRUPACIONSERV other = (ComunAltaEdicionAGRUPACIONSERV) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AGRUPADO==null && other.getAGRUPADO()==null) || 
             (this.AGRUPADO!=null &&
              this.AGRUPADO.equals(other.getAGRUPADO()))) &&
            ((this.NUMSERAGRUPADOS==null && other.getNUMSERAGRUPADOS()==null) || 
             (this.NUMSERAGRUPADOS!=null &&
              this.NUMSERAGRUPADOS.equals(other.getNUMSERAGRUPADOS())));
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
        if (getAGRUPADO() != null) {
            _hashCode += getAGRUPADO().hashCode();
        }
        if (getNUMSERAGRUPADOS() != null) {
            _hashCode += getNUMSERAGRUPADOS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicionAGRUPACIONSERV.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">comunAltaEdicion>AGRUPACIONSERV"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGRUPADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "AGRUPADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMSERAGRUPADOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "NUMSERAGRUPADOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
