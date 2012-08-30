/**
 * TaxaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.taxa;

public class TaxaDTO  implements java.io.Serializable {
    private java.lang.String codificacio;

    private java.lang.String descripcio;

    private java.lang.String formaPagament;

    private long id;

    private java.lang.Long orden;

    private java.lang.Long tramit;

    public TaxaDTO() {
    }

    public TaxaDTO(
           java.lang.String codificacio,
           java.lang.String descripcio,
           java.lang.String formaPagament,
           long id,
           java.lang.Long orden,
           java.lang.Long tramit) {
           this.codificacio = codificacio;
           this.descripcio = descripcio;
           this.formaPagament = formaPagament;
           this.id = id;
           this.orden = orden;
           this.tramit = tramit;
    }


    /**
     * Gets the codificacio value for this TaxaDTO.
     * 
     * @return codificacio
     */
    public java.lang.String getCodificacio() {
        return codificacio;
    }


    /**
     * Sets the codificacio value for this TaxaDTO.
     * 
     * @param codificacio
     */
    public void setCodificacio(java.lang.String codificacio) {
        this.codificacio = codificacio;
    }


    /**
     * Gets the descripcio value for this TaxaDTO.
     * 
     * @return descripcio
     */
    public java.lang.String getDescripcio() {
        return descripcio;
    }


    /**
     * Sets the descripcio value for this TaxaDTO.
     * 
     * @param descripcio
     */
    public void setDescripcio(java.lang.String descripcio) {
        this.descripcio = descripcio;
    }


    /**
     * Gets the formaPagament value for this TaxaDTO.
     * 
     * @return formaPagament
     */
    public java.lang.String getFormaPagament() {
        return formaPagament;
    }


    /**
     * Sets the formaPagament value for this TaxaDTO.
     * 
     * @param formaPagament
     */
    public void setFormaPagament(java.lang.String formaPagament) {
        this.formaPagament = formaPagament;
    }


    /**
     * Gets the id value for this TaxaDTO.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this TaxaDTO.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the orden value for this TaxaDTO.
     * 
     * @return orden
     */
    public java.lang.Long getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this TaxaDTO.
     * 
     * @param orden
     */
    public void setOrden(java.lang.Long orden) {
        this.orden = orden;
    }


    /**
     * Gets the tramit value for this TaxaDTO.
     * 
     * @return tramit
     */
    public java.lang.Long getTramit() {
        return tramit;
    }


    /**
     * Sets the tramit value for this TaxaDTO.
     * 
     * @param tramit
     */
    public void setTramit(java.lang.Long tramit) {
        this.tramit = tramit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxaDTO)) return false;
        TaxaDTO other = (TaxaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codificacio==null && other.getCodificacio()==null) || 
             (this.codificacio!=null &&
              this.codificacio.equals(other.getCodificacio()))) &&
            ((this.descripcio==null && other.getDescripcio()==null) || 
             (this.descripcio!=null &&
              this.descripcio.equals(other.getDescripcio()))) &&
            ((this.formaPagament==null && other.getFormaPagament()==null) || 
             (this.formaPagament!=null &&
              this.formaPagament.equals(other.getFormaPagament()))) &&
            this.id == other.getId() &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.tramit==null && other.getTramit()==null) || 
             (this.tramit!=null &&
              this.tramit.equals(other.getTramit())));
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
        if (getCodificacio() != null) {
            _hashCode += getCodificacio().hashCode();
        }
        if (getDescripcio() != null) {
            _hashCode += getDescripcio().hashCode();
        }
        if (getFormaPagament() != null) {
            _hashCode += getFormaPagament().hashCode();
        }
        _hashCode += new Long(getId()).hashCode();
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getTramit() != null) {
            _hashCode += getTramit().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://taxa.v2.api.rolsac.caib.es", "TaxaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codificacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codificacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formaPagament");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formaPagament"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tramit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
