/**
 * EntitatRemota.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.general;

public abstract class EntitatRemota  implements java.io.Serializable {
    private java.lang.Long administracionRemota;

    private java.lang.Long idExterno;

    private java.lang.String urlRemota;

    public EntitatRemota() {
    }

    public EntitatRemota(
           java.lang.Long administracionRemota,
           java.lang.Long idExterno,
           java.lang.String urlRemota) {
           this.administracionRemota = administracionRemota;
           this.idExterno = idExterno;
           this.urlRemota = urlRemota;
    }


    /**
     * Gets the administracionRemota value for this EntitatRemota.
     * 
     * @return administracionRemota
     */
    public java.lang.Long getAdministracionRemota() {
        return administracionRemota;
    }


    /**
     * Sets the administracionRemota value for this EntitatRemota.
     * 
     * @param administracionRemota
     */
    public void setAdministracionRemota(java.lang.Long administracionRemota) {
        this.administracionRemota = administracionRemota;
    }


    /**
     * Gets the idExterno value for this EntitatRemota.
     * 
     * @return idExterno
     */
    public java.lang.Long getIdExterno() {
        return idExterno;
    }


    /**
     * Sets the idExterno value for this EntitatRemota.
     * 
     * @param idExterno
     */
    public void setIdExterno(java.lang.Long idExterno) {
        this.idExterno = idExterno;
    }


    /**
     * Gets the urlRemota value for this EntitatRemota.
     * 
     * @return urlRemota
     */
    public java.lang.String getUrlRemota() {
        return urlRemota;
    }


    /**
     * Sets the urlRemota value for this EntitatRemota.
     * 
     * @param urlRemota
     */
    public void setUrlRemota(java.lang.String urlRemota) {
        this.urlRemota = urlRemota;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntitatRemota)) return false;
        EntitatRemota other = (EntitatRemota) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.administracionRemota==null && other.getAdministracionRemota()==null) || 
             (this.administracionRemota!=null &&
              this.administracionRemota.equals(other.getAdministracionRemota()))) &&
            ((this.idExterno==null && other.getIdExterno()==null) || 
             (this.idExterno!=null &&
              this.idExterno.equals(other.getIdExterno()))) &&
            ((this.urlRemota==null && other.getUrlRemota()==null) || 
             (this.urlRemota!=null &&
              this.urlRemota.equals(other.getUrlRemota())));
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
        if (getAdministracionRemota() != null) {
            _hashCode += getAdministracionRemota().hashCode();
        }
        if (getIdExterno() != null) {
            _hashCode += getIdExterno().hashCode();
        }
        if (getUrlRemota() != null) {
            _hashCode += getUrlRemota().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntitatRemota.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://general.v2.api.rolsac.caib.es", "EntitatRemota"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("administracionRemota");
        elemField.setXmlName(new javax.xml.namespace.QName("", "administracionRemota"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idExterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idExterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlRemota");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlRemota"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
