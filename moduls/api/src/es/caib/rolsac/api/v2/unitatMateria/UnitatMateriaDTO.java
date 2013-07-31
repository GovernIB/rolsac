/**
 * UnitatMateriaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.unitatMateria;

public class UnitatMateriaDTO  implements java.io.Serializable {
    private long id;

    private java.lang.Long materia;

    private java.lang.Long unidad;

    private java.lang.String unidadPrincipal;

    private java.lang.String urlUnidadMateria;

    public UnitatMateriaDTO() {
    }

    public UnitatMateriaDTO(
           long id,
           java.lang.Long materia,
           java.lang.Long unidad,
           java.lang.String unidadPrincipal,
           java.lang.String urlUnidadMateria) {
           this.id = id;
           this.materia = materia;
           this.unidad = unidad;
           this.unidadPrincipal = unidadPrincipal;
           this.urlUnidadMateria = urlUnidadMateria;
    }


    /**
     * Gets the id value for this UnitatMateriaDTO.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this UnitatMateriaDTO.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the materia value for this UnitatMateriaDTO.
     * 
     * @return materia
     */
    public java.lang.Long getMateria() {
        return materia;
    }


    /**
     * Sets the materia value for this UnitatMateriaDTO.
     * 
     * @param materia
     */
    public void setMateria(java.lang.Long materia) {
        this.materia = materia;
    }


    /**
     * Gets the unidad value for this UnitatMateriaDTO.
     * 
     * @return unidad
     */
    public java.lang.Long getUnidad() {
        return unidad;
    }


    /**
     * Sets the unidad value for this UnitatMateriaDTO.
     * 
     * @param unidad
     */
    public void setUnidad(java.lang.Long unidad) {
        this.unidad = unidad;
    }


    /**
     * Gets the unidadPrincipal value for this UnitatMateriaDTO.
     * 
     * @return unidadPrincipal
     */
    public java.lang.String getUnidadPrincipal() {
        return unidadPrincipal;
    }


    /**
     * Sets the unidadPrincipal value for this UnitatMateriaDTO.
     * 
     * @param unidadPrincipal
     */
    public void setUnidadPrincipal(java.lang.String unidadPrincipal) {
        this.unidadPrincipal = unidadPrincipal;
    }


    /**
     * Gets the urlUnidadMateria value for this UnitatMateriaDTO.
     * 
     * @return urlUnidadMateria
     */
    public java.lang.String getUrlUnidadMateria() {
        return urlUnidadMateria;
    }


    /**
     * Sets the urlUnidadMateria value for this UnitatMateriaDTO.
     * 
     * @param urlUnidadMateria
     */
    public void setUrlUnidadMateria(java.lang.String urlUnidadMateria) {
        this.urlUnidadMateria = urlUnidadMateria;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UnitatMateriaDTO)) return false;
        UnitatMateriaDTO other = (UnitatMateriaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.materia==null && other.getMateria()==null) || 
             (this.materia!=null &&
              this.materia.equals(other.getMateria()))) &&
            ((this.unidad==null && other.getUnidad()==null) || 
             (this.unidad!=null &&
              this.unidad.equals(other.getUnidad()))) &&
            ((this.unidadPrincipal==null && other.getUnidadPrincipal()==null) || 
             (this.unidadPrincipal!=null &&
              this.unidadPrincipal.equals(other.getUnidadPrincipal()))) &&
            ((this.urlUnidadMateria==null && other.getUrlUnidadMateria()==null) || 
             (this.urlUnidadMateria!=null &&
              this.urlUnidadMateria.equals(other.getUrlUnidadMateria())));
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
        _hashCode += new Long(getId()).hashCode();
        if (getMateria() != null) {
            _hashCode += getMateria().hashCode();
        }
        if (getUnidad() != null) {
            _hashCode += getUnidad().hashCode();
        }
        if (getUnidadPrincipal() != null) {
            _hashCode += getUnidadPrincipal().hashCode();
        }
        if (getUrlUnidadMateria() != null) {
            _hashCode += getUrlUnidadMateria().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UnitatMateriaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://unitatMateria.v2.api.rolsac.caib.es", "UnitatMateriaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "materia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlUnidadMateria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlUnidadMateria"));
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
